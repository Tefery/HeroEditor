package aventureroDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiaDeSeguridad extends Thread {
	
	String ruta;
	
	public CopiaDeSeguridad(String ruta) {
		this.ruta = ruta;
	}
	
	@Override
	public synchronized void start() {

		try {
			File inFile = new File(ruta);
			File outFile = new File(ruta+".old");

			/*FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while ((c = in.read()) != -1)
				out.write(c);*/
			InputStream in = new FileInputStream(inFile);
			OutputStream out = new FileOutputStream(outFile);
			
			byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            
            in.close();
            out.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

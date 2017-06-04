package aventureroDAO;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import aventureroUI.VentanaAventurero;

public class HiloActualiza extends Thread {

	Socket conexion;
	String host;
	int puerto;
	AlteraDatosBD db;
	VentanaAventurero va;

	public HiloActualiza(String host, int puerto, AlteraDatosBD db, VentanaAventurero va) {
		this.host = host;
		this.puerto = puerto;
		this.db = db;
		this.va = va;
		this.start();
	}

	@Override
	public void run() {
		try {
			conexion = new Socket(host, puerto);
			PrintStream salida = new PrintStream(conexion.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			int version = Integer.parseInt(entrada.readLine());
			if (version > db.versionBaseDeDatos()) {
				if (JOptionPane.showConfirmDialog(null, "Hay nuevas clases y razas, ¿Desea descargarlas?", "Atención",
						0, 3) == 0) {
					salida.println("outD");
					db.actualizaDataBase(conexion, entrada, salida);
					va.actualizaTodo();
				} else {
					salida.println("close");
				}
			} else {
				salida.println("close");
			}
			conexion.close();
		} catch (IOException | HeadlessException | SQLException e) {
		} catch (ClassNotFoundException e) {
		}
	}
}

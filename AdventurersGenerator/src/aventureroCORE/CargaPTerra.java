package aventureroCORE;

import java.awt.Font;
import java.io.InputStream;

/**
 * Crea una fuente "PTerra" (La usada en los manuales de D&D)
 * 
 * @see java.awt.Font
 * @author jc mouse
 * @version 1.0
 */
public class CargaPTerra {

	private Font font = null;

	/**
	 * Crea un nuevo CargaPTerra. Carga el archivo ttf en la clase, y si no lo
	 * consige, carga "Arial".
	 * 
	 */
	public CargaPTerra() {
		String fontName = "PTerra.ttf";
		try {
			// Se carga la fuente
			InputStream is = getClass().getResourceAsStream(fontName);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception ex) {
			// Si existe un error se carga fuente por defecto ARIAL
			System.err.println(fontName + " No se cargo la fuente");
			font = new Font("Arial", Font.PLAIN, 14);
		}
	}

	/**
	 * Devuelve la fuente. Con la configuracion que le mandes por parametros.
	 * 
	 * @see java.awt.Font.#deriveFont(int, float)
	 * @param estilo
	 *            PLAIN = 0 , BOLD = 1 , ITALIC = 2
	 * @param tamanio
	 *            Tamaño para la fuente.
	 * @return Esta fuente configurada como indiquen los parametros.
	 */
	public Font MyFont(int estilo, float tamanio) {
		Font tfont = font.deriveFont(estilo, tamanio);
		return tfont;
	}

}
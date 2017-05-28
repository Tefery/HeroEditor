package aventureroDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.JDBC;

/**
 * Se conecta a una DB SQLite. Se encarga de establecer las conexiones.
 * 
 * @author Tefery
 * @version 1.0
 * @since 1.0
 */
public class ConexionBD {

	/**
	 * Establece una conexion con SQLite. Se conecta a la base de datos
	 * HEROEDITOR.
	 * 
	 * @see #creaConexion(String)
	 * @return La conexion a la base de datos.
	 * @throws SQLException
	 *             Si no consigue establecer la conexión.
	 */
	public static Connection creaConexion() throws SQLException {
		return creaConexion(System.getProperty("user.home"));
	}

	/**
	 * Establece una conexion con SQLite. Se conecta a la base de datos
	 * HEROEDITOR.
	 * 
	 * @see #creaConexion()
	 * @param directorio
	 *            La ubicacion donde se ubica la BD.
	 * @return La conexion a la base de datos.
	 * @throws SQLException
	 *             Si no consigue establecer la conexión.
	 */
	public static Connection creaConexion(String directorio) throws SQLException {
		Connection connection;
		new File(directorio+"\\HeroEditor").mkdir();
		String url = "jdbc:sqlite:" + directorio + "\\HeroEditor\\HEROEDITOR.db";
		connection = DriverManager.getConnection(url);
		return connection;
	}

	/**
	 * Carga el driver MySQL
	 * 
	 */
	public static void cargaElDriver() {
		try {
			DriverManager.registerDriver(new JDBC());
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

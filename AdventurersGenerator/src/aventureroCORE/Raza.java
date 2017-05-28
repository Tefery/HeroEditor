package aventureroCORE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import aventureroDAO.AlteraDatosBD;
import aventureroDAO.ConexionBD;

/**
 * Contiene la información de una Raza. Para despues ser implementada en un
 * aventurero.
 * <p>
 * La inicialización sin parametros crea un humano.
 * </p>
 * <p>
 * Las posibles construcciones son <code>Raza()</code> y
 * <code>Raza(String)</code>.
 * </p>
 * 
 * @see AlteraDatosBD
 * @see Aventurero
 * @author Tefery
 * @version 0.4.6
 * @since 0.4.6
 */
public class Raza {
	private String Nombre;
	private int FuerzaExtra;
	private int DestrezaExtra;
	private int ConstitucionExtra;
	private int InteligenciaExtra;
	private int SabiduriaExtra;
	private int CarismaExtra;
	private char Tamaño;
	private String Descripcion;

	/**
	 * Crea una Raza del tipo humano.
	 * 
	 */
	public Raza() {
		this.setNombre("HUMANO");
		this.setFuerzaExtra(0);
		this.setDestrezaExtra(0);
		this.setConstitucionExtra(0);
		this.setInteligenciaExtra(0);
		this.setSabiduriaExtra(0);
		this.setCarismaExtra(0);
		this.setTamaño('M');
		this.setDescripcion("");
	}

	/**
	 * Crea una Raza del nombre indicado.
	 * 
	 * @see #cambiaRaza(String)
	 * @param raza
	 *            Nombre de la Raza.
	 */
	public Raza(String raza) {
		this.cambiaRaza(raza.toUpperCase());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Raza))
			return false;
		Raza raza = (Raza) o;
		if (this.getNombre().equals(raza.getNombre()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return this.getNombre().toLowerCase() + ", Tamaño: " + getTamaño() + ", Fue: " + getFuerzaExtra() + ", Des: "
				+ getDestrezaExtra() + ", Cons: " + getConstitucionExtra() + ", Int: " + getInteligenciaExtra()
				+ ", Sab: " + getSabiduriaExtra() + ", Char: " + getCarismaExtra();
	}

	/**
	 * Cambia de Raza. Cambia los atributos de esta Raza por los de la indicada.
	 * 
	 * <p>
	 * Realiza una consulta a la base de datos y busca los atributos de la Raza
	 * con el nombre de entrada.
	 * </p>
	 * 
	 * @see AlteraDatosBD
	 * @param raza
	 *            Nombre de la Raza.
	 */
	public void cambiaRaza(String raza) {
		try {
			this.setNombre(raza);
			Connection conn;
			conn = ConexionBD.creaConexion();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RAZAS WHERE NOMBRE=?");
			stmt.setString(1, raza);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			this.setFuerzaExtra(rs.getInt(2));
			this.setDestrezaExtra(rs.getInt(3));
			this.setConstitucionExtra(rs.getInt(4));
			this.setInteligenciaExtra(rs.getInt(5));
			this.setSabiduriaExtra(rs.getInt(6));
			this.setCarismaExtra(rs.getInt(7));
			this.setTamaño(rs.getString(8).charAt(0));
			this.setDescripcion(rs.getString(9));
			// this.setFoto(null);
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Esa raza no existe en el manual basico");
		}
	}

	/**
	 * Establece la {@link #Descripcion} de esta Clase.
	 * 
	 * @see #getDescripcion()
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

	/**
	 * Establece el {@link #Nombre} de esta Clase.
	 * 
	 * @see #getNombre()
	 * @param nombre
	 */
	protected void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	/**
	 * Establece la {@link #FuerzaExtra} de esta Clase.
	 * 
	 * @see #getFuerzaExtra()
	 * @param fuerzaExtra
	 */
	public void setFuerzaExtra(int fuerzaExtra) {
		this.FuerzaExtra = fuerzaExtra;
	}

	/**
	 * Establece la {@link #DestrezaExtra} de esta Clase.
	 * 
	 * @see #getDestrezaExtra()
	 * @param destrezaExtra
	 */
	public void setDestrezaExtra(int destrezaExtra) {
		this.DestrezaExtra = destrezaExtra;
	}

	/**
	 * Establece la {@link #ConstitucionExtra} de esta Clase.
	 * 
	 * @see #getConstitucionExtra()
	 * @param constitucionExtra
	 */
	public void setConstitucionExtra(int constitucionExtra) {
		this.ConstitucionExtra = constitucionExtra;
	}

	/**
	 * Establece la {@link #InteligenciaExtra} de esta Clase.
	 * 
	 * @see #getInteligenciaExtra()
	 * @param inteligenciaExtra
	 */
	public void setInteligenciaExtra(int inteligenciaExtra) {
		this.InteligenciaExtra = inteligenciaExtra;
	}

	/**
	 * Establece la {@link #SabiduriaExtra} de esta Clase.
	 * 
	 * @see #getSabiduriaExtra()
	 * @param sabiduriaExtra
	 */
	public void setSabiduriaExtra(int sabiduriaExtra) {
		this.SabiduriaExtra = sabiduriaExtra;
	}

	/**
	 * Establece el {@link #CarismaExtra} de esta Clase.
	 * 
	 * @see #getCarismaExtra()
	 * @param carismaExtra
	 */
	public void setCarismaExtra(int carismaExtra) {
		this.CarismaExtra = carismaExtra;
	}

	/**
	 * Establece el {@link #Tamaño} de esta Clase.
	 * 
	 * @see #getTamaño()
	 * @param tamaño
	 */
	public void setTamaño(char tamaño) {
		this.Tamaño = tamaño;
	}

	public String getNombre() {
		return this.Nombre;
	}

	public int getFuerzaExtra() {
		return this.FuerzaExtra;
	}

	public int getDestrezaExtra() {
		return this.DestrezaExtra;
	}

	public int getConstitucionExtra() {
		return this.ConstitucionExtra;
	}

	public int getInteligenciaExtra() {
		return this.InteligenciaExtra;
	}

	public int getSabiduriaExtra() {
		return this.SabiduriaExtra;
	}

	public int getCarismaExtra() {
		return this.CarismaExtra;
	}

	public char getTamaño() {
		return Tamaño;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	/**
	 * Devuelve la Foto de esta Raza. Utilizando el nombre, extraida de la base
	 * de datos.
	 * 
	 * @see AlteraDatosBD#claseRazaToBytes(String)
	 * @return Imagen en un mapa de <code>byte[]</code>
	 */
	public byte[] getFoto() {
		return AlteraDatosBD.claseRazaToBytes(Nombre);
	}
}

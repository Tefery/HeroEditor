package aventureroCORE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import aventureroDAO.AlteraDatosBD;
import aventureroDAO.ConexionBD;

/**
 * Contiene la información de una Clase. Para despues ser implementada en un
 * aventurero.
 * <p>
 * La inicialización sin parametros crea un guerrero.
 * </p>
 * <p>
 * Las posibles construcciones son <code>Clase()</code> y
 * <code>Clase(String)</code>.
 * </p>
 * 
 * @see AlteraDatosBD
 * @see Aventurero
 * @see Habilidades
 * @author Tefery
 * @version 0.4.6
 * @since 0.4.6
 */
public class Clase {

	private String Nombre;
	// private String[] Facultades;
	private boolean TieneMagia;
	private int Habilidades;
	private int DadoDeGolpe;
	private String Descripcion;

	/**
	 * Crea una Clase de tipo Guerrero.
	 * 
	 */
	public Clase() {
		this.setNombre("GUERRERO");
		this.setTieneMagia(false);
		this.setHabilidades(2);
		this.setDadoDeGolpe(10);
		this.setDescripcion("");
	}

	/**
	 * Crea una Clase del nombre indicado.
	 * 
	 * @see #cambiaClase(String)
	 * @param nombre
	 *            Nombre de la Clase.
	 */
	public Clase(String nombre) {
		this.cambiaClase(nombre.toUpperCase());
	}

	/**
	 * Cambia de Clase. Cambia los atributos de esta Clase por los de la
	 * indicada.
	 * 
	 * <p>
	 * Realiza una consulta a la base de datos y busca los atributos de la Clase
	 * con el nombre de entrada.
	 * </p>
	 * 
	 * @see AlteraDatosBD
	 * @param nombre
	 *            Nombre de la Clase.
	 */
	protected void cambiaClase(String nombre) {
		try {
			this.setNombre(nombre);
			Connection conn = ConexionBD.creaConexion();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CLASES WHERE NOMBRE=?");
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			this.setDescripcion(rs.getString(2));
			this.setTieneMagia(rs.getBoolean(3));
			this.setHabilidades(rs.getInt(4));
			this.setDadoDeGolpe(rs.getInt(5));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Esa clase no existe en el manual basico");
		}
	}

	@Override
	public String toString() {
		return this.getNombre().toLowerCase() + ", Magico: " + isTieneMagia() + ", Habilidades por nivel: "
				+ getHabilidades() + ", Dade de golpe: d" + getDadoDeGolpe();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Clase))
			return false;
		Clase clase = (Clase) o;
		if (this.getNombre().equals(clase.getNombre()))
			return true;
		else
			return false;
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
	 * Establece si esta Clase {@link #TieneMagia}.
	 * 
	 * @see #isTieneMagia()
	 * @param tieneMagia
	 */
	public void setTieneMagia(boolean tieneMagia) {
		this.TieneMagia = tieneMagia;
	}

	/**
	 * Establece los {@link #DadoDeGolpe} de esta Clase.
	 * 
	 * @see #getDadoDeGolpe()
	 * @param dadoDeGolpe
	 */
	public void setDadoDeGolpe(int dadoDeGolpe) {
		this.DadoDeGolpe = dadoDeGolpe;
	}

	/**
	 * Establece las {@link #Habilidades} iniciales de esta Clase.
	 * 
	 * @see #getHabilidades()
	 * @param habilidades
	 */
	public void setHabilidades(int habilidades) {
		Habilidades = habilidades;
	}

	/**
	 * Establece la {@link #descripcion} de esta Clase.
	 * 
	 * @see #getDescripcion()
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public boolean isTieneMagia() {
		return this.TieneMagia;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public String getNombre() {
		return this.Nombre;
	}

	public int getHabilidades() {
		return this.Habilidades;
	}

	public int getDadoDeGolpe() {
		return this.DadoDeGolpe;
	}

	/**
	 * Devuelve la Foto de esta Clase. Utilizando el nombre, extraida de la base
	 * de datos.
	 * 
	 * @see AlteraDatosBD#claseRazaToBytes(String)
	 * @return Imagen en un mapa de <code>byte[]</code>
	 */
	public byte[] getFoto() {
		return AlteraDatosBD.claseRazaToBytes(Nombre);
	}
}

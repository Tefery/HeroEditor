package aventureroCORE;

import aventureroDAO.AlteraDatosBD;

/**
 * Contiene un aventurero. Contiene todas las caracteristicas necesarias para
 * una ficha de D&D 3.5
 * <p>
 * La inicialización sin parametros crea un humano guerrero a nivel 1, con todas
 * las caracteristicas al 8, y con la imagen del guerrero estandard del manual
 * del jugador
 * </p>
 * <p>
 * Las posibles construcciones son <code>Aventurero()</code>,
 * <code>Aventurero(String)</code> y
 * <code>Aventurero(String, String, String)</code>.
 * </p>
 * 
 * @see Clase
 * @see Raza
 * @see Habilidades
 * @author Tefery
 * @version 0.3.6
 * @since 0.3.6
 */
public class Aventurero {
	private int PuntosTotales;
	private int Nivel;
	private int Vida;
	private int Fuerza;
	private int Destreza;
	private int Constitucion;
	private int Inteligencia;
	private int Sabiduria;
	private int Carisma;
	private byte[] Foto;
	private Raza RazaFicha;
	private Clase ClaseFicha;
	private String Nombre;
	private String Jugador;
	private String Aventura;
	private String Master;
	private String Informacion;

	private int FuerzaTotal;
	private int DestrezaTotal;
	private int ConstitucionTotal;
	private int InteligenciaTotal;
	private int SabiduriaTotal;
	private int CarismaTotal;

	// private Habilidades habilidades;
	/**
	 * Crea un nuevo Aventurero sin nombre. Un humano guerrero a nivel 1 y los
	 * stats al 8.
	 * 
	 */
	public Aventurero() {
		this.ClaseFicha = new Clase();
		this.RazaFicha = new Raza();
		this.setNivel(1);
		this.setFuerza(8);
		this.setDestreza(8);
		this.setConstitucion(8);
		this.setInteligencia(8);
		this.setSabiduria(8);
		this.setCarisma(8);
		this.setPuntosTotales(28);
		this.setFoto(AlteraDatosBD.claseRazaToBytes("guerrero"));
		this.setNombre("");
		this.setVida(dadoDeGolpe() + calculaModificador(this.getConstitucionTotal()));
		this.setJugador("");
		this.setAventura("Aventura");
		this.setMaster("Master");
		this.setInfo("");
		this.setFoto(this.getClaseFicha().getFoto());
		actualizaCaracteristicas();
	}

	/**
	 * Crea un nuevo Aventurero con el nombre indicado.
	 * 
	 * @see #inicializa(String, String, String)
	 * @param nombre
	 *            El nombre que queremos dar a este aventurero
	 */
	public Aventurero(String nombre) {
		inicializa(nombre, "guerrero", "humano");
	}

	/**
	 * Crea un nuevo Aventurero con el nombre, clase y raza indicados.
	 * 
	 * @see #inicializa(String, String, String)
	 * @param nombre
	 *            Nombre que queremos dar a este aventurero
	 * @param clase
	 *            {@link Clase} que queremos dar a este aventurero
	 * @param raza
	 *            {@link Raza} que queremos dar a este aventurero
	 */
	public Aventurero(String nombre, String clase, String raza) {
		inicializa(nombre, clase, raza);
	}

	/**
	 * Inicializa los valores iniciales de este aventurero. A nivel 1 y con los
	 * stats al 8.
	 * 
	 * <p>
	 * Adicionalmente asigna la {@link Clase}, la {@link Raza} y la foto a este
	 * aventurero. Llama a los metodos necesarios para el calculo de las demás
	 * estadisticas.
	 * </p>
	 * 
	 * @see #actualizaCaracteristicas()
	 * @see aventureroDAO.AlteraDatosBD#claseRazaToBytes(String)
	 * @param nombre
	 *            Nombre que queremos dar a este aventurero
	 * @param clase
	 *            {@link Clase} que queremos dar a este aventurero
	 * @param raza
	 *            {@link Raza} que queremos dar a este aventurero
	 */
	private void inicializa(String nombre, String clase, String raza) {
		this.setClaseFicha(clase);
		this.setRazaFicha(raza);
		this.setPuntosTotales(28);
		this.setNivel(1);
		this.setFuerza(8);
		this.setDestreza(8);
		this.setConstitucion(8);
		this.setInteligencia(8);
		this.setSabiduria(8);
		this.setCarisma(8);
		this.setFoto(AlteraDatosBD.claseRazaToBytes(this.getClaseFicha().getNombre()));
		this.setNombre(nombre);
		this.setVida(dadoDeGolpe() + calculaModificador(this.getConstitucionTotal()));
		this.setJugador("");
		this.setAventura("Aventura");
		this.setMaster("Master");
		this.setInfo("");
		this.setFoto(this.getClaseFicha().getFoto());
		actualizaCaracteristicas();
	}

	/**
	 * Calcula los cambios de stat de este aventurero. suma o resta un punto a
	 * la estadistica enviada, devolviendo el total en caso de ser posible.
	 * 
	 * <p>
	 * Si llamasemos a <code>cambiaStat(1,16)</code>, devolvería 17 en caso de
	 * haber {@link #PuntosTotales} suficientes, y los resta del total. Si
	 * llamasemos a <code>cambiaStat(-1,16)</code>, devolveria 15 y devuelve los
	 * puntos restantes. Si llamasemos a <code>cambiaStat(1,18)</code>,
	 * devolveria 18 al no haber podido cambiar el stat.
	 * </p>
	 * 
	 * @see #puedeCambiarStat(int, int)
	 * @see #cuantoCuestaElSiguietePuntoDeStat(int, int)
	 * @see #setPuntosTotales(int)
	 * @param sumaResta
	 *            1 si quieres sumar, -1 si quieres restar.
	 * @param stat
	 *            puntuacion actual de la caracteristica.
	 * @return <code>int</code> puntuacion final de la caracteristica
	 */
	public int cambiaStat(int sumaResta, int stat) {
		if (puedeCambiarStat(sumaResta, stat)) {
			this.setPuntosTotales(this.getPuntosTotales() - cuantoCuestaElSiguietePuntoDeStat(sumaResta, stat));
			return stat + sumaResta;
		} else {
			return stat;
		}
	}

	/**
	 * Calcula si puede o no cambiar el stat.
	 * 
	 * @param sumaResta
	 *            1 si quieres sumar, -1 si quieres restar.
	 * @param stat
	 *            puntuacion actual de la caracteristica.
	 * @return <code>true</code> si puede cambiar el stat, <code>false</code> en
	 *         caso negativo.
	 */
	public boolean puedeCambiarStat(int sumaResta, int stat) {
		if (sumaResta == -1 && stat == 8 || sumaResta == 1 && stat == 18 || sumaResta == 0 || sumaResta > 1
				|| sumaResta < -1 || cuantoCuestaElSiguietePuntoDeStat(sumaResta, stat) == 0
				|| cuantoCuestaElSiguietePuntoDeStat(sumaResta, stat) > this.getPuntosTotales())
			return false;
		else
			return true;
	}

	/**
	 * Calcula la diferencia de {@link #PuntosTotales}.
	 * 
	 * @param sumaResta
	 *            1 si quieres sumar, -1 si quieres restar.
	 * @param stat
	 *            puntuacion actual de la caracteristica.
	 * @return La diferencia de puntos necesarios.
	 */
	private int cuantoCuestaElSiguietePuntoDeStat(int sumaResta, int stat) {
		if (stat >= 8 && stat <= 13 && sumaResta == 1 || stat >= 9 && stat <= 14 && sumaResta == -1) {
			return 1 * sumaResta;
		} else if (stat >= 14 && stat <= 15 && sumaResta == 1 || stat >= 15 && stat <= 16 && sumaResta == -1) {
			return 2 * sumaResta;
		} else if (stat >= 16 && stat <= 17 && sumaResta == 1 || stat >= 16 && stat <= 18 && sumaResta == -1) {
			return 3 * sumaResta;
		} else
			return 0;
	}

	/**
	 * Actualiza el valor de todos los stats. Hace la suma de los stats base y
	 * los puntos extra de la {@link Raza} en aso de haberlos.
	 * 
	 */
	private void actualizaCaracteristicas() {
		this.setConstitucionTotal(this.getConstitucion() + this.getRazaFicha().getConstitucionExtra());
		this.setFuerzaTotal(this.getFuerza() + this.getRazaFicha().getFuerzaExtra());
		this.setDestrezaTotal(this.getDestreza() + this.getRazaFicha().getDestrezaExtra());
		this.setInteligenciaTotal(this.getInteligencia() + this.getRazaFicha().getInteligenciaExtra());
		this.setSabiduriaTotal(this.getSabiduria() + this.getRazaFicha().getSabiduriaExtra());
		this.setCarismaTotal(this.getCarisma() + this.getRazaFicha().getCarismaExtra());
		this.setVida(dadoDeGolpe() + calculaModificador(this.getConstitucionTotal()));
	}

	/**
	 * Cambia la {@link Raza} de este Aventurero por la indicada.
	 * 
	 * @see #setRazaFicha(String)
	 * @param nombreRaza
	 */
	public void cambiaRaza(String nombreRaza) {
		setRazaFicha(nombreRaza);
		actualizaCaracteristicas();
	}

	/**
	 * Cambia la {@link Clase} este Aventurero por la indicada.
	 * 
	 * @see #setClaseFicha(String)
	 * @param nombreClase
	 */
	public void cambiaClase(String nombreClase) {
		setClaseFicha(nombreClase);
		actualizaCaracteristicas();
	}

	/**
	 * Devuelve el dado de golpe de este Aventurero.
	 * 
	 * @return Valor numérico del dado de golpe.
	 */
	private int dadoDeGolpe() {
		return this.getClaseFicha().getDadoDeGolpe();
	}

	/**
	 * Devuelve el modificador de caracteristica que le envies.
	 * 
	 * @param caracteristica
	 *            Puntuacion actual de la estadistica
	 * @return Modificador actual de la estadistica
	 */
	private int calculaModificador(int caracteristica) {
		if (caracteristica == 10)
			return 0;
		caracteristica -= 10;
		if (caracteristica < 0) {
			if (caracteristica % 2 != 0)
				return (caracteristica / 2) - 1;
			else
				return caracteristica / 2;
		}
		return (caracteristica / 2);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Aventurero))
			return false;
		Aventurero aventurero = (Aventurero) o;
		if (this.getNombre().equals(aventurero.getNombre()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.Nombre + ", Clase: " + this.ClaseFicha.toString() + ", Raza: "
				+ this.RazaFicha.toString() + ", Nivel: " + this.Nivel;
	}

	/**
	 * Establece el {@link #Nivel} de este Aventurero.
	 * 
	 * @see #getNivel()
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		Nivel = nivel;
	}

	/**
	 * Establece la {@link #Vida} de este Aventurero.
	 * 
	 * @see #setVida(int)
	 * @param vida
	 */
	public void setVida(int vida) {
		Vida = vida;
	}

	/**
	 * Establece la {@link #Fuerza} de este Aventurero.
	 * 
	 * @see #setFuerza(int)
	 * @param fuerza
	 */
	public void setFuerza(int fuerza) {
		Fuerza = fuerza;
		actualizaCaracteristicas();
	}

	/**
	 * Establece la {@link #Destreza} de este Aventurero.
	 * 
	 * @see #getDestreza()
	 * @param destreza
	 */
	public void setDestreza(int destreza) {
		Destreza = destreza;
		actualizaCaracteristicas();
	}

	/**
	 * Establece la {@link #Constitucion} de este Aventurero.
	 * 
	 * @see #getConstitucion()
	 * @param constitucion
	 */
	public void setConstitucion(int constitucion) {
		Constitucion = constitucion;
		actualizaCaracteristicas();
	}

	/**
	 * Establece la {@link #Inteligencia} de este Aventurero.
	 * 
	 * @see #getInteligencia()
	 * @param inteligencia
	 */
	public void setInteligencia(int inteligencia) {
		Inteligencia = inteligencia;
		actualizaCaracteristicas();
	}

	/**
	 * Establece la {@link #Sabiduria} de este Aventurero.
	 * 
	 * @see #setSabiduria(int)
	 * @param sabiduria
	 */
	public void setSabiduria(int sabiduria) {
		Sabiduria = sabiduria;
		actualizaCaracteristicas();
	}

	/**
	 * Establece el {@link #Carisma} de este Aventurero.
	 * 
	 * @see #setCarisma(int)
	 * @param carisma
	 */
	public void setCarisma(int carisma) {
		Carisma = carisma;
		actualizaCaracteristicas();
	}

	/**
	 * Establece la {@link Raza} de este Aventurero.
	 * 
	 * @see #getRazaFicha()
	 * @param razaFicha
	 */
	public void setRazaFicha(String razaFicha) {
		RazaFicha = new Raza(razaFicha);
	}

	/**
	 * Establece la {@link Clase} de este Aventurero.
	 * 
	 * @see #getClaseFicha()
	 * @param nombre
	 */
	public void setClaseFicha(String nombre) {
		ClaseFicha = new Clase(nombre);
	}

	/**
	 * Establece el {@link #Nombre} de este Aventurero.
	 * 
	 * @see #getNombre()
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * Establece el nombre del {@link #Jugador} de este Aventurero.
	 * 
	 * @see #getJugador()
	 * @param nombre
	 */
	public void setJugador(String jugador) {
		Jugador = jugador;
	}

	/**
	 * Establece el nombre de la {@link #Aventura} de este Aventurero.
	 * 
	 * @see #getAventura()
	 * @param aventura
	 */
	public void setAventura(String aventura) {
		Aventura = aventura;
	}

	/**
	 * Establece el nombre del {@link #Master} de este Aventurero.
	 * 
	 * @see #getMaster()
	 * @param master
	 */
	public void setMaster(String master) {
		Master = master;
	}

	/**
	 * Establece la {@link #Foto} de este Aventurero.
	 * 
	 * @see #getFoto()
	 * @param foto
	 *            La foto en mapa de <code>byte</byte>
	 */
	public void setFoto(byte[] foto) {
		this.Foto = foto;
	}

	/**
	 * Establece los {@link #PuntosTotales} de este Aventurero.
	 * 
	 * @see #getPuntosTotales()
	 * @param puntosTotales
	 */
	public void setPuntosTotales(int puntosTotales) {
		PuntosTotales = puntosTotales;
	}

	/**
	 * Establece la {@link #FuerzaTotal} de este Aventurero.
	 * 
	 * @see #getFuerzaTotal()
	 * @param fuerzaTotal
	 */
	private void setFuerzaTotal(int fuerzaTotal) {
		FuerzaTotal = fuerzaTotal;
	}

	/**
	 * Establece la {@link #DestrezaTotal} de este Aventurero.
	 * 
	 * @see #getDestrezaTotal()
	 * @param destrezaTotal
	 */
	private void setDestrezaTotal(int destrezaTotal) {
		DestrezaTotal = destrezaTotal;
	}

	/**
	 * Establece la {@link #ConstitucionTotal} de este Aventurero.
	 * 
	 * @see #getConstitucionTotal()
	 * @param constitucionTotal
	 */
	private void setConstitucionTotal(int constitucionTotal) {
		ConstitucionTotal = constitucionTotal;
	}

	/**
	 * Establece la {@link #InteligenciaTotal} de este Aventurero.
	 * 
	 * @see #getInteligenciaTotal()
	 * @param inteligenciaTotal
	 */
	private void setInteligenciaTotal(int inteligenciaTotal) {
		InteligenciaTotal = inteligenciaTotal;
	}

	/**
	 * Establece la {@link #SabiduriaTotal} de este Aventurero.
	 * 
	 * @see #getSabiduriaTotal()
	 * @param sabiduriaTotal
	 */
	private void setSabiduriaTotal(int sabiduriaTotal) {
		SabiduriaTotal = sabiduriaTotal;
	}

	/**
	 * Establece el {@link #CarismaTotal} de este Aventurero.
	 * 
	 * @see #getCarismaTotal()
	 * @param carismaTotal
	 */
	private void setCarismaTotal(int carismaTotal) {
		CarismaTotal = carismaTotal;
	}

	/**
	 * Establece la {@link #Informacion} de este Aventurero.
	 * 
	 * @see #getInformacion()
	 * @param informacion
	 */
	public void setInfo(String informacion) {
		this.Informacion = informacion;
	}

	public int getPuntosTotales() {
		return PuntosTotales;
	}

	public int getNivel() {
		return Nivel;
	}

	public int getVida() {
		return Vida;
	}

	public int getFuerza() {
		return Fuerza;
	}

	public int getDestreza() {
		return Destreza;
	}

	public int getConstitucion() {
		return Constitucion;
	}

	public int getInteligencia() {
		return Inteligencia;
	}

	public int getSabiduria() {
		return Sabiduria;
	}

	public int getCarisma() {
		return Carisma;
	}

	public Raza getRazaFicha() {
		return RazaFicha;
	}

	public Clase getClaseFicha() {
		return ClaseFicha;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getJugador() {
		return Jugador;
	}

	public String getAventura() {
		return Aventura;
	}

	public String getMaster() {
		return Master;
	}

	public byte[] getFoto() {
		return Foto;
	}

	public int getFuerzaTotal() {
		return FuerzaTotal;
	}

	public int getDestrezaTotal() {
		return DestrezaTotal;
	}

	public int getConstitucionTotal() {
		return ConstitucionTotal;
	}

	public int getInteligenciaTotal() {
		return InteligenciaTotal;
	}

	public int getSabiduriaTotal() {
		return SabiduriaTotal;
	}

	public int getCarismaTotal() {
		return CarismaTotal;
	}

	public String getInformacion() {
		return Informacion;
	}
}

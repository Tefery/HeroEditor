package aventureroCORE;

/**
 * Contiene la informacion de las Habilidades de un aventurero. En desarrollo y
 * en desuso.
 * 
 * @see Aventurero
 * @see Clase
 * @author Tefery
 * @version 0.0.6
 */
public class Habilidades extends Aventurero {
	private int PuntosTotales;
	private int PuntosRestantes;

	private double AbrirCerraduras = 0;
	private double Arte = 0;
	private double AveriguarIntenciones = 0;
	private double Avistar = 0;
	private double Buscar = 0;
	private double Concentracion = 0;
	private double ConocimientoDeConjuros = 0;
	private double DescifrarEscritura = 0;
	private double Diplomacia = 0;
	private double Disfrazarse = 0;
	private double Enganiar = 0;
	private double Equilibrio = 0;
	private double Escapismo = 0;
	private double Esconderse = 0;
	private double Escuchar = 0;
	private double Falsificar = 0;
	private double Interpretar = 0;
	private double Intimidar = 0;
	private double InutilizarMecanismo = 0;
	private double JuegoDeManos = 0;
	private double Montar = 0;
	private double MoverseSigilosamente = 0;
	private double Nadar = 0;
	private double Oficio = 0;
	private double Piruetas = 0;
	private double ReunirInformacion = 0;
	private double SaberArcano = 0;
	private double SaberArquitectura = 0;
	private double Saberdungeons = 0;
	private double SaberGeografia = 0;
	private double SaberHistoria = 0;
	private double SaberLocal = 0;
	private double SaberLosPlanos = 0;
	private double SaberNaturaleza = 0;
	private double SaberNobleza = 0;
	private double SaberReligion = 0;
	private double Saltar = 0;
	private double Sanar = 0;
	private double Supervivencia = 0;
	private double Tasacion = 0;
	private double TratoConAnimales = 0;
	private double Trepar = 0;
	private double UsarObjetoMagico = 0;
	private double UsoDeCuerdas = 0;

	private boolean CAbrirCerraduras = false;
	private boolean CArte = false;
	private boolean CAvistar = false;
	private boolean CAveriguarIntenciones = false;
	private boolean CBuscar = false;
	private boolean CConcentracion = false;
	private boolean CConocimientoDeConjuros = false;
	private boolean CDescifrarEscritura = false;
	private boolean CDiplomacia = false;
	private boolean CDisfrazarse = false;
	private boolean CEnganiar = false;
	private boolean CEquilibrio = false;
	private boolean CEscapismo = false;
	private boolean CEsconderse = false;
	private boolean CEscuchar = false;
	private boolean CFalsificar = false;
	private boolean CInterpretar = false;
	private boolean CIntimidar = false;
	private boolean CInutilizarMecanismo = false;
	private boolean CJuegoDeManos = false;
	private boolean CMontar = false;
	private boolean CMoverseSigilosamente = false;
	private boolean CNadar = false;
	private boolean COficio = false;
	private boolean CPiruetas = false;
	private boolean CReunirInformacion = false;
	private boolean CSaberArcano = false;
	private boolean CSaberArquitectura = false;
	private boolean CSaberdungeons = false;
	private boolean CSaberGeografia = false;
	private boolean CSaberHistoria = false;
	private boolean CSaberLocal = false;
	private boolean CSaberLosPlanos = false;
	private boolean CSaberNaturaleza = false;
	private boolean CSaberNobleza = false;
	private boolean CSaberReligion = false;
	private boolean CSaltar = false;
	private boolean CSanar = false;
	private boolean CSupervivencia = false;
	private boolean CTasacion = false;
	private boolean CTratoConAnimales = false;
	private boolean CTrepar = false;
	private boolean CUsarObjetoMagico = false;
	private boolean CUsoDeCuerdas = false;

	public Habilidades() {
		inicializa();
	}

	private void inicializa() {
		setPuntosTotales();
		setClaseas();
	}

	// SETTERS
	private void setClaseas() {
		// TODO CONSULTA LAS CLASEAS EN LA BASE DE DATOS

	}

	private void setPuntosTotales() {
		PuntosTotales = (this.getNivel() * (this.getClaseFicha().getHabilidades() + this.getInteligencia()));
		PuntosRestantes = PuntosTotales;
	}

	protected void setPuntosRestantes(int suma) {
		if (suma == 1)
			PuntosRestantes++;
		if (suma == -1)
			PuntosRestantes--;
	}

	private double CambiaRango(double rango, int suma, boolean clasea) {
		if (rango <= 0 && suma == -1) {
			rango = 0;
		} else {
			double MaximosRangos = 4 + (this.getNivel() - 1);
			if (!clasea)
				MaximosRangos = MaximosRangos / 2;
			if (rango < MaximosRangos) {
				if (clasea) {
					if (suma == 1) {
						rango += 1;
					}
					if (suma == -1)
						rango -= 1;
				} else {
					if (suma == 1) {
						rango += 0.5;
					}
					if (suma == -1)
						rango -= -0.5;
				}
				setPuntosRestantes(suma);
			}
		}
		return rango;
	}

	protected void setAbrirCerraduras(int suma) {
		AbrirCerraduras = CambiaRango(AbrirCerraduras, suma, CAbrirCerraduras);
	}

	protected void setArte(int suma) {
		Arte = CambiaRango(Arte, suma, CArte);
	}

	protected void setAvistar(int suma) {
		Avistar = CambiaRango(Avistar, suma, CAvistar);
	}

	protected void setAveriguarIntenciones(int suma) {
		AveriguarIntenciones = CambiaRango(AveriguarIntenciones, suma, CAveriguarIntenciones);
	}

	protected void setBuscar(int suma) {
		Buscar = CambiaRango(Buscar, suma, CBuscar);
	}

	protected void setConcentracion(int suma) {
		Concentracion = CambiaRango(Concentracion, suma, CConcentracion);
	}

	protected void setConocimientoDeConjuros(int suma) {
		ConocimientoDeConjuros = CambiaRango(ConocimientoDeConjuros, suma, CConocimientoDeConjuros);
	}

	protected void setDescifrarEscritura(int suma) {
		DescifrarEscritura = CambiaRango(DescifrarEscritura, suma, CDescifrarEscritura);
	}

	protected void setDiplomacia(int suma) {
		Diplomacia = CambiaRango(Diplomacia, suma, CDiplomacia);
	}

	protected void setDisfrazarse(int suma) {
		Disfrazarse = CambiaRango(Disfrazarse, suma, CDisfrazarse);
	}

	protected void setEnganiar(int suma) {
		Enganiar = CambiaRango(Enganiar, suma, CEnganiar);
	}

	protected void setEquilibrio(int suma) {
		Equilibrio = CambiaRango(Equilibrio, suma, CEquilibrio);
	}

	protected void setEscapismo(int suma) {
		Escapismo = CambiaRango(Escapismo, suma, CEscapismo);
	}

	protected void setEsconderse(int suma) {
		Esconderse = CambiaRango(Esconderse, suma, CEsconderse);
	}

	protected void setEscuchar(int suma) {
		Escuchar = CambiaRango(Escuchar, suma, CEscuchar);
	}

	protected void setFalsificar(int suma) {
		Falsificar = CambiaRango(Falsificar, suma, CFalsificar);
	}

	protected void setInterpretar(int suma) {
		Interpretar = CambiaRango(Interpretar, suma, CInterpretar);
	}

	protected void setIntimidar(int suma) {
		Intimidar = CambiaRango(Intimidar, suma, CIntimidar);
	}

	protected void setInutilizarMecanismo(int suma) {
		InutilizarMecanismo = CambiaRango(InutilizarMecanismo, suma, CInutilizarMecanismo);
	}

	protected void setJuegoDeManos(int suma) {
		JuegoDeManos = CambiaRango(JuegoDeManos, suma, CJuegoDeManos);
	}

	protected void setMontar(int suma) {
		Montar = CambiaRango(Montar, suma, CMontar);
	}

	protected void setMoverseSigilosamente(int suma) {
		MoverseSigilosamente = CambiaRango(MoverseSigilosamente, suma, CMoverseSigilosamente);
	}

	protected void setNadar(int suma) {
		Nadar = CambiaRango(Nadar, suma, CNadar);
	}

	protected void setOficio(int suma) {
		Oficio = CambiaRango(Oficio, suma, COficio);
	}

	protected void setPiruetas(int suma) {
		Piruetas = CambiaRango(Piruetas, suma, CPiruetas);
	}

	protected void setReunirInformacion(int suma) {
		ReunirInformacion = CambiaRango(ReunirInformacion, suma, CReunirInformacion);
	}

	protected void setSaberArcano(int suma) {
		SaberArcano = CambiaRango(SaberArcano, suma, CSaberArcano);
	}

	protected void setSaberArquitectura(int suma) {
		SaberArquitectura = CambiaRango(SaberArquitectura, suma, CSaberArquitectura);
	}

	protected void setSaberdungeons(int suma) {
		Saberdungeons = CambiaRango(Saberdungeons, suma, CSaberdungeons);
	}

	protected void setSaberGeografia(int suma) {
		SaberGeografia = CambiaRango(SaberGeografia, suma, CSaberGeografia);
	}

	protected void setSaberHistoria(int suma) {
		SaberHistoria = CambiaRango(SaberHistoria, suma, CSaberHistoria);
	}

	protected void setSaberLocal(int suma) {
		SaberLocal = CambiaRango(SaberLocal, suma, CSaberLocal);
	}

	protected void setSaberLosPlanos(int suma) {
		SaberLosPlanos = CambiaRango(SaberLosPlanos, suma, CSaberLosPlanos);
	}

	protected void setSaberNaturaleza(int suma) {
		SaberNaturaleza = CambiaRango(SaberNaturaleza, suma, CSaberNaturaleza);
	}

	protected void setSaberNobleza(int suma) {
		SaberNobleza = CambiaRango(SaberNobleza, suma, CSaberNobleza);
	}

	protected void setSaberReligion(int suma) {
		SaberReligion = CambiaRango(SaberReligion, suma, CSaberReligion);
	}

	protected void setSaltar(int suma) {
		Saltar = CambiaRango(Saltar, suma, CSaltar);
	}

	protected void setSanar(int suma) {
		Sanar = CambiaRango(Sanar, suma, CSanar);
	}

	protected void setSupervivencia(int suma) {
		Supervivencia = CambiaRango(Supervivencia, suma, CSupervivencia);
	}

	protected void setTasacion(int suma) {
		Tasacion = CambiaRango(Tasacion, suma, CTasacion);
	}

	protected void setTratoConAnimales(int suma) {
		TratoConAnimales = CambiaRango(TratoConAnimales, suma, CTratoConAnimales);
	}

	protected void setTrepar(int suma) {
		Trepar = CambiaRango(Trepar, suma, CTrepar);
	}

	protected void setUsarObjetoMagico(int suma) {
		UsarObjetoMagico = CambiaRango(UsarObjetoMagico, suma, CUsarObjetoMagico);
	}

	protected void setUsoDeCuerdas(int suma) {
		UsoDeCuerdas = CambiaRango(UsoDeCuerdas, suma, CUsoDeCuerdas);
	}

	protected void setCAbrirCerraduras(boolean cAbrirCerraduras) {
		CAbrirCerraduras = cAbrirCerraduras;
	}

	protected void setCArte(boolean cArte) {
		CArte = cArte;
	}

	protected void setCAveriguarIntenciones(boolean cAveriguarIntenciones) {
		CAveriguarIntenciones = cAveriguarIntenciones;
	}

	protected void setCAvistar(boolean cAvistar) {
		CAvistar = cAvistar;
	}

	protected void setCBuscar(boolean cBuscar) {
		CBuscar = cBuscar;
	}

	protected void setCConcentracion(boolean cConcentracion) {
		CConcentracion = cConcentracion;
	}

	protected void setCConocimientoDeConjuros(boolean cConocimientoDeConjuros) {
		CConocimientoDeConjuros = cConocimientoDeConjuros;
	}

	protected void setCDescifrarEscritura(boolean cDescifrarEscritura) {
		CDescifrarEscritura = cDescifrarEscritura;
	}

	protected void setCDiplomacia(boolean cDiplomacia) {
		CDiplomacia = cDiplomacia;
	}

	protected void setCDisfrazarse(boolean cDisfrazarse) {
		CDisfrazarse = cDisfrazarse;
	}

	protected void setCEnganiar(boolean cengañar) {
		CEnganiar = cengañar;
	}

	protected void setCEquilibrio(boolean cEquilibrio) {
		CEquilibrio = cEquilibrio;
	}

	protected void setCEscapismo(boolean cEscapismo) {
		CEscapismo = cEscapismo;
	}

	protected void setCEsconderse(boolean cEsconderse) {
		CEsconderse = cEsconderse;
	}

	protected void setCEscuchar(boolean cEscuchar) {
		CEscuchar = cEscuchar;
	}

	protected void setCFalsificar(boolean cFalsificar) {
		CFalsificar = cFalsificar;
	}

	protected void setCInterpretar(boolean cInterpretar) {
		CInterpretar = cInterpretar;
	}

	protected void setCIntimidar(boolean cIntimidar) {
		CIntimidar = cIntimidar;
	}

	protected void setCInutilizarMecanismo(boolean cInutilizarMecanismo) {
		CInutilizarMecanismo = cInutilizarMecanismo;
	}

	protected void setCJuegoDeManos(boolean cJuegoDeManos) {
		CJuegoDeManos = cJuegoDeManos;
	}

	protected void setCMontar(boolean cMontar) {
		CMontar = cMontar;
	}

	protected void setCMoverseSigilosamente(boolean cMoverseSigilosamente) {
		CMoverseSigilosamente = cMoverseSigilosamente;
	}

	protected void setCNadar(boolean cNadar) {
		CNadar = cNadar;
	}

	protected void setCOficio(boolean cOficio) {
		COficio = cOficio;
	}

	protected void setCPiruetas(boolean cPiruetas) {
		CPiruetas = cPiruetas;
	}

	protected void setCReunirInformacion(boolean cReunirInformacion) {
		CReunirInformacion = cReunirInformacion;
	}

	protected void setCSaberArcano(boolean cSaberArcano) {
		CSaberArcano = cSaberArcano;
	}

	protected void setCSaberArquitectura(boolean cSaberArquitectura) {
		CSaberArquitectura = cSaberArquitectura;
	}

	protected void setCSaberdungeons(boolean cSaberdungeons) {
		CSaberdungeons = cSaberdungeons;
	}

	protected void setCSaberGeografia(boolean cSaberGeografia) {
		CSaberGeografia = cSaberGeografia;
	}

	protected void setCSaberHistoria(boolean cSaberHistoria) {
		CSaberHistoria = cSaberHistoria;
	}

	protected void setCSaberLocal(boolean cSaberLocal) {
		CSaberLocal = cSaberLocal;
	}

	protected void setCSaberLosPlanos(boolean cSaberLosPlanos) {
		CSaberLosPlanos = cSaberLosPlanos;
	}

	protected void setCSaberNaturaleza(boolean cSaberNaturaleza) {
		CSaberNaturaleza = cSaberNaturaleza;
	}

	protected void setCSaberNobleza(boolean cSaberNobleza) {
		CSaberNobleza = cSaberNobleza;
	}

	protected void setCSaberReligion(boolean cSaberReligion) {
		CSaberReligion = cSaberReligion;
	}

	protected void setCSaltar(boolean cSaltar) {
		CSaltar = cSaltar;
	}

	protected void setCSanar(boolean cSanar) {
		CSanar = cSanar;
	}

	protected void setCSupervivencia(boolean cSupervivencia) {
		CSupervivencia = cSupervivencia;
	}

	protected void setCTasacion(boolean cTasacion) {
		CTasacion = cTasacion;
	}

	protected void setCTratoConAnimales(boolean cTratoConAnimales) {
		CTratoConAnimales = cTratoConAnimales;
	}

	protected void setCTrepar(boolean cTrepar) {
		CTrepar = cTrepar;
	}

	protected void setCUsarObjetoMagico(boolean cUsarObjetoMagico) {
		CUsarObjetoMagico = cUsarObjetoMagico;
	}

	protected void setCUsoDeCuerdas(boolean cUsoDeCuerdas) {
		CUsoDeCuerdas = cUsoDeCuerdas;
	}

	// SETTERS

	public int getPuntosRestantes() {
		return PuntosRestantes;
	}

	public double getAbrirCerraduras() {
		return AbrirCerraduras;
	}

	public double getArte() {
		return Arte;
	}

	public double getAveriguarIntenciones() {
		return AveriguarIntenciones;
	}

	public double getAvistar() {
		return Avistar;
	}

	public double getBuscar() {
		return Buscar;
	}

	public double getConcentracion() {
		return Concentracion;
	}

	public double getConocimientoDeConjuros() {
		return ConocimientoDeConjuros;
	}

	public double getDescifrarEscritura() {
		return DescifrarEscritura;
	}

	public double getDiplomacia() {
		return Diplomacia;
	}

	public double getDisfrazarse() {
		return Disfrazarse;
	}

	public double getEnganiar() {
		return Enganiar;
	}

	public double getEquilibrio() {
		return Equilibrio;
	}

	public double getEscapismo() {
		return Escapismo;
	}

	public double getEsconderse() {
		return Esconderse;
	}

	public double getEscuchar() {
		return Escuchar;
	}

	public double getFalsificar() {
		return Falsificar;
	}

	public double getInterpretar() {
		return Interpretar;
	}

	public double getIntimidar() {
		return Intimidar;
	}

	public double getInutilizarMecanismo() {
		return InutilizarMecanismo;
	}

	public double getJuegoDeManos() {
		return JuegoDeManos;
	}

	public double getMontar() {
		return Montar;
	}

	public double getMoverseSigilosamente() {
		return MoverseSigilosamente;
	}

	public double getNadar() {
		return Nadar;
	}

	public double getOficio() {
		return Oficio;
	}

	public double getPiruetas() {
		return Piruetas;
	}

	public double getReunirInformacion() {
		return ReunirInformacion;
	}

	public double getSaberArcano() {
		return SaberArcano;
	}

	public double getSaberArquitectura() {
		return SaberArquitectura;
	}

	public double getSaberdungeons() {
		return Saberdungeons;
	}

	public double getSaberGeografia() {
		return SaberGeografia;
	}

	public double getSaberHistoria() {
		return SaberHistoria;
	}

	public double getSaberLocal() {
		return SaberLocal;
	}

	public double getSaberLosPlanos() {
		return SaberLosPlanos;
	}

	public double getSaberNaturaleza() {
		return SaberNaturaleza;
	}

	public double getSaberNobleza() {
		return SaberNobleza;
	}

	public double getSaberReligion() {
		return SaberReligion;
	}

	public double getSaltar() {
		return Saltar;
	}

	public double getSanar() {
		return Sanar;
	}

	public double getSupervivencia() {
		return Supervivencia;
	}

	public double getTasacion() {
		return Tasacion;
	}

	public double getTratoConAnimales() {
		return TratoConAnimales;
	}

	public double getTrepar() {
		return Trepar;
	}

	public double getUsarObjetoMagico() {
		return UsarObjetoMagico;
	}

	public double getUsoDeCuerdas() {
		return UsoDeCuerdas;
	}

	// BOOLEANOS

	public boolean isCAbrirCerraduras() {
		return CAbrirCerraduras;
	}

	public boolean isCArte() {
		return CArte;
	}

	public boolean isCAvistar() {
		return CAvistar;
	}

	public boolean isCAveriguarIntenciones() {
		return CAveriguarIntenciones;
	}

	public boolean isCBuscar() {
		return CBuscar;
	}

	public boolean isCConcentracion() {
		return CConcentracion;
	}

	public boolean isCConocimientoDeConjuros() {
		return CConocimientoDeConjuros;
	}

	public boolean isCDescifrarEscritura() {
		return CDescifrarEscritura;
	}

	public boolean isCDiplomacia() {
		return CDiplomacia;
	}

	public boolean isCDisfrazarse() {
		return CDisfrazarse;
	}

	public boolean isCEnganiar() {
		return CEnganiar;
	}

	public boolean isCEquilibrio() {
		return CEquilibrio;
	}

	public boolean isCEscapismo() {
		return CEscapismo;
	}

	public boolean isCEsconderse() {
		return CEsconderse;
	}

	public boolean isCEscuchar() {
		return CEscuchar;
	}

	public boolean isCFalsificar() {
		return CFalsificar;
	}

	public boolean isCInterpretar() {
		return CInterpretar;
	}

	public boolean isCIntimidar() {
		return CIntimidar;
	}

	public boolean isCInutilizarMecanismo() {
		return CInutilizarMecanismo;
	}

	public boolean isCJuegoDeManos() {
		return CJuegoDeManos;
	}

	public boolean isCMontar() {
		return CMontar;
	}

	public boolean isCMoverseSigilosamente() {
		return CMoverseSigilosamente;
	}

	public boolean isCNadar() {
		return CNadar;
	}

	public boolean isCOficio() {
		return COficio;
	}

	public boolean isCPiruetas() {
		return CPiruetas;
	}

	public boolean isCReunirInformacion() {
		return CReunirInformacion;
	}

	public boolean isCSaberArcano() {
		return CSaberArcano;
	}

	public boolean isCSaberArquitectura() {
		return CSaberArquitectura;
	}

	public boolean isCSaberdungeons() {
		return CSaberdungeons;
	}

	public boolean isCSaberGeografia() {
		return CSaberGeografia;
	}

	public boolean isCSaberHistoria() {
		return CSaberHistoria;
	}

	public boolean isCSaberLocal() {
		return CSaberLocal;
	}

	public boolean isCSaberLosPlanos() {
		return CSaberLosPlanos;
	}

	public boolean isCSaberNaturaleza() {
		return CSaberNaturaleza;
	}

	public boolean isCSaberNobleza() {
		return CSaberNobleza;
	}

	public boolean isCSaberReligion() {
		return CSaberReligion;
	}

	public boolean isCSaltar() {
		return CSaltar;
	}

	public boolean isCSanar() {
		return CSanar;
	}

	public boolean isCSupervivencia() {
		return CSupervivencia;
	}

	public boolean isCTasacion() {
		return CTasacion;
	}

	public boolean isCTratoConAnimales() {
		return CTratoConAnimales;
	}

	public boolean isCTrepar() {
		return CTrepar;
	}

	public boolean isCUsarObjetoMagico() {
		return CUsarObjetoMagico;
	}

	public boolean isCUsoDeCuerdas() {
		return CUsoDeCuerdas;
	}
}
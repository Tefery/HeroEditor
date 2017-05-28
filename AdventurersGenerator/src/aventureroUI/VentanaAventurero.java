package aventureroUI;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import aventureroCORE.Aventurero;
import aventureroCORE.CargaPTerra;
import aventureroDAO.AlteraDatosBD;
import aventureroDAO.ConexionBD;

/**
 * Entorno Visual para la clase Aventurero.
 * 
 * @see aventureroCORE.Aventurero
 * @see aventureroCORE.Clase
 * @see aventureroCORE.Raza
 * @see AlteraDatosBD
 * @author Tefery
 * @version 0.7.5
 * @since 0.3.8
 */
@SuppressWarnings("serial")
public class VentanaAventurero extends JFrame {

	/**
	 * Las resoluciones para la ventana
	 * 
	 * @see #_Alto
	 * @see #_AltoY
	 * @see #_AnchoX
	 */
	private static final int _Ancho = 1010;
	private static final int _Alto = 550;
	private static final int _AltoY = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (_Alto / 2);
	private static final int _AnchoX = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (_Ancho / 2);

	@SuppressWarnings("unused")
	private static ServerSocket SERVER_SOCKET;
	private int Pie = 0;
	private ArrayList<String> Filtro = null;
	private boolean isPie = true;
	private int PuntTL;
	private CargaPTerra PTerra;
	private boolean Conectado;
	private AlteraDatosBD Consul;
	private Aventurero Aventurero;
	private JPopupMenu Reset;
	private JButton BotonCambiaFoto;
	private JLabel FotoHeroe;
	private JPanel PanelHeroe;
	private JPanel PanelStats;
	private JPanel PanelFoto;
	private JComboBox<String> Clases;
	private JButton BtnGuardasCambios;
	private JSpinner SpinFue;
	private JSpinner SpinDes;
	private JSpinner SpinCon;
	private JSpinner SpinInt;
	private JSpinner SpinSab;
	private JSpinner SpinCar;
	private JComboBox<String> Razas;
	private JPanel PanelArbol;
	private JTree Arbol;
	private JPanel PanelAbrirBorrar;
	private JButton AbrirSeleccionArbol;
	private JButton BorraHeroe;
	private JPanel Panelbotonesfoto;
	private JPanel PanelCaracteristicas;
	private JPanel PanelDatos;
	private JPanel PanelRazaClase;
	private JLabel LabelFue;
	private JLabel LabelDes;
	private JLabel LabelCon;
	private JLabel LabelInt;
	private JLabel LabelSab;
	private JLabel LabelCar;
	private JPanel PanelNombreFich;
	private JPanel PanelTextArea;
	private JTextArea Informacion;
	private JPanel PanelConDeTodo;
	private JPanel TreePanel;
	private JLabel LabelNombre;
	private JLabel LabelClase;
	private JLabel LabelRaza;
	private JLabel LabelPuntosTotales;
	private JLabel LabelJugador;
	private JTextField TextJugador;
	private JTextField TextNombre;
	private JPanel Panel_1;
	private JLabel LabelVida;
	private JLabel LabelInforma;
	private JSpinner SpinnerNivel;
	private JLabel LabelNivel;
	private JLabel LabelOpcionFoto;
	private JComboBox<String> OpcionFoto;
	private JPanel PanelHuecoBlanco;
	private JPanel Panel_3;
	private JButton BotonRestaurar;
	private VentanaDescripcion VentDesClase;
	private VentanaDescripcion VentDesRaza;
	private JScrollPane ScrollArbol;
	private JLabel FotoArcoiris;
	private JScrollPane ScrollInformacion;
	private JPanel PanelBotonesArbol;
	private JPanel PanelBotonFiltra;
	private JButton BotonFiltra;

	public static void main(String[] args) {
		estoyAbierto();
		cambiarAspectoAlDelSistmeOperativo();
		ConexionBD.cargaElDriver();
		VentanaAventurero m = new VentanaAventurero();
		m.setVisible(true);
	}

	/**
	 * Crea una nueva VentanaAventurero. Inicializa el aventurero y verifica si
	 * tiene acceso a la base de datos.
	 * 
	 */
	public VentanaAventurero() {
		try {
			Consul = new AlteraDatosBD();
			Conectado = true;
		} catch (Exception e) {
			Conectado = false;
			JOptionPane.showMessageDialog(null, "No hay acceso a la base de datos, no podrás guardar ni cargar",
					"Warning", 2);
		}
		PTerra = new CargaPTerra();
		Aventurero = new Aventurero();
		PuntTL = Aventurero.getPuntosTotales();
		initComponents();
		actualizaTodo();
	}

	/**
	 * Comprueba qué se ha seleccionado. Diferencia si lo que buscas es el
	 * {@link aventureroCORE.Aventurero}, la {@link aventureroCORE.Clase}, o la
	 * {@link aventureroCORE.Raza}.
	 * <p>
	 * Si lo seleccionado es un {@link aventureroCORE.Aventurero}, lo carga en
	 * esta UI. Si lo seleccionado es una {@link aventureroCORE.Clase} o una
	 * {@link aventureroCORE.Raza}, lo carga en una nueva ventana.
	 * </p>
	 * 
	 * @see VentanaDescripcion
	 * @param nombre
	 *            Nombre del <code>String</string> seleccionado.
	 */
	private void queHaSeleccionado(String nombre) {
		if (Consul.aventureroExist(nombre)) {
			cambiaAventurero(nombre);
		} else if (AlteraDatosBD.claseExist(nombre)) {
			VentDesClase = new VentanaDescripcion(Consul.getClase(nombre.toUpperCase()), PTerra);
			VentDesClase.setVisible(true);
		} else if (AlteraDatosBD.razaExist(nombre)) {
			VentDesRaza = new VentanaDescripcion(Consul.getRaza(nombre.toUpperCase()), PTerra);
			VentDesRaza.setVisible(true);
		}
		construyeArbol();
	}

	/**
	 * Comprueba el hueov de pascua. Si se cumple la regla de escribir seguido
	 * "piepiepie" (tartatartatarta), se activa el huevo de pascua ¡HAY TARTA!.
	 * 
	 * @see #hayTarta()
	 * @param event
	 *            El keyevent capturado.
	 */
	private void tartas(KeyEvent event) {
		switch (Pie) {
		case 0:
		case 3:
		case 6:
			if (event.getKeyChar() == 'p' || event.getKeyChar() == 'P')
				Pie++;
			else
				Pie = 0;
			break;
		case 1:
		case 4:
		case 7:
			if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I')
				Pie++;
			else
				Pie = 0;
			break;
		case 2:
		case 5:
			if (event.getKeyChar() == 'e' || event.getKeyChar() == 'E')
				Pie++;
			else
				Pie = 0;
			break;
		case 8:
			Pie = 0;
			if (JOptionPane.showConfirmDialog(null, "¡¡¡¡¿¿¿QUIERES TARTA???!!!!", "TARTAAAAAAAAAAAAAAA", 0, 3) == 0) {
				isPie = false;
				try {
					hayTarta();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException
						| InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "PUES TE QUEDAS SIN TARTA", "=(", 0);
				isPie = false;
			}
			break;
		default:
			Pie = 0;
			break;
		}
	}

	/**
	 * Inicializa el huevo de pascua. Cambia el colos de todos los
	 * <code>JPanel</code> a rosa y carga al aventurero oculto Pinkie Pie.
	 * 
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @throws InterruptedException
	 * @see #tartas(KeyEvent)
	 * @see #todoRosaAmarillo()
	 */
	private void hayTarta()
			throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		if (Conectado) {
			Aventurero = new Aventurero();
			todoRosaAmarillo();
			setExtendedState(MAXIMIZED_BOTH);
			PanelArbol.removeAll();
			ImageIcon m = new ImageIcon(AlteraDatosBD.claseRazaToBytes("rainbow.gif"));
			PanelArbol.add(new JLabel(new ImageIcon(m.getImage().getScaledInstance(166,
					(int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) * 0.7), Image.SCALE_DEFAULT))));
			cambiaAventurero("PINKIE PIE");
			CambiaMensajeInforma(
					"<color=\"purple\">PIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE</color=\"purple\">",
					false);
			AudioInputStream audioStream = AudioSystem
					.getAudioInputStream(VentanaAventurero.class.getResource("/aventureroCORE/haytarta.wav"));
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			JOptionPane.showMessageDialog(null, "EN MODO DESCONECTADO NO HAY TARTA", "=(", 0);
		}
	}

	/**
	 * Cambia el color de todos los elementos. Los JPane* a rosa y los JText* a
	 * amarillo.
	 * 
	 */
	private void todoRosaAmarillo() {
		Color rosa = new Color(255, 99, 209);
		Panel_1.setBackground(rosa);
		Panel_3.setBackground(rosa);
		PanelArbol.setBackground(rosa);
		Panelbotonesfoto.setBackground(rosa);
		PanelCaracteristicas.setBackground(rosa);
		PanelConDeTodo.setBackground(rosa);
		PanelDatos.setBackground(rosa);
		PanelFoto.setBackground(rosa);
		PanelHeroe.setBackground(rosa);
		PanelHuecoBlanco.setBackground(rosa);
		PanelNombreFich.setBackground(rosa);
		PanelRazaClase.setBackground(rosa);
		PanelStats.setBackground(rosa);
		PanelTextArea.setBackground(rosa);
		Informacion.setBackground(Color.YELLOW);
		TextNombre.setBackground(Color.YELLOW);
		TextJugador.setBackground(Color.YELLOW);
		TextNombre.setForeground(Color.MAGENTA);
		TextJugador.setForeground(Color.MAGENTA);
		Informacion.setForeground(Color.MAGENTA);
	}

	/**
	 * Sustituye el {@link aventureroCORE.Aventurero}. Despues vuelve a pintar
	 * todo el Entorno Visual.
	 * 
	 * @param nombre
	 *            Nombre del {@link aventureroCORE.Aventurero} a buscar
	 */
	private void cambiaAventurero(String nombre) {
		Aventurero = Consul.getAventurero(nombre);
		Clases.setSelectedItem(Aventurero.getClaseFicha().getNombre());
		Razas.setSelectedItem(Aventurero.getRazaFicha().getNombre());
		actualizaStats();
		FotoHeroe.setIcon(new ImageIcon(Aventurero.getFoto()));
		TextNombre.setText(Aventurero.getNombre());
		TextJugador.setText(Aventurero.getJugador());
		Informacion.setText(Aventurero.getInformacion());
		OpcionFoto.setSelectedIndex(2);
		CambiaMensajeInforma("Cargado " + Aventurero.getNombre(), false);
		Informacion.select(0, 0);
		sucio(false);
	}

	/**
	 * Mensaje verificador de si quiere borrar tods los aventureros.
	 * Reconstrulle toda la base de datos.
	 * 
	 * @see aventureroDAO.AlteraDatosBD#resetDatabase()
	 */
	private void borraTodo() {
		if (JOptionPane.showConfirmDialog(null, "¿Esta seguro? Perderá todos sus heroes", "Atención", 0, 3) == 0) {
			Consul.resetDatabase();
			Filtro = null;
			construyeArbol();
		}
	}

	/**
	 * Cambia el texto del label informativo
	 * 
	 * @param mensaje
	 *            Texto a mostrar
	 * @param esUnError
	 *            <code>true</code> si es un error, <code>false</code> si es
	 *            informativo.
	 * @beaninfo preferred: false
	 */
	protected void CambiaMensajeInforma(String mensaje, boolean esUnError) {
		if (mensaje.length() > 44)
			mensaje = mensaje.substring(0, 44) + "...";
		if (esUnError)
			LabelInforma.setText("<html><font color=\"red\"><b>" + mensaje + "</b></font></html>");
		else
			LabelInforma.setText("<html><font color=\"#A26E3C\"><b>" + mensaje + "</b></font></html>");
	}

	/**
	 * Registra este {@link aventureroCORE.Aventurero}. En la base de datos.
	 * <p>
	 * Si el aventurero tiene un nombre de 2 o mas caracteres, lo guarda. Si el
	 * aventurero ya existe, lo sobreescribe.
	 * </p>
	 * 
	 */
	private void guardarAventurero() {
		Aventurero.setNombre(TextNombre.getText().trim());
		String nombre = TextNombre.getText().trim();
		if (nombre == null || nombre.length() < 2) {
			JOptionPane.showMessageDialog(null, "Todos los aventureros merecen un nombre");
			CambiaMensajeInforma("Pon un nombre decente", true);
			BtnGuardasCambios.setEnabled(false);
		} else {
			if (!AlteraDatosBD.esLeyenda(nombre)) {
				if (Consul.aventureroExist(nombre))
					try {
						Consul.delAventurero(nombre);
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "FALLO CRITICO EN LA BASE DE DATOS", "Warning", 0);
						return;
					}
				if (OpcionFoto.getSelectedIndex() != 2) {
					if (OpcionFoto.getSelectedIndex() == 0)
						Aventurero.setFoto(AlteraDatosBD.claseRazaToBytes(Clases.getSelectedItem().toString()));
					if (OpcionFoto.getSelectedIndex() == 1)
						Aventurero.setFoto(AlteraDatosBD.claseRazaToBytes(Razas.getSelectedItem().toString()));
				}
				Aventurero.setInfo(Informacion.getText().trim());
				try {
					Consul.addAventurero(Aventurero);
					Filtro = null;
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "FALLO CRITICO EN LA BASE DE DATOS", "Warning", 0);
					return;
				}
				BtnGuardasCambios.setEnabled(false);
				CambiaMensajeInforma(nombre + " guardado", false);
				construyeArbol();
				pintar();
			} else {
				BtnGuardasCambios.setEnabled(false);
				JOptionPane.showMessageDialog(null, "LAS LEYENDAS NO PUEDEN SOBREESCRIBIRSE", "Warning", 2);
				CambiaMensajeInforma("Cambia el nombre", true);
			}
		}
	}

	/**
	 * Instancia el arbol principal. Añade todos los objetos y los visualiza.
	 * 
	 */

	protected void construyeArbol() {
		if (Filtro == null && Conectado) {
			Filtro = Consul.getListaHeroes();
		}

		boolean[] expandido = new boolean[4];
		if (Arbol != null) {
			for (int i = 0; i < 4; i++)
				expandido[i] = Arbol.isExpanded(i);
		} else {
			expandido[0] = true;
			expandido[1] = true;
		}
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode();
		DefaultMutableTreeNode her = new DefaultMutableTreeNode("Heroes");
		DefaultMutableTreeNode cla = new DefaultMutableTreeNode("Clases");
		DefaultMutableTreeNode raz = new DefaultMutableTreeNode("Razas");
		if (Conectado) {
			DefaultMutableTreeNode ley = new DefaultMutableTreeNode("Leyendas");
			her.add(ley);
			for (String s : Filtro) {
				if (!s.equals("PINKIE PIE")) {
					DefaultMutableTreeNode asd = new DefaultMutableTreeNode(false);
					asd.setUserObject(s);
					if (AlteraDatosBD.esLeyenda(s))
						ley.add(asd);
					else
						her.add(asd);
				}
			}
			for (String s : Consul.getListaClases()) {
				DefaultMutableTreeNode asd = new DefaultMutableTreeNode(false);
				asd.setUserObject(s.substring(0, 1) + s.substring(1).toLowerCase());
				cla.add(asd);
			}
			for (String s : Consul.getListaRazas()) {
				DefaultMutableTreeNode asd = new DefaultMutableTreeNode(false);
				asd.setUserObject(s.substring(0, 1) + s.substring(1).toLowerCase());
				raz.add(asd);
			}
		}
		raiz.add(her);
		raiz.add(cla);
		raiz.add(raz);
		DefaultTreeModel asd = new DefaultTreeModel(raiz);
		Arbol.setModel(asd);
		expandido[1] = true;
		for (int i = 0; i < 4; i++) {
			if (expandido[i])
				Arbol.expandPath(Arbol.getPathForRow(i));
		}
	}

	/**
	 * Cambia la imagen a mostrar en la interfaz.
	 * 
	 * @see #escalaFoto(ImageIcon)
	 * @param opcion
	 *            La opcion seleccionada en el JCombobox.
	 */
	private void opciFoto(String opcion) {
		switch (opcion) {
		case "CLASE":
			FotoHeroe.setIcon(escalaFoto(new ImageIcon(Aventurero.getClaseFicha().getFoto())));
			break;
		case "RAZA":
			FotoHeroe.setIcon(escalaFoto(new ImageIcon(Aventurero.getRazaFicha().getFoto())));
			break;
		case "PERSONALIZADA":
			FotoHeroe.setIcon(escalaFoto(new ImageIcon(Aventurero.getFoto())));
			break;
		default:
			break;
		}
	}

	/**
	 * Cambia una caracteristica. Con un String indicando que caracteristica
	 * quieres cambiar, extraer la puntuacion de su respectivo JSpinner y
	 * calculando el cambio.
	 * <p>
	 * Las posibilidades son "Fue", "Des", "Con", "Int", "Sab" y "Char".
	 * </p>
	 * 
	 * @param statACambiar
	 *            Nombre de la caracteristica; Las posibilidades son "Fue",
	 *            "Des", "Con", "Int", "Sab" y "Char".
	 */
	private void cambiaStat(String statACambiar) {
		PuntTL = Aventurero.getPuntosTotales();
		switch (statACambiar) {
		case "Fue":
			Aventurero.setFuerza(Aventurero.cambiaStat(
					((int) SpinFue.getValue() - Aventurero.getRazaFicha().getFuerzaExtra()) - Aventurero.getFuerza(),
					Aventurero.getFuerza()));
			SpinFue.setToolTipText(actualizaSpinnerTooltip(Aventurero.getFuerza()));
			SpinFue.setValue(Aventurero.getFuerzaTotal());
			actualizaLabels();
			break;
		case "Des":
			Aventurero.setDestreza(
					Aventurero.cambiaStat(((int) SpinDes.getValue() - Aventurero.getRazaFicha().getDestrezaExtra())
							- Aventurero.getDestreza(), Aventurero.getDestreza()));
			SpinDes.setToolTipText(actualizaSpinnerTooltip(Aventurero.getDestreza()));
			SpinDes.setValue(Aventurero.getDestrezaTotal());
			actualizaLabels();
			break;
		case "Con":
			Aventurero.setConstitucion(
					Aventurero.cambiaStat(((int) SpinCon.getValue() - Aventurero.getRazaFicha().getConstitucionExtra())
							- Aventurero.getConstitucion(), Aventurero.getConstitucion()));
			SpinCon.setToolTipText(actualizaSpinnerTooltip(Aventurero.getConstitucion()));
			SpinCon.setValue(Aventurero.getConstitucionTotal());
			actualizaLabels();
			break;
		case "Int":
			Aventurero.setInteligencia(
					Aventurero.cambiaStat(((int) SpinInt.getValue() - Aventurero.getRazaFicha().getInteligenciaExtra())
							- Aventurero.getInteligencia(), Aventurero.getInteligencia()));
			SpinInt.setToolTipText(actualizaSpinnerTooltip(Aventurero.getInteligencia()));
			SpinInt.setValue(Aventurero.getInteligenciaTotal());
			actualizaLabels();
			break;
		case "Sab":
			Aventurero.setSabiduria(
					Aventurero.cambiaStat(((int) SpinSab.getValue() - Aventurero.getRazaFicha().getSabiduriaExtra())
							- Aventurero.getSabiduria(), Aventurero.getSabiduria()));
			SpinSab.setToolTipText(actualizaSpinnerTooltip(Aventurero.getSabiduria()));
			SpinSab.setValue(Aventurero.getSabiduriaTotal());
			actualizaLabels();
			break;
		case "Car":
			Aventurero.setCarisma(Aventurero.cambiaStat(
					((int) SpinCar.getValue() - Aventurero.getRazaFicha().getCarismaExtra()) - Aventurero.getCarisma(),
					Aventurero.getCarisma()));
			SpinCar.setToolTipText(actualizaSpinnerTooltip(Aventurero.getCarisma()));
			SpinCar.setValue(Aventurero.getCarismaTotal());
			actualizaLabels();
			break;
		default:
			break;
		}
	}

	/**
	 * Actualiza los datos de todos los labels.
	 * 
	 * @see #mensajePuntos()
	 */
	private void actualizaLabels() {
		mensajePuntos();
		LabelPuntosTotales.setText("<html>Puntos Totales: <b><u><font color=\"green\">" + Aventurero.getPuntosTotales()
				+ "</font></u></b></html>");
		LabelVida.setText(
				"<html>Puntos de vida: <b><u><font color=\"green\">" + Aventurero.getVida() + "</font></u></b></html>");
	}

	/**
	 * Genera el mensaje de los puntos. Al cambiar una estadistica, cambia el
	 * texto del label informativo mostrando los puntos ganastados o
	 * recuperados.
	 * 
	 * @see #CambiaMensajeInforma(String, boolean)
	 */
	private void mensajePuntos() {
		if (PuntTL - Aventurero.getPuntosTotales() == 1 || PuntTL - Aventurero.getPuntosTotales() == -1) {
			if (PuntTL < Aventurero.getPuntosTotales())
				CambiaMensajeInforma("Has recuperado " + (-1 * (PuntTL - Aventurero.getPuntosTotales()) + " punto"),
						false);
			else if (PuntTL > Aventurero.getPuntosTotales())
				CambiaMensajeInforma("Has gastado " + (PuntTL - Aventurero.getPuntosTotales() + " punto"), false);
		} else {
			if (PuntTL < Aventurero.getPuntosTotales() && !(PuntTL - Aventurero.getPuntosTotales() < -3))
				CambiaMensajeInforma("Has recuperado " + (-1 * (PuntTL - Aventurero.getPuntosTotales()) + " puntos"),
						false);
			else if (PuntTL > Aventurero.getPuntosTotales() && !(PuntTL - Aventurero.getPuntosTotales() > 3))
				CambiaMensajeInforma("Has gastado " + (PuntTL - Aventurero.getPuntosTotales() + " puntos"), false);
		}
	}

	/**
	 * Activa o desactiva la opcion de guardar. Si se está en modo desconectado
	 * nunca se activa.
	 * 
	 * @param sucio
	 *            Activa o desactiva la opción de guardar; <code>true</code>
	 *            para activar, <code>false</code> para desactivar.
	 * @beaninfo preferred: true
	 */
	private void sucio(boolean sucio) {
		if (!sucio || !Conectado || AlteraDatosBD.esLeyenda(Aventurero.getNombre())) {
			BtnGuardasCambios.setEnabled(false);
		} else {
			BtnGuardasCambios.setEnabled(true);
		}
	}

	/**
	 * Introduce la lista de clases en el JSelect.
	 * 
	 */
	private void actualizaListaClases() {
		for (String s : Consul.getListaClases()) {
			Clases.addItem(s);
		}
	}

	/**
	 * Introduce la lista de razas en el JSelect.
	 * 
	 */
	private void actualizaListaRazas() {
		for (String s : Consul.getListaRazas()) {
			Razas.addItem(s);
		}
	}

	/**
	 * Introduce las opciones de visualización en el JSelect.
	 * 
	 */
	private void actualizaOpciones() {
		OpcionFoto.addItem("CLASE");
		OpcionFoto.addItem("RAZA");
		OpcionFoto.addItem("PERSONALIZADA");
	}

	/**
	 * Cambia la numeracion de los JSpinner. Por los de las caracteristicas del
	 * aventurero.
	 * 
	 */
	private void actualizaStats() {
		SpinFue.setValue(Aventurero.getFuerzaTotal());
		SpinDes.setValue(Aventurero.getDestrezaTotal());
		SpinCon.setValue(Aventurero.getConstitucionTotal());
		SpinInt.setValue(Aventurero.getInteligenciaTotal());
		SpinSab.setValue(Aventurero.getSabiduriaTotal());
		SpinCar.setValue(Aventurero.getCarismaTotal());
	}

	/**
	 * Rellena todos los elementos visuales. Comprueba si estamos en modo
	 * conectado para visualizar a consecuencia
	 * 
	 * @see #construyeArbol()
	 * @see #actualizaOpciones()
	 * @see #actualizaListaClases()
	 * @see #actualizaListaRazas()
	 */
	private void actualizaTodo() {
		construyeArbol();
		actualizaOpciones();
		if (Conectado) {
			actualizaListaClases();
			actualizaListaRazas();
			Razas.setSelectedIndex(3);
		} else {
			Clases.addItem("GUERRERO");
			Razas.addItem("HUMANO");
			Clases.setEnabled(false);
			Razas.setEnabled(false);
			AbrirSeleccionArbol.setEnabled(false);
			BorraHeroe.setEnabled(false);
		}
		BtnGuardasCambios.setEnabled(false);
	}

	/**
	 * Cambia el aspecto del Entorno Visual. Por la del sistema operativo en el
	 * que se ejecuta la aplicación.
	 * 
	 * @see {@link UIManager.#setLookAndFeel(javax.swing.LookAndFeel)}
	 */
	private static void cambiarAspectoAlDelSistmeOperativo() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Evita la doble ejecución. Escuchando en un perto en desuso, evita la
	 * ejecucion multiple.
	 * 
	 */
	private static void estoyAbierto() {
		try {
			SERVER_SOCKET = new ServerSocket(1334);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"ULTIMATE DUNGEONS & DRAGONS HERO DEVELOPER TURBO 3000 DEMO YA SE ESTÁ EJECUTANDO");
			System.exit(0);
		}
	}

	/**
	 * Carga los tooltips. De todos los elementos, se usa al final de
	 * {@link #initComponents()} para evitar errores.
	 * 
	 * @see #initComponents()
	 */
	private void cargaToolTips() {
		AbrirSeleccionArbol.setToolTipText("Abre el elemento seleccionado del arbol");
		BorraHeroe.setToolTipText("Borra el aventurero seleccionado en el arbol");
		Informacion.setToolTipText("Descripcción detallada del aventurero");
		Clases.setToolTipText("Seleccione la clase para el aventurero");
		Razas.setToolTipText("Seleccione la raza para el aventurero");
		TextNombre.setToolTipText("Nombre del aventurero");
		TextJugador.setToolTipText("Nombre del creador del aventurero");
		OpcionFoto.setToolTipText("Seleccione la imagen a mostrar");
		BotonCambiaFoto.setToolTipText("Cambia la imagen por una ubicada en su equipo");
		BotonRestaurar.setToolTipText("Devuelve todos los stats a 8 puntos");
		BtnGuardasCambios.setToolTipText("Guarda al aventurero en la base de datos");
		LabelInforma.setToolTipText("Informa del estado actual");
		LabelPuntosTotales.setToolTipText("Puntos totales a repartir entre los Stats");
		LabelVida.setToolTipText("Vida total del heroe: (Dado de golpe de la clase*(1+(nivel/2)))+(Constitucion-10)/2");
		SpinCar.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
		SpinSab.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
		SpinFue.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
		SpinInt.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
		SpinDes.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
		SpinCon.setToolTipText("No se puede reducir más, subir cuesta 1 punto");
	}

	/**
	 * Devuelve un Texto para el Tooltip. De los Spinner, dependiendo de su
	 * puntuación.
	 * 
	 * @param actual
	 *            Valor del spinner.
	 * @return El texto nuevo para el Tooltip.
	 */
	private String actualizaSpinnerTooltip(int actual) {
		switch (actual) {
		case 8:
			return "No se puede reducir más, subir cuesta 1 punto";
		case 9:
		case 10:
		case 11:
		case 12:
			return "Bajar devuelve 1 punto, subir cuesta 1 punto";
		case 13:
			return "Bajar devuelve 1 punto, subir cuesta 1 punto";
		case 14:
			return "Bajar devuelve 1 punto, subir cuesta 2 puntos";
		case 15:
			return "Bajar devuelve 2 puntos, subir cuesta 2 puntos";
		case 16:
			return "Bajar devuelve 2 puntos, subir cuesta 3 puntos";
		case 17:
			return "Bajar devuelve 3 puntos, subir cuesta 3 puntos";
		case 18:
			return "Bajar devuelve 3 puntos, no se puede subir más";
		default:
			return "LAS TARTAN SON EL PODER ABSOLUTO";
		}
	}

	/**
	 * Ejecuta un repaint. Y actualiza la imagen.
	 * 
	 * @see #escalaFoto(ImageIcon)
	 */
	public void pintar() {
		FotoHeroe.setIcon(escalaFoto(new ImageIcon(Aventurero.getFoto())));
		Informacion.setText(Aventurero.getInformacion());
		LabelNombre.setText(Aventurero.getNombre());
		LabelJugador.setText(Aventurero.getJugador());
		this.repaint();
	}

	/**
	 * Abre un <code>JFileChooser</code>. Para seleccionar una imagen
	 * personalizada para el aventurero.
	 * 
	 * @see #cambiaFoto(File)
	 */
	private void seleccionarOtraImagen() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(
				new FileNameExtensionFilter("Archivos de imagen; jpg, png...", "jpg", "png", "gif", "jpeg"));
		chooser.showOpenDialog(this);
		File f = chooser.getSelectedFile();
		cambiaFoto(f);
	}

	/**
	 * Cambia la imagen del aventurero. Por una personalizada.
	 * 
	 * @see #escalaFoto(ImageIcon)
	 * @param foto
	 *            Imagen seleccionada para el aventurero.
	 * @exception <code>IOException</code>
	 *                Que el archivo seleccionado esté dañado o no sea una
	 *                imagen.
	 */
	private void cambiaFoto(File foto) {
		if (foto != null) {
			try {
				BufferedImage i = ImageIO.read(foto);
				ImageIcon fot = new ImageIcon(i);
				FotoHeroe.setIcon(escalaFoto(fot));
				sucio(true);
				Aventurero.setFoto(fotoToByte(foto));
				OpcionFoto.setSelectedIndex(2);
				this.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cambia el tamaño de una imagen. Por el de la imagen actual y sustituye a
	 * la anterior.
	 * 
	 * @param foto
	 *            La imagen a redimensionar
	 */
	protected static ImageIcon escalaFoto(ImageIcon foto) {
		return new ImageIcon(
				// foto.getImage().getScaledInstance(FotoHeroe.getWidth(),
				// FotoHeroe.getHeight(), Image.SCALE_DEFAULT)));
				foto.getImage().getScaledInstance(300, 365, Image.SCALE_DEFAULT));
	}

	/**
	 * Convierte una imagen (<code>File</code>) a byte[].
	 * 
	 * @param foto
	 *            Imagen a convertir.
	 * @return <code>byte[]</code> La imagen en formato de mapa de
	 *         <code>bytes</code>
	 */
	private byte[] fotoToByte(File foto) {
		{
			FileInputStream fileInputStream = null;
			byte[] bFile = new byte[(int) foto.length()];
			try {
				fileInputStream = new FileInputStream(foto);
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bFile;
		}
	}

	/**
	 * Inicializa todos los componentes visuales.
	 * 
	 * @see javax.swing.JFrame
	 */
	private void initComponents() {
		setTitle("ULTIMATE DUNGEONS & DRAGONS HERO DEVELOPER TURBO 3000 DEMO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(_AnchoX, _AltoY, _Ancho, _Alto);
		setMinimumSize(new Dimension(_Ancho, _Alto));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaAventurero.class.getResource("/fotos/fotos.ico/dnd_logo.png")));
		PanelHeroe = new JPanel();

		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				if (event.getID() == 402 && isPie)
					tartas((KeyEvent) event);
			}
		}, 8);

		getContentPane().add(PanelHeroe, BorderLayout.CENTER);
		PanelHeroe.setLayout(new BorderLayout(0, 0));

		PanelConDeTodo = new JPanel();
		PanelHeroe.add(PanelConDeTodo, BorderLayout.NORTH);
		PanelConDeTodo.setLayout(new BorderLayout(0, 0));

		PanelStats = new JPanel();
		PanelConDeTodo.add(PanelStats, BorderLayout.CENTER);
		PanelStats.setLayout(new BorderLayout(0, 0));

		PanelDatos = new JPanel();
		PanelStats.add(PanelDatos, BorderLayout.NORTH);
		PanelDatos.setLayout(new BorderLayout(0, 0));

		PanelRazaClase = new JPanel();
		PanelDatos.add(PanelRazaClase, BorderLayout.NORTH);

		LabelClase = new JLabel("Clase: ");
		LabelClase.setDisplayedMnemonic('s');
		PanelRazaClase.add(LabelClase);

		Clases = new JComboBox<String>();
		LabelClase.setLabelFor(Clases);
		Clases.setFont(PTerra.MyFont(0, 12));
		PanelRazaClase.add(Clases);
		Clases.setAlignmentY(Component.TOP_ALIGNMENT);
		Clases.setAlignmentX(Component.LEFT_ALIGNMENT);

		LabelRaza = new JLabel("Raza: ");
		LabelRaza.setDisplayedMnemonic('z');
		PanelRazaClase.add(LabelRaza);

		Razas = new JComboBox<String>();
		LabelRaza.setLabelFor(Razas);
		Razas.setFont(PTerra.MyFont(0, 12));
		PanelRazaClase.add(Razas);

		LabelOpcionFoto = new JLabel("Imagen: ");
		LabelOpcionFoto.setDisplayedMnemonic('m');
		PanelRazaClase.add(LabelOpcionFoto);

		OpcionFoto = new JComboBox<String>();
		LabelOpcionFoto.setLabelFor(OpcionFoto);
		OpcionFoto.setFont(PTerra.MyFont(0, 12));
		PanelRazaClase.add(OpcionFoto);

		PanelNombreFich = new JPanel();
		PanelDatos.add(PanelNombreFich);
		PanelNombreFich.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		LabelNivel = new JLabel("Nivel: ");
		PanelNombreFich.add(LabelNivel);

		SpinnerNivel = new JSpinner();
		SpinnerNivel.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		PanelNombreFich.add(SpinnerNivel);
		SpinnerNivel.setEnabled(false);

		LabelNombre = new JLabel("Nombre: ");
		LabelNombre.setDisplayedMnemonic('n');
		PanelNombreFich.add(LabelNombre);

		TextNombre = new JTextField();
		LabelNombre.setLabelFor(TextNombre);
		TextNombre.setFont(PTerra.MyFont(0, 12));

		TextNombre.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(javax.swing.event.DocumentEvent evt) {
				guardaCambios();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent evt) {
				guardaCambios();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent evt) {
				guardaCambios();
			}

			private void guardaCambios() {
				Aventurero.setNombre(TextNombre.getText().trim());
				if (AlteraDatosBD.esLeyenda(Aventurero.getNombre().trim()))
					CambiaMensajeInforma("Es un nombre de leyenda", true);
				else
					CambiaMensajeInforma("", false);
				if (!(Aventurero.getNombre().length() < 2))
					sucio(true);
			}

		});

		TextNombre.setText(Aventurero.getNombre());
		PanelNombreFich.add(TextNombre);
		TextNombre.setColumns(10);

		TextJugador = new JTextField();
		TextJugador.setFont(PTerra.MyFont(0, 12));
		TextJugador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(javax.swing.event.DocumentEvent evt) {
				guardaJugador();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent evt) {
				guardaJugador();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent evt) {
				guardaJugador();
			}

			private void guardaJugador() {
				Aventurero.setJugador(TextJugador.getText().trim());
				if (!Aventurero.getJugador().equals(""))
					sucio(true);
			}
		});

		LabelJugador = new JLabel("Jugador: ");
		LabelJugador.setDisplayedMnemonic('j');
		LabelJugador.setLabelFor(TextJugador);
		PanelNombreFich.add(LabelJugador);
		TextJugador.setText(Aventurero.getJugador());
		PanelNombreFich.add(TextJugador);
		TextJugador.setColumns(10);

		if (Conectado) {

			Razas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aventurero.cambiaRaza(Razas.getSelectedItem().toString());
					actualizaStats();
					actualizaLabels();
					if (isVisible() && OpcionFoto.getSelectedItem().equals("RAZA"))
						opciFoto("RAZA");
					sucio(true);
				}
			});

			Clases.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Aventurero.cambiaClase(Clases.getSelectedItem().toString());
					actualizaStats();
					actualizaLabels();
					if (isVisible() && OpcionFoto.getSelectedItem().equals("CLASE"))
						opciFoto("CLASE");
					sucio(true);
				}
			});
		}
		OpcionFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isVisible()) {
					opciFoto(OpcionFoto.getSelectedItem().toString());
				}
			}
		});

		PanelCaracteristicas = new JPanel();
		PanelStats.add(PanelCaracteristicas, BorderLayout.CENTER);
		PanelCaracteristicas.setLayout(new GridLayout(0, 1, 0, 0));

		Panel_1 = new JPanel();
		Panel_1.setMinimumSize(new Dimension(40, 40));
		PanelCaracteristicas.add(Panel_1);
		Panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		LabelVida = new JLabel(
				"<html>Puntos de vida: <b><u><font color=\"green\">" + Aventurero.getVida() + "</font></u></b></html>");
		LabelVida.setHorizontalAlignment(SwingConstants.LEFT);
		Panel_1.add(LabelVida);

		LabelPuntosTotales = new JLabel("<html>Puntos Totales: <b><u><font color=\"green\">"
				+ Aventurero.getPuntosTotales() + "</font></u></b></html>");
		LabelPuntosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		Panel_1.add(LabelPuntosTotales);

		LabelInforma = new JLabel();
		LabelInforma.setPreferredSize(new Dimension(151, 24));
		Panel_1.add(LabelInforma);
		LabelInforma.setForeground(new Color(0, 0, 0));
		LabelInforma.setHorizontalAlignment(SwingConstants.CENTER);

		LabelFue = new JLabel("Fuerza");
		PanelCaracteristicas.add(LabelFue);

		SpinFue = new JSpinner();
		PanelCaracteristicas.add(SpinFue);

		SpinFue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Fue");
				sucio(true);
			}
		});

		SpinFue.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		LabelDes = new JLabel("Destreza");
		PanelCaracteristicas.add(LabelDes);
		SpinDes = new JSpinner();
		PanelCaracteristicas.add(SpinDes);

		SpinDes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Des");
				sucio(true);
			}
		});
		SpinDes.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		LabelCon = new JLabel("Constitucion");
		PanelCaracteristicas.add(LabelCon);
		SpinCon = new JSpinner();
		PanelCaracteristicas.add(SpinCon);

		SpinCon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Con");
				sucio(true);
			}
		});
		SpinCon.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		LabelInt = new JLabel("Inteligencia");
		PanelCaracteristicas.add(LabelInt);
		SpinInt = new JSpinner();
		PanelCaracteristicas.add(SpinInt);

		SpinInt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Int");
				sucio(true);
			}
		});
		SpinInt.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		LabelSab = new JLabel("Sabiduria");
		PanelCaracteristicas.add(LabelSab);
		SpinSab = new JSpinner();
		PanelCaracteristicas.add(SpinSab);

		SpinSab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Sab");
				sucio(true);
			}
		});
		SpinSab.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		LabelCar = new JLabel("Carisma");
		PanelCaracteristicas.add(LabelCar);
		SpinCar = new JSpinner();
		PanelCaracteristicas.add(SpinCar);

		SpinCar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiaStat("Car");
				sucio(true);
			}
		});
		SpinCar.setModel(new SpinnerNumberModel(8, 6, 20, 1));

		PanelHuecoBlanco = new JPanel();
		PanelCaracteristicas.add(PanelHuecoBlanco);
		PanelHuecoBlanco.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		PanelFoto = new JPanel();
		PanelConDeTodo.add(PanelFoto, BorderLayout.EAST);
		FotoHeroe = new JLabel(new ImageIcon(Aventurero.getFoto()));
		PanelFoto.setLayout(new BorderLayout(0, 0));

		Panel_3 = new JPanel();
		PanelFoto.add(Panel_3, BorderLayout.WEST);
		PanelFoto.add(FotoHeroe);

		Panelbotonesfoto = new JPanel();
		PanelFoto.add(Panelbotonesfoto, BorderLayout.SOUTH);
		BotonCambiaFoto = new JButton("Cambiar Imagen");
		BotonCambiaFoto.setMnemonic('c');
		BotonCambiaFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarOtraImagen();
			}
		});
		Panelbotonesfoto.add(BotonCambiaFoto);
		BotonCambiaFoto.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		BotonRestaurar = new JButton("Reinicializar");
		BotonRestaurar.setMnemonic('r');
		BotonRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aventurero.setCarisma(8);
				Aventurero.setInteligencia(8);
				Aventurero.setSabiduria(8);
				Aventurero.setConstitucion(8);
				Aventurero.setDestreza(8);
				Aventurero.setFuerza(8);
				Aventurero.setPuntosTotales(28);
				actualizaStats();
				CambiaMensajeInforma("Reinicializando stats", false);
			}
		});
		Panelbotonesfoto.add(BotonRestaurar);

		BtnGuardasCambios = new JButton("Guardar");
		BtnGuardasCambios.setMnemonic('g');
		Panelbotonesfoto.add(BtnGuardasCambios);
		BtnGuardasCambios.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		BtnGuardasCambios.setAlignmentX(Component.RIGHT_ALIGNMENT);
		BtnGuardasCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarAventurero();
			}
		});

		PanelTextArea = new JPanel();
		PanelHeroe.add(PanelTextArea, BorderLayout.CENTER);
		PanelTextArea.setLayout(new CardLayout(0, 0));

		Informacion = new JTextArea();
		Informacion.setFont(PTerra.MyFont(0, 14));
		Informacion.setLineWrap(true);
		Informacion.setWrapStyleWord(true);
		Informacion.setText(Aventurero.getInformacion());
		Informacion.setMargin(new Insets(2, 5, 2, 5));
		Informacion.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

			public void insertUpdate(javax.swing.event.DocumentEvent evt) {
				guardaInformacion();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent evt) {
				guardaInformacion();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent evt) {
				guardaInformacion();
			}

			private void guardaInformacion() {
				if (!Informacion.getText().trim().equals("")) {
					Aventurero.setInfo(Informacion.getText());
					sucio(true);
				}
			}
		});
		ScrollInformacion = new JScrollPane(Informacion);
		ScrollInformacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollInformacion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		PanelTextArea.add(ScrollInformacion, "name_8855712049922");

		FotoArcoiris = new JLabel("New label");
		PanelTextArea.add(FotoArcoiris, "name_8855760740559");

		PanelArbol = new JPanel();
		PanelArbol.setPreferredSize(new Dimension(187, 511));
		PanelArbol.setBorder(UIManager.getBorder("Button.border"));
		getContentPane().add(PanelArbol, BorderLayout.WEST);
		PanelArbol.setLayout(new BorderLayout(0, 0));

		Reset = new JPopupMenu();
		JMenuItem Resetear = new JMenuItem("Borrar todos los heroes");
		Resetear.addActionListener((a) -> borraTodo());
		Reset.add(Resetear);

		TreePanel = new JPanel();
		TreePanel.setLayout(new BorderLayout(0, 0));
		PanelArbol.add(TreePanel, BorderLayout.CENTER);

		Arbol = new JTree();
		Arbol.setFont(PTerra.MyFont(0, 14));
		DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) Arbol.getCellRenderer();
		render.setLeafIcon(new ImageIcon(VentanaAventurero.class.getResource("/fotos/fotos.ico/ico1.png")));
		render.setOpenIcon(new ImageIcon(VentanaAventurero.class.getResource("/fotos/fotos.ico/libro_cerrado.png")));
		render.setClosedIcon(new ImageIcon(VentanaAventurero.class.getResource("/fotos/fotos.ico/libro_cerrado.png")));
		if (Conectado) {
			Arbol.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int selRow = Arbol.getRowForLocation(e.getX(), e.getY());
					if (selRow != -1) {
						if (e.getClickCount() == 2) {
							if (Arbol.getLastSelectedPathComponent().toString() != null)
								queHaSeleccionado(Arbol.getLastSelectedPathComponent().toString());
						}
					}
				}
			});
		} else
			Arbol.setEnabled(false);
		ScrollArbol = new JScrollPane(Arbol);
		TreePanel.add(ScrollArbol, BorderLayout.CENTER);

		PanelBotonesArbol = new JPanel();
		PanelArbol.add(PanelBotonesArbol, BorderLayout.SOUTH);
		PanelBotonesArbol.setLayout(new BorderLayout(0, 0));

		PanelAbrirBorrar = new JPanel();
		PanelBotonesArbol.add(PanelAbrirBorrar, BorderLayout.SOUTH);

		AbrirSeleccionArbol = new JButton("Abrir");
		AbrirSeleccionArbol.setMnemonic('a');
		AbrirSeleccionArbol.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		AbrirSeleccionArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arbol.getLastSelectedPathComponent().toString() != null)
					queHaSeleccionado(Arbol.getLastSelectedPathComponent().toString());
			}
		});
		PanelAbrirBorrar.setLayout(new BorderLayout(0, 0));
		PanelAbrirBorrar.add(AbrirSeleccionArbol, BorderLayout.WEST);

		BorraHeroe = new JButton("Borrar Heroe");
		BorraHeroe.setMnemonic('b');
		BorraHeroe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arbol.getLastSelectedPathComponent() != null) {
					String her = Arbol.getLastSelectedPathComponent().toString();
					if (Consul.aventureroExist(her)) {
						if (!AlteraDatosBD.esLeyenda(her)) {
							try {
								Consul.delAventurero(her);
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "FALLO CRITICO EN LA BASE DE DATOS", "Warning", 0);
								return;
							}
						} else {
							CambiaMensajeInforma("No se puede borrar", true);
							JOptionPane.showMessageDialog(null, "LAS LEYENDAS NO DEBEN CAER EN EL OLVIDO", "Warning",
									2);
						}
						Filtro = null;
						construyeArbol();
						pintar();
					}
				}
			}
		});
		BorraHeroe.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		BorraHeroe.setHorizontalAlignment(SwingConstants.CENTER);
		PanelAbrirBorrar.add(BorraHeroe);

		PanelBotonFiltra = new JPanel();
		PanelBotonesArbol.add(PanelBotonFiltra, BorderLayout.NORTH);
		PanelBotonFiltra.setLayout(new BorderLayout(0, 0));

		BotonFiltra = new JButton("Filtrar");
		BotonFiltra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FiltraAventureros filtro = new FiltraAventureros(Consul);
					Filtro = filtro.getHeroes();
					filtro.dispose();
					construyeArbol();
				} catch (SQLException e1) {

				}
			}
		});
		if (!Conectado) {
			BotonFiltra.setEnabled(false);
		}
		BotonFiltra.setToolTipText("Filtra los aventureros");
		BotonFiltra.setMnemonic('f');
		PanelBotonFiltra.add(BotonFiltra);

		if (Conectado) {
			BorraHeroe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						Reset.show(e.getComponent(), e.getX(), e.getY());
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.isPopupTrigger()) {
						Reset.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
		}
		cargaToolTips();
	}
}

package aventureroUI;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import aventureroCORE.CargaPTerra;
import aventureroCORE.Clase;
import aventureroCORE.Raza;

/**
 * Entorno visual para mostrar la descripción de las Razas y las Clases.
 * 
 * @see aventureroCORE.Raza
 * @see aventureroCORE.Clase
 * @see VentanaAventurero
 * @see aventureroDAO.AlteraDatosBD
 * @author Tefery
 * @since 0.5.2
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class VentanaDescripcion extends JFrame {

	/**
	 * Las resoluciones para las ventanas
	 * 
	 * @see #_Alto
	 * @see #_AltoY
	 * @see #_AnchoX
	 */
	private static final int _Alto = 395;
	private static final int _Ancho = 790;
	private static final int _AltoY = ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (_Alto / 2)
			- 100);
	private static final int _AnchoX = ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (_Ancho / 2)
			+ 100);

	private ImageIcon Foto;
	private String Descripcion;
	private String Titulo;
	private String Nombre;
	private JScrollPane scrollPane;

	/**
	 * Construlle todo entorno visual.
	 * 
	 * @param claseoraza
	 *            La {@link aventureroCORE.Clase} o la
	 *            {@link aventureroCORE.Raza} con la que se va ha inicializar.
	 */
	private void inicializa(Object claseoraza) {
		if (claseoraza instanceof Clase) {
			Clase clase = (Clase) claseoraza;
			Nombre = ((Clase) claseoraza).getNombre();
			Foto = new ImageIcon(clase.getFoto());
			Descripcion = clase.getDescripcion();
			Titulo = "El " + clase.getNombre().substring(0, 1)
					+ clase.getNombre().substring(1, clase.getNombre().length()).toLowerCase();
		} else if (claseoraza instanceof Raza) {
			Raza raza = (Raza) claseoraza;
			Nombre = ((Raza) claseoraza).getNombre();
			Foto = new ImageIcon(raza.getFoto());
			Descripcion = raza.getDescripcion();
			Titulo = "El " + raza.getNombre().substring(0, 1)
					+ raza.getNombre().substring(1, raza.getNombre().length()).toLowerCase();
		} else
			this.getDefaultCloseOperation();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VentanaDescripcion))
			return false;
		VentanaDescripcion O = (VentanaDescripcion) o;
		return this.Nombre.equals(O.Nombre);
	}

	/**
	 * Crea una VentanaDescripcion. Con la {@link aventureroCORE.Clase} o la
	 * {@link aventureroCORE.Raza}, y una fuente.
	 * 
	 * @param claseoraza
	 *            La {@link aventureroCORE.Clase} o la
	 *            {@link aventureroCORE.Raza} con la que se va ha inicializar.
	 * @param PTerra
	 *            La cuente con la que se construirá.
	 */
	public VentanaDescripcion(Object claseoraza, CargaPTerra PTerra) {
		inicializa(claseoraza);
		setFont(PTerra.MyFont(1, 18));
		setTitle(Titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(_AnchoX, _AltoY, _Ancho, _Alto);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaAventurero.class.getResource("/fotos/fotos.ico/dnd_logo.png")));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel PanelPrincipal = new JPanel();
		getContentPane().add(PanelPrincipal, BorderLayout.CENTER);
		PanelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel PanelFoto = new JPanel();
		PanelPrincipal.add(PanelFoto, BorderLayout.EAST);
		PanelFoto.setLayout(new BorderLayout(0, 0));

		JLabel LabelFoto = new JLabel(VentanaAventurero.escalaFoto(Foto));
		PanelFoto.add(LabelFoto);

		JPanel PanelDescripcion = new JPanel();
		PanelPrincipal.add(PanelDescripcion, BorderLayout.CENTER);
		PanelDescripcion.setLayout(new BorderLayout(0, 0));

		JTextArea Informacion = new JTextArea();
		Informacion.setFont(PTerra.MyFont(0, 14));
		Informacion.setLineWrap(true);
		Informacion.setWrapStyleWord(true);
		Informacion.setText(Descripcion);
		Informacion.setEditable(false);
		Informacion.setMargin(new Insets(0, 10, 0, 15));

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(Informacion);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		PanelDescripcion.add(scrollPane, BorderLayout.CENTER);
		Informacion.select(0, 0);
	}
}

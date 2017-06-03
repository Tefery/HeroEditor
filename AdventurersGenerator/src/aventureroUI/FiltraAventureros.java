package aventureroUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Entorno visual para le filtro.
 * 
 * @see VentanaAventurero
 * @see aventureroDAO.AlteraDatosBD
 * @author Tefery
 * @since 0.7.0
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class FiltraAventureros extends JDialog {

	/**
	 * Las resoluciones para las ventanas
	 * 
	 * @see #_Alto
	 * @see #_AltoY
	 * @see #_AnchoX
	 */
	private static final int _Alto = 300;
	private static final int _Ancho = 400;
	private static final int _AltoY = ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (_Alto / 2)
			- 100);
	private static final int _AnchoX = ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (_Ancho / 2)
			+ 100);
	private JTextField TextJugador;
	private JTextField TextNombre;
	private JComboBox<String> ComboClases;
	private JComboBox<String> ComboRazas;
	private ArrayList<String> listhero;
	private aventureroDAO.AlteraDatosBD Consul;
	private JButton btnCancelar;
	private JButton btnAplicar;

	/**
	 * Rellena los combobox. Con una lista de <code>String</code>.
	 * 
	 * @see aventureroDAO.AlteraDatosBD#getListaClases()
	 * @see aventureroDAO.AlteraDatosBD#getListaRazas()
	 */
	private void rellenaCombobox() {
		ComboClases.addItem("Cualquiera");
		for (String s : Consul.getListaClases()) {
			ComboClases.addItem(s);
		}
		ComboRazas.addItem("Cualquiera");
		for (String s : Consul.getListaRazas()) {
			ComboRazas.addItem(s);
		}
	}

	/**
	 * Devuelve la lista de heroes ya filtrada.
	 * 
	 * @return La lista filtrada.
	 */
	public ArrayList<String> getHeroes() {
		return listhero;
	}

	/**
	 * Realiza el filtro. y lo guarda en un <code>ArrayList<String></code>.
	 * 
	 * @see #btnAplicar
	 */
	private void aceptar() {
		if (hayAlgo()) {
			listhero = Consul.getListaHeroesFiltrado(TextNombre.getText(), TextJugador.getText(),
					(String) ComboClases.getSelectedItem(), (String) ComboRazas.getSelectedItem());
			setVisible(false);
			if (listhero.size() < 1) {
				JOptionPane.showMessageDialog(null, "No se obtuvieron resultados", "Atención", 1);
				listhero = null;
			}
		} else {
			listhero = null;
			setVisible(false);
		}
	}

	/**
	 * Comprueba si el filtro tiene algo.
	 * 
	 * @return <code>true</code> si está vacio, <code>false</code> en caso
	 *         contrario.
	 */
	private boolean hayAlgo() {
		if (TextJugador.getText().length() > 0 || TextNombre.getText().length() > 0
				|| ComboClases.getSelectedIndex() != 0 || ComboRazas.getSelectedIndex() != 0)
			return true;
		else
			return false;
	}

	/**
	 * Crea un FiltraAventureros. con la conexión.
	 * 
	 * @param consul
	 *            La conexion a la base de datos.
	 * @throws SQLException
	 *             En casod e no conseguir realizar la consulta.
	 */
	public FiltraAventureros(aventureroDAO.AlteraDatosBD consul) throws SQLException {

		Consul = consul;
		KeyAdapter EnterEscape = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 27)
					setVisible(false);
				else if (e.getKeyCode() == 10)
					aceptar();
			}
		};
		setModal(true);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setTitle("Filtro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(_AnchoX, _AltoY, 265, 200);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaAventurero.class.getResource("/fotos/fotos.ico/dnd_logo.png")));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel PanelPrincipal = new JPanel();
		PanelPrincipal.addKeyListener(EnterEscape);
		getContentPane().add(PanelPrincipal);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setDisplayedMnemonic('n');

		JLabel lblJugador = new JLabel("Jugador:");
		lblJugador.setDisplayedMnemonic('j');

		JLabel lblClase = new JLabel("Clase:");
		lblClase.setDisplayedMnemonic('c');
		lblClase.setLabelFor(lblClase);

		JLabel lblRaza = new JLabel("Raza:");
		lblRaza.setDisplayedMnemonic('r');

		TextNombre = new JTextField();
		TextNombre.addKeyListener(EnterEscape);
		lblNombre.setLabelFor(TextNombre);
		TextNombre.setColumns(10);

		TextJugador = new JTextField();
		TextJugador.addKeyListener(EnterEscape);
		lblJugador.setLabelFor(TextJugador);
		TextJugador.setColumns(10);

		ComboClases = new JComboBox<String>();
		ComboClases.addKeyListener(EnterEscape);

		ComboRazas = new JComboBox<String>();
		ComboRazas.addKeyListener(EnterEscape);
		lblRaza.setLabelFor(ComboRazas);
		GroupLayout gl_PanelPrincipal = new GroupLayout(PanelPrincipal);
		gl_PanelPrincipal.setHorizontalGroup(gl_PanelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelPrincipal.createSequentialGroup().addContainerGap()
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.LEADING).addComponent(lblNombre)
								.addComponent(lblJugador).addComponent(lblClase).addComponent(lblRaza))
						.addGap(18)
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ComboRazas, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(
										ComboClases, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(TextJugador)
								.addComponent(TextNombre, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
						.addContainerGap(156, Short.MAX_VALUE)));
		gl_PanelPrincipal.setVerticalGroup(gl_PanelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelPrincipal.createSequentialGroup().addContainerGap()
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre)
								.addComponent(TextNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.BASELINE).addComponent(lblJugador)
								.addComponent(TextJugador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.BASELINE).addComponent(lblClase)
								.addComponent(ComboClases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_PanelPrincipal.createParallelGroup(Alignment.BASELINE).addComponent(lblRaza)
								.addComponent(ComboRazas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(141, Short.MAX_VALUE)));
		PanelPrincipal.setLayout(gl_PanelPrincipal);

		JPanel PanelBotones = new JPanel();
		getContentPane().add(PanelBotones, BorderLayout.SOUTH);
		PanelBotones.setLayout(new GridLayout(0, 2, 0, 0));

		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		btnAplicar.addKeyListener(EnterEscape);
		btnAplicar.setMnemonic('a');
		PanelBotones.add(btnAplicar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(EnterEscape);
		btnCancelar.setMnemonic('e');
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listhero = null;
				setVisible(false);
			}
		});
		PanelBotones.add(btnCancelar);
		rellenaCombobox();
		setVisible(true);
	}
}

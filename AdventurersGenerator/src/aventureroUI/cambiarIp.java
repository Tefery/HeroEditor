package aventureroUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class cambiarIp extends JDialog {
	private JTextField textField;
	private VentanaAventurero va;
	public cambiarIp(VentanaAventurero va) {
		setTitle("Direccion del Servidor");
		setAlwaysOnTop(true);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cierra();
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.EAST);
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		this.va = va;
		textField.setText(va.ipUpdate);
		
		Dimension tamanio = new Dimension(315,73);
		this.setSize(tamanio);
		this.setMaximumSize(tamanio);
		this.setMinimumSize(tamanio);
		this.setModal(true);
		this.setAlwaysOnTop (true);
		this.setModalityType (ModalityType.APPLICATION_MODAL);
		this.setBounds(VentanaAventurero._AnchoX, VentanaAventurero._AltoY, 315, 73);
		
	}

	public void cierra() {
		if(textField.getText().length() > 5) {
			va.ipUpdate = textField.getText();
		}
		this.dispose();
	}
}

package practicaExamen.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import practicaExamen.conversor.Fichero;
import practicaExamen.conversor.Temperatura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Aplicaci&oacute;n que nos permite realizar conversiones 
 * entre grados Fahrenheit y grados Celsius.
 * 
 * @author Estela Mu&ntilde;oz Cord&oacute;n.
 *
 */

public class CelsiusFahrenheit extends JDialog {

	/**
	 * Identificador de versi&oacute;n.
	 */
	private static final long serialVersionUID = -4969028096732399523L;
	private final JPanel contentPanel = new JPanel();
	private JTextField celsius;
	private JTextField fahrenheit;

	/**
	 * Ejecuta la aplicaci&oacute;n.
	 */
	public static void main(String[] args) {
		try {
			CelsiusFahrenheit dialog = new CelsiusFahrenheit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea la ventana de di&aacute;logo.
	 */
	public CelsiusFahrenheit() {
		setResizable(false);
		setTitle("Convertidor Temperatura");
		setBounds(100, 100, 249, 163);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel celsiusEtiqueta = new JLabel("Celsius");
		celsiusEtiqueta.setBounds(21, 23, 72, 20);
		contentPanel.add(celsiusEtiqueta);
		
		JLabel FahrenheitEtiqueta = new JLabel("Fahrenheit");
		FahrenheitEtiqueta.setBounds(130, 23, 72, 20);
		contentPanel.add(FahrenheitEtiqueta);
		
		celsius = new JTextField();
		celsius.setText("0");
		celsius.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				aFahrenheit();
			}
		});
		celsius.setBounds(21, 50, 86, 20);
		contentPanel.add(celsius);
		celsius.setColumns(10);
		
		fahrenheit = new JTextField();
		fahrenheit.setText("32");
		fahrenheit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				aCelsius();
			}
		});
		fahrenheit.setColumns(10);
		fahrenheit.setBounds(130, 50, 86, 20);
		contentPanel.add(fahrenheit);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton resetBoton = new JButton("Reset");
				resetBoton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						reset();
					}
				});
				resetBoton.setActionCommand("OK");
				buttonPane.add(resetBoton);
				getRootPane().setDefaultButton(resetBoton);
			}
			{
				JButton salirBoton = new JButton("Salir");
				salirBoton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						salir();
					}
				});
				salirBoton.setActionCommand("Cancel");
				buttonPane.add(salirBoton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
		
		abrir();
	}

	/**
	 * Resetea los campos a unos valores iniciales: 0 y 32.
	 */
	private void reset() {
		celsius.setText("0");
		fahrenheit.setText("32");
	}

	/**
	 * Convierte de Fahrenheit a Celsius.
	 */
	private void aCelsius() {
		try {
			celsius.setText(Double.toString(Temperatura.aCelsius(Double.parseDouble(fahrenheit.getText()))));
		} catch (NumberFormatException | NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Oops...Los grados fahrenheit no son correctos...", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Convierte de Celsius a Fahrenheit.
	 */
	private void aFahrenheit() {
		try {
			fahrenheit.setText(Double.toString(Temperatura.aFahrenheit(Double.parseDouble(celsius.getText()))));
		} catch (NumberFormatException | NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Oops...Los grados celsius no son correctos...", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

	/**
	 * Sale del programa.
	 */
	private void salir() {
		guardar();	
		setVisible(false);
		dispose();
		System.exit(0); // más bonito con un dispose y un setVisible(false) antes
	}

	/**
	 * Guarda el fichero.
	 */
	private void guardar() {
		try {
			Fichero.guardar(Double.parseDouble(celsius.getText()));
		} catch (NumberFormatException | IOException e) {
			JOptionPane.showMessageDialog(this, 
				"Oops...Los grados celsius no son correctos y no pudieron guardarse...", "Error al guardar", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Abre el fichero.
	 */
	private void abrir() {
		try {
			Fichero.abrir();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, 
				"Oops... Los datos guardados están dañados...", "Error al recuperar los datos", JOptionPane.ERROR_MESSAGE);
		}
	}
}

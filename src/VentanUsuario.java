import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
//	private JChatCliente principal;
	
	private Inicio principal;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanUsuario frame = new VentanUsuario(principal);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanUsuario(Inicio principal) {
		this.principal = principal;
		setTitle("Ingrese usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(65, 40, 192, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escribir();
			}
		});
		btnAceptar.setBounds(65, 88, 93, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(164, 88, 93, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(65, 15, 53, 14);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(principal);
	}
	
	private void escribir() {
		principal.escribirUsuario(textField.getText());
		dispose();
	}
}

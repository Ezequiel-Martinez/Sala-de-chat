import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;

public class JChatCliente extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
//	private JMenuBar menuBar;
//	private JMenu mnOpciones;
//	private JMenuItem mntmConectar;
	private JTextArea textArea;
	private JButton btnEnviar;

	private Cliente cliente;
	private Usuario usuario;

	private int cantCliente;
	/**
	 * @wbp.nonvisual location=81,9
	 */
	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JChatCliente frame = new JChatCliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public JChatCliente(Usuario usuario, int puerto) throws UnknownHostException, IOException {
//		cliente = new Cliente(50000, "localhost");
//		cliente.inicializarHiloCliente(this);
		this.usuario = usuario;
		iniciarCliente(puerto);
		String titulo = "Chat - " + usuario.getId();
		vista(titulo);
	}

	/**
	 * Create the frame.
	 */
	public void vista(String titulo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getContentPane().setLayout(new CardLayout(0, 0));
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 281);

//		menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		mnOpciones = new JMenu("Opciones");
//		menuBar.add(mnOpciones);
//		
//		mntmConectar = new JMenuItem("Conectar");
//		mntmConectar.setEnabled(true);

//		mntmConectar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				abrirVentana();
//			}
//		});

//		mnOpciones.add(mntmConectar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarTexto();
			}
		});
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 1;
		contentPane.add(btnEnviar, gbc_btnEnviar);
	}

	public void escribirMensajeEnTextArea(String mensaje) {
		textArea.append(mensaje + "\n");
//		textArea.setLineWrap(true);
//		textArea.setText(mensaje);

	}

	public void iniciarCliente(int puerto) throws UnknownHostException, IOException {
		cliente = new Cliente(puerto, "localhost");
		cliente.inicializarHiloCliente(this);
		cantCliente++;
	}
//	public void escribirUsuario(String usuario) {
//		String newTitle;
//		this.usuario = usuario;
//		newTitle = this.getTitle()+"-"+usuario;
//		this.setTitle(newTitle);
//		this.btnEnviar.setEnabled(true);
//		this.mntmConectar.setEnabled(false);
//		try {
//			cliente = new Cliente(50000, "localhost");
//			cliente.inicializarHiloCliente(this);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	private void abrirVentana() {
////		new VentanUsuario(this).setVisible(true);
//	}

	private void enviarTexto() {
		if (usuario != null) {
			String text = this.textField.getText();
			text = usuario.getId() + ": " + text;
			try {
				cliente.enviarMensaje(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			this.textArea.append(text);
			this.textField.setText("");
		}

	}

	public int getCantClientes() {
		return cantCliente;
	}

	public void close() throws IOException {
//		if (cliente != null) {
//			cliente.cerrarCliente();
//		}

	}
}

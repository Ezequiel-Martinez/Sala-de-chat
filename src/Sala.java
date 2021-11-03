
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import interfaz.SalaDeJuegoListener;

import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;

public class Sala extends JPanel {

	private final int PUERTO_INI = 50000;
	private final int HEIGHT = 65;

	private ArrayList<Usuario> usuarios;
	private String nombre;

	private SalaDeJuegoListener listener;
	JLabel labelNombre;
	private JButton btnEntrar;

	private Servidor servidor;
	private Cliente cliente;
	private JChatCliente chat;
	private Usuario usuario;
	private String cantUsers = "0";

	private int puerto = PUERTO_INI;
	private JLabel lblCantUsers;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */

	private void puerto() {
		this.puerto++;
	}

	public Sala(String nombre) {
		// necesitamos algo para saber el id actual
		// id =
		this.nombre = nombre;
		usuarios = new ArrayList<Usuario>();
//		puerto();
//		System.out.println(puerto);
//		try {
//			servidor = new Servidor(puerto);
//			servidor.ejecutar();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		inicializarVista();
	}

	@Override
	public String toString() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void inicializarVista() {
		setBackground(new Color(0, 177, 64));
		setForeground(Color.YELLOW);
		setSize(542, 65);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		labelNombre = new JLabel(nombre);
		labelNombre.setForeground(Color.WHITE);
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
		add(labelNombre);

		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.WEST, labelNombre, 0, SpringLayout.WEST, separator);
		springLayout.putConstraint(SpringLayout.NORTH, separator, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, separator, -18, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, separator, 1270, SpringLayout.WEST, this);
		add(separator);

		btnEntrar = new JButton("Entrar a sala");
		if (this.usuario != null) {
			btnEntrar.setEnabled(true);
		} else {
			btnEntrar.setEnabled(false);
		}

		springLayout.putConstraint(SpringLayout.NORTH, labelNombre, 4, SpringLayout.NORTH, btnEntrar);
		springLayout.putConstraint(SpringLayout.SOUTH, btnEntrar, -6, SpringLayout.NORTH, separator);
		springLayout.putConstraint(SpringLayout.EAST, btnEntrar, -22, SpringLayout.EAST, this);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				listener.onClickSala();
				try {
					conectar();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnEntrar);

		JLabel lblCantUsersTitle = DefaultComponentFactory.getInstance().createTitle("Usuarios Conectados:");
		lblCantUsersTitle.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, lblCantUsersTitle, 0, SpringLayout.NORTH, labelNombre);
		springLayout.putConstraint(SpringLayout.WEST, lblCantUsersTitle, 33, SpringLayout.EAST, labelNombre);
		add(lblCantUsersTitle);

		lblCantUsers = new JLabel("0");
		lblCantUsersTitle.setLabelFor(lblCantUsers);
		lblCantUsers.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblCantUsers, 6, SpringLayout.EAST, lblCantUsersTitle);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCantUsers, 0, SpringLayout.SOUTH, labelNombre);
		add(lblCantUsers);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(AppConstants.getWidthRelationAspect(), HEIGHT);
	}

	public void setListener(SalaDeJuegoListener listener) {
		this.listener = listener;
	}

	public void habilitarSalas() {
		btnEntrar.setEnabled(true);
	}

	public boolean recvUsuario(String usuar_id) {
		if (!usuar_id.isBlank()) {
			for (Usuario user : usuarios) {
				if (!usuar_id.equals(user.getId())) {
					usuario = new Usuario(usuar_id, this);
					usuarios.add(usuario);
					return true;
				}
			}
		}
		return false;
	}

	public void conectar() throws UnknownHostException, IOException {
		if (usuarios.size() > 0) {
			for (Usuario user : usuarios) {
				if (!usuario.getId().equals(user.getId())) {
//				usuario = new Usuario(usuar_id, this);
					System.out.println(usuario.getId());
					System.out.println(user.getId());
					usuarios.add(usuario);
					chat = new JChatCliente(this.usuario, this.puerto);
					chat.setVisible(true);
					
//				cliente = new Cliente(50000, "localhost");
//				cliente.inicializarHiloCliente(this);
					int auxUsers = Integer.parseInt(cantUsers);
					auxUsers++;
					cantUsers = String.valueOf(auxUsers);
					lblCantUsers.setText(cantUsers);
				}
			}
		} else {
			usuarios.add(usuario);
			chat = new JChatCliente(this.usuario, this.puerto);
			chat.setVisible(true);
			
//			cliente = new Cliente(50000, "localhost");
//			cliente.inicializarHiloCliente(this);
			int auxUsers = Integer.parseInt(cantUsers);
			auxUsers++;
			cantUsers = String.valueOf(auxUsers);
			lblCantUsers.setText(cantUsers);
		}

	}

	public void newUser(String usuario) {
		this.usuario = new Usuario(usuario, this);
	}
}

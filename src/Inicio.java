
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import interfaz.SalaDeJuegoListener;

import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Inicio extends JFrame {

	private ArrayList<Sala> salas;

	private Usuario usuario;
	private Cliente cliente;

	private int nmSala = 0;
	private JMenuItem mntmCrearSala;

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
		salas = new ArrayList<Sala>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setVisible(true);
		setBackground(new Color(238, 232, 170));
		setBounds(100, 100, 731, 526);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);

		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mnOpciones.add(mntmConectar);

		mntmCrearSala = new JMenuItem("CrearSala");
		mntmCrearSala.setEnabled(false);
		mntmCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearSala();
			}
		});
		mnOpciones.add(mntmCrearSala);

		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentana();
			}
		});
	}

	public void agregarSala(Sala sala) {
		salas.add(sala);
		sala.setMaximumSize(sala.getPreferredSize());
		sala.setListener(new SalaDeJuegoListener() {

			@Override
			public void onClickSala() {
				clickSala(sala);

			}
		});
		getContentPane().add(sala);
		revalidate();
	}

	private void clickSala(Sala sala) {
		System.out.println(sala);
	}

	private void abrirVentana() {
		new VentanUsuario(this).setVisible(true);
	}

	public void escribirUsuario(String usuario) {
//		String newTitle;
//		newTitle = this.getTitle()+"-"+usuario;
//		this.setTitle(newTitle);
//		this.btnEnviar.setEnabled(true);
//		this.mntmConectar.setEnabled(false);
//		try {
		for (Sala sala : salas) {
//			if (sala.recvUsuario(usuario)) {
			sala.newUser(usuario);
			sala.habilitarSalas();
//			}

		}

		this.mntmCrearSala.setEnabled(true);
//			cliente = new Cliente(50000, "localhost");
//			cliente.inicializarHiloCliente(this);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void crearSala() {
		this.nmSala++;
		String titulo = "Sala ";
		titulo = titulo + nmSala;
		Sala sala = new Sala(titulo);
		this.agregarSala(sala);
		if (this.usuario != null) {
			if (sala.recvUsuario(usuario.getId())) {
				sala.habilitarSalas();
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
//					String titulo = "Sala ";
//					titulo = titulo + nmSala;
//					SalaDeJuego sala1 = new SalaDeJuego("Sala 1");
//					SalaDeJuego sala2 = new SalaDeJuego("Sala 2");
//					frame.agregarSala(sala1);
//					frame.agregarSala(sala2);
					frame.crearSala();
					frame.crearSala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

//	public void escribirMensajeEnTextArea(String mensaje) {
////		textArea.append(mensaje+"\n");
////		textArea.setLineWrap(true);
////		textArea.setText(mensaje);
//
//	}
}

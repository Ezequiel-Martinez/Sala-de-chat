import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private Socket socket;
	private int puerto;
	private String ip;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	public Cliente(int puerto, String ip) throws UnknownHostException, IOException {
		this.puerto = puerto;
		this.ip = ip;
		
		socket = new Socket(ip, puerto);
		salida = new DataOutputStream(socket.getOutputStream());
		entrada = new DataInputStream(socket.getInputStream());
		
		
	}
	
	public void enviarMensaje(String mensaje) throws IOException {
		salida.writeUTF(mensaje);
	}
	
	public void inicializarHiloCliente(JChatCliente ventana) {
		new HiloCliente(this.entrada,ventana).start();
	}
	
//	public void inicializarHiloCliente(Inicio ventana) {
//		new HiloCliente(this.entrada,ventana).start();
//	}
	
	public void cerrarCliente() throws IOException {
//		System.out.println("cerrando");
//		
////		entrada.close();
////		salida.close();
//		socket.close();
	}
}

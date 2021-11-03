import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	private int puerto;
	private ServerSocket server;
	private ArrayList<Socket> sockets;
	
	public Servidor(int puerto) throws IOException {
		this.puerto = puerto;
		server = new ServerSocket(this.puerto);
		sockets = new ArrayList<Socket>();
		System.out.println("Server "+ puerto + "inicializando...");
		
//		for(int i = 1; i <= 3; i++) {
//			Socket socket = servidor.accept();
//			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
//			salida.writeUTF("Sos el cliente Nro: " + i);
//			
//			salida.close();
//			socket.close();
//		}
//		
//		System.out.println("Server Finalizado");
//		servidor.close();
	}
	
	public void ejecutar() throws IOException {
		Socket socket;
		while(true) {
			System.out.println("escuchando...");
			socket = server.accept();
			sockets.add(socket);
			new HiloServidor(socket,sockets).start();
			
			
		}
	}
	
	public static void main(String arg[]) throws IOException {
		new Servidor(50000).ejecutar();
	}
}

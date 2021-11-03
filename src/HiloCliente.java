import java.io.DataInputStream;
import java.io.IOException;

public class HiloCliente extends Thread{
	private DataInputStream entrada;
	private JChatCliente ventana;
//	private Inicio ventana;
	
	public HiloCliente(DataInputStream entrada, JChatCliente ventana) {
		this.entrada = entrada;
		this.ventana = ventana;
	}
	
//	public HiloCliente(DataInputStream entrada, Inicio ventana) {
//		this.entrada = entrada;
//		this.ventana = ventana;
//	}
	
	public void run() {
		String mensaje;
		
		while(true) {
			try {
				mensaje = entrada.readUTF();
				ventana.escribirMensajeEnTextArea(mensaje);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

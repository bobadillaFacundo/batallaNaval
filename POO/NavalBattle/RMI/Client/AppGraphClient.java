package NavalBattle.RMI.Client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.View.IView;
import NavalBattle.MVC.View.Graph.GraphView;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

public class AppGraphClient {

	public static void main(String[] args) throws RMIMVCException, IOException {
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(null,
				"Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
				JOptionPane.QUESTION_MESSAGE, null, ips.toArray(), null);
		String port = (String) JOptionPane.showInputDialog(null,
				"Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente", 
				JOptionPane.QUESTION_MESSAGE, null,null,null);
		String ipServidor = (String) JOptionPane.showInputDialog(null, "Seleccione la IP en la corre el servidor",
				"IP del servidor", JOptionPane.QUESTION_MESSAGE, null, null, null);
		String portServidor = (String) JOptionPane.showInputDialog(null,
				"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", JOptionPane.QUESTION_MESSAGE,
				null, null, 8888);
			
			try {
				Controller controller = new Controller();
			    Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
				IView view = new GraphView(controller);
				controller.setView(view);
				c.iniciar(controller);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (RMIMVCException e) {
				e.printStackTrace();
			}
		}
	}

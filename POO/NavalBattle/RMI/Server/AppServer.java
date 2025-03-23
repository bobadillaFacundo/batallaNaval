package NavalBattle.RMI.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import NavalBattle.MVC.Model.Game;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class AppServer {

	public static void main(String[] args) throws RemoteException {
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(null,
				"Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
				JOptionPane.QUESTION_MESSAGE, null, ips.toArray(), null);
		String port = (String) JOptionPane.showInputDialog(null,
				"Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
				JOptionPane.QUESTION_MESSAGE, null, null, 8888);
			
			try {
				Game model = new Game();
			Servidor server = new Servidor(ip, Integer.parseInt(port));
				server.iniciar(model);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (RMIMVCException e) {
				e.printStackTrace();
		}
	}

}

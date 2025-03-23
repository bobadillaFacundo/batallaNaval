package NavalBattle.MVC.View.Graph;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.View.IView;


public class GraphView extends JFrame implements Serializable, IView {

	private static final long serialVersionUID = -5276096852892577328L;
	private Controller controller;
	private BoardBoat boardBoat;
	private PanelMenuStart panelMenuStart;
	private PanelMenuAddBoat panelMenuAdd;
	private PanelMenuGame panelMenuGame;
	private PanelMenuFinalized panelMenuFinalized;
	
	public GraphView(Controller controller) throws RemoteException {
		setTitle("Batalla Naval\r\n");
		setAutoRequestFocus(false);
		setResizable(false);
		setVisible(true);
		this.controller=controller;
		initialize();
		
	}

	private void initialize()throws RemoteException {	 
		panelMenuStart = new PanelMenuStart(controller);
		panelMenuStart.setVisible(true);
		getContentPane().add(panelMenuStart);
		panelMenuStart.updateUI();
		this.setBounds(0, 0, 1373, 700);
		
	}

	@Override
	public void startGame() throws RemoteException {
		panelMenuStart.setVisible(false);
		remove(panelMenuStart);
		boardBoat= new BoardBoat(controller);
		panelMenuAdd = new PanelMenuAddBoat(this.controller,boardBoat);
		getContentPane().add(panelMenuAdd);
		panelMenuAdd.updateUI();
		panelMenuAdd.setVisible(true);
	}

	@Override
	public void play() throws RemoteException {
		remove(panelMenuAdd);
		panelMenuGame = new PanelMenuGame(this.controller,boardBoat);
		getContentPane().add(panelMenuGame);
		panelMenuGame.updateUI();
		panelMenuGame.setVisible(true);
		panelMenuGame.controllerTurn();
		panelMenuGame.updateUI();
	}

	@Override
	public void statusFinalized() throws RemoteException {
		String g = "";

		if(controller.getTurnPlayer().equals(controller.getId()))
			g= "Felicidades "+controller.getPlayerName()+" as ganado el juego";
		else
			g= "Lo sentimos "+controller.getPlayerName()+" as perdido el juego";
		
		remove(panelMenuGame);
		panelMenuFinalized = new PanelMenuFinalized(g);
		getContentPane().add(panelMenuFinalized);
		panelMenuFinalized.updateUI();
		panelMenuFinalized.setVisible(true);
		this.setBackground(Color.BLACK);
		
		panelMenuFinalized.getBtnContinuar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(panelMenuFinalized);
				try {
					initialize();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
			
	}

	@Override
	public void quantityBoat() throws RemoteException {
		Integer c = null;
		controller.setQuantityBoat(c);

	}

	@Override
	public void leavePlayer() throws RemoteException {
		JOptionPane.showMessageDialog(this, "El otro Jugador dejo la Partida");
		statusFinalized();
	}

	@Override
	public void turnChange() throws RemoteException {
		panelMenuGame.controllerTurn();
	}

}

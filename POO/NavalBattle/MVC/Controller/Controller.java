package NavalBattle.MVC.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import NavalBattle.MVC.Model.GameStates;
import NavalBattle.MVC.Model.IGame;
import NavalBattle.MVC.View.IView;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class Controller implements IControladorRemoto,Serializable {
	
	private static final long serialVersionUID = -4734085605060738213L;
	private IGame game;
	private IView view;
	private String id;

	public String getId() {
		return id;
	}

	public Controller() {
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T game) {
		this.game = (IGame) game;
	}

	public void setView(IView view) {
		this.view = view;
	}

	@Override
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if (arg1 instanceof GameStates) {
			GameStates status = (GameStates) arg1;
			switch (status) {
			case INITIATED:
					view.startGame();
				break;
			case FINALIZED:
				view.statusFinalized();
				break;
			case PLAYING: try {
					view.play();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				break;
			case LEAVEPLAYER:{ view.leavePlayer();
				arg0.removerObservador(this);
				break;
			}
			case TURNCHANGE:{
				try {
					view.turnChange();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			default:
				break;

			}
		}

	}

	public GameStates placeBoat(Integer[] position, Boolean orientation, Integer length) throws RemoteException {
		return game.placeBoat(position, orientation, length, id);
	}

	public String getPlayerName() throws RemoteException {
		return game.getPlayerName(id);
	}

	public String getTurnPlayer() throws RemoteException {
		return game.getIdTurnPlayer();
	}

	public GameStates placeMissile(Integer[] position) throws IOException {
		return game.placeMissile(position, id);
	}

	public String topFivePlayers() throws FileNotFoundException, IOException {
		return game.topFivePlayers();
	}

	public void leaveGame() throws RemoteException {
		game.leaveGame(this.id);
	}

	public void addPlayer() throws RemoteException {
		this.id = game.addPlayer();
	}
	
	public GameStates getMissileBoxState(Integer[] position) throws RemoteException {
		return game.getMissileBoxState(position, id);	
	}

	public GameStates getBoatBoxState(Integer[] position) throws RemoteException {
		return game.getBoatBoxState(position, id);	
	}

	public void setPlayerName(String name) throws RemoteException {
		game.setPlayerName(name, id);
	}

	public GameStates getGameState() throws RemoteException {
		return game.getGameState();
	}

	public void setQuantityBoat(Integer c) throws RemoteException {
		this.game.setQuantityBoat(c);
		
	}

	public Integer getQuantityBoat() throws RemoteException {
		return this.game.getQuantityBoat();
	}
}

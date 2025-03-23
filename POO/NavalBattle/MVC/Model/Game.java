package NavalBattle.MVC.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import NavalBattle.MVC.Model.Persistence.Persistence;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Game extends ObservableRemoto implements Serializable, IGame {

	private static final long serialVersionUID = 8766090350991188269L;
	private GameStates state = GameStates.INACTIVE;
	private Player player1 = null;
	private Player player2 = null;
	private Persistence persistence;
	private Float rounds;
	private String playerTurn;
	private Integer quantityBoat;
	
	public Integer getQuantityBoat() {
		return quantityBoat;
	}

	public void setQuantityBoat(Integer quantityBoat) {
		this.quantityBoat = quantityBoat;
	}

	public Game() throws RemoteException {
		this.persistence = new Persistence("TopFivePlayers.txt");
		this.quantityBoat=6;
	}

	@Override
	public GameStates placeBoat(Integer[] pos, Boolean ori, Integer l, String id) throws RemoteException {
		GameStates result = calculatePlayer(id).placeBoat(pos, ori, l);// posiciono el barco y devuelvo el resultado
		if ((player1.getFleetQuantity()== quantityBoat) && (player2.getFleetQuantity() == quantityBoat)&&(this.state==GameStates.INITIATED)) {
			this.playerTurn=this.player1.getID();
			this.state = GameStates.PLAYING;
			notificar(GameStates.PLAYING);
			rounds = (float) 0;
			
		}
		return result;
	}

	private Player calculatePlayer(String id) {
		
		if (id.equals(String.valueOf(player1.hashCode())))
			return player1;

		if (id.equals(String.valueOf(player2.hashCode())))
			return player2;

		return null;
	}

	private Player calculateInversePlayer(String id)  {
		
		if (player1.hashCode() == Integer.parseInt(id))
			return player2;

		if (player2.hashCode() == Integer.parseInt(id))
			return player1;

		return null;
	}

	@Override
	public GameStates placeMissile(Integer[] pos, String id) throws IOException {
		GameStates es = calculateInversePlayer(id).getBoatBoxState(pos);// guardo que hay en la "pos" dada
		
		if (es == GameStates.BOAT) {// controlo si habia un barco
			calculateInversePlayer(id).setBoatBoxState(pos); // cambiar matris barco enemigo
			calculatePlayer(id).placeMissile(pos, GameStates.TOUCHEDBOAT);
			calculate(id,pos);
			es=GameStates.SUCCESS;
		}else {
			if(es ==GameStates.ERROR) {
				es = calculatePlayer(id).placeMissile(pos, GameStates.WATER);
				es = GameStates.SUCCESS;
			}else es=GameStates.ERROR;
				
		}
		if(es==GameStates.SUCCESS) { // tirar misil y guardar el resultado
			this.turnChange();
		}
		
		return es;
	}

	private void calculate(String id, Integer[] pos) {
		System.out.println(calculateInversePlayer(id).controllerBoat(pos)==GameStates.SUNKENBOAT);
		if(calculateInversePlayer(id).controllerBoat(pos)==GameStates.SUNKENBOAT){
			ArrayList<Box> b = calculateInversePlayer(id).getPositionBoat(pos);
			for (int i = 0; i < b.size(); i++) {
				Integer[] po = new Integer[2];
				po[0] = b.get(i).getPosition()[0];
				po[1] = b.get(i).getPosition()[1];
				calculatePlayer(id).setMissileBoxState(po, GameStates.SUNKENBOAT);
			}
		}
		}

	@Override
	public GameStates getMissileBoxState(Integer[] pos, String id) throws RemoteException {
		return calculatePlayer(id).getMissileBoxState(pos);
	}

	@Override
	public GameStates getBoatBoxState(Integer[] pos, String id) throws RemoteException {
		return calculatePlayer(id).getBoatBoxState(pos);
	}

	@Override
	public GameStates setMissileBoxState(Integer[] pos, GameStates e, String id) throws RemoteException {
		return calculatePlayer(id).setMissileBoxState(pos, e);
	}


	@Override
	public String getIdTurnPlayer() throws RemoteException {
		return this.playerTurn;
	}

	private void defaultSettings() throws RemoteException {
		player1.defaultSettings();
		player2.defaultSettings();
		state = GameStates.INACTIVE;
	}

	public void setPlayerName(String name, String id) throws RemoteException {
		calculatePlayer(id).setName(name);
	}

	public String getPlayerName(String id) throws RemoteException {
		return calculatePlayer(id).getName();
	}

	@Override
	public String topFivePlayers() throws FileNotFoundException, IOException {
		return persistence.read();
	}

	public void notificar(GameStates state) throws RemoteException {
		notificarObservadores(state);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void leaveGame(String id) throws RemoteException {

		if (this.player1.equals(id)) {
			this.player1 = null;
			defaultSettings();
			try {
				this.notificar(GameStates.LEAVEPLAYER);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		if (this.player2.equals(id)) {
			defaultSettings();
			this.player2 = null;
		}
	}

	public String addPlayer() throws RemoteException {
		if (player1 == null) {
			player1 = new Player();
			playersController();
			return player1.getID();
		} else {
			if (player2 == null) {
				player2 = new Player();
				playersController();
				return player2.getID();
			}
		}
		return null;

	}

	public void playStart() throws RemoteException {
		if (readyPlayers() == GameStates.READY) {
			state = GameStates.PLAYING;
			notificar(state);
		}
	}

	private GameStates readyPlayers() throws RemoteException {
		GameStates esperar = GameStates.WAITING;
		Integer c = 0;
		if ((player1 != null) && (player2 != null)) {
			if (player1.getState() == (GameStates.READY)) {
				c++;
			}
			if (c == 2)
				esperar = GameStates.READY;
		}
		playersController();
		return esperar;
	}

	private void turnChange() throws IOException {
		if (calculateInversePlayer(this.playerTurn).controlFlota()) {
			finalized();
		} else {
			rounds = rounds + 1 / 2;
			String x = this.playerTurn;; 
			this.playerTurn = calculateInversePlayer(x).getID();
			notificar(GameStates.TURNCHANGE);
		}
	}

	private void finalized() throws IOException {
		persistence.write( calculatePlayer(this.playerTurn).getName());
		defaultSettings();
		notificarObservadores(GameStates.FINALIZED);
		
	}

	public void setEsatdoJugador(GameStates x, String id) throws RemoteException {
		calculatePlayer(id).setState(x);
	}

	public GameStates getEstadoJugador(String id) throws RemoteException {
		return calculatePlayer(id).getState();
	}

	private void playersController() throws RemoteException {
		if ((player1 != null) && (player2 != null)) {
			this.state = GameStates.INITIATED;
			notificar(GameStates.INITIATED);
		}
	}

	@Override
	public GameStates getGameState() throws RemoteException {
		return this.state;
	}
	
	
}

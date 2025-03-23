package NavalBattle.MVC.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IGame extends IObservableRemoto{

	GameStates getMissileBoxState(Integer[] position, String id)throws RemoteException;

	GameStates getBoatBoxState(Integer[] pos, String id)throws RemoteException;

	GameStates setMissileBoxState(Integer[] pos, GameStates e, String id)throws RemoteException;

	String getIdTurnPlayer()throws RemoteException;

	void playStart() throws RemoteException;

	void notificar(GameStates state) throws RemoteException;

	void setEsatdoJugador(GameStates x, String id)throws RemoteException;

	GameStates getEstadoJugador(String id)throws RemoteException;

	void setPlayerName(String name, String id)throws RemoteException;

	String getPlayerName(String id)throws RemoteException;

	 String addPlayer()throws RemoteException;

	String topFivePlayers()throws RemoteException, FileNotFoundException, IOException;

	void leaveGame(String id)throws RemoteException;

	GameStates getGameState()throws RemoteException;

	GameStates placeBoat(Integer[] position, Boolean ori, Integer l, String id) throws RemoteException;

	GameStates placeMissile(Integer[] position, String id) throws RemoteException, IOException;
	
	Integer getQuantityBoat() throws RemoteException ;
	
	void setQuantityBoat(Integer quantityBoat) throws RemoteException ;

}

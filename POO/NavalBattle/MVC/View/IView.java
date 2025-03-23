package NavalBattle.MVC.View;

import java.io.IOException;
import java.rmi.RemoteException;

public interface IView {

	void startGame() throws RemoteException;

	void quantityBoat() throws RemoteException;

	void play() throws RemoteException, IOException;

	void statusFinalized() throws RemoteException;

	void leavePlayer()throws RemoteException;

	void turnChange()throws RemoteException, IOException;

}

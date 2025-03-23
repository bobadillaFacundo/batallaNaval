package NavalBattle.MVC.View.Graph;

import java.awt.Button;
import java.awt.Color;
import java.awt.HeadlessException;
import java.rmi.RemoteException;

import NavalBattle.MVC.Model.GameStates;

public class Box extends Button {

	private static final long serialVersionUID = -6704355020799894766L;
	private GameStates box;

	public Box(GameStates box) throws HeadlessException , RemoteException{
		this.box = box;
		this.setBackground(Color.BLUE);
	}

	public GameStates getBox()throws RemoteException {
		return box;
	}

	public void setBox(GameStates box)throws RemoteException {
		this.box = box;
	}

}

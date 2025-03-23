package NavalBattle.MVC.View.Graph;

import java.awt.Panel;
import java.awt.Color;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.Model.GameStates;
import java.rmi.RemoteException;


public class BoardBoat extends Panel {

	private static final long serialVersionUID = 403079420449236330L;
	private Box[][] box;
	private Controller controller;
	
	public Box[][] getBox()throws RemoteException {
		return box;
	}

	public void setBox(Box[][] box) throws RemoteException{
		this.box = box;
	}

	public BoardBoat(Controller controller)throws RemoteException {
		this.controller=controller;
		setBackground(Color.GRAY);
		setLayout(null);
		GameStates state = GameStates.WATER;
		box = new Box[10][10];
		Integer m = 0;
		for (Integer i = 0; i < 10; i++) {
			for (Integer j = 0; j < 10; j++) {
				Box box_00 = new Box(state);
				this.add(box_00, m);
				m++;
				box[i][j] = box_00;
				box[i][j].setForeground(Color.BLUE);
			}
		}
	}

	public void updateStatusBox(Integer[] position, Boolean orientation, Integer length) throws RemoteException {
		for (int i = 0; i < length; i++) {
			if(length.equals(1))
				box[position[0]][position[1]].setBackground(Color.MAGENTA);
			if(length.equals(2))
				box[position[0]][position[1]].setBackground(Color.YELLOW);
			if(length.equals(3))
				box[position[0]][position[1]].setBackground(Color.GREEN);
			if (orientation) {
				position[0]++;
			} else
				position[1]++;
		}
	}

	public void enabled()throws RemoteException {
		for (Integer i = 0; i < 10; i++) {
			for (Integer j = 0; j < 10; j++) {
				box[i][j].setEnabled(false);
			}
		}
	}

	public void updateStateBoat() throws RemoteException {
		if(box!=null) {
			Integer[] position;
			for (int i = 0; i < 10; i++) {
				position= new Integer[2];
				position[0]=i;
				for (int j = 0; j < 10; j++) {
					position[1]=j;
					GameStates g = controller.getBoatBoxState(position);
					if(g==GameStates.TOUCHEDBOAT)
						box[i][j].setBackground(Color.LIGHT_GRAY);
					if(g==GameStates.SUNKENBOAT)
						box[i][j].setBackground(Color.RED);
				}			
			}
	}
}
}

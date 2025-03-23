package NavalBattle.MVC.View.Graph;

import java.awt.Color;
import java.awt.Panel;
import java.rmi.RemoteException;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.Model.GameStates;

public class BoardMissil extends Panel {

	private static final long serialVersionUID = 7095603419833186035L;
	private Box[][] box;
	private Controller controller;

	public Box[][] getBox() {
		return box;
	}

	public void setBox(Box[][] box) throws RemoteException {
		this.box = box;
	}

	public BoardMissil(Controller controller) throws RemoteException {
		this.controller = controller;
		setBackground(Color.GRAY);
		setLayout(null);
		GameStates state = GameStates.EMPTY;
		box = new Box[10][10];
		Integer m = 0;
		for (Integer i = 0; i < 10; i++) {
			for (Integer j = 0; j < 10; j++) {
				Box box_00 = new Box(state);
				this.add(box_00, m);
				this.box[i][j] = box_00;
				this.box[i][j].setBackground(Color.DARK_GRAY);
				m++;
			}
		}

	}

	public void enabledFalse() throws RemoteException {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				box[i][j].setEnabled(false);
			}
		}
	}

	public void changedStatus2() throws RemoteException {
		if (box != null) {
			Integer[] position;
			for (int i = 0; i < 10; i++) {
				position = new Integer[2];
				position[0] = i;
				for (int j = 0; j < 10; j++) {
					position[1] = j;
					GameStates g = controller.getMissileBoxState(position);
					if (g == GameStates.TOUCHEDBOAT)
						box[i][j].setBackground(Color.LIGHT_GRAY);
					if (g == GameStates.SUNKENBOAT)
						box[i][j].setBackground(Color.RED);
					if (g == GameStates.WATER)
						box[i][j].setBackground(Color.BLUE);

				}
			}
		}
	}


	public void enabledTrue() throws RemoteException {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				box[i][j].setEnabled(true);
			}
		}
	}
}

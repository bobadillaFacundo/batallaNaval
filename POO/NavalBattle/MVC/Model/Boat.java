package NavalBattle.MVC.Model;

import java.util.ArrayList;

public class Boat {
	
	private GameStates state =GameStates.BOAT;
	private ArrayList<Box> position;
	
	public GameStates getState() {
		return state;
	}

	public void setState(GameStates state) {
		this.state = state;
	}

	public ArrayList<Box> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Box> position) {
		this.position = position;
	}

	public GameStates getEstado() {
		return state;
	}
	
	public void controllerState() {
		if(position!=null) {
			Integer c=0;
			for (Box box : position) {		
				if(box.getState()==GameStates.TOUCHEDBOAT)
					c++;
			}
			if(c.equals(position.size())){
				state=GameStates.SUNKENBOAT;
				for (Box box : position) {
					box.setState(GameStates.SUNKENBOAT);
				}
			}
		
		}
	}
	
}

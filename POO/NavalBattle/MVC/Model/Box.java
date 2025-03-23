package NavalBattle.MVC.Model;

public class Box {
	private Integer[] position = null;//Posicion de Inicio
	private GameStates state = null;//Estado barco dentro del tablero 
	public Integer[] getPosition() {
		return position;
	}
	public void setPosition(Integer[] position) {
		this.position = position;
	}
	public GameStates getState() {
		return state;
	}
	public void setState(GameStates state) {
		this.state = state;
	}
}

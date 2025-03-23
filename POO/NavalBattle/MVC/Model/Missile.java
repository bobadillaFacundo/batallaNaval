package NavalBattle.MVC.Model;

public class Missile {
	
	private Integer[] posicion; //Posicion del tablero
	private GameStates Estado; //Estado dentro del Tablero 
	
	
	public Integer[] getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer[] posicion) {
		this.posicion = posicion;
	}
	public GameStates getEstado() {
		return Estado;
	}
	public void setEstado(GameStates estado) {
		Estado = estado;
	}
	
}

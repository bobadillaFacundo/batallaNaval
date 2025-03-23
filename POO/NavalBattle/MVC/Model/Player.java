package NavalBattle.MVC.Model;

import java.util.ArrayList;

public class Player{

private String name;// nombre
ArrayList<Missile> m;// Array de misiles
 ArrayList<Boat> f;// Flota de barcos
private GameStates state = GameStates.WAITING;
private String Id;

public Player() {
	this.name = new String();
	this.m = new ArrayList<Missile>();
	this.f = new ArrayList<Boat>();
	this.Id = String.valueOf(this.hashCode());
}

public int getFleetQuantity() {
	Integer result=0;
	if(f!=null)
		result = f.size(); 
	return result;
}

public String getName() {
	return name;
}

public void setName(String Name) {
	if (name != null)
		this.name = Name;
}

public GameStates getState() {
	return state;
}

public void setState(GameStates state) {
	this.state = state;
}

public String getID() {
	return Id;
}
	
public GameStates getBoatBoxState(Integer[] pos) {
	GameStates e = GameStates.ERROR;
	if ((pos != null) && (f != null))
		if ((pos[0]) >= (0) && (pos[0] <= 9))
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				boolean b = true;
				int c=0;
				ArrayList<Box> positionBoat;
				int fila;
				int columna;
				while ((b) && (c < f.size())) {
					positionBoat = f.get(c).getPosition();
					for (Box box : positionBoat) {	
							fila = box.getPosition()[0];
							columna = box.getPosition()[1];
							if (((fila==pos[0])) && (columna==pos[1])) {
								b=false;
								e=box.getState();
							}
					}
					c++;
				}
			}
	return e;
}
	
public GameStates setBoatBoxState(Integer[] pos) {
	GameStates ee = GameStates.ERROR;
	if (pos != null)
		if ((pos[0]) >= (0) && (pos[0] <= 9))
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				
				boolean b = true;
				int c=0;
				ArrayList<Box> positionBoat;
				int fila;
				int columna;
				while ((b) && (c < f.size())) {
					positionBoat = f.get(c).getPosition();
					for (int i = 0; i < positionBoat.size(); i++) {
						fila = positionBoat.get(i).getPosition()[0];
						columna = positionBoat.get(i).getPosition()[1];
						if (((fila==pos[0])) && (columna==pos[1])) {
							positionBoat.get(i).setState(GameStates.TOUCHEDBOAT);
							ee = GameStates.SUCCESS;
						}
					}
					f.get(c).setPosition(positionBoat);
					f.get(c).controllerState();
					c++;
				}
			}
	return ee;
}
	
public void defaultSettings() {
	this.m = new ArrayList<Missile>();
	this.f = new ArrayList<Boat>();
	this.state = GameStates.WAITING;
}

public  GameStates placeBoat(Integer[] pos, Boolean ori, Integer l) {
	GameStates e = GameStates.ERROR;
	if (pos != null) {
		if ((pos[0]) >= (0) && (pos[0] <= 9)) {
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				Integer columna = pos[1];
				Integer fila = pos[0];
				boolean b0;
				if(ori) {
					b0 = ((((l - 1) + fila) <= 9));
				} else b0 = ((((l - 1) + columna) <= 9));
				
				if  (b0)  {
					ArrayList<Box> positionBoat;
					GameStates ee = GameStates.ERROR;
					int fila2 = pos[0];
					int columna2 = pos[1];
					for (int c = 0; c < f.size(); c++) {
						positionBoat = f.get(c).getPosition();
						for (Box box : positionBoat) {
							fila = box.getPosition()[0];
							columna = box.getPosition()[1];
							fila2 = pos[0];
							columna2 = pos[1];
							for (int i = 0; i < l; i++) {
								if (((fila.equals(fila2)) && (columna.equals(columna2)))) {
									ee = GameStates.SUCCESS;
								}
								if(ori)
									fila2++;
								else
									columna2++;
							}
							
						}
					}
					if (ee==GameStates.ERROR) {
						e = GameStates.SUCCESS;
						columna = pos[1];
						fila = pos[0];
						Boat barco = new Boat();
						positionBoat = new ArrayList<Box>();
						Integer[] position ;
						for (int i = 0; i < l; i++) {
							Box box = new Box();
							position = new Integer[2];
							position[0]=fila;
							position[1]=columna;
							box.setState(GameStates.BOAT);
							box.setPosition(position);
							positionBoat.add(box);
							if(ori)
								fila++;
							else
								columna++;
						}
						barco.setState(GameStates.BOAT);
						barco.setPosition(positionBoat);
						f.add(barco);
					}
				}else e=GameStates.ErrorPosicionBarco;
			}
		}
	}
	return e;
}

public Boolean controlFlota() {
	Boolean b = false;
	Integer c = 0;
	for (int i = 0; i < f.size(); i++) {
		if (f.get(i).getEstado() == GameStates.SUNKENBOAT) {
			c++;
		}
	}
	if (c.equals(f.size()))
		b = true;
	return b;
}

public GameStates getMissileBoxState(Integer[] pos) {
	GameStates e = GameStates.ERROR;
	if ((pos != null) && (m != null))
		if ((pos[0]) >= (0) && (pos[0] <= 9))
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				for (Missile missile : m) {
					if((missile.getPosicion()[0].equals(pos[0]))&&
							(missile.getPosicion()[1].equals(pos[1])))
							e=missile.getEstado();
				}
			}
	return e;
}

public GameStates setMissileBoxState(Integer[] pos, GameStates e) {
	GameStates ee = GameStates.ERROR;
	Integer columna;
	Integer fila;
	if (pos != null)
		if ((pos[0]) >= (0) && (pos[0] <= 9))
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				for (int c = 0; c < m.size(); c++) {
					fila=m.get(c).getPosicion()[0];
					columna=m.get(c).getPosicion()[1];
					if(((fila.equals(pos[0]))&&(columna.equals(pos[1])))){
							ee = GameStates.SUCCESS; 
							m.get(c).setEstado(e);
					}	
					}
				}
	return ee;
}

public GameStates placeMissile(Integer[] pos, GameStates es) {
	GameStates e = GameStates.ERROR;
	if (pos != null) {
		if ((pos[0]) >= (0) && (pos[0] <= 9)) {
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				GameStates ee = recorrerMisiles(pos, null, true);
				if (ee==GameStates.ERROR) {
					Missile misil = new Missile();
					misil.setEstado(es);
					misil.setPosicion(pos);
					m.add(misil);
					e = GameStates.SUCCESS;
				} else e = GameStates.EXIST; 
			}else e = GameStates.errorPositionMissil;
		}else e = GameStates.errorPositionMissil;
	} else e = GameStates.errorPositionMissil;
	return e;
}

private GameStates recorrerMisiles(Integer[] pos, GameStates e2, Boolean queHacer) {
	GameStates e = GameStates.ERROR;
	Integer c = 0;
	Integer[] posicion = null;
	posicion = null;
	Boolean b = true;
	while ((b) && (c < m.size())) {
		posicion = m.get(c).getPosicion();
		if ((posicion[0] == pos[0]) && (posicion[1] == pos[1])) {
			if (queHacer)
				e = m.get(c).getEstado();
			else {
				m.get(c).setEstado(e2);
				e = GameStates.SUCCESS;
			}
			b = false;
		}
		if (c == (m.size() - 1))
			b = false;
		c++;
	}
	return e;
}

public Boat getBoat(Integer[] pos) {
	if ((pos != null) && (f != null))
		if ((pos[0]) >= (0) && (pos[0] <= 9))
			if ((pos[1]) >= (0) && (pos[1] <= 9)) {
				boolean b = true;
				int c=0;
				ArrayList<Box> positionBoat;
				int fila;
				int columna;
				for (int i = 0; i < f.size(); i++) {
					f.get(i).controllerState();
				}
				while ((b) && (c < f.size())) {
					f.get(c).controllerState();
					positionBoat = f.get(c).getPosition();
					for (Box box : positionBoat) {	
							fila = box.getPosition()[0];
							columna = box.getPosition()[1];
							if (((fila==pos[0])) && (columna==pos[1])) {
								b=false;
								return f.get(c);
							}
					}
					c++;
				}
			}
	return null;
}

public GameStates controllerBoat(Integer[] pos) {
	return getBoat(pos).getEstado();
}

public ArrayList<Box> getPositionBoat(Integer[] pos) {
	return getBoat(pos).getPosition();
}	


}

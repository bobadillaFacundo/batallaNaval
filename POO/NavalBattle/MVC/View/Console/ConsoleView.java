package NavalBattle.MVC.View.Console;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.Model.GameStates;
import NavalBattle.MVC.View.IView;

public class ConsoleView implements  Serializable, IView {
	
	private static final long serialVersionUID = 907828523973321157L;
	private static Controller myController;
	private Scanner scanner = new Scanner(System.in);
	private Console console;
	private Integer quantityBoat = 4;
	private Boolean state = true;

	public ConsoleView(Controller controller) throws FileNotFoundException,RemoteException {
			console = new Console();
			ConsoleView.myController = controller;
			try {
				this.initialize();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(state) {
			}
	}

	public void topFivePlayer() throws FileNotFoundException, RemoteException {
		try {
			System.out.println(myController.topFivePlayers());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void startGame() throws RemoteException {
		Integer quantityBoat = this.quantityBoat;
		Boolean b = true;
		Integer[] position = new Integer[2];
		Integer fila = null;
		Integer column = null;
		Boolean orientation = null;
		Integer length = 2;
		
		while(b) {
		console.gameConfigurationMenu(quantityBoat);
		String s = scanner.next();
		switch (s) {
		case "1":{
			System.out.println("Ingresar Fila");
			System.out.println("--->>>>");
			s = scanner.next();
			if (console.isNumeric(s)) {
				fila = Integer.decode(s);} else System.out.println("Error Ingreso de la Fila");
			break;
		}
		case "2":{
			System.out.println("Ingresar Columna");
			System.out.println("--->>>>");
			s = scanner.next();
			if (console.isNumeric(s)) {
				column = Integer.decode(s);} else System.out.println("Error Ingreso de la Columna");
			break;
		}
		case "3":{
			System.out.println("1 = horizontal// 0 = vertical");
			System.out.println("--->>>>");
			s = scanner.next();
			switch (s) {
			case "1":
					orientation = false;
				break;
			case "0":
					orientation = true;
				break;
			default: System.out.println("Error al Agregar Orientacion");
				break;
			}
			break;
		}
		case "4":{
			position[0] = fila;
			position[1] = column;
			 
			GameStates C = myController.placeBoat(position, orientation, length);
			
			if((C==(GameStates.SUCCESS))&&(!(quantityBoat==0))) { 
				quantityBoat--;
				System.out.println("Se agrego Barco Correctamente");
				
			} else {
				switch (C) {
					case ErrorOrientacionHayBarco: 
						System.out.println("Error hay otro barco en esa posicion");
						break;
					case ErrorPosicionBarco: 
						System.out.println("Error en la posicion del barco");
						break;
					default: System.out.println("Error");
					break;
				}
			}
			fila = null;
			column = null;
			orientation = null;
			break;
		}
		case "5":{
			break;
		}
		case "6":{
			if(quantityBoat==0)
				b = false;
			else System.out.println("Error falta agregar Todavia "+(quantityBoat)+" barcos");
			break;
		}
		default:
			break;
		}
	}
		
	}
	
	private void initialize() throws FileNotFoundException, RemoteException {
		console.initialMenu();
		String string = scanner.next();
		switch (string) {
		case "1":{
				console.enterNameMenu();
				string = scanner.next();
				myController.addPlayer();
				myController.setPlayerName(string);
				console.waitMenu();
			break;
			}
		case "2":{
			String s = "";
			try {
				s = myController.topFivePlayers();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println(s);
			break;
		}
		case "3":{
			quantityBoat();
			break;
		}
		case "4":{
			myController.leaveGame();
			break;
		}
		default:	break;
		}
	}
	
	public void quantityBoat() throws RemoteException {
		System.out.println("Ingresar Cantidad de Barcos");
		System.out.println("--->>>>");
		String string = scanner.next();
		if (console.isNumeric(string)) {
			if(quantiBoat(Integer.decode(string))) {
				this.quantityBoat = Integer.decode(string);
				System.out.println("Exito");
			}
		}else System.out.println("Error");
	}
	
	public void play() throws RemoteException {
	if(myController.getId().equals(myController.getTurnPlayer())) {	
		System.out.println("Tu turno "+myController.getPlayerName());
		Boolean b = true;
		Integer[] position = new Integer[2];
		String string;
		Integer Fila = null;
		Integer column = null;
		
		while(b) {
		console.gameMenu();
		string = scanner.next();
		switch (string) {
		case "1":{
			Fila = Integer.decode(string);
			break;
		}
		case "2":{
			column = Integer.decode(string);
			break;
		}
		case "3":{
			position[0] = Fila;
			position[1] = column;
			GameStates d = null;
			try {
				d = myController.placeMissile(position);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			b = false;
			switch (d) {
			case TOUCHEDBOAT: System.out.println(d);
				break;
			case WATER: System.out.println(d);
				break;
			case ERROR: {
				System.out.println(d);
				b = true;
				break;
			}	
			default:{
				System.out.println(GameStates.ERROR);
				b= true;
				break;
			}
			}
			break;
		}
		case "4":{ 
			string = generateMissileBoard();
			System.out.println("s");
			break;}
		case "5":{
			try {
				myController.placeMissile(position);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			b=false;
			break;
		}
		default:
			break;
		}
	}
	}else console.waitMenu();
	}
	
	@Override
	public void statusFinalized() throws RemoteException {
		System.out.println("El otro jugador dejo el juego");
	}

	private Boolean quantiBoat(Integer number) {
		if((number>6)||(number<1)) {
			return false;
		}
		return true;
	}

    private String generateMissileBoard() throws RemoteException {
		GameStates state = null;
		Integer[] position = new Integer[2];
		String string="";
		for (int i = 0; i < 10; i++) {
			string = string + "["+String.valueOf(i)+"]";
			for (int j = 0; j < 10; j++) {
				position[0] = i;
				position[1] = j;
				state = myController.getMissileBoxState(position);
				switch (state) {
				case EMPTY: string = string + ("[VA]");
					break;
				case TOUCHEDBOAT : string = string + ("[BT]");
					break;
				default:
					break;
				}
			}
			string = string + ("/n");
		}
		return string;
	}

	@Override
	public void leavePlayer() throws RemoteException {
	}

	@Override
	public void turnChange() throws RemoteException {
		play();
	}

}

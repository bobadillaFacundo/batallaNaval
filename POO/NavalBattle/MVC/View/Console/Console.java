package NavalBattle.MVC.View.Console;

import java.rmi.RemoteException;

public class Console {

	public void enterNameMenu() throws RemoteException {
		System.out.println("*********************************");
		System.out.println("* Ingrese su nombre               *");
		System.out.println("*********************************");
		System.out.println("--->>>>: ");
	}

	public void initialMenu() throws RemoteException {
		System.out.println("************************************");
		System.out.println("* 1-Iniciar Juego                   *");
		System.out.println("* 2-Mostrat TOP Jugadores           *");
		System.out.println("* 3-Modificar la cantidad de Barcos *");
		System.out.println("* 4-Salir                           *");
		System.out.println("************************************");
		System.out.println("--->>>>: ");
	}

	public void gameConfigurationMenu(Integer quantityBoat) throws RemoteException {
		System.out.println("*****************************************************");
		System.out.println("* Colocar Barcos                                    *");
		System.out.println("* Todavia faltan " +String.valueOf(quantityBoat) +                             "*");
		System.out.println("* 1-Ingresar Fila                                 	*");
		System.out.println("* 2-Ingresar Columna                              	*");
		System.out.println("* 3-Ingresar orientacion                          	*");
		System.out.println("* 4-Ingresar barco                                	*");
		System.out.println("* 5-Mostrar Tablero Barcos Puestos                	*");
		System.out.println("* 6-Terminar de Cargar Barcos                     	*");
		System.out.println("*****************************************************");
		System.out.println("--->>>>: ");
	}

	public void gameMenu() throws RemoteException {
		System.out.println("**************************************************");
		System.out.println("* Tirar Misil									 *");
		System.out.println("* 1-Ingresar Fila								 *");
		System.out.println("* 2-Ingresar Columna							 *");
		System.out.println("* 3-Mostrar Tablero Misil Tirados                *");
		System.out.println("* 4-Mostrar Tablero Barcos                       *");
		System.out.println("* 5-Terminar                                     *");
		System.out.println("***************************************************");
		System.out.println("--->>>>: ");
	}

	public boolean isNumeric(String cadena) throws RemoteException {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	public void waitMenu() throws RemoteException {
		System.out.println("**************************************");
		System.out.println("* Esperando a otro Jugador.          *");
		System.out.println("**************************************");
	}

}
	
	  
	


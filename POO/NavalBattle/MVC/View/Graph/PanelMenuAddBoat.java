package NavalBattle.MVC.View.Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.Model.GameStates;
import java.awt.Panel;

public class PanelMenuAddBoat extends  JPanel {

	private static final long serialVersionUID = -939177130078983046L;
	private BoardBoat boarBoat;
	private JLabel lblBarcosPorColocar;
	private JLabel lblEsperandoJugador;
	private Controller controller;
	private String result;
	private Integer count=0;
	private int fragata = 0;
	private int destructor = 0;
	private int acorazado = 0;
	
	public BoardBoat getBoarBoat() throws RemoteException{
		return boarBoat;
	}

	public void setBoarBoat(BoardBoat boarBoat)throws RemoteException {
		this.boarBoat = boarBoat;
	}
	
	public PanelMenuAddBoat(Controller controller, BoardBoat boardBoat) throws RemoteException{
		this.controller=controller;
		setBackground(new Color(0, 0, 0));
		this.setBounds(0, 0, 1111, 602);
		boarBoat = boardBoat;
		boarBoat.setBounds(337, 44, 423, 423);
		boarBoat.setVisible(true);
		boarBoat.setLayout(null);
		add(boarBoat);
		setLayout(null);
		addBoat();
		JLabel lblNewLabel = new JLabel("Colocar Barcos");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(29, 46, 207, 26);
		add(lblNewLabel);
		
		lblEsperandoJugador = new JLabel("Esperando al otro Jugador...................");
		lblEsperandoJugador.setForeground(Color.BLUE);
		lblEsperandoJugador.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblEsperandoJugador.setBounds(215, 531, 629, 31);
		add(lblEsperandoJugador);
		lblEsperandoJugador.setVisible(false);

		lblBarcosPorColocar = new JLabel("Colocar "+controller.getQuantityBoat() +" Barcos\r\n");
		lblBarcosPorColocar.setEnabled(false);
		lblBarcosPorColocar.setForeground(Color.BLACK);
		lblBarcosPorColocar.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBarcosPorColocar.setBounds(38, 99, 257, 21);
		add(lblBarcosPorColocar);
		
		Panel panel = new Panel();
		panel.setBackground(Color.MAGENTA);
		panel.setEnabled(false);
		panel.setBounds(45, 171, 30, 30);
		add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setEnabled(false);
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(45, 207, 30, 30);
		add(panel_1);
		
		Panel panel_2 = new Panel();
		panel_2.setEnabled(false);
		panel_2.setBackground(Color.GREEN);
		panel_2.setBounds(45, 242, 30, 30);
		add(panel_2);
		
		JLabel lblFragata = new JLabel("Fragata");
		lblFragata.setForeground(Color.BLACK);
		lblFragata.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblFragata.setEnabled(false);
		lblFragata.setBounds(81, 180, 103, 21);
		add(lblFragata);
		
		JLabel lblDestructor = new JLabel("Destructor\r\n");
		lblDestructor.setForeground(Color.BLACK);
		lblDestructor.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDestructor.setEnabled(false);
		lblDestructor.setBounds(81, 216, 127, 21);
		add(lblDestructor);
		
		JLabel lblAcorazado = new JLabel("Acorazado\r\n");
		lblAcorazado.setForeground(Color.BLACK);
		lblAcorazado.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblAcorazado.setEnabled(false);
		lblAcorazado.setBounds(81, 253, 127, 21);
		add(lblAcorazado);
	}
	
	public BoardBoat getBoardBoat() throws RemoteException{
		return this.boarBoat;
	}

	public void setVisible() throws RemoteException{
		this.lblBarcosPorColocar.setVisible(false);
		this.lblEsperandoJugador.setVisible(true);
		this.boarBoat.setVisible(false);
	
	}
	
	public void addBoat() throws RemoteException{
		Integer y=10;
		Integer x=10;
		for (Integer i = 0; i < 10; i++) {
			Integer fila = i;
			for (Integer j = 0; j < 10; j++) {
				Integer columna = j;
				boarBoat.getBox()[i][j].setBounds(x, y, 35, 35);
				boarBoat.getBox()[i][j].setVisible(true);
				x = x + 41;
				boarBoat.getBox()[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							if (count < controller.getQuantityBoat()) {
								try {
									Integer[] position = new Integer[2];
									position[0] = fila;
									position[1] = columna;
									Boolean orientation = orientation();
									Integer length = length();
									if ((orientation != null) && (length != null)) {
										GameStates resultstate = (controller.placeBoat(position, orientation, length));
										if (resultstate == GameStates.SUCCESS) {
											count++;
											boarBoat.updateStatusBox(position, orientation, length);
											PanelMenuAddBoat.this.lblBarcosPorColocar.setText("Colocar "+(controller.getQuantityBoat()-count)+" Barcos\r\n");
											
											if(length.equals(1)){
											 	fragata++;
											 }
											if(length.equals(2)){
											 	destructor++;
											  }
											 if(length.equals(3)){
											 	acorazado++;
											 }
											 
											 if(count==controller.getQuantityBoat()) {
													PanelMenuAddBoat.this.lblEsperandoJugador.setVisible(true);
										
											 }
											 
										} else {
											if(resultstate==GameStates.ErrorPosicionBarco)
													JOptionPane.showMessageDialog(PanelMenuAddBoat.this,
															"posicion incorrecta");
											else JOptionPane.showMessageDialog(PanelMenuAddBoat.this,
													"ya hay un Barco en esta posicion");
											
										}
										}
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
							} else
								JOptionPane.showMessageDialog(PanelMenuAddBoat.this, "No se puede cargar mas Barcos");
						} catch (HeadlessException | RemoteException e1) {
							e1.printStackTrace();
						}
					}
	
				});
			}
			y = y + 41;
			x = 10;
		}
		
}

	private Integer length() throws RemoteException{		 
		String message = "Elegir tipo de Barco" + "\n"; 
		ArrayList<String> options = new ArrayList<String>();
		 
		if(fragata<3){
		 	options.add("fragata"); 
		 	
		 }
		  if(destructor<2){
		 		options.add("destructor"); 
		 	
		  }
		 if(acorazado<1){
		 		options.add("acorazado"); 
		 	
		 }
		 
		 result = (String) JOptionPane.showInputDialog(
		  PanelMenuAddBoat.this, message, "opciones", JOptionPane.QUESTION_MESSAGE,
		  null, options.toArray(), null ); 
		 if(result==null)
			 result="";
		 switch (result) { 
		 case "fragata": {  return 1; } 
		 case "destructor": {  return 2; } 
		 case "acorazado": {  return 3; } 
		 default: {
		 JOptionPane.showMessageDialog(PanelMenuAddBoat.this, "Error Barco"); 
		 return null; }
		 }
	}
	
	private Boolean orientation() throws RemoteException{
		String message = "Elegir orientacion del Barco" + "\n";
		ArrayList<String> options = new ArrayList<String>();
		options.add("horizontal");
		options.add("vertical");
		result = (String) JOptionPane.showInputDialog(PanelMenuAddBoat.this, message, "opciones",
				JOptionPane.QUESTION_MESSAGE, null, options.toArray(), null);
		if (result == null)
			result = "";
		switch (result) {
		case "vertical": {
			return true;
		}
		case "horizontal": {
				return false;
		}
		default: {
			JOptionPane.showMessageDialog(PanelMenuAddBoat.this, "Error Orientacion");
			return null;
		}
		}
	}
}

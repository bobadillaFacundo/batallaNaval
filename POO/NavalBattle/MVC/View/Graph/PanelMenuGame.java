package NavalBattle.MVC.View.Graph;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import NavalBattle.MVC.Controller.Controller;
import NavalBattle.MVC.Model.GameStates;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

public class PanelMenuGame extends JPanel {

	private static final long serialVersionUID = 2778723711435530289L;
	private BoardBoat boarBoat;
	private JLabel lblBarcosPorColocar;
	private BoardMissil boardMissil;
	private Controller controller;
	private JLabel lblNewLabel_1;
	private Integer[] position = new Integer[2];
	private JLabel lblNewLabel;
	private JLabel lblTableroMisiles;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblAgua;
	private JLabel lblNada;
	private JLabel lblAgua_1;
	private JLabel lblBarcoTocado;
	private JLabel lblBarcoHundido;
	private JPanel panel_8;
	private JLabel label_3;
	private JLabel label_4;
	private JPanel panel_9;
	
	public PanelMenuGame(Controller controller,BoardBoat boardBoat) throws RemoteException {
		this.setBackground(Color.BLACK);
		this.controller = controller;

		this.setBounds(0, 0, 1320, 602);
		
		boarBoat = boardBoat;
		boarBoat.setBounds(221, 42, 423, 423);
		boarBoat.setVisible(true);
		boarBoat.setLayout(null);
		add(boarBoat);
		boarBoat.enabled();
		boarBoat.setEnabled(false);
		setLayout(null);
		
		lblNewLabel = new JLabel("Tirar Misil\r\n");
		lblNewLabel.setBounds(7, 11, 198, 26);
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.BLUE);
	    add(lblNewLabel);
		boardMissil = new BoardMissil(controller);
		boardMissil.setBounds(659, 42, 423, 423);
		add(boardMissil);
		addMissile();
		
		lblBarcosPorColocar = new JLabel("Tablero Barcos\r\n");
		lblBarcosPorColocar.setBounds(302, 15, 247, 21);
		lblBarcosPorColocar.setEnabled(false);
		lblBarcosPorColocar.setForeground(Color.BLUE);
		lblBarcosPorColocar.setFont(new Font("Stencil", Font.PLAIN, 20));
	    add(lblBarcosPorColocar);
		
	    lblTableroMisiles = new JLabel("Tablero Misiles\r\n");
		lblTableroMisiles.setBounds(766, 15, 247, 21);
		lblTableroMisiles.setEnabled(false);
		lblTableroMisiles.setForeground(Color.BLUE);
		lblTableroMisiles.setFont(new Font("Stencil", Font.PLAIN, 20));
		add(lblTableroMisiles);
		
		lblNewLabel_1 = new JLabel("No es tu turno,Espere al otro Jugador................");
		lblNewLabel_1.setBounds(49, 496, 709, 95);
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.BLUE);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(1088, 63, 30, 30);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(1088, 104, 30, 30);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(1088, 180, 30, 30);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(1088, 139, 30, 30);
		add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.MAGENTA);
		panel_4.setBounds(7, 98, 30, 30);
		add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.YELLOW);
		panel_5.setBounds(7, 139, 30, 30);
		add(panel_5);
		
		panel_6 = new JPanel();
		panel_6.setBackground(Color.GREEN);
		panel_6.setBounds(7, 180, 30, 30);
		add(panel_6);
		
		panel_7 = new JPanel();
		panel_7.setBackground(Color.BLUE);
		panel_7.setBounds(7, 221, 30, 30);
		add(panel_7);
		
		label = new JLabel("Fragata");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Stencil", Font.PLAIN, 20));
		label.setEnabled(false);
		label.setBounds(49, 104, 103, 21);
		add(label);
		
		label_1 = new JLabel("Destructor\r\n");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Stencil", Font.PLAIN, 20));
		label_1.setEnabled(false);
		label_1.setBounds(49, 145, 127, 21);
		add(label_1);
		
		label_2 = new JLabel("Acorazado\r\n");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Stencil", Font.PLAIN, 20));
		label_2.setEnabled(false);
		label_2.setBounds(49, 189, 127, 21);
		add(label_2);
		
		lblAgua = new JLabel("Agua");
		lblAgua.setForeground(Color.BLACK);
		lblAgua.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblAgua.setEnabled(false);
		lblAgua.setBounds(49, 230, 103, 21);
		add(lblAgua);
		
		lblNada = new JLabel("Nada");
		lblNada.setForeground(Color.BLACK);
		lblNada.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblNada.setEnabled(false);
		lblNada.setBounds(1130, 72, 127, 21);
		add(lblNada);
		
		lblAgua_1 = new JLabel("AGUA\r\n");
		lblAgua_1.setForeground(Color.BLACK);
		lblAgua_1.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblAgua_1.setEnabled(false);
		lblAgua_1.setBounds(1130, 113, 88, 21);
		add(lblAgua_1);
		
		lblBarcoTocado = new JLabel("BARCO TOCADO\r\n");
		lblBarcoTocado.setForeground(Color.BLACK);
		lblBarcoTocado.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBarcoTocado.setEnabled(false);
		lblBarcoTocado.setBounds(1128, 148, 156, 21);
		add(lblBarcoTocado);
		
		lblBarcoHundido = new JLabel("BARCO HUNDIDO");
		lblBarcoHundido.setForeground(Color.BLACK);
		lblBarcoHundido.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBarcoHundido.setEnabled(false);
		lblBarcoHundido.setBounds(1130, 195, 166, 21);
		add(lblBarcoHundido);
		
		panel_8 = new JPanel();
		panel_8.setBackground(Color.RED);
		panel_8.setBounds(7, 262, 30, 30);
		add(panel_8);
		
		label_3 = new JLabel("BARCO HUNDIDO");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Stencil", Font.PLAIN, 20));
		label_3.setEnabled(false);
		label_3.setBounds(49, 271, 166, 21);
		add(label_3);
		
		label_4 = new JLabel("BARCO TOCADO\r\n");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Stencil", Font.PLAIN, 20));
		label_4.setEnabled(false);
		label_4.setBounds(49, 312, 156, 21);
		add(label_4);
		
		panel_9 = new JPanel();
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(7, 303, 30, 30);
		add(panel_9);
		lblNewLabel_1.setVisible(false);
	}

	public void controllerTurn() throws RemoteException {
		String id=controller.getId();
		if(id.equals(controller.getTurnPlayer())) {
			lblNewLabel_1.setVisible(false);
			updateState();
			boardMissil.enabledTrue();
			boardMissil.setEnabled(true);
			this.updateUI();
		} else { 
			lblNewLabel_1.setVisible(true);
			updateState();
			boardMissil.enabledFalse();
			boardMissil.setEnabled(false);
			this.updateUI();
		}
			}
	
	private void updateState() throws RemoteException {
				updateStateMissile();
				updateStateBoat();
	}
	
	private void updateStateBoat() throws RemoteException {
		boarBoat.updateStateBoat();
	}

	private void updateStateMissile() throws RemoteException {
			boardMissil.changedStatus2();
	}

	public void addMissile() throws RemoteException{
		Integer y=10;
		Integer x=10;
		for (Integer i = 0; i < 10; i++) {
			Integer fila = i;
			for (Integer j = 0; j < 10; j++) {
				Integer columna = j;
				boardMissil.getBox()[i][j].setBounds(x, y, 35, 35);
				x = x + 41;
				boardMissil.getBox()[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
								position = new Integer[2];
								position[0] = fila;
								position[1] = columna;
								GameStates resultState = (controller.placeMissile(position));
									if(resultState==GameStates.EXIST)
										JOptionPane.showMessageDialog(PanelMenuGame.this, "ya hay un Misil en esta posicion");
									if(GameStates.errorPositionMissil==resultState)
										JOptionPane.showMessageDialog(PanelMenuGame.this, "error posicion");
									if((GameStates.ERROR==resultState))
										JOptionPane.showMessageDialog(PanelMenuGame.this, "error");
							
							} catch (HeadlessException | RemoteException e1) {
								e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
							}
					}
					});
				
			}
			y = y + 41;
			x = 10;
		}
	}

}


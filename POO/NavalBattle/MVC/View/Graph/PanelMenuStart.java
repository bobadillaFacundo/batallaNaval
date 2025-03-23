package NavalBattle.MVC.View.Graph;

import java.awt.HeadlessException;
import NavalBattle.MVC.Controller.Controller;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class PanelMenuStart extends JPanel {

	private static final long serialVersionUID = -1263557862709618302L;
	private Controller controller;
	private String result = null;
	private JButton btnTop;
	private JButton btnNewButton;
	private JLabel lblBatallaNaval;
	private JLabel lblEsperandoJugador;
	
	public PanelMenuStart(Controller controller)throws RemoteException {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		setSize(747, 411);
		setBounds(0, 0, 937, 602);
		this.controller = controller;
		btnTop = new JButton("Top 5");
		btnTop.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnTop.setBounds(84, 169, 89, 23);
		add(btnTop);
		btnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String  message = PanelMenuStart.this.controller.topFivePlayers();
					if((message.equals(""))||(message==null))
						message="no hay jugadores Cargados";
					JOptionPane.showMessageDialog(PanelMenuStart.this, message );
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});

		btnNewButton = new JButton("Jugar");
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnNewButton.setBounds(84, 122, 89, 23);
		add(btnNewButton);

		lblBatallaNaval = new JLabel("Batalla Naval");
		lblBatallaNaval.setForeground(Color.BLUE);
		lblBatallaNaval.setFont(new Font("Stencil", Font.PLAIN, 35));
		lblBatallaNaval.setBounds(84, 51, 312, 60);
		add(lblBatallaNaval);
		
		lblEsperandoJugador = new JLabel("Esperando Jugador...................");
		lblEsperandoJugador.setForeground(Color.BLUE);
		lblEsperandoJugador.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblEsperandoJugador.setBounds(206, 180, 368, 69);
		add(lblEsperandoJugador);
		lblEsperandoJugador.setVisible(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Ingresar Nombre" + "\n";
				result = "";
				result = JOptionPane.showInputDialog(message, "");
				if (!("".equals(result))) {
					try {
						controller.addPlayer();
						controller.setPlayerName(result);
						btnNewButton.setVisible(false);
						btnTop.setVisible(false);
						lblBatallaNaval.setVisible(false);
						lblEsperandoJugador.setVisible(true);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(PanelMenuStart.this, "Debe ingresar un Nombre");
			}
		});
			
	}
}

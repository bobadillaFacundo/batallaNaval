package NavalBattle.MVC.View.Graph;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;


public class PanelMenuFinalized extends JPanel {
	private JButton btnContinuar;

	public JButton getBtnContinuar() {
		return btnContinuar;
	}

	public PanelMenuFinalized(String g) {
		setBackground(Color.BLACK);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Termino el Juego\r\n");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblNewLabel.setBounds(29, 34, 648, 60);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n"+g+"\r\n");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(29, 176, 620, 60);
		add(lblNewLabel_1);
		
		btnContinuar = new JButton("continuar");
		
		btnContinuar.setForeground(Color.BLUE);
		btnContinuar.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnContinuar.setBounds(29, 305, 121, 30);
		add(btnContinuar);
	}

	private static final long serialVersionUID = 2053895808444446297L;
}

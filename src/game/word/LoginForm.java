//�α��� ȭ���� ����� Ŭ���� ����
package game.word;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel implements ActionListener{
	GameWindow gameWindow;
	JPanel container; //BorderLayout����
	JPanel center; //GridLayout����
	JPanel p_south; //���ʿ� ��ư �� ����
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt;
	GamePanel gamePanel;
	
	
	public LoginForm(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		container = new JPanel();
		center = new JPanel();
		p_south = new JPanel();
		la_id = new JLabel("ID");
		la_pw= new JLabel("password");
		t_id = new JTextField("batman",15);
		t_pw = new JPasswordField("1234",15);
		bt = new JButton("�α���");
		
		container.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(2, 2));
		center.add(la_id);
		center.add(t_id);
		center.add(la_pw);
		center.add(t_pw);
		p_south.add(bt);
		container.add(center);
		container.add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		add(container);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(400, 100));
	}
	
	public void loginCheck(){
		String id= t_id.getText();
		String pw= new String(t_pw.getPassword()); //char[] �迭��ȯ
		if(id.equals("batman")&& pw.equals("1234")){
			JOptionPane.showMessageDialog(this, "�α��� ����");
			gameWindow.setPage(1);
		}else{
			JOptionPane.showMessageDialog(this, "�α��� ������ �ùٸ��� �ʽ��ϴ�.");
		}
	}
	public void actionPerformed(ActionEvent e) {
		loginCheck();
	}
}

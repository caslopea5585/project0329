//�α��� ȭ���� ����� Ŭ���� ����
package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrom extends JPanel{
	JPanel container; //BorderLayout����
	JPanel center; //GridLayout����
	JPanel p_south; //���ʿ� ��ư �� ����
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt;
	
	
	public LoginFrom() {
		container = new JPanel();
		center = new JPanel();
		p_south = new JPanel();
		la_id = new JLabel("ID");
		la_pw= new JLabel("password");
		t_id = new JTextField(15);
		t_pw = new JPasswordField(15);
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
		
		add(container);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(700, 500));
	}
	
}

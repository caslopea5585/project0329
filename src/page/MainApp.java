package page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JButton[] menu = new JButton[3];
	JPanel p_north;
	URL[] url=new URL[3];
	String[] path={"/login.png","/content.png","/etc.png"};
	LoginFrom loginForm; //���������� �����Ѵ�!!
	Content content;
	Etc etc;
	JPanel[] page = new JPanel[3];
	
	JPanel p_center; //���������� ������!
	
	public MainApp() {
		p_north = new JPanel();
		
		
		for(int i=0; i<path.length;i++){
			url[i]=this.getClass().getResource(path[i]);
			menu[i] = new JButton(new ImageIcon(url[i]));
			p_north.add(menu[i]);
			menu[i].addActionListener(this); 		//��ư�� ������ ����
		}		
		
		add(p_north,BorderLayout.NORTH);
		
			p_center = new JPanel();
		/*	loginForm = new LoginFrom();
			content = new Content();
			etc =  new Etc();
			
			page[0]=loginForm;
			page[1]=content;
			page[2]=etc;
		*/
		
		page[0] = new LoginFrom();
		page[1] = new Content();
		page[2] = new Etc();
		
		
		p_center.add(page[0]);		//�α����� ����...(����Ʈ ������)		
		p_center.add(page[1]); 		//����Ʈ ����
		p_center.add(page[2]);				//��Ÿ������ ����
		
		
		add(p_center);
		
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		/*
		if(obj==menu[0]){
			//�α����� O
			//������ X
			//��Ÿ X
			loginForm.setVisible(true);
			content.setVisible(false);
			etc.setVisible(false);
		}else if(obj==menu[1]){
			//�α����� X
			//������ O
			//��Ÿ X
			loginForm.setVisible(false);
			content.setVisible(true);
			etc.setVisible(false);
		}else if(obj==menu[2]){
			//�α����� X
			//������ X
			//��Ÿ O
			loginForm.setVisible(false);
			content.setVisible(false);
			etc.setVisible(true);
		}
		*/
		for(int i=0; i<menu.length;i++){
			
			if(obj==menu[i]){
				page[i].setVisible(true);
			}else{
				page[i].setVisible(false);
			}
			
			
		}
		
		
	}
	public static void main(String[] args) {
		new MainApp();
	}
}

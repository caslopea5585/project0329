package game.word;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//이 윈도우는 크기가 결정되어 있지 않아야 한다.
//왜?? 윈도우 안에 들어오게 될 패널이 그 크기를 결정하게 되므로...
//로그인 기능일때 작게, 게임 본 화면에서는 크게...
public class GameWindow extends JFrame{
	LoginForm loginForm;
	GamePanel gamePanel;
	
	JPanel[] page = new JPanel[2];
	
	public GameWindow() {
		setLayout(new FlowLayout());
		page[0] = new LoginForm(this);
		page[1] =new GamePanel(this);
		
		add(page[0]);
		add(page[1]);
		
		setPage(0);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	//윈도우 안에 어떤 패널이 올지를 결정해주는 메서드를 정의하자
	public void setPage(int index){
		for(int i=0 ; i<page.length;i++){
			if(i==index){
				page[i].setVisible(true);
			}else{
				page[i].setVisible(false);
			}
		}	
		pack(); //내용물의 크기만큼 윈도우 크기를 설정한다!!
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}

}

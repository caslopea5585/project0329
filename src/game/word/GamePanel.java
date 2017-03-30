package game.word;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel implements ItemListener,Runnable,ActionListener{
	GameWindow gameWindow;
	JPanel p_west; //왼쪽 컨드롤 영역
	JPanel p_center; //단어 그래픽 처리 영역
	
	JLabel la_user; //게임 로그인 유저명
	JLabel la_score; //게임점수
	Choice choice; //단어 선택 드랍박스
	JTextField t_input; //게임 입력창
	JButton bt_start,bt_pause,bt_stop; //게임시작
	
	FileInputStream fis;
	InputStreamReader reader; //파일을 기반으로한 문자스트림.
	BufferedReader buffr; //문자기반의 버퍼스트림.
	String res ="C:/java_workspace2/project0329/res/";
	
	//조사한 단어를 담아놓자 게임에 써먹으려고
	ArrayList<String> wordList = new ArrayList<String>();
	Thread thread; //단어게임을 진행할 쓰레드...
	boolean flag = true;
	boolean isDown=true;
	ArrayList<Word> words = new ArrayList<Word>();
	
	
	
	
	public GamePanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		p_west = new JPanel();

		//이 영역은 지금부터 그림을 그릴 영역!!
		p_center= new JPanel(){
			public void paintComponent(Graphics g) {
				//기존 그림 지우기
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 750, 700);
				
				g.setColor(Color.BLUE);
				//g.drawString("고등어", 200, y);
				//모든 워드들에 대한 render();
				for(int i =0 ; i<words.size();i++){
						words.get(i).render(g);
				}
			}
		};		
		la_user= new JLabel("민진호 님");
		la_score=new JLabel("0");
		choice = new Choice();
		t_input= new JTextField(10);
		bt_start=new JButton("Start");
		bt_pause=new JButton("Pause");
		bt_stop = new JButton("Stop");
		
		
		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.yellow);
		
		choice.add("▼ 단어집 선택");
		choice.setPreferredSize(new Dimension(135, 40));
		choice.addItemListener(this);
		
		//버튼과 리스너 연결
		bt_start.addActionListener(this);
		bt_pause.addActionListener(this);
		bt_stop.addActionListener(this);
		
		//텍스트필드와 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
				
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						//화면에 존재하는 words와 입력값 비교하여 맞으면 words에서 객체 삭제...
						String value = t_input.getText();
						
						for(int i=0; i<words.size();i++){
							if(words.get(i).name.equals(value)){
								words.remove(i);
								t_input.setText("");
							}	
							
						}
						
					}		
				}
		});
		
		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(bt_stop);
		p_west.add(la_score);
		
		setLayout(new BorderLayout());
		add(p_west,BorderLayout.WEST);
		add(p_center);
		
		
		
		//setBackground(Color.PINK);
		setPreferredSize(new Dimension(750, 700));
		setVisible(false); //최초에 등장안함.
		getCategory();
		p_center.repaint();
		
	}
	//초이스 컴포넌트에 채워질 파일명 조사하기
	public void getCategory(){

		File f = new File("C:/java_workspace2/project0329/res/");
		File[] list = f.listFiles();
		
		for(int i=0;i<list.length;i++){
			if(list[i].isFile()){			
				String name = list[i].getName();
				String[] arr = name.split("\\.");
				if(arr[1].equals("txt")){
					choice.add(name);
				}			
			}
			
		}
		
	}
	
	//단어 읽어오기
	public void getWord(){
		int index = choice.getSelectedIndex();
		String name= choice.getSelectedItem();
		if(index!=0){
			System.out.println(res+name);
			try {
				fis = new FileInputStream(res+name);
				reader = new InputStreamReader(fis,"utf-8");
				//스트림을 버퍼 처리 수준까지 올림
				buffr = new BufferedReader(reader);
				String data;
				//기존 wordList를 비우자..
				wordList.removeAll(wordList);
				
				
				while(true){
					data = buffr.readLine();
					if(data==null)break;
					wordList.add(data);
					System.out.println(data);
				}
				
				System.out.println("현재까지 wordLIst는  = "+ wordList.size());
				//준비된 단어를 화면에 보여주기
				createWord();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(buffr!=null){
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(reader!=null){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//단어 생성하기..
	public void createWord(){
		for(int i=0; i<wordList.size();i++){
			String name = wordList.get(i);
			Word word = new Word(name,i*(75)+10,100);
			words.add(word);	//워드 객체 명단 만들기.			
		}
	}
	
	//게임시작
	public void startGame(){
		
		if(thread==null){
			flag=true;
			thread = new Thread(this);
			thread.start();
		}
		
	}
	//게임중지 or 재시작
	public void pauseGame(){
		isDown = !isDown;
	}
	//게임종료
	/*--결국 처음으로 돌아가자!!!!!!!!!!!!
	 * 1. wordList() 비우기
	 * 2. words(Word의 인스턴스들이 들어있는 ) 비우기
	 * 3. choice 초기화 (index =0);
	 * 4. while문의 flag를 false로...
	 * 5. thread를 null로 다시 초기화
	 * */
	public void stopGame(){
		wordList.removeAll(wordList);
		words.removeAll(words);
		choice.select(0); //첫번째 요소 강제 선택
		flag = false; //while문 중지목적.
		thread=null;
		
	}
	
	//단어 내려오는 효과
	/*
	public void down(){
		//모든 단어들의 1.y값 증가시키고 , 
		//2. p_center패널로 하여금 그림을 다시그리게 해야한다.
		
		//y+=20;
		//p_center.repaint();
		
		System.out.println("down()");
	}
	*/
	public void itemStateChanged(ItemEvent e) {
		getWord();
	}
	public void actionPerformed(ActionEvent e) {		
		Object obj =e.getSource();
		
		if(obj == bt_start){
			startGame();
		}else if(obj == bt_pause){
			pauseGame();
		}else if(obj==bt_stop){
			stopGame();
		}
	}
	public void run() {
		while(flag){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//down();
			
			if(isDown){
				//모든 단어들에 대해서 tick()
				for(int i=0; i<words.size();i++){
					words.get(i).tick();
				}
				//모든 단어들에 대해서 repaint()
				p_center.repaint();
			}
		}
	}
	
}

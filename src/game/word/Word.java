/*
 * 게임에 등장할 대상 단어가 각각 y축의 값을 따로따로 갖고 대량으로 만들어져야 하므로
 * 결국! 재사용성을 위한 코드집합인 클래스로 가자!!!!
 * 
 * */
package game.word;

import java.awt.Graphics;

public class Word {
	int x;
	int y;
	String name; // 이 객체가 담게 될 단어.
	int velX; 
	int velY; //단어가 움직일 속도
	
	//이 단어가 태어날떄 갖추어야할 초기화 값. 
	public Word(String name, int x, int y) {
		this.name = name;
		this.x=x;
		this.y=y;
	}
	//이 객체에 반영될 데이터 변화 코드
	public void tick(){
		y+=5;
	}
	//그 반영된 데이터를 이용하여 화면에 그리기.
	public void render(Graphics g){
		g.drawString(name,x,y);
	}
}

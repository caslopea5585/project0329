/*
 * ���ӿ� ������ ��� �ܾ ���� y���� ���� ���ε��� ���� �뷮���� ��������� �ϹǷ�
 * �ᱹ! ���뼺�� ���� �ڵ������� Ŭ������ ����!!!!
 * 
 * */
package game.word;

import java.awt.Graphics;

public class Word {
	int x;
	int y;
	String name; // �� ��ü�� ��� �� �ܾ�.
	int velX; 
	int velY; //�ܾ ������ �ӵ�
	
	//�� �ܾ �¾�� ���߾���� �ʱ�ȭ ��. 
	public Word(String name, int x, int y) {
		this.name = name;
		this.x=x;
		this.y=y;
	}
	//�� ��ü�� �ݿ��� ������ ��ȭ �ڵ�
	public void tick(){
		y+=5;
	}
	//�� �ݿ��� �����͸� �̿��Ͽ� ȭ�鿡 �׸���.
	public void render(Graphics g){
		g.drawString(name,x,y);
	}
}

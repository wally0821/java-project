package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameWin extends JFrame {
	
	int width = 2*GameUtil.OFFSET + GameUtil.MAP_W*GameUtil.SQUARE_LENGTH;
	int height = 4 *GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH;
	
	Image offScreenImage = null;
	
	
	MapBottom mapBottom= new MapBottom();		
	MapTop mapTop = new MapTop();
	
	void launch() {
		this.setVisible(true); //建立視窗
		this.setSize(width, height); //視窗大小
		this.setLocationRelativeTo(null); //視窗位置
		this.setTitle("踩地雷");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //關閉視窗
		
		//滑鼠
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getButton()==1) {
					GameUtil.MOUSE_X=e.getX();
					GameUtil.MOUSE_Y=e.getY();
					GameUtil.LEFT = true;  //左鍵
				}
				if (e.getButton()==3) {
					GameUtil.MOUSE_X=e.getX();
					GameUtil.MOUSE_Y=e.getY();
					GameUtil.RIGHT = true;  //右鍵				
				}
			}
			
			
		});
			
		
		
		while (true) {
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		offScreenImage = this.createImage(width,height);
		Graphics gImage = offScreenImage.getGraphics();
		gImage.setColor(Color.gray);
		gImage.fillRect(0, 0, width, height);
		mapBottom.paintSelf(gImage);
		mapTop.paintSelf(gImage);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	  
	public static void main(String[] args) {
		GameWin gameWin =  new GameWin();
		gameWin.launch();
				
	}
}

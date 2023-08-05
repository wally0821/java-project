package game;

import java.awt.*;

public class GameUtil {
	
	
	static int  RAY_MAX=15; //地雷數
	static int MAP_W=11;  //寬
	static int MAP_H=11;  //高
	static int OFFSET=45; //間距
	static int SQUARE_LENGTH=50;  // 邊長
	
	//滑鼠座標
	static int MOUSE_X;
	static int MOUSE_Y;
	//滑鼠狀態
	static boolean LEFT=false;
	static boolean RIGHT=false;
	
	//遊戲狀態
	static int state=0;
	
	//-1是地雷
	static int [][] DATA_BOTTOM = new int [MAP_W+2][MAP_H+2];
	//0是無旗子 1是插旗子 2是差錯旗
	static int [][] DATA_TOP = new int [MAP_W+2][MAP_H+2];

	//載入圖片
	static Image lei = Toolkit.getDefaultToolkit().getImage("imgs/lei.png");
	static Image top = Toolkit.getDefaultToolkit().getImage("imgs/top.gif");
	static Image flag = Toolkit.getDefaultToolkit().getImage("imgs/flag.gif");
	static Image noflag = Toolkit.getDefaultToolkit().getImage("imgs/noflag.png");
	static Image face = Toolkit.getDefaultToolkit().getImage("imgs/face.png");
	static Image over = Toolkit.getDefaultToolkit().getImage("imgs/over.png");
	static Image win = Toolkit.getDefaultToolkit().getImage("imgs/win.png");

	static Image[] images = new Image[9];
	static {
		for (int i=1;i<8;i++) {
			images[i] = Toolkit.getDefaultToolkit().getImage("imgs/num/"+i+".png");
		}
	}
	
	
	
}

package game;

import java.awt.Color;
import java.awt.Graphics;

public class MapBottom {
	
	BottomRay bottomRay = new  BottomRay();
	BottomNum bottomNum = new BottomNum();
	

	
	void paintSelf(Graphics g) {
		g.setColor(Color.black);
//		for (int i=0;i<500;i=i+50) {
//			g.setColor(Color.black);
//			g.drawLine(0, i, 500, i);
//			g.drawLine(i, 0, i, 500);
//			
//		}
		
		//畫直線
		for (int i=0; i <= GameUtil.MAP_W; i++) {
			g.drawLine(GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH, 
					3*GameUtil.OFFSET,
					GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
					3*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH);
		}
		//畫橫線
		for (int i=0; i <= GameUtil.MAP_H; i++) {
			g.drawLine(GameUtil.OFFSET , 
					3*GameUtil.OFFSET+ i * GameUtil.SQUARE_LENGTH,
					GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH,
					3*GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH);
		}
		
		for (int i = 1; i<=GameUtil.MAP_W;i++) {
			for (int j = 1; j<=GameUtil.MAP_H;j++) {
				
				//放地雷
				if(GameUtil.DATA_BOTTOM[i][j] == -1) {
					g.drawImage(GameUtil.lei, 
							GameUtil.OFFSET+(i-1)*GameUtil.SQUARE_LENGTH+1, //x
							GameUtil.OFFSET*3+(j-1)*GameUtil.SQUARE_LENGTH+1, //y
							GameUtil.SQUARE_LENGTH-2, //width
							GameUtil.SQUARE_LENGTH-2, //height
							null);
				}
				//放數字
				if(GameUtil.DATA_BOTTOM[i][j] >=0) {
					g.drawImage(GameUtil.images[GameUtil.DATA_BOTTOM[i][j]], 
							GameUtil.OFFSET+(i-1)*GameUtil.SQUARE_LENGTH+1, //x
							GameUtil.OFFSET*3+(j-1)*GameUtil.SQUARE_LENGTH+1, //y
							GameUtil.SQUARE_LENGTH-2, //width
							GameUtil.SQUARE_LENGTH-2, //height
							null);
				}				
			}
		}
		
		switch(GameUtil.state) {
		case 0:
			g.drawImage(GameUtil.face,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
					GameUtil.OFFSET,null);
			break;
		case 1:
			g.drawImage(GameUtil.win,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
					GameUtil.OFFSET,null);
			break;
		case 2:
			g.drawImage(GameUtil.over,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
					GameUtil.OFFSET,null);
			break;
			
			default:
		}
		
	}
}

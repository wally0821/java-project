package game;

import java.awt.Color;
import java.awt.Graphics;

public class MapTop {
	//格子位置
	int temp_x;
	int temp_y;
	
	void logic() {
		temp_x=0;
		temp_y=0;
				
		if (GameUtil.MOUSE_X>GameUtil.OFFSET&&GameUtil.MOUSE_Y>3*GameUtil.OFFSET) {
		temp_x=(GameUtil.MOUSE_X - GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH+1;
		temp_y=(GameUtil.MOUSE_Y - GameUtil.OFFSET*3)/GameUtil.SQUARE_LENGTH+1;
		}
		if(temp_x>=1 && temp_x<=GameUtil.MAP_W
				&&temp_y>=1&&temp_y<=GameUtil.MAP_H) {
		//覆蓋格子
		if (GameUtil.LEFT) {
			if(GameUtil.DATA_TOP[temp_x][temp_y]==0) {
				GameUtil.DATA_TOP[temp_x][temp_y]=-1;
			}
			spaceOpen(temp_x,temp_y);
			GameUtil.LEFT=false;

		}
		if (GameUtil.RIGHT) {
			//插旗子
			if(GameUtil.DATA_TOP[temp_x][temp_y]==0) {
				GameUtil.DATA_TOP[temp_x][temp_y]=1;
			}
			//拿掉旗子
			else if (GameUtil.DATA_TOP[temp_x][temp_y]==1) {
				GameUtil.DATA_TOP[temp_x][temp_y]=0;
			}			
			else if (GameUtil.DATA_TOP[temp_x][temp_y]==-1) {
				numOpen(temp_x,temp_y);
			}
			GameUtil.RIGHT=false;			
		}
		}
		
		boom();
		victory();
	}
	
	void numOpen(int x,int y) {
		//記錄旗子數量
		int count=0;
		if(GameUtil.DATA_BOTTOM[x][y]>0) {
			for (int i =x-1; i<=x+1;i++) {
				for (int j=y-1;j <=y+1 ; j++) {
					if(GameUtil.DATA_TOP[i][j]==1) {
						count++;
						
					}
				}
			}
			if (count==GameUtil.DATA_BOTTOM[x][y]) {
				for (int i =x-1; i<=x+1;i++) {
					for (int j=y-1;j <=y+1 ; j++) {
						if(GameUtil.DATA_TOP[i][j]!=1) {
							GameUtil.DATA_TOP[i][j]=-1;
						}
						if(i>=1 && j>=1 && i< GameUtil.MAP_W &&j<=GameUtil.MAP_H) {
							spaceOpen(i,j);

						}
					}
				}
			}
		}
	}
	//失敗了！！
	boolean boom() {
		for (int i =1 ; i<=GameUtil.MAP_W;i++) {
			for (int j=1 ;j<=GameUtil.MAP_H;j++) {
				if(GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j]==-1) {
					GameUtil.state=2;
					seeBoom();
					return true;
				}
			}
		}
		return false;
	}
	//失敗顯示
	void seeBoom() {
		for (int i =1 ; i<=GameUtil.MAP_W;i++) {
			for (int j=1 ;j<=GameUtil.MAP_H;j++) {
				if(GameUtil.DATA_BOTTOM[i][j] ==-1  && GameUtil.DATA_TOP[i][j]!=1) {
					GameUtil.DATA_TOP[i][j]=-1;
					}
				if(GameUtil.DATA_BOTTOM[i][j] !=-1  && GameUtil.DATA_TOP[i][j]==1) {
					GameUtil.DATA_TOP[i][j]=2;
					}				
			}
		}			
	}
	//判斷成功
	boolean victory() {
		//統計為打開格子數
		int count=0;
		for (int i =1 ; i<=GameUtil.MAP_W;i++) {
			for (int j=1 ;j<=GameUtil.MAP_H;j++) {	
			if (GameUtil.DATA_TOP[i][j]!=-1) {
				count++;
				
			}
			}
		}
		if (count==GameUtil.RAY_MAX) {
			GameUtil.state=1;
			for (int i =1 ; i<=GameUtil.MAP_W;i++) {
				for (int j=1 ;j<=GameUtil.MAP_H;j++) {
				if (GameUtil.DATA_TOP[i][j]==0) {
					GameUtil.DATA_TOP[i][j]=1;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	//打開空格
	void spaceOpen(int x , int y) {
		if(GameUtil.DATA_BOTTOM[x][y]==0) {
			for (int i =x-1; i<=x+1;i++) {
				for (int j=y-1;j <=y+1 ; j++) {
					if(GameUtil.DATA_TOP[i][j]!=-1) {
						GameUtil.DATA_TOP[i][j]=-1;
						//必須讚地雷區域裡面
						if(i>=1 && j>=1 && i< GameUtil.MAP_W &&j<=GameUtil.MAP_H) {
							spaceOpen(i,j);

						}

					}
					
				}
				
			}
		}
		
	}
	
	//繪製方法
	void paintSelf(Graphics g) {
		logic();
		for (int i = 1; i<=GameUtil.MAP_W;i++) {
			for (int j = 1; j<=GameUtil.MAP_H;j++) {
				
				//覆蓋
				if(GameUtil.DATA_TOP[i][j] == 0) {
					g.drawImage(GameUtil.top, 
							GameUtil.OFFSET+(i-1)*GameUtil.SQUARE_LENGTH+1, //x
							GameUtil.OFFSET*3+(j-1)*GameUtil.SQUARE_LENGTH+1, //y
							GameUtil.SQUARE_LENGTH-2, //width
							GameUtil.SQUARE_LENGTH-2, //height
							null);
				}
				//插旗
				if(GameUtil.DATA_TOP[i][j] == 1) {
					g.drawImage(GameUtil.flag, 
							GameUtil.OFFSET+(i-1)*GameUtil.SQUARE_LENGTH+1, //x
							GameUtil.OFFSET*3+(j-1)*GameUtil.SQUARE_LENGTH+1, //y
							GameUtil.SQUARE_LENGTH-2, //width
							GameUtil.SQUARE_LENGTH-2, //height
							null);
				}
				//差錯旗
				if(GameUtil.DATA_TOP[i][j] == 2) {
					g.drawImage(GameUtil.noflag, 
							GameUtil.OFFSET+(i-1)*GameUtil.SQUARE_LENGTH+1, //x
							GameUtil.OFFSET*3+(j-1)*GameUtil.SQUARE_LENGTH+1, //y
							GameUtil.SQUARE_LENGTH-2, //width
							GameUtil.SQUARE_LENGTH-2, //height
							null);
				}
			}
		}
			
	}
}

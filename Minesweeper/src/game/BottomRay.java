package game;

public class BottomRay {

	int [] rays = new int [GameUtil.RAY_MAX*2]; //放置座標
	int x,y; // 地雷座標
	boolean isPlace = true; // 是否放地雷
	
	
	{
		for (int i=0; i<GameUtil.RAY_MAX*2; i=i+2) {
			x=(int) (Math.random()*GameUtil.MAP_W+1);  //1-12
			y=(int) (Math.random()*GameUtil.MAP_H+1);  //1-12
			
			rays[i]=x;
			rays[i+1]=y;
			//判斷地雷是否存在
			for (int j=0; j<i; j=j+2) {
				if (x==rays[j] && y==rays[j+1]) {
					i=i-2;
					isPlace = false;
					break;
				}
				//放入地雷
				if (isPlace) {
					rays[i]=x;
					rays[i+1]=y;
				}
				isPlace = true;
			}
			
		}
		for (int i=0; i<GameUtil.RAY_MAX*2; i=i+2) {
			GameUtil.DATA_BOTTOM[rays[i]][rays[i+1]]= -1; //-1代表地雷
		}
		
	}
	
	
	
	
}

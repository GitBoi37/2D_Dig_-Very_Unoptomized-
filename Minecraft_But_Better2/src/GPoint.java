 
import java.awt.Color;
public class GPoint {
	int x;
	int y;
	Color color;
	public GPoint() {
		x = 0;
		y = 0;
		color = Color.WHITE;
	}
	public GPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public GPoint(double x, double y) {
		this.x = (int)x;
		this.y = (int)y;
	}
	public GPoint(int x, int y, Color e) {
		this.x = x;
		this.y = y;
		this.color = e;
	}
	public GPoint(double x, double y, Color e) {
		this.x = (int)x;
		this.y = (int)y;
		this.color = e;
	}
	public void randomize(int startX, int endX, int startY, int endY) {
		x = (int)(Math.random() *(endX - startX)) + startX;
		y = (int)(Math.random() *(endY - startY)) + startY;
		while(y > endY) {
			y-=10;
		}
	}
	public void rectify() {
		x = x/10*10;
		y = y/10*10;
		if(x/10%10 % 2 != 0) {
			x+= 10;
		}
		if(y/10%10 % 2 != 0) {
			y+= 10;
		}
	}
}

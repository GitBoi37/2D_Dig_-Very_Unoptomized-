import java.awt.Rectangle;
public class MyRectangle extends Rectangle{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GPoint getMaxPos(){
        GPoint p = new GPoint(x + height, y + height);
        return p;
    }
    public MyRectangle(int a, int b, int c, int d){
        x = a;
        y = b;
        width = c;
        height = d;
    }
    public void setPos(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
}

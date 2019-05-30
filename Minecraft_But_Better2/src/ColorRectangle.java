import java.awt.Rectangle;
import java.awt.Color;
public class ColorRectangle extends Rectangle{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color color;
    public GPoint getMaxPos(){
    	GPoint p = new GPoint(x + height, y + height);
        return p;
    }
    public ColorRectangle(int a, int b, int c, int d, Color in, int transparency){
        x = a;
        y = b;
        width = c;
        height = d;
        color = new Color(in.getRed(), in.getGreen(), in.getBlue(), transparency);
    }
}

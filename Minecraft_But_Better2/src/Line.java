import java.awt.Color;
public class Line{
    private int x1, x2, y1, y2;
    public Color color;
    public Line(int a, int b, int c, int d, Color ee){
        x1 = a; 
        x2 = b; 
        y1 = c; 
        y2 = d;
        color = ee;
    }
    public int getX1(){
        return x1;
    }
    public int getX2(){
        return x2;
    }
    public int getY1(){
        return y1;
    }
    public int getY2(){
        return y2;
    }
}

import java.awt.Color;
import java.awt.Point;
public class Sprite{
    public GPoint[][] spritePointArr;
    public String id = "";
    private int pixelSize = 1;
    int x = 0;
    int y = 0;
    Point lowerRightCorner;
    Point upperRightCorner;
    Point lowerLeftCorner;
    private MyRectangle bounds;
    public Sprite(int rows, int cols){
        spritePointArr = new GPoint[rows][cols];
        for(int i = 0; i < spritePointArr.length; i++){
            for(int c = 0; c < spritePointArr[i].length;c++){
                spritePointArr[i][c] = new GPoint(c, i, Color.WHITE);
            }
        }
        bounds = new MyRectangle(x,y,cols*pixelSize,rows*pixelSize);
        lowerRightCorner = new Point(cols + x, rows + y);
        lowerLeftCorner = new Point(0, rows + y);
        upperRightCorner = new Point(cols + x, 0);
    }
    public Sprite(int rows, int cols, int x1, int y1){
        spritePointArr = new GPoint[rows][cols];
        this.x = x1;
        this.y = y1;
        for(int i = 0; i < spritePointArr.length; i++){
            for(int c = 0; c < spritePointArr[i].length;c++){
                spritePointArr[i][c] = new GPoint(c + x, i + y, Color.WHITE);
            }
        }
        bounds = new MyRectangle(x1, y1, cols*pixelSize, rows*pixelSize);
        lowerRightCorner = new Point(cols + x1, rows + y1);
        lowerLeftCorner = new Point(0, rows + y1);
        upperRightCorner = new Point(cols + x1, 0);
    }
    //preconditon: valid arr
    public Sprite(GPoint[][] arrIn){
        spritePointArr = new GPoint[arrIn.length][arrIn[0].length];
        for(int i = 0; i < spritePointArr.length; i++){
            for(int c = 0; c < spritePointArr[i].length; c++){
                spritePointArr[i][c] = arrIn[i][c];
            }
        }
        int x1 = arrIn[0][0].x;
        int x2 = arrIn[arrIn.length -1][arrIn[0].length -1].x;
        int y1 = arrIn[0][0].y;
        int y2 = arrIn[arrIn.length - 1][arrIn[0].length - 1].y;
        bounds = new MyRectangle(x1, y1, x2-x1, y2-y1);
        lowerRightCorner = new Point(arrIn[0].length + arrIn[0][0].x,  arrIn.length + arrIn[0][0].y); //x + length, y + length
        lowerLeftCorner = new Point(arrIn[0][0].x, arrIn.length + arrIn[0][0].y);//x, y + length
        upperRightCorner = new Point(arrIn[0].length + arrIn[0][0].x, arrIn[0][0].y);//x + length, y
    }
    public void modifySprite(int row, int col, Color in){
        spritePointArr[row][col].color = in;
    }
    public void setSize(int i){
        pixelSize = i;
    }
    public void move(int dx, int dy){
    	/*
        for(int i = 0; i < spritePointArr.length; i++){
            for(int c = 0; c < spritePointArr[i].length; c++){
                spritePointArr[i][c].x += dx;
                spritePointArr[i][c].y += dy;
            }
        }*/
    	int boundRight = Math.abs(upperRightCorner.x - x);
    	int boundDown = Math.abs(lowerLeftCorner.y -y);
        x += dx;
        y += dy;
        lowerRightCorner = new Point(x + boundRight, y + boundDown);
        lowerLeftCorner = new Point(x, y + boundDown);
        upperRightCorner = new Point(x + boundRight, y);
        bounds = new MyRectangle(x, y, boundRight, boundDown);
    }
    public void setPos(int x, int y){
    	int boundRight = Math.abs(upperRightCorner.x - this.x);
    	int boundDown = Math.abs(lowerLeftCorner.y -this.y);
        this.x = x;
        this.y = y;
        lowerRightCorner = new Point(x + boundRight, y + boundDown );
        lowerLeftCorner = new Point(x, y + boundDown );
        upperRightCorner = new Point(x + boundRight , y);
        bounds = new MyRectangle(x, y, boundRight, boundDown);
    }
    //for drawing in points
    public Line[][] returnLinesForSprite(){
        Line[][] lineArr = new Line[spritePointArr.length][spritePointArr[0].length];
        for(int i = 0; i < spritePointArr.length; i++){
            for(int c = 0; c < spritePointArr[i].length;c++){
                lineArr[i][c] = new Line(spritePointArr[i][c].x + this.x,spritePointArr[i][c].x + this.x, spritePointArr[i][c].y + this.y,spritePointArr[i][c].y + this.y, spritePointArr[i][c].color);
            }
        }
        return lineArr;
    }
    public MyRectangle getBounds(){
        return bounds;
    }
    public int returnWidth() {
    	return Math.abs(this.x - this.upperRightCorner.x);
    }
    public int returnHeight() {
    	return Math.abs(this.y - this.lowerLeftCorner.y);
    }
}

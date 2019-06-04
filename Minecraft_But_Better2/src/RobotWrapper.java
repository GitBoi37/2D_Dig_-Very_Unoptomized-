import java.awt.Robot;
import java.awt.*;
public class RobotWrapper{
    private final Robot robot;
    public boolean isEnabled = true;
    public RobotWrapper(){
        try{
            robot = new Robot();
        }
        catch(java.awt.AWTException e){
            throw new RuntimeException(e);
        }
    }
    public void mouseMove(int x, int y){
        robot.mouseMove(x, y);
    }
    public int[] checkPos(int centerX, int centerY, int range){
    	int dx = 0;
        int dy = 0;
        int[] dxdy = new int[2];
        if(isEnabled) {
	        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
	        
	        if(mouseX > centerX + range && mouseY > centerY + range){
	            robot.mouseMove(centerX + range, centerY + range);
	        }
	        if(mouseX < centerX - range && mouseY < centerY - range){
	            robot.mouseMove(centerX - range, centerY - range);
	        }
	        if(mouseX < centerX - range){
	            robot.mouseMove(centerX - range, mouseY);
	        }
	        if(mouseY < centerY - range){
	            robot.mouseMove(mouseX, centerY - range);
	        }
	        if(mouseX > centerX + range){
	            robot.mouseMove(centerX + range, mouseY);
	        }
	        if(mouseY > centerY + range){
	            robot.mouseMove(mouseX, centerY + range);
	        }
	        dx = mouseX - centerX;
	        dy = mouseY - centerY;
    	}
        dxdy[0] = dx;
        dxdy[1] = dy;
        return dxdy;
    }
}

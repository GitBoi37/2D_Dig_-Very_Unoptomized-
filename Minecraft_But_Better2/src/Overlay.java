import java.awt.Color;
public class Overlay{ 
  ColorRectangle[][] fieldOfOverlay;
  //precondition
  public Overlay(int width, int height, int indvWidth, int indvHeight){
      if(width % indvWidth  == 0 && height % indvHeight == 0){
          fieldOfOverlay = new ColorRectangle[height/indvHeight][width/indvWidth];
          for(int r = 0; r < height/indvHeight; r++){
              for(int i = 0; i < width/indvWidth; i++){
                  fieldOfOverlay[r][i] = new ColorRectangle(i*indvWidth, r*indvHeight, indvWidth, indvHeight, Color.WHITE, 0);
              }
          }
      }
  }
}

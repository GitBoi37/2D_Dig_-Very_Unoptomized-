import java.awt.Color;

public class GrassDirtBlock {
	public Sprite block;
	public GrassDirtBlock(int x, int y) {
		GPoint[][] p = new GPoint[25][25];
    	for(int i = 0; i < p.length; i++) {
    		for(int c = 0; c < p[i].length; c++) {
    			p[i][c] = new GPoint(c,i, new Color(94, 58, 13));
    		}
    	}
    	for(int i = 0; i < 5; i++) {
    		for(int c = 0; c < p[i].length; c++) {
    			p[i][c] = new GPoint(c,i, new Color(20, 147, 24));
    		}
    	}
    	int numOfRandomGreyPerLine = 3;
    	for(int i = 5; i < p.length; i++) {
    		int[] positionsOfGrey = new int[numOfRandomGreyPerLine];
    		for(int a = 0; a < positionsOfGrey.length; a++) {
    			positionsOfGrey[a] = (int)(Math.random()*p[i].length);
    			//check if already used;
    			for(int b = 0; b < a; b++) {
    				if(b == a) {
    					
    				}
    				else {
    					if(positionsOfGrey[a] == positionsOfGrey[b]) {
    						a = a - 1;
    						break;
    					}
    				}
    			}
    		}
    		for(int c = 0; c < positionsOfGrey.length; c++) {
    			p[i][positionsOfGrey[c]] = new GPoint(positionsOfGrey[c], i, new Color(124, 122, 103));
    		}
    	}
    	block = new Sprite(p);
    	block.setPos(x, y);
    	block.id = "G";
	}
}

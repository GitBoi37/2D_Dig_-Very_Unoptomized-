import java.awt.Color;
import java.util.ArrayList;
public class Sprites{
    public Sprite player = new Sprite(30, 50);
    public Sprite floor;
    public Sprite grassDirtBlock;
    public Sprite dirtDirtBlock;
    public ArrayList<Sprite> allSprites;
    public void MakePlayer(){
        GPoint[][] p = new GPoint[50][50];
        for(int i = 0; i < 50; i++){
            for(int e = 0; e < 50; e++){
                p[i][e] = new GPoint(e,i, Color.LIGHT_GRAY);
            }
        }
        for(int i =8; i < 13; i++){
            for(int c = 8; c < 13; c++){
                p[i][c] = new GPoint(c,i, Color.BLUE);
            }
        }
        for(int i =8; i < 13; i++){
            for(int c = 28; c < 33; c++){
                p[i][c] = new GPoint(c,i, Color.BLUE);
            }
        }
        for(int i = 35; i < 50; i++){
            for(int c = 16; c < 35; c++){
                p[i][c] = new GPoint(c,i, Color.BLUE);
            }
        }
        player = new Sprite(p);
        player.setPos(100, 0);
    }
    public void MakeStone() {
    	GPoint[][] p = new GPoint[25][25];
    	for(int i = 0; i < p.length; i++) {
    		for(int c = 0; c < p[i].length; c++) {
    			p[i][c] = new GPoint(c,i, new Color(165, 165, 165));
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
    			p[i][positionsOfGrey[c]] = new GPoint(positionsOfGrey[c], i, new Color(114, 114, 114));
    		}
    	}
    }
    public void MakeFloor(int sW, int sH) {
    	GPoint[][] p = new GPoint[sH/15][sW];
    	for(int i = 0; i < p.length; i++){
            for(int e = 0; e < p[i].length; e++){
                p[i][e] = new GPoint(e,i, new Color(104, 61, 6));
            }
        }
    	for(int i = 0; i < p.length/10; i++){
            for(int e = 0; e < p[i].length; e++){
                p[i][e] = new GPoint(e,i, new Color(76, 45, 6));
            }
        }
    	floor = new Sprite(p);
    	floor.setPos(0, sH - (sH/15));
    }
    public void MakeBlock(int w, int h) {
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
    	grassDirtBlock = new Sprite(p);
    	grassDirtBlock.setPos(w/2, h - (h/15) - 25);
    }
    public void MakeDDBlock(int w, int h) {
    	GPoint[][] p = new GPoint[25][25];
    	for(int i = 0; i < p.length; i++) {
    		for(int c = 0; c < p[i].length; c++) {
    			p[i][c] = new GPoint(c,i, new Color(94, 58, 13));
    		}
    	}
    	int numOfRandomGreyPerLine = 3;
    	for(int i = 0; i < p.length; i++) {
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
    	dirtDirtBlock = new Sprite(p);
    	dirtDirtBlock.setPos(w/2, h - (h/15) - 50);
    }
    public Sprites(int sW, int sH){
    	MakePlayer();
    	allSprites = new ArrayList<Sprite>();
    	allSprites.add(player);

    }
}

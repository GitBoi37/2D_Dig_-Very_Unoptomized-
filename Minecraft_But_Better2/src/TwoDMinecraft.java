import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
public class TwoDMinecraft extends JPanel implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int worldLength = 500;
	private int initialSkyGap = 100;
	private int screenW = 0;
    private int screenH = 0;
    private int DELAY = 2;
    private int range = 50;
    private int gravity = 5;
    private int gravityTimer = 1;
    private int jumpTimer = 100;
    private int jumpDistance = 75;
    private int leftCount = 0;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private int downCount = 0;
    private int rightCount = 0;
    private int upCount = 0;
    private boolean recentlyJumped = false;
    private boolean Q = false;
    private int t = 0;
    private int gT = 0;
    private RobotWrapper r = new RobotWrapper();
    private boolean debug = true;
    private Sprites sprites;
    private Thread animator;
    private Dimension screenD;
    private Sprite[][] world;
    public TwoDMinecraft(){
        super();
        setBackground(Color.BLUE);
        setFocusable(true);
        screenD = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenD);
        screenW = (int)screenD.getWidth();
        screenH = (int)screenD.getHeight();
        r.mouseMove(screenW/2, screenH/2);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
        Rectangle graphicsConfigurationBounds = new Rectangle();
        GraphicsDevice gDevice = graphicsDevices[0];
        graphicsConfigurationBounds.setRect(gDevice.getDefaultConfiguration().getBounds());
        screenW = (int) (graphicsConfigurationBounds.getWidth() -graphicsConfigurationBounds.x);
        screenH = (int) (graphicsConfigurationBounds.getHeight() - graphicsConfigurationBounds.y);
        sprites = new Sprites(screenW, screenH);
        createWorld();
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "Q");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_E,  0, false), "E");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, false), "Debug");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,  0, false), "Left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "Down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "Right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,  0, false), "Up");
        am.put("Left", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		leftCount++;
        		if(leftCount > 2) {
        			leftCount = 0;
        		}
        		if(!left) {
        			left = true;
        			up = false;
        			down = false;
        			right = false;
        		}
        	}
        });
        am.put("Down", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		downCount++;
        		if(downCount > 2) {
        			downCount = 0;
        		}
        		if(!down) {
        			down = true;
        		}
        		up = false;
        		left = false;
        		right = false;
        	}
        });
        am.put("Right", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		rightCount++;
        		if(rightCount > 2) {
        			rightCount = 0;
        		}
        		if(!right) {
        			right = true;
        		}
        		up = false;
        		down = false;
        		left = false;
        	}
        });
        am.put("Up", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		upCount++;
        		if(upCount > 2) {
        			upCount = 0;
        		}
        		if(!up) {
        			up = true;
        		}
        		left = false;
        		right = false;
        		down = false;
        	}
        });
        am.put("Q", new AbstractAction() { 
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Q = true;
			}
        });
        
        am.put("E", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		Q = false;
        	}
        });
        am.put("Debug", new AbstractAction() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public void actionPerformed(ActionEvent e) {
        		if(debug == true) {
        			debug = false;
        		}
        		else {
        			debug = true;
        		}
        	}
        });
        
    }
    public void createWorld() {
    	world = new Sprite[worldLength][screenW/25];
    	for(int i = 0; i < worldLength; i++) {
    		for(int c = 0; c < screenW/25; c++) {
    			if(i != 0) {
	    			int a =(int)(Math.random()*2);
	    			if(a == 0) {
	    				world[i][c] = (new DirtBlock(c*25, i*25 + initialSkyGap)).block;
	    			}
	    			if(a == 1) {
	    				world[i][c] = (new StoneBlock(c*25, i*25 + initialSkyGap)).block;
	    			}
    			}
    			else {
    				world[i][c] = (new GrassDirtBlock(c*25, i*25 + initialSkyGap)).block;
    			}
    		}
    	}
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(int a = 0; a < sprites.allSprites.size(); a++) {
        	Line[][] toPaint = sprites.allSprites.get(a).returnLinesForSprite();
            for(int b = 0; b < toPaint.length; b++){
                for(int c = 0; c < toPaint[b].length; c++){
                    g2d.setColor(toPaint[b][c].color);
                    g2d.drawLine(toPaint[b][c].getX1(), toPaint[b][c].getY1(), toPaint[b][c].getX2(), toPaint[b][c].getY2());
                }
            }
        }
        for(int a = 0; a < world.length; a++) {
        	for(int b = 0; b < world[a].length; b++) {
        		Line[][] toPaint = world[a][b].returnLinesForSprite();
        		for(int c = 0; c < toPaint.length; c++) {
        			for(int d = 0; d < toPaint[c].length; d++) {
        				g2d.setColor(toPaint[c][d].color);
        				g2d.drawLine(toPaint[c][d].getX1(), toPaint[c][d].getY1(), toPaint[c][d].getX2(), toPaint[c][d].getY2());
        			}
        		}
        	}
        }
        
            
        
        g2d.setColor(Color.WHITE);
        g2d.drawRect(screenW/2 - range, (int) (screenH/2 - range*1.5), range*2, range*2);
        g2d.fillRect(screenW/2 - range/12, (int)screenH/2 - range/2, range/6, range/6);
        if(debug){
            g2d.drawString("Mouse X: " + MouseInfo.getPointerInfo().getLocation().x + "Mouse Y: " + MouseInfo.getPointerInfo().getLocation().y, 200, 200);
            g2d.drawString("Center X: " + screenW/2 + ", Center Y: " + screenH/2, 200, 250);
            g2d.drawString("Player X: " + sprites.player.x + ", Player Y: " + sprites.player.y, 200, 300);
            g2d.drawString("Player X: " + sprites.player.spritePointArr[0][0].x + "Player Y: " + sprites.player.spritePointArr[0][0].y, 200, 350);
            if(Q) {
            	g2d.drawString("Q", 200, 400);
            }
            else {
            	g2d.drawString("E", 200, 400);
            }
        }
    }

    public void runFunction(){
        int[] dxdy = new int[2];
        dxdy[0] = 1; dxdy[1] = 0;
        dxdy = r.checkPos(screenW/2, screenH/2, range);
        boolean passedSprites = true;
    	
    	
        if(passedSprites == true) {
	        if(sprites.player.x < 0 && sprites.player.y < 0) {
	        	sprites.player.setPos(0, 0);
	        }
	        else if(sprites.player.x < 0){
	            sprites.player.setPos(0, sprites.player.y);
	        }
	        else if(sprites.player.y < 0){
	            sprites.player.setPos(sprites.player.x, 0);
	        }
	        else if(sprites.player.x > screenW){
	            sprites.player.setPos(screenW, sprites.player.y);
	        }
	        else if(sprites.player.y + sprites.player.getBounds().height  > screenH){
	            sprites.player.setPos(sprites.player.x, screenH - sprites.player.getBounds().height); 
	        }
	        else{
	        	if(dxdy[1] < 0) {
	        		if(t > jumpTimer) {
	        			t = 0;
	        			if(recentlyJumped == false){
	        				sprites.player.move(0, -jumpDistance);
	        				recentlyJumped = true;
	        			}
	        		}
	        		else {
	        			sprites.player.move(0, 0);
	        			t++;
	        		}
	        	}
	        	if(gT > gravityTimer) {
        			sprites.player.move(dxdy[0]/8, gravity);
        			gT = 0;
        		}
        		else {
        			gT++;
        		}
	        }
	        if(sprites.player.x < 0 && sprites.player.y < 0) {
	        	sprites.player.setPos(0, 0);
	        }
	        else if(sprites.player.x < 0){
	            sprites.player.setPos(0, sprites.player.y);
	        }
	        else if(sprites.player.y < 0){
	            sprites.player.setPos(sprites.player.x, 0);
	        }
	        else if(sprites.player.x > screenW){
	            sprites.player.setPos(screenW, sprites.player.y);
	        }
	        else if(sprites.player.y + sprites.player.getBounds().height  > screenH){
	            sprites.player.setPos(sprites.player.x, screenH - sprites.player.getBounds().height); 
	        }
	        for(int i = 0; i < world.length; i++) {
	        	for(int c = 0; c < world[i].length; c++) {
	        		double w = 0.5 * (sprites.player.returnWidth() + world[i][c].returnWidth());
		        	double h = 0.5 * (sprites.player.returnHeight() + world[i][c].returnHeight());
		        	double centerAX = sprites.player.x + (0.5 * sprites.player.returnWidth());
		        	double centerAY = sprites.player.y + (0.5 * sprites.player.returnHeight());
		        	double centerBX = world[i][c].x + (0.5* world[i][c].returnWidth());
		        	double centerBY = world[i][c].y + (0.5 * world[i][c].returnHeight());
		        	double dx = centerAX - centerBX;
		        	double dy = centerAY - centerBY;
		        	double wy = w * (dy);
		        	double hx = h * (dx);
		            
		            if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
			            passedSprites = false;
		            	if (wy > hx)
		            		//of player
			                if (wy > -hx) {/* collision at the top */
			                	sprites.player.setPos(sprites.player.x, world[i][c].lowerLeftCorner.y);
			                }
			                    
			                else {/* on the left */
			                	sprites.player.setPos(world[i][c].x- sprites.player.returnWidth(), sprites.player.y);
			                }
			                    
			            else
			                if (wy > -hx) {/* on the right */
			                	sprites.player.setPos(world[i][c].upperRightCorner.x, sprites.player.y);
			                }
			                    
			                else { /* at the bottom */
			                	sprites.player.setPos(sprites.player.x, world[i][c].y - sprites.player.returnHeight());
			                	recentlyJumped = false;
			                }
		            }
	        	}
	        }/*
	        for(int i = 1; i < sprites.allSprites.size(); i++) {
	    		/*
	        	if(sprites.player.upperRightCorner.x > sprites.allSprites.get(i).x || sprites.player.x < sprites.allSprites.get(i).upperRightCorner.x);{
	        		passedSprites = false;
	        	}
	        	if(sprites.player.lowerLeftCorner.y > sprites.allSprites.get(i).y || sprites.player.y < sprites.allSprites.get(i).lowerLeftCorner.y) {
	        		passedSprites = false;
	        	}
	        	double w = 0.5 * (sprites.player.returnWidth() + sprites.allSprites.get(i).returnWidth());
	        	double h = 0.5 * (sprites.player.returnHeight() + sprites.allSprites.get(i).returnHeight());
	        	double centerAX = sprites.player.x + (0.5 * sprites.player.returnWidth());
	        	double centerAY = sprites.player.y + (0.5 * sprites.player.returnHeight());
	        	double centerBX = sprites.allSprites.get(i).x + (0.5*sprites.allSprites.get(i).returnWidth());
	        	double centerBY = sprites.allSprites.get(i).y + (0.5 * sprites.allSprites.get(i).returnHeight());
	        	double dx = centerAX - centerBX;
	        	double dy = centerAY - centerBY;
	        	double wy = w * (dy);
	        	double hx = h * (dx);
	            
	            if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
		            passedSprites = false;
	            	if (wy > hx)
	            		//of player
		                if (wy > -hx) {/* collision at the top 
		                	sprites.player.setPos(sprites.player.x, sprites.allSprites.get(i).lowerLeftCorner.y);
		                }
		                    
		                else {/* on the left 
		                	sprites.player.setPos(sprites.allSprites.get(i).x- sprites.player.returnWidth(), sprites.player.y);
		                }
		                    
		            else
		                if (wy > -hx) {/* on the right 
		                	sprites.player.setPos(sprites.allSprites.get(i).upperRightCorner.x, sprites.player.y);
		                }
		                    
		                else { /* at the bottom 
		                	sprites.player.setPos(sprites.player.x, sprites.allSprites.get(i).y - sprites.player.returnHeight());
		                	recentlyJumped = false;
		                }
	            }
	        } use if you add enemies or something later*/
        }
    }
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    public void run(){
        long tBefore, tDiff, sleep;
        tBefore = System.currentTimeMillis();
        while(true){
            repaint();
            runFunction();
            tDiff = System.currentTimeMillis() - tBefore;
            sleep = DELAY - tDiff;
            if(sleep < 0){
                sleep = 2;
            }
            try{
                Thread.sleep(sleep);
            }
            catch(InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
            }
            tBefore = System.currentTimeMillis();
        }
    }
}

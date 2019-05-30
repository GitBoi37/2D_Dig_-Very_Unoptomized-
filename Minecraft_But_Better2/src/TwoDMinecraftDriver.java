import java.awt.EventQueue;
import javax.swing.JFrame;
public class TwoDMinecraftDriver extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TwoDMinecraftDriver(){
        init();
    }
    private void init(){
        add(new TwoDMinecraft());
        setResizable(false);
        pack();
        setTitle("Minecraft... but better.");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(()-> {
            JFrame ex = new TwoDMinecraftDriver();
            ex.setVisible(true);
        });
    }
}

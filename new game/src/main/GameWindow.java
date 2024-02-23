package main;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

class GameWindow{
    public GameWindow (GamePanel gamepanel){
        JFrame jframe = new JFrame("الرجل العناب");
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamepanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);
        jframe.setFocusable(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamepanel.getGame().resetWhenFocusLost();
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {}
        });
    }
}
package UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
public boolean up,down,left,right,shoot,pause;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> up=true;
            case KeyEvent.VK_S -> down=true;
            case KeyEvent.VK_A -> left=true;
            case KeyEvent.VK_D -> right=true;
            case KeyEvent.VK_0 -> shoot=true;
            case KeyEvent.VK_ESCAPE -> pause=!pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> up=false;
            case KeyEvent.VK_S -> down=false;
            case KeyEvent.VK_A -> left=false;
            case KeyEvent.VK_D -> right=false;
            case KeyEvent.VK_0 -> shoot=false;

        }
    }
}

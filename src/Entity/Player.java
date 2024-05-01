package Entity;

import GameGraphics.GamePanel;
import UserInput.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyInput keyInput;

    public Player(GamePanel gp, KeyInput keyInput) {
        this.gp = gp;
        this.keyInput = keyInput;
        x = 100;
        y = 100;
        speed = 8;
        loadPng();
    }
    public void update(){
        if (keyInput.up){
            y -= speed;
        }
        else if (keyInput.down){
            y += speed;
        }
        else if (keyInput.left){
            x -= speed;
        }
        else if (keyInput.right){
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(bufferedImage,x,y,85,80,null);
    }
    public void loadPng(){
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/PlayerModel/player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

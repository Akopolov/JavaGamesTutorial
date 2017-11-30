package DriverGame.BL;

import DriverGame.Framework;
import java.awt.event.KeyEvent;

public class MainMenu extends Framework{

    public MainMenu() {
        super();
    }

    public void mainMenuKeyLogic(KeyEvent event){
        int keyCode = event.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            gameState = GameState.EXIT_GAME;
        }else{
//            Framework.newGame();
        }
    }
}

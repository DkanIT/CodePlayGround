package GUI;

import Cards.Cards;
import static GUI.Menu.currentChoice;
import static GUI.Menu.options;
import static GUI.Menu.select;
import Main.Board;
import Main.Game;
import Main.Game.STATE;
import Main.Sound;
import Main.Hint;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//Doğukan
public class KeyInput extends KeyAdapter {

    public final static float a = 0.005f;
    public static boolean isT = true;
    private boolean[] keyDown = new boolean[4];
    private boolean[] wheelDown = new boolean[4];
    int k = 0;

    public KeyInput() {
        for (int i = 0; i < 4; i++) {
            keyDown[i] = false;
        }
        for (int i = 0; i < 2; i++) {
            wheelDown[i] = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(Game.getGameState()== STATE.Info){
            if(key == KeyEvent.VK_ESCAPE){
                Game.setGameState(Game.STATE.Game);
            }

        }
        else if (Game.getGameState()== STATE.Game) {
            if(key == KeyEvent.VK_ESCAPE){
                Game.setGameState(Game.STATE.Menu);
            }}
        else if (Game.getGameState()== STATE.Credits) {
            if(key == KeyEvent.VK_ESCAPE){
                Game.setGameState(Game.STATE.Menu);
            }}


        if (Game.getGameState() == STATE.Menu) {
            if (key == KeyEvent.VK_ENTER) {
                select();
            }
            if (key == KeyEvent.VK_UP) {
                currentChoice--;
                if (currentChoice == -1) {
                    currentChoice = options.length - 1;
                }
            }
            if (key == KeyEvent.VK_DOWN) {
                currentChoice++;
                if (currentChoice == options.length) {
                    currentChoice = 0;
                }
            }
            if (key == KeyEvent.VK_W) {
                currentChoice--;
                if (currentChoice == -1) {
                    currentChoice = options.length - 1;
                }
            }
            if (key == KeyEvent.VK_S) {
                currentChoice++;
                if (currentChoice == options.length) {
                    currentChoice = 0;
                }
            }
        }
        if (Game.getGameState() == STATE.Game) {
            if(key== KeyEvent.VK_M){
                Sound.stopAll();
            }
            if(key== KeyEvent.VK_P){
                Sound.playGameBg();
            }
            if (key == KeyEvent.VK_S) {
                Game.setVelORG_Y(a);
            }
            if (key == KeyEvent.VK_W) {
                Game.setVelORG_Y(-a);
            }
            if (key == KeyEvent.VK_D) {
                Game.setVelORG_X(a);
            }
            if (key == KeyEvent.VK_T) {
                isT = !isT;
            }
            if (key == KeyEvent.VK_A) {
                Game.setVelORG_X(-a);
            }
            if (key == KeyEvent.VK_SPACE) {
                if (Game.getSourceHint() != null) {
                    Hint sourceHint = Game.getSourceHint();
                    ArrayList visibleRange = sourceHint.getVision().visibleRange(sourceHint.getCoordinate());
                    for (int i = 0; i < visibleRange.size(); i++) {
                        if (Board.getBoard()[sourceHint.getVision().visibleRange(sourceHint.getCoordinate()).get(i)].getVision().getColor() == sourceHint.getVision().getColor()) {
                            k++;
                        }
                    }
                    if (k == 2) {
                        for (int i = 0; i < visibleRange.size(); i++) {
                            if (Board.getBoard()[sourceHint.getVision().visibleRange(sourceHint.getCoordinate()).get(i)].getVision().getColor() == sourceHint.getVision().getColor() &&
                                    Board.getBoard()[sourceHint.getVision().visibleRange(sourceHint.getCoordinate()).get(i)] != sourceHint) {
                                System.out.println("nothing");
                                Cards tmp = Board.getBoard()[sourceHint.getVision().visibleRange(sourceHint.getCoordinate()).get(i)].getVision().getCard();
                                Board.getBoard()[sourceHint.getVision().visibleRange(sourceHint.getCoordinate()).get(i)].getVision().setCard(sourceHint.getVision().getCard());
                                sourceHint.getVision().setCard(tmp);
                                
                            }
                        }

                    }
                    visibleRange = null;
                    sourceHint = null;
                }
                
                k = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Game.setVelORG_Y(0);
        Game.setVelORG_X(0);
    }
}

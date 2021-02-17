package Main;

import GUI.KeyInput;
import Visions.Visions;
import Visions.Visions.VisionType;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

//Berkay -Doğukan- Murat
public class Hint {

    private int poison;
    private static ArrayList<Integer> tmp;
    private static boolean isFromCP = false;
    private boolean isBlocked;
    public static final Color BLUE_BASE_DARK = Color.decode("#6298ef");
    public static final Color BLUE_BASE_LIGHT = Color.decode("#3f6eba");
    public static final Color RED_BASE_LIGHT = Color.decode("#f7948f");
    public static final Color RED_BASE_DARK = Color.decode("#dd615d");
    public static final Color MONEY_LIGHT = Color.gray;
    public static final Color MONEY_DARK = Color.LIGHT_GRAY;
    private final int coordinate;
    public final boolean isLeftSide;
    private float x, y;
    private Visions vision;
    private boolean isVisible4Blue, isVisible4Red;
    private int blockedRounds, poisonedRounds;

    public Hint(int coordinate, Visions vision) {
        this.isBlocked = false;
        this.poison = 0;
        this.blockedRounds = 0;
        this.poisonedRounds = 0;
        this.coordinate = coordinate;
        isLeftSide = this.coordinate % Game.getKS() <= Game.getKS() / 2;
        this.vision = vision;
        this.x = Board.getCoordinates().get(coordinate)[0];
        this.y = Board.getCoordinates().get(coordinate)[1];
        if (!isFromCP) {
            Handler.addObject(this);
        }
        this.vision.setHint(this);
    }
    public void update() {
        this.vision.update();
        int xBounds = 64;
        int yBounds = 484;
        if (Game.getORG_Y() + Game.getVelORG_Y() <= yBounds && Game.getORG_Y() + Game.getVelORG_Y() >= -28) {
            Game.setORG_Y(Game.getORG_Y() + Game.getVelORG_Y());
        }
        if (Game.getORG_X() + Game.getVelORG_X() <= xBounds && Game.getORG_X() + Game.getVelORG_X() >= -448) {
            Game.setORG_X(Game.getORG_X() + Game.getVelORG_X());
        }
    }
    public void render(Graphics g) {
        if ((Board.getBoard()[this.coordinate].isVisible4Red && !Board.isFirst())
                || (Board.getBoard()[this.coordinate].isVisible4Blue && Board.isFirst())) {
            if (this.isVisible4Red && !Board.isFirst() || this.isVisible4Blue && Board.isFirst()) {
                if (GameStatus.blueBase.contains(this.coordinate)) {
                    g.setColor(((this.coordinate % Game.getKS() + this.coordinate / Game.getKS()) % 2) != 0 ? BLUE_BASE_DARK : BLUE_BASE_LIGHT);
                    g.fillRect((int) Board.getCoordinates().get(this.coordinate)[0] - (int) Game.getORG_X(), (int) Board.getCoordinates().get(this.coordinate)[1] - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK());
                } else if (GameStatus.redBase.contains(this.coordinate)) {
                    g.setColor(((this.coordinate % Game.getKS() + this.coordinate / Game.getKS()) % 2) == 0 ? RED_BASE_DARK : RED_BASE_LIGHT);
                    g.fillRect((int) Board.getCoordinates().get(this.coordinate)[0] - (int) Game.getORG_X(), (int) Board.getCoordinates().get(this.coordinate)[1] - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK());
                }
            else {
                    g.setColor(((this.coordinate % Game.getKS() + this.coordinate / Game.getKS()) % 2) == 0 ? Game.getColor1() : Game.getColor2());
                    g.fillRect((int) Board.getCoordinates().get(this.coordinate)[0] - (int) Game.getORG_X(), (int) Board.getCoordinates().get(this.coordinate)[1] - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK());
                }
            }
            if (!Animation.isAnimating && Game.getSourceHint() != null && Game.getSourceHint().vision.getVisiontype() != VisionType.Bos
                    && Game.getSourceHint().vision == this.vision
                    && Game.getSourceHint().vision.getColor() == this.vision.getColor()
                    && (Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Blue
                    || !Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Red)) {
                g.setColor(Color.BLACK);
                for (int i = 0; i < 4; i++) {
                    g.drawRect((int) this.x + i - (int) Game.getORG_X(), (int) this.y + i - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i, Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i);
                }
            } else if (poison > 0) {
                g.setColor(Color.RED);
                for (int i = 0; i < 4; i++) {
                    g.drawRect((int) this.x + i - (int) Game.getORG_X(), (int) this.y + i - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i, Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i);
                }
            } else if (isBlocked) {
                g.setColor(Color.decode("#aff2f3"));
                for (int i = 0; i < 4; i++) {
                    g.drawRect((int) this.x + i - (int) Game.getORG_X(), (int) this.y + i - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i, Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2 * i);
                }
            }
            if (this.vision.getHP() > 0) {
                this.vision.render(g);
            }
        }
        try {
            if (Game.getSourceHint() != null) {
                if (Board.getBoard()[this.coordinate].vision.getVisiontype() == VisionType.Bos
                        && !Animation.isAnimating && !isBlocked && this.vision.getColor() == Main.Board.Renk.Temp
                        && Game.getSourceHint().vision.isLegal(Game.getSourceHint().coordinate, this.coordinate)
                        && ((Game.getSourceHint().vision.getColor() == Main.Board.Renk.Blue && Board.isFirst())
                        || (Game.getSourceHint().vision.getColor() == Main.Board.Renk.Red && !Board.isFirst()))) {

                    g.setColor(Color.decode("#0baa78"));
                    g.fillRect((int) this.x + 15 - (int) Game.getORG_X(), (int) this.y + 15 - (int) Game.getORG_Y(), 10, 10);
                }
                if (Game.getAimHint() != null && !isBlocked
                        && Game.getSourceHint().vision.isLegal(Game.getSourceHint().coordinate, Game.getAimHint().coordinate)
                        && ((Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Blue)
                        || !Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Red)) {

                    Handler.findDirections(Game.getSourceHint().coordinate, Game.getPressedHint() == null ? Game.getAimHint().coordinate : Game.getPressedHint().coordinate);
                    tmp = Board.getBoard()[Game.getSourceHint().coordinate].vision.getCard().range(Game.getAimHint().coordinate);

                    for (int i = 0; i < tmp.size(); i++) {
                        if (this.coordinate == tmp.get(i)) {
                            g.setColor(Color.yellow);
                            g.fillRect((int) this.x + 15 - (int) Game.getORG_X(), (int) this.y + 15 - (int) Game.getORG_Y(), 10, 10);
                        }
                    }

                    if (KeyInput.isT) {
                        if (Game.getAimHint() != null && !isBlocked
                                && Game.getSourceHint().vision.isLegal(Game.getSourceHint().coordinate, Game.getAimHint().coordinate)
                                && ((Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Blue)
                                || !Board.isFirst() && Game.getSourceHint().vision.getColor() == Main.Board.Renk.Red)) {

                            Handler.findDirections(Game.getSourceHint().coordinate, Game.getPressedHint() == null ? Game.getAimHint().coordinate : Game.getPressedHint().coordinate);
                            tmp = Board.getBoard()[Game.getSourceHint().coordinate].vision.range(Game.getAimHint().coordinate);

                            for (int i = 0; i < tmp.size(); i++) {
                                if (this.coordinate == tmp.get(i)) {
                                    g.setColor(Color.gray);
                                    g.fillRect((int) this.x + 15 - (int) Game.getORG_X(), (int) this.y + 30 - (int) Game.getORG_Y(), 10, 10);
                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("HINT");
        }
        g.setColor(Color.BLACK);
        g.drawRect((int) this.x - (int) Game.getORG_X(), (int) this.y - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2, Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 2);
        
    }
    public void setPoison(int poison) {this.poison = poison;}
    public void setIsBlocked(boolean isBlocked) {this.isBlocked = isBlocked;}
    public static void setIsFromCP(boolean s) {isFromCP = s;}
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setPoisonedRounds(int poisonedRounds) {this.poisonedRounds = poisonedRounds;}
    public void setVision(Visions vision) {this.vision = vision;}
    public void setIsVisible4Blue(boolean isVisible4White) {this.isVisible4Blue = isVisible4White;}
    public void setIsVisible4Red(boolean isVisible4Black) {this.isVisible4Red = isVisible4Black;}
    public void setBlockedRounds(int blockedRounds) {this.blockedRounds = blockedRounds;}
    public int getPoison() {return poison;}
    public boolean isIsBlocked() {return isBlocked;}
    public int getCoordinate() {return coordinate;}
    public float getX() {return x;}
    public float getY() {return y;}
    public Visions getVision() {return vision;}
    public boolean isIsVisible4Blue() {return isVisible4Blue;}
    public boolean isIsVisible4Red() {return isVisible4Red;}
    public int getBlockedRounds() {return blockedRounds;}
    public int getPoisonedRounds() {return poisonedRounds;}
}

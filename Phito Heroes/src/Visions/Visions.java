package Visions;

import Cards.Cards;
import Main.Board;
import Main.Game;
import Main.Hint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public abstract class Visions {

    protected int[] direction;
    private float greenValue = 250;
    private float redValue = 0;
    
    public enum VisionType {
        PoseidonV, ZeusV, NasosV, MartialisV, HecateV, PythiaV, EleonoreV, AresV, HerculesV, HadesV, MainBuilding, Bos,;
    }

    public void update() {
        x += velX;
        y += velY;
    }
    public void updateHP(){
        if (2.5f * 100 * this.HP / this.maxHP < 255 && 2.5f * 100 * this.HP / this.maxHP >= 0) {
            greenValue = 2.5f * 100 * this.HP / this.maxHP;
        }
        if (255 - greenValue >= 0 && 255 - greenValue < 255) {
            redValue = 255 - greenValue;
        }
        if (greenValue <= 0) {
            greenValue = 0;
        } else if (greenValue > 255) {
            greenValue = 255;
        }
        if (redValue <= 0) {
            redValue = 0;
        } else if (redValue > 255) {
            redValue = 255;
        }
    }

    //Kısa mesafe de 1*1 lik alan hasarı
    public ArrayList<Integer> range(int j){
        effectTmp = new ArrayList<>();
        effectTmp.add(j - 1);
        effectTmp.add(j);
        effectTmp.add(j + 1);
        effectTmp.add(j - Game.getKS() - 1);
        effectTmp.add(j - Game.getKS() + 1);
        effectTmp.add(j - Game.getKS());
        effectTmp.add(j + Game.getKS() - 1);
        effectTmp.add(j + Game.getKS() + 1);
        effectTmp.add(j + Game.getKS());
        cleanOut(j,effectTmp);
        return effectTmp;
    }


    //Hasar verme methodu
    public void effect() {
        for (int i = 0; i < effectTmp.size(); i++) {
            if (Board.getBoard()[effectTmp.get(i)].getVision().getColor() != this.getColor()
                    && (Board.getBoard()[i].getVision().getHP() / Board.getBoard()[i].getVision().getMaxHP()) != 0) {
                Board.getBoard()[effectTmp.get(i)].getVision().decreaseHP(700 / (100 * (Board.getBoard()[i].getVision().getHP() / Board.getBoard()[i].getVision().getMaxHP())));
            }
        }
    }


    public String info = "";
    protected Hint hint;
    protected Main.Board.Renk color;
    protected Cards card;
    protected VisionType visiontype;
    protected int HP;
    protected int maxHP;
    protected float x, y;
    public float velX, velY;
    protected ArrayList<Integer> visibleTmp = new ArrayList<>();
    protected ArrayList<Integer> effectTmp = new ArrayList<>();
    public static ArrayList<Visions> allVisions = new ArrayList<>();
    private static boolean isFromStorage = false;
    private static boolean isFromCP = false;
    protected int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Visions(VisionType visiontype, int HP, Cards card, int num) {
        this.num=num;
        this.visiontype = visiontype;
        this.card = card;
        this.HP = HP;
        this.maxHP = HP;
        this.direction = new int[2];
        if(!isFromStorage && !isFromCP){
            if (this.visiontype != VisionType.Bos) {
                this.card.setVision(this);
                allVisions.add(this);
            }
        }
    }

    //vision renders on boards
    public void render(Graphics g) {
        try {
            BufferedImage bimg = ImageIO.read(new File("res/visionart/" + this.card.getColor().toString().substring(0, 1) + this.visiontype + ".gif"));
            Image scaled = bimg.getScaledInstance(Game.getFRAME_DIMENSION().width / (int) Game.getVSK(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 10, Image.SCALE_SMOOTH);
            g.drawImage(scaled, (int) this.x - (int) Game.getORG_X(), (int) this.y + 10 - (int) Game.getORG_Y(), null);
        } catch (IOException | NullPointerException ex) {
        }

        if (this.HP != 0 && this.color != Main.Board.Renk.Temp && this.visiontype != VisionType.MainBuilding) {
            g.setColor(Color.white);
            g.fillRect((int) this.x + 3 - (int) Game.getORG_X(), (int) this.y - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 6, 4);
            g.setColor(new Color((int) redValue, (int) greenValue, 0));
            g.fillRect((int) this.x + 4 - (int) Game.getORG_X(), (int) this.y + 1 - (int) Game.getORG_Y(),
                    (100 * this.HP / this.maxHP) * ((Game.getFRAME_DIMENSION().width / (int) Game.getVSK()) - 8) / 100,
                    3);
            g.setColor(Color.white);
            g.drawRect((int) this.x + 3 - (int) Game.getORG_X(), (int) this.y - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK() - 6, 4);
        }
    }
    //Total range
    public abstract ArrayList<Integer> visibleRange(int j);
    //movement range
    public abstract boolean isLegal(int j, int k);
    
    public void cleanOut(int coor,ArrayList<Integer> tmp) {
        if(!isFromStorage)
            try {
                for (int i = 0; i < tmp.size(); i++) {
                    if ((tmp.get(i) >= Game.getKS() * Game.getKS() || tmp.get(i) < 0)) {
                        tmp.remove(i--);
                    } else if (Board.getBoard()[tmp.get(i)].isLeftSide != Board.getBoard()[coor].isLeftSide) {
                        if(i >= 0)
                            switch (Board.getBoard()[coor].getVision().getVisiontype()) {
                                case ZeusV:
                                case NasosV:
                                    if (coor % Game.getKS() - tmp.get(i) % Game.getKS() >= 5 || coor % Game.getKS() - tmp.get(i) % Game.getKS() < -6) {
                                        tmp.remove(i--);
                                    }
                                    break;
                                case PoseidonV:
                                case PythiaV:
                                    if (coor % Game.getKS() - tmp.get(i) % Game.getKS() >= 6 || coor % Game.getKS() - tmp.get(i) % Game.getKS() < -7) {
                                        tmp.remove(i--);
                                    }
                                    break;
                                case MainBuilding:
                                case HecateV:
                                case HadesV:
                                    if (coor % Game.getKS() - tmp.get(i) % Game.getKS() >= 7 || coor % Game.getKS() - tmp.get(i) % Game.getKS() < -8) {
                                        tmp.remove(i--);
                                    }
                                    break;
                                case MartialisV:
                                case EleonoreV:
                                    if (coor % Game.getKS() - tmp.get(i) % Game.getKS() >= 4 || coor % Game.getKS() - tmp.get(i) % Game.getKS() < -5) {
                                        tmp.remove(i--);
                                    }
                                    break;
                                case AresV:
                                case HerculesV:
                                    if (coor % Game.getKS() - tmp.get(i) % Game.getKS() >= 8 || coor % Game.getKS() - tmp.get(i) % Game.getKS() < -8) {
                                        tmp.remove(i--);
                                    }
                                    break;
                            }
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("visions clean out.");
            }
    }

    public static void setIsFromCP(boolean isFromCP) {
        Visions.isFromCP = isFromCP;
    }
    


    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public Hint getHint() {
        return hint;
    }

    public int getMaxHP() {
        return maxHP;
    }


    //HEALT POINT
    public void decreaseHP(int dcrs) {
        if (this.HP - dcrs <= maxHP) {
            this.HP -= dcrs;
        }
    }



    public void setCard(Cards card) {
        this.card = card;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setHP(int x) {
        this.HP = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Main.Board.Renk getColor() {
        return color;
    }

    public void setColor(Main.Board.Renk r) {
        color = r;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public int[] getDirection() {
        return direction;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public Cards getCard() {
        return card;
    }

    public VisionType getVisiontype() {
        return visiontype;
    }

    public int getHP() {
        return HP;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getGreenValue() {
        return greenValue;
    }

    public float getRedValue() {
        return redValue;
    }

}

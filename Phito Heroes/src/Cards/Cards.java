package Cards;

import Main.Board;
import Main.Game;
import Visions.*;


import java.util.ArrayList;


public abstract class Cards {


    public enum CardType {
        Zeus, EmptyCard, Poseidon, Hercules, Nasos, Pythia, Hades, Eleonore, Hecate, Martialis, Ares;
    }


    protected void attack(Cards card, int to, float bonus) {
        Board.getBoard()[to].getVision().decreaseHP(card.Power - (int) ((1 - bonus) * Board.getBoard()[to].getVision().getCard().getDefence()));
    }

    public void cleanOut(int coor) {
        for (int i = 0; i < tmp.size(); i++) {
            if ((tmp.get(i) >= Game.getKS() * Game.getKS() || tmp.get(i) < 0)) {
                tmp.remove(i--);
            } else if (Board.getBoard()[tmp.get(i)].isLeftSide != Board.getBoard()[coor].isLeftSide
                    && (coor % Game.getKS() <= 10 || coor % Game.getKS() >= Game.getKS() - 11)) {

            }
        }
    }


    public String info;
    private static boolean isFromCP = false;
    protected Visions vision;
    protected int[] direction;
    protected int PRICE;
    protected ArrayList<Integer> tmp;
    protected Main.Board.Renk color;
    protected int Power;
    protected int Defence;
    protected Visions.VisionType visiontype;
    protected CardType cardtype;
    public static ArrayList<Cards> allCards = new ArrayList<>();
    protected int num;

    public abstract ArrayList<Integer> range(int x);

    public abstract void effect();


    public Cards(int Power, int Defence,CardType cardtype, Visions.VisionType visiontype, int num) {
        this.num = num;
        this.visiontype = visiontype;
        this.Power = Power;
        this.Defence = Defence;
        this.cardtype = cardtype;
        this.PRICE = Power * 5 + Defence * 2;
        this.direction = new int[2];
        if (this.cardtype != CardType.EmptyCard && !isFromCP)
            allCards.add(this);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    //Blocks or poisons on map
    public void blockOrPoison(int from, int to) {
        if (Board.getBoard()[from].getVision().getCard().getCardtype() == CardType.Nasos) {
            Board.getBoard()[to].setIsBlocked(true);
            Board.getBoard()[to].setBlockedRounds(0);
        }
        if (Board.getBoard()[from].getVision().getCard().getCardtype() == CardType.Eleonore) {
            Board.getBoard()[to].setPoison(Board.getBoard()[to].getPoison() + 85);
            Board.getBoard()[to].setPoisonedRounds(0);
        }
        if (Board.getBoard()[to].getPoison() > 0 && Board.getBoard()[from].getVision().getCard().cardtype != CardType.Eleonore) {
            Board.getBoard()[from].getVision().decreaseHP(Board.getBoard()[to].getPoison());
        }
    }


    //This code blocks about Cards attack abilities and ranges
    public void roadEffect(int j, int k) {
        if (Board.getBoard()[k].getPoison() > 0) {
            Board.getBoard()[j].getVision().decreaseHP(Board.getBoard()[k].getPoison());
        }
        switch (Board.getBoard()[j].getVision().getVisiontype()) {
            case NasosV:
            case HerculesV:
            case EleonoreV:
                if (k % Game.getKS() == j % Game.getKS() && j < k) {//aşağıya gidiyorsa
                    for (int i = j; i < k; i += Game.getKS()) {
                        blockOrPoison(j, i);
                    }
                } else if (k % Game.getKS() == j % Game.getKS() && j > k) {
                    for (int i = k + Game.getKS(); i <= j; i += Game.getKS()) {
                        blockOrPoison(j, i);
                    }
                } else {
                    for (int l = 0; l < Game.getKS() * Game.getKS(); l += Game.getKS()) {
                        if (j < k && j >= l && k >= l && j < l + Game.getKS() && k < l + Game.getKS()) {
                            for (int i = j; i < k; i++) {
                                blockOrPoison(j, i);
                            }
                        } else if (j > k && j >= l && k >= l && j < l + Game.getKS() && k < l + Game.getKS()) {
                            for (int i = k + 1; i <= j; i++) {
                                blockOrPoison(j, i);
                            }
                        }
                    }
                }
                if (k > j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() + 1) == j % (Game.getKS() + 1)) {//sağ alt
                for (int i = j; i < k; i += Game.getKS() + 1) {
                    blockOrPoison(j, i);
                }
            } else if (k > j && k % Game.getKS() < j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sol alt
                for (int i = j; i < k; i += Game.getKS() - 1) {
                    blockOrPoison(j, i);
                }
            } else if (k < j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sağ üst
                for (int i = k + Game.getKS() - 1; i <= j; i += Game.getKS() - 1) {
                    blockOrPoison(j, i);
                }
            } else if (k < j && k % Game.getKS() < j % Game.getKS() && k % (Game.getKS() + 1) == j % (Game.getKS() + 1)) {//sol üst
                    for (int i = k + Game.getKS() + 1; i <= j; i += Game.getKS() + 1) {
                        blockOrPoison(j, i);
                    }
                }blockOrPoison(j, j);
                break;
            default:
                break;
        }
    }

    public int getPrice() {
        return PRICE;
    }

    public ArrayList<Integer> getRangeArrayList() {
        return tmp;
    }

    public static void setIsFromCP(boolean isFromCP) {
        Cards.isFromCP = isFromCP;
    }

    public Main.Board.Renk getColor() {
        return color;
    }

    public Visions getVision() {
        return vision;
    }

    public void setVision(Visions vision) {
        this.vision = vision;
    }

    public int getPower() {
        return Power;
    }

    public int getDefence() {
        return Defence;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public CardType getCardtype() {
        return cardtype;
    }

    public static ArrayList<Cards> getAllCards() {
        return allCards;
    }

    public void setPower(int Power) {
        this.Power = Power;
    }

    public void setColor(Main.Board.Renk r) {
        this.color = r;
    }

    public void setDefence(int Defence) {
        this.Defence = Defence;
    }

    public void setCardtype(CardType cardtype) {
        this.cardtype = cardtype;
    }
}

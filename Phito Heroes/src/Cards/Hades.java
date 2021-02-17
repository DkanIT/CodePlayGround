package Cards;
import Main.Board;
import Main.Game;
import Main.Hint;
import Visions.*;
import java.util.ArrayList;


public class Hades extends Cards {

    public Hades(Main.Board.Renk color) {
        super(85, 30,Cards.CardType.Hades, Visions.VisionType.HadesV, 3);
        this.color = color;
        this.info = "Deals damage to his opponent and Changes it to Phtyia";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        int menzil = 1;
        switch (this.direction[0]) {
            case 0:
                tmp.add(x - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) * Game.getKS());
                tmp.add(x - menzil * Game.getKS());
                break;
            case 1:
                tmp.add(x + (menzil + 1 + 1) - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) - (menzil + 1) * Game.getKS());
                tmp.add(x + menzil - menzil * Game.getKS());
                break;
            case 2:
                tmp.add(x + (menzil + 1 + 1));
                tmp.add(x + (menzil + 1));
                tmp.add(x + menzil);
                break;
            case 3:
                tmp.add(x + (menzil + 1 + 1) + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil + menzil * Game.getKS());
                break;
            case 4:
                tmp.add(x + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil * Game.getKS());
                break;
            case 5:
                tmp.add(x - (menzil + 1 + 1) + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x - menzil + menzil * Game.getKS());
                break;
            case 6:
                tmp.add(x - (menzil + 1 + 1));
                tmp.add(x - (menzil + 1));
                tmp.add(x - menzil);
                break;
            case 7:
                tmp.add(x - (menzil + 1 + 1) - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) - (menzil + 1) * Game.getKS());
                tmp.add(x - menzil - menzil * Game.getKS());
                break;
        }
        cleanOut(x);
        return this.tmp;
    }

    private int tmpHP;

    @Override
    public void effect() {
        for (int l = 0; l < Game.getKS() * Game.getKS(); l++) {
            for (int i = 0; i < this.tmp.size(); i++) {
                if (Board.getBoard()[l].getCoordinate() == this.tmp.get(i) && Board.getBoard()[l].getVision().getColor() != this.color && Board.getBoard()[l].getVision().getColor() != Board.Renk.Temp) {
                    tmpHP = Board.getBoard()[l].getVision().getHP() - this.Power + Board.getBoard()[l].getVision().getCard().Defence;
                    Board.getBoard()[l] = new Hint(l, new PythiaV(Board.getBoard()[l].getVision().getCard(), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                    Board.getBoard()[l].getVision().getCard().setDefence(120);
                    Board.getBoard()[l].getVision().getCard().setPower(0);
                    Board.getBoard()[l].getVision().setNum(9);
                    Board.getBoard()[l].getVision().setHP(tmpHP);
                }
            }
        }
    }
}

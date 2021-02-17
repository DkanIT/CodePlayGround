package Cards;

import Main.Board;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;


public class Zeus extends Cards {


    public Zeus(Main.Board.Renk color) {
        super(70, 100, CardType.Zeus, Visions.VisionType.ZeusV, 10);
        this.color = color;
        this.visiontype = Visions.VisionType.ZeusV;
        this.info = "Zeus gives huge damage and ignores half of enemy's armor. Great View on map";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        switch (this.direction[0]) {
            case 0:
                this.tmp.add(x - Game.getKS());
                this.tmp.add(x - Game.getKS() - 1);
                this.tmp.add(x - Game.getKS() + 1);
                break;
            case 1:
                this.tmp.add(x - 2 * Game.getKS());
                this.tmp.add(x + 1 - Game.getKS());
                this.tmp.add(x + 2);
                break;
            case 2:
                this.tmp.add(x + 1 - Game.getKS());
                this.tmp.add(x + 1 + Game.getKS());
                this.tmp.add(x + 1);
                break;
            case 3:
                this.tmp.add(x + 2 * Game.getKS());
                this.tmp.add(x + 1 + Game.getKS());
                this.tmp.add(x + 2);
                break;
            case 4:
                this.tmp.add(x + Game.getKS());
                this.tmp.add(x + Game.getKS() + 1);
                this.tmp.add(x + Game.getKS() - 1);
                break;
            case 5:
                this.tmp.add(x + 2 * Game.getKS());
                this.tmp.add(x - 1 + Game.getKS());
                this.tmp.add(x - 2);
                break;
            case 6:
                this.tmp.add(x - 1 - Game.getKS());
                this.tmp.add(x - 1 + Game.getKS());
                this.tmp.add(x - 1);
                break;
            case 7:
                this.tmp.add(x - 2 * Game.getKS());
                this.tmp.add(x - 1 - Game.getKS());
                this.tmp.add(x - 2);
                break;
        }
        cleanOut(x);
        return this.tmp;
    }

    @Override
    public void effect() {
        for (int l = 0; l < Game.getKS() * Game.getKS(); l++) {
            for (int i = 0; i < this.tmp.size(); i++) {
                if (Board.getBoard()[l].getCoordinate() == this.tmp.get(i) && Board.getBoard()[l].getVision().getColor() != this.color && Board.getBoard()[l].getVision().getColor() != Board.Renk.Temp)
                    Board.getBoard()[l].getVision().decreaseHP(70 + Board.getBoard()[l].getVision().getCard().getDefence() / 2);
            }
        }
    }
}

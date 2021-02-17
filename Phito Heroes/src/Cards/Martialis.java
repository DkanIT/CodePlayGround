package Cards;

import Main.Board;
import Main.Board.Renk;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;


public class Martialis extends Cards {

    public Martialis(Main.Board.Renk color) {
        super(60, 45, CardType.Martialis, Visions.VisionType.MartialisV, 6);

        this.color = color;
        this.info = "Deals damage equals to his current health divide 4 plus his power.Lose half hp.";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.tmp.add(x - Game.getKS() - 1 + i);
        }
        for (int i = 0; i < 3; i++) {
            this.tmp.add(x + Game.getKS() - 1 + i);
        }
        this.tmp.add(x + 1);
        this.tmp.add(x - 1);
        cleanOut(x);
        return this.tmp;
    }

    @Override
    public void effect() {
        for (int l = 0; l < Game.getKS() * Game.getKS(); l++) {
            for (int i = 0; i < this.tmp.size(); i++) {
                if (Board.getBoard()[l].getCoordinate() == this.tmp.get(i) && Board.getBoard()[l].getVision().getColor() != Renk.Temp) {
                    Board.getBoard()[l].getVision().decreaseHP(this.vision.getHP()/5+this.getPower()*2);
                    this.getVision().setHP(this.getVision().getHP()/2);

                }

            }
        }
    }
}

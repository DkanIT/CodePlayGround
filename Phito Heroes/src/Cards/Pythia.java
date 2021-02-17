package Cards;

import Main.Board;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;


public class Pythia extends Cards {

    private static int x;

    public Pythia(Main.Board.Renk color) {
        super(25, 120, CardType.Pythia, Visions.VisionType.PythiaV, 9);
        this.visiontype = Visions.VisionType.PythiaV;
        this.color = color;
        this.info = "Heals nearby allies. Uses allies Power*2 and own defences";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            this.tmp.add(x - Game.getKS() - 1 + i);
        for (int i = 0; i < 3; i++)
            this.tmp.add(x + Game.getKS() - 1 + i);
        this.tmp.add(x + 1);
        this.tmp.add(x - 1);
        cleanOut(x);
        return this.tmp;
    }

    @Override
    public void effect() {
        for (int l = 0; l < Game.getKS() * Game.getKS(); l++) {
            for (int i = 0; i < this.tmp.size(); i++) {
                if (Board.getBoard()[l].getCoordinate() == this.tmp.get(i) && Board.getBoard()[l].getVision().getColor() != Board.Renk.Temp) {
                    Board.getBoard()[l].getVision().decreaseHP(-(this.Defence + Board.getBoard()[l].getVision().getCard().Power * 2));
                }
            }
        }
    }
}

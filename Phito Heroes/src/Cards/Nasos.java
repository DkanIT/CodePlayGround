package Cards;

import Visions.Visions;

import java.util.ArrayList;


public class Nasos extends Cards {

    public Nasos(Main.Board.Renk color) {
        super(25, 80, CardType.Nasos, Visions.VisionType.NasosV, 7);
        this.color = color;
        this.visiontype = Visions.VisionType.NasosV;
        this.info = "Blocks the tiles that he passes for a particular time.Blocked tiles can’t be used by enemies.";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        this.tmp.add(x);
        cleanOut(x);
        return this.tmp;
    }

    @Override
    public void effect() {

    }
}

package Cards;
import Visions.EmptyVision;
import Visions.Visions;

import java.util.ArrayList;


public class EmptyCard extends Cards{

    public EmptyCard(Main.Board.Renk color){
        super(0,0,CardType.EmptyCard, Visions.VisionType.Bos,0);
        this.visiontype = Visions.VisionType.Bos;
        this.color = color;
    }
    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        return this.tmp;
    }
    @Override
    public void effect() {
    }
}

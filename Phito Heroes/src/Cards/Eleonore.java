package Cards;

import Visions.Visions;

import java.util.ArrayList;



public class Eleonore extends Cards{

    public Eleonore(Main.Board.Renk color){
        super(65,90,Cards.CardType.Eleonore, Visions.VisionType.EleonoreV,2);
        this.visiontype = Visions.VisionType.EleonoreV;
        this.color = color;
        this.info = "Leaves a poisonous trail on the the tails she passes.";
    }
    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        this.tmp.add(x);
        return this.tmp;
    }
    
    @Override
    public void effect() {
    }
}

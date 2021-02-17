package Cards;

import Main.Board;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;


public class Ares extends Cards{
    
    public Ares(Main.Board.Renk color){
        super(75,80,CardType.Ares, Visions.VisionType.AresV,1);
        this.color = color;
        this.PRICE += 455;
        this.visiontype = Visions.VisionType.AresV;
        this.info = "Ares gives a damage directly. Ignoring Armor";
    }
    
    @Override
    //Attack Range
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        for(int i=0;i<3;i++)
                this.tmp.add(x-Game.getKS()-1+i);
            for(int i=0;i<3;i++)
                this.tmp.add(x+Game.getKS()-1+i);
            this.tmp.add(x+1);
            this.tmp.add(x-1);
        cleanOut(x);
        return this.tmp;
    }
    @Override
    public void effect() {
        for(int l=0;l<Game.getKS()*Game.getKS();l++){
            for(int i=0;i<this.tmp.size();i++){
                if(Board.getBoard()[l].getCoordinate()==this.tmp.get(i)&&Board.getBoard()[l].getVision().getColor()!=this.color&&Board.getBoard()[l].getVision().getColor()!= Board.Renk.Temp){
                     Board.getBoard()[l].getVision().decreaseHP(365);

                }
            }
        }
    }
}
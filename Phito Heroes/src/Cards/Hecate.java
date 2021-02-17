package Cards;

import Main.Board;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;


public class Hecate extends Cards{

    public Hecate(Main.Board.Renk color){
        super(75,35,CardType.Hecate, Visions.VisionType.HecateV,4);
        this.color = color;
        this.visiontype = Visions.VisionType.HecateV;
        this.info = "Heals himself as the same amount as he deals damage.";
    }
    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        int menzil=2;
        switch(this.direction[0]){
            case 0:
                tmp.add(x-(menzil+1+1)*Game.getKS());
                tmp.add(x-(menzil+1)*Game.getKS());
                tmp.add(x-menzil*Game.getKS());
                break;
            case 1:
                tmp.add(x+(menzil+1+1)-(menzil+1+1)*Game.getKS());
                tmp.add(x+(menzil+1)-(menzil+1)*Game.getKS());
                tmp.add(x+menzil-menzil*Game.getKS());
                break;
            case 2:
                tmp.add(x+(menzil+1+1));
                tmp.add(x+(menzil+1));
                tmp.add(x+menzil);
                break;
            case 3:
                tmp.add(x+(menzil+1+1)+(menzil+1+1)*Game.getKS());
                tmp.add(x+(menzil+1)+(menzil+1)*Game.getKS());
                tmp.add(x+menzil+menzil*Game.getKS());
                break;
            case 4:
                tmp.add(x+(menzil+1+1)*Game.getKS());
                tmp.add(x+(menzil+1)*Game.getKS());
                tmp.add(x+menzil*Game.getKS());
                break;
            case 5:
                tmp.add(x-(menzil+1+1)+(menzil+1+1)*Game.getKS());
                tmp.add(x-(menzil+1)+(menzil+1)*Game.getKS());
                tmp.add(x-menzil+menzil*Game.getKS());
                break;
            case 6:
                tmp.add(x-(menzil+1+1));
                tmp.add(x-(menzil+1));
                tmp.add(x-menzil);
                break;
            case 7:
                tmp.add(x-(menzil+1+1)-(menzil+1+1)*Game.getKS());
                tmp.add(x-(menzil+1)-(menzil+1)*Game.getKS());
                tmp.add(x-menzil-menzil*Game.getKS());
                break;
        }
        cleanOut(x);
        return this.tmp;
    }
    @Override
    public void effect() {
        for(int l=0;l<Game.getKS()*Game.getKS();l++){
            for(int i=0;i<this.tmp.size();i++){
                if(Board.getBoard()[l].getCoordinate()==this.tmp.get(i)&&Board.getBoard()[l].getVision().getColor()!=this.color&&Board.getBoard()[l].getVision().getColor()!=Main.Board.Renk.Temp){
                    Board.getBoard()[l].getVision().decreaseHP(this.getPower()*2-Board.getBoard()[l].getVision().getCard().getDefence());
                    this.getVision().decreaseHP(-(this.getPower()*2-Board.getBoard()[l].getVision().getCard().getDefence()));
                }
            }
        }
    }
}



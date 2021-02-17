package Visions;

import Cards.Cards;
import Main.Game;
import java.util.ArrayList;


public class PythiaV extends Visions {

    public PythiaV(Cards card, float x, float y){
        super(VisionType.PythiaV,450,card,9);
        this.color = this.card.getColor();
        this.velX = 0;
        this.velY = 0;
        this.x = x;
        this.y = y;
    }

    @Override
    public ArrayList<Integer> visibleRange(int j) {
        visibleTmp = new ArrayList<>();
        visibleTmp.add(j);
        for (int i = -5; i < 6; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for(int o = 1;o<5;o++){
            for (int i = j - Game.getKS() * o - (5 - o); i < j - Game.getKS() * o + 1 + 5 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        for(int i=0;i<visibleTmp.size();i++){
            if(visibleTmp.get(i) == j - Game.getKS() * 3 -2 
             ||visibleTmp.get(i) == j - Game.getKS() * 2 -3
             ||visibleTmp.get(i) == j + Game.getKS() * 3 +2 
             ||visibleTmp.get(i) == j + Game.getKS() * 2 +3
             ||visibleTmp.get(i) == j - Game.getKS() * 3 +2 
             ||visibleTmp.get(i) == j - Game.getKS() * 2 +3
             ||visibleTmp.get(i) == j + Game.getKS() * 3 -2 
             ||visibleTmp.get(i) == j + Game.getKS() * 2 -3)
                visibleTmp.remove(i--);
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }
    
    @Override
    public boolean isLegal(int j, int k) {
        return (Math.abs(j-k)==Game.getKS()*4||(Math.abs(j-k)==4&&Math.abs(j%Game.getKS()-k%Game.getKS())==4))||
                (Math.abs(j-k)==Game.getKS()*3||(Math.abs(j-k)==3&&Math.abs(j%Game.getKS()-k%Game.getKS())==3))||
                (Math.abs(j-k)==Game.getKS()*2||(Math.abs(j-k)==2&&Math.abs(j%Game.getKS()-k%Game.getKS())==2));
    }
    
}

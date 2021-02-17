package Visions;

import Cards.Cards;
import Main.Game;
import java.util.ArrayList;



public class MainBuilding extends Visions {

    public MainBuilding(Cards card,float x,float y) {
        super(VisionType.MainBuilding, 8000, card,999);
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
        visibleTmp.add(j+Game.getKS()*6-1);
        visibleTmp.add(j-Game.getKS()*6+1);
        visibleTmp.add(j+Game.getKS()-6);
        visibleTmp.add(j-Game.getKS()+6);
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        return false;
    }

}

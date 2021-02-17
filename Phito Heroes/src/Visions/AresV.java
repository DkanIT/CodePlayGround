package Visions;

import Cards.Cards;

import Main.Board;
import Main.Game;
import java.util.ArrayList;


public class AresV extends Visions {

    public AresV(Cards card, float x, float y) {
        super(VisionType.AresV, 670, card, 1);
        this.color = this.card.getColor();
        this.velX = 0;
        this.velY = 0;
        this.x = x;
        this.y = y;
    }



    @Override
    public ArrayList<Integer> visibleRange(int j) {
        try{
            visibleTmp = new ArrayList<>();
            visibleTmp.add(j);
            int k = -4;
            for (int i = k * Game.getKS(); i < 5 * Game.getKS(); i += Game.getKS()) {
                for (k = -4; k < 5; k++) {
                    visibleTmp.add(j + k + i);
                }
            }
            for (int i = 0; i < visibleTmp.size(); i++) {
                if (visibleTmp.get(i) == j - 4 - Game.getKS() * 4
                        || visibleTmp.get(i) == j - 4 - Game.getKS() * 3
                        || visibleTmp.get(i) == j - 3 - Game.getKS() * 4
                        || visibleTmp.get(i) == j + 4 - Game.getKS() * 4
                        || visibleTmp.get(i) == j + 4 - Game.getKS() * 3
                        || visibleTmp.get(i) == j + 3 - Game.getKS() * 4
                        || visibleTmp.get(i) == j - 4 + Game.getKS() * 4
                        || visibleTmp.get(i) == j - 4 + Game.getKS() * 3
                        || visibleTmp.get(i) == j - 3 + Game.getKS() * 4
                        || visibleTmp.get(i) == j + 4 + Game.getKS() * 4
                        || visibleTmp.get(i) == j + 4 + Game.getKS() * 3
                        || visibleTmp.get(i) == j + 3 + Game.getKS() * 4) {
                    visibleTmp.remove(i--);
                }
            }
        }
        catch(Exception e){

        }
        cleanOut(j, visibleTmp);
        return visibleTmp;
    }


    @Override
    public boolean isLegal(int j, int k) {
        visibleTmp = new ArrayList<>();
        visibleTmp.add(j);
        try {
            for (int i = j - 4 * Game.getKS(); i < j - Game.getKS() ; i += Game.getKS()) {
                visibleTmp.add(i);
                visibleTmp.add(i + Game.getKS() * 6);
            }
            for (int i = j - 4; i < j -1; i++) {
                visibleTmp.add(i);
                visibleTmp.add(i + 6);
            }
            for (int i = 1; i < 4; i++) {
                visibleTmp.add(j - i - i * Game.getKS());
                visibleTmp.add(j - i + i * Game.getKS());
                visibleTmp.add(j + i - i * Game.getKS());
                visibleTmp.add(j + i + i * Game.getKS());
            }
        } catch (Exception e) {
            System.out.println("");
        }
        cleanOut(j, visibleTmp);
        return visibleTmp.contains(k);
    }

}

package Visions;

import Cards.Cards;
import Main.Game;
import java.util.ArrayList;


public class HadesV extends Visions {

    public HadesV(Cards card, float x, float y) {
        super(VisionType.HadesV, 575, card, 3);
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
        for (int o = 1; o < 5; o++) {
            for (int i = j - Game.getKS() * o - (5 - o); i < j - Game.getKS() * o + 1 + 5 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        for (int i = 0; i < visibleTmp.size(); i++) {
            if (   visibleTmp.get(i) == j - 5
                || visibleTmp.get(i) == j + 5
                || visibleTmp.get(i) == j - 5 * Game.getKS()
                || visibleTmp.get(i) == j + 5 * Game.getKS()) {
                visibleTmp.remove(i--);
            }
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        visibleTmp = new ArrayList<>();
        visibleTmp.add(j);
        for (int i = 0; i < 3; i++) {
            visibleTmp.add(j - 4 * Game.getKS() - 1 + i);
            visibleTmp.add(j + 4 * Game.getKS() - 1 + i);
            visibleTmp.add(j - 4 - Game.getKS() + i * Game.getKS());
            visibleTmp.add(j + 4 - Game.getKS() + i * Game.getKS());
        }
        visibleTmp.add(j + 3 * Game.getKS() + 2);
        visibleTmp.add(j + 2 * Game.getKS() + 3);
        visibleTmp.add(j - 3 * Game.getKS() + 2);
        visibleTmp.add(j - 2 * Game.getKS() + 3);
        visibleTmp.add(j + 3 * Game.getKS() - 2);
        visibleTmp.add(j + 2 * Game.getKS() - 3);
        visibleTmp.add(j - 3 * Game.getKS() - 2);
        visibleTmp.add(j - 2 * Game.getKS() - 3);
        visibleTmp.add(j - 1 * Game.getKS() - 1);
        visibleTmp.add(j - 2 * Game.getKS() - 2);
        visibleTmp.add(j + 1 * Game.getKS() + 1);
        visibleTmp.add(j + 2 * Game.getKS() + 2);
        visibleTmp.add(j + 1 * Game.getKS() - 1);
        visibleTmp.add(j + 2 * Game.getKS() - 2);
        visibleTmp.add(j - 1 * Game.getKS() + 1);
        visibleTmp.add(j - 2 * Game.getKS() + 2);
        cleanOut(j,visibleTmp);
        return visibleTmp.contains(k);
    }
}

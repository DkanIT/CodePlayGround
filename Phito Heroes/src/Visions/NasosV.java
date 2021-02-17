package Visions;

import Cards.Cards;
import Main.Game;

import java.util.ArrayList;


public class NasosV extends Visions {

    public NasosV(Cards card, float x, float y) {
        super(VisionType.NasosV, 900, card, 7);
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
        for (int i = -3; i < 4; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for (int o = 1; o < 3; o++) {
            for (int i = j - Game.getKS() * o - (3 - o); i < j - Game.getKS() * o + 1 + 3 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        cleanOut(j, visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        if (Math.abs(k - j) == Game.getKS()
                || Math.abs(k - j) == Game.getKS()
                || Math.abs(k - j) == Game.getKS()*2
                || Math.abs(k - j) == Game.getKS()*2
                || Math.abs(k - j) == Game.getKS()-2
                || Math.abs(k - j) == Game.getKS()*3
                || Math.abs(k - j) == 1
                || Math.abs(k - j) == 0
                || Math.abs(k - j) == Game.getKS()
                || Math.abs(k - j) == Game.getKS()+2
                || Math.abs(k - j) == Game.getKS()-2
                || Math.abs(k - j) == 2
                || Math.abs(k - j) == 3
                || Math.abs(k - j) == Game.getKS() + 1
                || Math.abs(k - j) == Game.getKS() - 1
                || Math.abs(k - j) == Game.getKS() * 2 + 1
                || Math.abs(k - j) == Game.getKS() * 2 - 1) {
            if (Math.abs(j % Game.getKS() - k % Game.getKS()) < 4) {
                return true;
            }
        }
        return false;
    }

}

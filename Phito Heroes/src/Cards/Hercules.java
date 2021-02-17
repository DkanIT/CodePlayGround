package Cards;

import Main.Board;
import Main.Game;
import Visions.Visions;

import java.util.ArrayList;

public class Hercules extends Cards {

    public Hercules(Main.Board.Renk color) {
        super(90, 80, CardType.Hercules, Visions.VisionType.HerculesV, 5);
        this.color = color;
        this.visiontype = Visions.VisionType.HerculesV;
        this.info = "Deals an area damage with his bombs and blocks his opponent’s next attack";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        int menzil = 2;
        switch (this.direction[0]) {
            case 0:
                tmp.add(x - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) * Game.getKS());
                tmp.add(x - menzil * Game.getKS());
                break;
            case 1:
                tmp.add(x + (menzil + 1 + 1) - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) - (menzil + 1) * Game.getKS());
                tmp.add(x + menzil - menzil * Game.getKS());
                break;
            case 2:
                tmp.add(x + (menzil + 1 + 1));
                tmp.add(x + (menzil + 1));
                tmp.add(x + menzil);
                break;
            case 3:
                tmp.add(x + (menzil + 1 + 1) + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil + menzil * Game.getKS());
                break;
            case 4:
                tmp.add(x + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil * Game.getKS());
                break;
            case 5:
                tmp.add(x - (menzil + 1 + 1) + (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x - menzil + menzil * Game.getKS());
                break;
            case 6:
                tmp.add(x - (menzil + 1 + 1));
                tmp.add(x - (menzil + 1));
                tmp.add(x - menzil);
                break;
            case 7:
                tmp.add(x - (menzil + 1 + 1) - (menzil + 1 + 1) * Game.getKS());
                tmp.add(x - (menzil + 1) - (menzil + 1) * Game.getKS());
                tmp.add(x - menzil - menzil * Game.getKS());
                break;
        }
        cleanOut(x);
        return this.tmp;
    }

    @Override
    public void effect() {
        for (int l = 0; l < Game.getKS() * Game.getKS(); l++) {
            for (int i = 0; i < this.tmp.size(); i++) {
                if (Board.getBoard()[l].getCoordinate() == this.tmp.get(i) && Board.getBoard()[l].getVision().getColor() != this.color && Board.getBoard()[l].getVision().getColor() != Board.Renk.Temp) {
                    Board.getBoard()[l].setIsBlocked(true);
                    attack(this, l, 0);
                    Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    if ((l + 1) % Game.getKS() - l % Game.getKS() == 1) {
                        Board.getBoard()[l + 1].setIsBlocked(true);
                        attack(this, l + 1, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if ((l - 1) % Game.getKS() - l % Game.getKS() == -1) {
                        Board.getBoard()[l - 1].setIsBlocked(true);
                        attack(this, l - 1, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l - Game.getKS() >= 0) {
                        Board.getBoard()[l - Game.getKS()].setIsBlocked(true);
                        attack(this, l - Game.getKS(), 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l - Game.getKS() + 1 >= 0) {
                        Board.getBoard()[l - Game.getKS() + 1].setIsBlocked(true);
                        attack(this, l - Game.getKS() + 1, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l - Game.getKS() - 1 >= 0) {
                        Board.getBoard()[l - Game.getKS() - 1].setIsBlocked(true);
                        attack(this, l - Game.getKS() - 1, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l + Game.getKS() < Game.getKS() * Game.getKS()) {
                        Board.getBoard()[l + Game.getKS()].setIsBlocked(true);
                        attack(this, l + Game.getKS(), 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l + Game.getKS() + 1 < Game.getKS() * Game.getKS()) {
                        Board.getBoard()[l + Game.getKS() + 1].setIsBlocked(true);
                        attack(this, l + Game.getKS() + l, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                    if (l + Game.getKS() - 1 < Game.getKS() * Game.getKS()) {
                        Board.getBoard()[l + Game.getKS() - 1].setIsBlocked(true);
                        attack(this, l + Game.getKS() - 1, 0);
                        Board.getBoard()[l].setBlockedRounds(Board.getBoard()[l].getBlockedRounds() + 4);
                    }
                }
            }
        }
    }
}

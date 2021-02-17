package Cards;

import Main.Board;
import Main.Game;
import Visions.EmptyVision;
import Visions.Visions;

import java.util.ArrayList;


public class Poseidon extends Cards {

    public Poseidon(Main.Board.Renk color) {
        super(60, 50, CardType.Poseidon, Visions.VisionType.PoseidonV, 8);
        this.color = color;
        this.visiontype = Visions.VisionType.PoseidonV;
        this.info = "Pushes enemy phito Towards and deals damage.";
    }

    @Override
    public ArrayList<Integer> range(int x) {
        this.tmp = new ArrayList<>();
        int menzil = 2;
        switch (this.direction[0]) {
            case 0:
                tmp.add(x - (menzil + 1) * Game.getKS());
                tmp.add(x - menzil * Game.getKS());
                break;
            case 1:
                tmp.add(x + (menzil + 1) - (menzil + 1) * Game.getKS());
                tmp.add(x + menzil - menzil * Game.getKS());
                break;
            case 2:
                tmp.add(x + (menzil + 1));
                tmp.add(x + menzil);
                break;
            case 3:
                tmp.add(x + (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil + menzil * Game.getKS());
                break;
            case 4:
                tmp.add(x + (menzil + 1) * Game.getKS());
                tmp.add(x + menzil * Game.getKS());
                break;
            case 5:
                tmp.add(x - (menzil + 1) + (menzil + 1) * Game.getKS());
                tmp.add(x - menzil + menzil * Game.getKS());
                break;
            case 6:
                tmp.add(x - (menzil + 1));
                tmp.add(x - menzil);
                break;
            case 7:
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
                    attack(this, i, 0.2f);
                    if (Board.Renk.Red == this.color) {
                        if (l + Game.getKS() * 3 < Game.getKS() * Game.getKS() && Board.getBoard()[l + Game.getKS()].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l + Game.getKS() * 2].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l + Game.getKS() * 3].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l + Game.getKS() * 3].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l + Game.getKS() * 3].getVision().setX(Board.getBoard()[l + Game.getKS() * 3].getX());
                            Board.getBoard()[l + Game.getKS() * 3].getVision().setY(Board.getBoard()[l + Game.getKS() * 3].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        } else if (l + Game.getKS() * 2 < Game.getKS() * Game.getKS() && Board.getBoard()[l + Game.getKS()].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l + Game.getKS() * 2].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l + Game.getKS() * 2].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l + Game.getKS() * 2].getVision().setX(Board.getBoard()[l + Game.getKS() * 2].getX());
                            Board.getBoard()[l + Game.getKS() * 2].getVision().setY(Board.getBoard()[l + Game.getKS() * 2].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        } else if (l + Game.getKS() < Game.getKS() * Game.getKS() && Board.getBoard()[l + Game.getKS()].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l + Game.getKS()].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l + Game.getKS()].getVision().setX(Board.getBoard()[l + Game.getKS()].getX());
                            Board.getBoard()[l + Game.getKS()].getVision().setY(Board.getBoard()[l + Game.getKS()].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        }
                    } else if (Board.Renk.Blue == this.color) {
                        if (l - Game.getKS() * 3 >= 0 && Board.getBoard()[l - Game.getKS()].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l - Game.getKS() * 2].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l - Game.getKS() * 3].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l - Game.getKS() * 3].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l - Game.getKS() * 3].getVision().setX(Board.getBoard()[l - Game.getKS() * 3].getX());
                            Board.getBoard()[l - Game.getKS() * 3].getVision().setY(Board.getBoard()[l - Game.getKS() * 3].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        } else if (l - Game.getKS() * 2 >= 0 && Board.getBoard()[l - Game.getKS()].getVision().getColor() == Board.Renk.Temp && Board.getBoard()[l - Game.getKS() * 2].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l - Game.getKS() * 2].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l - Game.getKS() * 2].getVision().setX(Board.getBoard()[l - Game.getKS() * 2].getX());
                            Board.getBoard()[l - Game.getKS() * 2].getVision().setY(Board.getBoard()[l - Game.getKS() * 2].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        } else if (l - Game.getKS() >= 0 && Board.getBoard()[l - Game.getKS()].getVision().getColor() == Board.Renk.Temp) {
                            Board.getBoard()[l - Game.getKS()].setVision(Board.getBoard()[l].getVision());
                            Board.getBoard()[l - Game.getKS()].getVision().setX(Board.getBoard()[l - Game.getKS()].getX());
                            Board.getBoard()[l - Game.getKS()].getVision().setY(Board.getBoard()[l - Game.getKS()].getY());
                            Board.getBoard()[l].setVision(new EmptyVision(new EmptyCard(Board.Renk.Temp), Board.getCoordinates().get(l)[0], Board.getCoordinates().get(l)[1]));
                        }
                    }
                }
            }
        }
    }
}
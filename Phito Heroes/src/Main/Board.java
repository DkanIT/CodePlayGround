package Main;

import Cards.*;
import GUI.CardPanel;
import GUI.Info;
import Visions.*;

import java.util.ArrayList;
import java.util.HashMap;


public class Board {

    public Board() {
        Sound.init();
        GameStatus.initRegions();
        tmp = new int[2];
        isFirst = true;

        //map boyutu 24*24
        board = new Hint[Game.getKS() * Game.getKS()];
        int m = 0;
        coordinates = new HashMap<>();
        for (int i = 0; i < Game.getKS(); i++) {
            for (int j = 0; j < Game.getKS(); j++) {

                //genel / zoom atmak istediğim alan * total kare sayısı
                tmp[0] = Game.getFRAME_DIMENSION().width / (int) Game.getVSK() * j;
                tmp[1] = Game.getFRAME_DIMENSION().width / (int) Game.getVSK() * i;
                coordinates.put(m, tmp);
                board[m] = new Hint(m, new EmptyVision(new EmptyCard(Renk.Temp), -1, -1));
                board[m].setX(coordinates.get(m)[0]);
                board[m].setY(coordinates.get(m)[1]);
                board[m].getVision().setX(coordinates.get(m)[0]);
                board[m].getVision().setY(coordinates.get(m)[1]);
                makeVisible(m++);
                tmp = new int[2];
            }
        }
        board[Game.getKS() * Game.getKS() - 1] = new Hint(Game.getKS() * Game.getKS() - 1, new MainBuilding(new EmptyCard(Renk.Blue), coordinates.get(Game.getKS() * Game.getKS() - 1)[0], coordinates.get(Game.getKS() * Game.getKS() - 1)[1]));
        board[0] = new Hint(0, new MainBuilding(new EmptyCard(Renk.Red), coordinates.get(0)[0], coordinates.get(0)[1]));


        //basic spawn giving coordinate to spwan a character
        //board[255] = new Hint(255, new HecateV (new Hecate(Renk.Blue), coordinates.get(255)[0], coordinates.get(255)[1]));
        //board[300] = new Hint(300, new MartialisV (new Martialis(Renk.Red), coordinates.get(300)[0], coordinates.get(300)[1]));

        Handler.updateCardDirection();
        Handler.updateVisionsHint();
        Info.initCoordinates();
        CardPanel.initCoordinates();
        makeInvisible();

    }


    //changing visions places and updates locations
    public static void updateCoordinates() {
        tmp = new int[2];
        int m = 0;
        coordinates = new HashMap<>();
        for (int i = 0; i < Game.getKS(); i++) {

            for (int j = 0; j < Game.getKS(); j++) {
                tmp[0] = Game.getFRAME_DIMENSION().width / (int) Game.getVSK() * j;
                tmp[1] = Game.getFRAME_DIMENSION().width / (int) Game.getVSK() * i;
                coordinates.put(m, tmp);
                m++;
                tmp = new int[2];
            }
        }
        for (int i = 0; i < Game.getKS() * Game.getKS(); i++) {
            board[i].setX(coordinates.get(i)[0]);
            board[i].setY(coordinates.get(i)[1]);
            board[i].getVision().setX(board[i].getX());
            board[i].getVision().setY(board[i].getX());
        }
    }


    //Visions moves
    public static void move(int j, int k) {
        if (!board[j].isIsBlocked() && !board[k].isIsBlocked() && board[j].getVision().isLegal(j, k) && board[k].getVision().getColor() == Renk.Temp
                && board[j].getVision().getVisiontype() != Visions.VisionType.Bos
                && ((isFirst && board[j].getVision().getColor() == Renk.Blue) || (!isFirst && board[j].getVision().getColor() == Renk.Red))) {

            board[j].getVision().getCard().roadEffect(j, k);

            if (Game.getPressedHint() != null)
                Handler.findDirections(j, Game.getAimHint().getCoordinate());
            else
                Handler.findDirections(j, k);


            //Visions change location like "j to k"
            Animation.move(j, k);


            board[k].getVision().effect();
            Sound.playAction();

            isFirst = !isFirst;
            CardPanel.updateColor();
            board[k].getVision().getCard().range(k);
            board[k].getVision().getCard().effect();
            Handler.updateVisionsHint();
            Handler.updateCardDirection();
            int tmpB = 0, tmpR = 0;
            for (int i = 0; i < Game.getKS() * Game.getKS(); i++) {
                if (board[i].getVision().getColor() != Renk.Temp)
                    board[i].getVision().getCard().setVision(board[i].getVision());
                if (board[i].getVision().getColor() == Renk.Blue) {
                    tmpB++;
                }
                if (board[i].getVision().getColor() == Renk.Red) {
                    tmpR++;
                }
                if (board[i].isIsBlocked() == true) {
                    board[i].setBlockedRounds(board[i].getBlockedRounds() + 1);
                    if (board[i].getBlockedRounds() >= 6) {
                        board[i].setIsBlocked(false);
                    }
                }
                if (board[i].getPoison() > 0) {
                    board[i].setPoisonedRounds(board[i].getPoisonedRounds() + 1);
                    if (board[i].getPoisonedRounds() >= 6) {
                        board[i].setPoison(0);
                    }
                }
                if (board[i].getVision().getVisiontype() != Visions.VisionType.Bos && board[i].getVision().getHP() <= 0) {
                    makeHintEmpty(i);
                }
                if (board[i].getVision().getColor() != Renk.Temp) {
                    board[i].getVision().range(i);
                    if (i != k)
                        board[i].getVision().effect();
                }
            }

            if (tmpR <= 1)
                if (GameStatus.getSecondPlayersMoney() < 300) {
                    Game.setIsBlueWon(true);
                    Game.setGameState(Game.STATE.GameOver);
                }
            if (tmpB <= 1)
                if (GameStatus.getFirstPlayersMoney() < 300) {
                    Game.setIsBlueWon(false);
                    Game.setGameState(Game.STATE.GameOver);
                }
            if (isFirst) {
                GameStatus.checkEnemyInRedBase();
            } else {
                GameStatus.checkEnemyInBlueBase();
            }
            makeInvisible();
        }
    }

    //Taşı tahtada göstermek için
    public static void makeVisible(int k) {
        tmpArrayList = board[k].getVision().visibleRange(k);
        for (int j = 0; j < tmpArrayList.size(); j++) {
            if (board[k].getVision().getColor() == Renk.Blue) {
                board[tmpArrayList.get(j)].setIsVisible4Blue(true);
            } else if (board[k].getVision().getColor() == Renk.Red) {
                board[tmpArrayList.get(j)].setIsVisible4Red(true);
            }
        }
    }

    //Taşı tahtadan kaybetmek için
    public static void makeInvisible() {
        for (int i = 0; i < Game.getKS() * Game.getKS(); i++) {
            board[i].setIsVisible4Red(false);
            board[i].setIsVisible4Blue(false);
        }
        for (int i = 0; i < Visions.allVisions.size(); i++) {
            Visions.allVisions.get(i).updateHP();
            makeVisible(Visions.allVisions.get(i).getHint().getCoordinate());
        }
    }

    //Visions moves
    public static void moveFromTo(int j, int k) {
        getBoard()[k].setVision(getBoard()[j].getVision());
        getBoard()[k].getVision().setX(Board.getCoordinates().get(k)[0]);
        getBoard()[k].getVision().setY(Board.getCoordinates().get(k)[1]);
        makeHintEmpty(j);
        //Board.makeInvisible();
    }

    //Visions moves ıt was on j
    public static void makeHintEmpty(int j) {
        getBoard()[j].setVision(new EmptyVision(new EmptyCard(Renk.Temp), -1, -1));
        getBoard()[j].getVision().setX(Board.getCoordinates().get(j)[0]);
        getBoard()[j].getVision().setY(Board.getCoordinates().get(j)[1]);
    }


    public static boolean isFirst() {
        return isFirst;
    }

    public static Hint[] getBoard() {
        return board;
    }

    public static HashMap<Integer, int[]> getCoordinates() {
        return coordinates;
    }

    private static ArrayList<Integer> tmpArrayList;
    private static boolean isFirst;
    private static int[] tmp;
    private static Hint[] board;
    private static HashMap<Integer, int[]> coordinates;

    public static enum Renk {
        Blue, Red, Temp
    }

}

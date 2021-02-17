package Main;

import Main.Board.Renk;

import java.util.ArrayList;


public class GameStatus {

    public static ArrayList<Integer> moneyMaker = new ArrayList<>();
    public static ArrayList<Integer> blueBase = new ArrayList<>();
    public static ArrayList<Integer> redBase = new ArrayList<>();
    private static int countBlueBase = 0;
    private static int countRedBase = 0;
    private boolean isBlueAbleToProduce; //asker yoksa ve üretemiyorsa kaybedicek
    private boolean isRedAbleToProduce;
    private static int secondPlayersMoney = 5000;
    private static int firstPlayersMoney = 5000;

    public static void initRegions() {

        for (int i = 0; i < 6; i++) {
            for (int j = Game.getKS() * (Game.getKS() - 5 + i) - 1; j < Game.getKS() * Game.getKS(); j += Game.getKS() - 1) {
                blueBase.add(j);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = Game.getKS() * (5 - i); j >= 0; j -= (Game.getKS() - 1)) {
                redBase.add(j);
            }
        }
    }



    public static void checkEnemyInBlueBase() {
        for (int i = 0; i < blueBase.size(); i++) {
            if (Board.getBoard()[blueBase.get(i)].getVision().getColor() == Renk.Red) {
                countBlueBase++;
            }
            if (Board.getBoard()[blueBase.get(i)].getVision().getColor() == Renk.Blue) {
                Board.getBoard()[blueBase.get(i)].getVision().decreaseHP(-50);
            }
        }
        if (countBlueBase >= 5) {
            Game.setIsBlueWon(false);
            Game.setGameState(Game.STATE.GameOver);

        }
    }

    public static void checkEnemyInRedBase() {
        for (int i = 0; i < redBase.size(); i++) {
            if (Board.getBoard()[redBase.get(i)].getVision().getColor() == Renk.Blue) {
                countRedBase++;
            }
            if (Board.getBoard()[redBase.get(i)].getVision().getColor() == Renk.Red) {
                Board.getBoard()[redBase.get(i)].getVision().decreaseHP(-50);
            }
        }
        if (countRedBase >= 5) {
            Game.setIsBlueWon(true);
            Game.setGameState(Game.STATE.GameOver);
        }
    }

    public static void decreaseFirstPlayersMoney(int a) {
        firstPlayersMoney -= a;
    }

    public static void decreaseSecondPlayersMoney(int a) {
        secondPlayersMoney -= a;
    }

    public int getCountBlueBase() {
        return countBlueBase;
    }

    public int getCountRedBase() {
        return countRedBase;
    }

    public static void setFirstPlayersMoney(int a) {
        GameStatus.firstPlayersMoney = a;
    }

    public static void setSecondPlayersMoney(int a) {
        GameStatus.secondPlayersMoney = a;
    }

    public static int getFirstPlayersMoney() {
        return GameStatus.firstPlayersMoney;
    }

    public static int getSecondPlayersMoney() {
        return GameStatus.secondPlayersMoney;
    }

}

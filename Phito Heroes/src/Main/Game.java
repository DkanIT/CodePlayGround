package Main;

import GUI.*;
import GUI.Info;
import Visions.Visions;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    public static void main(String[] args) {
        new Board();
        new Game();

    }

    public enum STATE {
        Menu,
        Info,
        Credits,
        Game,
        GameOver;


    }

    public Game() {
        menu = new Menu();
        credits = new Credits();
        info = new Info();
        gameover = new GameOver();


        window = new Window(this);
        this.addMouseListener(new MouseInput());
        this.addKeyListener(new KeyInput());

        //MOVE SCREEN WITH MOUSE

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                //MOUSE AIM
                if (gameState == STATE.Game) {
                    if (sourceHint != null && Board.getBoard()[Handler.hintFinder(e.getXOnScreen(), e.getYOnScreen())].getVision().getVisiontype() == Visions.VisionType.Bos) {
                        aimHint = Board.getBoard()[Handler.hintFinder(e.getXOnScreen(), e.getYOnScreen())];
                    }
                }
            }
        });
    }

    //Update game sound for screens
    private void update() {
        window.update();
        if (gameState == STATE.Game) {
            if (sourceHint != null && sourceHint.getVision().getVisiontype() == Visions.VisionType.MainBuilding)
                CardPanel.update();
            Handler.update();
            if (Sound.isMenu) {
                Sound.stopAll();
                Sound.playGameBg();
            }
        } else if (gameState == STATE.Menu) {
            menu.update();
            if (!Sound.isMenu) {
                Sound.stopAll();
                Sound.playMenu();
            }
        } else if (gameState == STATE.Credits) {
            credits.update();
            if (!Sound.isCredits) {
                Sound.stopAll();
                Sound.playCredits();
            }

        } else if (gameState == STATE.GameOver) {
            menu.update();
            if (!Sound.isGameOver) {
                Sound.stopAll();
                Sound.PlayGameOver();
            }

        }
    }

    // get screen of cards visions and background

    // Game General renders for cases
    private void renderState(Graphics g) {
        switch (gameState) {
            case Game:
                Handler.render(g);
                g.setColor(Color.BLACK);

                // Right and left spaces
                g.fillRect(0, 0, (1920 - 1024) / 2, 1080);
                g.fillRect(0, 0, 1920, 28);
                g.fillRect((1920 - 1024) / 2 + 1024, 0, (1920 - 1024) / 2, 1080);
                g.fillRect(0, 1052, 1920, 28);
                // Right and left spaces
                g.setColor(Color.darkGray);
                g.fillRect(28, 28, 392, 1024);
                g.fillRect(1500, 28, 392, 1024);
                if (sourceHint != null && sourceHint.getVision().getVisiontype() == Visions.VisionType.MainBuilding) {
                    CardPanel.render(g);
                    Symbols.render(g);
                } else if (sourceHint != null) {
                    window.update();
                    window.render(g);
                }
                Minimap.render(g);
                break;
            case Menu:
                menu.render(g);
                break;
            case Credits:
                credits.render(g);
                break;
            case Info:
                info.render(g);
                break;
            case GameOver:
                gameover.render(g);
                break;
        }
    }

    private void render() {


        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.decode("#333333"));
        g.fillRect(0, 0, FRAME_DIMENSION.width, FRAME_DIMENSION.height);
        Animation.initGraphic(g);
        renderState(g);
        g.dispose();
        bs.show();

    }


    //Frame Rate Counter
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 180.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 180;
            }
        }
        stop();
    }

    private Menu menu;
    private Credits credits;
    private Info info;
    private GameOver gameover;
    private static STATE gameState = STATE.Menu;
    private static float ORG_Y = 483;
    private static float ORG_X = 63;
    private static float velORG_Y = 0;
    private static float velORG_X = 0;
    private static final Dimension FRAME_DIMENSION = new Dimension(1920, 1080);
    private static final int KS = 24;
    //küçültme ölçeği zoom diyebiliriz it is zoom to your map
    private static float VSK = 30;
    private static Window window;

    private static Color color1 = Color.decode("#966F33");
    private static Color color2 = Color.decode("#deb887");
    //Tales for infos ingame like road aim or someting about cards and visions for Hp
    private static Hint sourceHint, destroyHint, aimHint, cpCardHint, cpVisionHint, pressedHint;

    private boolean running = false;
    private static boolean isBlueWon;
    private int frames;
    private static Graphics g;
    private Thread thread;

    public static void setIsBlueWon(boolean isBlueWon) {
        Game.isBlueWon = isBlueWon;
    }

    public static boolean isIsBlueWon() {
        return isBlueWon;
    }

    public static void setVelORG_Y(float velORG_Y) {
        Game.velORG_Y = velORG_Y;
    }

    public static void setVelORG_X(float velORG_X) {
        Game.velORG_X = velORG_X;
    }

    public static float getVelORG_Y() {
        return velORG_Y;
    }

    public static float getVelORG_X() {
        return velORG_X;
    }

    public static Hint getPressedHint() {
        return pressedHint;
    }


    public static void setPressedHint(Hint pressedHint) {
        Game.pressedHint = pressedHint;
    }

    public static void setGameState(STATE gameState) {
        Game.gameState = gameState;
    }

    public static void setORG_Y(float ORG_Y) {
        Game.ORG_Y = ORG_Y;
    }

    public static void setORG_X(float ORG_X) {
        Game.ORG_X = ORG_X;
    }

    public static void setVKS(float VKS) {
        Game.VSK = VKS;
    }

    public static void setColor1(Color color1) {
        Game.color1 = color1;
    }


    public static void setColor2(Color color2) {
        Game.color2 = color2;
    }

    public static void setSourceHint(Hint sourceHint) {
        Game.sourceHint = sourceHint;
    }

    public static void setAimHint(Hint aimHint) {
        Game.aimHint = aimHint;
    }

    public static void setDestroyHint(Hint destroyHint) {
        Game.destroyHint = destroyHint;
    }

    public static STATE getGameState() {
        return gameState;
    }

    public static float getORG_Y() {
        return ORG_Y;
    }

    public static float getORG_X() {
        return ORG_X;
    }

    public static Dimension getFRAME_DIMENSION() {
        return FRAME_DIMENSION;
    }

    public static int getKS() {
        return KS;
    }

    public static float getVSK() {
        return VSK;
    }

    public static Color getColor1() {
        return color1;
    }

    public static Color getColor2() {
        return color2;
    }

    public static Hint getSourceHint() {
        return sourceHint;
    }

    public static Hint getCpCardHint() {
        return cpCardHint;
    }

    public static Hint getAimHint() {
        return aimHint;
    }

    public static Hint getDestroyHint() {
        return destroyHint;
    }

    public static void setCpCardHint(Hint cpCardHint) {
        Game.cpCardHint = cpCardHint;
    }

    public static void setCpVisionHint(Hint cpVisionHint) {
        Game.cpVisionHint = cpVisionHint;
    }

    public static Hint getCpVisionHint() {
        return cpVisionHint;
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
        }
    }
}

package Main;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Doğukan -Cem-Ege
public class Sound {
    
    static Thread bg;
    static Clip clipSE;
    static Clip clipBG;
    static boolean isCredits;
    static boolean isMenu;
    static boolean isGameBG;
    static boolean isGameOver;
    static File[] select;
    static File[] action;
    
    public static void init(){
        select = new File[9];
        action = new File[5];
        for(int i=0;i<9;i++)
            select[i] = new File("res/soundeffect/Select" + (i+1) + ".wav");
        for(int i=0;i<4;i++)
            action[i] = new File("res/soundeffect/attack" + (i+1) + ".wav");
    }
    
    
    public static synchronized void playSelect() {
        int i=(int)(Math.random()*9);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipSE = AudioSystem.getClip();
                    clipSE.open(AudioSystem.getAudioInputStream(select[i]));
                    clipSE.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
    
    public static synchronized void playAction() {
        int i=(int)(Math.random()*4);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipSE = AudioSystem.getClip();
                    clipSE.open(AudioSystem.getAudioInputStream(action[i]));
                    clipSE.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
    

    public static synchronized void playMenu() {
        bg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    clipBG.open(AudioSystem.getAudioInputStream(new File("res/musics/menu.wav")));
                    clipBG.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        bg.start();
        isMenu = true;
    }
    public static synchronized void playCredits() {
        bg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    clipBG.open(AudioSystem.getAudioInputStream(new File("res/musics/credits.wav")));
                    clipBG.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        bg.start();
        isCredits = true;
    }
    public static synchronized void PlayGameOver() {
        bg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    clipBG.open(AudioSystem.getAudioInputStream(new File("res/musics/vitory.wav")));
                    clipBG.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        bg.start();
        isGameOver = true;
    }

    public static synchronized void playGameBg() {
        bg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    clipBG.open(AudioSystem.getAudioInputStream(new File("res/musics/gamebg.wav")));
                    clipBG.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        bg.start();
        isGameBG = true;
    }

    public static void stopAll() {
        if(clipBG != null){
            clipBG.stop();
            isMenu = false;
            isGameBG = false;
            isCredits = false;
            isGameOver = false;
        }
        if(clipSE != null)
            clipSE.stop();
    }

}

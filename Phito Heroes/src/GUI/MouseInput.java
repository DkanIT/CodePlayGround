package GUI;

import Main.Board;
import Main.Game;
import Main.Handler;
import Main.Sound;
import Visions.Visions.VisionType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;


public class MouseInput extends MouseAdapter {

    private boolean isMiddle;
    private boolean[] keyDown = new boolean[4];
    private boolean[] wheelDown = new boolean[4];


    @Override
    public void mouseClicked(MouseEvent me) {
        int mx = me.getX(), my = me.getY();
        if (Game.getGameState() == Game.STATE.Info) {
            if (mouseOver(mx, my, Info.getCARD_PANEL_ORG().width, Info.getCARD_PANEL_ORG().height, Info.getCARD_PANEL_WIDTH(), Info.getCARD_PANEL_HEIGHT())) {
                Game.setSourceHint(Info.cardBoard.get(Info.hintFinder(mx, my)));

            }
        }
        if (Game.getGameState() == Game.STATE.Game) {
            if (mouseOver(mx, my, Minimap.getInfoOrgX(), Minimap.getInfoOrgY(), Minimap.getInfopW(), Minimap.getInfopH())) {
                Game.setGameState(Game.STATE.Info);
            }
            if (mouseOver(mx, my, CardPanel.getButOrgX(), CardPanel.getButOrgY(), CardPanel.getButW(), CardPanel.getButH())) {
                CardPanel.setIsSelected(true);
            }
            if (SwingUtilities.isLeftMouseButton(me)) {
                if (Game.getSourceHint() != null && Game.getSourceHint().getVision().getVisiontype() == VisionType.MainBuilding) {
                    if (mouseOver(mx, my, CardPanel.getCARD_PANEL_ORG().width, CardPanel.getCARD_PANEL_ORG().height, CardPanel.getCARD_PANEL_WIDTH(), CardPanel.getCARD_PANEL_HEIGHT())
                            || mouseOver(mx, my, CardPanel.getVISION_PANEL_ORG().width, CardPanel.getVISION_PANEL_ORG().height, CardPanel.getVISION_PANEL_WIDTH(), CardPanel.getVISION_PANEL_HEIGHT())) {
                        if (SwingUtilities.isLeftMouseButton(me)) {
                            if (my < CardPanel.getVISION_PANEL_ORG().height + CardPanel.getVISION_PANEL_HEIGHT()
                                    && my >= CardPanel.getVISION_PANEL_ORG().height
                                    && mx < CardPanel.getVISION_PANEL_ORG().width + CardPanel.getVISION_PANEL_WIDTH()
                                    && mx >= CardPanel.getVISION_PANEL_ORG().width) {
                                Game.setCpVisionHint(CardPanel.getVisionBoard().get(Handler.visionHintFinder(mx, my)));
                            } else if (my < CardPanel.getCARD_PANEL_ORG().height + CardPanel.getCARD_PANEL_HEIGHT()
                                    && my >= CardPanel.getCARD_PANEL_ORG().height
                                    && mx < CardPanel.getCARD_PANEL_ORG().width + CardPanel.getCARD_PANEL_WIDTH()
                                    && mx >= CardPanel.getCARD_PANEL_ORG().width) {
                                Game.setCpCardHint(CardPanel.getCardBoard().get(Handler.cardHintFinder(mx, my)));
                            }
                        }
                    } else if (mx >= 512) {
                        Game.setSourceHint(null);
                        Game.setCpCardHint(null);
                        Game.setCpVisionHint(null);
                        Game.setDestroyHint(null);
                    }
                } else if (Game.getSourceHint() == null && Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())].getVision().getColor() == (!Board.isFirst() ? Board.Renk.Red : Board.Renk.Blue)) {
                    Game.setSourceHint(Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())]);
                    Sound.playSelect();
                } else if (Game.getSourceHint() != null && Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())].getVision().getColor() == (!Board.isFirst() ? Board.Renk.Red : Board.Renk.Blue)) {
                    Game.setSourceHint(Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())]);
                    Sound.playSelect();
                } else {
                    Game.setSourceHint(null);
                    Game.setCpCardHint(null);
                    Game.setCpVisionHint(null);
                }
                Game.setDestroyHint(null);
            }
            if (SwingUtilities.isRightMouseButton(me)) {
                if (Game.getSourceHint() != null && Game.getSourceHint().getVision().isLegal(Game.getSourceHint().getCoordinate(), Handler.hintFinder(mx, my))) {

                    Game.setDestroyHint(Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())]);
                    Board.move(Game.getSourceHint().getCoordinate(), Game.getDestroyHint().getCoordinate());
                }
                Game.setSourceHint(null);
                Game.setDestroyHint(null);
                Game.setCpCardHint(null);
                Game.setCpVisionHint(null);
            }
        }
        if (SwingUtilities.isMiddleMouseButton(me)) {
            if (!isMiddle) {
                Game.setPressedHint(Board.getBoard()[Handler.hintFinder(me.getX(), me.getY())]);
            } else {
                Game.setPressedHint(null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    private boolean mouseOver(int mx, int my, int x, int y, int WIDTH, int HEIGHT) {
        return (mx > x && mx < x + WIDTH) && (my > y && my < y + HEIGHT);
    }
}

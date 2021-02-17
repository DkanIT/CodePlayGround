package Visions;

import Cards.*;

import java.util.ArrayList;


public class EmptyVision extends Visions {
    
    public EmptyVision(Cards card, float x, float y){
        super(VisionType.Bos,1,card,0);
        this.color = this.card.getColor();
        this.velX = 0;
        this.velY = 0;
        this.x = x;
        this.y = y;
    }
    @Override
    public ArrayList<Integer> visibleRange(int j){
        ArrayList<Integer> tmp = new ArrayList<>();
        return tmp;
    }
    @Override
    public boolean isLegal(int j, int k) {
        return false;
    }

}

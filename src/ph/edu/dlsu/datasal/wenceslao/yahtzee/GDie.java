package ph.edu.dlsu.datasal.wenceslao.yahtzee;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRoundRect;
import acm.util.ErrorException;
import java.awt.Color;

class GDie extends GCompound implements YahtzeeDisplayConstants{
    private int dieValue;
    private boolean highlighted;

    public GDie(){
        set(0);
    }
  
    public void set(int pips){
        this.dieValue = pips;
        removeAll();
        createOutline(this.highlighted ? PIP_COLOR : DIE_COLOR);
        if (pips == 0) {
          createQuestionMark();
        } else {
          createPips(pips);
        }
    }
  
    public void setHighlighted(boolean flag){
        this.highlighted = flag;
        set(this.dieValue);
    }
  
    public boolean isHighlighted(){
        return this.highlighted;
    }
  
    private void createOutline(Color bgcolor){
        GRoundRect outline = new GRoundRect(0.0D, 0.0D, 44.0D, 44.0D, 18.0D);
        outline.setColor(PIP_COLOR);
        outline.setFilled(true);
        outline.setFillColor(bgcolor);
        add(outline);
    }
  
    private void createQuestionMark(){
        GLabel label = new GLabel("?");
        label.setColor(PIP_COLOR);
        label.setFont(QUESTION_MARK_FONT);
        add(label, (44.0D - label.getWidth()) / 2.0D, (44.0D + label.getAscent()) / 2.0D - 1.0D);
    }
  
    private void createPips(int pips){
        switch (pips){
            case 0: 
                break;
            case 1: 
                createPip(2, 2);
                break;
            case 2: 
                createPip(3, 1);
                createPip(1, 3);
                break;
            case 3: 
                createPip(3, 1);
                createPip(2, 2);
                createPip(1, 3);
                break;
            case 4: 
                createPip(1, 1);
                createPip(1, 3);
                createPip(3, 1);
                createPip(3, 3);
                break;
            case 5: 
                createPip(1, 1);
                createPip(1, 3);
                createPip(2, 2);
                createPip(3, 1);
                createPip(3, 3);
                break;
            case 6: 
                createPip(1, 1);
                createPip(1, 2);
                createPip(1, 3);
                createPip(3, 1);
                createPip(3, 2);
                createPip(3, 3);
                break;
            default: 
                throw new ErrorException("Illegal die value = " + pips);
        }
    }
  
    private void createPip(int col, int row){
        double x = 22.0D + 0.27D * (col - 2) * 44.0D;
        double y = 22.0D + 0.27D * (row - 2) * 44.0D + 1.0D;
        GOval pip = new GOval(10.0D, 10.0D);
        pip.setFilled(true);
        pip.setColor(this.highlighted ? DIE_COLOR : PIP_COLOR);
        add(pip, x - 5.0D, y - 5.0D);
    }
}
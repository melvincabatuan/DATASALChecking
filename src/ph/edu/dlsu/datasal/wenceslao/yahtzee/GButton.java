package ph.edu.dlsu.datasal.wenceslao.yahtzee;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GButton extends GCompound implements YahtzeeDisplayConstants, MouseListener {
    private GRoundRect frame;
    private GLabel label;
    private boolean enabled;
  
    public GButton(String text){
        this.frame = new GRoundRect(85.0D, 20.0D);
        this.frame.setFilled(true);
        this.frame.setColor(Color.black);
        this.frame.setFillColor(Color.white);
        this.label = new GLabel("");
        this.label.setFont(BUTTON_FONT);
        this.label.setColor(Color.black);
        add(this.frame);
        add(this.label);
        setLabel(text);
        setEnabled(false);
        addMouseListener(this);
    }
  
    public void setLabel(String text){
        this.label.setLabel(text);
        this.label.setLocation((85.0D - this.label.getWidth()) / 2.0D, (20.0D + this.label.getAscent()) / 2.0D - 1.0D);
    }
  
    public void setEnabled(boolean flag){
        this.enabled = flag;
        this.label.setColor(this.enabled ? Color.black : Color.gray);
    }
  
    public void mousePressed(MouseEvent e){
        if (this.enabled){
            this.frame.setFillColor(PIP_COLOR);
            this.label.setColor(Color.white);
        }
    }
  
    public void mouseReleased(MouseEvent e){
        if (this.enabled){
            this.frame.setFillColor(Color.white);
            this.label.setColor(Color.black);
            fireActionEvent(this.label.getLabel());
        }
    }
  
  public void mouseClicked(MouseEvent e) {}
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
}


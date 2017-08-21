package ph.edu.dlsu.velasco.yahtzee;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class GButton
  extends GCompound
  implements YahtzeeDisplayConstants, MouseListener
{
  private GRoundRect frame;
  private GLabel label;
  private boolean enabled;
  
  public GButton(String text)
  {
    frame = new GRoundRect(85.0D, 20.0D);
    frame.setFilled(true);
    frame.setColor(Color.black);
    frame.setFillColor(Color.white);
    label = new GLabel("");
    label.setFont(BUTTON_FONT);
    label.setColor(Color.black);
    add(frame);
    add(label);
    setLabel(text);
    setEnabled(false);
    addMouseListener(this);
  }
  
  public void setLabel(String text) {
    label.setLabel(text);
    label.setLocation((85.0D - label.getWidth()) / 2.0D, 
      (20.0D + label.getAscent()) / 2.0D - 1.0D);
  }
  
  public void setEnabled(boolean flag) {
    enabled = flag;
    label.setColor(enabled ? Color.black : Color.gray);
  }
  

  public void mousePressed(MouseEvent e)
  {
    if (enabled) {
      frame.setFillColor(PIP_COLOR);
      label.setColor(Color.white);
    }
  }
  
  public void mouseReleased(MouseEvent e) {
    if (enabled) {
      frame.setFillColor(Color.white);
      label.setColor(Color.black);
      fireActionEvent(label.getLabel());
    }
  }
  
  public void mouseClicked(MouseEvent e) {}
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
}

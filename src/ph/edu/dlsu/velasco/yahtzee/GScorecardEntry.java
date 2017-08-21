package ph.edu.dlsu.velasco.yahtzee;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.util.ErrorException;
import java.awt.Color;
import java.awt.Font;


class GScorecardEntry
  extends GCompound
  implements YahtzeeDisplayConstants
{
  private GRect box;
  private GLabel label;
  private int myCategory;
  private int alignment;
  private boolean highlighted;
  private Color highlightColor;
  private Color textColor;
  private Color backgroundColor;
  
  public GScorecardEntry(double width, double height, int category)
  {
    myCategory = category;
    box = new GRect(width, height);
    box.setFilled(true);
    label = new GLabel("");
    highlighted = false;
    add(box);
    add(label);
    setHighlightColor(STANDARD_HIGHLIGHT);
    setTextColor(Color.black);
    setBackgroundColor(Color.white);
    setHighlighted(false);
    setAlignment(2);
  }
  
  public void setLabel(String str) {
    label.setLabel(str);
    double x = getXCoordinate();
    double y = (box.getHeight() + label.getAscent()) / 2.0D - 1.0D;
    label.setLocation(x, y);
    setHighlighted(highlighted);
  }
  
  public void setHighlighted(boolean flag) {
    highlighted = flag;
    if (highlighted) {
      label.setColor(highlightColor == STANDARD_HIGHLIGHT ? Color.black : Color.white);
      box.setFillColor(highlightColor);
    } else {
      label.setColor(textColor);
      box.setFillColor(backgroundColor);
    }
  }
  
  public int getCategory() {
    return myCategory;
  }
  
  public boolean isHighlighted() {
    return highlighted;
  }
  
  public void setAlignment(int align) {
    alignment = align;
  }
  
  public void setFont(Font font) {
    label.setFont(font);
  }
  
  public void setHighlightColor(Color color) {
    highlightColor = color;
  }
  
  public void setTextColor(Color color) {
    textColor = color;
  }
  
  public void setBackgroundColor(Color color) {
    backgroundColor = color;
  }
  
  private double getXCoordinate() {
    switch (alignment) {
    case 0:  return 4.0D;
    case 1:  return 18.0D;
    case 2:  return (box.getWidth() - label.getWidth()) / 2.0D;
    case 3:  return 0.65D * box.getWidth() - label.getWidth();
    }
    throw new ErrorException("Illegal alignment");
  }
}

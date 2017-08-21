/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.yahtzee;

/**
 *
 * @author RGM-96X
 */
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
    this.myCategory = category;
    this.box = new GRect(width, height);
    this.box.setFilled(true);
    this.label = new GLabel("");
    this.highlighted = false;
    add(this.box);
    add(this.label);
    setHighlightColor(STANDARD_HIGHLIGHT);
    setTextColor(Color.black);
    setBackgroundColor(Color.white);
    setHighlighted(false);
    setAlignment(2);
  }
  
  public void setLabel(String str)
  {
    this.label.setLabel(str);
    double x = getXCoordinate();
    double y = (this.box.getHeight() + this.label.getAscent()) / 2.0D - 1.0D;
    this.label.setLocation(x, y);
    setHighlighted(this.highlighted);
  }
  
  public void setHighlighted(boolean flag)
  {
    this.highlighted = flag;
    if (this.highlighted)
    {
      this.label.setColor(this.highlightColor == STANDARD_HIGHLIGHT ? Color.black : Color.white);
      this.box.setFillColor(this.highlightColor);
    }
    else
    {
      this.label.setColor(this.textColor);
      this.box.setFillColor(this.backgroundColor);
    }
  }
  
  public int getCategory()
  {
    return this.myCategory;
  }
  
  public boolean isHighlighted()
  {
    return this.highlighted;
  }
  
  public void setAlignment(int align)
  {
    this.alignment = align;
  }
  
  public void setFont(Font font)
  {
    this.label.setFont(font);
  }
  
  public void setHighlightColor(Color color)
  {
    this.highlightColor = color;
  }
  
  public void setTextColor(Color color)
  {
    this.textColor = color;
  }
  
  public void setBackgroundColor(Color color)
  {
    this.backgroundColor = color;
  }
  
  private double getXCoordinate()
  {
    switch (this.alignment)
    {
    case 0: 
      return 4.0D;
    case 1: 
      return 18.0D;
    case 2: 
      return (this.box.getWidth() - this.label.getWidth()) / 2.0D;
    case 3: 
      return 0.65D * this.box.getWidth() - this.label.getWidth();
    }
    throw new ErrorException("Illegal alignment");
  }

    void setLabel(int score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

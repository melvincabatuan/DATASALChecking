package ph.edu.dlsu.velasco.yahtzee;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.util.ErrorException;
import acm.util.JTFTools;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class YahtzeeDisplay implements YahtzeeDisplayConstants, ActionListener, MouseListener
{
  private static final int WAIT_FOR_ROLL = 1;
  private static final int WAIT_FOR_REROLL = 2;
  private static final int WAIT_FOR_CATEGORY = 3;
  private GDie[] diceArray;
  private GScorecardEntry[][] scorecard;
  private GCanvas canvas;
  private GButton rollButton;
  private GLabel messageArea;
  private int mode;
  private int selectedCategory;
  private boolean mouseDown;
  
  public YahtzeeDisplay(GCanvas gc, String[] playerNames)
  {
    canvas = gc;
    canvas.setBackground(BACKGROUND_COLOR);
    if (playerNames.length > 4) {
      throw new ErrorException("Too many players");
    }
    createButton();
    createDice();
    createScorecard(playerNames);
    createMessageArea();
  }
  

  public void waitForPlayerToClickRoll(int player)
  {
    if ((player < 0) || (player >= scorecard.length)) {
      throw new ErrorException("waitForPlayerToClickRoll: Illegal player number " + player);
    }
    mode = 1;
    rollButton.setLabel("Roll Dice");
    rollButton.setEnabled(true);
    scorecard[(player + 1)][0].setHighlighted(true);
    for (int i = 0; i < 5; i++) {
      diceArray[i].set(0);
    }
    try {
      synchronized (this) {
        wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      messageArea.setLabel("");
    }
  }
  

  public void displayDice(int[] dice)
  {
    messageArea.setLabel("");
    if (dice.length != 5) {
      throw new ErrorException("Dice array has incorrect length");
    }
    for (int i = 0; i < 5; i++) {
      diceArray[i].set(dice[i]);
      diceArray[i].setHighlighted(false);
      JTFTools.pause(100.0D);
    }
  }
  


  public void waitForPlayerToSelectDice()
  {
    mode = 2;
    rollButton.setLabel("Roll again");
    rollButton.setEnabled(true);
    try {
      synchronized (this) {
        wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      messageArea.setLabel("");
    }
  }
  

  public boolean isDieSelected(int index)
  {
    if ((index < 0) || (index >= 5)) {
      throw new ErrorException("isDieSelected: Index is out of range");
    }
    return diceArray[index].isHighlighted();
  }
  

  public int waitForPlayerToSelectCategory()
  {
    mode = 3;
    rollButton.setLabel("Roll Dice");
    rollButton.setEnabled(false);
    try {
      synchronized (this) {
        wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      messageArea.setLabel(""); }
    return selectedCategory;
  }
  

  public void updateScorecard(int category, int player, int score)
  {
    messageArea.setLabel("");
    if ((category < 0) || (category > 16)) {
      throw new ErrorException("updateScorecard: Illegal category");
    }
    if ((player < 0) || (player >= scorecard.length)) {
      throw new ErrorException("updateScorecard: Illegal player number " + player);
    }
    scorecard[(player + 1)][0].setHighlighted(false);
    scorecard[(player + 1)][(category + 1)].setLabel(""+score);
  }
  
  public void printMessage(String message)
  {
    messageArea.setLabel(message);
  }
  

  public void actionPerformed(ActionEvent e)
  {
    if ((mode == 1) || (mode == 2)) {
      synchronized (this) {
        notifyAll();
      }
    }
  }
  

  public void mouseClicked(MouseEvent e)
  {
    if ((mode == 2) && ((e.getSource() instanceof GDie))) {
      GDie die = (GDie)e.getSource();
      die.setHighlighted(!die.isHighlighted());
    }
  }
  
  public void mousePressed(MouseEvent e) {
    if ((mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
    GScorecardEntry entry = (GScorecardEntry)e.getSource();
    int category = entry.getCategory();
    if (!isSelectableCategory(category)) return;
    for (int i = 0; i < scorecard.length; i++) {
      scorecard[i][(category + 1)].setHighlighted(true);
    }
    mouseDown = true;
  }
  
  public void mouseReleased(MouseEvent e) {
    if (!mouseDown) return;
    mouseDown = false;
    if ((mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
    GScorecardEntry entry = (GScorecardEntry)e.getSource();
    if (!entry.isHighlighted()) return;
    int category = entry.getCategory();
    for (int i = 0; i < scorecard.length; i++) {
      scorecard[i][(category + 1)].setHighlighted(false);
    }
    selectedCategory = category;
    synchronized (this) {
      notifyAll();
    }
  }
  
  public void mouseEntered(MouseEvent e) {
    if (!mouseDown) return;
    if ((mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
    GScorecardEntry entry = (GScorecardEntry)e.getSource();
    int category = entry.getCategory();
    if (!isSelectableCategory(category)) return;
    for (int i = 0; i < scorecard.length; i++) {
      scorecard[i][(category + 1)].setHighlighted(true);
    }
  }
  
  public void mouseExited(MouseEvent e) {
    if (!mouseDown) return;
    if ((mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
    GScorecardEntry entry = (GScorecardEntry)e.getSource();
    int category = entry.getCategory();
    if (!isSelectableCategory(category)) return;
    for (int i = 0; i < scorecard.length; i++) {
      scorecard[i][(category + 1)].setHighlighted(false);
    }
  }
  

  private void createButton()
  {
    rollButton = new GButton("Roll Dice");
    rollButton.addActionListener(this);
    canvas.add(rollButton, 12.0D, 10.0D);
  }
  
  private void createDice() {
    diceArray = new GDie[5];
    for (int i = 0; i < 5; i++) {
      diceArray[i] = new GDie();
      diceArray[i].addMouseListener(this);
      canvas.add(diceArray[i], 33.0D, 40 + i * 50);
    }
  }
  
  private void createScorecard(String[] playerNames) {
    scorecard = new GScorecardEntry[playerNames.length + 1][18];
    double x = 110.0D;
    double y = 10.0D;
    GScorecardEntry entry = new GScorecardEntry(140.0D, 20.0D, 0);
    entry.setLabel("Category");
    canvas.add(entry, x, y);
    y += 20.0D;
    for (int c = 0; c < 17; c++) {
      entry = new GScorecardEntry(140.0D, 15.0D, c);
      if (isSelectableCategory(c)) {
        entry.setAlignment(1);
        entry.setTextColor(CATEGORY_TEXT);
        entry.addMouseListener(this);
      } else {
        entry.setAlignment(0);
        entry.setTextColor(FIXED_CATEGORY_TEXT);
      }
      entry.setFont(CATEGORY_FONT);
      entry.setHighlightColor(CATEGORY_TEXT);
      entry.setBackgroundColor(CATEGORY_BACKGROUND);
      entry.setLabel(categoryName(c));
      scorecard[0][(c + 1)] = entry;
      canvas.add(entry, x, y);
      y += 15.0D;
    }
    x = 251.0D;
    for (int i = 0; i < playerNames.length; i++) {
      y = 10.0D;
      entry = new GScorecardEntry(65.0D, 20.0D, 0);
      entry.setLabel(playerNames[i]);
      scorecard[(i + 1)][0] = entry;
      canvas.add(entry, x, y);
      y += 20.0D;
      for (int c = 0; c < 17; c++) {
        entry = new GScorecardEntry(65.0D, 15.0D, c);
        entry.setAlignment(3);
        if (isSelectableCategory(c)) {
          entry.setHighlightColor(CATEGORY_TEXT);
          entry.setFont(SCORE_FONT);
          entry.addMouseListener(this);
        } else {
          entry.setFont(FIXED_SCORE_FONT);
        }
        scorecard[(i + 1)][(c + 1)] = entry;
        canvas.add(entry, x, y);
        y += 15.0D;
      }
      x += 65.0D;
    }
  }
  
  private void createMessageArea() {
    messageArea = new GLabel("");
    messageArea.setColor(Color.white);
    messageArea.setFont(MESSAGE_FONT);
    canvas.add(messageArea, 33.0D, 315.0D);
  }
  
  private String categoryName(int category) {
    switch (category) {
    case 0:  return "Ones";
    case 1:  return "Twos";
    case 2:  return "Threes";
    case 3:  return "Fours";
    case 4:  return "Fives";
    case 5:  return "Sixes";
    case 6:  return "Upper Score";
    case 7:  return "Upper Bonus (35)";
    case 8:  return "Three of a Kind";
    case 9:  return "Four of a Kind";
    case 10:  return "Full House (25)";
    case 11:  return "Small Straight (30)";
    case 12:  return "Large Straight (40)";
    case 13:  return "Yahtzee! (50)";
    case 14:  return "Chance";
    case 15:  return "Lower Score";
    case 16:  return "TOTAL"; }
    throw new ErrorException("Illegal category");
  }
  


  private boolean isSelectableCategory(int category)
  {
    return ((category >= 0) && (category <= 5)) || ((category >= 8) && (category <= 14));
  }
}

/*     */ 
package ph.edu.dlsu.rivera.yahtzee;
import acm.graphics.GCanvas;
/*     */ import acm.graphics.GLabel;
/*     */ import acm.util.ErrorException;
/*     */ import acm.util.JTFTools;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ 
/*     */ public class YahtzeeDisplay implements YahtzeeDisplayConstants, ActionListener, MouseListener
/*     */ {
/*     */   private static final int WAIT_FOR_ROLL = 1;
/*     */   private static final int WAIT_FOR_REROLL = 2;
/*     */   private static final int WAIT_FOR_CATEGORY = 3;
/*     */   private GDie[] diceArray;
/*     */   private GScorecardEntry[][] scorecard;
/*     */   private GCanvas canvas;
/*     */   private GButton rollButton;
/*     */   private GLabel messageArea;
/*     */   private int mode;
/*     */   private int selectedCategory;
/*     */   private boolean mouseDown;
/*     */   
/*     */   public YahtzeeDisplay(GCanvas gc, String[] playerNames)
/*     */   {
/*  27 */     this.canvas = gc;
/*  28 */     this.canvas.setBackground(BACKGROUND_COLOR);
/*  29 */     if (playerNames.length > 4) {
/*  30 */       throw new ErrorException("Too many players");
/*     */     }
/*  32 */     createButton();
/*  33 */     createDice();
/*  34 */     createScorecard(playerNames);
/*  35 */     createMessageArea();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void waitForPlayerToClickRoll(int player)
/*     */   {
/*  56 */     if ((player < 0) || (player >= this.scorecard.length)) {
/*  57 */       throw new ErrorException("waitForPlayerToClickRoll: Illegal player number " + player);
/*     */     }
/*  59 */     this.mode = 1;
/*  60 */     this.rollButton.setLabel("Roll Dice");
/*  61 */     this.rollButton.setEnabled(true);
/*  62 */     this.scorecard[(player + 1)][0].setHighlighted(true);
/*  63 */     for (int i = 0; i < 5; i++) {
/*  64 */       this.diceArray[i].set(0);
/*     */     }
/*     */     try {
/*  67 */       synchronized (this) {
/*  68 */         wait();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/*  73 */       this.messageArea.setLabel("");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void displayDice(int[] dice)
/*     */   {
/*  90 */     this.messageArea.setLabel("");
/*  91 */     if (dice.length != 5) {
/*  92 */       throw new ErrorException("Dice array has incorrect length");
/*     */     }
/*  94 */     for (int i = 0; i < 5; i++) {
/*  95 */       this.diceArray[i].set(dice[i]);
/*  96 */       this.diceArray[i].setHighlighted(false);
/*  97 */       JTFTools.pause(100.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void waitForPlayerToSelectDice()
/*     */   {
/* 117 */     this.mode = 2;
/* 118 */     this.rollButton.setLabel("Roll again");
/* 119 */     this.rollButton.setEnabled(true);
/*     */     try {
/* 121 */       synchronized (this) {
/* 122 */         wait();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/* 127 */       this.messageArea.setLabel("");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDieSelected(int index)
/*     */   {
/* 141 */     if ((index < 0) || (index >= 5)) {
/* 142 */       throw new ErrorException("isDieSelected: Index is out of range");
/*     */     }
/* 144 */     return this.diceArray[index].isHighlighted();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int waitForPlayerToSelectCategory()
/*     */   {
/* 167 */     this.mode = 3;
/* 168 */     this.rollButton.setLabel("Roll Dice");
/* 169 */     this.rollButton.setEnabled(false);
/*     */     try {
/* 171 */       synchronized (this) {
/* 172 */         wait();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/* 177 */       this.messageArea.setLabel(""); }
/* 178 */     return this.selectedCategory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateScorecard(int category, int player, int score)
/*     */   {
/* 197 */     this.messageArea.setLabel("");
/* 198 */     if ((category < 0) || (category > 16)) {
/* 199 */       throw new ErrorException("updateScorecard: Illegal category");
/*     */     }
/* 201 */     if ((player < 0) || (player >= this.scorecard.length)) {
/* 202 */       throw new ErrorException("updateScorecard: Illegal player number " + player);
/*     */     }
/* 204 */     this.scorecard[(player + 1)][0].setHighlighted(false);
/* 205 */     this.scorecard[(player + 1)][(category + 1)].setLabel(score+"");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printMessage(String message)
/*     */   {
/* 217 */     this.messageArea.setLabel(message);
/*     */   }
/*     */   
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 223 */     if ((this.mode == 1) || (this.mode == 2)) {
/* 224 */       synchronized (this) {
/* 225 */         notifyAll();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/* 233 */     if ((this.mode == 2) && ((e.getSource() instanceof GDie))) {
/* 234 */       GDie die = (GDie)e.getSource();
/* 235 */       die.setHighlighted(!die.isHighlighted());
/*     */     }
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 240 */     if ((this.mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
/* 241 */     GScorecardEntry entry = (GScorecardEntry)e.getSource();
/* 242 */     int category = entry.getCategory();
/* 243 */     if (!isSelectableCategory(category)) return;
/* 244 */     for (int i = 0; i < this.scorecard.length; i++) {
/* 245 */       this.scorecard[i][(category + 1)].setHighlighted(true);
/*     */     }
/* 247 */     this.mouseDown = true;
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 251 */     if (!this.mouseDown) return;
/* 252 */     this.mouseDown = false;
/* 253 */     if ((this.mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
/* 254 */     GScorecardEntry entry = (GScorecardEntry)e.getSource();
/* 255 */     if (!entry.isHighlighted()) return;
/* 256 */     int category = entry.getCategory();
/* 257 */     for (int i = 0; i < this.scorecard.length; i++) {
/* 258 */       this.scorecard[i][(category + 1)].setHighlighted(false);
/*     */     }
/* 260 */     this.selectedCategory = category;
/* 261 */     synchronized (this) {
/* 262 */       notifyAll();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/* 267 */     if (!this.mouseDown) return;
/* 268 */     if ((this.mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
/* 269 */     GScorecardEntry entry = (GScorecardEntry)e.getSource();
/* 270 */     int category = entry.getCategory();
/* 271 */     if (!isSelectableCategory(category)) return;
/* 272 */     for (int i = 0; i < this.scorecard.length; i++) {
/* 273 */       this.scorecard[i][(category + 1)].setHighlighted(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 278 */     if (!this.mouseDown) return;
/* 279 */     if ((this.mode != 3) || (!(e.getSource() instanceof GScorecardEntry))) return;
/* 280 */     GScorecardEntry entry = (GScorecardEntry)e.getSource();
/* 281 */     int category = entry.getCategory();
/* 282 */     if (!isSelectableCategory(category)) return;
/* 283 */     for (int i = 0; i < this.scorecard.length; i++) {
/* 284 */       this.scorecard[i][(category + 1)].setHighlighted(false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void createButton()
/*     */   {
/* 291 */     this.rollButton = new GButton("Roll Dice");
/* 292 */     this.rollButton.addActionListener(this);
/* 293 */     this.canvas.add(this.rollButton, 12.0D, 10.0D);
/*     */   }
/*     */   
/*     */   private void createDice() {
/* 297 */     this.diceArray = new GDie[5];
/* 298 */     for (int i = 0; i < 5; i++) {
/* 299 */       this.diceArray[i] = new GDie();
/* 300 */       this.diceArray[i].addMouseListener(this);
/* 301 */       this.canvas.add(this.diceArray[i], 33.0D, 40 + i * 50);
/*     */     }
/*     */   }
/*     */   
/*     */   private void createScorecard(String[] playerNames) {
/* 306 */     this.scorecard = new GScorecardEntry[playerNames.length + 1][18];
/* 307 */     double x = 110.0D;
/* 308 */     double y = 10.0D;
/* 309 */     GScorecardEntry entry = new GScorecardEntry(140.0D, 20.0D, 0);
/* 310 */     entry.setLabel("Category");
/* 311 */     this.canvas.add(entry, x, y);
/* 312 */     y += 20.0D;
/* 313 */     for (int c = 0; c < 17; c++) {
/* 314 */       entry = new GScorecardEntry(140.0D, 15.0D, c);
/* 315 */       if (isSelectableCategory(c)) {
/* 316 */         entry.setAlignment(1);
/* 317 */         entry.setTextColor(CATEGORY_TEXT);
/* 318 */         entry.addMouseListener(this);
/*     */       } else {
/* 320 */         entry.setAlignment(0);
/* 321 */         entry.setTextColor(FIXED_CATEGORY_TEXT);
/*     */       }
/* 323 */       entry.setFont(CATEGORY_FONT);
/* 324 */       entry.setHighlightColor(CATEGORY_TEXT);
/* 325 */       entry.setBackgroundColor(CATEGORY_BACKGROUND);
/* 326 */       entry.setLabel(categoryName(c));
/* 327 */       this.scorecard[0][(c + 1)] = entry;
/* 328 */       this.canvas.add(entry, x, y);
/* 329 */       y += 15.0D;
/*     */     }
/* 331 */     x = 251.0D;
/* 332 */     for (int i = 0; i < playerNames.length; i++) {
/* 333 */       y = 10.0D;
/* 334 */       entry = new GScorecardEntry(65.0D, 20.0D, 0);
/* 335 */       entry.setLabel(playerNames[i]);
/* 336 */       this.scorecard[(i + 1)][0] = entry;
/* 337 */       this.canvas.add(entry, x, y);
/* 338 */       y += 20.0D;
/* 339 */       for (int c = 0; c < 17; c++) {
/* 340 */         entry = new GScorecardEntry(65.0D, 15.0D, c);
/* 341 */         entry.setAlignment(3);
/* 342 */         if (isSelectableCategory(c)) {
/* 343 */           entry.setHighlightColor(CATEGORY_TEXT);
/* 344 */           entry.setFont(SCORE_FONT);
/* 345 */           entry.addMouseListener(this);
/*     */         } else {
/* 347 */           entry.setFont(FIXED_SCORE_FONT);
/*     */         }
/* 349 */         this.scorecard[(i + 1)][(c + 1)] = entry;
/* 350 */         this.canvas.add(entry, x, y);
/* 351 */         y += 15.0D;
/*     */       }
/* 353 */       x += 65.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   private void createMessageArea() {
/* 358 */     this.messageArea = new GLabel("");
/* 359 */     this.messageArea.setColor(Color.white);
/* 360 */     this.messageArea.setFont(MESSAGE_FONT);
/* 361 */     this.canvas.add(this.messageArea, 33.0D, 315.0D);
/*     */   }
/*     */   
/*     */   private String categoryName(int category) {
/* 365 */     switch (category) {
/* 366 */     case 0:  return "Ones";
/* 367 */     case 1:  return "Twos";
/* 368 */     case 2:  return "Threes";
/* 369 */     case 3:  return "Fours";
/* 370 */     case 4:  return "Fives";
/* 371 */     case 5:  return "Sixes";
/* 372 */     case 6:  return "Upper Score";
/* 373 */     case 7:  return "Upper Bonus (35)";
/* 374 */     case 8:  return "Three of a Kind";
/* 375 */     case 9:  return "Four of a Kind";
/* 376 */     case 10:  return "Full House (25)";
/* 377 */     case 11:  return "Small Straight (30)";
/* 378 */     case 12:  return "Large Straight (40)";
/* 379 */     case 13:  return "Yahtzee! (50)";
/* 380 */     case 14:  return "Chance";
/* 381 */     case 15:  return "Lower Score";
/* 382 */     case 16:  return "TOTAL"; }
/* 383 */     throw new ErrorException("Illegal category");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isSelectableCategory(int category)
/*     */   {
/* 390 */     return ((category >= 0) && (category <= 5)) || ((category >= 8) && (category <= 14));
/*     */   }
/*     */ }


/* Location:              A:\mave documents\term3 2016- 2017\LBYCP12\Assignment5\yahtzeelib.jar!\YahtzeeDisplay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
package ph.edu.dlsu.rivera.yahtzee;
/*     */ import acm.graphics.GCompound;
/*     */ import acm.graphics.GLabel;
/*     */ import acm.graphics.GOval;
/*     */ import acm.graphics.GRoundRect;
/*     */ import acm.util.ErrorException;
/*     */ import java.awt.Color;
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
/*     */ class GDie
/*     */   extends GCompound
/*     */   implements YahtzeeDisplayConstants
/*     */ {
/*     */   private int dieValue;
/*     */   private boolean highlighted;
/*     */   
/*     */   public GDie()
/*     */   {
/* 413 */     set(0);
/*     */   }
/*     */   
/*     */   public void set(int pips) {
/* 417 */     this.dieValue = pips;
/* 418 */     removeAll();
/* 419 */     createOutline(this.highlighted ? PIP_COLOR : DIE_COLOR);
/* 420 */     if (pips == 0) {
/* 421 */       createQuestionMark();
/*     */     } else {
/* 423 */       createPips(pips);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setHighlighted(boolean flag) {
/* 428 */     this.highlighted = flag;
/* 429 */     set(this.dieValue);
/*     */   }
/*     */   
/*     */   public boolean isHighlighted() {
/* 433 */     return this.highlighted;
/*     */   }
/*     */   
/*     */   private void createOutline(Color bgcolor) {
/* 437 */     GRoundRect outline = new GRoundRect(0.0D, 0.0D, 44.0D, 44.0D, 18.0D);
/* 438 */     outline.setColor(PIP_COLOR);
/* 439 */     outline.setFilled(true);
/* 440 */     outline.setFillColor(bgcolor);
/* 441 */     add(outline);
/*     */   }
/*     */   
/*     */   private void createQuestionMark() {
/* 445 */     GLabel label = new GLabel("?");
/* 446 */     label.setColor(PIP_COLOR);
/* 447 */     label.setFont(QUESTION_MARK_FONT);
/* 448 */     add(label, (44.0D - label.getWidth()) / 2.0D, (44.0D + label.getAscent()) / 2.0D - 1.0D);
/*     */   }
/*     */   
/*     */   private void createPips(int pips) {
/* 452 */     switch (pips) {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/* 456 */       createPip(2, 2);
/* 457 */       break;
/*     */     case 2: 
/* 459 */       createPip(3, 1);
/* 460 */       createPip(1, 3);
/* 461 */       break;
/*     */     case 3: 
/* 463 */       createPip(3, 1);
/* 464 */       createPip(2, 2);
/* 465 */       createPip(1, 3);
/* 466 */       break;
/*     */     case 4: 
/* 468 */       createPip(1, 1);
/* 469 */       createPip(1, 3);
/* 470 */       createPip(3, 1);
/* 471 */       createPip(3, 3);
/* 472 */       break;
/*     */     case 5: 
/* 474 */       createPip(1, 1);
/* 475 */       createPip(1, 3);
/* 476 */       createPip(2, 2);
/* 477 */       createPip(3, 1);
/* 478 */       createPip(3, 3);
/* 479 */       break;
/*     */     case 6: 
/* 481 */       createPip(1, 1);
/* 482 */       createPip(1, 2);
/* 483 */       createPip(1, 3);
/* 484 */       createPip(3, 1);
/* 485 */       createPip(3, 2);
/* 486 */       createPip(3, 3);
/* 487 */       break;
/*     */     default: 
/* 489 */       throw new ErrorException("Illegal die value = " + pips);
/*     */     }
/*     */   }
/*     */   
/*     */   private void createPip(int col, int row) {
/* 494 */     double x = 22.0D + 0.27D * (col - 2) * 44.0D;
/* 495 */     double y = 22.0D + 0.27D * (row - 2) * 44.0D + 1.0D;
/* 496 */     GOval pip = new GOval(10.0D, 10.0D);
/* 497 */     pip.setFilled(true);
/* 498 */     pip.setColor(this.highlighted ? DIE_COLOR : PIP_COLOR);
/* 499 */     add(pip, x - 5.0D, y - 5.0D);
/*     */   }
/*     */ }


/* Location:              A:\mave documents\term3 2016- 2017\LBYCP12\Assignment5\yahtzeelib.jar!\GDie.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
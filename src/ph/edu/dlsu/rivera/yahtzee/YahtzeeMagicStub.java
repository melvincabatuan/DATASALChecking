package ph.edu.dlsu.rivera.yahtzee;
/*    */ import acm.util.ErrorException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class YahtzeeMagicStub
/*    */   implements YahtzeeConstants
/*    */ {
/*    */   public static boolean checkCategory(int[] dice, int category)
/*    */   {
/* 25 */     if (dice.length != 5) {
/* 26 */       throw new ErrorException("checkCategory: Illegal number of dice");
/*    */     }
/* 28 */     int[] counts = new int[7];
/* 29 */     int maxCount = 0;
/* 30 */     for (int i = 0; i < 5; i++) {
/* 31 */       if ((dice[i] < 1) || (dice[i] > 6)) {
/* 32 */         throw new ErrorException("checkCategory: Illegal die value");
/*    */       }
/* 34 */       counts[dice[i]] += 1;
/* 35 */       maxCount = Math.max(maxCount, counts[dice[i]]);
/*    */     }
/* 37 */     switch (category) {
/* 38 */     case 0:  return true;
/* 39 */     case 1:  return true;
/* 40 */     case 2:  return true;
/* 41 */     case 3:  return true;
/* 42 */     case 4:  return true;
/* 43 */     case 5:  return true;
/* 44 */     case 8:  return maxCount >= 3;
/* 45 */     case 9:  return maxCount >= 4;
/*    */     case 10: 
/* 47 */       if (maxCount != 3) return false;
/* 48 */       for (int pips = 1; pips <= 6; pips++) {
/* 49 */         if (counts[pips] == 2) return true;
/*    */       }
/* 51 */       return false;
/*    */     case 11: 
/* 53 */       if ((counts[1] > 0) && (counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0)) return true;
/* 54 */       if ((counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0)) return true;
/* 55 */       if ((counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0) && (counts[6] > 0)) return true;
/* 56 */       return false;
/*    */     case 12: 
/* 58 */       if ((counts[1] > 0) && (counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0)) return true;
/* 59 */       if ((counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0) && (counts[6] > 0)) return true;
/* 60 */       return false;
/* 61 */     case 13:  return maxCount == 5;
/* 62 */     case 14:  return true; }
/* 63 */     throw new ErrorException("checkCategory: Illegal category");
/*    */   }
/*    */ }


/* Location:              A:\mave documents\term3 2016- 2017\LBYCP12\Assignment5\yahtzeelib.jar!\YahtzeeMagicStub.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
package ph.edu.dlsu.datasal.DELA_PENA.yahtzee;

import acm.util.ErrorException;

public class YahtzeeMagicStub
  implements YahtzeeConstants
{
  public static boolean checkCategory(int[] dice, int category)
  {
    if (dice.length != 5) {
      throw new ErrorException("checkCategory: Illegal number of dice");
    }
    int[] counts = new int[7];
    int maxCount = 0;
    for (int i = 0; i < 5; i++)
    {
      if ((dice[i] < 1) || (dice[i] > 6)) {
        throw new ErrorException("checkCategory: Illegal die value");
      }
      counts[dice[i]] += 1;
      maxCount = Math.max(maxCount, counts[dice[i]]);
    }
    switch (category)
    {
    case 0: 
      return true;
    case 1: 
      return true;
    case 2: 
      return true;
    case 3: 
      return true;
    case 4: 
      return true;
    case 5: 
      return true;
    case 8: 
      return maxCount >= 3;
    case 9: 
      return maxCount >= 4;
    case 10: 
      if (maxCount != 3) {
        return false;
      }
      for (int pips = 1; pips <= 6; pips++) {
        if (counts[pips] == 2) {
          return true;
        }
      }
      return false;
    case 11: 
      if ((counts[1] > 0) && (counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0)) {
        return true;
      }
      if ((counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0)) {
        return true;
      }
      if ((counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0) && (counts[6] > 0)) {
        return true;
      }
      return false;
    case 12: 
      if ((counts[1] > 0) && (counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0)) {
        return true;
      }
      if ((counts[2] > 0) && (counts[3] > 0) && (counts[4] > 0) && (counts[5] > 0) && (counts[6] > 0)) {
        return true;
      }
      return false;
    case 13: 
      return maxCount == 5;
    case 14: 
      return true;
    }
    throw new ErrorException("checkCategory: Illegal category");
  }
}

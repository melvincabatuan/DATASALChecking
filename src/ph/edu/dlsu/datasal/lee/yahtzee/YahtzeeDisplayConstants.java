package ph.edu.dlsu.datasal.lee.yahtzee;

import java.awt.Color;
import java.awt.Font;

abstract interface YahtzeeDisplayConstants extends YahtzeeConstants
{
  public static final int MESSAGE_HEIGHT = 20;
  public static final int LEFT_SIDE_WIDTH = 110;
  public static final int N_CATEGORIES = 17;
  public static final int PAUSE_TIME = 100;
  public static final Color BACKGROUND_COLOR = new Color(3381555);
  public static final Color DIE_COLOR = new Color(15658734);
  public static final Color PIP_COLOR = new Color(153);
  public static final Color CATEGORY_TEXT = new Color(10027059);
  public static final Color CATEGORY_BACKGROUND = new Color(16777164);
  public static final Color FIXED_CATEGORY_TEXT = new Color(7829367);
  public static final Color FIXED_CATEGORY_BACKGROUND = new Color(14540253);
  public static final Color STANDARD_HIGHLIGHT = Color.yellow;
  public static final int BUTTON_WIDTH = 85;
  public static final int BUTTON_HEIGHT = 20;
  public static final int BUTTON_X = 12;
  public static final int BUTTON_Y = 10;
  public static final int BUTTON_SEP = 10;
  public static final Font BUTTON_FONT = new Font("Dialog", 0, 12);
  public static final int DICE_SIZE = 44;
  public static final int DICE_X = 33;
  public static final int DICE_Y = 40;
  public static final int DICE_SEP = 6;
  public static final int DICE_CORNER_RADIUS = 9;
  public static final int PIP_RADIUS = 5;
  public static final Font QUESTION_MARK_FONT = new Font("Helvetica", 0, 36);
  public static final int MESSAGE_X = 33;
  public static final int MESSAGE_Y = 315;
  public static final Font MESSAGE_FONT = new Font("Helvetica", 0, 12);
  public static final int SCORECARD_X = 110;
  public static final int SCORECARD_Y = 10;
  public static final int SCORECARD_MARGIN = 4;
  public static final int CATEGORY_WIDTH = 140;
  public static final int CATEGORY_HEIGHT = 15;
  public static final int CATEGORY_INDENT = 14;
  public static final int PLAYER_WIDTH = 65;
  public static final int PLAYER_HEIGHT = 20;
  public static final int DIVIDER_SIZE = 1;
  public static final Font CATEGORY_FONT = new Font("Helvetica", 1, 10);
  public static final Font SCORE_FONT = new Font("Helvetica", 0, 10);
  public static final Font FIXED_SCORE_FONT = new Font("Helvetica", 1, 10);
  public static final Font TITLE_FONT = new Font("Helvetica", 0, 12);
  public static final int LEFT = 0;
  public static final int INDENTED = 1;
  public static final int CENTERED = 2;
  public static final int NUMERIC = 3;
}
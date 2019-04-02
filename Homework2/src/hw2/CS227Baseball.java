package hw2;


public class CS227Baseball
{
	/**
	 * Simplified model of American baseball.
	 * @author Eric Marcanio
	 */
  
   //Constant indicating that a pitch results in a ball.
  public static final int BALL = 0;
   //Constant indicating that a pitch results in a strike. 
  public static final int STRIKE = 1;
   //Constant indicating that a pitch results in an out from a fly ball.
  public static final int POP_FLY = 2;
   // Number of strikes causing a player to be out.
  public static final int MAX_STRIKES = 3;
   // Number of balls causing a player to walk.
  public static final int MAX_BALLS = 4;
   // Number of outs before the teams switch.
  public static final int MAX_OUTS = 3;
  //These are the two teams score. the are updated by newScore method
  private int team0Score;
  private int team1Score;
  //inning is the current inning and total is when the game will be over 
  private int totalInnings;
  private int inning;
  private boolean top;
  //These are the current values of strikes, outs, balls
  private int strikes;
  private int outs;
  private int balls;
 //These are used to see if someone is on a base
  private boolean onFirst;
  private boolean onSecond;
  private boolean onThird;
  
 
  
  /**
   * Constructs a game that has the given number of innings and starts
   * at the top of inning 1.
   * @param giventotalInnings
   *   number of innings for the game
   */
  public CS227Baseball(int giventotalInnings)
  {
	totalInnings = giventotalInnings;
    inning = 1;
    
    top = true;
  }
  
  
  
  //returns true if there is a player on first, false otherwise
  public boolean playerOnFirst()
  {
    return onFirst;
  }
  
   //true if there is a player on second, false otherwise 
  public boolean playerOnSecond()
  {
    return onSecond;
  }
  
   // true if there is a player on third, false otherwise
  public boolean playerOnThird()
  {
    return onThird;
  }
  
   //returns current inning, or the number of innings plus one if the game is over
  public int getInning()
  {
    return inning;
  }
  
   //true if it's the first half of the inning, false otherwise
  public boolean isTop()
  {
    return top;
  }
  
  // returns current number of outs
  public int getOuts()
  {
    return outs;
  }
 
   //returns current number of strikes
  public int getStrikes()
  {
    return strikes;
  }
  
   //returns current number of balls
  public int getBalls()
  {
    return balls;
  }
//returns false is the innings have not reached the max amount of innings
  public boolean isOver()
  {
    return inning > totalInnings;
  }
  
  

  
  /**
   *Pitch not resulting in a hit.
   * @param outcome
   *   one of the three constants STRIKE, BALL, or POP_FLY
   */
  public void pitch(int outcome)
  {
	 //Run is over to make sure we do not go over the total innings
    if (isOver()) return;
    //The ball outcome adds 1 to ball or advances the runner to the next base if it reached MAX_BALLS
    
    if (outcome == BALL)
    {
      balls += 1;
      if (balls == MAX_BALLS)
      {
    	balls= 0;
        advanceRunners(true);
      }
    }
    //The popfly is an automatic out and checks to see if outs have reached MAX_OUTS
    else if (outcome == POP_FLY)
    {
      strikes =0;
      outs += 1;
      if (outs == MAX_OUTS)
      {
        switchTeams();
      }
    }
    //Strike just adds one to strike and checks to see if we need to switch teams 
    else if (outcome == STRIKE)
    {
      strikes += 1;
      if (strikes == MAX_STRIKES)
      {
        outs += 1;
        strikes = 0;
      }
      if (outs == MAX_OUTS)
      {
        switchTeams();
      }
    }
   
    
  }
  

  /**
   *   Pitch resulting in a hit where no player is out.  The argument for 
   *   <code>numBases</code> should be 1, 2, 3, or 4, indicating a single, 
   *   double, triple, or home run.  The batter and all players currently on 
   *   base must advance the given number of bases (possibly resulting in one 
   *   or more runs).  This method does nothing if the game is over or if the 
   *   argument is not one of the values 1, 2, 3,  or 4.
   * @param numBases
   *   number of bases that the batter and all players on base will advance
   */
  public void pitchWithHit(int numBases)
  {
	if (isOver()) return;
	//new batter
    balls =0;
	strikes =0;
	
	//since they got a hit they would be moving to first
    advanceRunners(true);
    //This if statement will move the players in the amount of spots that a single, double, or triple would 
    
    
    if (numBases >1)
    	advanceRunners(false);
    if(numBases > 2) {
    	advanceRunners(false);
    }
    if(numBases > 3) {
    	advanceRunners(false);
    	
    }
    	
    	
  }
  /**
   * Pitch resulting in a hit and a possible out. Advancement of runners is the same as pitchWithHit() except that it might cause a player to be out. The argument for whichBaseFielded indicates the base to which the ball is fielded (1, 2, 3, or 4). If the batter or one of the players on base arrives at the base whichBaseFielded after advancing exactly numBases, then that player is out. That is, we assume that the time it takes to field the ball to the indicated base is the same as the time it takes the runners to advance numBase bases. If a player is put out when advancing to home, that run is not counted. (Note that at most one out is possible, i.e. there are no double or triple plays. Also, there are no special exceptions for counting runs when it's the third out.) This method does nothing if the game is over or if either argument is not one of the values 1, 2, 3, or 4.
   * @param numBases - {@link #advanceRunners(boolean)}of bases that the batter and all players will advnace
   * @param whichBaseFielded - the base to which the defense fields the ball
   */

  public void pitchWithHitAndOut(int numBases, int whichBaseFielded)
  {
	if (isOver()) return;
    boolean outAtHome = false;
    
    
    
    if (whichBaseFielded == 4)
    {
    outAtHome = (numBases == 1 && onThird) || (numBases == 2 && onSecond); //||(numBases == 3 && onFirst) || (numBases == 4);
    }   
    pitchWithHit(numBases);
    
    if (whichBaseFielded == 1 && onFirst)
   {
      onFirst = false;
      outs +=1;
    }
   else if (whichBaseFielded == 2 && onSecond)
    {
      onSecond = false;
      outs += 1;
    }
    else if (whichBaseFielded == 3 && onThird)
    {
      onThird = false;
      outs += 1;
    }
    else if (whichBaseFielded == 4 && outAtHome)
    {
      outs+=1;
      //Need to subtract a point because he is out and my other method would add a point 
      newScore(-1);
    }
    if (outs == MAX_OUTS)
    {
      switchTeams();
    }
  }
  /**
   * Advances all players on base by one base, updating the score if there is currently a player on third. If the argument newPlayerOnFirst is true, a player (i.e. the batter) is placed on first base.
   * @param newPlayerOnFirst - if true, there will be a runner on first after this method executes
   */
  public void advanceRunners(boolean newPlayerOnFirst)
  {
	//advance people bases by 1. start with third and move back
    if (onThird)
    {
      onThird = false;
      newScore(1);
    }
    if (onSecond)
    {
      onSecond = false;
      onThird = true;
    }
    if (onFirst)
    {
      onFirst = false;
      onSecond = true;
    }
    if (newPlayerOnFirst)
    {
      onFirst = true;
    }
    
  }

  
 /**
  * A helper method used to switch the teams when max outs is reached 
  * Resets all the values back to 0 and false so the new team has a blank field
  */
  private void switchTeams()
  {
	onFirst= false;
	onSecond = false;
	onThird = false;
	strikes = 0;
    outs = 0;
    balls = 0;
    if (top)    
      top = false;
    else
    {
      top = true;
      inning += 1;
    }    
  }
  /**
   * used to get the score of both teams
   * @param team0 - if false it is team1
   * @return the team score you asked for
   */
  public int getScore(boolean team0)
  {
    if (team0)
    return team0Score;
    else
    return team1Score;
    
  }
  /**
   * used to update the score. determines the team from top and bottom
   * @param value - how much is going to be added to score
   */
  
  private void newScore(int value)
  {
    if (top)
      team0Score += value;
    else
      team1Score += value;
  }
  
  /**
   * Returns a three-character string representing the players on base, 
   * in the order first, second, and third, where 'X' indicates a player 
   * is present and 'o' indicates no player.  For example, the string "XXo" 
   * means that there are players on first and second but not on third.
   * @return
   *   three-character string showing players on base
   */
  public String getBases()
  {
    return (playerOnFirst() ? "X" : "o") +
        (playerOnSecond() ? "X" : "o") +
        (playerOnThird() ? "X" : "o");
  }

  /**
   * Returns a one-line string representation of the current game state.
   * The format is:
   * <pre>
   *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
   * </pre>
   * The first three characters represent the players on base as returned by 
   * the <code>getBases()</code> method. The 'T' after the inning number indicates 
   * it's the top of the inning, and a 'B' would indicate the bottom.
   * 
   * @return
   *   one-line string representation of the game state
   */
  public String toString()
  {
    String bases = getBases();
    String topOrBottom = (isTop() ? "T" : "B");
    String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
    return String.format(fmt, bases, getInning(), topOrBottom, getScore(true), getScore(false), getBalls(), getStrikes(), getOuts());
  }

  /**
   * Returns a multi-line string representation of the current game state.
   * The format is:  
   * <pre>
   *     o - o    Inning:1 (T)
   *     |   |    Score:0-0
   *     o - H    Balls:0 Strikes:0 Outs:0
   * </pre>
   * The 'T' after the inning number indicates it's the top of the inning, 
   * and a 'B' would indicate the bottom.
   * @return
   *   multi-line string representation of current game state
   */
  public String toDisplayString()
  {
    String firstChar = (playerOnFirst() ? "X" : "o");
    String secondChar = (playerOnSecond() ? "X" : "o");
    String thirdChar = (playerOnThird() ? "X" : "o");
    String topOrBottom = (isTop() ? "T" : "B");
    String firstLine = String.format("%s - %s    Inning:%d (%s)\n", secondChar, firstChar, getInning(), topOrBottom);
    String secondLine = String.format("|   |    Score:%d-%d\n", getScore(true), getScore(false));
    String thirdLine = String.format("%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar, getBalls(), getStrikes(), getOuts());
    return firstLine + secondLine + thirdLine;   
  }
  
}
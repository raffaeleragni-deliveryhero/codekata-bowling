package bowling;

public class BowlingGame {

  private int score;
  
  public int score() {
    return score;
  }

  public void hit(int hits) {
    if(hits < 0) throw new IllegalArgumentException("The score can't be negative!");
    if(hits > 10) throw new IllegalArgumentException("The score can't be positive!");

    score += hits;
  }

}

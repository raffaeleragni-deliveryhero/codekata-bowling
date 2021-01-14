package bowling;

public class BowlingGame {

  private int score;
  
  public int score() {
    return score;
  }

  public void hit(int hits) {
    score = hits;
  }
  
}

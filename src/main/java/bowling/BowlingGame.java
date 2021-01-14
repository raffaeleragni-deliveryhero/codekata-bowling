package bowling;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {

  boolean firstStrike = true;
  private List<Integer> hitHistory = new LinkedList<>();
  private boolean isStrike;

  public int score() {
    var total = 0;
    for (int hit: hitHistory)
      total += hit;
    return total;
  }

  public void hit(int hit) {
    if(hit < 0) throw new IllegalArgumentException("The score can't be negative!");
    if(hit > 10) throw new IllegalArgumentException("The score can't be positive!");

    if (isTurnHitsAbove10(hit))
      throw new IllegalArgumentException();

    isStrike = (hit == 10);
    
    hitHistory.add(hit);
    firstStrike = !firstStrike;
  }

  boolean isTurnHitsAbove10(int hit) {
    int lastScore = getLastScore();
    return lastScore + hit > 10;
  }

  int getLastScore() {
    if (firstStrike)
      return 0;
    int lastScore = 0;
    if (hitHistory.size() > 0)
      lastScore = hitHistory.get(hitHistory.size() - 1);
    return lastScore;
  }

  public boolean isStrike() {
    return isStrike;
  }

}

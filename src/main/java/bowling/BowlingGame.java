package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BowlingGame {

  boolean firstStrike = true;
  private List<Integer> hitHistory = new LinkedList<>();
  private boolean isStrike;

  public int score() {
    var total = 0;
    for (int i = 0; i < hitHistory.size(); i++) {
      int hit = hitHistory.get(i);
      total += hit;
      
      if (isStrike(hit)) {
        total += safeGet(i + 1).orElse(0);
        total += safeGet(i + 2).orElse(0);
      }
    }
    return total;
  }
  
  private Optional<Integer> safeGet(int index) {
    if (index > hitHistory.size() - 1) {
      return Optional.empty();
    }
    
    return Optional.of(hitHistory.get(index));
  }

  public void hit(int hit) {
    if(hit < 0) throw new IllegalArgumentException("The score can't be negative!");
    if(hit > 10) throw new IllegalArgumentException("The score can't be positive!");

    if (isTurnHitsAbove10(hit))
      throw new IllegalArgumentException();

    isStrike = isStrike(hit);
    
    hitHistory.add(hit);
    firstStrike = !firstStrike;
  }

  private boolean isStrike(int hit) {
    return hit == 10;
  }

  boolean isTurnHitsAbove10(int hit) {
    int lastScore = getLastScore();
    return lastScore + hit > 10;
  }

  int getLastScore() {
    if (firstStrike || isLastHitStrike())
      return 0;
    int lastScore = 0;
    if (hitHistory.size() > 0)
      lastScore = hitHistory.get(hitHistory.size() - 1);
    return lastScore;
  }

  public boolean isLastHitStrike() {
    return isStrike;
  }

}

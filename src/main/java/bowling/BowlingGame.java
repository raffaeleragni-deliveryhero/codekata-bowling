package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class BowlingGame {

  boolean firstStrike = true;
  private List<Integer> hitHistory = new LinkedList<>();
  private boolean isLastHitStrike;
  private boolean isLastHitSpare;

  public int score() {
    var total = 0;
    var previousHit = Optional.<Integer>empty();
    for (int i = 0; i < hitHistory.size(); i++) {
      int hit = hitHistory.get(i);
      total += hit;

      if (isStrike(hit)) {
        total += safeGet(i + 1).orElse(0);
        total += safeGet(i + 2).orElse(0);
        previousHit = Optional.empty();
      } else if (isSpare(hit, previousHit.orElse(0))) {
        total += safeGet(i + 1).orElse(0);
        previousHit = Optional.empty();
      } else
        previousHit = previousHit.isEmpty() ? Optional.of(hit) : Optional.empty();
    }
    return total;
  }

  public void hit(int hit) {
    if(hit < 0) throw new IllegalArgumentException("The score can't be negative!");
    if(hit > 10) throw new IllegalArgumentException("The score can't be positive!");

    if (isTurnHitsAbove10(hit))
      throw new IllegalArgumentException();

    isLastHitStrike = isStrike(hit);
    isLastHitSpare = isTurnHits10(hit);

    hitHistory.add(hit);
    firstStrike = !firstStrike;
  }

  public boolean isLastHitStrike() {
    return isLastHitStrike;
  }

  public boolean isLastHitSpare() {
    return isLastHitSpare;
  }

  private Optional<Integer> safeGet(int index) {
    if (index > hitHistory.size() - 1) {
      return Optional.empty();
    }

    return Optional.of(hitHistory.get(index));
  }

  private boolean isStrike(int hit) {
    return hit == 10;
  }

  private boolean isSpare(int hit, int previousHit) {
    if (isStrike(hit))
      return false;
    return hit + previousHit == 10;
  }

  boolean isTurnHitsAbove10(int hit) {
    return getLastScore().orElse(0) + hit > 10;
  }

  boolean isTurnHits10(int hit) {
    return getLastScore()
      .map(last -> last + hit == 10)
      .orElse(false);
  }

  Optional<Integer> getLastScore() {
    if (firstStrike || isLastHitStrike())
      return Optional.empty();
    int lastScore = 0;
    if (hitHistory.size() > 0)
      lastScore = hitHistory.get(hitHistory.size() - 1);
    return Optional.of(lastScore);
  }

}

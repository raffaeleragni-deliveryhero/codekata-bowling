package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class BowlingGameTest {

  BowlingGame game;

  @BeforeEach
  void setup() {
    game = new BowlingGame();
  }
  
  @Test
  void testInitGame() {
    assertThat(game.score(), is(0));
  }
  
  @Test
  void testGameScore() {
    game.hit(4);
    assertThat(game.score(), is(4));
  }
}

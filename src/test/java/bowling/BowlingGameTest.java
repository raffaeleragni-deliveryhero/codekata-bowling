package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameTest {

  BowlingGame game;

  @BeforeEach
  void setup() {
    game = new BowlingGame();
  }

  @Test
  void testInitGame() {
    assertThat(game.score(), is(0));
    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testGameScore() {
    game.hit(4);
    assertThat(game.score(), is(4));
    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testSecondHit(){
    game.hit(4);
    game.hit(2);
    assertThat(game.score(), is(6));
    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }


  @ParameterizedTest
  @ValueSource(ints = {-2, 11})
  void testInvalidHit(int param){
    assertThrows(IllegalArgumentException.class, () -> game.hit(param));
  }

  @Test
  void testDoubleHitGreaterThan10() {
    game.hit(6);
    assertThrows(IllegalArgumentException.class, () -> game.hit(6));
  }

  @Test
  void testTripleHitOK() {
    game.hit(2);
    game.hit(4);

    game.hit(6);
    assertThat(game.score(), is(12));
    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testTripleAboveBackToBack() {
    game.hit(2);
    game.hit(6);

    game.hit(6);
    assertThat(game.score(), is(14));
    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testStrike() {
    game.hit(10);

    assertThat(game.isLastHitStrike(), is(true));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testNotStrike() {
    game.hit(1);

    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
  }

  @Test
  void testTwoStrike() {
    game.hit(10);
    game.hit(10);

    assertThat(game.isLastHitStrike(), is(true));
    assertThat(game.isLastHitSpare(), is(false));
    assertThat(game.score(), is(30));
  }

  @Test
  void testThreeStrike() {
    game.hit(10);
    game.hit(10);
    game.hit(10);

    assertThat(game.isLastHitStrike(), is(true));
    assertThat(game.score(), is(60));
  }

  @Test
  void testSpareNoExtraBall() {
    game.hit(9);
    game.hit(1);

    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(true));
    assertThat(game.score(), is(10));
  }

  @Test
  void testSpareWithExtraBall() {
    game.hit(9);
    game.hit(1);
    game.hit(1);

    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
    assertThat(game.score(), is(12));
  }

  @Test
  void testSpareWithExtraTwoBalls() {
    game.hit(9);
    game.hit(1);
    game.hit(5);
    game.hit(3);

    assertThat(game.isLastHitStrike(), is(false));
    assertThat(game.isLastHitSpare(), is(false));
    assertThat(game.score(), is(23));
  }

  @Test
  void testGameFlow(){
    game.hit(7);
    game.hit(3);
    /* 10*/
    game.hit(10);
    /* 30*/
    game.hit(2);
    game.hit(4);
    /* 42*/
    assertThat(game.score(), is(42));
  }

  @Test
  void maxStrikes(){
    for(int i =0; i < 12; i++){
      game.hit(10);
    }
    assertThat(game.score(), is(300));

  }



}

package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    /**
     * The game under test
     */
    private SinglePlayerGame game;

    /**
     * A player in this game
     */
    private Player playerMock = mock(Player.class, withSettings().verboseLogging());

    /**
     * A leve in this game
     */
    private Level levelMock = mock(Level.class, withSettings().verboseLogging());

    /**
     * The game's point calculator
     */
    private PointCalculator pointCalculatorMock = mock(PointCalculator.class, withSettings().verboseLogging());

    /**
     * Sets up a basic game
     */
    @BeforeEach
    void setup() {
        game = new SinglePlayerGame(playerMock, levelMock, pointCalculatorMock);
    }

    /**
     * Validates
     */
    @Test
    void testStartWhenNotInProgress() {
        when(levelMock.isAnyPlayerAlive()).thenReturn(true);
        when(levelMock.remainingPellets()).thenReturn(1);
        game.start();
        assertTrue(game.isInProgress());
    }

    @Test
    void testStartWhenInProgress() {
        when(levelMock.isAnyPlayerAlive()).thenReturn(true);
        when(levelMock.remainingPellets()).thenReturn(1);

        game.start();
        assertTrue(game.isInProgress());
        game.start();
        assertTrue(game.isInProgress());
    }

}
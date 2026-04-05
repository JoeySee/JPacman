package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */
public class DirectionTest {
    /**
     * Do we get the correct delta when moving north?
     */
    @ParameterizedTest
    @CsvSource({"NORTH,0,-1", "EAST,1,0", "SOUTH,0,1", "WEST,-1,0"})
    void testDirection(String directionString, int expectedX, int expectedY) {
        Direction direction = Direction.valueOf(directionString);
        assertThat(direction.getDeltaX()).isEqualTo(expectedX);
        assertThat(direction.getDeltaY()).isEqualTo(expectedY);
    }
}

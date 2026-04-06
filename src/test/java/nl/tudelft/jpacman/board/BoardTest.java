package nl.tudelft.jpacman.board;

import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import net.jqwik.api.*;

class BoardTest {
    private static final int MAX_WIDTH = 20;
    private static final int MAX_HEIGHT = 20;

    private final Square[][] grid = new Square[MAX_HEIGHT][MAX_WIDTH];
    private Board board = createBoard();

    private Board createBoard(){
        for(int x = 0; x < MAX_WIDTH; x++){
            for(int y = 0; y < MAX_HEIGHT; y++){
                grid[y][x] = mock(Square.class);
            }
        }
        return new Board(grid);
    }

    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    @Property
    void withinBoundaries(@ForAll("validHeight") int x, @ForAll("validWidth") int y){
        assertTrue(board.withinBorders(x, y));
    }

    @Property
    void outsideBoundaries(@ForAll("invalidHeight") int x, @ForAll("invalidWidth") int y){
        assertFalse(board.withinBorders(x, y));
    }

    @Provide
    private Arbitrary<Integer> validHeight(){
        return Arbitraries.integers().between(0, MAX_HEIGHT - 1);
    }

    @Provide
    private Arbitrary<Integer> validWidth(){
        return Arbitraries.integers().between(0, MAX_WIDTH - 1);
    }

    @Provide
    private Arbitrary<Integer> invalidWidth(){
        return Arbitraries.oneOf(Arbitraries.integers().greaterOrEqual(MAX_WIDTH), Arbitraries.integers().lessOrEqual(-1));
    }

    @Provide
    private Arbitrary<Integer> invalidHeight(){
        return Arbitraries.oneOf(Arbitraries.integers().greaterOrEqual(MAX_HEIGHT), Arbitraries.integers().lessOrEqual(-1));
    }
}
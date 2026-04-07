package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.*;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClydeTest {

    /**
     * Map parser used to construct boards.
     */
    private GhostMapParser parser;

    /**
     * Player used to test navigation
     */
    private final PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
    private final Player player = playerFactory.createPacMan();

    /**
     * Set up the map parser.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(
                sprites,
                ghostFactory,
                mock(PointCalculator.class));
        parser = new GhostMapParser(levelFactory, new BoardFactory(sprites), ghostFactory);
    }

    @ParameterizedTest
    @MethodSource("provideLargerHorizonalGap")
    void testLargerHorizonalGap(ArrayList<String> map, Direction direction) {
        Level l = parser.parseMap(map);
        Board b = l.getBoard();
        l.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, b);

        Optional<Direction> expected = Optional.of(direction);
        Optional<Direction> actual = clyde.nextAiMove();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideLargerVerticalGap")
    void testLargerVerticalGap(ArrayList<String> map, Direction direction) {
        Level l = parser.parseMap(map);
        Board b = l.getBoard();
        l.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, b);

        Optional<Direction> expected = Optional.of(direction);
        Optional<Direction> actual = clyde.nextAiMove();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideSameSizeGap")
    void testSameSizeGap(ArrayList<String> map, Direction direction) {
        Level l = parser.parseMap(map);
        Board b = l.getBoard();
        l.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, b);

        Optional<Direction> expected = Optional.of(direction);
        Optional<Direction> actual = clyde.nextAiMove();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideLargerHorizonalGap() {
        return Stream.of(
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#c     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     P#",
                                "########"),
                        Direction.EAST),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     P#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#c     #",
                                "########"),
                        Direction.EAST),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     c#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#P     #",
                                "########"),
                        Direction.WEST),Arguments.of(Lists.newArrayList(
                        "########",
                                "#P     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     c#",
                                "########"),
                        Direction.WEST),
                Arguments.of(Lists.newArrayList("#####", "#c  #", "#  P#", "#####"),
                        Direction.WEST),
                Arguments.of(Lists.newArrayList("#####", "#  P#", "#c  #", "#####"),
                        Direction.WEST),
                Arguments.of(Lists.newArrayList("#####", "#  c#", "#P  #", "#####"),
                        Direction.EAST),
                Arguments.of(Lists.newArrayList("#####", "#P  #", "#  c#", "#####"),
                        Direction.EAST)
        );
    }

    private static Stream<Arguments> provideLargerVerticalGap() {
        return Stream.of(
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#P     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     c#",
                                "########"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     P#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#c     #",
                                "########"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#c     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     P#",
                                "########"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     c#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#P     #",
                                "########"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "# P#", "#  #", "#c #", "####"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "#P #", "#  #", "# c#", "####"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "#c #", "#  #", "# P#", "####"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList("####", "# c#", "#  #", "#P #", "####"),
                        Direction.NORTH)
        );
    }

    private static Stream<Arguments> provideSameSizeGap() {
        return Stream.of(
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     P#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#c     #",
                                "########"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#P     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     c#",
                                "########"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#c     #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#     P#",
                                "########"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList(
                        "########",
                                "#     c#",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#      #",
                                "#P     #",
                                "########"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "# P#", "#c #", "####"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "#P #", "# c#", "####"),
                        Direction.SOUTH),
                Arguments.of(Lists.newArrayList("####", "#c #", "# P#", "####"),
                        Direction.NORTH),
                Arguments.of(Lists.newArrayList("####", "# c#", "#P #", "####"),
                        Direction.NORTH)
        );
    }
}
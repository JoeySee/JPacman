package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.*;
import nl.tudelft.jpacman.level.*;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionInteractionMapTest {
    private CollisionInteractionMap map;

    @BeforeEach
    void setup() {
        map = new CollisionInteractionMap();
    }

    @Test
    void collisionOccurs() {
        Unit a = mock(Unit.class);
        Unit b = mock(Unit.class);

        final boolean[] called = {false};

        map.onCollision(Unit.class, Unit.class, (c1, c2) -> {
            called[0] = true;
        });

        map.collide(a, b);

        assertTrue(called[0]);
    }

    @Test
    void symmetricCollisions() {
        Unit a = mock(Unit.class);
        Unit b = mock(Unit.class);

        final int[] count = {0};

        map.onCollision(Unit.class, Unit.class, (c1, c2) -> count[0]++);

        map.collide(a, b);
        map.collide(b, a);

        assertEquals(2, count[0]);
    }

    @Test
    void nonSymmetricCollisions() {
        Unit a = mock(Player.class);
        Unit b = mock(Ghost.class);

        final int[] count = {0};

        map.onCollision(Player.class, Ghost.class, false, (c1, c2) -> count[0]++);

        map.collide(a, b);
        map.collide(b, a);

        assertEquals(1, count[0]);
    }

}
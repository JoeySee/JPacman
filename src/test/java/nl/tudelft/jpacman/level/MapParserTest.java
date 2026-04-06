package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

class MapParserTest {

    private MapParser mapParser;
    private LevelFactory levelFactoryMock;
    private BoardFactory boardFactoryMock;

    @Test
    void parseMap() {
        LevelFactory levelFactoryMock = mock(LevelFactory.class, withSettings().verboseLogging());
        BoardFactory boardFactoryMock = mock(BoardFactory.class, withSettings().verboseLogging());
        mapParser = new MapParser(levelFactoryMock, boardFactoryMock);

        when(levelFactoryMock.createLevel(any(), any(), any())).thenReturn(null);

        assertNull(mapParser.parseMap(List.of("#")));
        verify(levelFactoryMock, times(1)).createLevel(any(), any(), any());
        verify(boardFactoryMock, times(1)).createBoard(any());
        verify(boardFactoryMock, times(1)).createWall();

        verifyNoMoreInteractions(levelFactoryMock);
        verifyNoMoreInteractions(boardFactoryMock);
    }
}
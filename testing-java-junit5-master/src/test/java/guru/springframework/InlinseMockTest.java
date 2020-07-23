package guru.springframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class InlinseMockTest {
	
@Test
void testInMock() {
	Map mapMock = mock(Map.class);
	
	assertEquals(mapMock.size(), 0);
}

}

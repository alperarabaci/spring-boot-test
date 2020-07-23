package guru.springframework.sfgpetclinic;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnnotationMocksTest {

	@BeforeEach
	void setUp() {
		//bu depreceted olmus
		//MockitoAnnotations.initMocks(this);
		MockitoAnnotations.openMocks(this);
	}
	
	@Mock
	Map<String, Object> mapMock;
	
	@Test
	void testMock() {
		//bir sey yaptigi yok yalandan patlamiyor iste.
		mapMock.put("val", "foo");
		//get metodunu yazmak lazim tabii yoksa null alir oturursun
		System.out.println(mapMock.get("val"));
	}
	
}

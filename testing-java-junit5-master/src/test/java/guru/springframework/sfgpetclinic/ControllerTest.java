package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag("controller")
@TestInstance(Lifecycle.PER_CLASS)
public interface ControllerTest {

	@BeforeAll
	default void beforeAll() {
		System.out.println("Lets do some test.");
	}
	
}

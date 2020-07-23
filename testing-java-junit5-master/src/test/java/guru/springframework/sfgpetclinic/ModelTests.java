package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag("model")
@TestInstance(Lifecycle.PER_CLASS)
public interface ModelTests {

	/**
	 * Interface test icin kullanmamistim, abstract class'lari tercih ederim gibi.
	 * Ama bu da aklimizda kalsin.
	 */
	@BeforeAll
	default void beforeAll() {
		System.out.println("Lets do some test.");
	}
	
}

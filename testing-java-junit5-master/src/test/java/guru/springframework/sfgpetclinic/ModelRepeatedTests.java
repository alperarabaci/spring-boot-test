package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/**
 * Tekrar eden testlerde calismadan once teste dair log.
 * Boylece testte bu loglara gerek kalmaz sadece bu amaçla eklencekse.
 * @author alper
 *
 */
@Tag("repeated")
public interface ModelRepeatedTests {

	@BeforeEach
	default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println("Before-" + testInfo.getDisplayName() + ": "+ repetitionInfo.getCurrentRepetition());
	}
	
}

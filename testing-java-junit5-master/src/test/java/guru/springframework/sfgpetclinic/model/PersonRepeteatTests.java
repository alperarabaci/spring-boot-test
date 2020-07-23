package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import guru.springframework.sfgpetclinic.ModelRepeatedTests;

public class PersonRepeteatTests implements ModelRepeatedTests{

	/**
	 * Tekrar eden testler. BUlnarda ayrıca @Test demeye gerek yok.
	 */
	@DisplayName("My Repetation")
	@RepeatedTest(value=10, name = "{displayName}: {currentRepetition}-{totalRepetitions}")	
	void myRepeatTest() {
		
	}
	
	/**
	 * Test bilgilerinin teste gecilmesi
	 * @param testInfo
	 * @param repetitionInfo
	 */
	@RepeatedTest(5)
	void myRepeatTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println(testInfo.getDisplayName() + ": "+ repetitionInfo.getCurrentRepetition());
	}
	
	@RepeatedTest(5)
	@DisplayName("My Assignment Repeated Test")
	void myAssignmentRepeated(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		
	}
	
}

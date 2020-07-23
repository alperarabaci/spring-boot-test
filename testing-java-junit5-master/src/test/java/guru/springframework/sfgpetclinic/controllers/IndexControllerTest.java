package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import guru.springframework.sfgpetclinic.ControllerTest;

class IndexControllerTest implements ControllerTest{
	
	IndexController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new IndexController();
	}

	@DisplayName("Test Proper View name is returned for index page")
	@Test
	void testIndex() {
		assertEquals("index", controller.index());
		assertEquals("index", controller.index(), "Wrong View Returned");
		
		assertEquals("index", controller.index(), () -> "Another Expensive Message " +
		        "Make me only if you have to");
		
		assertThat(controller.index()).isEqualTo("index");
	}

	@Test
	void testOupsHandler() {
		assertThrows(ValueNotFoundException.class, () -> {
			controller.oupsHandler();
		});
	}

	@Disabled
	@Test
	void testTimeOut() {
		assertTimeout(Duration.ofMillis(100), ()-> {
			Thread.sleep(2000);
			System.out.println("I got here");
		});
	}
	
	@Disabled
	@Test
	void testTimeOutPremt() {
		assertTimeoutPreemptively(Duration.ofMillis(100), ()-> {
			Thread.sleep(2000);
			
			System.out.println("I got here 234234");
		});
	}

	@Test
	void testAssumptionTrue() {
		System.out.println("testAssumptionTrue start");
		assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
		System.out.println("testAssumptionTrue continue");
	}
	
    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "alper")
    @Test
    void testIfUserAlper() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
    @Test
    void testIfUserFred() {
    }
    
}
package org.springframework.samples.petclinic.sfg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@TestPropertySource("classpath:laurel.properties")
@ActiveProfiles("externalized")
@SpringJUnitConfig(classes = LaurelPropertiesTest.TestConfig.class)
public class LaurelPropertiesTest {

	
    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")	
	static class TestConfig {

	}

	@Autowired
	HearingInterpreter hearingInterpreter;

	@Test
	void whatIheard() {
		String word = hearingInterpreter.whatIheard();

		assertEquals("lAurel", word);
	}

}

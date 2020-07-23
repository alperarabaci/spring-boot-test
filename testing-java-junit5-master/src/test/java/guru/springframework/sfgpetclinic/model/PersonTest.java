package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import guru.springframework.sfgpetclinic.ModelTests;

class PersonTest implements ModelTests{

	@Test
	void groupedAssertions() {
		Person person = new Person(1l, "Alper", "Arabacı");
		
		assertAll("Test Props Set",
				()-> assertEquals(person.getFirstName(), "Alper"),
				()-> assertEquals(person.getLastName(), "Arabacı")
				);
	}

	@Test
	void groupedAssertionMsg() {
		Person person = new Person(1l, "Alper", "Arabacı");
		
		assertAll("Test Props Set",
				()-> assertEquals(person.getFirstName(), "Alper2", "Name fail"),
				()-> assertEquals(person.getLastName(), "Arabacı", "Surname fail")
				);
	}
	
	
}

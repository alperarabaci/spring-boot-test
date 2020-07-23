package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerSDJpaServiceTest {

	OwnerSDJpaService service;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new OwnerSDJpaService(null, null, null);
	}

	@Disabled(value = "Disabled")
	@Test
	void testFindByLastName() {
		Owner foundOwner = service.findByLastName("Buck");
	}

	@Test
	void testFindAllByLastNameLike() {
		assertTrue(true);
	}

	@Test
	void testFindAll() {
	}

	@Test
	void testFindById() {
	}

	@Test
	void testSave() {
	}

	@Test
	void testDelete() {
	}

	@Test
	void testDeleteById() {
	}

}

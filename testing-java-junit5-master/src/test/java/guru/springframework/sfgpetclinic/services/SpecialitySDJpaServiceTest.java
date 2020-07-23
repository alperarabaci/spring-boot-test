package guru.springframework.sfgpetclinic.services;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.SpecialitySDJpaService;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

	@Mock
	SpecialtyRepository specialtyRepository;
	
	@InjectMocks
	SpecialitySDJpaService service;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Bunu neyi test ediyor acaba?
	 */
	@Test
	void deletedById() {
		service.deleteById(1L);
		service.deleteById(1L);
		//verify(specialtyRepository).deleteById(1L);
		verify(specialtyRepository, times(2)).deleteById(1L);
	}
	
	@Test
	void deletedByIdAtLeast() {
		service.deleteById(1L);
		//service.deleteById(1L);
		//verify(specialtyRepository).deleteById(1L);
		verify(specialtyRepository, atLeastOnce()).deleteById(1L);
	}
	
	@Test
	void deletedByIdAtMost() {
		service.deleteById(1L);
		//service.deleteById(1L);
		//verify(specialtyRepository).deleteById(1L);
		verify(specialtyRepository, atMost(1)).deleteById(1L);
	}
	
	@Test
	void deletedByNever() {
		service.deleteById(5L);
		//service.deleteById(1L);
		//verify(specialtyRepository).deleteById(1L);
		verify(specialtyRepository, atMost(1)).deleteById(1L);
		
		verify(specialtyRepository, never()).deleteById(5L);
	}
	
	/**
	 * Bunu neyi test ediyor acaba?
	 */
	@Test
	void testDelete() {
		service.delete(new Speciality());
	}

}

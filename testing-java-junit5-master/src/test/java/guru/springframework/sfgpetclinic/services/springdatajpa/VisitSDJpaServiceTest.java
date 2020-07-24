package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anySetOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

	private static final Long ID = 1L;
	private static final Long ID_TWO = 2L;

	@Mock
	VisitRepository visitRepository;
	
	@InjectMocks
	VisitSDJpaService service;

	@DisplayName("Find all visits")
	@Test
	void testFindAll() {
		Set<Visit> visits = new HashSet<>();
		visits.add(new Visit(ID));
		when(visitRepository.findAll()).thenReturn(visits);
		
		Set<Visit> foundVisits = service.findAll();
		verify(visitRepository).findAll();
		
		assertThat(foundVisits).hasSize(1);
	}

	@Test
	void testFindById() {
		Visit visit = new Visit(ID);
		when(visitRepository.findById(ID)).thenReturn(Optional.of(visit));
		
		Visit foundVisit = service.findById(ID);

		verify(visitRepository).findById(anyLong());
		assertThat(foundVisit).isNotNull();
	}
	
	@Test
	void testNotFindById() {		
		when(visitRepository.findById(ID_TWO)).thenReturn(Optional.empty());
		Visit foundVisit = service.findById(ID_TWO);
		
		verify(visitRepository).findById(anyLong());
		assertThat(foundVisit).isNull();
	}

	@Test
	void testSave() {
		Visit visit = new Visit(ID);
		
		when(visitRepository.save(any(Visit.class))).thenReturn(visit);
		
		Visit foundVisit = service.save(new Visit());
				
		verify(visitRepository).save(any(Visit.class));
		assertThat(foundVisit).isNotNull();
	}

	@Test
	void testDelete() {
		Visit visit = new Visit(ID);
		service.delete(visit);
		
		//burada gelen visit'in id'si olmali, bu da o kodda kontrolle 
		//saglanir id olmadan cagirirsin ve kontrollu bir exception
		//beklersin...
		verify(visitRepository).delete(any(Visit.class));		
	}

	@Test
	void testDeleteById() {
		service.deleteById(ID);
		
		verify(visitRepository).deleteById(anyLong());	
	}

}

package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.junit.jupiter.api.Assertions.assertThrows;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

	private static final Long ID = 1L;

	@Mock
	SpecialtyRepository specialtyRepository;
	
	@InjectMocks
	SpecialitySDJpaService service;
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
    public void testDeleteByObject() {
		Speciality speciality = new Speciality();
        service.delete(speciality);
        
        verify(specialtyRepository).delete(any(Speciality.class));
    }

	@Test
	void testFindById() {
		Speciality speciality = new Speciality();
		when(specialtyRepository.findById(ID)).thenReturn(Optional.of(speciality));
		
		Speciality foundSpeciality = service.findById(ID);
		
		assertThat(foundSpeciality).isNotNull();
		verify(specialtyRepository).findById(anyLong());
	}
	
    @Test
    void testFindByIdBdd() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpecialty = service.findById(1L);

        //then
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }
	
	/**
	 * Bunu neyi test ediyor acaba?
	 * Icerde repo cagrilmali onu kontrol ediyor.
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
	
	@Test
	void testDelete() {
		service.delete(new Speciality());
	}

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }
    
    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        when(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).thenReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }
}

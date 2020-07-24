package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

	@InjectMocks
	ClinicServiceImpl service;
	
	@Mock
    PetRepository petRepository;
    
	@Mock
	VetRepository vetRepository;
    
	@Mock
	OwnerRepository ownerRepository;
    
	@Mock
	VisitRepository visitRepository;
	
	@Test
	void testFindPetTypes() {
		Collection<PetType> types = new ArrayList<>();
		types.add(new PetType());
		
		Collection<PetType> foundTypes = service.findPetTypes();
		assertThat(foundTypes).hasSize(1);
	}

}

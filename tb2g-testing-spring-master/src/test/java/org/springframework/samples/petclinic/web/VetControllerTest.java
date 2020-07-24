package org.springframework.samples.petclinic.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

	@InjectMocks
	VetController controller;
	
	@Mock
	ClinicService clinicService;
	
	@Mock
	Map<String, Object> model;
	
	@BeforeEach
	public void setUp() {
		Collection<Vet> list = new ArrayList<>();
		Vet vets = new Vet();
		list.add(vets);
		
		when(clinicService.findVets()).thenReturn(list);
	}
			
	@Test
	void testShowVetList() {
		String result = controller.showVetList(model);
		
		assertThat(result).isEqualToIgnoringCase("vets/vetList");
		verify(clinicService).findVets();
	}
	
	@Test
	void testShowResourcesVetList() {
		Vets result = controller.showResourcesVetList();
		
		verify(clinicService).findVets();
		assertThat(result.getVetList()).hasSize(1);
	}

}

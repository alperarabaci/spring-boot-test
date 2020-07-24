package guru.springframework.sfgpetclinic.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

	@InjectMocks
	OwnerController controller;
	
	@Mock
	OwnerService ownerService;
	
	@Mock
	BindingResult bindingResult;
	
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
	
    /**
     * Bunu ilk defa gordum. Rontgenini cekiyor input neymis bakabiliyorsun.
     * İlgincmis.
     * captor.capture()
     */
    @Test
    void processFindFormWildcardString() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        System.out.println(viewName);
        
        //then
        assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());
    }
    
    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");
        List<Owner> ownerList = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        System.out.println(viewName);
        
        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    }
	
	
	@Test
	void processCreationFormHasErrors() {
        //given
        Owner owner = new Owner(1l, "Jim", "Bob");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
	}
	
    @Test
    void processCreationFormNoErrors() {
        //given
        Owner owner = new Owner(5l, "Jim", "Bob");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        //when
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
	
	
	@Test
	void testSetAllowedFields() {

	}

	@Test
	void testFindOwners() {

	}

	@Test
	void testProcessFindForm() {

	}

	@Test
	void testShowOwner() {

	}

	@Test
	void testInitCreationForm() {

	}


	@Test
	void testInitUpdateOwnerForm() {

	}

	@Test
	void testProcessUpdateOwnerForm() {

	}

}

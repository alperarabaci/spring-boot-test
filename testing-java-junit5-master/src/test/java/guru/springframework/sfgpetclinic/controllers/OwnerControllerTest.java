package guru.springframework.sfgpetclinic.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

	@InjectMocks
	OwnerController controller;
	
    @Mock
    Model model;
    
	@Mock
	OwnerService ownerService;
	
	@Mock
	BindingResult bindingResult;
	
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
    
    @BeforeEach
    void setUp() {
		given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
		        .willAnswer(invocation -> {
		    List<Owner> owners = new ArrayList<>();
		
		    String name = invocation.getArgument(0);
		
		    if (name.equals("%Buck%")) {
		        owners.add(new Owner(1l, "Joe", "Buck"));
		        return owners;
		    } else if (name.equals("%DontFindMe%")) {
		        return owners;
		    } else if (name.equals("%FindMe%")) {
		        owners.add(new Owner(1l, "Joe", "Buck"));
		        owners.add(new Owner(2l, "Joe2", "Buck2"));
		        return owners;
		    }
		
		    throw new RuntimeException("Invalid Argument");
		});
    }

    @Test
    void processFindFormWildcardFound() {
        //given
        Owner owner = new Owner(1l, "Joe", "FindMe");
        InOrder inOrder = inOrder(ownerService, model);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, model);

        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        // inorder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
        
        verifyNoMoreInteractions(model);
    }

    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(1l, "Joe", "Buck");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
        
        org.mockito.Mockito.verifyNoInteractions(model);
    }


    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(1l, "Joe", "DontFindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }
    
	
    /**
     * Bunu ilk defa gordum. Rontgenini cekiyor input neymis bakabiliyorsun.
     * İlgincmis.
     * captor.capture() - captor.getValue()
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

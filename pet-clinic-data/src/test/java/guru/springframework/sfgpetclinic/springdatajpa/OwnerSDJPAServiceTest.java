package guru.springframework.sfgpetclinic.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

	private static final String SMITH = "Smith";

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJPAService service;

	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().lastName(SMITH).build();
	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().build());
		returnOwnerSet.add(Owner.builder().build());

		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

		Set<Owner> owners = service.findAll();

		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

		Owner owner = service.findById(1L);

		assertNotNull(owner);
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

		Owner owner = service.findById(1L);

		assertNull(owner);
	}

	@Test
	void testSave() {

		Owner ownerToSave = Owner.builder().build();

		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());

	}

	@Test
	void testFindByLastName() {

		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		Owner smithOwner = service.findByLastName(SMITH);
		assertEquals(SMITH, smithOwner.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}

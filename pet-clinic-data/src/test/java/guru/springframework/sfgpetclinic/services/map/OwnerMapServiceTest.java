package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerMapServiceTest {
	
	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lastName = "Nagaraja";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		ownerMapService.save(Owner.builder().lastName(lastName).build());
	}
	
	@Test
	void testFindAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(1,  ownerSet.size());
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
		System.out.println("testFindByIdLong Id is  " + ownerId);
	}

	@Test
	void testSaveOwner() {
		String firstName = "manju";
		Owner owner2 = Owner.builder().firstName(firstName).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(firstName, savedOwner.getFirstName());
		System.out.println("testSaveOwner " + savedOwner.getFirstName());
	}
	
	@Test
	void testSaveOwnerId() {
		Owner owner2 = ownerMapService.save(Owner.builder().build());
		assertNotNull(owner2);
		assertNotNull(owner2.getId());
		System.out.println("testSaveOwnerId Id is " + owner2.getId());
	}

	@Test
	void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleteByIdLong() {
		ownerMapService.deleteById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		Owner ownerlast = ownerMapService.findByLastName(lastName);
		assertNotNull(ownerlast);
		assertEquals(ownerId, ownerlast.getId());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		Owner ownerlast = ownerMapService.findByLastName("Foo");
		assertNull(ownerlast);
	}

}

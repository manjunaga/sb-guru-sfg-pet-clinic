package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatType = petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel Street");
		owner1.setCity("Miami");
		owner1.setTelephone("123456");
		
		Pet mikesDog  = new Pet();
		mikesDog.setOwner(owner1);
		mikesDog.setPetType(savedDogType);
		mikesDog.setBirthDate(LocalDate.now());
		mikesDog.setName("Rosco");
		owner1.getPets().add(mikesDog);
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Fionne");
		owner2.setLastName("Glenanne");
		owner2.setAddress("456 Manago Street");
		owner2.setCity("Missouri");
		owner2.setTelephone("456789");
		
		Pet fionasCat  = new Pet();
		fionasCat.setOwner(owner2);
		fionasCat.setPetType(savedCatType);
		fionasCat.setBirthDate(LocalDate.now());
		fionasCat.setName("Fionas Cat");
		owner2.getPets().add(fionasCat);
		ownerService.save(owner2);

		System.out.println("Loaded owners..." + ownerService.findAll().toString() );

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Venam");
		vet2.setLastName("Triol");
		vetService.save(vet2);

		System.out.println("Loaded Vets..." + vetService.findAll().toString() );
	}

}

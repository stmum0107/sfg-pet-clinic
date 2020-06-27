package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.services.*;
import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private SpecialtyService specialtyService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, 
			PetTypeService petTypeService, SpecialtyService specialtyService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}

	@Override
	public void run(String... args) throws Exception {

		int count = petTypeService.findAll().size();
		if (count ==0 ) {
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		dog.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology = specialtyService.save(radiology);
		
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty savedSurgery = specialtyService.save(surgery);
		
		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedDentistry = specialtyService.save(dentistry);

		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Sanjeev");
        owner1.setLastName("Tiwari");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456");
        
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        
        ownerService.save(owner1);
        
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glennaen");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("123456");
		
        Pet FionasCat = new Pet();
        FionasCat.setName("Just Cat");
        FionasCat.setOwner(owner2);
        FionasCat.setBirthDate(LocalDate.now());
        FionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(FionasCat);
        
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);
        
        vetService.save(vet1);
        
        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet1.getSpecialities().add(savedSurgery);        
        
        vetService.save(vet2);
        
        System.out.println("Loaded vets...");
	}

}


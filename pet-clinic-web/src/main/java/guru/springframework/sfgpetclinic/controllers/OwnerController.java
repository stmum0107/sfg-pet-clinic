package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.OwnerService;


@Controller
@RequestMapping("/owners")
public class OwnerController {
	
	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}

	@RequestMapping({"","/", "/index", "/index.html"})
	public String ListOwners(Model model) {
		model.addAttribute("owners", ownerService.findAll());
		return "Owners/index";		
	}
	
	@RequestMapping({"/find"})
	public String FindOwners(Model model) {
		return "notimplemented";		
	}

}

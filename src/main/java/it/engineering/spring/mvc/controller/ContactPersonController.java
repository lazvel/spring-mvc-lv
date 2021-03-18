package it.engineering.spring.mvc.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.ContactPersonDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.ContactPersonService;
import it.engineering.spring.mvc.service.ManufacturerService;
import it.engineering.spring.mvc.validator.ContactPersonValidator;
import it.engineering.spring.mvc.validator.ManufacturerValidator;

@Controller
@RequestMapping(path = {"/contactPerson", "/cp"})
public class ContactPersonController {
	private ContactPersonService contactPersonService;
	private ManufacturerService manufacturerService;
	
	@Autowired
	public ContactPersonController(ContactPersonService contactPersonService, ManufacturerService manufacturerService) {
		this.contactPersonService = contactPersonService;
		this.manufacturerService = manufacturerService;
	}
	
	@GetMapping(path = "/list")
	public ModelAndView list(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("contact-person/contact-person-list");
		request.setAttribute("persons", contactPersonService.getAll());
		
		return modelAndView;
	}
	
	// BINDING
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView addBinding(@ModelAttribute("contactPersonDto") ContactPersonDto contactPersonDto) throws Exception {
		contactPersonDto.setFirstname("n/a");
		contactPersonDto.setLastname("n/a");
		ModelAndView modelAndView = new ModelAndView("contact-person/contact-person-add");
		return modelAndView;
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public ModelAndView saveBinding(@Valid @ModelAttribute("contactPersonDto") ContactPersonDto contactPersonDto, Errors error) throws Exception {
		System.out.println("=============================");
		System.out.println("/save");
		System.out.println(contactPersonDto);
		
		ModelAndView modelAndView = new ModelAndView("contact-person/contact-person-add"); // IME FOLDERA I FAJLA
		
		if (error.hasErrors()) {
			System.out.println("There are errors in validation...");
		} else {
			System.out.println("No errors, save manufacturer...");
			contactPersonService.save(contactPersonDto);
		}
		return modelAndView;
	}
	
	@GetMapping(path = "/details/id/{id}")
	public ModelAndView details(@PathVariable(name = "id") Long id) throws Exception {
		System.out.println("=================== DETAILS ===================");
		System.out.println("ID: " + id);
		
		ContactPersonDto contactPersonDto = contactPersonService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact-person/contact-person-details");
		modelAndView.addObject("contactPersonDto", contactPersonDto);
		
		return modelAndView;
	}
	
	@GetMapping(path = "/edit/id/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) throws Exception {
		System.out.println("=================== DETAILS ===================");
		System.out.println("ID: " + id);
		
		ContactPersonDto contactPersonDto = contactPersonService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact-person/contact-person-edit");
		modelAndView.addObject("contactPersonDto", contactPersonDto);
		
		return modelAndView;
	}
	
	@PostMapping(path = "/confirm")
	public ModelAndView confirm(@Valid @ModelAttribute("contactPersonDto") ContactPersonDto contactPersonDto, Errors error) {
		System.out.println("========================================");
		System.out.println("=================== public ModelAndView confirm =====================");
		System.out.println("========================================");
		
		String view = "contact-person/contact-person-add";
		
		ModelAndView modelAndView = new ModelAndView();
		if (error.hasErrors()) {
			System.out.println("There is error from validation");
			if (contactPersonDto.getId() != null) view = "manufacturer/manufacturer-edit";
		} else view = "contact-person/contact-person-confirm";
		
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
	@PostMapping(path = "/confirm/saveOrUpdate")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("contactPersonDto") ContactPersonDto contactPersonDto, Errors error) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String view = "manufacturer/manufacturer-add";
		
		if (error.hasErrors()) {
			System.out.println("There is error from validation");
			if (contactPersonDto.getId() != null) view = "manufacturer/manufacturer-edit";
		} else {
			// are we doin save or update ??
			// update na osnovu id-a
			if (contactPersonDto.getId() == null) {
				contactPersonService.save(contactPersonDto);
				view = "redirect:/contactPerson/add";
			}
			else {
				contactPersonDto = contactPersonService.update(contactPersonDto);
				view = "redirect:/contactPerson/details/id/" + contactPersonDto.getId();
			}
		}
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
//	@ModelAttribute(name = "cities") // na svaki poziv izvrsice se ova metoda da popuni
//	private List<CityDto> getCities() throws Exception {
//		return cityService.getAll();
//	}

	@ModelAttribute(name = "manufacturers") // na svaki poziv izvrsice se ova metoda da popuni
	private List<ManufacturerDto> getManufacturers() throws Exception {
		return manufacturerService.getAll();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("=============================");
		System.out.println("public void initBinder(WebDataBinder binder)");
		binder.addValidators(new ContactPersonValidator());
	}
	
	// ovo imam gore vec u add-binding
//	@ModelAttribute(name = "manufacturerDto")
//	private ManufacturerDto manufacturerDto() {
//		ManufacturerDto manufacturerDto = new ManufacturerDto();
//		manufacturerDto.setName("n/a");
//		return manufacturerDto;
//	}
	
	// todo greske koje vraca service metoda
}

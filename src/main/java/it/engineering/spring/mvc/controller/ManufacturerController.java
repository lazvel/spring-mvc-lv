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
import it.engineering.spring.mvc.dto.ManufacturerDto;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.ManufacturerService;
import it.engineering.spring.mvc.validator.ManufacturerValidator;

@Controller
@RequestMapping(path = {"/manufacturer", "/m"})
public class ManufacturerController {
	private ManufacturerService manufacturerService;
	private CityService cityService;
	
	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService, CityService cityService) {
		this.manufacturerService = manufacturerService;
		this.cityService = cityService;
	}
	
	@GetMapping(path = "/list")
	public ModelAndView list(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("manufacturer/manufacturer-list");
		request.setAttribute("manufacturers", manufacturerService.getAll());
		
		return modelAndView;
	}
	
	// BINDING
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView addBinding(@ModelAttribute("manufacturerDto") ManufacturerDto manufacturerDto) throws Exception {
		manufacturerDto.setName("n/a");
		ModelAndView modelAndView = new ModelAndView("manufacturer/manufacturer-add");
		return modelAndView;
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public ModelAndView saveBinding(@Valid @ModelAttribute("manufacturerDto") ManufacturerDto manufacturerDto, Errors error) throws Exception {
		System.out.println("=============================");
		System.out.println("/save");
		System.out.println(manufacturerDto);
		
		ModelAndView modelAndView = new ModelAndView("manufacturer/manufacturer-add");
		
		if (error.hasErrors()) {
			System.out.println("There are errors in validation...");
		} else {
			System.out.println("No errors, save manufacturer...");
			manufacturerService.save(manufacturerDto);
		}
		return modelAndView;
	}
	
	@GetMapping(path = "/details/id/{id}")
	public ModelAndView details(@PathVariable(name = "id") Long id) throws Exception {
		System.out.println("=================== DETAILS ===================");
		System.out.println("ID: " + id);
		
		ManufacturerDto manufacturerDto = manufacturerService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer/manufacturer-details");
		modelAndView.addObject("manufacturerDto", manufacturerDto);
		
		return modelAndView;
	}
	
	@GetMapping(path = "/edit/id/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) throws Exception {
		System.out.println("=================== DETAILS ===================");
		System.out.println("ID: " + id);
		
		ManufacturerDto manufacturerDto = manufacturerService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer/manufacturer-edit");
		modelAndView.addObject("manufacturerDto", manufacturerDto);
		
		return modelAndView;
	}
	
	@PostMapping(path = "/confirm")
	public ModelAndView confirm(@Valid @ModelAttribute("manufacturerDto") ManufacturerDto manufacturerDto, Errors error) {
		System.out.println("========================================");
		System.out.println("=================== public ModelAndView confirm =====================");
		System.out.println("========================================");
		
		String view = "manufacturer/manufacturer-add";
		//if (manufacturerDto.getId() != null) view = "manufacturer/manufacturer-edit";
		
		ModelAndView modelAndView = new ModelAndView();
		if (error.hasErrors()) {
			System.out.println("There is error from validation");
			if (manufacturerDto.getId() != null) view = "manufacturer/manufacturer-edit";
		} else view = "manufacturer/manufacturer-confirm";
		
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
	@PostMapping(path = "/confirm/saveOrUpdate")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("manufacturerDto") ManufacturerDto manufacturerDto, Errors error) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String view = "manufacturer/manufacturer-add";
		
		if (error.hasErrors()) {
			System.out.println("There is error from validation");
			if (manufacturerDto.getId() != null) view = "manufacturer/manufacturer-edit";
		} else {
			// are we doin save or update ??
			// update na osnovu id-a
			if (manufacturerDto.getId() == null) {
				manufacturerService.save(manufacturerDto);
				view = "redirect:/manufacturer/add";
			}
			else {
				manufacturerDto = manufacturerService.update(manufacturerDto);
				view = "redirect:/manufacturer/details/id/" + manufacturerDto.getId();
			}
		}
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
	@ModelAttribute(name = "cities") // na svaki poziv izvrsice se ova metoda da popuni
	private List<CityDto> getCities() throws Exception {
		return cityService.getAll();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("=============================");
		System.out.println("public void initBinder(WebDataBinder binder)");
		binder.addValidators(new ManufacturerValidator());
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

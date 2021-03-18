package it.engineering.spring.mvc.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.exception.ExistEntityException;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.impl.CityServiceImpl;

@Controller
@SessionAttributes(value = "cityDto")
public class CityController {
	@Autowired
	private LocalValidatorFactoryBean validator;
	
	@Autowired
	@Qualifier(value = "cityServiceImpl")
	private CityService cityService;
	
	
	@RequestMapping(path = {"/city", "/city/add"}, method = RequestMethod.GET)
	public String cityAdd(SessionStatus session) {
		session.setComplete();
		System.out.println("================ METHOD ================");
		System.out.println("public String cityAdd()");
		return "city-add"; // logicko ime ?
	}
	
//	@RequestMapping(path = "/city/save", method = RequestMethod.POST)
//	public String citySave(HttpServletRequest request) {
//		System.out.println("================ METHOD ================");
//		System.out.println("public String citySave()");
//		
//		String number = request.getParameter("number");
//		String name = request.getParameter("name");
//		System.out.println(number + ", " + name);
//		
//		try {
//			Long code = Long.parseLong(number);
//			CityDto cityDto = new CityDto(code, name);
//			System.out.println(cityDto);
//			
//			cityService.add(cityDto);
//			
//			request.setAttribute("information", "Grad je uspesno sacuvan");
//			return "redirect:/city";
//		} catch (Exception e) {
//			request.setAttribute("exception", "Greska kod unosa");
//			return "city-add";// (kao forward) vracamo svakako city-add i ako je uspesno i ako nije
//		}
//	}
	
	@RequestMapping(path = "/city/save", method = RequestMethod.POST)
	public String citySave(
			HttpServletRequest request, 
			@RequestParam(name = "number") String number,
			@RequestParam(name = "name") String name) {
		System.out.println("================ SAVE ================");
		
		try {
			Long code = Long.parseLong(number);
			CityDto cityDto = new CityDto(code, name);
			System.out.println(cityDto);
			
			cityService.save(cityDto);
			
			request.setAttribute("information", "Grad je uspesno sacuvan");
			return "redirect:/city";
		} catch (Exception e) {
			request.setAttribute("exception", "Greska kod unosa");
			return "city-add";// (kao forward) vracamo svakako city-add i ako je uspesno i ako nije
		}
	}
	
	// ovo radi znaci i bez request parametara
	@RequestMapping(path = "/city/saveDto", method = RequestMethod.POST)
	public String saveCityDto(HttpServletRequest request, CityDto cityDto) {
		System.out.println("================ SAVE DTO ================");
		
		try {
			System.out.println(cityDto);
			
			cityService.save(cityDto);
			
			request.setAttribute("information", "Grad je uspesno sacuvan");
			return "redirect:/city";
		} catch (Exception e) {
			request.setAttribute("exception", "Greska kod unosa");
			return "city-add";// (kao forward) vracamo svakako city-add i ako je uspesno i ako nije
		}
	}
	
	@RequestMapping(path = "/city/saveToConfirm", method = RequestMethod.POST) 
	public String saveToConfirm(CityDto cityDto, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("=============================");
		System.out.println("/saveToConfirm");
		
		model.addAttribute("cityDto", cityDto);
		model.addAttribute("information", "Kliknite na Confirm da potvrdite unos");
		// ja iz ove metode ne vrsim redirekciju vec samo prosledjujem metodu nazad
		//redirectAttributes.addAttribute("information", "Kliknite na Confirm da potvrdite unos");
		
		return "city-confirm";
	}
	
	@RequestMapping(path = "/city/confirm", method = RequestMethod.POST)
	public String confirm(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("=============================");
		System.out.println("/confirm");
		System.out.println(cityDto);
		cityService.save(cityDto);
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
		
		return "redirect:/city";
	}
	
	@RequestMapping(path = "/city/addModel", method = RequestMethod.GET)
	public ModelAndView addModel(CityDto cityDto, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("city-add-model");
		modelAndView.addObject("cityDto", new CityDto(0L, "n/a"));
		return modelAndView;
	}
	
	@RequestMapping(path = "/city/saveModel", method = RequestMethod.POST)
	public String saveModel(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("=============================");
		System.out.println("/saveModel");
		System.out.println(cityDto);
		cityService.save(cityDto);
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
		
		return "redirect:/city/addModel";
	}
	
	// BINDING
	@RequestMapping(path = "/city/add-binding", method = RequestMethod.GET)
	public String addBinding(@ModelAttribute("cityDto") CityDto cityDto) {
		System.out.println("===========================================================");
		System.out.println("=========================== /add-binding ================================");
		cityDto.setNumber(-2L);
		cityDto.setName("Ombre que passa -2L");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("cityDto", new CityDto(-1L, "-"));
//		modelAndView.setViewName("city-add-binding");
		return "city-add-binding";
	}
	
	@RequestMapping(path = "/city/save-binding", method = RequestMethod.POST)
	public String saveBinding(@ModelAttribute("cityDto") CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("=============================");
		System.out.println("/saveBinding");
		System.out.println(cityDto);
		cityService.save(cityDto);
		
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
		
		return "redirect:/city/add-binding";
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("exception", e.getMessage());
//			return "redirect:/city/add-binding";
//		}
		
	}
	
//	@RequestMapping(path = "/city/save-binding-validation", method = RequestMethod.POST)
//	public ModelAndView saveBindingAndValidation(
//			@Valid @ModelAttribute("cityDto") CityDto cityDto, 
//			final BindingResult bindingResult) {
//		System.out.println("=============================");
//		System.out.println("/saveBinding-validation");
//		System.out.println(cityDto);
//		
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = factory.getValidator();
//		Set<ConstraintViolation<CityDto>> constraintViolations = validator.validate(cityDto);
//		System.out.println("======================================");
//		constraintViolations.stream().forEach(el -> System.out.println(el));
//		System.out.println("======================================");
//		
//		SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
//		springValidator.validate(cityDto, bindingResult);
//		
//		// da li postoje greske
//		if (bindingResult.hasErrors()) {
//			System.out.println("================== HAS ERRORS ==================");
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("city-add-binding");
//			return modelAndView;
//		} else {
//			System.out.println("================== NO ERRORS ==================");
//			try {
//				cityService.save(cityDto);
//				ModelAndView modelAndView = new ModelAndView();
//				modelAndView.setViewName("redirect:/city/add-binding");
//				return modelAndView;
//			} catch (Exception e) {
//				System.out.println("==================================== GRESKA ====================================");
//				System.out.println(e.getMessage());
//				ModelAndView modelAndView = new ModelAndView();
//				modelAndView.setViewName("redirect:/city/add-binding");
//				modelAndView.addObject("exception", e.getMessage());
//				return modelAndView;
//			}
//			
//		}
//		
//	}
	
	@RequestMapping(path = "/city/save-binding-validation", method = RequestMethod.POST)
	public ModelAndView saveBindingAndValidation(
			@Valid @ModelAttribute("cityDto") CityDto cityDto, 
			final BindingResult bindingResult,
			SessionStatus session) throws Exception {
		System.out.println("=============================");
		System.out.println("/saveBinding-validation");
		System.out.println(cityDto);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CityDto>> constraintViolations = validator.validate(cityDto);
		System.out.println("======================================");
		constraintViolations.stream().forEach(el -> System.out.println(el));
		System.out.println("======================================");
		
		SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
		springValidator.validate(cityDto, bindingResult);
		
		// da li postoje greske
		if (bindingResult.hasErrors()) {
			System.out.println("================== HAS ERRORS ==================");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("city-add-binding");
			return modelAndView;
		} else {
			System.out.println("================== NO ERRORS ==================");
			cityService.save(cityDto);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/city/add-binding");
			session.setComplete();
			return modelAndView;
		}
	}
	
	@ExceptionHandler(ExistEntityException.class)
	public ModelAndView exceptionHandlerEntityExists(ExistEntityException eee) {
		System.out.println("=============================================================");
		System.out.println(eee.getMessage());
		System.out.println("===================== Objekat nad kojim se desila greska je =====================");
		System.out.println(eee.getEntity());
		System.out.println("=============================================================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("city-add-binding");
		modelAndView.addObject("cityDto", ((CityDto) eee.getEntity()));
		modelAndView.addObject("exception", eee.getMessage());
		return modelAndView;
	}
	
	@RequestMapping(path = "/city/all", method = RequestMethod.GET)
	public String cityAll(HttpServletRequest request) throws Exception {
		System.out.println("================ METHOD ================");
		System.out.println("public String cityAll()");
		request.setAttribute("cities", cityService.getAll());
		
		
		return "city-list"; // logicko ime ?
	}
	
	@RequestMapping(path = "/city/info", method = RequestMethod.GET)
	public String cityInfo(HttpServletRequest request) throws NumberFormatException, Exception {
		System.out.println("================ INFO ================");
		System.out.println("City number = " + request.getParameter("number"));
		
		CityDto cityDto = cityService.findById(Long.parseLong(request.getParameter("number")));
		if (cityDto == null) {
			
		} else {
			request.setAttribute("city", cityDto);
		}
		
		return "city-info"; // logicko ime ?
	}
	
	@RequestMapping(path = "/city/update", method = RequestMethod.PATCH)
	public String cityUpdate() {
		System.out.println("================ METHOD ================");
		System.out.println("public String cityUpdate()");
		return "city-update"; // logicko ime ?
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		if (binder.getTarget() instanceof CityDto) {
			binder.setValidator(validator);
		}
	}
	
	@ModelAttribute(name = "cityDto")
	private CityDto getCityDto() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("CITY DTO KREIRAN");
		System.out.println("------------------------------------------------------------------------------");
		return new CityDto(-1L, "City -1");
	}
}

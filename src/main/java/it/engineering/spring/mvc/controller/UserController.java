//package it.engineering.spring.mvc.controller;
//
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolation;
//import javax.validation.Valid;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.DataBinder;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import it.engineering.spring.mvc.dto.UserDto;
//import it.engineering.spring.mvc.service.UserService;
//
//@Controller
//public class UserController {
//	@Autowired
//	private LocalValidatorFactoryBean validator;
//	
//	private final UserService userService;
//	
//	@Autowired
//	public UserController(UserService userService) {
//		this.userService = userService;
//	}
//	
//	@RequestMapping(path = {"/user", "/user/add"}, method = RequestMethod.GET)
//	public String userAdd() {
//		System.out.println("================ METHOD ================");
//		System.out.println("public String userAdd()");
//		return "user-add"; // logicko ime ?
//	}
//	
////	@RequestMapping(path = "/user/save", method = RequestMethod.POST)
////	public String userSave(HttpServletRequest request) {
////		System.out.println("================ METHOD ================");
////		System.out.println("public String userSave()");
////		
////		String number = request.getParameter("number");
////		String name = request.getParameter("name");
////		System.out.println(number + ", " + name);
////		
////		try {
////			Long code = Long.parseLong(number);
////			UserDto userDto = new UserDto(code, name);
////			System.out.println(userDto);
////			
////			userService.add(userDto);
////			
////			request.setAttribute("information", "Grad je uspesno sacuvan");
////			return "redirect:/user";
////		} catch (Exception e) {
////			request.setAttribute("exception", "Greska kod unosa");
////			return "user-add";// (kao forward) vracamo svakako user-add i ako je uspesno i ako nije
////		}
////	}
//	
//	@RequestMapping(path = "/user/save", method = RequestMethod.POST)
//	public String userSave(
//			HttpServletRequest request, 
//			@RequestParam(name = "firstname") String firstname,
//			@RequestParam(name = "lastname") String lastname,
//			@RequestParam(name = "username") String username,
//			@RequestParam(name = "password") String password) {
//		System.out.println("================ SAVE ================");
//		
//		try {
//			UserDto userDto = new UserDto(firstname, lastname, username, password);
//			System.out.println(userDto);
//			
//			userService.add(userDto);
//			
//			request.setAttribute("information", "Grad je uspesno sacuvan");
//			return "redirect:/user";
//		} catch (Exception e) {
//			request.setAttribute("exception", "Greska kod unosa");
//			return "user-add";// (kao forward) vracamo svakako user-add i ako je uspesno i ako nije
//		}
//	}
//	
//	// ovo radi znaci i bez request parametara
//	@RequestMapping(path = "/user/saveDto", method = RequestMethod.POST)
//	public String saveUserDto(HttpServletRequest request, UserDto userDto) {
//		System.out.println("================ SAVE DTO ================");
//		
//		try {
//			System.out.println(userDto);
//			
//			userService.add(userDto);
//			
//			request.setAttribute("information", "Grad je uspesno sacuvan");
//			return "redirect:/user";
//		} catch (Exception e) {
//			request.setAttribute("exception", "Greska kod unosa");
//			return "user-add";// (kao forward) vracamo svakako user-add i ako je uspesno i ako nije
//		}
//	}
//	
//	@RequestMapping(path = "/user/saveToConfirm", method = RequestMethod.POST) 
//	public String saveToConfirm(UserDto userDto, Model model, RedirectAttributes redirectAttributes) {
//		System.out.println("=============================");
//		System.out.println("/saveToConfirm");
//		
//		model.addAttribute("userDto", userDto);
//		model.addAttribute("information", "Kliknite na Confirm da potvrdite unos");
//		// ja iz ove metode ne vrsim redirekciju vec samo prosledjujem metodu nazad
//		//redirectAttributes.addAttribute("information", "Kliknite na Confirm da potvrdite unos");
//		
//		return "user-confirm";
//	}
//	
//	@RequestMapping(path = "/user/confirm", method = RequestMethod.POST)
//	public String confirm(UserDto userDto, RedirectAttributes redirectAttributes) {
//		System.out.println("=============================");
//		System.out.println("/confirm");
//		System.out.println(userDto);
//		userService.add(userDto);
//		redirectAttributes.addFlashAttribute("information", "User je uspesno sacuvan...");
//		
//		return "redirect:/user";
//	}
//	
//	@RequestMapping(path = "/user/addModel", method = RequestMethod.GET)
//	public ModelAndView addModel(UserDto userDto, RedirectAttributes redirectAttributes) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("user-add-model");
//		modelAndView.addObject("userDto", new UserDto("n/a", "n/a", "n/a", "n/a"));
//		return modelAndView;
//	}
//	
//	@RequestMapping(path = "/user/saveModel", method = RequestMethod.POST)
//	public String saveModel(UserDto userDto, RedirectAttributes redirectAttributes) {
//		System.out.println("=============================");
//		System.out.println("/saveModel");
//		System.out.println(userDto);
//		userService.add(userDto);
//		redirectAttributes.addFlashAttribute("information", "User je uspesno sacuvan...");
//		
//		return "redirect:/user/addModel";
//	}
//	
//	// BINDING
//	@RequestMapping(path = "/user/add-binding", method = RequestMethod.GET)
//	public String addBinding(@ModelAttribute("userDto") UserDto userDto) {
//		userDto.setFirstname("John");
//		userDto.setFirstname("Doe");
//		userDto.setPassword("johndoe");
////		ModelAndView modelAndView = new ModelAndView();
////		modelAndView.addObject("userDto", new UserDto(-1L, "-"));
////		modelAndView.setViewName("user-add-binding");
//		return "user-add-binding";
//	}
//	
//	@RequestMapping(path = "/user/save-binding", method = RequestMethod.POST)
//	public String saveBinding(@ModelAttribute("userDto") UserDto userDto, RedirectAttributes redirectAttributes) {
//		System.out.println("=============================");
//		System.out.println("/saveBinding");
//		System.out.println(userDto);
//		userService.add(userDto);
//		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
//		
//		return "redirect:/user/add-binding";
//	}
//	
//	@RequestMapping(path = "/user/save-binding-validation", method = RequestMethod.POST)
//	public ModelAndView saveBindingAndValidation(
//			@Valid @ModelAttribute("userDto") UserDto userDto, 
//			final BindingResult bindingResult) {
//		System.out.println("=============================");
//		System.out.println("/saveBinding-validation");
//		System.out.println(userDto);
//		
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = factory.getValidator();
//		Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
//		System.out.println("======================================");
//		constraintViolations.stream().forEach(el -> System.out.println(el));
//		System.out.println("======================================");
//		
//		SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
//		springValidator.validate(userDto, bindingResult);
//		
//		// da li postoje greske
//		if (bindingResult.hasErrors()) {
//			System.out.println("================== HAS ERRORS ==================");
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("user-add-binding");
//			return modelAndView;
//		} else {
//			System.out.println("================== NO ERRORS ==================");
//			userService.add(userDto);
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("redirect:/user/add-binding");
//			return modelAndView;
//		}
//		
//	}
//
//	@InitBinder
//	public void initBinder(DataBinder binder) {
//		if (binder.getTarget() instanceof UserDto) {
//			binder.setValidator(validator);
//		}
//	}
//	
//	@RequestMapping(path = "/user/all", method = RequestMethod.GET)
//	public String userAll(HttpServletRequest request) {
//		System.out.println("================ METHOD ================");
//		System.out.println("public String userAll()");
//		request.setAttribute("users", userService.getAll());
//		
//		
//		return "user-list"; // logicko ime ?
//	}
//	
//	@RequestMapping(path = "/user/info", method = RequestMethod.GET)
//	public String userInfo(HttpServletRequest request) {
//		System.out.println("================ INFO ================");
//		System.out.println("Name = " + request.getParameter("firstname"));
//		
//		UserDto userDto = userService.findByName(request.getParameter("firstname"));
//		if (userDto == null) {
//			
//		} else {
//			request.setAttribute("user", userDto);
//		}
//		
//		return "user-info"; // logicko ime ?
//	}
//	
//	@RequestMapping(path = "/user/update", method = RequestMethod.PATCH)
//	public String userUpdate() {
//		System.out.println("================ METHOD ================");
//		System.out.println("public String userUpdate()");
//		return "user-update"; // logicko ime ?
//	}
//	
//	@RequestMapping(path = "/user/delete", method = RequestMethod.GET)
//	public String userDelete(HttpServletRequest request) {
//		System.out.println("================ DELETE ================");
//		userService.delete(request.getParameter("username"));
//		
//		return "user-list"; // logicko ime ?
//	}
//}

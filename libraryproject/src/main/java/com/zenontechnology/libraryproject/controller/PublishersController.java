package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.service.PublisherService;

@Controller
public class PublishersController {

	@Autowired
	private PublisherService publisherService;

	@RequestMapping("/publishers")
	public String viewpublishersPage(Model model) {
		List<Publishers> listPublishers = publisherService.listAll();
		model.addAttribute("listPublishers", listPublishers);
		return "./Views/Publishers/index";
	}

	@RequestMapping("/publishers/create")
	public String showNewUserForm(Model model) {
		Publishers publisher = new Publishers();
		model.addAttribute("publisher", publisher);

		return "./Views/Publishers/create";
	}

	@RequestMapping(value = "/publishers/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("publisher") Publishers publisher) {
		publisherService.save(publisher);

		return "redirect:/publishers";
	}

	@RequestMapping("/publishers/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Publishers/edit");
		Publishers publisher = publisherService.get(id);
		mav.addObject("publisher", publisher);
		publisher.setPublisherId(id);
		return mav;
	}

	@RequestMapping("/publishers/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		publisherService.delete(id);
		return "redirect:/publishers";
	}

	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET)
	public @ResponseBody List<Publishers> getPublishers() {
		List<Publishers> publishers = publisherService.listAll();

		return publishers;
	}
}

package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Pizza;
import org.generation.italy.services.IngredienteService;
import org.generation.italy.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService service;
	
	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping
	public String pizzaList(Model model, @RequestParam(name="keyword",required=false) String keyword ) {
		List<Pizza> result;
		if(keyword !=null && !keyword.isEmpty()) {
			model.addAttribute("keyword", keyword);
			result=service.trovaPizzePerNome(keyword);
		}else {
			result=service.trovaPizze();
		}
		model.addAttribute("list", result);
		return "pizza";

	}
	@GetMapping("/create")
	public String pizzaCreate(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredientiList", ingredienteService.trovaIngrediente());
		return "edit";
	}
	@PostMapping("/create")
	public String creaPizza(@Valid @ModelAttribute("pizza") Pizza formPizza,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			model.addAttribute("ingredientiList", ingredienteService.trovaIngrediente());
			return "edit";
		}
		service.save(formPizza);
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("pizza", service.getById(id));
		model.addAttribute("ingredientiList", ingredienteService.trovaIngrediente());
		return "edit";
	}
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("ingredientiList", ingredienteService.trovaIngrediente());
			return "edit";
		}
		service.save(formPizza);
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/";
	}
}

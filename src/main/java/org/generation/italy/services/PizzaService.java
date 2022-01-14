package org.generation.italy.services;

import java.util.List;

import org.generation.italy.model.Pizza;
import org.generation.italy.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository repository;
	
	public List<Pizza> trovaPizze(){
		return repository.findAll();
	}
	public Pizza save(Pizza pizza) {
		return repository.save(pizza);
	}
	public Pizza getById(Integer id) {
		return repository.getById(id);
	}
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	public List<Pizza> trovaPizzePerNome(String keyword){
		return repository.findByNomeContainingIgnoreCase(keyword);
	}
	
}

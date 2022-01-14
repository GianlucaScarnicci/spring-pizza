package org.generation.italy.services;

import java.util.List;

import org.generation.italy.model.Ingrediente;
import org.generation.italy.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository repository;
	
	public List<Ingrediente> trovaIngrediente(){
		return repository.findAll();
	}
}

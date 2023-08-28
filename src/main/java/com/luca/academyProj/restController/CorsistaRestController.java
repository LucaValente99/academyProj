package com.luca.academyProj.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.academyProj.businesscomponent.model.Corsista;
import com.luca.academyProj.service.CorsistaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="")
public class CorsistaRestController {
	@Autowired
	CorsistaService corsistaService;
	
	@GetMapping("/corsisti")
	public List<Corsista> getCorsisti(){
		return corsistaService.getAll();
	}
}

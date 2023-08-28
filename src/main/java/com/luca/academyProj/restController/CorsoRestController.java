package com.luca.academyProj.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.academyProj.businesscomponent.model.Corso;
import com.luca.academyProj.service.CorsoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="")
public class CorsoRestController {
	@Autowired
	CorsoService corsoService;
	
	@GetMapping("/corsiDisponibili")
	public List<Corso> getCorsiDisponibili(){
		return corsoService.findCorsiDisponibili();
	}
	
	@GetMapping("/corsoById/{id}")
	public Corso getContatto(@PathVariable Long id){
		return corsoService.findById(id).get();
	}
	
	@PostMapping("/saveCorso")
	public void saveCorso(@RequestBody Corso corso){
		corsoService.saveCorso(corso);
	}
	
	@DeleteMapping("/deleteCorso/{id}")
	public boolean deleteCorso(@PathVariable Long id) {
		corsoService.deleteCorsoById(id);
		return true;
	}
}

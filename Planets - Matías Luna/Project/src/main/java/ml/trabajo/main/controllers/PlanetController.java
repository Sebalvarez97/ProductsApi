package ml.trabajo.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.trabajo.main.dtos.PlanetDTO;
import ml.trabajo.main.services.PlanetService;

@Controller
@RestController
@RequestMapping("api/v1/planets")
public class PlanetController {
	
	private PlanetService planetService;
	
	public PlanetController(PlanetService planetService) {
		this.planetService = planetService;
	}
	
	@GetMapping("/")
	public List<PlanetDTO> getAll(){
		return ResponseEntity.ok().body(this.planetService.listAll()).getBody();
	}
	
	@GetMapping("/{id}")
	public PlanetDTO get(@PathVariable int id) {
		return ResponseEntity.ok().body(this.planetService.getOne(id)).getBody();
	}
	
	@PostMapping("/")
	public ResponseEntity post(@RequestBody PlanetDTO planetDTO) {
		//New rows with the same name can't be created
		if (planetService.verify(planetDTO)) {
			//If not exist 201 - Created
			return ResponseEntity.status(201).body(this.planetService.create(planetDTO));
		}
		else {
			//Else exist 400 - Bad request
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		this.planetService.delete(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody @Valid PlanetDTO planetDTO, @PathVariable int id) {
		//Don't update rows with the same name to existing ones
		if (planetService.verify(planetDTO)) {
			//If not exist 201 - Created
			return ResponseEntity.status(201).body(planetService.update(planetDTO, id));
		}
		else {
			//Else exist 400 - Bad request
			return ResponseEntity.status(400).body(null);
		}
		
	}

}

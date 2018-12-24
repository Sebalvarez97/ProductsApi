package ml.trabajo.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ml.trabajo.main.dtos.PlanetDTO;
import ml.trabajo.main.entities.Planet;
import ml.trabajo.main.repositories.PlanetRepository;

@Service
public class PlanetService {

	private PlanetRepository planetRepository;
	
	public PlanetService(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}
	
	//List all items of the DB table
	public List<PlanetDTO> listAll(){
		//Temporally list that save objects PlanetDTO
		List<PlanetDTO> result = new ArrayList<>(); 
		
		for(Planet p : this.planetRepository.findAll()) {
			
			//New temporally object that create a PlanetDTO
			PlanetDTO planetDTO = new PlanetDTO();
			planetDTO.setName(p.getName());
			planetDTO.setSurface(p.getSurface());
			planetDTO.setMiddleRadio(p.getMiddleRadio());
			planetDTO.setDensity(p.getDensity());
			
			//This is added to the list
			result.add(planetDTO);
			
		}
		
		//Return the list
		return result;
	}
	
	//Get an element by its id
	public PlanetDTO getOne(int id) {
		
		//New temporally object (Planet)
		Optional<Planet> planet = planetRepository.findById(id);
		
		//New temporally object (PlanetDTO) that be returned
		PlanetDTO planetDTO = new PlanetDTO();

		try {
			Planet p = planet.get();
			
			planetDTO.setName(p.getName());
			planetDTO.setSurface(p.getSurface());
			planetDTO.setMiddleRadio(p.getMiddleRadio());
			planetDTO.setDensity(p.getDensity());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Return a object (PlanetDTO)
		return planetDTO;
		
	}
	
	//Create a new row in the DB
	public PlanetDTO create(PlanetDTO planetDTO) {
		
		//Temporally object container
		Planet planet = new Planet();
		
		//The states are assigned
		planet.setName(planetDTO.getName());
		planet.setSurface(planetDTO.getSurface());
		planet.setMiddleRadio(planetDTO.getMiddleRadio());
		planet.setDensity(planetDTO.getDensity());
		
		//Save the date in the DB
		planetRepository.save(planet);
		
		//Return original object
		return planetDTO;
		
	}
	
	//Delete an item of the DB
	public boolean delete(int id) {
		
		//It got deleted D:
		try {
			planetRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//Update a row adding new states
	public PlanetDTO update(PlanetDTO planetDTO, int id) {
		
		//Temporally object search an row with id...
		Planet planet = planetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No encontrado"));
		planet.setName(planetDTO.getName());
		planet.setSurface(planetDTO.getSurface());
		planet.setMiddleRadio(planetDTO.getMiddleRadio());
		planet.setDensity(planetDTO.getDensity());
		
		//Update and save
		Planet planetResult = planetRepository.save(planet);
		
		//Return original object
		return planetDTO;	
	}
	
	public boolean verify(PlanetDTO planetDTO) {
		
		for(PlanetDTO busqueda : listAll()) {
			if (planetDTO.getName().equals(busqueda.getName())){
				return false;
			}
		}
		
		return true;
		
	}
	
	
}

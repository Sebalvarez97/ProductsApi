package ml.trabajo.main.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ml.trabajo.main.dtos.PlanetDTO;
import ml.trabajo.main.services.PlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceTest {

	@Autowired
	private PlanetService planetService;
	
	@Test
	public void listAllTest() {
		
		for(PlanetDTO pDTO : planetService.listAll()) {
			assertNotNull(pDTO);
			assertTrue(!(pDTO.getName()).equals(""));
		}
		
	}
	
	@Test
	public void getOneSuccessTest() {
				
		//This can be null if not find the row
		assertNotNull(planetService.getOne(17));
		assertTrue((planetService.getOne(17).getName()).equals("ASD8197801.080863028"));		
		
	}
	
	@Test
	public void createTest() {
		
		PlanetDTO planetDTO = new PlanetDTO();
		planetDTO.setName("ASD"+(Math.random()*9999999));
		planetDTO.setDensity(1000);
		planetDTO.setMiddleRadio(1001);
		planetDTO.setSurface(1002);
		
		PlanetDTO result = planetService.create(planetDTO);
		
		assertEquals(planetDTO, result);
		
	}
	
	@Test
	public void deleteTest() {
		
		//You need change this value to execute this test correctly
		int deletePosition = 12;
		
		assertTrue(planetService.delete(deletePosition)); 
		
	}
	
	@Test
	public void updateTest() {
		
		//Index
		int position = 2;
		//Object
		PlanetDTO test = new PlanetDTO();
		
		//Test
		test.setName("Prueba Modificada");		
		planetService.update(test, position);
		PlanetDTO result = planetService.listAll().get(position-1);
		System.out.println(result.getName());
		
		assertEquals(result.getName(), "Prueba Modificada");
		
		//Original state
		test.setName("prueba2");
		planetService.update(test, position);
		
	}
	
	@Test
	public void verifyTest() {
		
		//This method confirm that entity already exists
		PlanetDTO planetDTO = new PlanetDTO("prueba2",0,0,0);		
		assertFalse(planetService.verify(planetDTO));
		
	}

}
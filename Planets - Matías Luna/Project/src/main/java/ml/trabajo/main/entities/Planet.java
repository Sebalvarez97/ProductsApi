package ml.trabajo.main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "main_planet")
public class Planet {

	//Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planet_id")
	private int id;
	@Column(name = "planet_name", unique = true)
	private String name;
	@Column(name = "planet_surface")
	private double surface;
	@Column(name = "planet_middleRadio")
	private float middleRadio;
	@Column(name = "planet_density")
	private float density;
	
	//Constructors
	
	public Planet() {
		
	}
	
	public Planet(String name, double surface, float middleRadio, float density) {
		this.name = name;
		this.surface = surface;
		this.middleRadio = middleRadio;
		this.density = density;
	}

	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public float getMiddleRadio() {
		return middleRadio;
	}

	public void setMiddleRadio(float middleRadio) {
		this.middleRadio = middleRadio;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}
	
}

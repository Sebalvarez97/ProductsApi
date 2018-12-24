package com.everis.products.dtos;

import java.io.Serializable;

public class PlanetDTO implements Serializable{
	
	private String name;
	private double surface;
	private float middleRadio;
	private float density;
	
	public PlanetDTO() {
		
	}
	
	public PlanetDTO(String name, double surface, float middleRadio, float density) {
		this.name = name;
		this.surface = surface;
		this.middleRadio = middleRadio;
		this.density = density;
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

package com.everis.products.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.everis.products.dtos.PlanetDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class PlanetService {
	
	private static final String BASE_URL = "http://localhost:8081/api/v1";
	
	public PlanetDTO getPlanet(String id) {
		try {
			HttpResponse<JsonNode> result = 
					Unirest
					.get(String.format("%s/planets/%s", BASE_URL, id))
					.asJson();
			
			JSONObject json = result.getBody().getObject();
			PlanetDTO dto = new PlanetDTO();
			if(result.getStatus() == 200) {
				dto.setName(json.getString("name"));
				dto.setSurface(Double.parseDouble(json.getString("surface")));
				dto.setMiddleRadio(json.getInt("middleRatio"));
				dto.setDensity(Float.parseFloat(json.getString("density")));
			}
			return dto;
		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PlanetDTO> getPlanets(){
		try {
			HttpResponse<JsonNode> result =
					Unirest
							.get(String.format("%s/planets", BASE_URL))
							.asJson();
			JSONArray jsons = result.getBody().getArray();
			List<PlanetDTO> list = new ArrayList<PlanetDTO>();
			if(result.getStatus() == 200) {
				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = (JSONObject) jsons.get(i);
					PlanetDTO dto = new PlanetDTO();
					dto.setName(json.getString("name"));
					dto.setSurface(Double.parseDouble(json.getString("surface")));
					dto.setMiddleRadio(json.getInt("middleRatio"));
					dto.setDensity(Float.parseFloat(json.getString("density")));
					list.add(dto);
				}
			}
			return list;
		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PlanetDTO createPlanet(PlanetDTO dto) {
		try {
			HttpResponse<JsonNode> result = 
					Unirest
							.post(String.format("%s/planets", BASE_URL))
							.header("Content-Type", "application/json")
							.body(dto)
							.asJson();
			JSONObject json = result.getBody().getObject();
			PlanetDTO ret = new PlanetDTO();
			if(result.getStatus() == 201) {
				ret.setName(json.getString("name"));
				ret.setSurface(Double.parseDouble(json.getString("surface")));
				ret.setMiddleRadio(json.getInt("middleRatio"));
				ret.setDensity(Float.parseFloat(json.getString("density")));
			}
			return ret;
		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PlanetDTO editPlanet(PlanetDTO dto, String id) {
		try {
			HttpResponse<JsonNode> result = 
					Unirest.put(String.format("%/planets/%s", BASE_URL, id))
							.header("Content-Type", "application/json")
							.body(dto)
							.asJson();
			JSONObject json = result.getBody().getObject();
			PlanetDTO ret = new PlanetDTO();
			if(result.getStatus() == 201) {
				ret.setName(json.getString("name"));
				ret.setSurface(Double.parseDouble(json.getString("surface")));
				ret.setMiddleRadio(json.getInt("middleRatio"));
				ret.setDensity(Float.parseFloat(json.getString("density")));
			}
			return ret;
		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deletePlanet(String id) {
		HttpResponse<JsonNode> result;
		try {
			result = Unirest.delete(String.format("%s/planets/%s", BASE_URL, id)).asJson();
			if(result.getStatus() == 204) {
				return true;
			}
			return false;
		} catch (UnirestException e) {
			e.printStackTrace();
			return false;
		}

	}
}

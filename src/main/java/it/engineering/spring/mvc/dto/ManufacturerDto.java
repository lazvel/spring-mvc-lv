package it.engineering.spring.mvc.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ManufacturerDto implements Serializable, Dto{

	private static final long serialVersionUID = 17032021103000L;
	
	private Long id;
	private String name;
	private CityDto cityDto;
	
	public ManufacturerDto() {
		// TODO Auto-generated constructor stub
	}

	public ManufacturerDto(Long id, String name, CityDto cityDto) {
		super();
		this.id = id;
		this.name = name;
		this.cityDto = cityDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	@Override
	public String toString() {
		return "ManufacturerDto [id=" + id + ", name=" + name + ", cityDto=" + cityDto + "]";
	}
}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityDto == null) ? 0 : cityDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManufacturerDto other = (ManufacturerDto) obj;
		if (cityDto == null) {
			if (other.cityDto != null)
				return false;
		} else if (!cityDto.equals(other.cityDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

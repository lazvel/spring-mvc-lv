package it.engineering.spring.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityEntity implements it.engineering.spring.mvc.dto.Entity{
	@Id
	private Long number;
	private String name;
	
	public CityEntity() {
		// TODO Auto-generated constructor stub
	}

	public CityEntity(Long number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityEntity [number=" + number + ", name=" + name + "]";
	}
	
}

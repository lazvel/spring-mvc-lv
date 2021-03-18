package it.engineering.spring.mvc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturer")
public class ManufacturerEntity implements Serializable, it.engineering.spring.mvc.dto.Entity{

	private static final long serialVersionUID = 17032021102200L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "city_number")
	private CityEntity city;
	
	public ManufacturerEntity() {
		// TODO Auto-generated constructor stub
	}

	public ManufacturerEntity(Long id, String name, CityEntity city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ManufacturerEntity [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
}

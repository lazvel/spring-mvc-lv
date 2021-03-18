package it.engineering.spring.mvc.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.engineering.spring.mvc.dto.Entity;

@javax.persistence.Entity
@Table(name = "contact_person")
public class ContactPersonEntity implements Serializable, Entity{

	private static final long serialVersionUID = 18032021123300L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	
	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private ManufacturerEntity manufacturerEntity;

	public ContactPersonEntity() {
	}

	public ContactPersonEntity(Long id, String firstname, String lastname, ManufacturerEntity manufacturerEntity) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.manufacturerEntity = manufacturerEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public ManufacturerEntity getManufacturerEntity() {
		return manufacturerEntity;
	}

	public void setManufacturerEntity(ManufacturerEntity manufacturerEntity) {
		this.manufacturerEntity = manufacturerEntity;
	}

	@Override
	public String toString() {
		return "ContactPersonEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", manufacturerEntity=" + manufacturerEntity + "]";
	}
	
}

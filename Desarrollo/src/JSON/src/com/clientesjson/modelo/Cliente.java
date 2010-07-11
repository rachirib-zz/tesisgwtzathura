package com.clientesjson.modelo;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String direccion;

	private String email;

	private String nombre;

	private Long telefono;

    public Cliente() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

}
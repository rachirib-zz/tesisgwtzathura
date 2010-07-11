package com.clientesjson.client.dto;


import com.google.gwt.user.client.rpc.IsSerializable;


public class ClientesDTO implements IsSerializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5515354538803623127L;
	/**
	 * 
	 */
	
	private Long id;
	private String direccion;
	private String email;
	private String nombre;
	private Long telefono;
	private boolean isFetchOnly;
	
	
	public ClientesDTO(Long id, String direccion, String email,
			String nombre, Long telefono) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public ClientesDTO() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public void setFetchOnly(boolean isFetchOnly) {
		this.isFetchOnly = isFetchOnly;
	}

	public boolean isFetchOnly() {
		return isFetchOnly;
	}
	
}

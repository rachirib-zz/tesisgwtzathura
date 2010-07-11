package com.clientesjson.server;

import java.util.ArrayList;
import java.util.List;

import com.clientesjson.client.dataService.DataServiceClientes;
import com.clientesjson.client.dto.ClientesDTO;
import com.clientesjson.modelo.Cliente;
import com.clientesjson.server.businessDelegate.BusinessDelegatorView;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceClientesImpl extends RemoteServiceServlet implements
DataServiceClientes {
	//private JsonDBFacade clientesManager = null;
//	public String getClientes() {
//		clientesManager = new JsonDBFacade();
//		String users = clientesManager.getClientes();
//
//		return users;
//	}
//	
//
//	public void save(String stringJSON) {
//		clientesManager = new JsonDBFacade();
//		clientesManager.save(stringJSON);
//	}
	public List<ClientesDTO> fetch() {
		List<ClientesDTO> fetchData = null;
		try {
			fetchData = toDTOList(BusinessDelegatorView.getCliente());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return fetchData;
	}
	
	public ClientesDTO fetch(Long key) {
		ClientesDTO clientesDTO = null;
		try {
			clientesDTO = toDTO(BusinessDelegatorView.getCliente(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesDTO;
	}

	public ClientesDTO add(ClientesDTO clienteDTO) throws Exception {
		ClientesDTO clientesDTO;
		
		BusinessDelegatorView.saveCliente(clienteDTO.getDireccion(), clienteDTO.getEmail(), clienteDTO.getId(), clienteDTO.getNombre(), clienteDTO.getTelefono());
		clientesDTO = toDTO(BusinessDelegatorView.getCliente(clienteDTO.getId()));
		
		return clientesDTO;
	}

	public ClientesDTO update(ClientesDTO clienteDTO)throws Exception {
		ClientesDTO clientesDTO;
		
		BusinessDelegatorView.updateCliente(clienteDTO.getDireccion(), clienteDTO.getEmail(), clienteDTO.getId(), clienteDTO.getNombre(), clienteDTO.getTelefono());
		clientesDTO = toDTO(BusinessDelegatorView.getCliente(clienteDTO.getId()));
		
		return clientesDTO;
	}

	public void remove(ClientesDTO clienteDTO) {
		try {
			BusinessDelegatorView.deleteCliente(clienteDTO.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Cliente toCliente(ClientesDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setNombre(dto.getNombre());
		cliente.setDireccion(dto.getDireccion());
		cliente.setTelefono(dto.getTelefono());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}
	private ArrayList<ClientesDTO> toDTOList(List<Cliente> clientes) {
		ArrayList<ClientesDTO> dtos = new ArrayList<ClientesDTO>();
		for (Cliente cliente : clientes) {
			dtos.add(toDTO(cliente));
		}
		return dtos;
	}
	public ClientesDTO toDTO(Cliente cliente) {
		if (cliente==null) {
			return null;
		}
		return new ClientesDTO(cliente.getId(), cliente.getDireccion(),
				cliente.getEmail(), cliente.getNombre(), cliente.getTelefono());
	}
}

package com.clientesjson.client.dataService;

import java.util.List;

import com.clientesjson.client.dto.ClientesDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataClientes")
public interface DataServiceClientes extends RemoteService {
    List<ClientesDTO> fetch ();
    
	public ClientesDTO fetch(Long key);

	ClientesDTO add (ClientesDTO swagItemGWTDTO) throws Exception;

	ClientesDTO update (ClientesDTO swagItemGWTDTO) throws Exception;

    void remove (ClientesDTO swagItemGWTDTO);
}
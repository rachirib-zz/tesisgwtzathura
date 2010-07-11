package com.clientesjson.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {
    List<ClientesDTO> fetch ();
    
	public ClientesDTO fetch(Long key);

	ClientesDTO add (ClientesDTO swagItemGWTDTO) throws Exception;

	ClientesDTO update (ClientesDTO swagItemGWTDTO) throws Exception;

    void remove (ClientesDTO swagItemGWTDTO);
}
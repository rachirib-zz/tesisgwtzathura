package com.clientesjson.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	public abstract void fetch (AsyncCallback<List<ClientesDTO>> asyncCallback);

	public abstract void fetch (Long key, AsyncCallback<ClientesDTO> asyncCallback);

	public abstract void add (ClientesDTO clientesDTO, AsyncCallback<ClientesDTO> asyncCallback);

	public abstract void update (ClientesDTO clientesDTO, AsyncCallback<ClientesDTO> asyncCallback);

	public abstract void remove (ClientesDTO clientesDTO, AsyncCallback<Void> asyncCallback);
}

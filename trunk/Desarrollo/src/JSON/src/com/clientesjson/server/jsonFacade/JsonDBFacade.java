package com.clientesjson.server.jsonFacade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.clientesjson.modelo.Cliente;
import com.clientesjson.server.businessDelegate.BusinessDelegatorView;

public class JsonDBFacade {
	private List userList = null;
	
	
	public String getClientes(){
		userList = new ArrayList();
		
		try {
			userList = BusinessDelegatorView.getCliente();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONArray clientes = new JSONArray();

		for( Iterator iterator = userList.iterator(); iterator.hasNext(); )
		{
			Cliente cliente = ( Cliente )iterator.next();

			JSONObject jsonCliente = new JSONObject();

			jsonCliente.put( "id", cliente.getId() );
			jsonCliente.put( "nombre", cliente.getNombre() );
			jsonCliente.put( "direccion", cliente.getDireccion()  );
			jsonCliente.put( "telefono", cliente.getTelefono() );
			jsonCliente.put( "email", cliente.getEmail() );

			clientes.add( jsonCliente );
		}

		

		return clientes.toString();
	}
	
	public void save(String stringJSON){
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject cliente = (JSONObject) jsonParser.parse(stringJSON);
			
				try {
					BusinessDelegatorView.saveCliente(cliente.get("direccion").toString()
							, cliente.get("email").toString()
							, (Long)cliente.get("id")
							, cliente.get("name").toString(), 
							(Long)cliente.get("telefono"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

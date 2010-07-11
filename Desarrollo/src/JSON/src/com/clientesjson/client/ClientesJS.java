package com.clientesjson.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientesJS extends JavaScriptObject{
	protected ClientesJS(){}
	
	public final native String getId() /*-{ return this.id; }-*/;
	public final native String getNombre() /*-{ return this.nombre; }-*/; 
	public final native String getDireccion() /*-{ return this.direccion; }-*/; 
	public final native String getEmail() /*-{ return this.email; }-*/; 
	public final native String getTelefono() /*-{ return this.telefono; }-*/; 

}

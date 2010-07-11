package com.clientesjson.client.smartds;

import java.util.List;

import com.clientesjson.client.dataService.DataService;
import com.clientesjson.client.dataService.DataServiceAsync;
import com.clientesjson.client.dto.ClientesDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * A SwagSwapGWT client helper that makes remote calls to ItemServiceGWTWrapper
 * It conforms to the requirements of SwagSwapGWT components (which are a layer on top
 * of the SmartClient javascript lib)
 * 
 * This is inspired by http://code.google.com/p/smartgwt-extensions/source/browse/trunk/src/main/java/com/smartgwt/extensions/gwtrpcds/client/example/SimpleGwtRPCDS.java
 */
public class SmartGWTRPCDataSource extends AbstractGWTRPCDataSource {

	// TODO is singleton what we want here?
	private static SmartGWTRPCDataSource instance = null;

	public static SmartGWTRPCDataSource getInstance() {
		if (instance == null) {
			instance = new SmartGWTRPCDataSource();
		}
		return instance;
	}

	private SmartGWTRPCDataSource() {
		DataSourceField key = new DataSourceIntegerField("id");
		key.setPrimaryKey(true);
	    // AutoIncrement on server.
		key.setRequired (true);
        addField (key);

        addField(new DataSourceTextField("nombre", "Nombre", 30, false));
        addField(new DataSourceTextField("direccion", "Direccion", 30, false));
        addField(new DataSourceTextField("email", "Email", 30, false));
		addField(new DataSourceTextField("telefono", "Telefono", 20, false));
	}

	@Override
	protected void executeFetch(final String requestId,
			final DSRequest request, final DSResponse response) {
		DataServiceAsync service = GWT
				.create(DataService.class);
		service.fetch(new AsyncCallback<List<ClientesDTO>>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}
			public void onSuccess(List<ClientesDTO> result) {
				int size = result.size();
				// Create list for return - it is just requested records
				ListGridRecord[] list = new ListGridRecord[size];
				if (size > 0) {
					for (int i = 0; i < result.size(); i++) {
						ListGridRecord record = new ListGridRecord();
						copyValues(result.get(i), record);
						list[i] = record;
					}
				}
				response.setData(list);
				response.setTotalRows(result.size());
				processResponse(requestId, response);
			}
		});
	}
	
	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
		// Retrieve record which should be added.
		JavaScriptObject data = request.getData();
		ListGridRecord rec = new ListGridRecord(data);
		ClientesDTO testRec = new ClientesDTO();
		copyValues(rec, testRec);
		DataServiceAsync service = GWT
				.create(DataService.class);
		
		//get newImageBytes
		//TODO figure out where request.getUploadedFile is
//		Object newImageBytes = request.getAttribute("newImageBytes");
		
		service.add(testRec, new AsyncCallback<ClientesDTO>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(ClientesDTO result) {
				ListGridRecord[] list = new ListGridRecord[1];
				ListGridRecord newRec = new ListGridRecord();
				copyValues(result, newRec);
				list[0] = newRec;
				response.setData(list);
				processResponse(requestId, response);
			}
		});
	}

	@Override
	protected void executeUpdate(final String requestId,
			final DSRequest request, final DSResponse response) {
		// Retrieve record which should be updated.
		// Next line would be nice to replace with line:
		// ListGridRecord rec = request.getEditedRecord ();
		ListGridRecord rec = getEditedRecord(request);
		ClientesDTO testRec = new ClientesDTO();
		copyValues(rec, testRec);
		DataServiceAsync service = GWT
				.create(DataService.class);
		
		//Just do a fetch to refresh the item
		if (testRec.isFetchOnly()) {
			service.fetch(testRec.getId(),new UpdateOrFetchCallback(requestId, response));
		}
		else { //really do an update
			service.update(testRec, new UpdateOrFetchCallback(requestId, response));
		}
		
	}

	/**
	 * 
	 * Used to trick the browser cache
	 *
	 */
	final class UpdateOrFetchCallback implements AsyncCallback<ClientesDTO> {
		private final String requestId;
		private final DSResponse response;

		private UpdateOrFetchCallback(String requestId, DSResponse response) {
			this.requestId = requestId;
			this.response = response;
		}
		
		public void onFailure(Throwable caught) {
			throw new RuntimeException(caught);
		}
		
		public void onSuccess(ClientesDTO result) {
			ListGridRecord[] list = new ListGridRecord[1];
			ListGridRecord updRec = new ListGridRecord();
			//Trick the cache so that the image updates in the TileGrid
			copyValues(result, updRec);
			list[0] = updRec;
			response.setData(list);
			processResponse(requestId, response);
		}
	}
	
	@Override
	protected void executeRemove(final String requestId,
			final DSRequest request, final DSResponse response) {
		// Retrieve record which should be removed.
		JavaScriptObject data = request.getData();
		final ListGridRecord rec = new ListGridRecord(data);
		ClientesDTO testRec = new ClientesDTO();
		copyValues(rec, testRec);
		DataServiceAsync service = GWT
				.create(DataService.class);
		service.remove(testRec, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(Void result) {
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				response.setData(list);
				processResponse(requestId, response);
			}

		});
	}

	/**
	 * Remove cache tricking side effect QueryString from imageKey 
	 * see above: result.setImageKey(appendRandomQueryString(result.getImageKey()));
	 * @param imageKey
	 * @return imageKey without cache trick QueryString
	 */
	public static String removeQueryString(String imageKey) {
		// if it's a new item there is no imageKey
		if (imageKey==null) {
			return null;
		}
		Integer queryStringSuffix = imageKey.indexOf("?");
		if (queryStringSuffix == -1) {
			queryStringSuffix = imageKey.length();
		}
		String imagekeyNoQueryString = imageKey.substring(0, queryStringSuffix);
		return imagekeyNoQueryString;
	}
	
	/**
	 * Used to trick the browser cache
	 * @param imageKey
	 * @return imageKey with Random QueryString
	 */
	public static String appendRandomQueryString(String imageKey) {
		String imageKeyNoQueryString = removeQueryString(imageKey);
		if (imageKeyNoQueryString==null) {
			return null;
		}
		String imageKeyWithRandomQueryString = imageKeyNoQueryString + "?" + Random.nextInt();
		return imageKeyWithRandomQueryString;
	}
	
	public static void copyValues(ListGridRecord from, ClientesDTO to) {
		to.setDireccion(from.getAttributeAsString("direccion"));
		to.setEmail(from.getAttributeAsString("email"));
		to.setId(new Long(from.getAttributeAsString("id")));
		to.setTelefono(new Long(from.getAttributeAsString("telefono")));
		to.setNombre(from.getAttributeAsString("nombre"));

	}

	public static void copyValues(ClientesDTO from, ListGridRecord to) {
		to.setAttribute("id", from.getId());
		to.setAttribute("email", from.getEmail());
		to.setAttribute("direccion", from.getDireccion());
		to.setAttribute("telefono", from.getTelefono());
		to.setAttribute("nombre", from.getNombre());
	}

	private ListGridRecord getEditedRecord(DSRequest request) {
		// Retrieving values before edit
		JavaScriptObject oldValues = request
				.getAttributeAsJavaScriptObject("oldValues");
		// Creating new record for combining old values with changes
		ListGridRecord newRecord = new ListGridRecord();
		// Copying properties from old record
		JSOHelper.apply(oldValues, newRecord.getJsObj());
		// Retrieving changed values
		JavaScriptObject data = request.getData();
		// Apply changes
		JSOHelper.apply(data, newRecord.getJsObj());
		return newRecord;
	}
}

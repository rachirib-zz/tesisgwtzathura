package com.clientesjson.client.entryPoint;


import com.clientesjson.client.dataService.DataService;
import com.clientesjson.client.dataService.DataServiceAsync;
import com.clientesjson.client.smartds.SmartGWTRPCDataSource;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ClientesJSON implements EntryPoint {
	private DataServiceAsync dataService = GWT.create( DataService.class );
	private IButton buttonSave = new IButton("Save");
	private IButton buttonClean = new IButton("Clean");
	private IButton buttonDelete = new IButton("Delete");
	private ListGrid grid ;
	private DynamicForm form ;

	public void onModuleLoad() {
		VLayout layout = new VLayout(16);
		layout.setWidth("50%");
		layout.setAlign(Alignment.CENTER);
		ServiceDefTarget endpoint = ( ServiceDefTarget )dataService;
		String moduleRelativeURL = GWT.getModuleBaseURL()+"data";
		endpoint.setServiceEntryPoint( moduleRelativeURL );
		
		buttonSave.enable();
		buttonDelete.disable();


		form = new DynamicForm();
		form.setDataSource(SmartGWTRPCDataSource.getInstance());
		form.setIsGroup(true);  
		form.setGroupTitle("Update");  
		form.setNumCols(4);
		form.setID("id");
		
		
		form.setAlign(Alignment.CENTER);
		



		grid =  new ListGrid();
		grid.setDataSource(SmartGWTRPCDataSource.getInstance());

		
		grid.setWidth100(); 
		grid.setHeight(150);  
		grid.setAutoFetchData(true);
		grid.setSortField("id");
		form.setAlign(Alignment.CENTER);
		
		grid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
//				form.reset();
				form.editSelectedData(grid);
				form.getField("id").setDisabled(true);
				
				buttonDelete.enable();

			}
		});


		layout.addMember(grid);  
		layout.addMember(form);

		
		buttonSave.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				form.submit();
				clear();
				
			}
		});
		buttonDelete.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				grid.removeData(grid.getSelectedRecord());
			}
		});
		buttonClean.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				clear();
			}

		});

		HLayout hLayout = new HLayout(15);
		hLayout.addMember(buttonSave);
		hLayout.addMember(buttonDelete);
		hLayout.addMember(buttonClean);

		layout.addMember(hLayout);
		layout.setAlign(Alignment.CENTER);
		layout.draw();

	}

	
	private void clear() {
		form.clearValues();
		form.editNewRecord();
		form.getField("id").setDisabled(false);
		buttonSave.enable();
		buttonDelete.disable();
	}


}

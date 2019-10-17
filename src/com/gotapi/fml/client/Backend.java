package com.gotapi.fml.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.gotapi.fml.shared.Data;
import com.gotapi.fml.shared.Entity;
import com.gotapi.fml.shared.Item;
import com.gotapi.fml.shared.ItemList;
import com.gotapi.fml.shared.User;

public class Backend {

	//private final GreetingServiceAsync SERVICE = GWT.create(GreetingService.class);
	private Data data;
	
	public Backend() {
				
		User u1 = new User("userone");
		User u2 = new User("usertwo");
		User u3 = new User("userthree");
		
		data =  new Data();
		data.getUsers().add( u1 );
		data.getUsers().add( u2 );
		data.getUsers().add( u3 );
		
		data.getItemLists().add( new ItemList(u1) );
		data.getItemLists().add( new ItemList(u1) );
		data.getItemLists().add( new ItemList(u2) );
		data.getItemLists().add( new ItemList(u3) );
		
	}
	
	public Data getData(String username) {
		return data;
	}

	public long createItem(ItemList list, Item item, Item afterItem) { 
		if( afterItem != null ) {
			int idx = list.getItems().indexOf( afterItem );
			list.getItems().add( idx + 1, item );
		}
		else {
			list.getItems().add( item );
		}
		data.setLastUpdated( data.getLastUpdated()+1 );
		return data.getLastUpdated();
	}

	public long saveItem(Item item) {
		// assumed it is saved
		data.setLastUpdated( data.getLastUpdated()+1 );
		return data.getLastUpdated();
	}

	public long deleteItem(Item item) {
		ItemList list = data.findList(item);
		list.getItems().remove( item );
		data.setLastUpdated( data.getLastUpdated()+1 );
		return data.getLastUpdated();
	}

}

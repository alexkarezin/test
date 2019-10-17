package com.gotapi.fml.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Data implements Serializable, IsSerializable {

	private long lastUpdated;
	private List<User> users = new ArrayList<>();
	private List<ItemList> itemLists = new ArrayList<>();
	
	public Data() {		
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public ItemList findList(Item item) {
		for(ItemList list : itemLists) {
			if( list.getItems().contains( item ) ) return list;
		}
		return null;
	}

	public List<ItemList> getItemLists() {
		return itemLists;
	}

	public void setItemLists(List<ItemList> itemLists) {
		this.itemLists = itemLists;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}

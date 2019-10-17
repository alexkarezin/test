package com.gotapi.fml.shared;

import java.util.ArrayList;
import java.util.List;

public class ItemList extends Entity {

	private User user;
	private List<Item> items = new ArrayList<>();
	
	public ItemList() {		
	}
	
	public ItemList(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}

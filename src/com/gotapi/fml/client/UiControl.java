package com.gotapi.fml.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.gotapi.fml.shared.Data;
import com.gotapi.fml.shared.Item;
import com.gotapi.fml.shared.ItemList;
import com.gotapi.fml.shared.User;

public class UiControl extends Control {

	private DivElement container;
	private DivElement listsContainer;
	private Data data;
	private String username;
	private long lastUpdated;
	private List<ListControl> listControls = new ArrayList<>();

	public UiControl(String username) {		
		this.username = username;		
		this.container = Document.get().createDivElement();		
		
		listsContainer = Document.get().createDivElement();
		container.appendChild( listsContainer );
		
		refresh(null);
		
		new Timer() {
			@Override
			public void run() {
				refresh(null);
			}
		}.scheduleRepeating(3000);
	}
	
	@Override
	public Element getContainer() {
		return container;
	}
	
	public void refresh(Item focusItem) {
		Data newData = FML.BACKEND.getData(username);
		
		if( data == null || newData.getLastUpdated() > lastUpdated ) { 		
			GWT.log("Refreshing for user "+username);
			data = newData;
			lastUpdated = data.getLastUpdated();
			listsContainer.removeAllChildren();
			
			listControls.clear();
			addUserListControls(true);				
			addUserListControls(false);				
		}
		
		if( focusItem != null ) {
			for(ListControl l : listControls) {
				l.focus(focusItem);
			}
		}
		
	}

	private void addUserListControls(boolean forCurrentUser) {
		for(ItemList itemList : data.getItemLists()) {
			boolean currentUser = itemList.getUser().getName().equals(username);
			if( currentUser && !forCurrentUser || !currentUser && forCurrentUser ) continue;
			ListControl list = new ListControl(this,itemList,currentUser);
			listsContainer.appendChild( list.getContainer() );
			listControls.add( list );
		}
	}
	
}

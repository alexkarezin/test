package com.gotapi.fml.client;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.gotapi.fml.shared.Entity;
import com.gotapi.fml.shared.Item;
import com.gotapi.fml.shared.ItemList;

public class ListControl extends Control {

	private DivElement container;
	private DivElement itemContainer;
	private ItemList itemList;
	private DivElement nameElement;
	private UiControl uiControl;
	private boolean currentUser;
	private AnchorElement addTaskLink;
	private List<ItemControl> itemControls = new ArrayList<>();

	public ListControl(UiControl uiControl, ItemList itemList, boolean currentUser) {
		this.uiControl = uiControl;
		this.itemList = itemList;
		this.currentUser = currentUser;
		
		container = Document.get().createDivElement();
		container.setClassName("list-container");
		
		nameElement = Document.get().createDivElement();
		container.appendChild( nameElement );		
		
		itemContainer = Document.get().createDivElement();
		itemContainer.setClassName("list-itemcontainer");
		container.appendChild( itemContainer );
		
		addTaskLink = createLink(container,"Add Task",null,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				addTask(null);
			}
		});
		
		render();
	}
	
	private void addItemControl(Item item) {
		ItemControl itemControl = new ItemControl(uiControl,this,item,currentUser);
		itemContainer.appendChild( itemControl.getContainer() );
		itemControls.add( itemControl );
	}

	@Override
	public Element getContainer() {
		return container;
	}

	public void render() {		
		if( currentUser ) {
			container.addClassName("list-currentuser");
			setVisible(addTaskLink,true);
		}
		else {
			container.removeClassName("list-currentuser");			
			setVisible(addTaskLink,false);
		}
		nameElement.setInnerText( itemList.getUser().getName() );
		itemContainer.removeAllChildren();
		for(Item item : itemList.getItems()) {
			addItemControl(item);
		}
	}

	public void addTask(Item afterItem) {
		Item item = new Item();
		FML.BACKEND.createItem(itemList,item,afterItem); 
		ListControl.this.uiControl.refresh(item);		
	}

	public void focus(Item item) {
		for(ItemControl c : itemControls) {
			if( c.getItem().getId() == item.getId() ) {
				c.focus();
			}
		}
	}

	
	
}

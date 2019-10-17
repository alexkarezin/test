package com.gotapi.fml.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.gotapi.fml.shared.Item;
import com.gotapi.fml.shared.Item.Status;

public class ItemControl extends Control {

	private DivElement container;
	private Element input;
	private Item item;
	private AnchorElement makeCompleteLink;
	private AnchorElement makeOpenLink;
	private AnchorElement deleteLink;
	private UiControl uiControl;
	private boolean currentUser;
	private SpanElement dragHandle;
	private ListControl listControl;

	public ItemControl(UiControl uiControl, ListControl listControl, Item item, boolean currentUser) {
		this.uiControl = uiControl;
		this.item = item;
		this.currentUser = currentUser;
		this.listControl = listControl;
		
		container = Document.get().createDivElement();
		container.setClassName("item-container");
		
		SpanElement rightToolbar = Document.get().createSpanElement();
		rightToolbar.setClassName("item-righttoolbar");
		container.appendChild( rightToolbar );
		
		makeCompleteLink = createLink(container," [\u00A0\u00A0\u00A0] ",null,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				item.setStatus(Status.COMPLETE);
				FML.BACKEND.saveItem(item);
				uiControl.refresh(null);
			}
		});
		
		makeOpenLink = createLink(container," [\u00A0+\u00A0] ",null,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				item.setStatus(Status.OPEN);
				FML.BACKEND.saveItem(item);
				uiControl.refresh(null);
			}
		});
		
		input = Document.get().createSpanElement();
		input.setClassName("item-input");
		container.appendChild( input );
		DOM.sinkEvents(input,Event.ONBLUR|Event.ONKEYDOWN);
		DOM.setEventListener(input,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				if( event.getTypeInt() == Event.ONBLUR ) {
					storeInputValue();
				}
				else if( event.getTypeInt() == Event.ONKEYDOWN ) {
					if( event.getKeyCode() == 13 ) {
						storeInputValue();
						//input.blur();
						listControl.addTask(item);
						event.preventDefault();
					}
					else if( event.getKeyCode() == 27 ) {
						storeInputValue();
						input.blur();
						event.preventDefault();
					}
				}
			}
		});
						
		deleteLink = createLink(rightToolbar,"Delete",null,new EventListener() {
				@Override
				public void onBrowserEvent(Event event) {
					FML.BACKEND.deleteItem(item);
					uiControl.refresh(null);
				}
			});	
		
		dragHandle = Document.get().createSpanElement();
		dragHandle.setClassName("item-draghandle");
		dragHandle.setInnerText("Drag");
		rightToolbar.appendChild( dragHandle );
		
		updateStatus();
		updateInput();
	}
	
	protected void storeInputValue() {
		String original = item.getText();
		String value = input.getInnerText();
		if( FML.equals(original,value) ) return;
		item.setText( value );
		FML.BACKEND.saveItem(item);
		// avoid refresh as it will kill focus
		//FML.getInstance().refresh();
	}

	private void updateInput() {
		input.setInnerText( item.getText() == null || item.getText().length() == 0 ? "\u00A0" : item.getText() );
	}

	protected void updateStatus() {
		if( item.getStatus() == Status.OPEN ) {
			container.removeClassName("item-status-complete");
			container.addClassName("item-status-open");
			setVisible(makeCompleteLink,true); 
			setVisible(makeOpenLink,false); 
		}
		if( item.getStatus() == Status.COMPLETE ) {
			container.addClassName("item-status-complete");
			container.removeClassName("item-status-open");
			setVisible(makeCompleteLink,false); 
			setVisible(makeOpenLink,true); 
		}
		setVisible(deleteLink,currentUser);
		setVisible(dragHandle,currentUser);
		setLinkEnabled(makeCompleteLink,currentUser);
		setLinkEnabled(makeOpenLink,currentUser);
		if( currentUser ) {
			input.setAttribute("contenteditable","true");
		}
		else {
			input.removeAttribute("contenteditable");
		}
	}

	@Override
	public Element getContainer() {
		return container;
	}

	public void focus() {
		input.focus();
	}
	
	public Item getItem() {
		return item;
	}

}

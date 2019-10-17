package com.gotapi.fml.client;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

abstract class Control {

	public abstract Element getContainer();
	
	public static AnchorElement createLink(Element parent, String text, String className, EventListener eventListener) {
		AnchorElement link = Document.get().createAnchorElement();
		link.setHref("#");
		if( className != null ) link.setClassName(className);
		if( parent != null ) parent.appendChild( link );
		if( text != null ) link.setInnerText(text);
		if( eventListener != null ) {
			link.setPropertyObject("listener",eventListener);
			DOM.sinkEvents(link,Event.ONCLICK);
			DOM.setEventListener(link,eventListener);
		}
		return link;
	}
	
	public static void setLinkEnabled(Element link, boolean enabled) {
		if( enabled ) {
			link.getStyle().setCursor(Cursor.POINTER);			
			DOM.sinkEvents(link,Event.ONCLICK);
			DOM.setEventListener(link,(EventListener)link.getPropertyObject("listener"));
		}
		else {
			DOM.sinkEvents(link,Event.UNDEFINED);
			link.getStyle().setCursor(Cursor.DEFAULT);
		}
	}
	
	public static void setVisible(Element element, boolean visible) {		
		element.getStyle().setDisplay(visible ? Style.Display.INITIAL : Style.Display.NONE);
	}
	
}

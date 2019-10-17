package com.gotapi.fml.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.gotapi.fml.shared.Data;
import com.gotapi.fml.shared.Entity;
import com.gotapi.fml.shared.ItemList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FML implements EntryPoint {

	public static final Backend BACKEND = new Backend();
	private static FML instance;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {		
		instance = this;
		
		Document.get().getBody().appendChild( Document.get().createTextNode("What userone sees | ") );
		
		final UiControl userOneControl = new UiControl("userone");
		Control.createLink(Document.get().getBody(),"reload",null,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				userOneControl.refresh(null);
			}
		});
		Document.get().getBody().appendChild( userOneControl.getContainer() );
		
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		Document.get().getBody().appendChild( Document.get().createBRElement() );
		
		Document.get().getBody().appendChild( Document.get().createTextNode("What usertwo sees | ") );
		final UiControl usertwoControl = new UiControl("usertwo");
		Control.createLink(Document.get().getBody(),"reload",null,new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				usertwoControl.refresh(null);
			}
		});
		Document.get().getBody().appendChild( usertwoControl.getContainer() );
Window.alert("DO THING THREE TIMES - just a test");
		initDragula();
		initDragula();
		initDragula();
Window.alert("DO THING THREE TIMES - just a test");
		initDragula();
		initDragula();
Window.alert("DO THING THREE TIMES - just a test");
Window.alert("DO THING THREE TIMES - just a test");
Window.alert("DO THING THREE TIMES - just a test");

getInstance().getInstance();

		initDragula();

	}

	private final native void initDragula() /*-{
		$wnd.dragula({
		  isContainer: function (el) {
		  	return el.className.indexOf('list-itemcontainer')>=0;
		  },
		  moves: function (el, source, handle, sibling) {
		  	return handle.className.indexOf('item-draghandle')>=0;
		  },
		  accepts: function (el, target, source, sibling) {
		    return true; // elements can be dropped in any of the `containers` by default
		  },
		  invalid: function (el, handle) {
		    return false; // don't prevent any drags from initiating by default
		  },
		  direction: 'vertical',             // Y axis is considered when determining where an element would be dropped
		  copy: false,                       // elements are moved by default, not copied
		  copySortSource: false,             // elements in copy-source containers can be reordered
		  revertOnSpill: false,              // spilling will put the element back where it was dragged from, if this is true
		  removeOnSpill: false,              // spilling will `.remove` the element, if this is true
//		  mirrorContainer: document.body,    // set the element that gets mirror elements appended
		  ignoreInputTextSelection: true     // allows users to select input text, see details below
		});
	}-*/;

	public static FML getInstance() {
		return instance;
	}

	public static boolean equals(String arg1, String arg2) {
		return arg1 == null ? arg2 == null || arg2.trim().length() == 0 : arg1.trim().equals(arg2==null?"":arg2.trim());
	}

	
}

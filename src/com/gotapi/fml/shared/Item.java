package com.gotapi.fml.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Item extends Entity {

	public enum Status {		
		OPEN,
		COMPLETE
	}
	
	private String text;
	private Status status = Status.OPEN;
	
	public Item() {
	}
	
	public Item(String text) {
		this.text = text;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

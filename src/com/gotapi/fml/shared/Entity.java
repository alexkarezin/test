package com.gotapi.fml.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Entity implements Serializable, IsSerializable {

	private long id;
	
	protected Entity() {
		id = Math.round( Math.random()*10000000 );
	}
	
	public long getId() {
		return id;
	}
}

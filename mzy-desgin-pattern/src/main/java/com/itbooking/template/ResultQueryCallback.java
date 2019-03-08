package com.itbooking.template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ResultQueryCallback {
	
	public void query(PreparedStatement statement,ResultSet rs);
}

package com.itbooking.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KJdbcTemplate {

	
	public void execute(ConnectionCallback connectionCallback ) {
		Connection connection = null;
		try {
			 connection = ConnectionFactory.getConnection();
			 // insert update delte select
			 connectionCallback.donConnection(connection);
			 
		} finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	public void query(String sql,ResultQueryCallback queryCallback ) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			 connection = ConnectionFactory.getConnection();
			 // insert update delte select
			 statement = connection.prepareStatement(sql);
			 rs = statement.executeQuery();
			 queryCallback.query(statement, rs);
		} finally {
			try {
				if(rs!=null)rs.close();
				if(statement!=null)statement.close();
				if(connection!=null)connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}

package com.advocate.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.AdvocateDao;
import com.advocate.entity.Advocate;

import com.advocate.util.DataBaseConfig;

public class advocateDaoDbImpl implements AdvocateDao {

	private static  Connection connection = DataBaseConfig.getConnection();
	
	private static final String INSERT_ADVOCATE = "insert into advocates (advocate_name) values(?)";
	
//	//adding advocate into MySql DB
	@Override
	public boolean addAdvocate(Advocate advocate) throws SQLException {
	
		PreparedStatement ps = connection.prepareStatement(INSERT_ADVOCATE);

	
		// Set The value
		ps.setString(1, advocate.getName());
	
		
		// Execute Statement
		int executeUpdate = ps.executeUpdate();
		System.out.println("\n >>>>\n");
		ps.close();
		if (executeUpdate > 0) {
			System.out.println("advocate created");
			return true;
		}
		System.out.println("advocate not created");
		return false;
		
	
	}
	

	//Getting all advocates info from ADVOCATEDB--> table-> advocates
		private static final String ALL_ADVOCATES = "select * from advocates";
	@Override
	public List<Advocate> getAllAdvocates() {
	
		List<Advocate> advocateCollection = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(ALL_ADVOCATES);
			ResultSet result = ps.executeQuery();
//			  System.out.println(result);

			while (result.next()) {
				Advocate advocate = new Advocate();
				advocate.setId(result.getInt("id"));
				advocate.setName(result.getString("advocate_name"));
				
				

				advocateCollection.add(advocate);
			}

			result.close();
			ps.close();

		} catch (SQLException e) {

			System.out.println("Error:" + e);
		}
		return advocateCollection;

	}
	
	


	//for editing
		private static final String UPDATE_QUERY = "UPDATE advocates SET advocate_name=? WHERE id=?";

	@Override
	public void editAdvocate(int id, Advocate advocate) {
	
		try {
			// Update the customer record in the database

			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, advocate.getName());
		
			preparedStatement.setInt(2, id);

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Advocate information updated successfully in the database.");
			} else {
				System.out.println("Failed to update Advocate information in the database.");
			}

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	//for getting info by id
		private static final String FIND_BY_ID = "select * from advocates where id=?";

	@Override
	public Advocate getAdvocateById(int id) {
		Advocate advocate = new Advocate();
		try {
		
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				advocate.setId(result.getInt("id"));
				advocate.setName(result.getString("advocate_name"));
			
				
			}else {
				System.out.println("No result found on this id.");
				return null;
			}
			
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return advocate;
	}

	//deleting the advocate record by id 
	 private static final String DELETE_BY_ID="delete from advocates where id=?";
	@Override
	public void deleteAdvocateById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
			ps.setInt(1, id);
			 int affectedRows = ps.executeUpdate();
		        
		        if (affectedRows > 0) {
		            System.out.println("Advocate with ID " + id + " deleted successfully.");
		        } else {
		            System.out.println("Advocate with ID " + id + " not found or not deleted.");
		        }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}

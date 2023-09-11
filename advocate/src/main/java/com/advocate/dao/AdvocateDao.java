package com.advocate.dao;

import java.sql.SQLException;
import java.util.List;

import com.advocate.entity.Advocate;
import com.advocate.entity.Advocate;

public interface AdvocateDao {

	boolean addAdvocate(Advocate advocate) throws SQLException;

	List<Advocate> getAllAdvocates();

	void editAdvocate(int id, Advocate advocate);

	Advocate getAdvocateById(int id); 
	
	void deleteAdvocateById(int id);
}

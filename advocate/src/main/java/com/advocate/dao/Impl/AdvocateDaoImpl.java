package com.advocate.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advocate.dao.AdvocateDao;
import com.advocate.entity.Advocate;


public class AdvocateDaoImpl implements AdvocateDao {

	private List<Advocate> advocates = new ArrayList<>();

	@Override
	public boolean addAdvocate(Advocate advocate) throws SQLException {
	
		return advocates.add(advocate);
	}

	@Override
	public List<Advocate> getAllAdvocates() {
		
		return 	advocates;
				
	}
	@Override
	public void editAdvocate(int id, Advocate advocate) {
		
		
	}

	@Override
	public Advocate getAdvocateById(int id) {
	
		return new Advocate();
	}

	@Override
	public void deleteAdvocateById(int id) {
	
	}

}

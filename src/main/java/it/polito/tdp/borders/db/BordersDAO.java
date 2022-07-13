package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<String, Country> map) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!map.containsKey(rs.getString("StateAbb"))) {
					Country c = new Country(rs.getString("StateAbb"), rs.getInt("ccode"), rs.getString("StateNme"));
					map.put(c.getStateAbb(), c);
				}

			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		String sql = "SELECT c.state1ab, c.state2ab, c.`year` FROM contiguity AS c WHERE c.`year`<=? AND c.conttype=1";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Border b = new Border(rs.getString("state1ab"), rs.getString("state2ab"));
				result.add(b);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}

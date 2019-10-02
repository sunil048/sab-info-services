/**
 * 
 */
package com.system;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sunil
 *
 * DataSourceProperties.java Oct 2, 2019 5:31:06 PM
 */
@RestController
@RequestMapping("/sys/")
public class DataSourceProperties {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	List<DataSource> datasources;
	
	@GetMapping("/datasource")
	public Object test() {
		Map <Object,Object> response1 = new LinkedHashMap <Object,Object>();
		try {
			Connection con;
			int count = 0;
			for (DataSource dataSource : datasources) {
				count++;
				Map <Object,Object> response = new LinkedHashMap <Object,Object>();
				con = dataSource.getConnection();
			    response.put("DATASOURCE", con.toString());
			   // response.put("METADATA", con.getMetaData());
			   // response.put("SCHEMA", con.getSchema());
			   // Properties prop = con.getClientInfo();
			  //  response.putAll(prop);
			   // response.put("TIMEOUT", con.getNetworkTimeout());
			    response1.put("DATASOURCE"+count, response);
			}
			return response1;
		} catch (SQLException e) {
			response1.put("Error",e.getMessage());
			return response1;
		}
	}
}

// Importing package to code module
package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
// Importing required classes
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping(path = "/tenants")
public class TenantController {

	@PostMapping(
	        path = "",
	        consumes = "application/json",
	        produces = "application/json")
	public ResponseEntity<String> addTenant(@RequestBody Tenant tenant, HttpServletResponse response)
	{
		HttpHeaders headers = new HttpHeaders();
        
        
		String url = "jdbc:mysql://localhost:3306/tanentinformation";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
             
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//            String sqlStmt = String.format("INSERT INTO tbl_tenant (F_NAME, L_NAME, PURPOSE, EMAIL, GOVERNMENT_ID, ADDRESS, MOB_NO) "
//            		+ "VALUES ('%s', '%s', '%s', '%s', %2d, '%s', %2d);",
//            		tenant.getFirstName(),tenant.getLastName(),tenant.getpurpose(),tenant.getEmail(),tenant.getGovermentId(),
//            		tenant.getAddress(),tenant.getMobNumber());
            
//            )
//            boolean result = statement.execute(sqlStmt);
			String SQL = "{call sp_insert_tenant (?, ?, ?, ?, ?, ?, ?)}";
			cstmt = connection.prepareCall (SQL);
			cstmt.setString(1, tenant.getFirstName());
			cstmt.setString(2, tenant.getLastName());
			cstmt.setString(3, tenant.getpurpose());
			cstmt.setString(4, tenant.getEmail());
			cstmt.setInt(5, tenant.getGovermentId());
			cstmt.setString(6, tenant.getAddress());
			cstmt.setInt(7, tenant.getMobNumber());
			cstmt.execute();
           
            connection.close();
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			
			return ResponseEntity.internalServerError().headers(headers).body(e.getMessage());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		// Print statement
        

        return ResponseEntity.ok().headers(headers).body(tenant.toString());
	}
	
	@GetMapping(
	        path = "",
	        produces = "application/json")
	public ArrayList<Tenant> getTenant()
	{
		String url = "jdbc:mysql://localhost:3306/tanentinformation";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

			String SQL = "{call sp_get_AllTenant()}";
			cstmt = connection.prepareCall (SQL);
			ResultSet resultSet = cstmt.executeQuery();
			ArrayList<Tenant> tenants = new ArrayList<>();
		
			while (resultSet.next()) {
				Integer tenanatId = resultSet.getInt("id");
			    String firstName = resultSet.getString("F_NAME");
			    String lastName = resultSet.getString("L_NAME");
			    String purpose = resultSet.getString("PURPOSE");
			    String email = resultSet.getString("EMAIL");
			    Integer governmentId = resultSet.getInt("GOVERNMENT_ID");
			    String address = resultSet.getString("ADDRESS");
			    Integer mobNumber = resultSet.getInt("MOB_NO");
			    
			    Tenant tenant = new Tenant(tenanatId,  firstName, lastName, email,mobNumber, governmentId, address, purpose);
	            tenants.add(tenant);
			    // Do something with the values
			}

            connection.close();
            
            return tenants;
            
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		// Print statement
		return null;
	}
	
	@PutMapping("/{id}")
	public String UpdateTenant(@RequestBody Tenant tenant, @PathVariable Integer id) {
		String url = "jdbc:mysql://localhost:3306/tanentinformation";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);
            String SQL = "{call sp_update_tenant (?, ?, ?,  ?, ?, ?, ?)}";
			cstmt = connection.prepareCall (SQL);
			cstmt.setInt(1,id);
			cstmt.setString(2, tenant.getFirstName());
			cstmt.setString(3, tenant.getLastName());
			cstmt.setString(4, tenant.getpurpose());
			cstmt.setString(5, tenant.getEmail());
//			cstmt.setInt(6, tenant.getGovermentId());  bcs its unique cant update
			cstmt.setString(6, tenant.getAddress());
			cstmt.setInt(7, tenant.getMobNumber());
			cstmt.execute();
	           
            connection.close();
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			return e.getMessage();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		// Print statement
		return "Tenant updated succesfully. "+tenant.toString();
	}

	  @DeleteMapping("/{id}")
	  public void deleteTenant(@PathVariable Integer id) {
		  String url = "jdbc:mysql://localhost:3306/tanentinformation";
	        String username = "root";
	        String password = "";
	        CallableStatement cstmt = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	
	            Connection connection = DriverManager.getConnection(url,username,password);
	            String SQL = "{call sp_delete_tenant (?)}";
				cstmt = connection.prepareCall (SQL);
				cstmt.setInt(1,id);
				cstmt.execute();
	            connection.close();
	        }  catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	  }
	  
	  @GetMapping(
	  path = "/{id}",
	  produces = "application/json")
	  public Tenant getTenantById(@PathVariable Integer id)
		{
			String url = "jdbc:mysql://localhost:3306/tanentinformation";
	        String username = "root";
	        String password = "";
	        CallableStatement cstmt = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection connection = DriverManager.getConnection(url,username,password);

				String SQL = "{call sp_get_tenantById (?)}";
				cstmt = connection.prepareCall (SQL);
				cstmt.setInt(1, id);
				ResultSet resultSet = cstmt.executeQuery();
				Tenant tenant = null;
			
				while (resultSet.next()) {
					Integer tenanatId = resultSet.getInt("id");
				    String firstName = resultSet.getString("F_NAME");
				    String lastName = resultSet.getString("L_NAME");
				    String purpose = resultSet.getString("PURPOSE");
				    String email = resultSet.getString("EMAIL");
				    Integer governmentId = resultSet.getInt("GOVERNMENT_ID");
				    String address = resultSet.getString("ADDRESS");
				    Integer mobNumber = resultSet.getInt("MOB_NO");
				    
				    tenant = new Tenant(tenanatId,  firstName, lastName, email,mobNumber, governmentId, address, purpose);
				    // Do something with the values
				}

	            connection.close();
	            
	            return tenant;
	        }  catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				

			}catch(Exception e)
			{
				e.printStackTrace();
			}
			// Print statement
			return null;
		}
}




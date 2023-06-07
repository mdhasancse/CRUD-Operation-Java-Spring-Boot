package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class TenantData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tanentinformation";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from tbl_tenant");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+
                        " "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6)+
                " "+resultSet.getString(7)+" "+resultSet.getString(8));
            }
            connection.close();
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");

		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import java.sql.Statement;
//import java.sql.ResultSet;

//public class TenantData {
//	public void GetTenants() {
//		try {
//			Connection con = DriverManager.getConnection("jdbc:mysql:///tanentinformation","root","");
//			Statement st = con.createStatement();
//			String query = "select * from tbl_tenant";
//			ResultSet rs = st.executeQuery(query);
//			
//			while(rs.next()) {
//				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+
//						rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+
//						rs.getString(7)+" "+rs.getString(8));
//			}
//			con.close();
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("error");
//
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//}



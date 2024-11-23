package com.nit.MultiMediaOperationInDB;

import java.io.*;
import java.sql.*;
import java.util.*;

public class RetrieveImageFromDB {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try(sc;) {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");

			PreparedStatement ps = con.prepareStatement("select * from InsertImage where id=?");
			
			System.out.println("Enter the image id : ");
			String id = sc.nextLine();
			
			ps.setString(1, id);
			
			 ResultSet rs = ps.executeQuery();
			 
			 if(rs.next()) {
				 
				 Blob b = rs.getBlob(2);
				 
				 byte[] by = b.getBytes(1,(int)b.length());
				 
				 System.out.println("Enter the file path where you want to store image : ");
				 String path = sc.nextLine();
				 
				 File f = new File(path);
				 
				 FileOutputStream fos = new FileOutputStream(f);
				 try(fos;){
				 fos.write(by);
				 
				 System.out.println("Image retrieve successfully..!");
				 }
				 
				 
			 }else {
				 System.err.println("Invalid image id or somthing went wrong..");
			 }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
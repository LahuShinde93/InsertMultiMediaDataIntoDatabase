package com.nit.MultiMediaOperationInDB;

import java.io.*;
import java.sql.*;
import java.util.*;

public class InsertImageIntoDB {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try(sc;) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");

			PreparedStatement pst = con.prepareStatement("insert into InsertImage values(?,?)");

			System.out.println("Enter the id for storing image : ");
			String id = sc.nextLine();

			pst.setString(1, id);

			System.out.println("Enter the FileName & FilePath  : ");
			String path = sc.nextLine();

			File f = new File(path);

			if (f.exists()) { // E:\E Drive\OneDrive\Desktop\java\myImage.jpg

				FileInputStream fis = new FileInputStream(f);
				pst.setBinaryStream(2, fis, f.length());

				int k = pst.executeUpdate();

				if (k > 0) { 
					System.out.println("Image inserted successfully..!");
				} else {
					System.err.println("somthing went wrong...");
				}

			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
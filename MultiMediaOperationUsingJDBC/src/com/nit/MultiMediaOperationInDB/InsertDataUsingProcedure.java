package com.nit.MultiMediaOperationInDB;

import java.sql.*;
import java.util.Scanner;

public class InsertDataUsingProcedure {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try (sc;) {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");
			CallableStatement cs = con.prepareCall("{call InsertCustData(?,?,?,?,?,?,?,?)}");

			 
			System.out.println("Enter Customer id : ");
			int cid = sc.nextInt();

			System.out.println("Enter the Fname : ");
			String fname = sc.next();

			System.out.println("Enter the Lname : ");
			String lname = sc.nextLine();
			lname = sc.nextLine();

			System.out.println("Enter House no : ");
			String hno = sc.nextLine();

			System.out.println("Enter the city : ");
			String city = sc.nextLine();

			System.out.println("Enter the pincode : ");
			long pincode = sc.nextLong();

			System.out.println("Enter the mail id : ");
			String mid = sc.nextLine();
			mid = sc.nextLine();

			System.out.println("Enter the phone Number : ");
			Long pno = sc.nextLong();

			cs.setInt(1, cid);
			cs.setString(2, fname);
			cs.setString(3, lname);
			cs.setString(4, hno);
			cs.setString(5, city);
			cs.setLong(6, pincode);
			cs.setString(7, mid);
			cs.setLong(8, pno);
			
			cs.execute();
			
			System.out.println("Data inserted successfully..!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

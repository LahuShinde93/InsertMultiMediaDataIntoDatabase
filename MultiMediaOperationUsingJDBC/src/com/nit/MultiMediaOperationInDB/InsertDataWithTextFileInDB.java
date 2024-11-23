package com.nit.MultiMediaOperationInDB;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class InsertDataWithTextFileInDB {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try (sc;) {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");

			PreparedStatement ps = con.prepareStatement("insert into empInfo values(?,?,?,?,?,?)");

			System.out.println("Enter the Emp id : ");
			int eid = sc.nextInt();

			System.out.println("Enter the emp name : ");
			String ename = sc.nextLine();
			ename = sc.nextLine();

			System.out.println("Enter the emp Address:; ");
			String address = sc.nextLine();

			System.out.println("Enter the emp email : ");
			String mail = sc.nextLine();

			System.out.println("Enter the phone No : ");
			long pno = sc.nextLong();

			System.out.println("Enter the file path from which file you want to insert : ");
			String path = sc.nextLine();
			path = sc.nextLine();
			// "E:\\E Drive\\OneDrive\\Desktop\\java\\DemoResume.txt";

			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setString(3, address);
			ps.setString(4, mail);
			ps.setLong(5, pno);

			File f = new File(path);

			if (f.exists()) {

				FileInputStream fis = new FileInputStream(f);
				try (fis;) {

					ps.setBinaryStream(6, fis, fis.available());
					int k = ps.executeUpdate();

					if (k > 0) {
						System.out.println("Data inserted Successfully..!");
					} else {
						System.err.println("somthin wrong..");
					}

				}
			} else {

				System.err.println("File not exist");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

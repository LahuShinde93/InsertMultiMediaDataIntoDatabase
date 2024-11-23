package com.nit.MultiMediaOperationInDB;

import java.io.*;
import java.sql.*;
import java.util.*;

public class RetrieveFileFromDB {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (sc) {

            // Establish database connection
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");

            // Corrected SQL query
            PreparedStatement ps = con.prepareStatement("SELECT EMPRESUME FROM empInfo WHERE EMPID=?");

            // Get the file ID from the user
            System.out.println("Enter the file ID: ");
            int id = sc.nextInt();
            ps.setInt(1, id);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Retrieve BLOB from the result set
                Blob blob = rs.getBlob("EMPRESUME");

                // Convert the BLOB data to a byte array
                byte[] byteData = blob.getBytes(1, (int) blob.length());

                // Ask the user for the file path to store the data
                System.out.println("Enter the file path where you want to store the text data: ");
                sc.nextLine(); // Consume newline character
                String path = sc.nextLine();

                // Create a File object
                File file = new File(path);

                // Use FileOutputStream to write the data to the file
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(byteData);
                    System.out.println("Text file retrieved and saved successfully!");
                }

            } else {
                System.err.println("Invalid file ID or something went wrong.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

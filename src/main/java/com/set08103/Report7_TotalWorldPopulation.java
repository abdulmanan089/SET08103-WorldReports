package com.set08103;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report7_TotalWorldPopulation {
    public static void generate() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT SUM(Population) AS WorldPopulation FROM country";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Total World Population:");
            if (rs.next()) {
                long total = rs.getLong("WorldPopulation");
                System.out.println("World Population: " + total);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

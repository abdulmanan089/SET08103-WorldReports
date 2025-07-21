package com.set08103;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report1_CountriesByPopulation {
    public static void generate() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Name, Population FROM country ORDER BY Population DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Countries by Population:");
            while (rs.next()) {
                String name = rs.getString("Name");
                int pop = rs.getInt("Population");
                System.out.println(name + " - " + pop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

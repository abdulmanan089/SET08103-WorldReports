package com.set08103;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Report4_TopNCitiesByPopulation {
    public static void generate() {
        // For Docker environment, use default value instead of user input
        int n = 10; // Default to top 10 cities
        System.out.println("Showing top " + n + " cities (default value for Docker environment)");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Name, Population FROM city ORDER BY Population DESC LIMIT " + n;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Top " + n + " Cities by Population:");
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

package com.set08103;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report3_CapitalCitiesByPopulation {
    public static void generate() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = """
                SELECT city.Name AS CityName, country.Name AS CountryName, city.Population
                FROM city
                JOIN country ON city.ID = country.Capital
                ORDER BY city.Population DESC
                """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Capital Cities by Population:");
            while (rs.next()) {
                String city = rs.getString("CityName");
                String country = rs.getString("CountryName");
                int pop = rs.getInt("Population");
                System.out.println(city + " (" + country + ") - " + pop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

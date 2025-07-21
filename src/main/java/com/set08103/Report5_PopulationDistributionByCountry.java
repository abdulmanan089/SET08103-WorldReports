package com.set08103;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report5_PopulationDistributionByCountry {

    public static void generate() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = """
                SELECT country.Name AS Country,
                       country.Population AS TotalPopulation,
                       IFNULL(SUM(city.Population), 0) AS PeopleInCities,
                       (country.Population - IFNULL(SUM(city.Population), 0)) AS PeopleNotInCities
                FROM country
                LEFT JOIN city ON country.Code = city.CountryCode
                GROUP BY country.Name, country.Population
                ORDER BY country.Name;
            """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population Distribution by Country:");
            while (rs.next()) {
                String name = rs.getString("Country");
                int total = rs.getInt("TotalPopulation");
                int inCities = rs.getInt("PeopleInCities");
                int notInCities = rs.getInt("PeopleNotInCities");
                double cityPercent = (total > 0) ? (inCities * 100.0 / total) : 0;
                double nonCityPercent = (total > 0) ? (notInCities * 100.0 / total) : 0;

                System.out.printf("%s | Total: %d | In Cities: %d (%.2f%%) | Not in Cities: %d (%.2f%%)%n",
                        name, total, inCities, cityPercent, notInCities, nonCityPercent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

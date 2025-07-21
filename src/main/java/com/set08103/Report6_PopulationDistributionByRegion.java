package com.set08103;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report6_PopulationDistributionByRegion {
    public static void generate() {
        System.out.println("Population Distribution by Region:\n");

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String query = """
                    SELECT region,
                           SUM(country.population) AS total_population,
                           SUM(city.population) AS population_in_cities,
                           SUM(country.population) - SUM(city.population) AS population_not_in_cities
                    FROM country
                    LEFT JOIN city ON country.code = city.countrycode
                    GROUP BY region
                    ORDER BY total_population DESC
                    """;

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String region = rs.getString("region");
                long total = rs.getLong("total_population");
                long inCities = rs.getLong("population_in_cities");
                long notInCities = rs.getLong("population_not_in_cities");

                System.out.printf("Region: %s%n", region);
                System.out.printf("  Total Population: %,d%n", total);
                double percentInCities = (inCities * 100.0) / total;
                double percentNotInCities = (notInCities * 100.0) / total;
                System.out.printf("  In Cities: %,d (%.2f%%)%n", inCities, percentInCities);
                System.out.printf("  Not in Cities: %,d (%.2f%%)%n%n", notInCities, percentNotInCities);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error in Report 6: " + e.getMessage());
        }
    }
}

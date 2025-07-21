package com.set08103;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report9_LanguagePopulation {
    public static void generate() {
        try {
            Connection con = DBConnection.getConnection();

            // Statement for language-wise population
            Statement stmt1 = con.createStatement();

            String query = "SELECT Language, SUM(Percentage * country.Population / 100) AS Speakers " +
                    "FROM countrylanguage " +
                    "JOIN country ON country.Code = countrylanguage.CountryCode " +
                    "WHERE Language IN ('Chinese', 'English', 'Spanish') " +
                    "GROUP BY Language ORDER BY Speakers DESC";

            ResultSet rs = stmt1.executeQuery(query);

            // Statement for world population
            Statement stmt2 = con.createStatement();
            String worldPopQuery = "SELECT SUM(Population) AS TotalWorldPopulation FROM country";
            ResultSet rsWorld = stmt2.executeQuery(worldPopQuery);

            long worldPopulation = 0;
            if (rsWorld.next()) {
                worldPopulation = rsWorld.getLong("TotalWorldPopulation");
            }

            System.out.println("Language Population Statistics:");
            while (rs.next()) {
                String language = rs.getString("Language");
                long speakers = rs.getLong("Speakers");
                double percentage = (speakers / (double) worldPopulation) * 100;

                System.out.printf("%s - Speakers: %d (%.2f%% of world population)%n",
                        language, speakers, percentage);
            }

        } catch (Exception e) {
            System.out.println("Error fetching language population data.");
            e.printStackTrace();
        }
    }
}

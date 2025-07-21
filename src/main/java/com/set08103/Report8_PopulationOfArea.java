package com.set08103;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report8_PopulationOfArea {
    public static void generate() {
        // Generate all population reports automatically (no user input needed for Docker)
        generateContinentPopulation();
        generateRegionPopulation();
        generateCountryPopulation();
        generateDistrictPopulation();
        generateCityPopulation();
    }

    private static void generateContinentPopulation() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Continent, SUM(Population) AS TotalPopulation FROM country GROUP BY Continent ORDER BY TotalPopulation DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population by Continent:");
            System.out.println("================================");
            System.out.printf("%-20s %15s%n", "Continent", "Population");
            System.out.println("================================");

            while (rs.next()) {
                String continent = rs.getString("Continent");
                long population = rs.getLong("TotalPopulation");
                System.out.printf("%-20s %,15d%n", continent, population);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error generating continent population: " + e.getMessage());
        }
    }

    private static void generateRegionPopulation() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Region, SUM(Population) AS TotalPopulation FROM country GROUP BY Region ORDER BY TotalPopulation DESC LIMIT 10";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population by Region (Top 10):");
            System.out.println("================================");
            System.out.printf("%-25s %15s%n", "Region", "Population");
            System.out.println("================================");

            while (rs.next()) {
                String region = rs.getString("Region");
                long population = rs.getLong("TotalPopulation");
                System.out.printf("%-25s %,15d%n", region, population);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error generating region population: " + e.getMessage());
        }
    }

    private static void generateCountryPopulation() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT 10";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population by Country (Top 10):");
            System.out.println("================================");
            System.out.printf("%-25s %15s%n", "Country", "Population");
            System.out.println("================================");

            while (rs.next()) {
                String country = rs.getString("Name");
                long population = rs.getLong("Population");
                System.out.printf("%-25s %,15d%n", country, population);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error generating country population: " + e.getMessage());
        }
    }

    private static void generateDistrictPopulation() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT District, SUM(Population) AS TotalPopulation FROM city GROUP BY District ORDER BY TotalPopulation DESC LIMIT 10";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population by District (Top 10):");
            System.out.println("=================================");
            System.out.printf("%-25s %15s%n", "District", "Population");
            System.out.println("=================================");

            while (rs.next()) {
                String district = rs.getString("District");
                long population = rs.getLong("TotalPopulation");
                System.out.printf("%-25s %,15d%n", district, population);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error generating district population: " + e.getMessage());
        }
    }

    private static void generateCityPopulation() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Name, Population FROM city ORDER BY Population DESC LIMIT 10";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Population by City (Top 10):");
            System.out.println("=============================");
            System.out.printf("%-25s %15s%n", "City", "Population");
            System.out.println("=============================");

            while (rs.next()) {
                String city = rs.getString("Name");
                long population = rs.getLong("Population");
                System.out.printf("%-25s %,15d%n", city, population);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error generating city population: " + e.getMessage());
        }
    }
}

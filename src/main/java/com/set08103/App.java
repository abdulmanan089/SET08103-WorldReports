package com.set08103;

public class App {
    public static void main(String[] args) {
        System.out.println("=== WORLD REPORTS APPLICATION ===");
        System.out.println("Generating all 8 required reports...\n");

        // Report 1: All countries by population (largest to smallest)
        System.out.println("REPORT 1:");
        Report1_CountriesByPopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 2: All cities by population (largest to smallest)
        System.out.println("REPORT 2:");
        Report2_CitiesByPopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 3: All capital cities by population (largest to smallest)
        System.out.println("REPORT 3:");
        Report3_CapitalCitiesByPopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 4: Top N cities by population (user input required)
        System.out.println("REPORT 4:");
        Report4_TopNCitiesByPopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 5: Population distribution by country
        System.out.println("REPORT 5:");
        Report5_PopulationDistributionByCountry.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 6: World population
        System.out.println("REPORT 6:");
        Report7_TotalWorldPopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 7: Population by area (continent, region, country, district, city)
        System.out.println("REPORT 7:");
        Report8_PopulationOfArea.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        // Report 8: Language speakers (Chinese, English, Spanish)
        System.out.println("REPORT 8:");
        Report9_LanguagePopulation.generate();
        System.out.println("\n" + "=".repeat(80) + "\n");

        System.out.println("All reports generated successfully!");
    }
}

package com.set08103;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Report8_PopulationOfArea {
    public static void generate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Area Type:");
        System.out.println("1. Continent");
        System.out.println("2. Region");
        System.out.println("3. Country");
        System.out.println("4. District");
        System.out.println("5. City");
        System.out.print("Enter choice (1-5): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String column = null;
        String table = null;
        switch (choice) {
            case 1:
                column = "continent";
                table = "country";
                break;
            case 2:
                column = "region";
                table = "country";
                break;
            case 3:
                column = "name";
                table = "country";
                break;
            case 4:
                column = "district";
                table = "city";
                break;
            case 5:
                column = "name";
                table = "city";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter name of " + column + ": ");
        String name = scanner.nextLine();

        String query = String.format("SELECT SUM(population) AS total FROM %s WHERE %s = ?", table, column);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long population = rs.getLong("total");
                System.out.printf("Total population of %s '%s': %,d%n", column, name, population);
            } else {
                System.out.println("No data found.");
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

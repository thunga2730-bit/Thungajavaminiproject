package EmployeeManager;

import java.sql.*;

public class EmployeeOperations {
    private static final Connection c = DBConnection.getConnection();

    // CREATE: Add a new employee
    public static void addEmployee(int id, String name, String dept, double salary, String designation, String contact) {
        String query = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setDouble(4, salary);
            ps.setString(5, designation);
            ps.setString(6, contact);
            
            ps.executeUpdate();
            System.out.println("Employee inserted successfully: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Display all employees
    public static void viewEmployees() {
        String query = "SELECT * FROM employees";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            
            System.out.println("\n--- EMPLOYEE RECORDS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Dept: " + rs.getString("department") +
                        " | Salary: " + rs.getDouble("salary") +
                        " | Designation: " + rs.getString("designation") +
                        " | Contact: " + rs.getString("contact_details"));
            }
            System.out.println("-------------------------\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: Update employee salary and designation by ID
    public static void updateEmployee(int id, double newSalary, String newDesignation) {
        String query = "UPDATE employees SET salary = ?, designation = ? WHERE id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDouble(1, newSalary);
            ps.setString(2, newDesignation);
            ps.setInt(3, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee ID " + id + " updated successfully.");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Remove an employee by ID
    public static void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee ID " + id + " deleted successfully.");
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 1. Insert sample records
        addEmployee(101, "Alice Smith", "IT", 75000.00, "Software Engineer", "alice@example.com");
        addEmployee(102, "Bob Jones", "HR", 55000.00, "HR Executive", "+1-555-0192");
        addEmployee(103, "Charlie Brown", "Finance", 65000.00, "Financial Analyst", "charlie@example.com");

        // 2. Read all records
        viewEmployees();

        // 3. Update employee record
        updateEmployee(101, 85000.00, "Senior Software Engineer");

        // 4. Delete employee record
        deleteEmployee(102);

        // 5. Read remaining records
        viewEmployees();
    }
}

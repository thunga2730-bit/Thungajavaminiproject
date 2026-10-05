package EmployeeManager;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws Exception {
        Connection c = DBConnection.getConnection();
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS employees (
                id INT PRIMARY KEY,
                name VARCHAR(100),
                department VARCHAR(50),
                salary DOUBLE,
                designation VARCHAR(50),
                contact_details VARCHAR(100)
            )
            """;
        Statement s = c.createStatement();
        s.executeUpdate(createTableSQL);
        System.out.println("Employees Table Created Successfully");
    }
}

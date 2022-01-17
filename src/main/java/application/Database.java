package application;

import java.io.File;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Database {

    private final String PATH;
    private final String url;

    private final DecimalFormat df;

    public Database(String PATH, String fileName) {
        this.PATH = PATH;
        this.url = "jdbc:sqlite:" + PATH + fileName;

        this.df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#,##0.00;(#,##0.00)");
    }

    // Connect to the database
    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    // Check for existing database and create a new one if an existing one is not found
    public void createDatabase() {
        File directory = new File(PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }

        try (Connection conn = this.connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created with the "
                    + meta.getDriverName() + " driver.\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables() {
        String sqlAssets = """
                CREATE TABLE IF NOT EXISTS Asset (
                accountNumber INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
                name varchar(255) NOT NULL,
                type varchar(255) DEFAULT 'Asset',
                balance double DEFAULT 0.00
                );""";

        String sqlLiabilities = """
                CREATE TABLE IF NOT EXISTS Liability (
                accountNumber INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 2000,
                name varchar(255) NOT NULL,
                type varchar(255) DEFAULT 'Liability',
                balance double DEFAULT 0.00
                );""";

        String sqlEquities = """
                CREATE TABLE IF NOT EXISTS Equity (
                accountNumber INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 3000,
                name varchar(255) NOT NULL,
                type varchar(255) DEFAULT 'Equity',
                balance double DEFAULT 0.00
                );""";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlAssets);
            stmt.execute(sqlLiabilities);
            stmt.execute(sqlEquities);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAccount(String name, String type) {
        String sql = "INSERT INTO " + type + "(name) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAccount(int accountNumber, String type) {
        String sql = "DELETE FROM " + type + " WHERE accountNumber = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints each account in the specified account type table for the printChartOfAccounts() method
    public void printByAccountType(String type) {
        ArrayList<String> list = selectByAccountType(type);

        if (list.isEmpty()) {
            System.out.println("* No " + type + " accounts exist *");
        } else {
            for (String account : list) {
                System.out.println(account);
            }
        }
    }

    // Returns a list of accounts based on type (Asset, Liability, or Equity)
    public ArrayList<String> selectByAccountType(String type) {
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT accountNumber, name, balance FROM " + type;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                accounts.add(rs.getInt("accountNumber")
                            + " - "
                            + rs.getString("name")
                            + ": "
                            + df.format(rs.getDouble("balance")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return accounts;
    }

    public ArrayList<String> getAllAccounts() {
        ArrayList<String> accounts = new ArrayList<>();

        accounts.addAll(selectByAccountType("Asset"));
        accounts.addAll(selectByAccountType("Liability"));
        accounts.addAll(selectByAccountType("Equity"));

        return accounts;
    }

    // Returns last account added to the specified account type table
    public void selectLastRecord(String type) {
        String sql = "SELECT accountNumber, name, balance FROM " + type + " ORDER BY accountNumber DESC LIMIT 1";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println(
                rs.getInt("accountNumber") +  " - " +
                rs.getString("name") + ": " +
                df.format(rs.getDouble("balance")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Returns the account number for the last account added to the specified account type table
    public Integer selectLastAccountNumber(String type) {
        int lastAccountNumber = -1;

        String sql = "SELECT accountNumber, name, balance FROM " + type + " ORDER BY accountNumber DESC LIMIT 1";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            lastAccountNumber = rs.getInt("accountNumber");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastAccountNumber;
    }

    // Enables updating account balances for the double entry method
    public void updateBalance(int accountNumber, double balance) {
        String sql = """
                UPDATE accounts
                SET balance = ?
                WHERE accountNumber = ?
                """;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, balance);
            pstmt.setInt(2, accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Returns whether an account with the specified account number exists in the specified type table
    public boolean checkAccountExists(int accountNumber, String type) {
        String sql = "SELECT FROM " + type + " WHERE accountNumber = " + accountNumber;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Returns whether any accounts exist in the Chart of Accounts
    public boolean checkIfAllTablesAreEmpty() {

        return checkIfTableIsEmpty("Asset")
                && checkIfTableIsEmpty("Liability")
                && checkIfTableIsEmpty("Equity");
    }

    // Returns whether any accounts exist in the specified account type table
    public boolean checkIfTableIsEmpty(String type) {
        ArrayList<String> list = selectByAccountType(type);

        return list.isEmpty();
    }
}

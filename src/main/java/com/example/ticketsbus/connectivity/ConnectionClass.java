package com.example.ticketsbus.connectivity;

import org.controlsfx.dialog.ExceptionDialog;

import java.sql.*;

public class ConnectionClass {
    public Connection connection;

    public Connection getConnection() {
        String dbName = "bus";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, password);

        } catch (SQLException e) {
            showExceptionDialog(e);
        } catch (ClassNotFoundException e) {
            showExceptionDialog(e);
        }
        return connection;
    }

    public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (connect != null)
                connect.close();
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            showExceptionDialog(e);
        }
    }

    public void close(Connection connect, PreparedStatement pstmt) {
        try {
            close(connect, pstmt, null);
        } catch (Exception e) {
            showExceptionDialog(e);
        }
    }

    public void close(PreparedStatement pstmt) {
        try {
            close(null, pstmt, null);
        } catch (Exception e) {
            showExceptionDialog(e);
        }
    }

    private void showExceptionDialog(Exception exception) {
        ExceptionDialog dialog = new ExceptionDialog(exception);
        dialog.setTitle("Exception");
        dialog.setHeaderText("An exception occurred");
        dialog.setContentText("Please see the details below:");
        dialog.showAndWait();
    }
}

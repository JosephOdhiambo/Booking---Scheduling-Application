package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BalanceController implements Initializable {
    @FXML
    private TableColumn<BalanceModel, Integer> colBalance;

    @FXML
    private TableColumn<BalanceModel, String> colUsername;

    @FXML
    private TableView<BalanceModel> tableUser;

    @FXML
    private TextField txtBalance;

    String query;

    DataAccessObject dao;

    @FXML
    private ComboBox<String> comboUsername;

    @FXML
    private Button updateBalance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new DataAccessObject();
        refreshUser();
        loadUser();
        updateBalance.setOnAction(e->{
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                // Create a SQL query to update the balance
                String updateQuery = "UPDATE user SET balance = balance +'"+ txtBalance.getText()+"' WHERE uname = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, comboUsername.getSelectionModel().getSelectedItem());

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Balance updated successfully.");
                    refreshUser();
                    comboUsername.getSelectionModel().clearSelection();
                    txtBalance.clear();
                } else {
                    System.out.println("No user found with the given username.");
                }

                // Close the database connection
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }


    private void initTableUser() {
        colUsername.setCellValueFactory(cell -> cell.getValue().getUsername());
        colBalance.setCellValueFactory(cell ->cell.getValue().getBalance());
    }

    private void refreshUser() {
        dao = new DataAccessObject();
        initTableUser();
        query = "SELECT `uname`,`balance` FROM `user`";
        tableUser.setItems(dao.getUserData(query));


    }


    private void loadUser() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        comboUsername.getItems().clear();
        comboUsername.getSelectionModel().clearSelection();

        // Execute the SELECT query
        String query = "SELECT uname FROM user";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Populate the ComboBox with the retrieved data
            while (resultSet.next()) {
                String serviceName = resultSet.getString("uname");
                comboUsername.getItems().add(serviceName);
            }
            comboUsername.getItems().add("");
        } catch (SQLException e) {
            System.out.println("");
        }

    }
}

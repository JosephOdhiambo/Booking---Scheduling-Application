package com.example.ticketsbus;
import com.example.ticketsbus.connectivity.ConnectionClass;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    private TableView<Booking> bookingRecords;
    @FXML
    private TableColumn<Booking, String> nameColumn;
    @FXML
    private TableColumn<Booking, String> phoneColumn;
    @FXML
    private TableColumn<Booking, String> sourceColumn;
    @FXML
    private TableColumn<Booking, String> destinationColumn;
    @FXML
    private TableColumn<Booking, String> serviceColumn;
    @FXML
    private TableColumn<Booking, String> dateColumn;
    @FXML
    private TableColumn<Booking, String> seatsColumn;
    @FXML
    private TableColumn<Booking, String> amountColumn;

    @FXML
    private JFXComboBox<String> comboUsers;
    @FXML
    private JFXComboBox<String> comboServ;

    @FXML
    private JFXComboBox<String> comboSource;

    @FXML
    private JFXComboBox<String> comboDestination;

    Booking b;

    DataAccessObject dao;

    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtSeats;
    @FXML
    private TextField txtAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        loadServiceBooking();
        loadUserBooking();
        refreshBook();
        // Load data from the database
        loadBookingData();

        nameColumn.setCellValueFactory(data -> data.getValue().getName());
        phoneColumn.setCellValueFactory(data -> data.getValue().getPhone());
        sourceColumn.setCellValueFactory(data -> data.getValue().getSource());
        destinationColumn.setCellValueFactory(data -> data.getValue().getDestination());
        serviceColumn.setCellValueFactory(data -> data.getValue().getService());
        dateColumn.setCellValueFactory(data -> data.getValue().getDate());
        seatsColumn.setCellValueFactory(data -> data.getValue().getSeats());
        amountColumn.setCellValueFactory(data -> data.getValue().getAmount());

    }

    private void refreshBook() {
        bookingRecords.getSelectionModel().clearSelection();
        comboUsers.getSelectionModel().clearSelection();
        txtPhone.clear();
        comboSource.getSelectionModel().clearSelection();
        comboDestination.getSelectionModel().clearSelection();
        comboServ.getSelectionModel().clearSelection();
        txtDate.clear();
        txtSeats.clear();
        txtAmount.clear();
    }


    private void loadBookingData() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            if (connection != null) {
                String query = "SELECT name,phone,source,destination,service,date,seats,amount FROM bookings";
                PreparedStatement pst = connection.prepareStatement(query);
                ResultSet resultSet = pst.executeQuery();

                ObservableList<Booking> bookingList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    Booking booking = new Booking(
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("source"),
                            resultSet.getString("destination"),
                            resultSet.getString("service"),
                            resultSet.getString("date"),
                            resultSet.getString("seats"),
                            resultSet.getString("amount")
                    );
                    bookingList.add(booking);
                }

                bookingRecords.setItems(bookingList);
            } else {
                // Handle the case when the connection is null
                System.out.println("Failed to establish a database connection");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }

    public void sumitBooking(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        b = bookingRecords.getSelectionModel().getSelectedItem();
        String un = comboUsers.getSelectionModel().getSelectedItem();
        String pn = txtPhone.getText();
        String sr = comboSource.getSelectionModel().getSelectedItem();
        String des = comboDestination.getSelectionModel().getSelectedItem();
        String ser = comboServ.getSelectionModel().getSelectedItem();
        String da = txtDate.getText();
        String se = txtSeats.getText();
        String am = txtAmount.getText();
        try {
            PreparedStatement ps;
            String q = "UPDATE bookings SET name = ?, phone = ?, source = ?, destination = ?, service = ?, date = ?, seats = ?, amount = ? WHERE amount='"+am+"'";
            ps = connection.prepareStatement(q);
            ps.setString(1, un);
            ps.setString(2, pn);
            ps.setString(3, sr);
            ps.setString(4, des);
            ps.setString(5, ser);
            ps.setString(6, da);
            ps.setString(7, se);
            ps.setString(8, am);
            int count = ps.executeUpdate();

            if (count > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Updated");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    loadBookingData();
                    refreshBook();
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void editBooking(ActionEvent actionEvent) {
        b = bookingRecords.getSelectionModel().getSelectedItem();
        if(b == null){
            showError("No row selected");
        }else{
            comboUsers.getSelectionModel().select(b.getName().get());
            txtPhone.setText(b.getPhone().get());
            comboSource.getSelectionModel().select(b.getSource().get());
            comboDestination.getSelectionModel().select(b.getDestination().get());
            comboServ.getSelectionModel().select(b.getService().get());
            txtDate.setText(b.getDate().get());
            txtSeats.setText(b.getSeats().get());
            txtAmount.setText(b.getAmount().get());
        }
    }

    private void loadUserBooking() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        // Execute the SELECT query
        String query = "SELECT uname FROM user";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Populate the ComboBox with the retrieved data
            while (resultSet.next()) {
                String serviceName = resultSet.getString("uname");
                comboUsers.getItems().add(serviceName);
            }
            comboUsers.getItems().add("");
        } catch (SQLException e) {
            System.out.println("");
        }

    }

    private void loadServiceBooking() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        // Execute the SELECT query
        String query = "SELECT service FROM search";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Populate the ComboBox with the retrieved data
            while (resultSet.next()) {
                String serviceName = resultSet.getString("service");
                comboServ.getItems().add(serviceName);
            }
            comboServ.getItems().add("");
        } catch (SQLException e) {
            System.out.println("");
        }

    }

    public void connect() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<String> fromData = FXCollections.observableArrayList();
        ObservableList<String> toData = FXCollections.observableArrayList();
        ObservableList<String> DateData = FXCollections.observableArrayList();
        ResultSet rS;
        PreparedStatement PST;
        PreparedStatement pst;
        ResultSet resultSet;
        try {
            PST = connection.prepareStatement("select * from search");
            pst = connection.prepareStatement("select * from bookk");
            rS = PST.executeQuery();
            resultSet = pst.executeQuery();


            while (rS.next()) {
                String Date = rS.getString("date");
                DateData.add(Date);
            }


            while (resultSet.next()) {
                String location = resultSet.getString("location");

                fromData.add(location);
                toData.add(location);
            }

            comboSource.setItems(fromData);
            comboDestination.setItems(toData);

            comboSource.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (toData.contains(newValue)) {
                        toData.remove(newValue);
                        comboDestination.setItems(toData);
                    }
                }
            });

            comboDestination.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (fromData.contains(newValue)) {
                        fromData.remove(newValue);
                        comboSource.setItems(fromData);
                    }
                }
            });

        } catch (SQLException e) {
            System.out.println("");
        }

    }

    private void showError(String message) {
        // You can replace this with your preferred method of showing an error message to the user,
        // for example, using a Dialog or an Alert
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    public void resetSelections(ActionEvent actionEvent) {
        refreshBook();
        loadBookingData();

    }

    void loadFXMLAndSetScene(String fxmlFilePath, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Image icon16 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_16px.png");
            Image icon32 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_32px.png");
            Image icon48 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_64px.png");

            window.getIcons().addAll(icon16, icon32, icon48);
            window.centerOnScreen();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(ActionEvent actionEvent) {
        dao = new DataAccessObject();
        b = bookingRecords.getSelectionModel().getSelectedItem();
        String am = txtAmount.getText();
        String sv = comboServ.getSelectionModel().getSelectedItem();
        String unme = comboUsers.getSelectionModel().getSelectedItem();

        // Create confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete this item?");

        // Customize button text
        ButtonType deleteButton = new ButtonType("Delete");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(deleteButton, cancelButton);

        // Wait for user input
        Optional<ButtonType> result = alert.showAndWait();

        // Check user's choice
        if (result.isPresent() && result.get() == deleteButton) {
            dao = new DataAccessObject();
            String query = "DELETE FROM bookings where amount = '" + am + "' AND service = '"+sv+"'";
            System.out.println(query);
            dao.saveData(query);
            showError("Deleted");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try {
                PreparedStatement ps;
                String q = "update seat_names SET picked = 0, uname = '' WHERE service = ?";
                ps = connection.prepareStatement(q);
                ps.setString(1, sv);
                ps.executeUpdate();


            } catch(SQLException e){
                e.printStackTrace();
            }

            loadBookingData();
            refreshBook();
        }
    }
}

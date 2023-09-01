package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static javafx.beans.binding.Bindings.select;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class ChairController implements Initializable {

    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXButton releaseChairs;

    @FXML
    private TableColumn<Chair, String> colPicked;

    @FXML
    private TableColumn<Chair, String> colSeatName;

    @FXML
    private TableColumn<Chair, String> colService;

    @FXML
    private TableColumn<Chair, String> colUname;

    @FXML
    private TableView<Chair> tableSeats;

    @FXML
    private TableView<CountModel> tblSeatCount;
    @FXML
    private TableColumn<CountModel, String> colCountService;
    @FXML
    private TableColumn<CountModel, Integer> colSeatCount;
    @FXML
    private TableColumn<CountModel, Integer> colAvailableSeats;
    @FXML
    private TableColumn<CountModel, Integer> colDifference;

    @FXML
    private JFXComboBox<Integer> comboPicked;

    @FXML
    private TextField txtSeatName;

    @FXML
    private JFXComboBox<String> comboService;
    @FXML
    private JFXComboBox<String> comboService1;
    @FXML
    private JFXComboBox<String> comboUsername;


    private DataAccessObject dao;

    private String query;

    private boolean EDIT=false, ADD=true;

    private String Picked, Service, Username;
    String Seat;
    ConnectionClass connectionClass;

    Connection connection;
    String columnSeat;

    @FXML
    private Label SeatNameLBL;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @FXML
    private TextField searchChairs;

    private ObservableList<Chair> chairData = FXCollections.observableArrayList();
    private FilteredList<Chair> filteredData;

    @FXML
    private JFXToggleButton toggleSearch;

    @FXML
    private TextField seatcount;

    @FXML
    private JFXToggleButton toggleCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtSeatName.setText("");
        btnNew.setOnAction(e->{
            initSeats();
            refreshSeats();
            initCount();
            refreshCount();
            initChairControls();
            EDIT = false;
            ADD = true;
        });
        loadUser();
        loadService();
        comboPicked.getItems().addAll(0, 1);
        dao = new DataAccessObject();
        initSeats();
        refreshSeats();
        initCount();
        refreshCount();
        releaseChairs.setOnAction(e->{
            dao = new DataAccessObject();
            String ComboServ = comboService1.getSelectionModel().getSelectedItem();
            query = "UPDATE `seat_names` SET `picked`= 0, `uname` = '' WHERE `service` = '"+ComboServ+"';";
            dao.saveData(query);
            loadService();
            initSeats();
            refreshSeats();
            initCount();
            refreshCount();
            comboService1.getSelectionModel().clearSelection();
        });

        filteredData = new FilteredList<>(dao.chairList, b -> true);

        searchChairs.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<Chair> predicate = chair -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                return chair.getSeatName().toString().toLowerCase().contains(searchKeyword)
                        || chair.getPicked().toString().toLowerCase().contains(searchKeyword)
                        || chair.getService().toString().toLowerCase().contains(searchKeyword)
                        || chair.getUname().toString().toLowerCase().contains(searchKeyword);
            };

            applyFilterAndSort(tableSeats, searchChairs, filteredData, predicate);
        });

        toggleSearch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                toggleSearch.setText("Off");
                searchChairs.setDisable(true);
            } else {
                toggleSearch.setText("On");
                searchChairs.setDisable(false);
            }
        });



        btnSubmit.setOnAction(e->{
            dao = new DataAccessObject();
            filteredData = new FilteredList<>(dao.chairList, b -> true);
            Chair selected = tableSeats.getSelectionModel().getSelectedItem();
            Seat = txtSeatName.getText();
            Picked = String.valueOf(comboPicked.getSelectionModel().getSelectedItem());
            Service = comboService.getSelectionModel().getSelectedItem();
            Username = comboUsername.getSelectionModel().getSelectedItem();
            if(EDIT){
                dao = new DataAccessObject();
                columnSeat = selected.getSeatName().get();
                query = "UPDATE `seat_names` SET `seatname`= '"+Seat+"', `picked` = '"+Picked+"', `service` = '"+Service+"', `uname` = '"+Username+"' WHERE `seat_names`.`seatname` = '"+columnSeat+"';";
                showError("Record updated succesfully");
                dao.saveData(query);
                resetFilterAndSort(tableSeats, searchChairs, filteredData);
            }
            else if(ADD){
                Integer count = Integer.valueOf(seatcount.getText());
                dao = new DataAccessObject();

                    for (int i = 0; i < count; i++) {
                        query = "INSERT INTO seat_names VALUES('" + generateRandomString(5) + "','" + Picked + "', '" + Service + "', '" + Username + "')";
                        // Execute your query here

                        // Show success message for each iteration
                        dao.saveData(query);
                        resetFilterAndSort(tableSeats, searchChairs, filteredData);
                    }
                    showError("Record inserted successfully");
                    resetFilterAndSort(tableSeats, searchChairs, filteredData);
                }
            refreshSeats();
            initSeats();
            initCount();
            refreshCount();
            initChairControls();
        });

        btnEdit.setOnAction(e->{
            EDIT = true;
            ADD = false;
            editSeatTable();
        });


        btnDelete.setOnAction(e->{
            deleteChair();
        });
    }

    private void deleteChair() {
        Chair selected = tableSeats.getSelectionModel().getSelectedItem();
        dao = new DataAccessObject();
        if(selected == null){
            showError("Error: No item selected!");
        }
        else {
            String NameSeat = selected.getSeatName().get();
            query = "DELETE FROM seat_names WHERE seatname = '" + NameSeat + "';";
            dao.saveData(query);
            showError("Record deleted succesfully");
            refreshSeats();
            initSeats();
            initCount();
            refreshCount();
            initChairControls();
        }
    }

    private void initChairControls() {
        txtSeatName.setText("");
        comboPicked.getSelectionModel().clearSelection();
        comboService.getSelectionModel().clearSelection();
        comboUsername.getSelectionModel().clearSelection();
        SeatNameLBL.setDisable(true);
        txtSeatName.setDisable(true);
        if(tableSeats.getSelectionModel().getSelectedIndex() != -1) {
            tableSeats.getSelectionModel().clearSelection();
        }
        initSeats();
    }

    private void loadService() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        // Create HashSet to store unique service names
        HashSet<String> uniqueServices = new HashSet<>();
        comboService.getItems().clear();
        comboService.getSelectionModel().clearSelection();
        comboService1.getItems().clear();
        comboService1.getSelectionModel().clearSelection();

        // Execute the SELECT query
        String query = "SELECT service FROM search";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Populate the ComboBox with the retrieved data
            while (resultSet.next()) {
                String serviceName = resultSet.getString("service");
                if (!uniqueServices.contains(serviceName)) {
                    comboService.getItems().add(serviceName);
                    comboService1.getItems().add(serviceName);
                    uniqueServices.add(serviceName);
                }
            }
        } catch (SQLException e) {
            System.out.println("");
        }
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

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private void editSeatTable() {
        dao= new DataAccessObject();
        Chair selected = tableSeats.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Error: No item selected!");
        } else {
            // Code to run when 'tableSeats' is selected
            txtSeatName.setText("");
            txtSeatName.setText(selected.getSeatName().get());
            comboPicked.getSelectionModel().select(Integer.valueOf(selected.getPicked().get()));
            comboService.getSelectionModel().select(selected.getService().get());
            comboUsername.getSelectionModel().select(selected.getUname().get());
            SeatNameLBL.setDisable(false);
            txtSeatName.setDisable(false);
        }
    }

    private void showError(String message) {
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text(message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("CLicked on event handler");
                    }
                });
        notificationBuilder.showConfirm();
    }


    private void initSeats() {
        colSeatName.setCellValueFactory(cell -> cell.getValue().getSeatName());
        colPicked.setCellValueFactory(cell -> cell.getValue().getPicked());
        colService.setCellValueFactory(cell -> cell.getValue().getService());
        colUname.setCellValueFactory(cell -> cell.getValue().getUname());
    }

    private void initCount(){
        colCountService.setCellValueFactory(cell -> cell.getValue().getServicE());
        colSeatCount.setCellValueFactory(cell -> cell.getValue().getSeatscount());
        colAvailableSeats.setCellValueFactory(cell -> cell.getValue().getAvailableSeats());
        colDifference.setCellValueFactory(cell -> cell.getValue().getDifference());
    }

    private void refreshSeats(){
        initSeats();
        query = "SELECT seatname, picked, service, uname FROM seat_names ORDER BY service ASC;\n";
        tableSeats.setItems(dao.getChairData(query));
    }


    private void refreshCount(){
        initCount();
        query = "SELECT seat_names.service, COUNT(*) AS seat_count, search.seat AS available_seats, (search.seat - COUNT(*)) AS difference\n" +
                "FROM seat_names \n" +
                "JOIN search \n" +
                "ON seat_names.service = search.service\n" +
                "GROUP BY service;\n";
        tblSeatCount.setItems(dao.getCountData(query));
    }


    public void chairInfo(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        String details = "As an administrator, you are able to release chairs occupied in a certain bus. When the bus has reached its destination, the system has to identify certain chairs as unoccupied. The admin is tasked with the duty of releasing certain buses so they can be booked by other users";
        alert.setHeaderText("Customize Chairs");
        alert.setContentText(details);
        alert.setTitle("Chairs");
        alert.show();
    }

    private static void applyFilterAndSort(
            TableView<Chair> tableSeats,
            TextField searchChairs,
            FilteredList<Chair> filteredData,
            Predicate<Chair> predicate
    ) {
        filteredData.setPredicate(predicate);

        SortedList<Chair> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableSeats.comparatorProperty());
        tableSeats.setItems(sortedData);
    }

    public static void resetFilterAndSort(
            TableView<Chair> tableSeats,
            TextField searchChairs,
            FilteredList<Chair> filteredData
    ) {
        // Clear the text in the searchChairs TextField
        searchChairs.clear();

        // Clear the predicate of filteredData
        filteredData.setPredicate(null);

        // Clear the items in the tableSeats
        tableSeats.setItems(null);
    }

    public void refreshService(ActionEvent actionEvent) {
        loadService();
    }

    public void refreshUsername(ActionEvent actionEvent) {
        loadUser();
    }




}

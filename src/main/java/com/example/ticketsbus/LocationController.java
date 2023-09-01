package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LocationController implements Initializable {

    @FXML
    private JFXButton btnDelete;


    @FXML
    private JFXButton btnSubmit;

    @FXML
    private TableColumn<Location, String> colLocation;

    @FXML
    private TableView<Location> tableLocation;

    @FXML
    private TextField txtLocation;

    private DataAccessObject dao;
    ConnectionClass connectionClass;
    Connection connection;

    String query;
    String Loc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new DataAccessObject();
        initLocations();
        refreshLocations();


        btnSubmit.setOnAction(e->{
            Loc = txtLocation.getText();
            if(Loc.isEmpty()){
                showError("The Location field is empty");
            } else{
                query = "INSERT INTO bookk VALUES('"+Loc+"')";
                dao.saveData(query);
                refreshLocations();
                txtLocation.setText("");
                showError("Location inserted succesfully");
            }
        });

        btnDelete.setOnAction(e -> {
            Location selected = tableLocation.getSelectionModel().getSelectedItem();
           if(selected == null){
               showError("Error: No item selected!");
           }else{
               String loc = selected.getLocation().get();

               Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirm Deletion");
               alert.setHeaderText("Are you sure you want to delete this record?");
               alert.setContentText("Location: " + loc);

               // Show the alert and wait for the user's response
               ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

               if (result == ButtonType.OK) {
                   String query = "DELETE FROM bookk WHERE location='" + loc + "'";
                   dao.saveData(query);
                   tableLocation.getSelectionModel().clearSelection();
                   refreshLocations();
                   showError("Location deleted succesfully");
               } else if(result == ButtonType.CANCEL){
                   tableLocation.getSelectionModel().clearSelection();
               }
           }
        });

    }

    private void initLocations() {
        colLocation.setCellValueFactory(cell -> cell.getValue().getLocation());
    }

    private void refreshLocations() {
        initLocations();
        query = "SELECT location FROM bookk";
        tableLocation.setItems(dao.getLocationData(query));
    }


    private void showError(String message) {
        // You can replace this with your preferred method of showing an error message to the user,
        // for example, using a Dialog or an Alert
//        Alert alert = new Alert(Alert.AlertType.NONE);
//        alert.setAlertType(Alert.AlertType.INFORMATION);
//        alert.setContentText(message);
//        alert.show();


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

    public void refreshView(ActionEvent actionEvent) {
        tableLocation.getSelectionModel().clearSelection();
        txtLocation.clear();
    }
}

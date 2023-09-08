package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.controlsfx.control.MaskerPane;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import jfxtras.scene.control.LocalTimePicker;
import org.controlsfx.control.NotificationPane;


import javafx.scene.Node;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.jsoup.Jsoup;


public class AdminController implements Initializable {
    @FXML
    private AnchorPane adminDrawer;

    @FXML
    private AnchorPane reportPane;
    @FXML
    private AnchorPane reports;
    @FXML
    private AnchorPane addChairs;
    @FXML
    private SplitPane reportAnchor;

    @FXML
    private Button adminNav;
    @FXML
    private Button btnAddChairs;


    @FXML
    private Button reportView;

    @FXML
    private JFXButton dBoard;

    @FXML
    private JFXButton mBooking;

    @FXML
    private FontAwesomeIconView nav;

    @FXML
    private AnchorPane bookingRecords;

    @FXML
    private AnchorPane addBus;


    @FXML
    private LocalTimePicker arrival;


    @FXML
    private DatePicker date;

    @FXML
    private LocalTimePicker depart;

    @FXML
    private TextField fare;

    @FXML
    private TextField seats;

    @FXML
    private TextField service;

    private boolean EDIT = false, ADD = true;
    PreparedStatement ps;
    @FXML
    private TableView<Service> tblService;
    @FXML
    private TableColumn<Service, String> sr;

    @FXML
    private TableColumn<Service, String> sour;
    @FXML
    private TableColumn<Service, String> dest;
    @FXML
    private TableColumn<Service, String> far;
    @FXML
    private TableColumn<Service, LocalTime> dept;
    @FXML
    private TableColumn<Service, LocalTime> arr;

    @FXML
    private TableColumn<Service, String> iFrame;
    @FXML
    private TableColumn<Service, Integer> sea;
    @FXML
    private TableColumn<Service, String> dat;

    private String query;
    private DataAccessObject dao;

    @FXML
    private JFXComboBox<String> source;
    @FXML
    private JFXComboBox<String> destination;

    Service s;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private AnchorPane busAnchor;

    @FXML
    private AnchorPane locationPane;

    @FXML
    private Button btnLocation;

    @FXML
    private WebView siteWeb;


    @FXML
    private SplitPane SplitPane;

    @FXML
    private Button aboutBTN;

    @FXML
    private AnchorPane aboutUs;


    @FXML
    private WebView siteWebServ;

    @FXML
    private Button btnServ;

    @FXML
    private AnchorPane ourServices;
    @FXML
    private AnchorPane mid;

    @FXML
    private TextField txtIframe;

    @FXML
    private Button editBus;

    @FXML
    private TextField searchBusServ;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableColumn<reportAvailableSeats, String> colReportService;

    @FXML
    private TableColumn<reportAvailableSeats, Integer> colReportAvailable;

    @FXML
    private TableView<reportAvailableSeats> tblUnpicked;


    @FXML
    private Label amountReceived;

    @FXML
    private AnchorPane tableAvaiable;
    @FXML
    private AnchorPane bargraph;

    @FXML
    private JFXToggleButton toggleSearch;
    FilteredList<Service> filteredData;

    @FXML
    private Label currDate;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currDate.setText(LocalDateTime.now().getMonth().toString()+", "+LocalDateTime.now().getDayOfMonth()+", "+LocalDateTime.now().getYear());
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart.setTitle("Picked Seats Count by Service");


        ConnectionClass connectionClass = new ConnectionClass();
        try (Connection connection = connectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT service, COUNT(*) AS picked_seats_count " +
                             "FROM seat_names " +
                             "WHERE picked = 1 " +
                             "GROUP BY service")) {

            // Populate the bar chart with data from the database
            while (resultSet.next()) {
                String service = resultSet.getString("service");
                int pickedSeatsCount = resultSet.getInt("picked_seats_count");
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(service, pickedSeatsCount));
                series.setName(service);
                barChart.getData().add(series);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        MaskerPane maskerPane = new MaskerPane();
        maskerPane.setText("Please wait...");
        AnchorPane.setTopAnchor(maskerPane, 0.0);
        AnchorPane.setRightAnchor(maskerPane, 0.0);
        AnchorPane.setBottomAnchor(maskerPane, 0.0);
        AnchorPane.setLeftAnchor(maskerPane, 0.0);
        mid.getChildren().addAll(maskerPane);
        maskerPane.setVisible(true);

        // Create a task to simulate a time-consuming process
        Task<Void> timeConsumingTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Simulate a time-consuming process
                Thread.sleep(5000);
                return null;
            }
        };

        timeConsumingTask.setOnSucceeded(event -> {
            // Hide the MaskerPane when the task is completed
            maskerPane.setVisible(false);
        });

        // Start the time-consuming task
        new Thread(timeConsumingTask).start();

        WebEngine webEngine = siteWeb.getEngine();
        WebEngine webEngine_1 = siteWebServ.getEngine();


        webEngine.setJavaScriptEnabled(true);
        webEngine_1.setJavaScriptEnabled(true);


        File htmlFile = new File("C:\\Users\\odhis\\IdeaProjects\\Booking---Scheduling-Application\\src\\main\\java\\com\\example\\ticketsbus\\Boutit.html");
        File htmlFile_1 = new File("C:\\Users\\odhis\\IdeaProjects\\Booking---Scheduling-Application\\src\\main\\java\\com\\example\\ticketsbus\\services.html");

        String fileURL = htmlFile.toURI().toString();
        String fileURL_1 = htmlFile_1.toURI().toString();

        webEngine.load(fileURL);
        webEngine_1.load(fileURL_1);

        dao = new DataAccessObject();
        connect();
        refreshService();

        loadBooking();

        depart.setLocalTime(LocalTime.MIN);
        arrival.setLocalTime(LocalTime.MIN);


        filteredData = new FilteredList<>(dao.serviceList, b -> true);

        searchBusServ.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<Service> predicate = Service -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                return Service.getService().toString().toLowerCase().contains(searchKeyword)
                        || Service.getSource().toString().toLowerCase().contains(searchKeyword)
                        || Service.getDestination().toString().toLowerCase().contains(searchKeyword)
                        || Service.getFare().toString().toLowerCase().contains(searchKeyword)
                        || Service.getDtime().toString().toLowerCase().contains(searchKeyword)
                        || Service.getAtime().toString().toLowerCase().contains(searchKeyword)
                        || Service.getSeats().toString().contains(searchKeyword)
                        || Service.getDt().toString().toLowerCase().contains(searchKeyword);
            };


            applyFilterAndSort(tblService, searchBusServ, filteredData, predicate);
        });


        toggleSearch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                toggleSearch.setText("Off");
                searchBusServ.setDisable(true);
            } else {
                toggleSearch.setText("On");
                searchBusServ.setDisable(false);
            }
        });



        TranslateTransition openNav = new TranslateTransition(new Duration(350), adminDrawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), adminDrawer);
        adminNav.setOnAction((ActionEvent evt) -> {
            RotateTransition spinNav = new RotateTransition(new Duration(350), nav);
            if (adminDrawer.getTranslateX() != 0) {
                // Animate 'nav' to spin and change the icon to left icon
                spinNav.play();
                spinNav.setByAngle(360);
                // Change the icon back to the default icon
                nav.setIcon(FontAwesomeIcon.NAVICON);
                // Play the openNav transition
                openNav.play();
            } else {
                // Reverse the process: Animate 'nav' to spin and change the icon back to the default icon
                spinNav.setByAngle(-360);
                spinNav.play();

                // Change the icon to the left icon
                nav.setIcon(FontAwesomeIcon.CHEVRON_LEFT);

                // Play the closeNav transition
                closeNav.setToX(-(adminDrawer.getWidth()));
                closeNav.play();
            }
            // Disable the adminNav button during animation
            adminNav.setDisable(true);

            // Enable the adminNav button after animation finishes
            spinNav.setOnFinished(event -> adminNav.setDisable(false));
        });

        bookingRecords.setVisible(false);

        initseatReport();
        initTableServ();

        dBoard.setOnAction(e -> {
            if (bookingRecords.isVisible() || addChairs.isVisible() || aboutUs.isVisible() || ourServices.isVisible() || locationPane.isVisible() || reports.isVisible()) {
                bookingRecords.setVisible(false);
                locationPane.setVisible(false);
                addChairs.setVisible(false);
                aboutUs.setVisible(false);
                ourServices.setVisible(false);
                reports.setVisible(false);
            }
            addBus.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            connect();
            searchBusServ.setDisable(true);
            refreshControls();
        });
        btnAddChairs.setOnAction(e -> {
            if (addBus.isVisible() || bookingRecords.isVisible() || aboutUs.isVisible() || ourServices.isVisible() || locationPane.isVisible() || reports.isVisible()) {
                addBus.setVisible(false);
                locationPane.setVisible(false);
                bookingRecords.setVisible(false);
                aboutUs.setVisible(false);
                ourServices.setVisible(false);
                reports.setVisible(false);
            }
            addChairs.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            loadChair();
            refreshControls();
        });
        mBooking.setOnAction(e -> {
            if (addBus.isVisible() || addChairs.isVisible() || aboutUs.isVisible() || ourServices.isVisible() || locationPane.isVisible() || reports.isVisible()) {
                addBus.setVisible(false);
                locationPane.setVisible(false);
                addChairs.setVisible(false);
                aboutUs.setVisible(false);
                ourServices.setVisible(false);
                reports.setVisible(false);
            }
            bookingRecords.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            refreshControls();
        });

        aboutBTN.setOnAction(e -> {
            if (addBus.isVisible() || addChairs.isVisible() || bookingRecords.isVisible() || ourServices.isVisible() || locationPane.isVisible() || reports.isVisible()) {
                addBus.setVisible(false);
                locationPane.setVisible(false);
                addChairs.setVisible(false);
                bookingRecords.setVisible(false);
                ourServices.setVisible(false);
                reports.setVisible(false);
            }
            aboutUs.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            refreshControls();
        });

        btnServ.setOnAction(e -> {
            if (addBus.isVisible() || addChairs.isVisible() || bookingRecords.isVisible() || aboutUs.isVisible() || locationPane.isVisible() || reports.isVisible()) {
                addBus.setVisible(false);
                locationPane.setVisible(false);
                addChairs.setVisible(false);
                bookingRecords.setVisible(false);
                aboutUs.setVisible(false);
                reports.setVisible(false);
            }
            ourServices.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            refreshControls();
        });

        btnLocation.setOnAction(e -> {
            if (addBus.isVisible() || addChairs.isVisible() || bookingRecords.isVisible() || aboutUs.isVisible() || ourServices.isVisible() || reports.isVisible()) {
                addBus.setVisible(false);
                addChairs.setVisible(false);
                bookingRecords.setVisible(false);
                aboutUs.setVisible(false);
                ourServices.setVisible(false);
                reports.setVisible(false);
            }
            locationPane.setVisible(true);
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            loadLocation();
            refreshControls();
        });

        reportView.setOnAction(e -> {
            loadFXMLAndSetScene("admin.fxml", e);
            LocalDate ld = LocalDate.now();
            if (addBus.isVisible() || addChairs.isVisible() || bookingRecords.isVisible() || aboutUs.isVisible() || ourServices.isVisible() || locationPane.isVisible()) {
                addBus.setVisible(false);
                addChairs.setVisible(false);
                bookingRecords.setVisible(false);
                aboutUs.setVisible(false);
                ourServices.setVisible(false);
                locationPane.setVisible(false);
            }
            reports.setVisible(true);
            currDate.setText(LocalDateTime.now().getMonth().toString()+", "+LocalDateTime.now().getDayOfMonth()+", "+LocalDateTime.now().getYear());
            tblService.getSelectionModel().clearSelection();
            searchBusServ.setDisable(true);
            refreshControls();
        });


        refreshChairReport();
        refreshProfitCount();
    }

    public void loadChair() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chair.fxml"));
        try {
            Node root = loader.load();
            addChairs.getChildren().add(root);

            // Set the left and right anchors to stretch the content horizontally
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateBalance.fxml"));
            Node root = loader.load();

            // Create a new Stage for the modal dialog
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UNDECORATED); // Remove window decorations
            dialogStage.setTitle("Update Balance"); // Set the title for the modal dialog

            // Create an exit button
            Button exitButton = new Button("Exit");
            exitButton.setOnAction(e -> dialogStage.close());

            // Create a new AnchorPane to hold the loaded content and the exit button
            AnchorPane modalPane = new AnchorPane(root, exitButton);

            // Set the left and right anchors to stretch the content and position the exit button
            AnchorPane.setBottomAnchor(root, 30.0); // Adjust the bottom margin as needed
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

            // Position the exit button in the top-right corner
            AnchorPane.setTopAnchor(exitButton, 5.0);
            AnchorPane.setRightAnchor(exitButton, 5.0);

            // Create a Scene with the modalPane as its root
            Scene dialogScene = new Scene(modalPane);

            // Set the Scene for the dialogStage
            dialogStage.setScene(dialogScene);

            // Show the modal dialog
            dialogStage.showAndWait(); // This will block the main UI until the modal is closed
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadLocation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Location.fxml"));
        try {
            Node root = loader.load();
            locationPane.getChildren().add(root);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadBooking() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminBooking.fxml"));
        try {
            AnchorPane newpane = loader.load();
            AnchorPane.setTopAnchor(newpane, 0.0);
            AnchorPane.setLeftAnchor(newpane, 0.0);
            AnchorPane.setBottomAnchor(newpane, 0.0);
            AnchorPane.setRightAnchor(newpane, 0.0);
            bookingRecords.getChildren().add(newpane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void connect() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<String> fromData = FXCollections.observableArrayList();
        ObservableList<String> toData = FXCollections.observableArrayList();
        ResultSet rS;
        PreparedStatement PST;
        try {
            PST = connection.prepareStatement("select * from bookk");
            rS = PST.executeQuery();
            while (rS.next()) {
                String location = rS.getString("location");
                fromData.add(location);
                toData.add(location);
            }

            source.setItems(fromData);
            destination.setItems(toData);

            source.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (toData.contains(newValue)) {
                        toData.remove(newValue);
                        destination.setItems(toData);
                    }
                }
            });

            destination.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (fromData.contains(newValue)) {
                        fromData.remove(newValue);
                        source.setItems(fromData);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void busSubmit(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        dao = new DataAccessObject();
        try {
            String serv = service.getText();
            String src = source.getSelectionModel().getSelectedItem();
            String dest = destination.getSelectionModel().getSelectedItem();
            String fre = fare.getText();
            String dept = depart.getLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME);
            String arr = arrival.getLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME);
            String seat = seats.getText();
            String date = ((LocalDate) this.date.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE);
            String frame = txtIframe.getText();

            if (ADD) {
                ps = connection.prepareStatement("Insert into search values('" + serv + "','" + src + "','" + dest + "','" + fre + "','" + dept + "','" + arr + "','" + seat + "','" + date + "','" + frame + "')");
                int count = ps.executeUpdate();

                if (count > 0) {
                        refreshService();
                        refreshControls();
                    showError("Saved");
                    filteredData = new FilteredList<>(dao.serviceList, b -> true);
                    resetFilterAndSortServ(tblService, searchBusServ, filteredData);
                }
            } else if (EDIT) {
                String q = "UPDATE search SET source = ?, dest = ?, fare = ?, dtime = ?, atime = ?, seat = ?, date = ?, iframe = ? WHERE service=?";
                ps = connection.prepareStatement(q);
                ps.setString(1, src);
                ps.setString(2, dest);
                ps.setString(3, fre);
                ps.setString(4, dept);
                ps.setString(5, arr);
                ps.setString(6, seat);
                ps.setString(7, date);
                ps.setString(8, frame);
                ps.setString(9, serv);
                int count = ps.executeUpdate();

                if (count > 0) {
                        refreshService();
                        refreshControls();
                    showError("Updated");
                    filteredData = new FilteredList<>(dao.serviceList, b -> true);
                    resetFilterAndSortServ(tblService, searchBusServ, filteredData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadFXMLAndSetScene(String fxmlFilePath, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleNavClick(MouseEvent mouseEvent) {
    }

    public void busEdit(ActionEvent actionEvent) {
        EDIT = true;
        ADD = false;
        editBus();
    }

    @FXML
    private void editBus() {
        s = tblService.getSelectionModel().getSelectedItem();
        if (s == null) {
            showError("Error: No item selected!");
        } else {
            service.setText(s.getService().get());
            source.getSelectionModel().select(s.getSource().get());
            destination.getSelectionModel().select(s.getDestination().get());
            fare.setText(s.getFare().get());
            depart.setLocalTime(s.getDtime().get());
            arrival.setLocalTime(s.getAtime().get());
            seats.setText(s.getSeats().get().toString());
            date.setValue(LocalDate.parse(s.getDt().get()));
            txtIframe.setText(s.getIframe().get());
        }
    }

    private void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Image icon16 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_16px.png");
            Image icon32 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_32px.png");
            Image icon48 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_64px.png");

            Stage newStage = new Stage();
            newStage.getIcons().addAll(icon16, icon32, icon48);
            newStage.setScene(scene);
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
            newStage.centerOnScreen();
            newStage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertNewBus() {
        EDIT = false;
        ADD = true;
        service.setText("");
        source.getSelectionModel().clearSelection();
        destination.getSelectionModel().clearSelection();
        fare.setText("");
        depart.setLocalTime(LocalTime.MIN);
        arrival.setLocalTime(LocalTime.MIN);
        seats.setText("");
        date.getEditor().clear();
    }

    public void busDelete(ActionEvent actionEvent) {
        dao = new DataAccessObject();
        Service s = tblService.getSelectionModel().getSelectedItem();
        String ser = String.valueOf(s.getService().get());

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
            query = "DELETE FROM search where service = '" + ser + "';";
            dao.saveData(query);
            showError("Deleted");
            filteredData = new FilteredList<>(dao.serviceList, b -> true);
            resetFilterAndSortServ(tblService, searchBusServ, filteredData);
            refreshService();
            refreshControls();
        }
    }

    public void initTableServ() {
        sr.setCellValueFactory(cell -> cell.getValue().getService());
        sour.setCellValueFactory(cell -> cell.getValue().getSource());
        dest.setCellValueFactory(cell -> cell.getValue().getDestination());
        far.setCellValueFactory(cell -> cell.getValue().getFare());
        dept.setCellValueFactory(cell -> cell.getValue().getDtime());
        arr.setCellValueFactory(cell -> cell.getValue().getAtime());
        sea.setCellValueFactory(cell -> cell.getValue().getSeats());
        dat.setCellValueFactory(cell -> cell.getValue().getDt());
        iFrame.setCellValueFactory(cell -> cell.getValue().getIframe());
    }

    private void refreshService() {
        initTableServ();
        query = "SELECT `service`,`source`,`dest`,`fare`,`dtime`,`atime`,`seat`,`date`,`iframe` FROM `search`";
        tblService.setItems(dao.getServiceData(query));
    }

    private void refreshChairReport() {
        initseatReport();
        query = "SELECT service, COUNT(*) AS available_seats_count\n" +
                "FROM seat_names\n" +
                "WHERE picked = 0\n" +
                "GROUP BY service";
        tblUnpicked.setItems(dao.getReportServiceData(query));
    }

    private void refreshProfitCount() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(CAST(`amount` AS DECIMAL(10, 2))) AS total_amount FROM `bookings`");
            if (resultSet.next()) {
                double totalAmount = resultSet.getDouble("total_amount");
                amountReceived.setText(String.valueOf(totalAmount));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void busInsert(ActionEvent actionEvent) {
        dao = new DataAccessObject();
        filteredData = new FilteredList<>(dao.serviceList, b -> true);
        refreshControls();
        refreshService();
    }

    private void refreshControls() {
        tblService.getSelectionModel().clearSelection();
        service.clear();
        source.getSelectionModel().clearSelection();
        destination.getSelectionModel().clearSelection();
        fare.clear();
        depart.setLocalTime(LocalTime.MIN);
        arrival.setLocalTime(LocalTime.MIN);
        seats.clear();
        date.getEditor().clear();
        txtIframe.clear();
        initTableServ();
        ADD = true;
        EDIT = false;
        resetFilterAndSortServ(tblService, searchBusServ, filteredData);
        searchBusServ.setDisable(true);
        toggleSearch.setText("Off");
        toggleSearch.setSelected(false);
    }


    private Stage createSplashStage(String message) {
        MaskerPane maskerPane = new MaskerPane();
        maskerPane.setText(message);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), maskerPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.play();

        AnchorPane splashRoot = new AnchorPane(maskerPane);
//        StackPane.setAlignment(maskerPane, Pos.CENTER);
        AnchorPane.setTopAnchor(maskerPane, 0.0);
        AnchorPane.setRightAnchor(maskerPane, 0.0);
        AnchorPane.setBottomAnchor(maskerPane, 0.0);
        AnchorPane.setLeftAnchor(maskerPane, 0.0);
        splashRoot.setStyle("-fx-background-color: transparent;"); // Set background color to transparent
        Scene splashScene = new Scene(splashRoot, 300, 200);
        splashScene.setFill(Color.TRANSPARENT); // Set the scene fill to transparent
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.TRANSPARENT); // Set the stage style to transparent
        splashStage.setScene(splashScene);
        return splashStage;
    }

    public void logoutAdmin(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Create and show the splash screen stage
        Stage splashStage = createSplashStage("logging off");
        splashStage.show();

        // Delay the execution of newStage.show() using a separate thread
        Task<Void> delayTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(2000); // Adjust the delay time as needed
                return null;
            }
        };
        delayTask.setOnSucceeded(e -> {
            // Close the splash screen stage and show the new stage
            splashStage.close();
            showLoginPage();
        });
        new Thread(delayTask).start();

        // Close the current stage
        currentStage.close();
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
                        System.out.println(message);
                    }
                });
        notificationBuilder.showConfirm();
    }

    public void mapView(ActionEvent actionEvent) {
        Dialog<Void> mapDialog = new Dialog<>();
        mapDialog.initModality(Modality.APPLICATION_MODAL);

        WebView webView = new WebView();
        webView.setPrefSize(600, 450); // Set the preferred size of the WebView

        WebEngine webEngine = webView.getEngine();

        String iframeContent = (txtIframe.getText() != null) ? txtIframe.getText() : "";


        if (iframeContent.equals("")) {
            showError("No bus selected");

        } else {
            // Parse the HTML content with Jsoup
            Document doc = Jsoup.parse(iframeContent, "UTF-8");

            // Get the first iframe element
            Element iframe = doc.selectFirst("iframe");

            // Add the id attribute to the iframe element
            iframe.attr("id", "mapFrame");

            // Get the modified HTML content
            String modifiedHTML = doc.html();

            String htmlContent = "<html><body style=\"margin:0;padding:0;\">"
                    + "<div id=\"loader-container\">"
                    + "  <div id=\"loader\"></div>"
                    + "</div>"
                    + modifiedHTML
                    + "<style>"
                    + "#loader-container {"
                    + "  display: flex;"
                    + "  align-items: center;"
                    + "  justify-content: center;"
                    + "  width: 100%;"
                    + "  height: 100%;"
                    + "}"
                    + "#loader {"
                    + "  border: 16px solid #f3f3f3;"
                    + "  border-top: 16px solid #3498db;"
                    + "  border-radius: 50%;"
                    + "  width: 120px;"
                    + "  height: 120px;"
                    + "  animation: spin 2s linear infinite;"
                    + "}"
                    + "@keyframes spin {"
                    + "  0% { transform: rotate(0deg); }"
                    + "  100% { transform: rotate(360deg); }"
                    + "}"
                    + "</style>"
                    + "<script>"
                    + "var mapFrame = document.getElementById('mapFrame');"
                    + "var loaderContainer = document.getElementById('loader-container');"
                    + "mapFrame.addEventListener('load', function() {"
                    + "  loaderContainer.style.display = 'none';"
                    + "});"
                    + "</script>"
                    + "</body></html>";


            webEngine.loadContent(htmlContent);

            VBox dialogContent = new VBox(webView);
            dialogContent.setAlignment(Pos.CENTER);

            mapDialog.getDialogPane().setContent(dialogContent);
            mapDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            mapDialog.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);

            mapDialog.showAndWait();
        }
    }

    public void busInfo(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        String details = "This page helps you as the administrator to customize particular details of a bus to be booked, be it location, routes to be travelled, and bus service to be used. This section of the program gives you as the admin ability to add and customize all buses according to the customer's requirement";
        alert.setContentText(details);
        alert.setHeaderText("Customize");
        alert.setTitle("Bus Info");
        alert.show();
    }


    public void initseatReport() {
        colReportService.setCellValueFactory(cell -> cell.getValue().getService());
        colReportAvailable.setCellValueFactory(cell -> cell.getValue().getSeats());
    }


    public static void resetFilterAndSortServ(
            TableView<Service> tblService,
            TextField searchBusServ,
            FilteredList<Service> filteredData
    ) {
        // Clear the text in the searchBusServ TextField
        searchBusServ.clear();

        // Clear the predicate of filteredData
        filteredData.setPredicate(null);


    }

    private static void applyFilterAndSort(
            TableView<Service> tblService,
            TextField searchBusServ,
            FilteredList<Service> filteredData,
            Predicate<Service> predicate
    ) {
        filteredData.setPredicate(predicate);

        SortedList<Service> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblService.comparatorProperty());
        tblService.setItems(sortedData);
    }


    public void disableBTN(MouseEvent mouseEvent) {

    }

    public void enableBTN(MouseEvent mouseEvent) {
    }

    public void closeAdmin(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Create an Alert with a confirmation dialog
        Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        exitConfirmation.setTitle("Exit Confirmation");
        exitConfirmation.setHeaderText(null);
        exitConfirmation.setContentText("Are you sure you want to exit?");

        // Set custom button types (YES and NO)df
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);

        exitConfirmation.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Show the dialog and wait for the user's response
        ButtonType result = exitConfirmation.showAndWait().orElse(ButtonType.CANCEL);

        if (result == buttonTypeYes) {
            // User confirmed, proceed with closing the application
            // Create and show the splash screen stage
            Stage splashStage = createSplashStage("Closing Application");
            splashStage.show();

            // Delay the execution of newStage.show() using a separate thread
            Task<Void> delayTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000); // Adjust the delay time as needed
                    return null;
                }
            };
            delayTask.setOnSucceeded(e -> {
                // Close the splash screen stage and show the new stage
                splashStage.close();
                System.exit(0);
            });
            new Thread(delayTask).start();

            // Close the current stage
            currentStage.close();
        }
    }



    private void printToPDF(SplitPane anchorPane, Stage primaryStage) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null && job.showPrintDialog(primaryStage)) {
            job.getJobSettings().setPageLayout(pageLayout);

            // Print the complete contents of the AnchorPane
            boolean success = job.printPage(anchorPane);
            if (success) {
                job.endJob();
            }
        }
    }

    public void printReport(ActionEvent actionEvent) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
//        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        SplitPane.applyCss();
//        SplitPane.layout();
//        printToPDF(SplitPane, window);


        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            Printer printer = Printer.getDefaultPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
            printerJob.getJobSettings().setPageLayout(pageLayout);
            printerJob.getJobSettings().setPageRanges(new PageRange(1, 2));


            boolean success = printerJob.printPage(bargraph);
            if (success) {
                success = printerJob.printPage(tableAvaiable);
            }

            if (success) {
                printerJob.endJob();
            }

        }
    }

    public void checkUser(ActionEvent actionEvent) {
        loadUser();
    }
}

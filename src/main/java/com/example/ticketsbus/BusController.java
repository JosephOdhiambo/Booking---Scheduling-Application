package com.example.ticketsbus;


import com.example.ticketsbus.model.BookinDetails;
import com.example.ticketsbus.model.HeaderDetails;
import com.codingerror.model.ProductTableHeader;
import com.codingerror.model.Product;
import com.example.ticketsbus.connectivity.ConnectionClass;


import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.codingerror.service.CodingErrorPdfInvoiceCreator;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.Notifications;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class BusController implements Initializable {

    @FXML
    private AnchorPane Bookings;

    @FXML
    private JFXButton button;

    @FXML
    private JFXButton dBoard;

    @FXML
    private AnchorPane dashboard;
    @FXML
    private AnchorPane advert;

    @FXML
    private ComboBox datE;

    @FXML
    private ComboBox from;

    @FXML
    private JFXButton mBooking;

    @FXML
    private Label unameLabel;
    @FXML
    private TableColumn<Service, String> atime;
    @FXML
    private TableColumn<Service, String> dtime;
    @FXML
    private TableColumn<Service, Integer> fare;
    @FXML
    private TableColumn<Service, String> service;

    @FXML
    private TableColumn<Service, String> destination;

    @FXML
    private TableColumn<Service, String> source;

    ResultSet resultSet;
    PreparedStatement pst;


    @FXML
    private CheckComboBox<String> seats;

    @FXML
    private TableView<Service1> tableview;

    @FXML
    private ComboBox to;

    @FXML
    private Label sourcelabel;
    @FXML
    private Label serlabel;
    @FXML
    private Label dlabel;
    @FXML
    private Label flabel;
    @FXML
    private Label datelabel;

    @FXML
    private Label seatcount;

    @FXML
    private Label seatlabel;

    private boolean EDIT = false, ADD = true;

    private String query;

    private DataAccessObject dao;

    private DataAccessObject doa;

    private String naMe;
    private String pHone;
    private String soUrce;
    private String deStination;
    private String sErvice;
    private String daTe;
    private String seatS;
    private String amoUnt;

    @FXML
    private JFXButton bookSeats;
    @FXML
    private TextField contactNo;

    @FXML
    private Label lblPhone;

    String filePath;
    String fileContent;
    @FXML
    private Label userName;

    public int count, i;

    @FXML
    private Label totalPrice;

    @FXML
    private Button refreshLayout;
    @FXML
    private Button checkAvailability;

    private String serve;

    @FXML
    private Button printReport;


    @FXML
    private TableView<Booking> booKings;
    @FXML
    private TableColumn<Booking, String> bookName;
    @FXML
    private TableColumn<Booking, String> bookPhone;
    @FXML
    private TableColumn<Booking, String> bookSource;
    @FXML
    private TableColumn<Booking, String> bookDest;
    @FXML
    private TableColumn<Booking, String> bookService;
    @FXML
    private TableColumn<Booking, String> bookDate;
    @FXML
    private TableColumn<Booking, String> bookSeat;
    @FXML
    private TableColumn<Booking, String> bookAmount;


    String nm, num, src, dest, se, dt, sts, tot;

    @FXML
    private Label bookingName;
    @FXML
    private Label bookingPhone;
    @FXML
    private Label bookingSource;
    @FXML
    private Label bookingDest;
    @FXML
    private Label bookingServ;
    @FXML
    private Label bookingDate;
    @FXML
    private Label bookingSeats;
    @FXML
    private Label bookingAmount;
    @FXML
    private Button navbar;
    @FXML
    private AnchorPane drawer;
    @FXML
    private AnchorPane leftBorderPane;

    @FXML
    private FontAwesomeIconView usnav;

    Connection con;

    private Timeline countdownTimeline;

    @FXML
    private Label countdownLabel;
    @FXML
    private Label Hours;
    @FXML
    private Label Minutes;
    @FXML
    private Label Seconds;


    private long daysRemaining;
    private long hoursRemaining;
    private long minutesRemaining;
    private long secondsRemaining;

    StringBuilder selectedSeats;

    @FXML
    private WebView galleryBuses;
    String date = "2023-12-16";

    String iframeCode;

    @FXML
    private Button rescheduleBTN;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filePath = "data.txt";
        fileContent = readFileToString(filePath);
        unameLabel.setText(fileContent);



        boolean isBookingFound = checkBooking();

        if (isBookingFound) {
            rescheduleBTN.setVisible(true);
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("You are late for some bus trips");
            confirmationAlert.setContentText("Should we reschedule you for later dates?");
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                dateFetch();
            }
        } else{
            rescheduleBTN.setVisible(false);
        }

        WebEngine webEngine = galleryBuses.getEngine();

        webEngine.setJavaScriptEnabled(true);

        File htmlFile = new File("C:\\Users\\odhis\\IdeaProjects\\Booking---Scheduling-Application\\src\\main\\java\\com\\example\\ticketsbus\\gallery.html");

        String fileURL = htmlFile.toURI().toString();

        webEngine.load(fileURL);
        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);
        navbar.setOnAction((ActionEvent evt) -> {
            RotateTransition spinNav = new RotateTransition(new Duration(350), usnav);
            if (drawer.getTranslateX() != 0) {
                openNav.play();
                // Animate 'nav' to spin and change the icon to left icon
                spinNav.setByAngle(360);
                spinNav.play();


                // Change the icon back to the default icon
                usnav.setIcon(FontAwesomeIcon.NAVICON);
                // Play the openNav transition
            } else {
                // Reverse the process: Animate 'nav' to spin and change the icon back to the default icon
                spinNav.setByAngle(-360);
                spinNav.play();

                // Change the icon to the left icon
                usnav.setIcon(FontAwesomeIcon.CHEVRON_LEFT);

                // Play the closeNav transition
                closeNav.setToX(-(leftBorderPane.getWidth()));
                closeNav.play();
            }
            // Disable the adminNav button during animation
            navbar.setDisable(true);

            // Enable the adminNav button after animation finishes
            spinNav.setOnFinished(event -> navbar.setDisable(false));
        });

        initBooking();
        dao = new DataAccessObject();
        doa = new DataAccessObject();
        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            selectedSeats = new StringBuilder();
            for (String seat : seats.getCheckModel().getCheckedItems()) {
                selectedSeats.append("'").append(seat).append("', ");
            }
            if (selectedSeats.length() > 0) {
                selectedSeats.delete(selectedSeats.length() - 2, selectedSeats.length()); // Remove the trailing comma and space
            }
            System.out.println("Selected Seats: " + selectedSeats.toString());
        });
        refreshLayout.setOnAction(event -> {
            loadFXMLAndSetScene("book.fxml", event);
        });


        bookSeats.setOnAction(e -> {
            // Establish a database connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "")) {
                // Prepare the SQL update statement
                String updateQuery = "UPDATE seat_names SET picked = 1 WHERE seatname IN (" + selectedSeats.toString() + ")";
                String updateQ = "UPDATE seat_names SET uname = '" + fileContent + "' WHERE seatname IN (" + selectedSeats.toString() + ")";

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                PreparedStatement prepared = connection.prepareStatement(updateQ);

                // Execute the update statement
                int rowsUpdated = preparedStatement.executeUpdate();
                int rowsUpdate = prepared.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);
                System.out.println(updateQuery);
                System.out.println(updateQ);

                System.out.println("Users updated: " + rowsUpdate);
                seats.disableProperty();
            } catch (SQLException n) {
                n.printStackTrace();
            }
            saveBooking();
            seats.setDisable(false);
            loadFXMLAndSetScene("book.fxml", e);
        });

        connect();
        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            count = seats.getCheckModel().getCheckedItems().size();
            seatcount.setText("Selected Count: " + count);
            i = Integer.parseInt(flabel.getText()) * count;
            totalPrice.setText(String.valueOf(i));
            if (count >= 3) {
//                seats.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("You can select a maximum of 3 Seats");
                alert.show();

                seats.hide();
                seats.setDisable(true);
            }
        });

        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            StringBuilder selectedSeats = new StringBuilder();
            for (String seat : seats.getCheckModel().getCheckedItems()) {
                selectedSeats.append(seat).append(", ");
            }
            if (selectedSeats.length() > 0) {
                selectedSeats.delete(selectedSeats.length() - 2, selectedSeats.length()); // Remove the trailing comma and space
            }
            System.out.println("Selected Seats: " + selectedSeats.toString());
        });
        tableview.setOnMouseClicked(e -> {
            try {
                Service1 service = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                sourcelabel.setText(service.getSource());
                serlabel.setText(service.getService());
                serve = serlabel.getText();
                lblPhone.setText(contactNo.getText());
                dlabel.setText(service.getDestination());
                flabel.setText(String.valueOf(service.getFare()));
                datelabel.setText(String.valueOf(service.getDt()));
                userName.setText(fileContent);
                seats.setVisible(true);
                seatcount.setVisible(true);
                seatlabel.setVisible(true);
                if (isBookingExists(userName.getText(), datelabel.getText())) {
                    Notifications notificationBuilder = Notifications.create()
                            .title("Booking")
                            .text("You have Already made a booking on this location.Check '" + "bookings" + "'")
                            .graphic(null)
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                }
                            });
                    notificationBuilder.showConfirm();

                    loadFXMLAndSetScene("book.fxml", e);

//                    userName.setText("");
//                    lblPhone.setText("");
//                    sourcelabel.setText("");
//                    dlabel.setText("");
//                    serlabel.setText("");
//                    datelabel.setText("");
//                    from.getSelectionModel().clearSelection();
//                    to.getSelectionModel().clearSelection();
//                    datE.getSelectionModel().clearSelection();
//                    contactNo.setText("");
//                    flabel.setText("");
//                    seats.hide();
//                    seats.setDisable(true);
                } else {
                    loadSeats();
                }
            } catch (Exception v) {
                System.out.println("You did not select a row");
            }
        });
        service.setCellValueFactory(new PropertyValueFactory<>("service"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
        dtime.setCellValueFactory(new PropertyValueFactory<>("dtime"));
        atime.setCellValueFactory(new PropertyValueFactory<>("atime"));

        dashboard.setVisible(true);
        Bookings.setVisible(false);

        dBoard.setOnAction(e -> {
            if (Bookings.isVisible()) {
                Bookings.setVisible(false);
            }
            dashboard.setVisible(true);
        });

        mBooking.setOnAction(e -> {
            if (dashboard.isVisible()) {
                dashboard.setVisible(false);
            }
            Bookings.setVisible(true);
            refreshBooking();
        });


        booKings.setOnMouseClicked(event -> {
            bookinData();
        });

        printReport.setOnAction(r -> {
            LocalDate ld = LocalDate.now();


            // Let the user choose the location and name of the PDF
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(ld + ".pdf");
            fileChooser.setTitle("Save PDF");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                String pdfName = file.getAbsolutePath();

                CodingErrorPdfInvoiceCreator cepdf = new CodingErrorPdfInvoiceCreator(pdfName);
                try {
                    cepdf.createDocument();

                    // Create Header start
                    HeaderDetails header = new HeaderDetails();
                    header.setInvoiceNo("RK35623").setInvoiceDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build();
                    cepdf.createHeader(header);

                    BookinDetails bookinDetails = new BookinDetails();
                    bookinDetails
                            .setBillingCompany("Mainstream Travels")
                            .setBillingName(nm.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"))
                            .setBillingAddress(src.replaceAll("StringProperty \\[value: (.*?)\\]", "$1") + "\n To\n" + dest.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"))
                            .setBillingEmail(num.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"))
                            .setShippingName(se.replaceAll("StringProperty \\[value: (.*?)\\]", "$1") + "\n")
                            .setShippingAddress(tot.replaceAll("StringProperty \\[value: (.*?)\\]", "$1") + " kshs")
                            .build();

                    cepdf.createAddress(bookinDetails);

                    // Term and Condition Start
                    List<String> TncList = new ArrayList<>();
                    TncList.add("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.");
                    TncList.add("2. The Seller warrants the product for one (1) year from the date of shipment");
                    String imagePath = "C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\ce_logo_circle_transparent.png";
                    cepdf.createTnc(TncList, false, imagePath);
                    // Term and condition end
                    System.out.println("PDF generated");

                    if (file.exists()) {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    loadFXMLAndSetScene("book.fxml", r);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        checkAvailability.setOnAction(e -> {
            String FRM, TO;
            FRM = (String) from.getValue();
            TO = (String) to.getValue();
            boolean isAvailable = checkAvailability(FRM, TO);
            Alert error = new Alert(Alert.AlertType.ERROR);

            if (isAvailable) {
                Notifications notificationBuilder = Notifications.create()
                        .title("Available")
                        .text("The source and destination are available.")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            }
                        });
                notificationBuilder.showConfirm();


                traverseAndSelectComboBox(datE);
                button.setDisable(false);
            } else {
                Notifications notificationBuilder = Notifications.create()
                        .title("Not Available")
                        .text("The source and destination are not available.")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            }
                        });
                notificationBuilder.showConfirm();
            }
        });

        from.setOnAction(e -> checkSelections(from, to));
        to.setOnAction(e -> checkSelections(from, to));


    }


    private void bookinData() {
        try {
            Booking book = booKings.getItems().get(booKings.getSelectionModel().getSelectedIndex());
            nm = String.valueOf(book.getName());
            num = String.valueOf(book.getPhone());
            src = String.valueOf(book.getSource());
            dest = String.valueOf(book.getDestination());
            se = book.getService().toString();
            dt = book.getDate().toString();
            sts = book.getSeats().toString();
            tot = book.getAmount().toString();


            String url = "jdbc:mysql://localhost:3306/bus";
            String username = "root";
            String password = "";

            // Establish the database connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create and execute the SQL query
            String query = "SELECT s.dtime, s.date\n" +
                    "FROM search s\n" +
                    "JOIN bookings b ON s.service = b.service AND s.date = b.date\n" +
                    "WHERE b.date = ?;\n";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dt.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            ResultSet resultSet = pstmt.executeQuery();

            LocalTime departureTime = null;
            LocalDate departureDate = null;

            // Iterate over the result set and fetch the data
            while (resultSet.next()) {
                departureTime = resultSet.getTime("dtime").toLocalTime();
                departureDate = resultSet.getDate("date").toLocalDate();
            }

//            System.out.println("Departure Time: " + departureTime);
//            System.out.println("Date: " + departureDate);

            LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
//            System.out.println(departureDateTime);

            bookingName.setText(nm.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingPhone.setText(num.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingSource.setText(src.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingDest.setText(dest.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingServ.setText(se.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingDate.setText(dt.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingSeats.setText(sts.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            bookingAmount.setText(tot.replaceAll("StringProperty \\[value: (.*?)\\]", "$1"));
            printReport.setVisible(true);

            LocalDateTime currentDateTime = LocalDateTime.now();
            final long[] totalRemainingSeconds = {ChronoUnit.SECONDS.between(currentDateTime, departureDateTime)};
            if (booKings.getSelectionModel().getSelectedIndex() >= 0) {
                loadiframe();
            } else{
                Notifications notificationBuilder = Notifications.create()
                        .title("Error")
                        .text("No records selected")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            }
                        });
                notificationBuilder.showConfirm();

            }

            AnimationTimer countdownTimer = new AnimationTimer() {
                private long lastTime = -1;
                private boolean notificationShown = false; // Add this flag

                @Override
                public void handle(long now) {
                    if (lastTime == -1) {
                        lastTime = now;
                        return;
                    }

                    // Calculate the elapsed time since the last frame
                    double elapsedTimeSeconds = (now - lastTime) / 1_000_000_000.0;

                    // Calculate the remaining seconds in the countdown
                    totalRemainingSeconds[0] -= elapsedTimeSeconds;

                    // Calculate the remaining days, hours, minutes, and seconds
                    long remainingDays = totalRemainingSeconds[0] / (24 * 60 * 60);
                    long remainingHours = (totalRemainingSeconds[0] % (24 * 60 * 60)) / (60 * 60);
                    long remainingMinutes = (totalRemainingSeconds[0] % (60 * 60)) / 60;
                    long remainingSeconds = totalRemainingSeconds[0] % 60;

                    countdownLabel.setText(remainingDays + " days");
                    Hours.setText(remainingHours + " hours");
                    Minutes.setText(remainingMinutes + " minutes");
                    Seconds.setText(remainingSeconds + " seconds");

                    // Check if the countdown has reached zero
                    if (totalRemainingSeconds[0] <= 0) {
                        // Stop the countdown timer
                        stop();

                        // Perform any actions needed when the countdown reaches zero
                        // ...
                    } else if (remainingDays <= 0 && !notificationShown) {
                        Notifications notificationBuilder = Notifications.create()
                                .title("Booking")
                                .text("A few more hours to go. Print your ticket")
                                .graphic(null)
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_CENTER)
                                .onAction(event -> {
                                    // Handle the notification action if needed
                                });
                        notificationBuilder.showConfirm();

                        notificationShown = true; // Set the flag to true
                    } else if (remainingDays <= 7 && remainingDays > 0 && !notificationShown) {
                        Notifications notificationBuilder = Notifications.create()
                                .title("Booking")
                                .text(" A few more days to go. Print your ticket")
                                .graphic(null)
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_CENTER)
                                .onAction(event -> {
                                    // Handle the notification action if needed
                                });
                        notificationBuilder.showConfirm();

                        notificationShown = true; // Set the flag to true
                    }

                    lastTime = now;
                }
            };

// Start the countdown timer
            countdownTimer.start();


        } catch (Exception e) {
            System.out.println("Table mini error");
        }
    }

    private void loadiframe() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT iframe FROM search WHERE source = '" + bookingSource.getText() + "' AND dest = '" + bookingDest.getText() + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                iframeCode = resultSet.getString("iframe");
            } else {
                System.out.println("No data found.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void saveBooking() {
        naMe = userName.getText();
        pHone = lblPhone.getText();
        soUrce = sourcelabel.getText();
        deStination = dlabel.getText();
        sErvice = serlabel.getText();
        daTe = datelabel.getText();
        seatS = String.valueOf(count);
        amoUnt = totalPrice.getText();

        if (isBookingExists(naMe, daTe)) {
            // Handle duplicate entry case, e.g., show an error message
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Duplicate booking entry!");
            alert.show();
            return;
        }

        query = "INSERT INTO bookings(name, phone, source, destination, service, date, seats, amount) VALUES ('" + naMe + "','" + pHone + "','" + soUrce + "','" + deStination + "','" + sErvice + "','" + daTe + "','" + seatS + "','" + amoUnt + "')";

        dao.saveData(query);
    }

    private void loadSeats() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<String> seat = FXCollections.observableArrayList();
        ResultSet rS;
        PreparedStatement PST;
        try {
            PST = connection.prepareStatement("select * from seat_names where picked = 0 AND service='" + serve + "' AND uname !='" + userName.getText() + "'");
            rS = PST.executeQuery();
            while (rS.next()) {
                String Seat = rS.getString("seatname");
                seat.add(Seat);
            }
            seats.getItems().addAll(seat);
        } catch (SQLException e) {
            System.out.println("");
        }
    }

    public void search() {
        loaddata();
    }


    private double getZoomToFitContent(WebView webView) {
        double viewportWidth = webView.getWidth();
        double contentWidth = (Double) webView.getEngine().executeScript("document.documentElement.scrollWidth");
        return viewportWidth / contentWidth;
    }

    private void loaddata() {

        ObservableList<Service1> data = FXCollections.observableArrayList();

        String source = from.getSelectionModel().getSelectedItem().toString();
        String dest = to.getSelectionModel().getSelectedItem().toString();
        String Date = datE.getSelectionModel().getSelectedItem().toString();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(
                    "select *  from search where source ='" + source + "' and dest = '" + dest + "'and date = '" + Date + "' ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Service1(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getTime(5),
                        rs.getTime(6),
                        rs.getString(8)));
                advert.setVisible(false);
                tableview.setItems(data);
                galleryBuses.setVisible(false);
                tableview.setVisible(true);

            }
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
        try {
            PST = connection.prepareStatement("select * from search");
            pst = connection.prepareStatement("select * from bookk");
            rS = PST.executeQuery();
            resultSet = pst.executeQuery();


            while (rS.next()) {
                String Date = rS.getString("date");
                DateData.add(Date);
            }

            datE.setItems(DateData);

            while (resultSet.next()) {
                String location = resultSet.getString("location");

                fromData.add(location);
                toData.add(location);
            }

            from.setItems(fromData);
            to.setItems(toData);

            from.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (toData.contains(newValue)) {
                        toData.remove(newValue);
                        to.setItems(toData);
                    }
                }
            });

            to.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (fromData.contains(newValue)) {
                        fromData.remove(newValue);
                        from.setItems(fromData);
                    }
                }
            });

        } catch (SQLException e) {
            System.out.println("");
        }

    }

    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    content.append(System.lineSeparator());
                }
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return content.toString();
    }

    private int calculateI() {
        int flabelValue = Integer.parseInt(flabel.getText());
        return flabelValue * count;
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

    void loadFXMLAndSetScene(String fxmlFilePath, MouseEvent event) {
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

    private boolean isBookingExists(String name, String date) {
        // Perform a database query to check if the booking already exists based on name and date
        String checkQuery = "SELECT COUNT(*) FROM bookings WHERE name = '" + name + "' AND date = '" + date + "'";
        int count = dao.getCount(checkQuery);

        return count > 0;
    }

    public void refreshBooking() {
        initBooking();
        query = "select name, phone, source, destination, service, date, seats, amount from bookings where name='" + fileContent + "' AND date > CURRENT_DATE";
        booKings.setItems(dao.getBookingData(query));
    }

    public void initBooking() {
        bookName.setCellValueFactory(cell -> cell.getValue().getName());
        bookPhone.setCellValueFactory(cell -> cell.getValue().getPhone());
        bookSource.setCellValueFactory(cell -> cell.getValue().getSource());
        bookDest.setCellValueFactory(cell -> cell.getValue().getDestination());
        bookService.setCellValueFactory(cell -> cell.getValue().getService());
        bookDate.setCellValueFactory(cell -> cell.getValue().getDate());
        bookSeat.setCellValueFactory(cell -> cell.getValue().getSeats());
        bookAmount.setCellValueFactory(cell -> cell.getValue().getAmount());
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

    private void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Image icon16 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_16px.png");
            Image icon32 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_32px.png");
            Image icon48 = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\Compass_64px.png");


            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.getIcons().addAll(icon16, icon32, icon48);
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
            newStage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logoutCust(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Create and show the splash screen stage
        Stage splashStage = createSplashStage("Logging out "+fileContent);
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

    private boolean checkAvailability(String source, String dest) {
        boolean isAvailable = false;
        String sourceValue = null;
        String destValue = null;
        String dateValue = null;

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT source, dest, date FROM `search` WHERE `source` = '" + source + "' AND `dest` = '" + dest + "'";
            ResultSet resultSet = statement.executeQuery(query);


            isAvailable = resultSet.next();

            date = resultSet.getString("date");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAvailable;
    }


    private void checkSelections(ComboBox<String> comboBox1, ComboBox<String> comboBox2) {
        if (comboBox1.getValue() != null && comboBox2.getValue() != null) {
            String selectedValue1 = comboBox1.getValue();
            String selectedValue2 = comboBox2.getValue();
            System.out.println("Selected value from ComboBox1: " + selectedValue1);
            System.out.println("Selected value from ComboBox2: " + selectedValue2);
            checkAvailability.setVisible(true);
        } else {
            System.out.println("Please select values from both ComboBoxes.");
        }


    }

    private void traverseAndSelectComboBox(ComboBox<String> comboBox) {
        for (String item : comboBox.getItems()) {
            if (item.equals(date)) {
                System.out.println("Date '" + date + "' found in the ComboBox!");
                comboBox.setValue(item); // Select the date if found
                return; // If found, you can return or break the loop here.
            }
        }
        System.out.println("Date '" + date + "' not found in the ComboBox.");
    }


    public void viewMap(ActionEvent actionEvent) {
        Dialog<Void> mapDialog = new Dialog<>();
        mapDialog.initModality(Modality.APPLICATION_MODAL);

        WebView webView = new WebView();
        webView.setPrefSize(600, 450); // Set the preferred size of the WebView

        WebEngine webEngine = webView.getEngine();

        String iframeContent = (iframeCode != null) ? iframeCode : "";


        if (iframeContent.equals("")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Error")
                    .text("No Booking Selected")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        }
                    });
            notificationBuilder.showConfirm();

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

    public void dateFetch() {
        ConnectionClass connectionClass = new ConnectionClass();

        try (Connection connection = connectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT date FROM bookings")) {

            Calendar calendar = Calendar.getInstance();
            while (resultSet.next()) {
                Date originalDate = resultSet.getDate("date");

                LocalDate l = LocalDate.now();
                String inputDateStr = l.toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date inputDate = dateFormat.parse(inputDateStr);

                if (originalDate != null) {
                    calendar.setTime(inputDate);
                    calendar.add(Calendar.WEEK_OF_YEAR, 1);

                    Date updatedDate = new Date(calendar.getTimeInMillis());
                    // Update the date in the database
                    try (Statement updateStatement = connection.createStatement()) {
                        String updateQuery = "UPDATE bookings SET date = '" + updatedDate + "' WHERE date < CURRENT_DATE AND name = '"+fileContent+"'";
                        String updateSearch = "UPDATE search SET date = '" + updatedDate + "' WHERE date < CURRENT_DATE";
                        updateStatement.executeUpdate(updateQuery);
                        updateStatement.executeUpdate(updateSearch);
                    }

//                    System.out.println("Original Date: " + originalDate + ", Updated Date: " + updatedDate);
                    rescheduleBTN.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean checkBooking() {
        ConnectionClass connectionClass = new ConnectionClass();
        try (Connection connection = connectionClass.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM bookings WHERE `date` <= CURRENT_DATE AND `name` = ?"
             )) {
            statement.setString(1, fileContent);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println(count);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
        notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
    }

    public void rescheduleTrip(ActionEvent actionEvent) {
        loadFXMLAndSetScene("book.fxml", actionEvent);
    }

    public void exitApplication(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Create an Alert with a confirmation dialog
        Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        exitConfirmation.setTitle("Exit Confirmation");
        exitConfirmation.setHeaderText(null);
        exitConfirmation.setContentText("Are you sure you want to exit?");

        // Set custom button types (YES and NO)df
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

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
}


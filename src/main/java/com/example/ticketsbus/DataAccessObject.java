package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.ExceptionDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataAccessObject {
    private ConnectionClass database = new ConnectionClass();
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection connect;

    ObservableList<Chair> chairList = FXCollections.observableArrayList();
    ObservableList<Service> serviceList = FXCollections.observableArrayList();

    ObservableList<reportAvailableSeats> servicereportList = FXCollections.observableArrayList();

    public DataAccessObject() {

    }


    public ObservableList<BalanceModel> getUserData(String query) {
        ObservableList<BalanceModel> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new BalanceModel(rs.getString(1), rs.getInt(2)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return list;
    }
    public ObservableList<Booking> getBookingData(String query) {
        ObservableList<Booking> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return list;
    }


    public ObservableList<CountModel> getCountData(String query) {
        ObservableList<CountModel> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new CountModel(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return list;
    }


    public ObservableList<Chair> getChairData(String query) {
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                chairList.add(new Chair(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return chairList;
    }
    public ObservableList<Location> getLocationData(String query) {
        ObservableList<Location> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Location(rs.getString(1)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return list;
    }

    public ObservableList<Service> getServiceData(String query) {
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                serviceList.add(new Service(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime(), rs.getInt(7), rs.getString(8), rs.getString(9)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return serviceList;
    }


    public ObservableList<reportAvailableSeats> getReportServiceData(String query) {
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                servicereportList.add(new reportAvailableSeats(rs.getString(1), rs.getInt(2)));
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return servicereportList;
    }


    public void saveData(String query) {
        try {
            connect = database.getConnection(); // get connection
            pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, null);
        }
    }

    public int getCount(String checkQuery) {
        int count = 0;
        try {
            connect = database.getConnection(); // get connection
            pstmt = connect.prepareStatement(checkQuery);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        } finally {
            database.close(connect, pstmt, rs);
        }
        return count;
    }

    private void showExceptionDialog(Exception exception) {
        ExceptionDialog dialog = new ExceptionDialog(exception);
        dialog.setTitle("Exception");
        dialog.setHeaderText("An exception occurred");
        dialog.setContentText("Please see the details below:");
        dialog.showAndWait();
    }
}

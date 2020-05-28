package sample.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.database.DBHandller;
import sample.model.Paper;
import sample.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DashboardConroller {

    @FXML
    private JFXListView<Paper> lisOfQuestion;

    @FXML
    private JFXTextArea questionTextBox;

    @FXML
    private JFXTextField ansone;

    @FXML
    private JFXTextField anstwo;

    @FXML
    private JFXTextField currectans;

    @FXML
    private JFXTextField ansthree;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXDatePicker setdate;

    @FXML
    private JFXTimePicker setIme;

    @FXML
    private JFXButton setDateAndTimeButoon;


    private DBHandller handller;
    private ObservableList<Paper> listOfQA;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        setdate.setValue(LocalDate.now());
        setIme.setIs24HourView(true);
        setIme._24HourViewProperty().setValue(true);
        setIme.setValue(LocalTime.parse("13:00"));


        setDateAndTimeButoon.setOnAction(e->{

            System.out.println(setIme.getValue().format(DateTimeFormatter.ofPattern("HH.mm.ss")));
            handller=new DBHandller();
            User user=new User(setdate.getValue().toString(),setIme.getValue().format(DateTimeFormatter.ofPattern("HH.mm.ss")));
            try {
                handller.setTime(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });


        listOfQA= FXCollections.observableArrayList();
        handller=new DBHandller();

        ResultSet resultSet=handller.getQandA();

        while (resultSet.next()){
            Paper p=new Paper();
            List<String> ans=new ArrayList<>();

            p.setId(resultSet.getInt(1));
            p.setQuestion(resultSet.getString(2));
            ans.add(resultSet.getString(3));
            ans.add(resultSet.getString(4));
            ans.add(resultSet.getString(5));
            ans.add(resultSet.getString(6));
            p.setAnswars(ans);
            listOfQA.addAll(p);
        }
        lisOfQuestion.setItems(listOfQA);
        lisOfQuestion.setCellFactory(e->new ListCellController());
        lisOfQuestion.getSelectionModel().select(0);


        addButton.setOnAction(e->{

            if(!questionTextBox.getText().trim().equals("") && !ansone.getText().trim().equals("") && !anstwo.getText().trim().equals("") && !ansthree.getText().trim().equals("") && !currectans.getText().trim().equals("")){



                handller=new DBHandller();
                User user=new User(LocalDate.now().toString(),setIme.getValue().format(DateTimeFormatter.ofPattern("HH.mm.ss")));
                try {
                    handller.setTime(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                Paper p=new Paper();
                List<String> listOfAns=new ArrayList<>();
                p.setQuestion(questionTextBox.getText().trim());

                listOfAns.add(ansone.getText().trim());
                listOfAns.add(anstwo.getText().trim());
                listOfAns.add(currectans.getText().trim());
                listOfAns.add(ansthree.getText().trim());
                p.setAnswars(listOfAns);

                handller=new DBHandller();
                try {
                    handller.setData(p);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Entered details Successful");
                alert.setHeaderText(null);
                alert.show();
                alert.setOnCloseRequest(ee->{
                    try {
                        refreshList();
                        clearAll();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
            }else {

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Enter Details Properly");
                alert.setHeaderText(null);
                alert.show();
            }
        });
    }

    private void clearAll() {
        questionTextBox.setText("");
        anstwo.setText("");
        ansone.setText("");
        ansthree.setText("");
        currectans.setText("");
    }

    private void refreshList() throws SQLException, ClassNotFoundException {

        listOfQA= FXCollections.observableArrayList();
        handller=new DBHandller();

        ResultSet resultSet=handller.getQandA();

        while (resultSet.next()){
            Paper p=new Paper();
            List<String> ans=new ArrayList<>();

            p.setId(resultSet.getInt(1));
            p.setQuestion(resultSet.getString(2));
            ans.add(resultSet.getString(3));
            ans.add(resultSet.getString(4));
            ans.add(resultSet.getString(5));
            ans.add(resultSet.getString(6));
            p.setAnswars(ans);
            listOfQA.addAll(p);
        }
        lisOfQuestion.setItems(listOfQA);
        lisOfQuestion.setCellFactory(e->new ListCellController());
        lisOfQuestion.getSelectionModel().select(0);

    }
}

package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import sample.database.DBHandller;
import sample.model.Paper;

import java.io.IOException;
import java.sql.SQLException;

public class ListCellController extends JFXListCell<Paper> {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label qText;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXComboBox<String> answaersAll;

    private FXMLLoader loader;
    private ObservableList<String> ansLis;
    private int index;


    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Paper item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
            setText(null);
        }
       else {
           if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/sample/view/listcellview.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

                ansLis= FXCollections.observableArrayList();

                qText.setText(item.getQuestion());
                ansLis.addAll(item.getAnswars());
                answaersAll.setItems(ansLis);
                answaersAll.setPromptText(ansLis.get(2));

                getListView().setOnMouseClicked(event -> {
                    index=getListView().getSelectionModel().getSelectedIndex();
                });

                deleteButton.setOnAction(event -> {
                    int id=getListView().getItems().get(index).getId();
                    DBHandller handller=new DBHandller();

                    try {
                        handller.getDelete(id);
                        getListView().getItems().remove(index);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                });






                setGraphic(pane);
                setText(null);



        }
    }
}


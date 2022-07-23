package com.example.praktikum_database_2072007.Controller;

import com.example.praktikum_database_2072007.MenuApplication;
import com.example.praktikum_database_2072007.dao.MenuDao;
import com.example.praktikum_database_2072007.dao.CategoryDao;
import com.example.praktikum_database_2072007.model.Category;
import com.example.praktikum_database_2072007.model.Menu;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class MenuController {

    public TextField txtID;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtDescription;
    public ComboBox<Category> CombCategory;
    public Button BtnSave;
    public Button BtnReset;
    public Button BtnUpdate;
    public Button BtnDelete;
    public TableView<Menu> TableMenu;
    public TableColumn<String,Menu> ColID;
    public TableColumn<String,Menu> ColNama;
    public TableColumn<Float,Menu> ColPrice;
    public TableColumn<Category, Menu> ColCategory;
    public MenuItem Category;
    public MenuItem Close;
    private Stage stage;
    ObservableList<Menu> mList_tampilan;
    ObservableList<Category> cList_tampilan;

    public void initialize() throws IOException {
        stage = new Stage();
        SelectedCategory();
        Category.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        Close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        ShowData();
    }

    public void GoToCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("Category-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 558, 350);

        CategoryController ctgController = fxmlLoader.getController();

        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void Closed(ActionEvent actionEvent) {
        txtID.getScene().getWindow().hide();
    }

    public void ShowData() {
        MenuDao dao = new MenuDao();
        mList_tampilan = dao.getData();
        TableMenu.setItems(mList_tampilan);
        ColID.setCellValueFactory(new PropertyValueFactory<>("idMenu"));
        ColNama.setCellValueFactory(new PropertyValueFactory<>("namaMenu"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("hargaMenu"));
        ColCategory.setCellValueFactory(new PropertyValueFactory<>("Category_idCategory"));
        BtnUpdate.setDisable(true);
        BtnDelete.setDisable(true);
    }

    public void SaveData(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        if(txtID.getText() == null || txtName.getText() == null || txtPrice.getText() == null || txtDescription.getText() == null || CombCategory.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Please Fill all the field",ButtonType.OK);
            alert.showAndWait();
        } else {
            dao.addData(new Menu(Integer.parseInt(txtID.getText()), txtName.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText(), CombCategory.getValue()));
            ShowData();
            ResetData();
        }
    }

    public void ResetData() {
        txtID.clear();
        txtName.clear();
        txtPrice.clear();
        txtDescription.clear();
        CombCategory.getSelectionModel().select(-1);
    }

    public void SelectedCategory() {
        CategoryDao dao = new CategoryDao();
        cList_tampilan = dao.getData();
        CombCategory.setItems(cList_tampilan);
    }

    public void UpdateData(ActionEvent actionEvent) {

    }

    public void DeleteData(ActionEvent actionEvent) {

    }
}

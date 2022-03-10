/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Category;
import edu.arena.services.CategoryCRUD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ProductCatController implements Initializable {

    @FXML
    private JFXTextField catNameFld;
    @FXML
    private JFXTextField catDescFld;
    @FXML
    private TableView<Category> categoriesTable;
    @FXML
    private TableColumn<Category, String> catNameCol;
    @FXML
    private TableColumn<Category, String> catDescCol;

    ObservableList<Category> CategoriesList = FXCollections.observableArrayList();
    private Category catItem;
    @FXML
    private JFXButton addBtn;
    @FXML
    private Label catNumberLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void refreshTable() {
        try {
            CategoriesList.clear();
            CategoryCRUD ccrud = new CategoryCRUD();

            List<Category> categories = ccrud.showAllCategories();
            CategoriesList.setAll(categories);
            categoriesTable.setItems(CategoriesList);

            if (categories.size() == 1) {
                catNumberLbl.setText(Integer.toString(categories.size()) + " category");
            } else {
                catNumberLbl.setText(Integer.toString(categories.size()) + " categories");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadData() {
        refreshTable();

        catNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        catDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
    }

    private void clean() {
        catNameFld.setText(null);
        catDescFld.setText(null);
    }

    @FXML
    private void addCategory(ActionEvent event) {
        if (catNameFld.getText().length() == 0) {
            catNameFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(catNameFld).play();
        } else {
            catNameFld.setStyle(null);
        }
        if (catDescFld.getText().length() == 0) {
            catDescFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(catDescFld).play();
        } else {
            catDescFld.setStyle(null);
        }

        String name = catNameFld.getText();
        String desc = catDescFld.getText();

        Category c = new Category(name, desc);

        CategoryCRUD ccrud = new CategoryCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            ccrud.addCategory(c);
            alert.setTitle("Success");
            alert.setHeaderText("added");
            alert.setContentText("category added successfully");
            clean();
            refreshTable();
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not addedd");
            alert.setContentText("category isn't added");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void updateCategory(ActionEvent event) {
        if (catNameFld.getText().length() == 0) {
            catNameFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(catNameFld).play();
        } else {
            catNameFld.setStyle(null);
        }
        if (catDescFld.getText().length() == 0) {
            catDescFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(catDescFld).play();
        } else {
            catDescFld.setStyle(null);
        }

        String name = catNameFld.getText();
        String desc = catDescFld.getText();
        int id = catItem.getId();
        
        CategoryCRUD ccrud = new CategoryCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            ccrud.updateCategory(name, desc, id);
            alert.setTitle("Success");
            alert.setHeaderText("updated");
            alert.setContentText("category updated successfully");
            clean();
            refreshTable();
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not updated");
            alert.setContentText("category isn't updated");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteCategory(ActionEvent event) {

        int id = catItem.getId();
        CategoryCRUD ccrud = new CategoryCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            ccrud.deleteCategory(id);
            alert.setTitle("Success");
            alert.setHeaderText("deleted");
            alert.setContentText("category deleted successfully");
            clean();
            refreshTable();
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not deleted");
            alert.setContentText("category isn't deleted");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void selectCategory(MouseEvent event) {
        catItem = categoriesTable.getSelectionModel().getSelectedItem();
        catNameFld.setText(catItem.getName());
        catDescFld.setText(catItem.getDesc());
        addBtn.setDisable(true);
    }

    @FXML
    private void release(MouseEvent event) {
        clean();
        addBtn.setDisable(false);
    }

}

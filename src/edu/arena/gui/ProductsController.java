/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Category;
import edu.arena.entities.Product;
import edu.arena.services.CategoryCRUD;
import edu.arena.services.ProductCRUD;
import edu.arena.services.ProductSrvc;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ProductsController implements Initializable {

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Integer> qtyCol;
    @FXML
    private TableColumn<Product, String> descCol;
    @FXML
    private TableColumn<Product, String> catCol;
    @FXML
    private TableColumn<Product, Integer> priceCol;
    @FXML
    private TableColumn<Product, String> imageCol;

    ObservableList<Product> ProductsList = FXCollections.observableArrayList();
    ObservableList<Category> CategoriesList = FXCollections.observableArrayList();
    private Product productItem;

    @FXML
    private JFXTextField nameFld;
    @FXML
    private JFXTextField qtyFld;
    @FXML
    private JFXTextField descFld;
    @FXML
    private JFXTextField imageFld;
    @FXML
    private JFXTextField priceFld;
    @FXML
    private JFXComboBox<Category> catFld;
    @FXML
    private JFXButton addBtn;
    @FXML
    private Label productsNumberLbl;
    @FXML
    private JFXTextField searchByNameFld;
    @FXML
    private PieChart productsChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
            displayChart();
            CategoryCRUD ccrud = new CategoryCRUD();

            List<Category> categories = ccrud.showAllCategories();
            CategoriesList.setAll(categories);
            catFld.setItems(CategoriesList);

//            ValidationSupport validate = new ValidationSupport();
//            validate.setErrorDecorationEnabled(false);
//            validate.registerValidator(nameFld, Validator.createEmptyValidator("Name is required"));
//            validate.registerValidator(qtyFld, Validator.createEmptyValidator("Quantity is required"));
//            validate.registerValidator(descFld, Validator.createEmptyValidator("Description is required"));
//            validate.registerValidator(imageFld, Validator.createEmptyValidator("Image is required"));
//            validate.registerValidator(catFld, Validator.createEmptyValidator("Category is required"));
//            validate.registerValidator(priceFld, Validator.createEmptyValidator("Price is required"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    private void displayChart() {

        try {
            pieChartData.clear();
            productsChart.getData().clear();
            ProductCRUD pcrud = new ProductCRUD();
            CategoryCRUD ccrud = new CategoryCRUD();

            List<Product> products = pcrud.showAllProducts();
            List<Category> categories = ccrud.showAllCategories();

//            int categoriesN = categories.size();
            for (Category c : categories) {
                int catN = 0;
                for (Product p : products) {
                    if (c.getName().equals(p.getCatName())) {
                        catN++;
                    }
                }

                PieChart.Data data = new PieChart.Data(c.getName(), catN);
                pieChartData.add(data);

            }

//            pieChartData
//                    = FXCollections.observableArrayList(
//                            new PieChart.Data("Clothes", clothesN),
//                            new PieChart.Data("T-Shirts", tshirtsN),
//                            new PieChart.Data("Games", gamesN)
//                    );
//        pieChartData.forEach(data
//                -> data.nameProperty().bind(
//                        Bindings.concat(
//                                data.getName(), " amount: ", data.pieValueProperty()
//                        )
//                )
//        );
//            productsChart.setTitle("Products");
            productsChart.setLabelsVisible(true);
            productsChart.setLegendVisible(true);
            productsChart.getData().addAll(pieChartData);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void refreshTable() {
        try {
            ProductsList.clear();
            ProductCRUD pcrud = new ProductCRUD();

            List<Product> products = pcrud.showAllProducts();
            ProductsList.setAll(products);
//            System.out.print(products);
            Collections.reverse(ProductsList);
            productsTable.setItems(ProductsList);

            if (products.size() == 1) {
                productsNumberLbl.setText(Integer.toString(products.size()) + " product");
            } else {
                productsNumberLbl.setText(Integer.toString(products.size()) + " products");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadData() {
        refreshTable();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("catName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
    }

    private void clean() {
        nameFld.setText(null);
        qtyFld.setText(null);
        descFld.setText(null);
        imageFld.setText(null);
        priceFld.setText(null);
        catFld.setValue(null);
    }

    @FXML
    private void addProduct(ActionEvent event) {
        if (nameFld.getText().length() == 0) {
            nameFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(nameFld).play();
        } else {
            nameFld.setStyle(null);
        }
        if (priceFld.getText().length() == 0) {
            priceFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(priceFld).play();
        } else {
            priceFld.setStyle(null);
        }
        if (qtyFld.getText().length() == 0) {
            qtyFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(qtyFld).play();
        } else {
            qtyFld.setStyle(null);
        }
        if (descFld.getText().length() == 0) {
            descFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(descFld).play();
        } else {
            descFld.setStyle(null);
        }
        if (imageFld.getText().length() == 0) {
            imageFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(imageFld).play();
        } else {
            imageFld.setStyle(null);
        }
        if (catFld.getValue() == null) {
            catFld.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(catFld).play();
        } else {
            catFld.setStyle(null);
        }

        String name = nameFld.getText();
        int price = Integer.parseInt(priceFld.getText());
        int qty = Integer.parseInt(qtyFld.getText());
        String desc = descFld.getText();
        String image = imageFld.getText();
        int idCat = catFld.getValue().getId();
        Product p = new Product(name, price, qty, desc, image, idCat);

        ProductCRUD pcrud = new ProductCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            pcrud.addProduct(p);
            alert.setTitle("Success");
            alert.setHeaderText("added");
            alert.setContentText("product added successfully");
            clean();
            refreshTable();
            displayChart();
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not addedd");
            alert.setContentText("product isn't added");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        String name = nameFld.getText();
        int price = Integer.parseInt(priceFld.getText());
        String desc = descFld.getText();
        int qty = Integer.parseInt(qtyFld.getText());
        String image = imageFld.getText();
        int idCat = productItem.getIdCategory();
        int id = productItem.getId();

        ProductCRUD pcrud = new ProductCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        pcrud.updateProduct(name, price, qty, desc, image, idCat, id);
        alert.setTitle("Success");
        alert.setHeaderText("updated");
        alert.setContentText("product updated successfully");
        clean();
        refreshTable();
        displayChart();
        alert.showAndWait();
    }

    @FXML
    private void deleteProduct(ActionEvent event) {

        int id = productItem.getId();
        ProductCRUD pcrud = new ProductCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            pcrud.deleteProduct(id);
            alert.setTitle("Success");
            alert.setHeaderText("deleted");
            alert.setContentText("product deleted successfully");
            clean();
            refreshTable();
            displayChart();
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not deleted");
            alert.setContentText("product isn't deleted");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void selectProduct(MouseEvent event) {
        productItem = productsTable.getSelectionModel().getSelectedItem();
        nameFld.setText(productItem.getName());
        descFld.setText(productItem.getDesc());
        qtyFld.setText(Integer.toString(productItem.getQty()));
        priceFld.setText(Integer.toString(productItem.getPrice()));
        imageFld.setText(productItem.getImage());
        addBtn.setDisable(true);
        Category c = new Category(productItem.getIdCategory(), productItem.getCatName(), productItem.getCatDesc());
        catFld.setValue(c);
        catFld.getSelectionModel().select(c);
    }

    @FXML
    private void search(KeyEvent event) {
        FilteredList<Product> filteredData = new FilteredList<>(ProductsList, b -> true);
        searchByNameFld.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (p.getCatName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });

        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
        productsTable.setItems(sortedData);
    }

    @FXML
    private void exportTable(ActionEvent event) throws SQLException, FileNotFoundException {
        ProductSrvc ps = new ProductSrvc();
        ps.exportTable();
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newimageFile = imageFile.replace("C:\\Users\\LENOVO\\Documents\\github\\Arena\\src\\resources\\", "");

            imageFld.setText(newimageFile);
        }
    }

    @FXML
    private void release(MouseEvent event) {
        clean();
        addBtn.setDisable(false);
    }

}

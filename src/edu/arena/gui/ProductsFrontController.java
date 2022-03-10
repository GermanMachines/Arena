/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Product;
import edu.arena.services.ProductCRUD;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ProductsFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private Product p;

    public static List<Product> productsList = new ArrayList<>();
    public static List<Product> prod = new ArrayList<>();

    private MyListener myListener;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXSlider priceSlider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
        // TODO
//            ProductCRUD pcrud = new ProductCRUD();
//            List products = pcrud.showAllProducts();
//            System.out.println(products);
//            productsList.addAll(products);
        priceSlider.setValue(0);
        try {
            ProductCRUD pcrud = new ProductCRUD();

            productsList.addAll(pcrud.showAllProducts());
            Collections.reverse(productsList);

            prod.addAll(getData());
            Collections.reverse(prod);

            int column = 0;
            int row = 1;
            for (int i = 0; i < productsList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemProduct.fxml"));
                AnchorPane anchorPane;
                anchorPane = fxmlLoader.load();

                ItemProductController itemController = fxmlLoader.getController();
                itemController.setData(productsList.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Product> getData() {
        List<Product> productItem = new ArrayList<>();
        Product pro;

        for (Product p : productsList) {
            pro = new Product();
            pro.setName(p.getName());
            pro.setRate(p.getRate());
            pro.setImage(p.getImage());

            productItem.add(pro);
        }

        return productItem;

    }

    private List<Product> productss = new ArrayList<>();
    private List<Product> filteredSearch = new ArrayList<>();

    @FXML
    private void onClickSearch(ActionEvent event) {
        filteredSearch = new ArrayList<>();

        if (searchBar.getText().equals("") && priceSlider.getValue() <= 0) {
            filteredSearch = productsList;

        }
        if (searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            filteredSearch = filtredPrixServices;

        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() <= 0) {

            for (Product p : productsList) {

                if (p.getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filteredSearch.add(p);
                }
            }

        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() > 0) {

            for (Product p : filtredPrixServices) {

                if (p.getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filteredSearch.add(p);
                }
            }

        }
//        if (searchBar.getText().equals("")) {
//            filteredSearch = productsList;
//        } else {
//            for (Product p : productsList) {
//                if (p.getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
//                    filteredSearch.add(p);
//                }
//            }
//
//            System.out.println(filteredSearch);
//        }

        int column = 0;
        int row = 1;
        grid.getChildren().clear();

        try {
            for (int i = 0; i < filteredSearch.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemProduct.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ItemProductController itemController = fxmlLoader.getController();
                itemController.setData(filteredSearch.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToCart(ActionEvent event) throws IOException {
        URL fxURL = getClass().getResource("ProductsCart.fxml");
        FXMLLoader loader = new FXMLLoader(fxURL);
        Parent root = loader.load();
        ProductsCartController pcc = loader.getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void redirectToMyOrders(ActionEvent event) throws IOException {
        URL fxURL = getClass().getResource("MyOrders.fxml");
        FXMLLoader loader = new FXMLLoader(fxURL);
        Parent root = loader.load();
        MyOrdersController moc = loader.getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }

    List<Product> filtredPrixServices = new ArrayList<>();
    private int productSlider = 0;

    @FXML
    private void filterByPrice(MouseEvent event) {
        filtredPrixServices = new ArrayList<>();
        productSlider = (int) priceSlider.getValue();

        if (searchBar.getText().equals("") && priceSlider.getValue() <= 0) {
            filtredPrixServices = productsList;
        }

        if (searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            for (Product p : productsList) {

                if (p.getPrice() >= productSlider) {
                    filtredPrixServices.add(p);
                }
            }
        }
        if (!searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            for (Product p : filteredSearch) {

                if (p.getPrice() >= productSlider) {
                    filtredPrixServices.add(p);
                }
            }
        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() <= 0) {

            for (Product p : filteredSearch) {

                if (p.getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filtredPrixServices.add(p);
                }
            }

        }
        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < filtredPrixServices.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemProduct.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ItemProductController itemController = fxmlLoader.getController();
                itemController.setData(filtredPrixServices.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

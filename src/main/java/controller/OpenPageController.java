package controller;

import database.HandleData;
import javafx.scene.control.ComboBox;
import model.algorithm.DepthFirst;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.train.ProblemTrain;
import model.train.StateTrain;
import model.train.cost.CostFunction;
import model.train.cost.SampleCost;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class OpenPageController {
    private final ArrayList<Integer> utaslista = new ArrayList<>();
    private Integer traveller;
    private String fxmlName;
    private CostFunction cost;

    @FXML
    ComboBox jarat;

    @FXML
    TextField utas;

    @FXML
    private void initialize(){
        List<String> list = HandleData.getDepartingTrains();
        ObservableList<String> olist= FXCollections.observableArrayList(list);
        jarat.setItems(olist);
        jarat.setPromptText("Válasszon járatot!");

        numericOnly(utas);
    }

    private static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private static void changeScreen(Integer numberOfSeats,Optional<List<StateTrain>> goalState, ActionEvent event) throws IOException {
        if(numberOfSeats == 40) {
            FXMLLoader fxmlLoader = new FXMLLoader(OpenPageController.class.getResource("/seat40.fxml"));
            Parent root = fxmlLoader.load();
            Seat40Controller controller = fxmlLoader.<Seat40Controller>getController();
            controller.setUlesrend(goalState.get().get(goalState.get().size() - 1));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if(numberOfSeats == 60){
            FXMLLoader fxmlLoader = new FXMLLoader(OpenPageController.class.getResource("/seat60.fxml"));
            Parent root = fxmlLoader.load();
            Seat60Controller controller = fxmlLoader.<Seat60Controller>getController();
            controller.setUlesrend(goalState.get().get(goalState.get().size() - 1));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if(numberOfSeats == 80){
            FXMLLoader fxmlLoader = new FXMLLoader(OpenPageController.class.getResource("/seat80.fxml"));
            Parent root = fxmlLoader.load();
            Seat80Controller controller = fxmlLoader.<Seat80Controller>getController();
            controller.setUlesrend(goalState.get().get(goalState.get().size() - 1));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Nincs megjeleníthető képernyő");
        }
    }

    @FXML
    public void handleCalculateButton(ActionEvent event) throws IOException {
        String[] train = String.valueOf(jarat.getValue()).split(" ");
        Integer numberOfSeats = HandleData.getSeatsNum(LocalDateTime.parse(train[0]), train[1]);
        String disposition = HandleData.getDisposition(LocalDateTime.parse(train[0]), train[1]);

        if (disposition.equals("SampleTrain")){
            cost = new SampleCost();
        } else{
            System.out.println("Nincs megfelelő");
            cost = new SampleCost();
        }

        ProblemTrain problem = new ProblemTrain(numberOfSeats, utaslista);
        DepthFirst method = new DepthFirst();
        Optional<List<StateTrain>> goalState = method.run(problem, cost);
        if (goalState.isPresent()) {
            changeScreen(numberOfSeats, goalState, event);
        } else {
            System.out.println("No goal state.");
        }
    }

    @FXML
    public void handleSaveButton(ActionEvent event){
        Integer traveller = Integer.parseInt(utas.getText());
        utas.setText("");
        utaslista.add(traveller);
        System.out.println(utaslista);
    }
}

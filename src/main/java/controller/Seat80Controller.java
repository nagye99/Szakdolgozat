package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import model.train.StateTrain;

public class Seat80Controller {

    private StackPane[] ulesek;

    @FXML
    StackPane ules1;

    @FXML
    StackPane ules2;

    @FXML
    StackPane ules3;

    @FXML
    StackPane ules4;

    @FXML
    StackPane ules5;

    @FXML
    StackPane ules6;

    @FXML
    StackPane ules7;

    @FXML
    StackPane ules8;

    @FXML
    StackPane ules9;

    @FXML
    StackPane ules10;

    @FXML
    StackPane ules11;

    @FXML
    StackPane ules12;

    @FXML
    StackPane ules13;

    @FXML
    StackPane ules14;

    @FXML
    StackPane ules15;

    @FXML
    StackPane ules16;

    @FXML
    StackPane ules17;

    @FXML
    StackPane ules18;

    @FXML
    StackPane ules19;

    @FXML
    StackPane ules20;

    @FXML
    StackPane ules21;

    @FXML
    StackPane ules22;

    @FXML
    StackPane ules23;

    @FXML
    StackPane ules24;

    @FXML
    StackPane ules25;

    @FXML
    StackPane ules26;

    @FXML
    StackPane ules27;

    @FXML
    StackPane ules28;

    @FXML
    StackPane ules29;

    @FXML
    StackPane ules30;

    @FXML
    StackPane ules31;

    @FXML
    StackPane ules32;

    @FXML
    StackPane ules33;

    @FXML
    StackPane ules34;

    @FXML
    StackPane ules35;

    @FXML
    StackPane ules36;

    @FXML
    StackPane ules37;

    @FXML
    StackPane ules38;

    @FXML
    StackPane ules39;

    @FXML
    StackPane ules40;

    @FXML
    StackPane ules41;

    @FXML
    StackPane ules42;

    @FXML
    StackPane ules43;

    @FXML
    StackPane ules44;

    @FXML
    StackPane ules45;

    @FXML
    StackPane ules46;

    @FXML
    StackPane ules47;

    @FXML
    StackPane ules48;

    @FXML
    StackPane ules49;

    @FXML
    StackPane ules50;

    @FXML
    StackPane ules51;

    @FXML
    StackPane ules52;

    @FXML
    StackPane ules53;

    @FXML
    StackPane ules54;

    @FXML
    StackPane ules55;

    @FXML
    StackPane ules56;

    @FXML
    StackPane ules57;

    @FXML
    StackPane ules58;

    @FXML
    StackPane ules59;

    @FXML
    StackPane ules60;

    @FXML
    StackPane ules61;

    @FXML
    StackPane ules62;

    @FXML
    StackPane ules63;

    @FXML
    StackPane ules64;

    @FXML
    StackPane ules65;

    @FXML
    StackPane ules66;

    @FXML
    StackPane ules67;

    @FXML
    StackPane ules68;

    @FXML
    StackPane ules69;

    @FXML
    StackPane ules70;

    @FXML
    StackPane ules71;

    @FXML
    StackPane ules72;

    @FXML
    StackPane ules73;

    @FXML
    StackPane ules74;

    @FXML
    StackPane ules75;

    @FXML
    StackPane ules76;

    @FXML
    StackPane ules77;

    @FXML
    StackPane ules78;

    @FXML
    StackPane ules79;

    @FXML
    StackPane ules80;

    @FXML
    private void initialize(){
        ulesek = new StackPane[]{ules1, ules2, ules3, ules4, ules5, ules6, ules7, ules8, ules9, ules10, ules11, ules12, ules13, ules14, ules15, ules16, ules17, ules18, ules19, ules20, ules21, ules22, ules23, ules24, ules25, ules26, ules27, ules28, ules29, ules30, ules31, ules32, ules33, ules34, ules35, ules36, ules37, ules38, ules39, ules40,
                ules41, ules42, ules43, ules44, ules45, ules46, ules47, ules48, ules49, ules50, ules51, ules52, ules53, ules54, ules55, ules56, ules57, ules58, ules59, ules60, ules61, ules62, ules63, ules64, ules65, ules66, ules67, ules68, ules69, ules70, ules71, ules72, ules73, ules74, ules75, ules76, ules77, ules78, ules79, ules80};
    }

    public void setUlesrend(StateTrain state){
        for(int i = 1; i<state.getState().length; i++){
            if(state.getState()[i] != 0){
                ulesek[i-1].getStyleClass().add("foglalt");
            } else {
                ulesek[i-1].getStyleClass().add("ures");
            }
        }
    }
}

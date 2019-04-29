package org.isj.interfaces.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.isj.interfaces.main.view.RecuperationMotDePasse1;
import org.isj.interfaces.main.view.SeConnecter;

import java.io.IOException;

public class Appli extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try{
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("ISJ Application");

            showSeConnecter();
            showMdp();
        }catch (Exception e){
            e.getMessage();
        }

    }

    public void showSeConnecter() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Appli.class.getResource("view/seConnecter.fxml"));
            AnchorPane connex = loader.load();

            Scene fen = new Scene(connex);
            primaryStage.setScene(fen);
            primaryStage.show();
            //dialogStage.setScene(fen);

            //SeConnecter controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            //dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMdp() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Appli.class.getResource("view/recuperationMotDePasse.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Récupérer le mot de passe");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {

        return primaryStage;
    }

    public static void main(String[] args) {

        launch(args);
    }
}

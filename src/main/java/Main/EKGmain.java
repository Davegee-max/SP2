package Main;

import SerialPort.EKGSensor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EKGmain extends Application {



    public static void main(String[] args) throws InterruptedException {
        launch(args);
            }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Startside.fxml"));
        Parent load = null;
        try {
            load = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Data.EKG LAUNCHER 2000");
        primaryStage.setScene(new Scene(load));
        primaryStage.show();

    }
}



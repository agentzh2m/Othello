package fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by karn806 on 9/18/17.
 */
public class Main extends Application{
        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("style.fxml"));
            primaryStage.setTitle("Orthello aka Reversi");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();

        }

}

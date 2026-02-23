package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Grid;
import com.nihal.nbodyproblem.Body.Body;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class StartAnimation extends Application {
    @Override
    public void start(Stage stage) {
        Pane world = new Pane();
        world.setStyle("-fx-background-color: black;");

        Grid grid = new Grid();
        world.getChildren().add(grid);

        Body[] bodies = new Body[Constants.N];
        int[] numOfBodies = {0};

        world.setOnMouseClicked(event ->{
            if (numOfBodies[0] == Constants.N)
                return;
            bodies[numOfBodies[0]] = new Body(event.getX(), event.getY());

            world.getChildren().add(bodies[numOfBodies[0]]);
            numOfBodies[0]++;
        });



        stage.setTitle("Circle Simulation");
        stage.setScene(new Scene(world, Constants.worldWidth, Constants.worldHeight));
        stage.show();
    }
}

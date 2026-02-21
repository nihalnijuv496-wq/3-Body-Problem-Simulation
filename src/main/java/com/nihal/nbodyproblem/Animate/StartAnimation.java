package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Grid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class StartAnimation extends Application {
    @Override
    public void start(Stage stage) {
        Pane world = new Pane();

        Grid grid = new Grid();
        world.getChildren().add(grid);

        stage.setTitle("Circle Simulation");
        stage.setScene(new Scene(world, Constants.worldWidth, Constants.worldHeight ,Color.BLACK));
        stage.show();
    }
}

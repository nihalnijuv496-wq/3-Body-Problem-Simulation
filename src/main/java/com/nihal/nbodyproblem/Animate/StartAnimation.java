package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.UI.ButtonKey;
import com.nihal.nbodyproblem.UI.CONTROLBUTTON;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.UI.Grid;
import com.nihal.nbodyproblem.Body.Body;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class StartAnimation extends Application {
    @Override
    public void start(Stage stage) {
        setUpScene(stage);
    }

    private void setUpScene(Stage stage)
    {
        Pane world = new Pane();
        world.setStyle("-fx-background-color: black;");

        Grid grid = new Grid();
        world.getChildren().add(grid);

        List<Body> bodies = new ArrayList<>();
        int[] numOfBodies = {0};

        world.setOnMouseClicked(event ->{
            if (numOfBodies[0] == Constants.N)
                return;

            bodies.add(new Body(event.getX(), event.getY()));
            bodies.getLast().setFill(Constants.bodyColors[numOfBodies[0]]);
            world.getChildren().add(bodies.getLast());
            numOfBodies[0]++;
        });


        Timeloop timeloop = new Timeloop(bodies);

        ButtonKey[] controlButtons = {
                ButtonKey.addControlButton("Start", CONTROLBUTTON.START, timeloop, numOfBodies, bodies, world),
                ButtonKey.addControlButton("Restart", CONTROLBUTTON.RESTART, timeloop, numOfBodies, bodies, world),
                ButtonKey.addControlButton("Reset", CONTROLBUTTON.RESET, timeloop, numOfBodies, bodies, world)};

        for (ButtonKey buttonKey: controlButtons)
            world.getChildren().add(buttonKey);




        stage.setTitle("Circle Simulation");
        stage.setScene(new Scene(world, Constants.worldWidth, Constants.worldHeight));
        stage.show();
    }
}

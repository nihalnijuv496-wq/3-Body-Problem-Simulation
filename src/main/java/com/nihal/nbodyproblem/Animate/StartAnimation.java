package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.Util.ButtonKey;
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
        setUpScene(stage);
    }

    private void setUpScene(Stage stage)
    {
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
            bodies[numOfBodies[0]].setFill(Constants.bodyColors[numOfBodies[0]]);
            world.getChildren().add(bodies[numOfBodies[0]]);
            numOfBodies[0]++;
        });

        Timeloop timeloop = new Timeloop(bodies);
        ButtonKey startButton =
                new ButtonKey("Start",
                        Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                        (Constants.worldHeight - 3*Constants.buttonHeight - 2*Constants.buttonSpacing)/2);
        ButtonKey restartButton =
                new ButtonKey("Restart",
                        Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                        (Constants.worldHeight - Constants.buttonHeight)/2);
        ButtonKey resetButton =
                new ButtonKey("Reset",
                        Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                        (Constants.worldHeight + Constants.buttonHeight + 2*Constants.buttonSpacing)/2);
        world.getChildren().addAll(startButton, restartButton, resetButton);


        startButton.setOnAction(e -> {
            if (numOfBodies[0] < Constants.N)
                return;
            startButton.setText(timeloop.pauseOrPlay());
        });
        restartButton.setOnAction(e -> {
            if (numOfBodies[0] < Constants.N)
                return;
            for(Body body: bodies)
                body.resetFieldsToInitial();
            timeloop.pause();
        });
        resetButton.setOnAction(e -> {
            if (numOfBodies[0] < Constants.N)
                return;
            timeloop.pause();
            setUpScene(stage);
        });


        stage.setTitle("Circle Simulation");
        stage.setScene(new Scene(world, Constants.worldWidth, Constants.worldHeight));
        stage.show();
    }
}

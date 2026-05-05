package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.UI.ButtonKey;
import com.nihal.nbodyproblem.UI.CONTROLBUTTON;
import com.nihal.nbodyproblem.UI.SideBar.DataInputBox;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.UI.Grid;
import com.nihal.nbodyproblem.Body.Body;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        HBox root = new HBox();


        Pane world = new Pane();
        world.setStyle(Constants.worldStyle);

        Grid grid = new Grid();
        world.getChildren().add(grid);

        List<Body> bodies = new ArrayList<>();
        SideBar sideBar = new SideBar();

        int[] numOfBodies = {0};

        world.setOnMouseClicked(event ->{
            if (numOfBodies[0] == Constants.N)
                return;
            sideBar.addNewTab(numOfBodies[0], bodies);
            bodies.add(new Body(event.getX(), event.getY(), sideBar.getLastDataInputBox()));
            bodies.getLast().setFill(Constants.bodyColors[numOfBodies[0]]);
            world.getChildren().add(bodies.getLast());
            numOfBodies[0]++;

            if (numOfBodies[0] == 1)
            {
                sideBar.setDefaultTab();
            }
        });


        Timeloop timeloop = new Timeloop(bodies);

        ButtonKey[] controlButtons = {
                ButtonKey.addControlButton("Start", CONTROLBUTTON.START, timeloop, numOfBodies, bodies, world, sideBar),
                ButtonKey.addControlButton("Restart", CONTROLBUTTON.RESTART, timeloop, numOfBodies, bodies, world, sideBar),
                ButtonKey.addControlButton("Reset", CONTROLBUTTON.RESET, timeloop, numOfBodies, bodies, world, sideBar)};

        for (ButtonKey buttonKey: controlButtons)
            world.getChildren().add(buttonKey);


        //=================================================/



        root.getChildren().addAll(sideBar, world);
        stage.setTitle(Constants.N + "Body Simulation");
        stage.setScene(new Scene(root, Constants.worldWidth + Constants.sideBarWidth, Constants.worldHeight));
        stage.show();
    }
}
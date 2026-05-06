package com.nihal.nbodyproblem.Animate;

import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.UI.ButtonKey;
import com.nihal.nbodyproblem.UI.CONTROLBUTTON;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.UI.Grid;
import com.nihal.nbodyproblem.Body.Body;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StartAnimation extends Application {
    @Override
    public void start(Stage stage) {
        setUpScene(stage);
    }

    private void setUpScene(Stage stage)
    {
        HBox root = new HBox();


        Pane world = new Pane();

        Grid grid = new Grid();
        world.getChildren().add(grid);

        List<BodyWrapper> bodyWrappers = new ArrayList<>();
        SideBar sideBar = new SideBar();

        int[] numOfBodies = {0};

        world.setOnMouseClicked(event ->{
            if (numOfBodies[0] == Constants.N)
                return;

            sideBar.addNewTab(numOfBodies[0], bodyWrappers);
            bodyWrappers.add(new BodyWrapper(new Body(event.getX(), event.getY(), sideBar.getLastDataInputBox())));
            bodyWrappers.getLast().getBody().setFill(Constants.bodyColors[numOfBodies[0]]);
            world.getChildren().add(bodyWrappers.getLast());
            numOfBodies[0]++;

            if (numOfBodies[0] == 1)
            {
                sideBar.setDefaultTab();
            }
        });


        Timeloop timeloop = new Timeloop(bodyWrappers);

        ButtonKey[] controlButtons = {
                ButtonKey.addControlButton("Start", CONTROLBUTTON.START, timeloop, numOfBodies, bodyWrappers, world, sideBar),
                ButtonKey.addControlButton("Restart", CONTROLBUTTON.RESTART, timeloop, numOfBodies, bodyWrappers, world, sideBar),
                ButtonKey.addControlButton("Reset", CONTROLBUTTON.RESET, timeloop, numOfBodies, bodyWrappers, world, sideBar)};

        for (ButtonKey buttonKey: controlButtons)
            world.getChildren().add(buttonKey);


        //=================================================/



        root.getChildren().addAll(sideBar, world);
        stage.setTitle(Constants.N + "Body Simulation");
        Scene scene = new Scene(root, Constants.worldWidth + Constants.sideBarWidth, Constants.worldHeight);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
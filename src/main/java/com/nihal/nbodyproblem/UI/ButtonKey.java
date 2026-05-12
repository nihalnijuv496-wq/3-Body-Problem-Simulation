package com.nihal.nbodyproblem.UI;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.List;

public class ButtonKey extends Button {

    public ButtonKey(String title, int x, int y)
    {
        setText(title);
        setPrefSize(Constants.buttonWidth, Constants.buttonHeight);
        getStyleClass().add("control-button");
        setLayoutX(x);
        setLayoutY(y);
    }

    static boolean isFirstClick = true;
    public static ButtonKey addControlButton
            (String title, CONTROLBUTTON type, Timeloop timeloop, int[] numberOfBodies, List<BodyWrapper> bodyWrappers, Pane world, SideBar sb)
    {

        if (type == CONTROLBUTTON.START)
        {
            ButtonKey startButton =
                    new ButtonKey("Start/Pause",
                            Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                            (Constants.worldHeight - 3*Constants.buttonHeight - 2*Constants.buttonSpacing)/2);
            startButton.setOnAction(e -> {
                if (numberOfBodies[0] < Constants.N)
                    return;

                if (isFirstClick)
                {
                    List<Body> bodies = bodyWrappers.stream().map(BodyWrapper::getBody).toList();
                    for (Body b : bodies) b.captureInitialFields();
                    timeloop.initializeAccelerations(bodies);
                    isFirstClick = false;
                }
                timeloop.pauseOrPlay();
            });

            return startButton;
        }
        else if(type == CONTROLBUTTON.RESTART)
        {
            ButtonKey restartButton =
                    new ButtonKey("Restart",
                            Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                            (Constants.worldHeight - Constants.buttonHeight)/2);
            restartButton.setOnAction(e -> {
                if (numberOfBodies[0] < Constants.N)
                    return;
                for(BodyWrapper bw: bodyWrappers)
                {
                    bw.getBody().resetFieldsToInitial();
                    bodyWrappers.forEach(BodyWrapper::updateArrow);
                }

                timeloop.pause();
                sb.getRunTimeDataTab().updateValues(bodyWrappers);
            });

            return restartButton;
        }
        else if(type == CONTROLBUTTON.RESET)
        {
            ButtonKey resetButton =
                    new ButtonKey("Reset",
                            Constants.worldWidth - Constants.buttonWidth - Constants.cellWidth,
                            (Constants.worldHeight + Constants.buttonHeight + 2*Constants.buttonSpacing)/2);
            resetButton.setOnAction(e -> {
                resetAll(timeloop, numberOfBodies, bodyWrappers, world, sb);
                isFirstClick = true;
            });

            return resetButton;
        }

        return null;
    }

    public static void resetAll
            (Timeloop timeloop, int[] numberOfBodies, List<BodyWrapper> bodyWrappers, Pane world, SideBar sb)
    {
        numberOfBodies[0] = 0;
        bodyWrappers.clear();
        timeloop.pause();
        world.getChildren().removeIf(node -> node instanceof BodyWrapper);
        world.getChildren().removeIf(node -> node instanceof Circle);

        sb.getRunTimeDataTab().resetAll();
        sb.resetAll(bodyWrappers, world);
    }

}

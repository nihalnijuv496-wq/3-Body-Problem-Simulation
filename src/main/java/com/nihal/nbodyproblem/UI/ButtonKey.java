package com.nihal.nbodyproblem.UI;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Timeloop.Timeloop;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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
            (String title, CONTROLBUTTON type, Timeloop timeloop, int[] numberOfBodies, List<Body> bodies, Pane world, SideBar sb)
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
                timeloop.pauseOrPlay();

                if (isFirstClick)
                {
                    for (Body b: bodies) b.captureInitialFields();
                    isFirstClick = false;
                }

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
                for(Body body: bodies)
                    body.resetFieldsToInitial();
                timeloop.pause();
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
                numberOfBodies[0] = 0;
                bodies.clear();
                timeloop.pause();
                world.getChildren().removeIf(node -> node instanceof Body);
                sb.resetAll();
                isFirstClick = true;


            });

            return resetButton;
        }

        return null;
    }

}

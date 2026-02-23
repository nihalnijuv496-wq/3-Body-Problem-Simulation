package com.nihal.nbodyproblem.Util;

import javafx.scene.control.Button;

public class ButtonKey extends Button {

    public ButtonKey(String title, int x, int y)
    {
        setText(title);
        setPrefSize(Constants.buttonWidth, Constants.buttonHeight);
        setStyle("-fx-background-color: #ffff00;" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 24px");
        setLayoutX(x);
        setLayoutY(y);
    }

}

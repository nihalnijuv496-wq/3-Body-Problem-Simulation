package com.nihal.nbodyproblem.Util;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class PopupData extends Stage {

    private double mass;
    private Vector velocity;
    private Vector center;
    private double radius;

    public PopupData(String title, double x, double y)
    {
        setTitle(title);

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));


        Label massLabel = new Label("Mass:");
        TextField massField = new TextField("1");
        massField.setPromptText("Enter mass");
        inputGrid.add(massLabel, 0, 0);
        inputGrid.add(massField, 1, 0);


        Label radiusLabel = new Label("Radius:");
        TextField radiusField = new TextField("10");
        radiusField.setPromptText("Enter radius");
        inputGrid.add(radiusLabel, 0, 1);
        inputGrid.add(radiusField, 1, 1);


        Label velocityXLabel = new Label("Velocity X:");
        TextField velocityXField = new TextField("0");
        velocityXField.setPromptText("Enter Velocity(x)");
        inputGrid.add(velocityXLabel, 0, 2);
        inputGrid.add(velocityXField, 1, 2);

        Label velocityYLabel = new Label("Velocity Y:");
        TextField velocityYField = new TextField("0");
        velocityYField.setPromptText("Enter Velocity(y)");
        inputGrid.add(velocityYLabel, 0, 3);
        inputGrid.add(velocityYField, 1, 3);


        Label centerXLabel = new Label("Center X:");
        TextField centerXField = new TextField(String.valueOf(x));
        centerXField.setPromptText("Refine CenterX");
        inputGrid.add(centerXLabel, 0, 4);
        inputGrid.add(centerXField, 1, 4);

        Label centerYLabel = new Label("Center Y:");
        TextField centerYField = new TextField(String.valueOf(y));
        centerYField.setPromptText("Refine CenterY");
        inputGrid.add(centerYLabel, 0, 5);
        inputGrid.add(centerYField, 1, 5);

        Button proceed = new Button("Create");
        inputGrid.add(proceed, 0, 6);


        Scene popupScene = new Scene(inputGrid, 300, 250);
        setScene(popupScene);

        proceed.setOnAction(event -> {
            try
            {
                mass = Double.parseDouble(massField.getText());
            }
            catch (NumberFormatException e)
            {
                massField.setText("Enter a valid value");
                return;
            }
            try
            {
                radius = Double.parseDouble(radiusField.getText());
            }
            catch (NumberFormatException e)
            {
                radiusField.setText("Enter a valid value");
                return;
            }
            try
            {
                double velX = Double.parseDouble(velocityXField.getText());
                double velY = Double.parseDouble(velocityYField.getText());
                velocity = new Vector(velX, velY);
            }
            catch (NumberFormatException e)
            {
                velocityXField.setText("Enter a valid value");
                velocityYField.setText("Enter a valid value");
                return;
            }

            try
            {
                double centerX = Double.parseDouble(centerXField.getText());
                double centerY = Double.parseDouble(centerYField.getText());
                center = new Vector(centerX, centerY);
            }
            catch (NumberFormatException e)
            {
                centerXField.setText("Enter a valid value");
                centerYField.setText("Enter a valid value");
                return;
            }

            close();
        });

        showAndWait();
    }

    public Vector getCenter() {
        return center;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

}

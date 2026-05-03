package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class DataInputBox extends VBox {

    Slider speedSlider = new Slider(Constants.minV, Constants.maxV, 0);
    Slider angleSlider = new Slider(0, 360, 0);
    Slider vxSlider = new Slider(Constants.minVx, Constants.maxVx, 0);
    Slider vySlider = new Slider(Constants.minVy, Constants.maxVy, 0);
    Slider massSlider = new Slider(Constants.minMass, Constants.maxMass, 1);
    Slider centerXSlider = new Slider(0, Constants.worldWidth, 100);
    Slider centerYSlider = new Slider(0, Constants.worldHeight, 100);
    Slider radiusSlider = new Slider(Constants.minRadius, Constants.maxRadius, 10);


    boolean[] updating = {false};

    ChangeListener<Number> vaListener = (obs, old, newVal) -> {
        if (updating[0]) return;
        updating[0] = true;
        double speed = speedSlider.getValue();
        double angle = Math.toRadians(angleSlider.getValue());
        vxSlider.setValue(speed * Math.cos(angle));
        vySlider.setValue(speed * Math.sin(angle));
        updating[0] = false;
    };

    ChangeListener<Number> vcListener = (obs, old, newVal) -> {
        if (updating[0]) return;
        updating[0] = true;
        double vx = vxSlider.getValue();
        double vy = vySlider.getValue();
        speedSlider.setValue(Math.sqrt(vx*vx + vy*vy));
        angleSlider.setValue(Math.toDegrees(Math.atan2(vy, vx)));
        updating[0] = false;
    };


    public DataInputBox()
    {

        String headerStyle = "-fx-font-size: 20px; -fx-font-weight: bold;";
        Label VATitle = new Label("Velocity-Angle");
        VATitle.setStyle(headerStyle);

        Label VCTitle = new Label("Velocity Component");
        VCTitle.setStyle(headerStyle);

        Label centerTitle = new Label("Center");
        centerTitle.setStyle(headerStyle);

        Label massTitle = new Label("Mass");
        massTitle.setStyle(headerStyle);


        speedSlider.valueProperty().addListener(vaListener);
        angleSlider.valueProperty().addListener(vaListener);
        vxSlider.valueProperty().addListener(vcListener);
        vySlider.valueProperty().addListener(vcListener);



        getChildren().addAll(
                VATitle,
                new Label("Speed"),
                speedSlider,
                new Label("Angle"),
                angleSlider,
                VCTitle,
                new Label("SpeedX"),
                vxSlider,
                new Label("SpeedY"),
                vySlider,
                massTitle,
                massSlider,
                centerTitle,
                centerXSlider,
                centerYSlider
        );

    }

    public double getMass() { return massSlider.getValue(); }
    public double getSpeed() { return speedSlider.getValue(); }
    public double getAngle() { return angleSlider.getValue(); }
    public Vector getVelocity() { return new Vector(vxSlider.getValue(), vySlider.getValue()); }
    public Vector getCenter() { return new Vector(centerXSlider.getValue(), centerYSlider.getValue()); }
    public double getRadius() { return radiusSlider.getValue(); }
}

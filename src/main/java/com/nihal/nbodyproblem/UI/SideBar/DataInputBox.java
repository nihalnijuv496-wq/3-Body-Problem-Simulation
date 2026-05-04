package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.util.List;

public class DataInputBox extends VBox {

    static int totalNum = 0;

    int num;
    Slider speedSlider = new Slider(Constants.minV, Constants.maxV, 0);
    Slider angleSlider = new Slider(0, 360, 0);
    Slider vxSlider = new Slider(Constants.minVx, Constants.maxVx, 0);
    Slider vySlider = new Slider(Constants.minVy, Constants.maxVy, 0);
    Slider massSlider = new Slider(Constants.minMass, Constants.maxMass, 1);
    Slider centerXSlider = new Slider(0, Constants.worldWidth, 100);
    Slider centerYSlider = new Slider(0, Constants.worldHeight, 100);
    Slider radiusSlider = new Slider(Constants.minRadius, Constants.maxRadius, 10);


    boolean[] updating = {false};
    public DataInputBox(List<Body> bodies)
    {
        num = totalNum++;

        String headerStyle = "-fx-font-size: 20px; -fx-font-weight: bold;";
        Label VATitle = new Label("Velocity-Angle");
        VATitle.setStyle(headerStyle);

        Label VCTitle = new Label("Velocity Component");
        VCTitle.setStyle(headerStyle);

        Label centerTitle = new Label("Center");
        centerTitle.setStyle(headerStyle);

        Label massTitle = new Label("Mass");
        massTitle.setStyle(headerStyle);

        Label radiusTitle = new Label("Radius");
        radiusTitle.setStyle(headerStyle);


        ChangeListener<Number> vaListener = (obs, old, newVal) -> {
            if (updating[0]) return;
            updating[0] = true;
            double speed = speedSlider.getValue();
            double angle = Math.toRadians(angleSlider.getValue());
            vxSlider.setValue(speed * Math.cos(angle));
            vySlider.setValue(speed * Math.sin(angle));
            bodies.get(num).setVelocity(getVelocity());
            updating[0] = false;

            System.out.println("va listener "+bodies.get(num).getVelocity().getX() + " " +bodies.get(num).getVelocity().getX());


        };

        ChangeListener<Number> vcListener = (obs, old, newVal) -> {
            if (updating[0]) return;
            updating[0] = true;
            double vx = vxSlider.getValue();
            double vy = vySlider.getValue();
            speedSlider.setValue(Math.sqrt(vx*vx + vy*vy));
            angleSlider.setValue(Math.toDegrees(Math.atan2(vy, vx)));
            bodies.get(num).setVelocity(getVelocity()); // ← add this
            updating[0] = false;
        };


        speedSlider.valueProperty().addListener(vaListener);
        angleSlider.valueProperty().addListener(vaListener);
        vxSlider.valueProperty().addListener(vcListener);
        vySlider.valueProperty().addListener(vcListener);

        massSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bodies.get(num).setMass(this.getMass());
        });
        centerXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bodies.get(num).setCenter(this.getCenter());
        });
        centerYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bodies.get(num).setCenter(this.getCenter());
        });
        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bodies.get(num).setRad(this.getRadius());
        });


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
                centerYSlider,
                radiusTitle,
                radiusSlider
        );

    }




    public double getMass() { return massSlider.getValue(); }
    public double getSpeed() { return speedSlider.getValue(); }
    public double getAngle() { return angleSlider.getValue(); }
    public Vector getVelocity() { return new Vector(vxSlider.getValue(), vySlider.getValue()); }
    public Vector getCenter() { return new Vector(centerXSlider.getValue(), centerYSlider.getValue()); }
    public double getRadius() { return radiusSlider.getValue(); }
}

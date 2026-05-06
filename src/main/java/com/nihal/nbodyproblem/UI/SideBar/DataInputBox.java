package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
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
    Slider massSlider = new Slider(Constants.minMass, Constants.maxMass, 150);
    Slider centerXSlider = new Slider(0, Constants.worldWidth, 100);
    Slider centerYSlider = new Slider(0, Constants.worldHeight, 100);
    Slider radiusSlider = new Slider(Constants.minRadius, Constants.maxRadius, 15);


    boolean[] updating = {false};
    public DataInputBox(List<BodyWrapper> bodyWrappers)
    {

        num = totalNum++;


        Label VAHeader = new Label("Velocity-Angle");
        Label VCHeader = new Label("Velocity Component");
        Label centerHeader = new Label("Center");
        Label massHeader = new Label("Mass");
        Label radiusHeader = new Label("Radius");

        Label speedSubHeader = new Label("Speed");
        Label angleSubHeader = new Label("Angle");
        Label speedXSubHeader = new Label("SpeedX");
        Label speedYSubHeader = new Label("SpeedY");
        Label centerXSubHeader = new Label("CenterX");
        Label centerYSubHeader = new Label("centerY");

        Label speedValue = new Label("Speed: " + String.valueOf(speedSlider.getValue()));
        Label angleValue = new Label("Angle: " + String.valueOf(angleSlider.getValue()));
        Label vxValue = new Label("SpeedX: " + String.valueOf(vxSlider.getValue()));
        Label vyValue = new Label("SpeedY: " + String.valueOf(vySlider.getValue()));
        Label centerXValue = new Label("CenterX: " + String.valueOf(centerXSlider.getValue()));
        Label centerYValue = new Label("CenterY: " + String.valueOf(centerYSlider.getValue()));
        Label radiusValue = new Label("Radius: " + String.valueOf(radiusSlider.getValue()));
        Label massValue = new Label("Mass: " + String.valueOf(massSlider.getValue()));

        VAHeader.getStyleClass().add("header-label");
        VCHeader.getStyleClass().add("header-label");
        centerHeader.getStyleClass().add("header-label");
        massHeader.getStyleClass().add("header-label");
        radiusHeader.getStyleClass().add("header-label");

        speedSubHeader.getStyleClass().add("sub-header-label");
        angleSubHeader.getStyleClass().add("sub-header-label");
        speedXSubHeader.getStyleClass().add("sub-header-label");
        speedYSubHeader.getStyleClass().add("sub-header-label");
        centerXSubHeader.getStyleClass().add("sub-header-label");
        centerYSubHeader.getStyleClass().add("sub-header-label");

        speedValue.getStyleClass().add("sub-header-label");
        angleValue.getStyleClass().add("sub-header-label");
        vxValue.getStyleClass().add("sub-header-label");
        vyValue.getStyleClass().add("sub-header-label");
        centerXValue.getStyleClass().add("sub-header-label");
        centerYValue.getStyleClass().add("sub-header-label");
        radiusValue.getStyleClass().add("sub-header-label");
        massValue.getStyleClass().add("sub-header-label");

        ChangeListener<Number> vaListener = (obs, old, newVal) -> {
            if (updating[0]) return;
            updating[0] = true;
            double speed = speedSlider.getValue();
            double angle = Math.toRadians(angleSlider.getValue());
            vxSlider.setValue(speed * Math.cos(angle));
            vySlider.setValue(-1*speed * Math.sin(angle));
            bodyWrappers.get(num).getBody().setVelocity(getVelocity());
            updating[0] = false;

            speedValue.setText("Speed: " + speed);
            angleValue.setText("Angle: " + Math.toDegrees(angle));
            vxValue.setText("SpeedX: " + String.valueOf(vxSlider.getValue()));
            vyValue.setText("SpeedY: " + String.valueOf(vySlider.getValue()));

            bodyWrappers.get(num).updateArrow();
        };

        ChangeListener<Number> vcListener = (obs, old, newVal) -> {
            if (updating[0]) return;
            updating[0] = true;
            double vx = vxSlider.getValue();
            double vy = vySlider.getValue();

            double angleDeg = Math.toDegrees(Math.atan2(-1*vy, vx));
            if (angleDeg < 0) angleDeg += 360;
            angleSlider.setValue(angleDeg);
            speedSlider.setValue(Math.sqrt(vx*vx + vy*vy));

            bodyWrappers.get(num).getBody().setVelocity(getVelocity());
            updating[0] = false;

            speedValue.setText("Speed: " + speedSlider.getValue());
            angleValue.setText("Angle: " + angleSlider.getValue());
            vxValue.setText("SpeedX: " + vx);
            vyValue.setText("SpeedY: " + vy);

            bodyWrappers.get(num).updateArrow();
        };


        speedSlider.valueProperty().addListener(vaListener);
        angleSlider.valueProperty().addListener(vaListener);
        vxSlider.valueProperty().addListener(vcListener);
        vySlider.valueProperty().addListener(vcListener);

        massSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double m = this.getMass();
            bodyWrappers.get(num).getBody().setMass(m);
            massValue.setText("Mass: " + String.valueOf(m));
            bodyWrappers.get(num).updateArrow();
        });
        centerXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Vector C = this.getCenter();
            bodyWrappers.get(num).getBody().setCenter(C);
            centerXValue.setText("CenterX: " + String.valueOf(C.getX()));
            bodyWrappers.get(num).updateArrow();
        });
        centerYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Vector C = this.getCenter();
            bodyWrappers.get(num).getBody().setCenter(C);
            centerYValue.setText("CenterY: " + String.valueOf(C.getY()));
            bodyWrappers.get(num).updateArrow();
        });
        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double r = this.getRadius();
            bodyWrappers.get(num).getBody().setRad(r);
            radiusValue.setText("Radius: " + String.valueOf(r));
            bodyWrappers.get(num).updateArrow();
        });


        getChildren().addAll(
                VAHeader,
                speedSubHeader,
                speedSlider,
                speedValue,
                angleSubHeader,
                angleSlider,
                angleValue,
                VCHeader,
                speedXSubHeader,
                vxSlider,
                vxValue,
                speedYSubHeader,
                vySlider,
                vyValue,
                massHeader,
                massSlider,
                massValue,
                centerHeader,
                centerXSubHeader,
                centerXSlider,
                centerXValue,
                centerYSubHeader,
                centerYSlider,
                centerYValue,
                radiusHeader,
                radiusSlider,
                radiusValue
        );

    }




    public double getMass() { return massSlider.getValue(); }
    public double getSpeed() { return speedSlider.getValue(); }
    public double getAngle() { return angleSlider.getValue(); }
    public Vector getVelocity() { return new Vector(vxSlider.getValue(), vySlider.getValue()); }
    public Vector getCenter() { return new Vector(centerXSlider.getValue(), centerYSlider.getValue()); }
    public double getRadius() { return radiusSlider.getValue(); }
}

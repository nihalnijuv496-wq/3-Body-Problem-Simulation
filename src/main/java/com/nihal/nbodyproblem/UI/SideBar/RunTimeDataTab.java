package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RunTimeDataTab extends VBox {

    List<DataDisplayBox> dataDisplayBoxes = new ArrayList<>();
    Label totalSystemEnergyHeader;
    Label totalSystemEnergyValue;
    List<Body> bodies = new ArrayList<>();

    public RunTimeDataTab()
    {
        totalSystemEnergyValue = new Label("0");
        totalSystemEnergyHeader = new Label("Total System Energy: ");
        totalSystemEnergyValue.getStyleClass().add("header-label");
        totalSystemEnergyHeader.getStyleClass().add("header-label");
        getChildren().addAll(totalSystemEnergyHeader, totalSystemEnergyValue);
    }


    private class DataDisplayBox extends VBox{
        Label velocityValue;
        Label positionValue;
        Label KEValue;
        Label PEValue;
        Label TEValue;

        DataDisplayBox(Body body, List<BodyWrapper> bodyWrappers)
        {
            Label BodyHeader = new Label("Body " + bodyWrappers.size());
            Label velocityHeader = new Label("Velocity: ");
            Label positionHeader = new Label("Position: ");
            Label KEHeader = new Label("Kinetic Energy: ");
            Label PEHeader = new Label("Potential Energy: ");
            Label TEHeader = new Label("Total Energy: ");



            bodies.add(body);
            double kineticEnergy = body.getKineticEnergy();
            double potentialEnergy = body.getPotentialEnergy(bodies);/*hjbdwdcbkwjuwujwbiwbik*/
            double totalEnergy = kineticEnergy + potentialEnergy;
            velocityValue = new Label(
                    "(" + body.getVelocity().magn() + ", " + Math.toDegrees(body.getVelocity().getAngle()) + ")"
                            + " = ( " + body.getVelocity().getX() + ", " + body.getVelocity().getY() + ")");
            positionValue = new Label("(" + body.getCenter().getX() + body.getCenter().getY() + ")");
            KEValue = new Label(String.valueOf(kineticEnergy));
            PEValue = new Label(String.valueOf(potentialEnergy));
            TEValue = new Label(String.valueOf(totalEnergy));

            velocityHeader.getStyleClass().add("sub-header-label");
            positionHeader.getStyleClass().add("sub-header-label");
            KEHeader.getStyleClass().add("sub-header-label");
            PEHeader.getStyleClass().add("sub-header-label");
            TEHeader.getStyleClass().add("sub-header-label");
            BodyHeader.getStyleClass().add("header-label");

            velocityValue.getStyleClass().add("sub-header-label");
            positionValue.getStyleClass().add("sub-header-label");
            KEValue.getStyleClass().add("sub-header-label");
            PEValue.getStyleClass().add("sub-header-label");
            TEValue.getStyleClass().add("sub-header-label");



            getChildren().addAll(
                    BodyHeader,
              velocityHeader, velocityValue,
              positionHeader, positionValue,
              KEHeader, KEValue,
              PEHeader, PEValue,
              TEHeader, TEValue
            );
        }
    }

    public void addRunTimeData(Body body, List<BodyWrapper> bodyWrappers)
    {

        dataDisplayBoxes.add(new DataDisplayBox(body, bodyWrappers));
        getChildren().add(dataDisplayBoxes.getLast());
        totalSystemEnergyHeader.toFront();
        totalSystemEnergyValue.toFront();
        updateValues();
    }

    public void updateValues()
    {

        double totalSystemKE = 0;
        double totalSystemPE = 0;

        for (int i = 0; i < bodies.size(); ++i)
        {
            Body body = bodies.get(i);
            DataDisplayBox db = dataDisplayBoxes.get(i);
            db.velocityValue.setText(
                    "(" + body.getVelocity().magn() + ", " + Math.toDegrees(body.getVelocity().getAngle()) + ")"
                            + " = ( " + body.getVelocity().getX() + ", " + body.getVelocity().getY() + ")");
            db.positionValue.setText("(" + body.getCenter().getX() + body.getCenter().getY() + ")");

            double kineticEnergy = body.getKineticEnergy();
            double potentialEnergy = body.getPotentialEnergy(bodies);
            double totalEnergy = kineticEnergy + potentialEnergy;
            totalSystemKE += kineticEnergy;
            totalSystemPE += potentialEnergy;
            db.KEValue.setText(String.valueOf(kineticEnergy));
            db.PEValue.setText(String.valueOf(potentialEnergy));
            db.TEValue.setText(String.valueOf(totalEnergy));
        }
        totalSystemPE /= 2;
        totalSystemEnergyValue.setText(String.valueOf(totalSystemKE + totalSystemPE));


    }

    public void resetAll()
    {
        dataDisplayBoxes.clear();
        getChildren().clear();
        bodies.clear();
        totalSystemEnergyValue = new Label("0");
        totalSystemEnergyHeader = new Label("Total System Energy: ");
        totalSystemEnergyValue.getStyleClass().add("header-label");
        totalSystemEnergyHeader.getStyleClass().add("header-label");
        getChildren().addAll(totalSystemEnergyHeader, totalSystemEnergyValue);
    }
}

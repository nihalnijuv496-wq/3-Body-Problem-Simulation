package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RunTimeDataTab extends VBox {

    List<DataDisplayBox> dataDisplayBoxes = new ArrayList<>();

    private class DataDisplayBox extends VBox{
        Label velocityValue;
        Label positionValue;
        Label KEValue;
        Label PEValue;
        Label TEValue;

        DataDisplayBox(Body body, List<BodyWrapper> bodyWrappers)
        {
            Label velocityHeader = new Label("Velocity: ");
            Label positionHeader = new Label("Position: ");
            Label KEHeader = new Label("Kinetic Energy: ");
            Label PEHeader = new Label("Potential Energy: ");
            Label TEHeader = new Label("Total Energy: ");


            List<Body> bodies = bodyWrappers.stream().map(BodyWrapper::getBody).toList();
            double kineticEnergy = body.getKineticEnergy();
            double potentialEnergy = body.getPotentialEnergy(bodies);
            double totalEnergy = kineticEnergy + potentialEnergy;
            velocityValue = new Label(
                    "(" + String.valueOf(body.getVelocity().magn()) + ", " + String.valueOf(Math.toDegrees(body.getVelocity().getAngle()) + ")")
                        + " = ( " +  String.valueOf(body.getVelocity().getX()) + ", " + String.valueOf( body.getVelocity().getY()) + ")");
            positionValue = new Label("(" + String.valueOf(body.getCenter().getX()) + String.valueOf(body.getCenter().getY()) + ")");
            KEValue = new Label(String.valueOf(kineticEnergy));
            PEValue = new Label(String.valueOf(potentialEnergy));
            TEValue = new Label(String.valueOf(totalEnergy));

            velocityHeader.getStyleClass().add("sub-header-label");
            positionHeader.getStyleClass().add("sub-header-label");
            KEHeader.getStyleClass().add("sub-header-label");
            PEHeader.getStyleClass().add("sub-header-label");
            TEHeader.getStyleClass().add("sub-header-label");

            velocityValue.getStyleClass().add("sub-header-label");
            positionValue.getStyleClass().add("sub-header-label");
            KEValue.getStyleClass().add("sub-header-label");
            PEValue.getStyleClass().add("sub-header-label");
            TEValue.getStyleClass().add("sub-header-label");


            getChildren().addAll(
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
        updateValues(bodyWrappers);
    }

    public void updateValues(List<BodyWrapper> bodyWrappers)
    {
        List<Body> bodies = bodyWrappers.stream().map(BodyWrapper::getBody).toList();
        for (int i = 0; i < bodyWrappers.size(); ++i)
        {
            Body body = bodies.get(i);
            DataDisplayBox db = dataDisplayBoxes.get(i);
            db.velocityValue.setText(
                    "(" + String.valueOf(body.getVelocity().magn()) + ", " + String.valueOf(Math.toDegrees(body.getVelocity().getAngle()) + ")")
                            + " = ( " +  String.valueOf(body.getVelocity().getX()) + ", " + String.valueOf( body.getVelocity().getY()) + ")");
            db.positionValue.setText("(" + String.valueOf(body.getCenter().getX()) + String.valueOf(body.getCenter().getY()) + ")");

            double kineticEnergy = body.getKineticEnergy();
            double potentialEnergy = body.getPotentialEnergy(bodies);
            double totalEnergy = kineticEnergy + potentialEnergy;

            db.KEValue.setText(String.valueOf(kineticEnergy));
            db.PEValue.setText(String.valueOf(potentialEnergy));
            db.TEValue.setText(String.valueOf(totalEnergy));
        }
    }

    public void resetAll()
    {
        dataDisplayBoxes.clear();
        getChildren().clear();
    }
}

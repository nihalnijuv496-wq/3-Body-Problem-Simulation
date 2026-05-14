package com.nihal.nbodyproblem.Presets;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.UI.SideBar.DataInputBox;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.PresetUtils;
import com.nihal.nbodyproblem.Util.Vector;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


import java.util.ArrayList;
import java.util.List;

import static com.nihal.nbodyproblem.Util.Constants.defaultCMassX;
import static com.nihal.nbodyproblem.Util.Constants.defaultCMassY;

public class Lagrange {



    public static void equilateralTriangleSolution(List<BodyWrapper> bodyWrappers, Pane world, SideBar sideBar)
    {
        double triangleSide = 100;

        Vector[] bodyPositions = {
                new Vector(defaultCMassX, defaultCMassY - triangleSide/Math.sqrt(3)),
                new Vector(defaultCMassX +triangleSide/2, defaultCMassY +triangleSide/(2*Math.sqrt(3))),
                new Vector(defaultCMassX -triangleSide/2, defaultCMassY +triangleSide/(2*Math.sqrt(3)))};


        for (int i = 0; i < Constants.N; ++i)
        {
            Point2D scenePoint = world.localToScene(bodyPositions[i].getX(), bodyPositions[i].getY());
            MouseEvent event = new MouseEvent(
                    MouseEvent.MOUSE_CLICKED,                                   // event type
                    bodyPositions[i].getX(), bodyPositions[i].getY(),           // nodeX, nodeY
                    scenePoint.getX(), scenePoint.getY(),                       // screenX, screenY
                    MouseButton.PRIMARY,                                        // button
                    1,                                                          // click count
                    false, false, false, false,                     // shift, ctrl, alt, meta
                    true,                                                       // primary button down
                    false, false,                                           // middle, secondary down
                    false,                                                      // synthesized
                    false,                                                      // popup trigger
                    true,                                                       // still since press
                    null                                                        // pick result
            );

            world.fireEvent(event);
        }

        configureSlidersForEquilateralTriangle(sideBar, bodyWrappers);
    }


    public static void configureSlidersForEquilateralTriangle(SideBar sideBar, List<BodyWrapper> bodyWrappers)
    {

        List<Body> bodies = bodyWrappers.stream().map(BodyWrapper::getBody).toList();
        List<Slider> lengthSliders = new ArrayList<>();

        for (int i = 0; i < Constants.N; ++i) {
            DataInputBox db = sideBar.getDataInputBoxes().get(i);
            Vector pos = PresetUtils.getCenterForEquilateralTriangleSolution(100).get(i);
            db.getCenterXSlider().setValue(pos.getX());
            db.getCenterYSlider().setValue(pos.getY());
        }

        for(int i = 0; i < Constants.N; ++i)
        {
            DataInputBox db = sideBar.getDataInputBoxes().get(i);
            Slider lengthSlider = PresetUtils.getSideLengthSlider(sideBar, bodies, lengthSliders);
            lengthSliders.add(lengthSlider);
            Label lengthHeader = new Label("Triangle Side");
            lengthHeader.getStyleClass().add("header-label");
            db.getChildren().addAll(lengthHeader, lengthSlider);

            db.disableAllSliders();
            db.getRadiusSlider().setDisable(false);
            db.getMassSlider().setDisable(false);


            for(int j = 0; j < Constants.N; ++j)
            {
                DataInputBox currDB = sideBar.getDataInputBoxes().get(j);
                Vector velocity = PresetUtils.getVelocityForEquilateralTriangleSolution(lengthSlider.getValue(), bodies, j);
                currDB.getVxSlider().setValue(velocity.getX());
                currDB.getVySlider().setValue(velocity.getY());
            }

            db.getMassSlider().valueProperty().addListener((observable, oldValue, newValue) ->
            {
                double newMass = db.getMassSlider().getValue();
                for(int j = 0; j < Constants.N; ++j)
                {
                    DataInputBox currDB = sideBar.getDataInputBoxes().get(j);
                    currDB.getMassSlider().setValue(newMass);
                    Vector velocity = PresetUtils.getVelocityForEquilateralTriangleSolution(lengthSlider.getValue(), bodies, j);
                    currDB.getVxSlider().setValue(velocity.getX());
                    currDB.getVySlider().setValue(velocity.getY());
                }

            });

        }



    }

}

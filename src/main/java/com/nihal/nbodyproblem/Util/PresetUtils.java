package com.nihal.nbodyproblem.Util;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Presets.Lagrange;
import com.nihal.nbodyproblem.Presets.Presets;
import com.nihal.nbodyproblem.UI.SideBar.DataInputBox;
import com.nihal.nbodyproblem.UI.SideBar.SideBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.util.List;

import static com.nihal.nbodyproblem.Presets.Presets.*;
import static com.nihal.nbodyproblem.Util.Constants.defaultCMassX;
import static com.nihal.nbodyproblem.Util.Constants.defaultCMassY;

public class PresetUtils {



    public static void loadPreset(Presets presetType, List<BodyWrapper> bodyWrappers, Pane world, SideBar sideBar)
    {
        if(presetType == LagrangeEquilateralTriangle)
            Lagrange.equilateralTriangleSolution(bodyWrappers, world, sideBar);
    }

    public static List<Vector> getCenterForEquilateralTriangleSolution(double triangleSide)
    {
        return List.of(
                new Vector(defaultCMassX, defaultCMassY - triangleSide/Math.sqrt(3)),
                new Vector(defaultCMassX +triangleSide/2, defaultCMassY +triangleSide/(2*Math.sqrt(3))),
                new Vector(defaultCMassX -triangleSide/2, defaultCMassY +triangleSide/(2*Math.sqrt(3))));

    }

    public static Vector getVelocityForEquilateralTriangleSolution(double triangleSide, List<Body> bodies, int bodyI)
    {
        /*Body bodyJ = bodies.get((bodyI+1)%Constants.N);
        Body bodyK = bodies.get((bodyI+2)%Constants.N);

        double massJ = bodyJ.getMass();
        double massK = bodyK.getMass();

        double velocityMagn = Math.sqrt(Constants.G * (massJ + massK) / (triangleSide * Math.sqrt(3)));
        double velocityMagn = Math.sqrt((Constants.G/(triangleSide*Math.sqrt(3))) * Math.sqrt(massJ*massJ + massK*massK + massJ*massK));*/

        double velocityMagn = Math.sqrt(
                Constants.G * bodies.get(bodyI).getMass() * triangleSide * triangleSide
                        / Math.pow(triangleSide*triangleSide + Constants.epsilon*Constants.epsilon, 1.5)
        );
        if (bodyI == 0) return new Vector(velocityMagn, 0);
        if (bodyI == 1) return new Vector(-velocityMagn*Math.cos(Math.toRadians(60)), velocityMagn*Math.sin(Math.toRadians(60)));
        if (bodyI == 2) return new Vector(-velocityMagn*Math.cos(Math.toRadians(60)), -velocityMagn*Math.sin(Math.toRadians(60)));
        return new Vector(0,0);
    }

    public static Slider getSideLengthSlider(SideBar sideBar, List<Body> bodies, List<Slider> lengthSliders)
    {
        Slider lengthSlider = new Slider(1, 500, 100);
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            double currLength = lengthSlider.getValue();
            List<Vector> bodyPos = getCenterForEquilateralTriangleSolution(currLength);
            List<DataInputBox> dataInputBoxes = sideBar.getDataInputBoxes();
            for (int i = 0; i < dataInputBoxes.size(); ++i)
            {
                DataInputBox db = sideBar.getDataInputBoxes().get(i);
                db.getCenterXSlider().setValue(bodyPos.get(i).getX());
                db.getCenterYSlider().setValue(bodyPos.get(i).getY());

                Vector velocity = getVelocityForEquilateralTriangleSolution(currLength, bodies, i);
                db.getVxSlider().setValue(velocity.getX());
                db.getVySlider().setValue(velocity.getY());
            }
            for (Slider s: lengthSliders) s.setValue(currLength);
        });
        return lengthSlider;
    }

}

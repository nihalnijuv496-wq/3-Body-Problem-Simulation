package com.nihal.nbodyproblem.Util;

import com.nihal.nbodyproblem.Body.Body;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;

public class FadeProperty {
    private static final int fadeTimeInMillis = 5000;

    private static Circle copy(Body body)
    {
        Circle bodyCopy = new Circle();
        bodyCopy.setCenterX(body.getCenterX());
        bodyCopy.setCenterY(body.getCenterY());
        bodyCopy.setRadius(2.0);
        Color bodyColor = (Color) body.getFill();

        Color newColor = new Color(
                Math.min(1, bodyColor.getRed() + 0.35),
                Math.min(1, bodyColor.getGreen() + 0.35),
                Math.min(1, bodyColor.getBlue() + 0.35), 1.0);
        bodyCopy.setFill(newColor);
        return bodyCopy;
    }

    public static void addFadeToBodies(List<Body> bodies, Pane world)
    {
        for (Body body: bodies)
        {
            Circle bodyCopy = copy(body);
            FadeTransition fade = new FadeTransition(Duration.millis(fadeTimeInMillis), bodyCopy);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);

            fade.setOnFinished(e ->
            {
                world.getChildren().remove(bodyCopy);
            });

            world.getChildren().add(bodyCopy);
            fade.play();
        }
    }
}

package com.nihal.nbodyproblem.Body;

import com.nihal.nbodyproblem.UI.ArrowIcon.Arrow;
import javafx.scene.Group;

public class BodyWrapper extends Group {
    private Body body;
    private Arrow arrow;

    public BodyWrapper(Body b)
    {
        this.body = b;
        this.arrow =  new Arrow(body.getVelocity().magn(), body.getVelocity().getAngle(), body.getCenter());

        getChildren().addAll(this.body, this.arrow);
    }


    public void updateArrow()
    {
        arrow.setValues(body.getVelocity(), body.getCenter());

    }

    public Body getBody(){ return this.body; }
}


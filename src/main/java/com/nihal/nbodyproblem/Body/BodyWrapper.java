package com.nihal.nbodyproblem.Body;

import com.nihal.nbodyproblem.UI.ArrowIcon.Arrow;
import javafx.scene.Group;

public class BodyWrapper extends Group {
    private Body body;
    private Arrow arrow;

    public BodyWrapper(Body b)
    {
        this.body = b;
        double l = body.getVelocity().magn();
        this.arrow =  new Arrow(body.getVelocity().magn(), body.getVelocity().getAngle(), body.getCenter());

        if (l == 0) this.arrow.setVisible(false);

        getChildren().addAll(this.body, this.arrow);
    }


    public void updateArrow()
    {
        arrow.setValues(body.getVelocity(), body.getCenter());
        double l = body.getVelocity().magn();
        this.arrow.setVisible(l != 0);
    }

    public Body getBody(){ return this.body; }
}


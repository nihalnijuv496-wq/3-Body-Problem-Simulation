package com.nihal.nbodyproblem.Engine;
import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Vector;

import java.util.List;

public class PhysicsEngine {

    public void update(List<Body> bodies)
    {
        for (Body body: bodies)
        {
            body.setAcceleration(new Vector(0,0));
            for (Body bodyIter: bodies)
            {
                if(body.equals(bodyIter)) continue;

                Vector displacement = bodyIter.getCenter().sub(body.getCenter());
                double distance = displacement.magn();
                double scalingFactorAcc = Constants.G * bodyIter.getMass() / Math.pow(distance, 3);

                Vector acc = displacement.scale(scalingFactorAcc);
                body.setAcceleration(body.getAcceleration().add(acc));
            }
        }
        for (Body body: bodies)
        {
            //x dir
            double newX = body.getCenterX() +
                    body.getVelocity().getX()*(Constants.timeStep) +
                    0.5*body.getAcceleration().getX()*(Constants.timeStep)*(Constants.timeStep);
            body.getVelocity().setX(body.getVelocity().getX() + body.getAcceleration().getX() * (Constants.timeStep));
            //y dir
            double newY = body.getCenterY() +
                    body.getVelocity().getY()*(Constants.timeStep) +
                    0.5*body.getAcceleration().getY()*(Constants.timeStep)*(Constants.timeStep);
            body.getVelocity().setY(body.getVelocity().getY() + body.getAcceleration().getY() * (Constants.timeStep));

            body.setCenter(newX, newY);
        }
    }
}

package com.nihal.nbodyproblem.Engine;
import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Vector;

public class PhysicsEngine {

    public void update(Body[] bodies)
    {
        for (Body body: bodies)
        {
            for (Body bodyIter: bodies)
            {
                if(body.equals(bodyIter)) continue;

                Vector displacement = bodyIter.getCenter().sub(body.getCenter());
                double distance = displacement.magn();
                double scalingFactorAcc = Constants.G * bodyIter.getMass() / Math.pow(distance, 3);

                Vector acc = displacement.scale(scalingFactorAcc);
                body.setAcceleration(body.getAcceleration().add(acc));
            }

            int nextPosX;
            int nextPosY;

            //x dir
            body.setCenterX(body.getCenterX() +
                    body.getVelocity().getX()*((double) Constants.fps /10) +
                    0.5*body.getAcceleration().getX()*((double) Constants.fps /10)*((double) Constants.fps /10));
            body.getVelocity().setX(body.getVelocity().getX() + body.getAcceleration().getX() * ((double) Constants.fps /100));
            //y dir
            body.setCenterY(body.getCenterY() +
                    body.getVelocity().getY()*((double) Constants.fps /10) +
                    0.5*body.getAcceleration().getY()*((double) Constants.fps /10)*((double) Constants.fps /10));
            body.getVelocity().setY(body.getVelocity().getY() + body.getAcceleration().getY() * ((double) Constants.fps /100));
        }
    }
}

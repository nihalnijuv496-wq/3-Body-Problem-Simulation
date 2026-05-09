package com.nihal.nbodyproblem.Engine;
import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.Vector;

import java.util.List;

public class PhysicsEngine {

    public void updateWithVerlet(List<Body> bodies)
    {
        Vector[] oldAccs = new Vector[Constants.N];
        int i = 0;
        for (Body body: bodies)
        {
            oldAccs[i++] = body.getAcceleration();
            body.setAcceleration(new Vector(0,0));
            for (Body bodyIter: bodies)
            {
                if(body.equals(bodyIter)) continue;

                Vector displacement = bodyIter.getCenter().sub(body.getCenter());
                double distance = displacement.magn();
                double softenedDistance = Math.sqrt(distance*distance + Constants.epsilon*Constants.epsilon);
                double scalingFactorAcc = Constants.G * bodyIter.getMass() / Math.pow(softenedDistance, 3);

                Vector acc = displacement.scale(scalingFactorAcc);
                body.setAcceleration(body.getAcceleration().add(acc));
            }
        }
        i = 0;
        for (Body body: bodies)
        {
            //x dir
            double newX = body.getCenterX() +
                    body.getVelocity().getX()*(Constants.timeStep) +
                    0.5*body.getAcceleration().getX()*(Constants.timeStep)*(Constants.timeStep);
            body.getVelocity().setX(body.getVelocity().getX() + 0.5*(body.getAcceleration().getX() + oldAccs[i].getX()) * (Constants.timeStep));
            //y dir
            double newY = body.getCenterY() +
                    body.getVelocity().getY()*(Constants.timeStep) +
                    0.5*body.getAcceleration().getY()*(Constants.timeStep)*(Constants.timeStep);
            body.getVelocity().setY(body.getVelocity().getY() + 0.5*(body.getAcceleration().getY() + oldAccs[i].getY()) * (Constants.timeStep));

            body.setCenter(new Vector(newX, newY));
            ++i;
        }
        i = 0;
    }


    public void updateVerletWithAdaptiveTimeStepUpdate(List<Body> bodies, double adaptiveTimeStep)
    {
        Vector[] oldAccs = new Vector[Constants.N];
        int i = 0;
        for (Body body: bodies)
        {
            oldAccs[i++] = body.getAcceleration();
            body.setAcceleration(new Vector(0,0));
            for (Body bodyIter: bodies)
            {
                if(body.equals(bodyIter)) continue;

                Vector displacement = bodyIter.getCenter().sub(body.getCenter());
                double distance = displacement.magn();
                double softenedDistance = Math.sqrt(distance*distance + Constants.epsilon*Constants.epsilon);
                double scalingFactorAcc = Constants.G * bodyIter.getMass() / Math.pow(softenedDistance, 3);

                Vector acc = displacement.scale(scalingFactorAcc);
                body.setAcceleration(body.getAcceleration().add(acc));
            }
        }
        i = 0;
        for (Body body: bodies)
        {
            //x dir
            double newX = body.getCenterX() +
                    body.getVelocity().getX()*(adaptiveTimeStep) +
                    0.5*body.getAcceleration().getX()*(adaptiveTimeStep)*(adaptiveTimeStep);
            body.getVelocity().setX(body.getVelocity().getX() + 0.5*(body.getAcceleration().getX() + oldAccs[i].getX()) * (adaptiveTimeStep));
            //y dir
            double newY = body.getCenterY() +
                    body.getVelocity().getY()*(adaptiveTimeStep) +
                    0.5*body.getAcceleration().getY()*(adaptiveTimeStep)*(adaptiveTimeStep);
            body.getVelocity().setY(body.getVelocity().getY() + 0.5*(body.getAcceleration().getY() + oldAccs[i].getY()) * (adaptiveTimeStep));

            body.setCenter(new Vector(newX, newY));
            ++i;
        }
        i = 0;
    }

    public void updateVerletWithAdaptiveTimeStep(List<Body> bodies)
    {
        double minDist = Double.MAX_VALUE;
        double adaptiveTimeStep = Constants.timeStep;
        for (int i = 0; i < bodies.size(); ++i)
            for (int j = i+1; j < bodies.size(); ++j)
                minDist = Math.min(minDist, bodies.get(i).getCenter().sub(bodies.get(j).getCenter()).magn());

        int subStepCount = (int) Math.max(1, Math.min(20, 500/minDist));
        adaptiveTimeStep = Constants.timeStep/subStepCount;

        for (int i = 0; i < subStepCount; ++i)
            updateVerletWithAdaptiveTimeStepUpdate(bodies, adaptiveTimeStep);
    }

}

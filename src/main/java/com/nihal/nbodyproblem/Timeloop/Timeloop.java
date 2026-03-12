package com.nihal.nbodyproblem.Timeloop;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Engine.PhysicsEngine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class Timeloop{
    private Body[] bodies;
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private Timeline timeloop;

    public Timeloop(Body[] bodies)
    {
        this.bodies = bodies;
        this.timeloop = new Timeline(
                new KeyFrame(Duration.millis(10), e ->{
                    physicsEngine.update(bodies);
                })
        );
        timeloop.setCycleCount(Animation.INDEFINITE);
    }


    public void start() {timeloop.play();}
    public void pause() {timeloop.stop();}

    public String pauseOrPlay()
    {
        if (timeloop.getStatus() == Animation.Status.RUNNING) {
            timeloop.stop();
            return "Start";
        }
        else
        {
            timeloop.play();
            return "Stop";
        }
    }
};

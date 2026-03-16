package com.nihal.nbodyproblem.Timeloop;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Engine.PhysicsEngine;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Timeloop{
    List<Body> bodies = new ArrayList<>();
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private Timeline timeloop;

    public Timeloop(List<Body> bodies)
    {
        this.bodies = bodies;
        this.timeloop = new Timeline(
                new KeyFrame(Duration.millis(1000/Constants.fps), e ->{
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

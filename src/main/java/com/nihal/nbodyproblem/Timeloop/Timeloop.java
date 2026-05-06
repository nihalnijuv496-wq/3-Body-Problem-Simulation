package com.nihal.nbodyproblem.Timeloop;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Engine.PhysicsEngine;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Timeloop{
    List<BodyWrapper> bodyWrappers = new ArrayList<>();
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private Timeline timeloop;

    public Timeloop(List<BodyWrapper> bodyWrappers)
    {
        this.bodyWrappers = bodyWrappers;

        this.timeloop = new Timeline(
                new KeyFrame(Duration.millis((double) 1000 /Constants.fps), e ->{
                    List<Body> bodies = bodyWrappers.stream().map(BodyWrapper::getBody).toList();
                    physicsEngine.update(bodies);
                })
        );
        timeloop.setCycleCount(Animation.INDEFINITE);
    }


    public void start() {timeloop.play();}
    public void pause() {timeloop.stop();}

    public void pauseOrPlay()
    {
        if (timeloop.getStatus() == Animation.Status.RUNNING)
            timeloop.stop();
        else
            timeloop.play();
    }
};

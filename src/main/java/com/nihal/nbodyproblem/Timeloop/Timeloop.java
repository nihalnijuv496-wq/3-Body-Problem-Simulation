package com.nihal.nbodyproblem.Timeloop;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Engine.PhysicsEngine;
import com.nihal.nbodyproblem.UI.SideBar.RunTimeDataTab;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.FadeProperty;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Timeloop{
    List<BodyWrapper> bodyWrappers;
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private List<Body> bodies;
    private Timeline timeloop;

    public Timeloop(List<BodyWrapper> bodyWrappers, RunTimeDataTab runTimeDataTab, Pane world)
    {
        this.bodyWrappers = bodyWrappers;


        this.timeloop = new Timeline(
                new KeyFrame(Duration.millis((double) 1000 /Constants.fps), e ->{

                    if(bodies == null) bodies = this.bodyWrappers.stream().map(BodyWrapper::getBody).toList();
                    FadeProperty.addFadeToBodies(bodies, world);
                    physicsEngine.updateVerletWithAdaptiveTimeStep(bodies);
                    bodyWrappers.forEach(BodyWrapper::updateArrow);
                    runTimeDataTab.updateValues();
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
    public void resetAll()
    {
        bodyWrappers.clear();
        bodies = null;
    }

    public void initializeAccelerations(List<Body> bodies)
    {
        physicsEngine.initializeAccelerations(bodies);
    }
};

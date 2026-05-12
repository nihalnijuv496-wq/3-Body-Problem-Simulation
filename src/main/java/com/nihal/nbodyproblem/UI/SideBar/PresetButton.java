package com.nihal.nbodyproblem.UI.SideBar;


import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Presets.Presets;
import com.nihal.nbodyproblem.Util.PresetUtils;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.List;

public class PresetButton extends Button {
    Presets buttonType;
    public PresetButton(Presets buttonType, List<BodyWrapper> bodyWrappers, Pane world, SideBar sideBar)
    {
        this.buttonType = buttonType;
        setText(Presets.getText(buttonType));
        setOnAction(e ->{
            PresetUtils.loadPreset(buttonType, bodyWrappers, world, sideBar);
        });
        getStyleClass().add("square-button");
    }
}



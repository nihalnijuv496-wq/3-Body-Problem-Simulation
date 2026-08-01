package com.nihal.nbodyproblem.UI.SideBar;
import javafx.scene.control.ToggleButton;
public class Tab extends ToggleButton {

    public Tab(String text)
    {
        super(text);
        getStyleClass().add("tab-button");
    }
}

package com.nihal.nbodyproblem.UI.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
public class Tab extends ToggleButton {

    public Tab(String text)
    {
        super(text);
        getStyleClass().add("tab-button");
    }
}

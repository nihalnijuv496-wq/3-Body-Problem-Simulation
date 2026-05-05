package com.nihal.nbodyproblem.UI.SideBar;
import com.nihal.nbodyproblem.Util.Constants;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
public class Tab extends ToggleButton {

    public Tab(String text)
    {
        super(text);
        setStyle(Constants.tabStyleUnselected);
        selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                setStyle(Constants.tabStyleSelected);
            } else {
                setStyle(Constants.tabStyleUnselected); // Resets to default
            }
        });
    }
}

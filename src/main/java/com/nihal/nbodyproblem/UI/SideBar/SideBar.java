package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Util.Constants;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SideBar extends ScrollPane {
    List<DataInputBox> dataInputBoxes = new ArrayList<>();
    List<ToggleButton> toggleButtons = new ArrayList<>();
    ToggleGroup grp = new ToggleGroup();
    HBox tabs = new HBox(5);
    VBox sidebarContentArea = new VBox(10);

    public SideBar()
    {
        grp.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            int index = toggleButtons.indexOf(newVal);
            sidebarContentArea.getChildren().setAll(dataInputBoxes.get(index));
        });

        setPrefWidth(Constants.sideBarWidth);
        setFitToWidth(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox sideBarContent = new VBox(tabs, sidebarContentArea);
        sideBarContent.setStyle("-fx-background-color: #070D0D;");
        setContent(sideBarContent);
    }

    public void addNewTab(int i)
    {
        dataInputBoxes.add(new DataInputBox());
        toggleButtons.add(new ToggleButton("m" + (i + 1)));
        toggleButtons.getLast().setToggleGroup(grp);
        tabs.getChildren().add(toggleButtons.getLast());
    }



    public DataInputBox getLastDataInputBox(){ return dataInputBoxes.getLast();}

    public void setDefaultTab()
    {
        toggleButtons.getLast().setSelected(true);
        sidebarContentArea.getChildren().setAll(dataInputBoxes.getLast());
    }

    public void resetAll()
    {
        dataInputBoxes.clear();
        toggleButtons.clear();
        tabs.getChildren().clear();
        sidebarContentArea.getChildren().clear();
    }
}

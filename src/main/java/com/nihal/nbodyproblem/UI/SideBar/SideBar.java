package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.Body;
import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Body.BodyWrapper;
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
    List<Tab> tabs = new ArrayList<>();
    ToggleGroup grp = new ToggleGroup();
    HBox tabBar = new HBox(1);
    VBox sidebarContentArea = new VBox(10);





    public SideBar()
    {
        grp.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            int index = tabs.indexOf(newVal);
            sidebarContentArea.getChildren().setAll(dataInputBoxes.get(index));
        });

        setPrefWidth(Constants.sideBarWidth);
        setFitToWidth(true);
        setFitToHeight(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox sideBarContent = new VBox(tabBar, sidebarContentArea);
        setContent(sideBarContent);

        getStyleClass().add("sidebar");
        tabBar.getStyleClass().add("tab-bar");
        sideBarContent.getStyleClass().add("sidebar-content");
    }

    public void addNewTab(int i, List<BodyWrapper> bodyWrappers)
    {

        dataInputBoxes.add(new DataInputBox(bodyWrappers));
        tabs.add(new Tab("m" + (i + 1)));
        tabs.getLast().setToggleGroup(grp);
        tabBar.getChildren().add(tabs.getLast());
    }





    public DataInputBox getLastDataInputBox(){ return dataInputBoxes.getLast();}

    public void setDefaultTab()
    {
        tabs.getLast().setSelected(true);
        sidebarContentArea.getChildren().setAll(dataInputBoxes.getLast());
    }

    public void resetAll()
    {
        DataInputBox.totalNum = 0;
        dataInputBoxes.clear();
        tabs.clear();
        tabBar.getChildren().clear();
        sidebarContentArea.getChildren().clear();
    }
}

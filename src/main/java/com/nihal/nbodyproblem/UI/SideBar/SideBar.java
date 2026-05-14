package com.nihal.nbodyproblem.UI.SideBar;

import com.nihal.nbodyproblem.Body.BodyWrapper;
import com.nihal.nbodyproblem.Presets.Presets;
import com.nihal.nbodyproblem.Util.Constants;
import com.nihal.nbodyproblem.Util.PresetUtils;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SideBar extends ScrollPane {
    List<DataInputBox> dataInputBoxes = new ArrayList<>();
    List<Tab> tabs = new ArrayList<>();
    ToggleGroup grp = new ToggleGroup();
    HBox tabBar = new HBox(1);
    VBox sidebarContentArea = new VBox(10);
    RunTimeDataTab runTimeDataTab;
    List<PresetButton> presetsButtons = new ArrayList<>();






    public SideBar(List<BodyWrapper> bodyWrappers, Pane world)
    {
        grp.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            int index = tabs.indexOf(newVal);
            if (index == 0)
            {
                sidebarContentArea.getChildren().setAll(runTimeDataTab);
                return;
            }
            sidebarContentArea.getChildren().setAll(dataInputBoxes.get(index - 1));
        });

        setPrefWidth(Constants.sideBarWidth);
        setFitToWidth(true);
        setFitToHeight(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        runTimeDataTab = new RunTimeDataTab();

        setPresets(bodyWrappers, world);

        VBox sideBarContent = new VBox(tabBar, sidebarContentArea);

        setContent(sideBarContent);

        getStyleClass().add("sidebar");
        tabBar.getStyleClass().add("tab-bar");
        sideBarContent.getStyleClass().add("sidebar-content");
    }

    public void addNewTab(int i, List<BodyWrapper> bodyWrappers, double clickedPosX, double clickedPosY)
    {

        if(i == 0)
        {

            tabs.add(new Tab("Runtime Data"));
            tabs.getLast().setToggleGroup(grp);
            tabBar.getChildren().add(tabs.getLast());
        }

        dataInputBoxes.add(new DataInputBox(bodyWrappers, clickedPosX, clickedPosY, runTimeDataTab));

        tabs.add(new Tab("m" + (i + 1)));
        tabs.getLast().setToggleGroup(grp);
        tabBar.getChildren().add(tabs.getLast());
    }

    public void addRunTimeDataField(int i, List<BodyWrapper> bodyWrappers)
    {
        runTimeDataTab.addRunTimeData(bodyWrappers.get(i).getBody(), bodyWrappers);
    }





    public DataInputBox getLastDataInputBox(){ return dataInputBoxes.getLast();}

    public List<DataInputBox> getDataInputBoxes() {
        return dataInputBoxes;
    }

    public void setDefaultTab()
    {
        tabs.getLast().setSelected(true);
        sidebarContentArea.getChildren().setAll(dataInputBoxes.getLast());
    }

    private void setPresets(List<BodyWrapper> bodyWrappers, Pane world)
    {
        Presets[] presets = Presets.values();
        for(Presets p : presets)
        {
            presetsButtons.add(new PresetButton(p, bodyWrappers, world, this));
            sidebarContentArea.getChildren().add(presetsButtons.getLast());
        }
    }

    public void resetAll(List<BodyWrapper> bodyWrappers, Pane world)
    {
        DataInputBox.totalNum = 0;
        dataInputBoxes.clear();
        tabs.clear();
        tabBar.getChildren().clear();
        sidebarContentArea.getChildren().clear();
        setPresets(bodyWrappers, world);

    }

    public RunTimeDataTab getRunTimeDataTab() { return runTimeDataTab; }
}

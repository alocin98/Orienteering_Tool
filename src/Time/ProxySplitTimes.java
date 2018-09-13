package Time;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import java.util.ArrayList;

public class ProxySplitTimes implements ISplitTimes{

    private SplitTimes splitTimes;
    private ListView<TextField> listView;

    public ProxySplitTimes(SplitTimes splits, ListView<TextField> lv){
        this.splitTimes = splits;
        this.listView = lv;
    }

    public SplitTimes getSplitTimes(){
        return this.splitTimes;
    }

    public void addSplit(){
        splitTimes.addSplit(new Time(0));
        listView.getItems().add(new TextField());
        updateView();
    }

    @Override
    public void addSplit(Time time) {
        splitTimes.addSplit(time);
        listView.getItems().add(new TextField());
        updateView();
    }

    @Override
    public void removeSplit(int i) {
        splitTimes.removeSplit(i);
        listView.getItems().remove(i);
        updateView();
    }

    @Override
    public void changeSplit(int index, Time time) {
        splitTimes.changeSplit(index, time);
        updateView();
    }

    private void updateView(){
        ArrayList<Time> splitList = splitTimes.getSplits();
        listView.getItems().get(0).setText("Start");
        if(listView.getItems().size() <= 1)
            return;
        for(int i = 1; i < splitList.size()-1; i++){
            listView.getItems().get(i).setText("Control "+i);
        }
        listView.getItems().get(listView.getItems().size()-1).setText("Finish");
    }
}

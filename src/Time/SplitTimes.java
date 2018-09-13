package Time;

import java.util.ArrayList;

public class SplitTimes implements ISplitTimes {
    private ArrayList<Time> splitTimes;

    public SplitTimes(){
        splitTimes = new ArrayList<Time>();
    }

    public ArrayList<Time> getSplits(){
        return this.splitTimes;
    }

    @Override
    public void addSplit(Time time){
        splitTimes.add(time);
    }

    @Override
    public void removeSplit(int i){
        splitTimes.remove(i);
    }

    @Override
    public void changeSplit(int index, Time time){
        splitTimes.set(index, time);
    }

}

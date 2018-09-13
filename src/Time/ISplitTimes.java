package Time;

public interface ISplitTimes {

    void addSplit(Time time);

    void removeSplit(int i);

    void changeSplit(int index, Time time);
}

package com.kodonho.android.fragment_observer;

import java.util.ArrayList;

public class Data {
    ArrayList<Observer> observers = new ArrayList<>();
    ArrayList<MusicData> datas = new ArrayList<>();
    private int position = -1;
    public void setPosition(int i) {
        position = i;
        notifyChanged();
    }
    public int getPosition(){
        return position;
    }
    public void attach(Observer o){
        observers.add(o);
    }
    private void notifyChanged(){
    }
    public interface Observer {
        public void update();
    }
    public int getCount(){
        return datas.size();
    }
}
class MusicData{

}


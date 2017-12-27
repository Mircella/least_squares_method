package sample.Calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    protected ObservableList<Data> list;
    private Data data;

    public Generator() {
    }

    protected ObservableList<Data> generate(){
        double x,y;
        list = FXCollections.observableArrayList();
        Random random = new Random(System.currentTimeMillis());
        int size = 10;
        for(int i=0;i<size;i++){
            x = Math.rint(random.nextDouble()*100)/10;
            y = Math.rint(random.nextDouble()*100)/10;
            data = new Data(x,y);
            list.add(data);
        }
        return list;
    }
}

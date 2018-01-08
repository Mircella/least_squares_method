package sample.Calculator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.DATA_CONVERSION;

import java.util.ArrayList;

public class Data {
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private ObservableList<Data>list;

    private DataGenerator dataGenerator;
    public Data() {
        dataGenerator = new DataGenerator();
        list = dataGenerator.generate();
    }

    public Data(double x, double y) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
    }

    public double getX() {
        return xProperty.get();
    }

    public void setX(double x) {
        this.xProperty.set(x);
    }


    public DoubleProperty xPropertyProperty() {
        return xProperty;
    }

    public DoubleProperty yPropertyProperty() {
        return yProperty;
    }

    public double getY() {
        return this.yProperty.get();
    }

    public void setY(double y) {
        this.yProperty.set(y);
    }

    public ObservableList<Data> getList() {
        return list;
    }

    private static class DataGenerator extends Generator{
        public DataGenerator() {
        }

        @Override
        protected ObservableList<Data> generate() {
            return super.generate();
        }
    }

    public void print(){
        if(list!=null){
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).getX()+" "+list.get(i).getY());
            }
        }else{
            System.out.println("list is empty");
        }
    }

    public static ObservableList<Data>createDefaultList(){
        ObservableList<Data> list = FXCollections.observableArrayList();
        list.add(new Data(1, 8));
        list.add(new Data(2, 6));
        list.add(new Data(3, 4));
        list.add(new Data(6, 3));
        list.add(new Data(7, 3));
        list.add(new Data(10, 2));
        list.add(new Data(12, 1));
        list.add(new Data(9, 2));
        list.add(new Data(11, 1));
        list.add(new Data(10, 2));
        list.add(new Data(8, 4));
        list.add(new Data(5, 2));
        return list;
    }
}

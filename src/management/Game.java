package management;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Game {

    private int id;
    private String name;

    private StringProperty cityName = new SimpleStringProperty();

    public Game(int id, String name) {
        this.id = id;
        this.name = name;
        cityName.set(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return cityName.get();
    }
}

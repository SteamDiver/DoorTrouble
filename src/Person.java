import java.util.ArrayList;
import java.util.Random;

public class Person {
    public int Type;

    private int selected = -1;
    private static Random _rnd;

    public Person(int type) {
        Type = type;
        _rnd = new Random();
    }

    public int SelectDoor(ArrayList<Door> doors, int openedDoor) {
        if (selected == -1 && openedDoor == -1) {
            selected = _rnd.nextInt(doors.size());
            return selected;
        } else {
            if (selected != -1 && openedDoor != -1) {
                switch (Type) {
                    case 0:
                        return selected;
                    case 1:
                        int desicion = _rnd.nextInt(doors.size());
                        while (desicion == selected || desicion == openedDoor) {
                            desicion = _rnd.nextInt(doors.size());
                        }
                        return desicion;
                }
            }
        }
        return -1;
    }
}


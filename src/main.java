import jdk.nashorn.internal.runtime.Debug;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Random;

public class main {
    static Random _rnd = new Random();

    public static void main(String[] args) {

        ArrayList<Door> doors = GenerateDoors(3);

        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(0));
            people.add(new Person(1));
        }


        boolean[][] results = Play(doors, people);

        PrintStat(results);

    }

    private static boolean[][] Play(ArrayList<Door> doors, ArrayList<Person> people) {
        boolean[][] results = new boolean[2][];

        for (int i = 0; i < people.size(); i++) {
            results[people.get(i).Type] = new boolean[people.size() / results.length];
        }
        for (int type = 0; type < results.length; type++) {
            int counter = 0;
            for (int i = 0; i < people.size(); i++) {
                if (people.get(i).Type == type) {
                    int door = people.get(i).SelectDoor(doors, -1);
                    int openedDoor = OpenDoor(doors, door);
                    int secondDecision = people.get(i).SelectDoor(doors, openedDoor);
                    results[type][counter] = doors.get(secondDecision).isPrise;
                    counter++;

                    System.out.println(i + ": " + people.get(i).Type + "; first selection " + door + "; opened door " + openedDoor + "; second selection " + secondDecision + "; result " + doors.get(secondDecision).isPrise);
                }
            }
        }
        return results;
    }

    private static int OpenDoor(ArrayList<Door> doors, int selectedDoor) {
        int door;
        do{
            door = _rnd.nextInt(doors.size());
        }
        while (doors.get(door).isPrise || door == selectedDoor);

        return door;
    }

    private static void PrintStat(boolean[][] results) {
        for (int i = 0; i < results.length; i++) {
            int winCount = 0;
            for (int j = 0; j < results[i].length; j++) {
                if (results[i][j]) winCount++;
            }
            System.out.println(i + ": " + winCount + " == " + (float) winCount / results[i].length * 100 + "%");
        }
    }
    private static ArrayList<Door> GenerateDoors(int count){
        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            doors.add(new Door());
        }
        Random rnd = new Random();
        doors.get(rnd.nextInt(doors.size())).isPrise = true;
        return doors;
    }
}

package metodapodstawiania;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Substitution s = new Substitution(4);
        s.calculateAllValues();
        //generowanie listy dla zmodyfikowanego 2-giego gniazda pętli
        ArrayList<RowNestedArr> listMod2 = s.getListNestTwoModified();
        //wydruk
        System.out.println("Tabela 1. Zmodyfikowane 2-gie gniazdo pętli");
        printList(listMod2);

        System.out.println("");

        //generowanie listy dla zmodyfikowanego 1-szego gniazda pętli
        ArrayList<RowNestedArr> listMod1 = s.getListNestOneModified();
        //wydruk
        System.out.println("Tabela 2. Zmodyfikowane 1-sze gniazdo pętli");
        printList(listMod1);

        //wydruk współrzędnych wierzchołków

        System.out.println("Liczba wierzchołków: " + listMod2.size());
        for(RowNestedArr row: listMod2){
            System.out.print("[" + row.getCoordinateX() + ", " + row.getCoordinateY() + ", " + row.getCoordinateZ() + "], ");
        }
        System.out.println();
        //utworzenie listy połączeń między wierzchołkami
        ArrayList<Connection> connections = s.getListOfConnections();

        System.out.println("Liczba połączen: " + connections.size());
        //wydruk połączeń między wierzchołkami współrzędne wierzchołków
        /*System.out.println();

        for (Connection con : connections) {
            RowNestedArr firstRow = listMod2.get(con.getFirstVerticeID() - 1);
            RowNestedArr secondRow =listMod2.get(con.getSecondVerticeID() - 1);
            System.out.print("[" + Arrays.toString(firstRow.getCoordinates())
                    + ", " + Arrays.toString(secondRow.getCoordinates()) + "], ");
        }
        System.out.println();
        */

        //wydruk połączeń między wierzchołkami - ID wierzchołków

        for (Connection con : connections) {
            System.out.print("[" + con.getFirstVerticeID()+", "+ con.getSecondVerticeID() + "], ");
        }
        /*
       //weryfikacja algorytmu
        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0, 0.0},
                {2.0, -2.0, 0.0, 0.0, 0.0},
                {3.0, 1.0, 2.0, 0.0, 0.0},
                {3.0, 3.0, -4.0, 3.0, 0.0},
                {2.0, -3.0, 1.0, 4.0, 1.0}
        };
        double[] vectorB ={3.0, -2.0, 1.0, 0.0, 5.0};

        Subsitution subsitutionExample = new Subsitution(5, matrix, vectorB);


        System.out.println(Arrays.toString(
                subsitutionExample.calculate()
        )
                +"\ntime of execution in nanoseconds: " //+ subsitutionExample.getTimeInNano()
        );
            /*
        //pierwsze gniazdo pętli wydruk

        //Obliczanie czasów wykonania dla poszczególnych wymiarów i wyrduk danych do tabeli

        for (int j = 0; j < 10; j++) {
            for (int i = 20; i <= 240; i += 20) {
                Subsitution sub = new Subsitution(i);
                sub.calculate();
            }
        }

        /*
        TreeMap<Integer, Double> timeMeasurement = new TreeMap<>();

        for (int j = 0; j < 20; j++) {
            for (int i = 20; i <= 240; i += 20) {
                Subsitution sub = new Subsitution(i);
                sub.calculate();
                timeMeasurement.put(i, sub.getTimeInMicroseconds());
            }
        }

        for(Map.Entry<Integer, Double> item : timeMeasurement.entrySet()){
            System.out.println(item);
        }

         */
    }

    //metoda do drukowania macierzy 2-wym.
    public static void printMatrix(Object[][] matrix) {
        for (Object[] row : matrix) {
            for (Object cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    //metoda do drukowania listy typu ArrayList przechowujących obiekty RoNestedArr
    public static void printList(ArrayList<RowNestedArr> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }
}

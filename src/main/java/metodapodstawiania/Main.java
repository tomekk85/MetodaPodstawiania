package metodapodstawiania;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Subsitution s = new Subsitution(5);
        s.calculateAllValues();
        //generowanie listy dla zmodyfikowanej 2-giej gniazdy pętli
        ArrayList<RowNestedArr> listMod2 = s.getListNestTwoModified();
        //wydruk
        System.out.println("Tabela 1. Zmodyfikowane 2-gie gniazdo pętli");
        printList(listMod2);

        System.out.println("");

        //generowanie listy dla zmodyfikowanej 1-szej gniazdy pętli
        ArrayList<RowNestedArr> listMod1 = s.getListNestOneModified();
        //wydruk
        System.out.println("Tabela 2. Zmodyfikowane 1-sze gniazdo pętli");
        printList(listMod1);

        for(RowNestedArr row: listMod2){
            System.out.print("[" + row.getCoordinateX() + ", " + row.getCoordinateY() + ", " + row.getCoordinateZ() + "], ");
        }
        System.out.println();
        ArrayList<Connection> connections = s.getListOfConnections();
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

    public static void printMatrix(Object[][] matrix) {
        for (Object[] row : matrix) {
            for (Object cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void printList(ArrayList<RowNestedArr> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }
}

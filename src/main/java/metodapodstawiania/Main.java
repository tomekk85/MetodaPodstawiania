package metodapodstawiania;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Subsitution s = new Subsitution(4);
        s.calculateAllValues();
        printMatrix(s.getMatrixNestOne());

        /*weryfikacja algorytmu
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
                +"\ntime of execution in nanoseconds: " + subsitutionExample.getTimeInNano()
        );*/

        //pierwsze gniazdo pętli wydruk

        /* Obliczanie czasów wykonania dla poszczególnych wymiarów i wyrduk danych do tabeli

        for (int j = 0; j < 10; j++) {
            for (int i = 20; i <= 240; i += 20) {
                Subsitution s = new Subsitution(i);
                s.calculate();
            }
        }


        TreeMap <Integer, Double> timeMeasurement = new TreeMap<>();

        for (int j = 0; j < 20; j++) {
            for (int i = 20; i <= 240; i += 20) {
                Subsitution s = new Subsitution(i);
                s.calculate();
                timeMeasurement.put(i, s.getTimeInMicroseconds());
            }
        }

        for(Map.Entry<Integer, Double> item:timeMeasurement.entrySet()){
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
}

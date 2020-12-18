package metodapodstawiania;

import java.util.ArrayList;

public class Subsitution {
    private int dimension;//wymiar macierzy
    private double[][] matrixA;//wejściowa macierz A
    private double[] vectorB;//wektor współczynników przy niewiadomych(?)
    private double[] vectorX;// wektor niewiadomych
    private long start;//przechowuje czas rozpoczęcia pomiaru czasu
    private long stop;//przechowuje czas zkończenia pomiaru czasu
    private ArrayList<RowNestedArr> listNestTwoModified;// lista danych do tabeli - zmodyfikowano 2-gie gn. pętli
    private ArrayList<RowNestedArr> listNestOneModified;// lista danych do tabeli - zmodyfikowano 1-sze gn. pętli
    private ArrayList<Connection> listOfConnections;//lista połączeń między wierzchołkami

    //konstruktor wykorzystywany przy weryfikacji algorytmu
    public Subsitution(int dimension, double[][] matrixA, double[] vectorB) {
        this.dimension = dimension;
        this.matrixA = matrixA;
        this.vectorB = vectorB;
    }

    //konstruktor tworzy macierz trójkątną(pod główną przekątną same jedynki, na główną przekątną same zera)
    public Subsitution(int dimension) {
        this.dimension = dimension;
        this.matrixA = generateTestMatrix();
        this.vectorB = new double[dimension];
        //przypisannie wartości 1 we wszystkich kom. wektoraB
        for (int i = 0; i < dimension; i++) {
            vectorB[i] = 1;
        }
    }

    //generowanie macierzy trójkątnej z jedynkami na głównej przekątnej i pod główną przekątną(nad główną przekątną same 0)
    public double[][] generateTestMatrix() {
        double[][] texstMatrix = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i >= j) {
                    texstMatrix[i][j] = 1;
                } else {
                    texstMatrix[i][j] = 0;
                }
            }
        }

        return texstMatrix;
    }

    private Long getTimeInNano() {
        return stop - start;
    }

    public void warmup() {
        for (int i = 0; i < 10; i++) {
            calculate();
        }
    }

    public Double getTimeInMicroseconds() {
        // zamienić nanosekundy na mikrosekundy
        return Double.valueOf(getTimeInNano()) / 1000.0;
    }

    public int getDimension() {
        return dimension;
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public double[] getVectorB() {
        return vectorB;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setMatrixA(double[][] matrixA) {
        this.matrixA = matrixA;
    }

    public void setVectorB(double[] vectorB) {
        this.vectorB = vectorB;
    }

    public double[] getVectorX() {
        return vectorX;
    }

    public ArrayList<RowNestedArr> getListNestTwoModified() {
        return listNestTwoModified;
    }

    public ArrayList<RowNestedArr> getListNestOneModified() {
        return listNestOneModified;
    }

    public ArrayList<Connection> getListOfConnections() {
        return listOfConnections;
    }

    //petle obliczeniowe - implementacja algorytmu
    public double[] calculate() {
        calculateVecorX();
        return vectorX;
    }

    public void calculateVecorX() {
        vectorX = new double[dimension];
        //start pomiaru czasu
        start = System.nanoTime();

        for (int i = 0; i < dimension; i++) {
            vectorX[i] = vectorB[i] / matrixA[i][i];
            for (int j = i + 1; j < dimension; j++) {
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];
            }
        }
        //koniec pomiaru czasu
        stop = System.nanoTime();
    }

    /**
     * Metoda wylicza elementy tabeli i zapisuje je do tablicy przy zmodyfikowanym 2-gim gnieździe pętli
     * (psiwykład2 - slajd4)
     */
    public void calculateWithSecondNestModified() {

        listNestTwoModified = new ArrayList<>();// lista przechowuje elementy zliczane z 1 i 2 gniazda pętli

        vectorX = new double[dimension];

        //pierwsze gniazdo
        int iterator = 1;//zlicza ile razy była wykonana(w ogóle) operacja z wewnętrznej pętli

        for (int i = 0; i < dimension; i++) {
            vectorX[i] = vectorB[i] / matrixA[i][i];
            for (int j = i + 1; j < dimension; j++) {
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];

                for (int k = i + 1; k < dimension; k++) {
                    int vertA = i + 1;
                    int vertB = j + 1;
                    int vertC = k + 1;

                    listNestTwoModified.add(
                            new RowNestedArr(iterator, vertA, vertB, vertC,
                                    new PairOfCoordinates(vertB, vertA),
                                    new PairOfCoordinates(vertA, vertC),
                                    new PairOfCoordinates(vertB, vertC)
                            )
                    );
                    iterator++;
                }
            }
        }
    }

    /**
     * Metoda wylicza elementy do tabeli i zapisuje je do tablicy przy zmodyfikowanym 1-szym gnieździe pętli
     * (psiwykład2 - slajd6)
     */

    public void calculateWithFirstNestModified() {
        int numberOfElements = 0; // liczba elementów
        for (int i = 1; i < dimension; i++) {
            numberOfElements += i * i;
        }

        listNestOneModified = new ArrayList<>();// lista przechowuje elementy zliczane z 1 i 2 gniazda pętli

        vectorX = new double[dimension];

        //pierwsze gniazdo
        int iterator = 1;//zlicza ile razy była wykonana(w ogóle) operacja z wewnętrznej pętli

        for (int i = 0; i < dimension; i++) {
            vectorX[i] = vectorB[i] / matrixA[i][i];

            for (int j = i + 1; j < dimension; j++) {
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];

                for (int k = i; k <= i; k++) {
                    int vertA = i + 1;
                    int vertB = j + 1;
                    int vertC = k + 1;

                    listNestOneModified.add(
                            new RowNestedArr(
                                    iterator,
                                    vertA, vertB, vertC,
                                    new PairOfCoordinates(vertB, vertA),
                                    new PairOfCoordinates(vertA, vertC),
                                    new PairOfCoordinates(vertB, vertC)
                            )
                    );
                    iterator++;
                }
            }
        }
    }

    public void calculateAllValues() {
        calculateVecorX();
        calculateWithSecondNestModified();
        calculateWithFirstNestModified();
        calculateListOfConnections();
    }

    /**
     * Metoda zwraca listę par ID(ID pobierane z obiektu klasy @RowNestedArr) wierzchołków, między którymi są połączenia
     * oraz informację o kierunku(lewo, prawo, góra, skos)
     */
    public void calculateListOfConnections() {
        listOfConnections = new ArrayList<Connection>();

        for (int i = 0; i < listNestTwoModified.size(); i++) {
            for (int j = 1; j < listNestTwoModified.size(); j++) {
                if (i != j) {
                    RowNestedArr rowA = listNestTwoModified.get(i);
                    RowNestedArr rowB = listNestTwoModified.get(j);
                    //lewo
                    if (rowA.getCoordinateX() == rowA.getCoordinateX() && rowA.getIm().equals(rowB.getIm())) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "LEFT"));
                    }//prawo
                    else if (rowA.getCoordinateX() == rowA.getCoordinateX() && rowA.getIa2().equals(rowB.getIa2())) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "RIGHT"));
                    }//góra
                    else if (rowA.getCoordinateY() == rowA.getCoordinateY() && rowA.getCoordinateZ() == rowA.getCoordinateZ()
                            && rowA.getIa1().equals(rowB.getIa1())) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "UP"));
                    }//skos
                    if (rowA.getCoordinateZ() == rowB.getCoordinateZ() && rowA.getIa1().equals(rowB.getIa2())
                            && Math.abs(rowA.getCoordinateX() - rowB.getCoordinateX()) == 1
                            && Math.abs(rowA.getCoordinateY() - rowB.getCoordinateY()) == 1
                    ) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "CROSS"));
                    }
                }
            }
        }
    }


}

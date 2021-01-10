package metodapodstawiania;

import java.util.ArrayList;

public class Substitution {
    private int dimension;//wymiar macierzy
    private double[][] matrixA;//wejściowa macierz A
    private double[] vectorB;//wektor współczynników przy niewiadomych(?)
    private double[] vectorX;// wektor niewiadomych
    private long start;//przechowuje czas rozpoczęcia pomiaru czasu
    private long stop;//przechowuje czas zkończenia pomiaru czasu
    private ArrayList<RowNestedArr> listNestTwo;// lista danych do tabeli - zmodyfikowano 2-gie gn. pętli
    private ArrayList<RowNestedArr> listNestOne;// lista danych do tabeli - zmodyfikowano 1-sze gn. pętli
    private ArrayList<RowNestedArr> listOfBothNests;// lista danych do tabeli zbiorczej 1-sze i 2-gie gn. pętli
    private ArrayList<Connection> listOfConnections;//lista połączeń między wierzchołkami

    //konstruktor wykorzystywany przy weryfikacji algorytmu
    public Substitution(int dimension, double[][] matrixA, double[] vectorB) {
        this.dimension = dimension;
        this.matrixA = matrixA;
        this.vectorB = vectorB;
    }

    //konstruktor tworzy macierz trójkątną(pod główną przekątną same jedynki, na główną przekątną same zera)
    public Substitution(int dimension) {
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

    public ArrayList<RowNestedArr> getListOfBothNests() {
        return listOfBothNests;
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

    public ArrayList<RowNestedArr> getListNestTwo() {
        return listNestTwo;
    }

    public ArrayList<RowNestedArr> getListNestOne() {
        return listNestOne;
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

        listNestTwo = new ArrayList<>();// lista przechowuje elementy zliczane z 1 i 2 gniazda pętli

        vectorX = new double[dimension];

        //drugie gniazdo
        int iterator = 1;//zlicza ile razy była wykonana(w ogóle) operacja z wewnętrznej pętli

        for (int i = 0; i < dimension; i++) {
            vectorX[i] = vectorB[i] / matrixA[i][i];
            for (int j = i + 1; j < dimension; j++) {
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];


                int vertA = i + 1;
                int vertB = j + 1;


                listNestTwo.add(
                        new RowNestedArr(iterator, vertA, vertB,
                                new PairOfCoordinates(vertA, vertB),
                                new PairOfCoordinates(vertB, vertA)
                        )
                );
                iterator++;

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

        listNestOne = new ArrayList<>();//

        vectorX = new double[dimension];

        //pierwsze gniazdo
        int iterator = listNestTwo.size() + 1;//zlicza ile razy była wykonana(w ogóle) operacja z wewnętrznej pętli

        for (int i = 0; i < dimension; i++) {
            vectorX[i] = vectorB[i] / matrixA[i][i];
            int vertA = i + 1;
            int vertB = i + 1;

            listNestOne.add(
                    new RowNestedArr(
                            iterator,
                            vertA, vertB,
                            new PairOfCoordinates(vertA, vertB),
                            new PairOfCoordinates(vertB, vertA)
                    )
            );
            iterator++;

            for (int j = i + 1; j < dimension; j++) {
                vectorB[j] = vectorB[j] - matrixA[j][i] * vectorX[i];

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
        listOfBothNests = new ArrayList<>(listNestTwo);

        for(RowNestedArr row: listNestOne){
            listOfBothNests.add(row);
        }

        listOfConnections = new ArrayList<Connection>();


        for (int i = 0; i < listOfBothNests.size(); i++) {
            for (int j = 0; j < listOfBothNests.size(); j++) {
                if (i != j) {
                    RowNestedArr rowA = listOfBothNests.get(i);
                    RowNestedArr rowB = listOfBothNests.get(j);
                    //prawo
                    if ((rowA.getCoordinateX() == rowB.getCoordinateX() - 1) && rowA.getCoordinateY() == rowB.getCoordinateY()
                    ) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "RIGHT"));
                    }//góra
                    else if ((rowA.getCoordinateY() == rowB.getCoordinateY() - 1) && rowA.getCoordinateX() == rowB.getCoordinateX()) {
                        listOfConnections.add(new Connection(rowA.getID(), rowB.getID(), "UP"));
                    }
                }
            }
        }
    }


}

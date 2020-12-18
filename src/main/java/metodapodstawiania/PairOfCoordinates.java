package metodapodstawiania;

import java.util.Objects;

//klasa, która przechowuję parę wierzchołków
public class PairOfCoordinates {
    int a;
    int b;
    PairOfCoordinates(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairOfCoordinates that = (PairOfCoordinates) o;
        return a == that.a &&
                b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

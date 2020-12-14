package metodapodstawiania;

//klasa, która przechowuję parę wierzchołków
public class PairOfVertices {
    int a;
    int b;
    PairOfVertices(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }

}

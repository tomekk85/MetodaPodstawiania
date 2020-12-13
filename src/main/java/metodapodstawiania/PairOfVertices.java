package metodapodstawiania;

//klasa, która przechowuję parę wierzchołków
public class PairOfVertices {
    Object a;
    Object b;
    PairOfVertices(Object a, Object b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }

}

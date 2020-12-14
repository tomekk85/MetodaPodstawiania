package metodapodstawiania;

//obiekt przechowujący elementy wiersza tabeli gniazd pętli 1 i 2
public class RowNestedArr{
    private int verticeA;//W1
    private int verticeB;//W2
    private int verticeC;//W3
    private PairOfVertices im;//Im[nr]
    private PairOfVertices ia2;//Ia2[nr]
    private PairOfVertices ia1;//Ia1[nr]

    public int getVerticeA() {
        return verticeA;
    }

    public int getVerticeB() {
        return verticeB;
    }

    public int getVerticeC() {
        return verticeC;
    }

    public PairOfVertices getIm() {
        return im;
    }

    public PairOfVertices getIa2() {
        return ia2;
    }

    public PairOfVertices getIa1() {
        return ia1;
    }

    public RowNestedArr(int verticeA, int verticeB, int verticeC,
                        PairOfVertices im, PairOfVertices ia2, PairOfVertices ia1) {
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.verticeC = verticeC;
        this.im = im;
        this.ia2 = ia2;
        this.ia1 = ia1;

    }
    @Override
    public String toString(){
        return verticeA + " " + verticeB + " " + verticeC + " " + im + " " + ia2 + " "  + ia1;
    }
}

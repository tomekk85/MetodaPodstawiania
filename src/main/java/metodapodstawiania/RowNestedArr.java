package metodapodstawiania;

//obiekt przechowujący elementy wiersza tabeli gniazd pętli 1 i 2
public class RowNestedArr{
    int index;
    private int coordinateX;//W1
    private int coordinateY;//W2
    private int coordinateZ;//W3
    private PairOfCoordinates im;//Im[nr]
    private PairOfCoordinates ia2;//Ia2[nr]
    private PairOfCoordinates ia1;//Ia1[nr]

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getCoordinateZ() {
        return coordinateZ;
    }

    public PairOfCoordinates getIm() {
        return im;
    }

    public PairOfCoordinates getIa2() {
        return ia2;
    }

    public PairOfCoordinates getIa1() {
        return ia1;
    }

    public int getIndex() { return index;}

    public RowNestedArr(int index, int coordinateX, int coordinateY, int coordinateZ,
                        PairOfCoordinates im, PairOfCoordinates ia2, PairOfCoordinates ia1) {
        this.index = index;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
        this.im = im;
        this.ia2 = ia2;
        this.ia1 = ia1;

    }
    @Override
    public String toString(){
        return index + ". "+ coordinateX + " " + coordinateY + " " + coordinateZ + " " + im + " " + ia2 + " "  + ia1;
    }
}

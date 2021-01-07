package metodapodstawiania;

//obiekt przechowujący elementy wiersza tabeli gniazd pętli 1 i 2
public class RowNestedArr{
    private int ID;
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

    public int getID() { return ID;}

    public int[] getCoordinates(){
        int[] arrOfCoordinates ={coordinateX, coordinateY, coordinateZ};
        return arrOfCoordinates ;}

    public RowNestedArr(int ID, int coordinateX, int coordinateY, int coordinateZ,
                        PairOfCoordinates im, PairOfCoordinates ia2, PairOfCoordinates ia1) {
        this.ID = ID;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
        this.im = im;
        this.ia2 = ia2;
        this.ia1 = ia1;

    }
    @Override
    public String toString(){
        return ID + ". "+ coordinateX + " " + coordinateY + " " + coordinateZ + " " + im + " " + ia2 + " "  + ia1;
    }
}

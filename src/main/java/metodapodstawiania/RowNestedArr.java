package metodapodstawiania;

//obiekt przechowujący elementy wiersza tabeli gniazd pętli 1 i 2
public class RowNestedArr{
    private int ID;
    private int coordinateX;//W1
    private int coordinateY;//W2

    private PairOfCoordinates ia1;//Im[nr]
    private PairOfCoordinates ia2;//Ia2[nr]


    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }



    public PairOfCoordinates getIa1() {
        return ia1;
    }

    public PairOfCoordinates getIa2() {
        return ia2;
    }



    public int getID() { return ID;}

    public int[] getCoordinates(){
        int[] arrOfCoordinates ={coordinateX, coordinateY};
        return arrOfCoordinates ;}

    public RowNestedArr(int ID, int coordinateX, int coordinateY,
                        PairOfCoordinates ia1, PairOfCoordinates ia2) {
        this.ID = ID;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.ia1 = ia1;
        this.ia2 = ia2;

    }
    @Override
    public String toString(){
        return ID + ". "+ coordinateX + " " + coordinateY + " "  + ia1 + " " + ia2;
    }
}

public abstract class Model {

    public int wysokosc, szerokosc;

    public int[] wspolrzedne = new int[19];

    public int indeksX, indeksY;

    static boolean potwierdzenie[][] = new boolean[19][19];

    abstract void makePoints();

}

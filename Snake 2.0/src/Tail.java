public class Tail extends Model {

    public Tail(int x, int y, int wysokosc, int szerokosc){
        makePoints();

        indeksX = x;
        indeksY = y;

        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;

        potwierdzenie[x][y] = true;
    }

    @Override
    void makePoints() {
        wspolrzedne[0] = 15;
        for(int i=1;i<wspolrzedne.length;i++) wspolrzedne[i] = wspolrzedne[i-1]+30;
    }
}

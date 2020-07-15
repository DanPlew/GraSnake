public class Score extends Model{

    public Score(int wysokosc, int szerokosc){


        makePoints();

        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;

        makeCoordinates();
    }

    @Override
    void makePoints() {
        wspolrzedne[0] = 19;
        for(int i=1;i<wspolrzedne.length;i++) wspolrzedne[i] = wspolrzedne[i-1]+30;
    }

    public void makeCoordinates(){
        boolean searching = true;
        do {
            indeksX = (int) (Math.random() * 19);
            indeksY = (int) (Math.random() * 19);

            if(!potwierdzenie[indeksX][indeksY]) searching = false;
        }while(searching);
    }
}

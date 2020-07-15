import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Tail> ogonek;


    public Snake(){
        ogonek = new ArrayList<>();
        ogonek.add(new Tail(0,0, 29,29));
        ogonek.add(new Tail(1,0, 29,29));
    }

    public void moveRight(){
        ogonek.get(0).potwierdzenie[ogonek.get(0).indeksX][ogonek.get(0).indeksY] = false;
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX + 1, ogonek.get(ogonek.size()-1).indeksY, 29,29));
        ogonek.remove(0);
    }

    public void moveLeft(){
        ogonek.get(0).potwierdzenie[ogonek.get(0).indeksX][ogonek.get(0).indeksY] = false;
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX - 1, ogonek.get(ogonek.size()-1).indeksY, 29,29));
        ogonek.remove(0);
    }
    public void moveUp(){
        ogonek.get(0).potwierdzenie[ogonek.get(0).indeksX][ogonek.get(0).indeksY] = false;
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX, ogonek.get(ogonek.size()-1).indeksY - 1, 29,29));
        ogonek.remove(0);
    }
    public void moveDown(){
        ogonek.get(0).potwierdzenie[ogonek.get(0).indeksX][ogonek.get(0).indeksY] = false;
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX, ogonek.get(ogonek.size()-1).indeksY + 1, 29,29));
        ogonek.remove(0);
    }


    public void telRight(){
        ogonek.add(new Tail(18, ogonek.get(ogonek.size()-1).indeksY, 29,29));
        ogonek.remove(0);
    }
    public void telLeft(){
        ogonek.add(new Tail(0, ogonek.get(ogonek.size()-1).indeksY, 29,29));
        ogonek.remove(0);
    }
    public void telUp(){
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX, 0, 29,29));
        ogonek.remove(0);
    }
    public void telDown(){
        ogonek.add(new Tail(ogonek.get(ogonek.size()-1).indeksX, 18, 29,29));
        ogonek.remove(0);
    }
}

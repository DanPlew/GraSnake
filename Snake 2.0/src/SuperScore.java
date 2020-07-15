public class SuperScore extends Score implements Runnable {

    public boolean canIShow;
    private int chance, x;

    public SuperScore(int wysokosc, int szerokosc){
        super(wysokosc, szerokosc);
        canIShow = false;
        chance = 3;
    }

    @Override
    public void run() {
        while(true) {
            if (canIShow) {
                try {
                    Thread.sleep(5000);
                    canIShow = false;

                }catch(InterruptedException e){
                    canIShow = false;
                    return;
                }
            }

            else{
                x = (int)(Math.random()*100);
                if(x <= chance){
                    makeCoordinates();
                    canIShow = true;
                }
                else{
                    try{
                        Thread.sleep(400);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

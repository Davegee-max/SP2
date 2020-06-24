package Data.Puls;
import java.nio.channels.Pipe;
import java.sql.Timestamp;
public class PulsGenerator implements Runnable, PulsObservable {

    PulsObserver pulsObserver;

    @Override
    public void register(PulsObserver pulsObserver) {
        this.pulsObserver= pulsObserver;
    }

    @Override
    public void run() {
        while(true){
            PulsDTO pulsDTO = new PulsDTO ();
            pulsDTO.setTime(new Timestamp(System.currentTimeMillis()));
            pulsDTO.setPuls(getPuls());
            if (pulsObserver != null){
               pulsObserver.notifyPuls(pulsDTO);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public double getPuls() {
       double data = Math.random() * 50 + 60;
       double dataPuls = Math.round(data);
    return dataPuls;

    }
}



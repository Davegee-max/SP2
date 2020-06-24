package Data.Temperatur;

import Data.SPO2.SPO2DTO;

import java.sql.Timestamp;

public class TempGenerator implements Runnable,TempObservable {
    TempObserver tempObserver;
    @Override
    public void register(TempObserver tempObserver) {
        this.tempObserver=tempObserver;
    }

    @Override
    public void run() {
        while(true){
            TempDTO tempDTO = new TempDTO();
            tempDTO.setTime(new Timestamp(System.currentTimeMillis()));
            tempDTO.setTemp(getTemp());
            if (tempObserver != null){
                tempObserver.notifyTemp(tempDTO);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public double getTemp() {
        double data = Math.random() * 5 + 35;
        double dataTemp = Math.round(data);
        return dataTemp;

    }
}

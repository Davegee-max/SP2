package Data.SPO2;

import Data.Puls.PulsDTO;

import java.sql.Timestamp;

public class SPO2Generator implements Runnable, SPO2Observable {
   SPO2Observer spo2Observer;
    @Override
    public void register(SPO2Observer spo2Observer) {
        this.spo2Observer = spo2Observer;
    }

    @Override
    public void run() {
            while(true){
                SPO2DTO spo2DTO = new SPO2DTO ();
                spo2DTO.setTime(new Timestamp(System.currentTimeMillis()));
                spo2DTO.setSPO2(getSP02());
                if (spo2Observer != null){
                    spo2Observer.notifySPO2(spo2DTO);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public double getSP02() {
            double data = Math.random() * 2 + 97;
            double dataSP02 = Math.round(data);
            return dataSP02;

        }
    }


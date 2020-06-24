package Data.EKG;

public class EKGGenerator  {

    }
   /* private EKGObserver ekgobserver;

    public void register(EKGObserver ekgObserver) {
        this.ekgobserver = ekgObserver;
    }

    public void run() {
        while(true){
            EKGDTO ekgdto = new EKGDTO();
            ekgdto.setTime(new Timestamp(System.currentTimeMillis()));
            ekgdto.setEKG(Math.random()*1000);
            if (ekgobserver != null){
               ekgobserver.notify(ekgdto);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/


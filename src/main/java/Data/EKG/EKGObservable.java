package Data.EKG;

public interface EKGObservable extends Runnable {
    void register(EKGObserver ekgObserver);
}

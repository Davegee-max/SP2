package Data.EKG;

import java.util.List;

public interface EKGObserver {
    void notify(List<EKGDTO> ekgdto);
}

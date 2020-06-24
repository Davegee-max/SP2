package Data.Puls;

import java.sql.Timestamp;
import java.util.List;

public interface PulsDAO {
    void save(PulsDTO pulsDTO);

    List<PulsDTO> load(Timestamp time);

}

package Data.SPO2;

import java.sql.Timestamp;
import java.util.List;

public interface SPO2DAO {

    void save(SPO2DTO spo2DTO);


    List<SPO2DTO> load(Timestamp time);
}

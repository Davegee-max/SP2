package Data.Temperatur;



import java.sql.Timestamp;
import java.util.List;

public interface TempDAO {

    void save(TempDTO tempDTO);



    List<TempDTO> load(Timestamp time);
}

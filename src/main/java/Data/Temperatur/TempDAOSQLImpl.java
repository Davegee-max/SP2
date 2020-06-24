package Data.Temperatur;

import Data.EKG.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TempDAOSQLImpl implements TempDAO{
    @Override
    public void save(TempDTO tempDTO) {
        Connection connTemp = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connTemp.prepareStatement("INSERT INTO Temperatur(CPR,time,Temperatur) VALUES (?,?,?)");
            preparedStatement.setString(1, (tempDTO.getCPR()));
            preparedStatement.setTimestamp(2, (tempDTO.getTime()));
            preparedStatement.setDouble(3,tempDTO.getTemp());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TempDTO> load(Timestamp time) {
        return null;
    }
}

package Data.SPO2;

import Data.EKG.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SPO2DAOSQLImpl implements SPO2DAO {

    @Override
    public void save(SPO2DTO spo2DTO) {
        Connection connSPO2 = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connSPO2.prepareStatement("INSERT INTO SPO2(CPR,time,SPO2) VALUES (?,?,?)");
            preparedStatement.setString(1, (spo2DTO.getCPR()));
            preparedStatement.setTimestamp(2, (spo2DTO.getTime()));
            preparedStatement.setDouble(3,spo2DTO.getSPO2());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SPO2DAO> load(Timestamp time) {
        return null;
    }
}

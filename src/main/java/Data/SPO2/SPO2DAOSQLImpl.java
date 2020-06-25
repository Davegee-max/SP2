package Data.SPO2;

import Data.EKG.SQLConnector;
import Data.Puls.PulsDTO;

import java.sql.*;
import java.util.ArrayList;
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
    public List<SPO2DTO> load(Timestamp time) {
        List<SPO2DTO> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SPO2 WHERE time>?");
            //SELECT Puls.puls, Temperatur.Temperatur FROM Puls INNER JOIN Temperatur ON Temperatur.CPR = Puls.CPR WHERE time>?
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SPO2DTO spo2DTO = new SPO2DTO();
                spo2DTO.setCPR(resultSet.getString("CPR"));
                spo2DTO.setTime(resultSet.getTimestamp("time"));
                spo2DTO.setSPO2(resultSet.getDouble("SPO2"));
                data.add(spo2DTO);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data; }}

package Data.Temperatur;

import Data.EKG.SQLConnector;
import Data.SPO2.SPO2DTO;

import java.sql.*;
import java.util.ArrayList;
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
        List<TempDTO> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Temperatur WHERE time>?");
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TempDTO tempDTO = new TempDTO();
                tempDTO.setCPR(resultSet.getString("CPR"));
                tempDTO.setTime(resultSet.getTimestamp("time"));
                tempDTO.setTemp(resultSet.getDouble("Temperatur"));
                data.add(tempDTO);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data; }}
package Data.Puls;

import Data.EKG.EKGDTO;
import Data.EKG.SQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PulsDAOSQLImpl implements PulsDAO {
    @Override
    public void save(PulsDTO pulsdto) {
        Connection connPuls = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connPuls.prepareStatement("INSERT INTO Puls(CPR,time,puls) VALUES (?,?,?)");
            preparedStatement.setString(1, (pulsdto.getCPR()));
            preparedStatement.setTimestamp(2, (pulsdto.getTime()));
            preparedStatement.setDouble(3,pulsdto.getPuls());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PulsDTO> load(Timestamp time) {
        List<PulsDTO> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Puls WHERE time>?");
            //SELECT Puls.puls, Temperatur.Temperatur FROM Puls INNER JOIN Temperatur ON Temperatur.CPR = Puls.CPR WHERE time>?
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PulsDTO pulsDTO = new PulsDTO();
                pulsDTO.setCPR(resultSet.getString("CPR"));
                pulsDTO.setTime(resultSet.getTimestamp("time"));
                pulsDTO.setPuls(resultSet.getDouble("puls"));
                data.add(pulsDTO);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }}
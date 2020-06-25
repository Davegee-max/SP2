package GUI;

import Data.EKG.EKGDAO;
import Data.EKG.EKGDAOSQLImpl;
import Data.EKG.EKGDTO;
import Data.Puls.PulsDAO;
import Data.Puls.PulsDAOSQLImpl;
import Data.Puls.PulsDTO;
import Data.SPO2.SPO2DAO;
import Data.SPO2.SPO2DAOSQLImpl;
import Data.SPO2.SPO2DTO;
import Data.Temperatur.TempDAO;
import Data.Temperatur.TempDAOSQLImpl;
import Data.Temperatur.TempDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class LoadGui {
    public DatePicker datePicker;
    public TextArea EKGDataArea;
    public TextArea pulsDataArea;

    public void LoadData(ActionEvent actionEvent) {
    LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
    Timestamp time = Timestamp.valueOf(localDateTime);
        EKGDAO ekgdao = new EKGDAOSQLImpl();
       List<EKGDTO> ekgData = ekgdao.load(time);
        String text ="";
        for(EKGDTO data: ekgData) {
           text += "Time: " + data.getTime() + " CPR: " +  data.getCPR() + " Data.EKG: " +data.getEKG() + "\r\n";
           EKGDataArea.setText(text);
        }
        }



    public void Tilbage(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/CollectorGui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void loadPuls(ActionEvent event) {
        LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        PulsDAO pulsDAO = new PulsDAOSQLImpl();
        List<PulsDTO> pulsData = pulsDAO.load(time);
        String text ="";
        for(PulsDTO data: pulsData) {
            text += "Time: " + data.getTime() + " CPR: " +  data.getCPR() + " Puls " +data.getPuls() + "\r\n";
            EKGDataArea.setText(text);
        }
    }

    public void loadSPO2(ActionEvent event) {
        LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        SPO2DAO spo2DAO = new SPO2DAOSQLImpl();
        List<SPO2DTO> SPO2data = spo2DAO.load(time);
        String text ="";
        for(SPO2DTO data: SPO2data) {
            text += "Time: " + data.getTime() + " CPR: " +  data.getCPR() + " SPO" +data.getSPO2() + "\r\n";
            EKGDataArea.setText(text);
        }
    }

    public void loadTemp(ActionEvent event) {
        LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        TempDAO tempDAO = new TempDAOSQLImpl();
        List<TempDTO> tempdata = tempDAO.load(time);
        String text ="";
        for(TempDTO data: tempdata) {
            text += "Time: " + data.getTime() + " CPR: " +  data.getCPR() + " Temperatur" +data.getTemp() + "\r\n";
            EKGDataArea.setText(text);
    }
}
}

package GUI;

import Data.Puls.*;
import Data.SPO2.*;
import Data.Temperatur.*;
import Data.EKG.EKGDAO;
import Data.EKG.EKGDAOSQLImpl;
import Data.EKG.EKGDTO;
import Data.EKG.EKGObserver;
import SerialPort.EKGSensor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class GUIController implements EKGObserver, PulsObserver, SPO2Observer, TempObserver {

    public TextField CPRField;
    public TextArea ÆgteEKGDataOutput;
    public Polyline polyline;
    public boolean jatjak = true;
    private double x=0.0;
    public Label puls;
    public Label SPO2;
    public Label temp;
    private boolean gemSPO2;
    private boolean gemTemp;
    private boolean gemEKG;
    private boolean record;


    public void StartRecording(ActionEvent actionEvent) {
        this.record = !this.record;
    }
@Override

    public void notify(List<EKGDTO> ekgdtoList) {

    List<Double> punkter = new LinkedList<>();

    Platform.runLater(new Runnable() {
        @Override
        public void run() {

            for (int i = 0; i < ekgdtoList.size(); i++) {
                EKGDTO ekgdto1 = ekgdtoList.get(i);

                punkter.add(x);
                punkter.add(0.2 * -(double) ekgdto1.getEKG());
                x++;


            }
            polyline.getPoints().addAll(punkter);
            if (x > 2000) {
                x = 0;

                polyline.getPoints().clear();
            }


        }

    });
    Platform.runLater(new Runnable() {
        public void run() {
            for (int i = 0; i < ekgdtoList.size(); i++) {
                ekgdtoList.get(i).setTime(new Timestamp(System.currentTimeMillis()));
                ekgdtoList.get(i).setCPR(CPRField.getText());

                if (gemEKG) {

                    EKGDAO ekgdao = new EKGDAOSQLImpl();
                    ekgdao.save(ekgdtoList);

                }
//ekgdao.batchsave(ekgdto);
            }

        }
    });
}

    public void TrykStart(ActionEvent actionEvent) {

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

    public void TilbageTilStart(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/Startside.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void loadData(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/LoadGui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void ekgKnap(ActionEvent event) {
        if (jatjak) {
            EKGSensor ekgSensor = new EKGSensor();
            new Thread(ekgSensor).start();
            ekgSensor.register(this);
        }
    }
    public void StopEKg(ActionEvent actionEvent) {
        jatjak = false;
    }

    public void HentEkgData(ActionEvent actionEvent) {
        EKGSensor ekgSensor = new EKGSensor();
        String text = ÆgteEKGDataOutput.getText();


        text +=  ekgSensor.hentData();
        ÆgteEKGDataOutput.setText(text);
    }

    public void pulsKnap(ActionEvent event) {
        PulsGenerator pulsSimulator = new PulsGenerator();
        new Thread(pulsSimulator).start();
        pulsSimulator.register(this);
    }

    @Override
    public void notifyPuls(PulsDTO pulsDTO) {
        Platform.runLater(new Runnable() {
            public void run() {
        puls.setText("Puls: "+pulsDTO.getPuls() + " BPM");
         pulsDTO.setCPR(CPRField.getText());
        if (record) {
                    PulsDAO pulsDAO = new PulsDAOSQLImpl();
                   pulsDAO.save(pulsDTO);
                }
            }
});
    }

    public void SPO2Knap(ActionEvent event) {
        SPO2Generator spo2Simulator = new SPO2Generator();
        new Thread(spo2Simulator).start();
        spo2Simulator.register(this);
    }

    @Override
    public void notifySPO2(SPO2DTO spo2DTO) {
        Platform.runLater(new Runnable() {
            public void run() {
                SPO2.setText("SPO2: "+spo2DTO.getSPO2()+ " %");
                spo2DTO.setCPR(CPRField.getText());
                if (gemSPO2) {
                    SPO2DAO spo2DAO = new SPO2DAOSQLImpl();
                    spo2DAO.save(spo2DTO);
                }
            }
    });
}

    public void TempKnap(ActionEvent event) {
        TempGenerator tempSimulator = new TempGenerator();
    new Thread(tempSimulator).start();
    tempSimulator.register(this);
    }

    @Override
    public void notifyTemp(TempDTO tempDTO) {
        Platform.runLater(new Runnable() {
            public void run() {
                temp.setText("Temperatur: "+tempDTO.getTemp() + " C");
                tempDTO.setCPR(CPRField.getText());
                if (gemTemp) {
                    TempDAO tempDAO = new TempDAOSQLImpl();
                    tempDAO.save(tempDTO);
                }
        }
    });
}

    public void GemSPO2(ActionEvent event) {
        this.gemSPO2 = !this.gemSPO2;
    }

    public void GemTemp(ActionEvent event) {
        this.gemTemp = !this.gemTemp;
    }

    public void GemEKGdata(ActionEvent event) {
        this.gemEKG = !this.gemEKG;
    }
}
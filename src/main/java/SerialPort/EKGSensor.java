package SerialPort;

import Data.EKG.EKGDTO;
import Data.EKG.EKGObservable;
import Data.EKG.EKGObserver;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EKGSensor implements EKGObservable {
    String[] ports = SerialPortList.getPortNames();
    SerialPort serialPort;
    private String result = null;
    private String bufferstring = "";
    private List<EKGDTO> guiList =new LinkedList<>();
    private EKGObserver observer;

    public EKGSensor() {
        System.out.println(ports[0]);
        System.out.println(ports[0]+"Data.EKG SENSOR INITIERET");

        try {
            serialPort = new SerialPort(ports[0]);
            serialPort.openPort();
            serialPort.setRTS(true);
            serialPort.setDTR(true);
            serialPort.setParams(115200, 8, 1, serialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (SerialPortException e) {
            e.printStackTrace();

        }
    }
    public List<EKGDTO> hentData() {

        try {
            if(serialPort.getInputBufferBytesCount() > 12) {
                result = serialPort.readString();
                bufferstring += result;

                int i = bufferstring.lastIndexOf(' ');
                String parseString = bufferstring.substring(0,i).trim();
                bufferstring = bufferstring.substring(i);




                String[] inputValues;




                inputValues = parseString.split(" ");
                List<EKGDTO> ekgdtos = new LinkedList<>();
                for (int h = 0; h < inputValues.length; h++) {
                    if (!Objects.equals(inputValues[h],"")) {
                       EKGDTO ekgdto = new EKGDTO();
                        ekgdto.setTime(new Timestamp(System.currentTimeMillis()));

                        try {
                            ekgdto.setEKG(Integer.parseInt(inputValues[h]));
                        } catch(Exception e){
                            System.out.println("fejl fundet");

                        }
                       ekgdtos.add(ekgdto);


                   }

                }
                guiList.addAll(ekgdtos);
                if(guiList.size()>100){
                    //TODO print i gui
                    for (int k=0; k<guiList.size() ; k++)
                    {



                    }
                    if(observer!=null){
                        List<EKGDTO> notifyList = guiList;
                        guiList = new LinkedList<>();
                        observer.notify(notifyList);
                    }



                }
                return guiList;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void register(EKGObserver ekgObserver) {
        this.observer = ekgObserver;
    }

    @Override
    public void run() {
        while(true){

            hentData();




    }
    }
}
/*System.out.println(result);
                        EKGDTO ekgdto = new EKGDTO();
                        ekgdto.setEKG(Double.parseDouble(Values[i]));
                        ekgdto.setTime(new Timestamp(System.currentTimeMillis()));
                        values.add(ekgdto);
                        System.out.println(ekgdto);
                        Thread.sleep(1);
    */
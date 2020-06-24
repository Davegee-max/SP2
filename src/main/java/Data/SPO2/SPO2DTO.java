package Data.SPO2;

import java.sql.Timestamp;

public class SPO2DTO {
    private String CPR;
    private double SPO2;
    private Timestamp time;

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public double getSPO2() {
        return SPO2;
    }

    public void setSPO2(double puls) {
        this.SPO2 = puls;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

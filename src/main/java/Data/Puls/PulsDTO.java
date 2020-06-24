package Data.Puls;

import java.sql.Timestamp;

public class PulsDTO {
    private String CPR;
    private double puls;
    private Timestamp time;

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public double getPuls() {
        return puls;
    }

    public void setPuls(double puls) {
        this.puls = puls;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

package Data.EKG;

import java.sql.Timestamp;

public class EKGDTO {
    private String CPR;
    private double EKG;
    private Timestamp time;

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public double getEKG() {
        return EKG;
    }

    public void setEKG(double EKG) {
        this.EKG = EKG;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

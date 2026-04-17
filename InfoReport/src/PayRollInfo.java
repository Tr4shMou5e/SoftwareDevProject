public class PayRollInfo {
    private int empID;
    private String name;
    private double pay;
    private String payDate;

    public PayRollInfo() {

    }

    public void addPayRollInfo(int empID, String name, double pay, String date) {
        this.empID = empID;
        this.name = name;
        this.pay = pay;
        this.payDate = date;
    }

    public int getEmpID() {
        return empID;
    }

    public String getName() {
        return name;
    }

    public double getPay() {
        return pay;
    }

    public String getPayDate() {
        return payDate;
    }
}

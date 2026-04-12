public class PayRollInfo {
    private int empID;
    private String name;
    private double pay;

    public PayRollInfo() {

    }

    public void addPayRollInfo(int empID, String name, double pay) {
        this.empID = empID;
        this.name = name;
        this.pay = pay;
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
}

public class JobTitle {
    private int DivisionId;
    private String jobTitle;

    public JobTitle() {
    }

    public JobTitle(int DivisionId, String jobTitle) {
        this.DivisionId = DivisionId;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return DivisionId;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}

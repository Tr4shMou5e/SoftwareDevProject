public class JobTitle {
    private int Job_Title_Id;
    private String jobTitle;

    public JobTitle() {
    }

    public JobTitle(int Job_Title_Id, String jobTitle) {
        this.Job_Title_Id = Job_Title_Id;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return Job_Title_Id;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}

public class Division {
    private int job_title_Id;
    private String name;

    public Division() {
    }

    public Division(int job_title_Id, String name) {
        this.job_title_Id = job_title_Id;
        this.name = name;
    }

    public int getId() {
        return job_title_Id;
    }

    public String getName() {
        return name;
    }
}

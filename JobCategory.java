
/**
 * Kelas enumerasi untuk memberikan kategori pada class Job
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public enum JobCategory
{
    WebDeveloper("Web Developer"),
    FrontEnd("Front End"),
    BackEnd("Back End"),
    UI("UI"), 
    UX("UX"),
    Devops("Devops"),
    DataScientist("Data Scientist"),
    DataAnalyst("Data Analyst");
    
    private String name;
    
    private JobCategory(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
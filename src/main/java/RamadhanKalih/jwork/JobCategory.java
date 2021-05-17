
/**
 * Kelas enumerasi untuk memberikan kategori pada class Job
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

package RamadhanKalih.jwork;

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
    
    /** representasi enum berupa string */
    private String name;
    
    /** ctor insiasi nama enum dengan string */
    private JobCategory(String name) {
        this.name = name;
    }
    /** mengakses enumerasi dalam bentuk String */
    public String toString() {
        return name;
    }
}
package RamadhanKalih.jwork;

/** Kelas enumerasi untuk memberikan kategori pada class Job
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public enum JobCategory
{
    WebDeveloper("WebDeveloper"),
    FrontEnd("FrontEnd"),
    BackEnd("BackEnd"),
    UI("UI"), 
    UX("UX"),
    Devops("Devops"),
    DataScientist("DataScientist"),
    DataAnalyst("DataAnalyst");
    
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

    /** mengubah bentuk string menjadi enum */
    public static JobCategory fromString(String text) {
        for (JobCategory cat : JobCategory.values())
            if (cat.name.equalsIgnoreCase(text))
                return cat;
        return null;
    }
}

// @Author: Ramadhan Kalih Sewu (1806148826)
// @Version: 210318

public class Jobseeker
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String joinDate;
    private Location location;
    
    public Jobseeker(int id, String name, String email, String password,
                    String joinDate, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
    }
    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getJoinDate() {
        return this.joinDate;
    }
    public void setID(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    public void printData() {
        System.out.println(this.name);
    }
}


// @Author: Ramadhan Kalih Sewu (1806148826)
// @Version: 210318

public class Recruiter
{
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    
    public Recruiter(int id, String name, String email,
                    String phoneNumber, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
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
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public Location getLocation() {
        return this.location;
    }
    public void setID(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void printData() {
        System.out.println(this.name);
    }
}

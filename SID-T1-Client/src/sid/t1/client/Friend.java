package sid.t1.client;

public class Friend {
    
    String name;
    boolean status;
    
    public Friend(String name) {
        this.name = name;
        status = false;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return name + " (" + (status ? "Online" : "Offline") + ")";
    }
}

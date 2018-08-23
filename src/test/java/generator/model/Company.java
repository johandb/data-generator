package generator.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private int id;
    private String companyName;
    protected List<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Address> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<Address>();
        }
        return addresses;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Company [id=" + this.id + ", name=" + this.companyName + " Addresses : [");
        for(Address address : getAddresses()) {
            s.append(address);
            s.append(",");
        }
        s.append("]");
        return s.toString();
    }
}
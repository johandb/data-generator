package nl.ict.psa.utils.generator.model;

/**
 * Created by johan.den.boer on 28-7-2017.
 */
public class Address
{
    private String street;
    private String city;
    private int number;
    private Person person;

    public Address()
    {
    }

    public String getStreet()
    {
        return this.street;
    }

    public void setStreet(final String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(final String city)
    {
        this.city = city;
    }

    public int getNumber()
    {
        return this.number;
    }

    public void setNumber(final int number)
    {
        this.number = number;
    }

    public Person getPerson()
    {
        return this.person;
    }

    public void setPerson(final Person person)
    {
        this.person = person;
    }

    @Override
    public String toString()
    {
        return "Address [street=" + this.street + ", city=" + this.city + ", number=" + this.number + "]";
    }

}

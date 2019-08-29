package generator.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by johan.den.boer on 28-7-2017.
 */
public class Person {
    private int id;
    private String name;
    private Address address;
    private float salary;
    private Integer age;
    private Date dateBirth;
    private boolean isActive;
    private long lvalue;
    private Status status;
    private char c;
    private List<String> emailAdressen;
    private List<Address> addresses;
    private UUID uid;

    public Person() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public float getSalary() {
        return this.salary;
    }

    public void setSalary(final float salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Date getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(final Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    public long getLvalue() {
        return this.lvalue;
    }

    public void setLvalue(final long lvalue) {
        this.lvalue = lvalue;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public char getC() {
        return this.c;
    }

    public void setC(final char c) {
        this.c = c;
    }

    public List<String> getEmailAdressen() {
        return this.emailAdressen;
    }

    public void setEmailAdressen(final List<String> emailAdressen) {
        this.emailAdressen = emailAdressen;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Person [id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", salary=" + this.salary + ", age=" + this.age + ", dateBirth=" + this.dateBirth + ", isActive=" + this.isActive + ", lvalue=" + this.lvalue + ", status=" + this.status + ", c=" + this.c + ", emailAdressen=" + this.emailAdressen + "]";
    }
}

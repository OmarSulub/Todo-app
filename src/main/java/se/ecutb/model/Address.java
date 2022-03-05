package se.ecutb.model;

import java.util.Objects;

public class Address {
    private String street;
    private String zipCode;
    private String city;

    /**
     *
     * @param street - Street and number
     * @param zipCode - zip code example: 36263
     * @param city - Växjö, Ronneby etc...
     * @throws IllegalArgumentException when any argument has null values
     */
    public Address(String street, String zipCode, String city) throws IllegalArgumentException{
        if(street == null || zipCode == null || city == null){
            throw new IllegalArgumentException("Invalid arguments: " + "street: " + street + " zipCode: " + zipCode + " city: " + city);
        }
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

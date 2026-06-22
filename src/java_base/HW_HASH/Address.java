import java.util.Objects;

public class Address {
    private final String country;
    private final String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    // Без переопределения equals/hashCode HashMap не найдёт адрес по новому объекту
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address other = (Address) o;
        return Objects.equals(country, other.country)
            && Objects.equals(city, other.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

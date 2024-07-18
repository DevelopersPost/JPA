package app.model;

import app.convertor.ZipcodeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class City {

    @NotNull
    @Column(nullable = false,length = 5)
    @Convert(converter = ZipcodeConverter.class)
    private Zipcode zipcode;

    @NotNull
    @Column(nullable = false,length = 5)
    private String name;

    @NotNull
    @Column(nullable = false,length = 5)
    private String country;

    public Zipcode getZipcode() {
        return zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

package com.sumeyyeakay;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Mysql de city tablosunda neler varsa onlari buraya yaziyoruz.
 * tanimlamalari yaptiktan sonra bu ozelliklerin getter and setter metodlarini insert ediyoruz.
 *
 * javax.persis. seciyoruruz. Hibernate bunun imple. yapiyor. (javax.persistence.Entity;)
 *
 * Entity : city vt nesnesidir diyoruz.
 * Class ismi ile vt'de olan tabloya otomatik olarak baglanir.
 *
 *
 * vt ile map etmis olduk.
 */

@Entity

public class City {

    @Id
    private int id;

    private String name;

    private String countryCode;

    private  String district;

    private int population;

    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }
    public City(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}

package ru.job4j.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Candidate implements Serializable {
    private int id;
    private String name;
    private String description;

    private byte[] photo;

    private String created = String.valueOf(LocalDate.now());

    private City city;

    public Candidate() {
    }

    public Candidate(int id, String name, byte[] photo, String description, City city, String created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.created = created;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

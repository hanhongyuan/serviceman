package com.dstvdm.person.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orientechnologies.orient.core.annotation.OId;
import com.orientechnologies.orient.core.annotation.OVersion;

import javax.persistence.OneToMany;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */
@JsonIgnoreProperties("handler")
public class Person {

    @OId
    private String id;

    @OVersion
    private Long version;

    private String firstName;

    private String lastName;

    private String email;

    private String title;

    private URI image;

    @OneToMany
    private List<Person> knows;

    private int age;

    private URL homepage;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }

    public List<Person> getKnows() {
        return knows;
    }

    public void setKnows(List<Person> knows) {
        this.knows = knows;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public URL getHomepage() {
        return homepage;
    }

    public void setHomepage(URL homepage) {
        this.homepage = homepage;
    }
}
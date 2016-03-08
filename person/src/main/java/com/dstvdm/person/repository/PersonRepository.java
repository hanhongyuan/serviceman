package com.dstvdm.person.repository;

/**
 * Created by pscot on 3/7/2016.
 */

import com.dstvdm.person.model.Person;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pscot on 3/2/2016.
 */

@Component
public class PersonRepository  {

    @Autowired
    private OrientGraph graph;

    public String addPerson(Person person) {
        OrientVertexType vertexType = graph.getVertexType("Person");
        if(vertexType == null) {
            graph.createVertexType("Person");

        }
        Vertex p = graph.addVertex("Person", "Person");
        Object id = p.getId();

        p.setProperty("firstName", person.getFirstName());
        p.setProperty("lastName", person.getLastName());
        p.setProperty("email", person.getEmail());
        p.setProperty("age", person.getAge());
        p.setProperty("title", person.getTitle());
        p.setProperty("image", person.getImage());
        p.setProperty("homepage", person.getHomepage());
        p.setProperty("knows", person.getKnows());

        graph.commit();

        return id.toString();
    }

    public Iterable<Vertex> findByFirstName(String firstName) {
        return graph.getVertices("firstName", firstName);
    }

    public Iterable<Vertex> findByEmailAddress(String email) {
        return graph.getVertices("email", email);
    }

    public Iterable<Vertex> findByLastName(String lastName) {
        return graph.getVertices("lastName", lastName);
    }

    public Iterable<Vertex> findAll() {
        return graph.getVertices();
    }
}
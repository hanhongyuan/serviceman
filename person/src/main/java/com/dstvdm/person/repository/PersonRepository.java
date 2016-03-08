package com.dstvdm.person.repository;

/**
 * Created by pscot on 3/7/2016.
 */

import com.dstvdm.person.model.Person;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */

@Component
public class PersonRepository  {

    private OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/person", "admin", "admin").setupPool(1, 10);
    //@Autowired
    //private OrientGraphFactory factory;

    public String addPerson(Person person) {
        OrientGraph graph = factory.getTx();
        try {
            OrientVertexType vertexType = graph.getVertexType("Person");
        } catch (NullPointerException e) {
            graph.createVertexType("Person");
        }
        OrientVertexType vertexType = graph.getVertexType("Person");
        Vertex p = graph.addVertex("class:Person");
        Object id = p.getId();

        p.setProperty("firstName", person.getFirstName());
        p.setProperty("lastName", person.getLastName());
        p.setProperty("email", person.getEmail());
        p.setProperty("age", person.getAge());
        p.setProperty("title", person.getTitle());
        p.setProperty("image", person.getImage());
        p.setProperty("homepage", person.getHomepage());

        // we use the knows array to create edges outgoing to other people
        //p.setProperty("knows", person.getKnows());

        graph.commit();
        graph.shutdown();
        return id.toString();
    }

    public Iterable<Vertex> findByFirstName(String firstName) {
        OrientGraph graph = factory.getTx();
        Iterable<Vertex> data = graph.getVertices("firstName", firstName);
        graph.commit();
        //graph.shutdown();
        return data;
    }

    public Iterable<Vertex> findByEmailAddress(String email) {
        OrientGraph graph = factory.getTx();
        Iterable<Vertex> data = graph.getVertices("email", email);
        //graph.shutdown();
        return data;
    }

    public Iterable<Vertex> findByLastName(String lastName) {
        OrientGraph graph = factory.getTx();
        Iterable<Vertex> data = graph.getVertices("lastName", lastName);
        //graph.shutdown();
        return data;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<Person>();
        OrientGraph graph = factory.getTx();
        for (Vertex v : graph.getVertices()) {
            Person p = new Person();
            p.setAge(v.getProperty("age"));
            p.setEmail(v.getProperty("email"));
            p.setFirstName(v.getProperty("firstName"));
            p.setHomepage(v.getProperty("homepage"));
            p.setId(v.getId().toString());
            p.setImage(v.getProperty("image"));
            p.setKnows(v.getProperty("knows"));
            p.setLastName(v.getProperty("lastName"));
            p.setTitle(v.getProperty("title"));
            people.add(p);
            // System.out.println(v.getProperty("firstName").toString());
        }
        graph.shutdown();
        return people;
    }
}
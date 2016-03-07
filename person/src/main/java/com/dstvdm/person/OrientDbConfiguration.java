package com.dstvdm.person;

/**
 * Created by pscot on 3/7/2016.
 */

import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.commons.core.OrientTransactionManager;
import org.springframework.data.orient.commons.repository.config.EnableOrientRepositories;
import org.springframework.data.orient.object.OrientObjectDatabaseFactory;
import org.springframework.data.orient.object.repository.support.OrientObjectRepositoryFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * Created by pscot on 3/2/2016.
 */

@Configuration
@EnableOrientRepositories(basePackages = "com.dstvdm.person.repository", repositoryFactoryBeanClass = OrientObjectRepositoryFactoryBean.class)
@EnableTransactionManagement
public class OrientDbConfiguration {

    @Autowired
    private OrientObjectDatabaseFactory factory;

    @Bean
    public OrientObjectDatabaseFactory factory() {
        OrientObjectDatabaseFactory factory = new OrientObjectDatabaseFactory();

        factory.setUrl("remote:localhost/bamf");
        factory.setUsername("admin");
        factory.setPassword("admin");

        return factory;
    }

    @Bean
    public OrientTransactionManager transactionManager() {
        return new OrientTransactionManager(factory());
    }

//    @Bean
//    public OrientObjectTemplate objectTemplate() {
//        return new OrientObjectTemplate(factory());
//    }

    @Bean
    public OrientGraph graphTemplate() {
        String url = "remote:localhost/person";
        try {
            OServerAdmin serverAdmin = new OServerAdmin(url).connect("root", "hello");
            if(serverAdmin.existsDatabase()) {
                OrientGraphFactory factory = new OrientGraphFactory(url);
                return factory.getTx();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("DB does not exist");
        }
        return null;
    }

//    @PostConstruct
//    @Transactional
//    public void registerEntities() {
//        factory.db().getEntityManager().registerEntityClasses("com.dstvdm.bamf.model");
//    }

}

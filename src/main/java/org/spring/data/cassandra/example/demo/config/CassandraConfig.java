package org.spring.data.cassandra.example.demo.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.util.Assert;

@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {
    @Value("${cassandra.contactpoints}") private String contactPoints = "localhost";
    @Value("${cassandra.port}") private int port = 9432;
    @Value("${cassandra.keyspace}") private String keyspace = "mykeyspace";
    @Value("${cassandra.basepackages}") private String basePackages = "org.spring.data.cassandra.example.demo.repo";
    @Value("${cassandra.username}") private String username;
    @Value("${cassandra.password}") private String password;
    @Override protected String getKeyspaceName() {
        return keyspace;
    }
    @Override protected String getContactPoints() {
        return "localhost";
    }
    @Override protected int getPort() {
        return 9042;
    }
    @Override public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    @Override public String[] getEntityBasePackages() {
        return new String[] {
                basePackages
        };
    }
    @Override
    protected Cluster getRequiredCluster() {
        CassandraClusterFactoryBean factoryBean = cluster();
        factoryBean.setJmxReportingEnabled(false);
        factoryBean.setMetricsEnabled(false);
        factoryBean.setUsername(username);
        factoryBean.setPassword(password);
        try {
            factoryBean.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.state(factoryBean.getObject() != null, "Cluster factory not initialized");
        return factoryBean.getObject();
    }

    @Override
    protected Session getRequiredSession() {

        CassandraSessionFactoryBean factoryBean = session();

        factoryBean.setKeyspaceName(keyspace);

        Session session = factoryBean.getObject();

        Assert.state(session != null, "Session factory not initialized");

        return session;
    }
}
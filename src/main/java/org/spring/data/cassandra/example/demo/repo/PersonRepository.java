package org.spring.data.cassandra.example.demo.repo;

import org.spring.data.cassandra.example.demo.domain.Person;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCassandraRepository<Person, String> {
    Flux<Person> findByKeyFirstName(final String firstName);
    Mono<Person> findOneByKeyFirstName(final String firstName);
}


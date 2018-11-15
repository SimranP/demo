package org.spring.data.cassandra.example.demo;

import org.spring.data.cassandra.example.demo.domain.Person;
import org.spring.data.cassandra.example.demo.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private PersonRepository personRepository;
	public static void main(final String args[]) {
		SpringApplication.run(Application.class);
	}
	@Override public void run(String...args) throws Exception {
		final Person a = new Person(UUID.randomUUID().toString(), "A");
		final Person b = new Person(UUID.randomUUID().toString(), "B");
		final Person c = new Person(UUID.randomUUID().toString(), "C");
		final Person d = new Person(UUID.randomUUID().toString(), "D");
		List<Person> list = new ArrayList<Person>();
		list.add(a); list.add(b); list.add(c); list.add(d);
		personRepository.insert(list).subscribe();
		System.out.println("starting findAll");
		personRepository.findAll().log().map(Person::getName);//.subscribe(l - > System.out.println("findAll: " + l));
		System.out.println("starting findByKeyFirstName");
		personRepository.findByKeyFirstName("John").log().map(Person::getId);//.subscribe(l - > System.out.println("findByKeyFirstName: " + l));
		System.out.println("starting findOneByKeyFirstName");
		personRepository.findOneByKeyFirstName("John").log().map(Person::getId);//.subscribe(l - > System.out.println("findOneByKeyFirstName: " + l));
	}
}
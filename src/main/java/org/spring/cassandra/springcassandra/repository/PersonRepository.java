package org.spring.cassandra.springcassandra.repository;


import org.spring.cassandra.springcassandra.entity.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CassandraRepository<Person, Integer> {
}

package com.example;



import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    @Query(value = "SELECT c FROM Client c WHERE c.name = ?1")
    Collection<Client> findAllUsersByName(String name);

    static Specification<Client> hasName(String name){
        return (client,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(client.get("name"),name);
    }
    static Specification<Client> hasSurname(String surname){
        return (client,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(client.get("surname"),surname);
    }

    static Specification<Client> hasPesel(String peselNumber){
        return (client,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(client.get("peselNumber"),peselNumber);
    }

    static Specification<Client> hasCountry(String country){
        return (client,criteriaQuery,criteriaBuilder) -> {
            Join<Client, Address> clientAddresses = client.join("addressSet");
            return criteriaBuilder.equal(clientAddresses.get("country"), country);
        };
    }

}

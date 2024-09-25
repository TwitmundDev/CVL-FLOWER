package fr.twitmund.cvlapi.db.repository;

import fr.twitmund.cvlapi.db.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {


    /*
    Getter des acheteurs
     */

    Optional<CustomerEntity> findByBuyerFirstName(String firstName);
    Optional<CustomerEntity> findByBuyerLastName(String lastName);
    Optional<CustomerEntity> findByBuyerClass(String className);

    /*
    Getter des receveur
     */

    Optional<CustomerEntity> findByReceiverFirstName(String firstName);
    Optional<CustomerEntity> findByReceiverLastName(String lastName);
    Optional<CustomerEntity> findByReceiverClass(String className);




}

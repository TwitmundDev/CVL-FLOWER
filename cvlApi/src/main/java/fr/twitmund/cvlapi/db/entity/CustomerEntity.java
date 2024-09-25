package fr.twitmund.cvlapi.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import org.bson.types.ObjectId;
import org.springframework.aot.generate.GenerationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Document
public class CustomerEntity {

    @Id
    public String id ;

    public String buyerFirstName;
    public String buyerLastName;
    public String buyerClass;

    public String receiverFirstName;
    public String receiverLastName;
    public String receiverClass;

    public String customMessage;

    public Integer numberOfFlowers;

    public String distributedBy;

    public Boolean didCustomerGetFlower;


    public CustomerEntity(String buyerFirstName, String buyerLastName, String buyerClass, String receiverFirstName, String receiverLastName, String receiverClass, String customMessage, Integer numberOfFlowers, String distributedBy, Boolean didCustomerGetFlower) {
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
        this.buyerClass = buyerClass;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        this.receiverClass = receiverClass;
        this.customMessage = customMessage;
        this.numberOfFlowers = numberOfFlowers;
        this.distributedBy = distributedBy;
        this.didCustomerGetFlower = didCustomerGetFlower;
    }
}

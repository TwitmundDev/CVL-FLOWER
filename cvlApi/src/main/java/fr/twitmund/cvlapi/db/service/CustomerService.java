package fr.twitmund.cvlapi.db.service;

import com.google.gson.JsonObject;
import fr.twitmund.cvlapi.db.entity.CustomerEntity;
import fr.twitmund.cvlapi.db.repository.CustomerRepository;
import fr.twitmund.cvlapi.utils.DbError;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final MongoTemplate mongoTemplate;

    public List<CustomerEntity> fetch() {
        return customerRepository.findAll();
    }

    public JsonObject customerFetchById(String id) {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("status", "success");
            JsonObject obj = new JsonObject();
            CustomerEntity entity = customerRepository.findById(id).get();
            obj.addProperty("id", entity.id.toString());
            obj.addProperty("buyerFirstName", entity.buyerFirstName);
            obj.addProperty("buyerLastName", entity.buyerLastName);
            obj.addProperty("buyerClass", entity.buyerClass);
            obj.addProperty("receiverFirstName", entity.receiverFirstName);
            obj.addProperty("receiverLastName", entity.receiverLastName);
            obj.addProperty("receiverClass", entity.receiverClass);
            obj.addProperty("customMessage", entity.customMessage);
            obj.addProperty("numberOfFlowers", entity.numberOfFlowers);
            obj.addProperty("distributedBy", entity.distributedBy);
            obj.addProperty("didCustomerGetFlower", entity.didCustomerGetFlower);
            jsonObject.add("customer",obj);
        }catch (Exception e) {
            jsonObject.addProperty("status", "error");
            jsonObject.addProperty("message", "An internal error has occurred !");
            jsonObject.addProperty("error", e.getMessage());
        }
        return jsonObject;
    }


    public JsonObject customerUpdate(CustomerEntity customerEntity, String idOfCustomer) {
        JsonObject jsonObject = new JsonObject();
        CustomerEntity entity = customerRepository.findById(idOfCustomer).get();
        entity.setBuyerFirstName(customerEntity.buyerFirstName);
        entity.setBuyerLastName(customerEntity.buyerLastName);
        entity.setBuyerClass(customerEntity.buyerClass);

        entity.setReceiverFirstName(customerEntity.receiverFirstName);
        entity.setReceiverLastName(customerEntity.receiverLastName);
        entity.setReceiverClass(customerEntity.receiverClass);

        entity.setCustomMessage(customerEntity.customMessage);

        entity.setNumberOfFlowers(customerEntity.numberOfFlowers);
        entity.setDistributedBy(customerEntity.getDistributedBy());

        entity.setDidCustomerGetFlower(customerEntity.didCustomerGetFlower);
        try {
            customerRepository.save(entity);
            jsonObject.addProperty("status", "success");
            jsonObject.addProperty("message", "Customer modified successfully");
        } catch (Exception e) {
            jsonObject.addProperty("status", "error");
            jsonObject.addProperty("message", "An internal error has occurred !");
            jsonObject.addProperty("error", e.getMessage());

        }


        return jsonObject;
    }


    public void productDelete(String id) {
        customerRepository.deleteById(id);
    }


    public DbError createCustomer(CustomerEntity customerEntity) {
        try {
            customerRepository.insert(customerEntity);
            return DbError.Success;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return DbError.InternalServerError;
        }


    }


}

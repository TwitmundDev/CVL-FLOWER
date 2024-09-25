package fr.twitmund.cvlapi.controller;


import com.google.gson.JsonObject;
import fr.twitmund.cvlapi.db.entity.CustomerEntity;
import fr.twitmund.cvlapi.db.repository.CustomerRepository;
import fr.twitmund.cvlapi.db.service.CustomerService;
import fr.twitmund.cvlapi.utils.DbError;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" ,allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     *
     * @return List of Customer Entity
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerEntity> modifyCustomer(){
        return customerService.fetch();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public JsonObject modifyCustomer(@PathVariable String id){
        return customerService.customerFetchById(id);
    }


    /**
     *
     * @param buyerFirstName
     * @param buyerLastName
     * @param buyerClass
     * @param receiverFirstName
     * @param receiverLastName
     * @param receiverClass
     * @param customMessage
     * @param numberOfFlowers
     * @param distributedBy
     * @param didCustomerGetFlower
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public JsonObject createCustomer(@RequestParam(value = "buyerFirstName") String buyerFirstName,
                                     @RequestParam(value = "buyerLastName") String buyerLastName,
                                     @RequestParam(value = "buyerClass") String buyerClass,
                                     @RequestParam(value = "receiverFirstName") String receiverFirstName,
                                     @RequestParam(value = "receiverLastName") String receiverLastName,
                                     @RequestParam(value = "receiverClass") String receiverClass,
                                     @RequestParam(value = "customMessage") String customMessage,
                                     @RequestParam(value = "numberOfFlowers") Integer numberOfFlowers,
                                     @RequestParam(value = "distributedBy") String distributedBy,
                                     @RequestParam(value = "didCustomerGetFlower") boolean didCustomerGetFlower){
        JsonObject jsonObject = new JsonObject();
        CustomerEntity entity = new CustomerEntity(buyerFirstName, buyerLastName,buyerClass,receiverFirstName,receiverLastName,receiverClass,customMessage,numberOfFlowers,distributedBy,didCustomerGetFlower);
        if (customerService.createCustomer(entity) == DbError.Success) {
            jsonObject.addProperty("status", "success");
            jsonObject.addProperty("message", "Customer created successfully");
            return jsonObject;
        }

        jsonObject.addProperty("status", "error");
        jsonObject.addProperty("message", "Something went wrong");
        return jsonObject;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json")
    public JsonObject modifyCustomer(@RequestParam(value = "_id") ObjectId id,
                                     @RequestParam(value = "buyerFirstName") String buyerFirstName,
                                     @RequestParam(value = "buyerLastName") String buyerLastName,
                                     @RequestParam(value = "buyerClass") String buyerClass,
                                     @RequestParam(value = "receiverFirstName") String receiverFirstName,
                                     @RequestParam(value = "receiverLastName") String receiverLastName,
                                     @RequestParam(value = "receiverClass") String receiverClass,
                                     @RequestParam(value = "customMessage") String customMessage,
                                     @RequestParam(value = "numberOfFlowers") Integer numberOfFlowers,
                                     @RequestParam(value = "distributedBy") String distributedBy,
                                     @RequestParam(value = "didCustomerGetFlower") boolean didCustomerGetFlower){
        JsonObject jsonObject = new JsonObject();
        CustomerEntity entity = new CustomerEntity(buyerFirstName, buyerLastName,buyerClass,receiverFirstName,receiverLastName,receiverClass,customMessage,numberOfFlowers,distributedBy,didCustomerGetFlower);
        if (!(numberOfFlowers instanceof Integer)) {
            jsonObject.addProperty("status", "error");
            jsonObject.addProperty("message", "Bad parameter in request");
            return jsonObject;
        }
        return customerService.customerUpdate(entity , String.valueOf(id));
    }


}

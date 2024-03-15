package com.kafka.demo.consumer;

import com.kafka.demo.model.*;
import com.kafka.demo.repository.AddressRepository;
import com.kafka.demo.repository.MovementRepository;
import com.kafka.demo.repository.PersonRepository;
import com.kafka.demo.repository.StayRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {
    
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StayRepository stayRepository;
    @Autowired
    private MovementRepository movementRepository;


    @KafkaListener(topics = "topic1", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(String message) {

        try {
            if(message == null) return;

            JSONObject jsonResponse = new JSONObject(message);

            JSONArray personArray = jsonResponse.getJSONArray("Person");
            JSONArray addressArray = jsonResponse.getJSONArray("Address");
            JSONArray stayArray = jsonResponse.getJSONArray("Stay");
            JSONArray movementArray = jsonResponse.getJSONArray("Movement");

            List<Person> personList = new ArrayList<>();
            for (Object p : personArray) personList.add(new Person((JSONObject) p));
            List<Address> addresses = new ArrayList<>();
            for (Object p : addressArray) addresses.add(new Address((JSONObject) p));
            List<Stay> stays = new ArrayList<>();
            for (Object p : stayArray) stays.add(new Stay((JSONObject) p));
            List<Movements> movements = new ArrayList<>();
            for (Object p : movementArray) movements.add(new Movements((JSONObject) p));

            personRepository.saveAll(personList);
            addressRepository.saveAll(addresses);
            stayRepository.saveAll(stays);
            movementRepository.saveAll(movements);


        } catch (Exception e) { System.err.println("Error while parsing data, maybe to much request"); }

    }
}

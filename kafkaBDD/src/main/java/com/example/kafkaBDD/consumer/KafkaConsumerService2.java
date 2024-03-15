package com.example.kafkaBDD.consumer;

import com.example.kafkaBDD.helper.ObjectToXmlConverter;
import com.example.kafkaBDD.model.*;
import com.example.kafkaBDD.producer.KafkaProducerService3;
import com.example.kafkaBDD.repository.*;
import jakarta.xml.bind.JAXBException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaConsumerService2 {

    @Autowired
    private GlobalRepository globalRepository;
    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StayRepository stayRepository;
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    KafkaProducerService3 kafkaProducerService3;

    private final String EMPTY_RESULT = "No data";

    @KafkaListener(topics = "topic2", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(String message) {

        if (message == null) return;

        String[] messageParts = message.split("&");
        if(messageParts.length != 2) return;

        String requestKey = messageParts[0];
        String body = messageParts[1];
        String result = "";

        try {
            if(body.contains("getAllPatients")) result = getAllPatients();
            else if(body.contains("getPatient"))  result = getPatientByPid(body);
            else if(body.contains("getPatientByName")) result = getPatientByName(body);
            else if(body.contains("getPatientStayByPid")) result = getPatientStayByPid(body);
            else if(body.contains("getPatientMovementsBySid")) result = getPatientMovementsBySid(body);
            else if(body.contains("exportData")) result = exportData();
            else if(body.contains("help")) result = showHelp();

        } catch (Exception e) { result = e.getMessage(); }

        kafkaProducerService3.sendMessage(requestKey, result);
    }

    private String getAllPatients() {
        List<Person> res = personRepository.findAll();
        if (res.isEmpty()) return EMPTY_RESULT;
        return res.stream().map(JSONObject::new).toList().toString();
    }

    private String getPatientByPid(String message) {
        String[] urlParts = message.split("/");
        if(urlParts.length != 2) return "Impossible to find patient inputted";

        String id = urlParts[1];
        Person p = personRepository.findById(Integer.valueOf(id)).orElse(null);
        if (p == null) return EMPTY_RESULT;
        return new JSONObject(p).toString();
    }

    private String getPatientByName(String message) {
        String[] urlParts = message.split("/");
        if(urlParts.length != 2) return "Impossible to find patient inputted";

        String name = urlParts[1];
        Person p = personRepository.findByLastNameOrBirthName(name, name).orElse(null);
        if (p == null) return EMPTY_RESULT;
        return new JSONObject(p).toString();
    }

    private String getPatientStayByPid(String message) {
        String[] urlParts = message.split("/");
        if(urlParts.length != 2) return "Impossible to find patient inputted";

        String id = urlParts[1];
        Person p = personRepository.findById(Integer.valueOf(id)).orElse(null);
        if (p == null) return EMPTY_RESULT;
        List<Stay> res = stayRepository.findAllByPerson(p);
        return res.stream().map(JSONObject::new).toList().toString();
    }

    private String getPatientMovementsBySid(String message) {
        String[] urlParts = message.split("/");
        if(urlParts.length != 2) return "Impossible to find patient inputted";

        String id = urlParts[1];
        Stay stay = stayRepository.findById(Long.valueOf(id)).orElse(null);
        if (stay == null) return EMPTY_RESULT;
        List<Movements> res = movementRepository.findAllByStay(stay);
        return res.stream().map(JSONObject::new).toList().toString();
    }

    private String exportData() {
        // Implementation to export data to a JSON file and send the response or the file location
        return "";
    }

    private String showHelp() {
        JSONObject json = new JSONObject();
        json.put("/patient/getAllPatients", "Returns all patients identity.");
        json.put("/patient/getPatient/id", "Returns the identity of a patient by their id.");
        json.put("/patient/getPatientByName/name", "Returns the identity of a patient by their name.");
        json.put("/patient/getPatientStayByPid/id", "Returns the stays of a patient by their PID.");
        json.put("/patient/getPatientMovementsBySid/id", "Returns all movements of a patient by stay ID.");
        json.put("/patient/export", "Exports database data to a JSON file.");
        json.put("/patient/help", "Displays this list of commands.");
        return json.toString();
    }


}

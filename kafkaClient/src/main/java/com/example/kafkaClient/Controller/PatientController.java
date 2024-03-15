package com.example.kafkaClient.Controller;

import com.example.kafkaClient.model.DataSynchronizer;
import com.example.kafkaClient.producer.KafkaProducerService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    KafkaProducerService2 kafkaProducerService2;

    @GetMapping("/getAllPatients")
    public String getAllPatients(){
        String requestKey =  "getAllPatients-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "getAllPatients");
        return getResult(requestKey);
    }

    @GetMapping("/getPatient/{id}")
    public String getPatientById(@PathVariable("id") String id){
        String requestKey =  "getPatient/" + id + "-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "getPatient/" + id);
        return getResult(requestKey);
    }

    @GetMapping("/getPatientByName/{name}")
    public String getPatientByName(@PathVariable("name") String name) {
        String requestKey = "getPatientByName/" + name + "-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "getPatientByName/" + name);
        return getResult(requestKey);
    }

    @GetMapping("/getPatientStayByPid/{pid}")
    public String getPatientStayByPid(@PathVariable("pid") String pid) {
        String requestKey = "getPatientStayByPid/" + pid + "-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "getPatientStayByPid/" + pid);
        return getResult(requestKey);
    }

    @GetMapping("/getPatientMovementsBySid/{sid}")
    public String getPatientMovementsBySid(@PathVariable("sid") String sid) {
        String requestKey = "getPatientMovementsBySid/" + sid + "-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "getPatientMovementsBySid/" + sid);
        return getResult(requestKey);
    }

    @GetMapping("/exportData")
    public String exportData() {
        String requestKey = "exportData-" + new Date();
        kafkaProducerService2.sendMessage(requestKey, "exportData");
        return getResult(requestKey);
    }

    @GetMapping("/help")
    public String help() {
        // Assuming help content is static and does not need to be requested via Kafka
        String helpContent = "Available commands: \n" +
                "- /getAllPatients/: Returns all patients identity.\n" +
                "- /getPatientById/{name}: Returns the identity of a patient by their id.\n" +
                "- /getPatientByName/{name}: Returns the identity of a patient by their name.\n" +
                "- /getPatientStayByPid/{pid}: Returns the stays of a patient by their PID.\n" +
                "- /getPatientMovementsBySid/{sid}: Returns all movements of a patient by stay ID.\n" +
                "- /exportData: Exports database data to a JSON file.\n" +
                "- /help: Displays this list of commands.";
        return helpContent;
    }

    public String getResult(String requestKey) {
        String result = "";
        int nbSecond = 0;
        while (nbSecond < 10) {

            result = DataSynchronizer.getByKey(requestKey);
            if(result != null && !result.isBlank()) return result;

            try {
                Thread.sleep(1000);
                nbSecond++;
            } catch (Exception e) {
                result = e.getMessage();
                break;
            }
        }

        return result == null || result.isBlank() ? "Error while message transition" : result;
    }
}

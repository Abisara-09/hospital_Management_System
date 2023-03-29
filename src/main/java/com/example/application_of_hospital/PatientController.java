package com.example.application_of_hospital;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class PatientController {

  HashMap<Integer,Patient> patientDb = new HashMap<>();

    @PostMapping("/addPatientsViaParamaters")
    public String addPatient(@RequestParam("patientId")Integer patientId, @RequestParam("name")String name,
                             @RequestParam("disease")String disease,@RequestParam("age")Integer age){


        Patient patient = new Patient(patientId,name, disease,age);
        patientDb.put(patientId,patient);

        return "Patient added successfullly";
    }

    @PostMapping("/addpatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){

        int key = patient.getPatientId();

        patientDb.put(key,patient);

        return "Patient added via Request Body";
    }
    @GetMapping("/getInfoViaPathVariablle/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId")Integer patientId)
    {
        Patient patient = patientDb.get(patientId);
        return patient;
    }

    @GetMapping("/getPatient/{age}/{disease}")
    public List<Patient> getPatient(@PathVariable("age")Integer age,@PathVariable("disease")String disease)
    {
        List<Patient> patients = new ArrayList<>();
        for(Patient p : patientDb.values()){

            if(p.getAge() > age && p.getDisease().equals(disease))
            {
                patients.add(p);
            }
        }
        return patients;
    }
    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId")Integer patientId)
    {
        Patient patient = patientDb.get(patientId);
        return patient;
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients()
    {
        List<Patient> patients = new ArrayList<>();
        for(Patient p : patientDb.values())
        {
            patients.add(p);
        }
        return patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name)
    {
        for(Patient p : patientDb.values())
        {
            if(p.getName().equals(name))
            {
                return p;
            }
        }
        return null;
    }

    @GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient>getPatientGreaterThanAge(@RequestParam("age")Integer age)
    {
        List<Patient> patients = new ArrayList<>();
        for(Patient p : patientDb.values())
        {
            if(p.getAge()>age)
            {
                patients.add(p);
            }
        }
        return patients;
    }


    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId")Integer patientId,@RequestParam("disease")String disease){

        if(patientDb.containsKey(patientId)){

            Patient patient = patientDb.get(patientId);

            patient.setDisease(disease);

            patientDb.put(patientId,patient);

            return "Updated successfully";
        }else {
            return "Patient doesnot exist";
        }
    }

    @PutMapping("/updatePatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient){

        int key = patient.getPatientId();

        if(patientDb.containsKey(key)){
            patientDb.put(key,patient);
            return "Updated patient successfully";
        }
        else{
            return "Data was not existing";
        }

    }


    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId")Integer patientId){

        patientDb.remove(patientId);

        return "Patient deleted successfully";
    }

}

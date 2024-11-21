package com.javacademy.insurance;

import com.javacademy.insurance.insurence_objects.InsuranceContract;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class Archive {
    private HashMap<String, InsuranceContract> map = new HashMap<>();

    public void addContract(InsuranceContract insuranceContract) {
        map.put(insuranceContract.getNumber(), insuranceContract);
    }

    public InsuranceContract getContract(String numberContract) {
        return map.get(numberContract);
    }

    public boolean contractContains(String numberContract) {
        return map.containsKey(numberContract);
    }



}

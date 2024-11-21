package com.javacademy.insurance;

import com.javacademy.insurance.insurence_objects.InsuranceContract;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Архив
 */
@Component
@Data
public class Archive {
    private HashMap<String, InsuranceContract> map = new HashMap<>();

    /**
     * метод добавления страхового контракта в архив
     * @param insuranceContract     страховой контракт
     */
    public void addContract(InsuranceContract insuranceContract) {
        map.put(insuranceContract.getNumber(), insuranceContract);
    }

    /**
     * метод извлечения контракта из архива
     * @param numberContract    номер контракта
     * @return                  страховой контракт
     */
    public InsuranceContract getContract(String numberContract) {
        return map.get(numberContract);
    }

    /**
     * метод проверки содержится ли страховой контракт с таким номером в архиве
     * @param numberContract        номер страхового контракта
     * @return                      страховой контракт
     */
    public boolean contractContains(String numberContract) {
        return map.containsKey(numberContract);
    }



}

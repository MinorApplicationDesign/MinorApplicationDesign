package nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage;

import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.content.UseIndication;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * InMemoryDrugDao implements interface DrugDao and is the class that
 * saves all drug substances and drug groups. The class saves all drugs
 * in a hashmap called allDrugs. To make searching on drug substance and
 * drug group possible two arraylists are used (drugSubstances, mainDrugGroups)
 * which contain the drug groups and substances. these arraylists can be used
 * to search the hashmap on substance or groups.
 * @author Larissa Bouwknegt, Jonathan Klimp, Naomi Hindriks
 */
@Component
public class InMemoryDrugDao implements DrugDao {
    static private List<String> drugSubstances = new ArrayList<>();
    static private List<String> mainDrugGroups = new ArrayList<>();
    static private Map<String, Drug> allDrugs = new HashMap<>();
    static private Map<String, UseIndication> useIndications = new HashMap<>();

    /**
     * Method that will add drug to the dao.
     * It checks if the drug already exists, if it exists the method throws an IllegalArgumentException.
     * It will check if the given drug is a DrugSubstance or a DrugGroup and will add it to the correct list accordingly
     * @param drug Drug object to be added
     */
    @Override
    public void addDrug(Drug drug) {
        if(drugInDrugDao(drug.name)){
            throw new IllegalArgumentException("Cannot add drug that already exists");
        }
        allDrugs.put(drug.getName(), drug);
        if (drug instanceof DrugSubstance) {
            drugSubstances.add(drug.getName());
        }
        else if (drug instanceof DrugGroup && drug.getParent() == null) {
            mainDrugGroups.add(drug.getName());
        }
    }

    /**
     * Method that will return a Drug object given a drug name.
     * It checks if the drugs exists if it does not exist an IllegalArgumentException is thrown
     * @param drugName Name of the drug to be returned
     * @return Drug object
     */
    @Override
    public Drug getDrugByName(String drugName) {
        if (!allDrugs.containsKey(drugName.toLowerCase())) {
            throw new IllegalArgumentException(drugName.toLowerCase() + " does not exist.");
        }
        return allDrugs.get(drugName);
    }

    @Override
    public List<Drug> getAllDrugs() {
        return new ArrayList<>(allDrugs.values());
    }

    @Override
    public List<String> getAllDrugNames() {
        return new ArrayList<>(allDrugs.keySet());
    }

    @Override
    public List<DrugSubstance> getDrugSubstances() {
        List<DrugSubstance> returnList = new ArrayList<>();
        for(String drugName : drugSubstances) {
            returnList.add((DrugSubstance) allDrugs.get(drugName));
        }
        return returnList;
    }

    @Override
    public List<DrugGroup> getMainDrugGroups() {
        List<DrugGroup> returnList = new ArrayList<>();
        for (String drugGroup : mainDrugGroups) {
            returnList.add((DrugGroup) allDrugs.get(drugGroup));
        }
        return returnList;
    }

    @Override
    public boolean drugInDrugDao(String drugName) {
        return allDrugs.containsKey(drugName);
    }

    @Override
    public UseIndication getUseIndication(String indicationName) {
        if (useIndications.containsKey(indicationName)) {
            return useIndications.get(indicationName);
        } else {
            throw new IllegalArgumentException(indicationName + " Is not found in the dao");
        }

    }

    @Override
    public void addUseIndication(UseIndication useIndication) {
        useIndications.put(useIndication.getName(), useIndication);
    }


    @Override
    public List<UseIndication> getAllUseIndications() {
        return new ArrayList<>(useIndications.values());
    }

    /**
     * Method that will remove all drugs in the dao
     */
    public void removeAllDrugs() {
        drugSubstances.clear();
        mainDrugGroups.clear();
        allDrugs.clear();
        useIndications.clear();
    }
}
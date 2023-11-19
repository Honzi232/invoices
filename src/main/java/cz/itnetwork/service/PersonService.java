package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person.
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * Updating the data of the created person.
     * @param personId ID person to update
     * @param personDTO Updated data for person
     * @return Data of updated person
     */
    PersonDTO updatePerson(long personId, PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     * @param personId Person to delete
     */
    void removePerson(long personId);

    /**
     * Fetches all non-hidden people.
     * @return List of all non-hidden people
     */
    List<PersonDTO> getAllPeople();

    /**
     * Obtaining a person's ID based on an identification number.
     * @param identificationNumber Identification person number
     * @return ID person
     */
    Long getPersonIdByIdentificationNumber(String identificationNumber);

    /**
     * Getting statistics for person.
     * @return List of statistics for companies
     */
    List<PersonStatisticDTO> getPersonStatistics();

    /**
     * Attempts to fetch a person with the given ID.
     *
     * @param personId ID of the person
     * @return Fetchen person
     */
    PersonDTO getPerson(long personId);

 }

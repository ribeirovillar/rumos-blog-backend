package com.blog.mappers;

import com.blog.dtos.UserRegistrationDTO;
import com.blog.models.Person;
import com.blog.models.User;
import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T20:49:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setPerson( userRegistrationDTOToPerson( dto ) );
        user.setEmail( dto.email() );
        user.setPassword( dto.password() );

        return user;
    }

    @Override
    public UserRegistrationDTO toUserRegistrationDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UUID id = null;
        String firstName = null;
        String lastName = null;
        Date birthDate = null;
        String email = null;

        id = userPersonId( user );
        firstName = userPersonFirstName( user );
        lastName = userPersonLastName( user );
        birthDate = userPersonBirthDate( user );
        email = user.getEmail();

        String password = null;
        String role = null;

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO( id, email, password, firstName, lastName, birthDate, role );

        return userRegistrationDTO;
    }

    protected Person userRegistrationDTOToPerson(UserRegistrationDTO userRegistrationDTO) {
        if ( userRegistrationDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setFirstName( userRegistrationDTO.firstName() );
        person.setLastName( userRegistrationDTO.lastName() );
        person.setBirthDate( userRegistrationDTO.birthDate() );

        return person;
    }

    private UUID userPersonId(User user) {
        if ( user == null ) {
            return null;
        }
        Person person = user.getPerson();
        if ( person == null ) {
            return null;
        }
        UUID id = person.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userPersonFirstName(User user) {
        if ( user == null ) {
            return null;
        }
        Person person = user.getPerson();
        if ( person == null ) {
            return null;
        }
        String firstName = person.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String userPersonLastName(User user) {
        if ( user == null ) {
            return null;
        }
        Person person = user.getPerson();
        if ( person == null ) {
            return null;
        }
        String lastName = person.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private Date userPersonBirthDate(User user) {
        if ( user == null ) {
            return null;
        }
        Person person = user.getPerson();
        if ( person == null ) {
            return null;
        }
        Date birthDate = person.getBirthDate();
        if ( birthDate == null ) {
            return null;
        }
        return birthDate;
    }
}

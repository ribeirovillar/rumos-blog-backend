package com.blog.api.mappers;

import com.blog.api.dtos.AuthRequestDTO;
import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.data.models.Person;
import com.blog.data.models.User;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-05-11T00:12:03+0100",
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

        String firstName = null;
        String lastName = null;
        Date birthDate = null;
        UUID id = null;
        String email = null;

        firstName = userPersonFirstName( user );
        lastName = userPersonLastName( user );
        birthDate = userPersonBirthDate( user );
        id = user.getId();
        email = user.getEmail();

        String password = null;
        Set<String> roles = toStringRoles(user.getRoles());

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO( id, email, password, firstName, lastName, birthDate, roles );

        return userRegistrationDTO;
    }

    @Override
    public User toUser(AuthRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( dto.email() );
        user.setPassword( dto.password() );

        return user;
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

package com.backend.hormonalcare.profile.domain.model.aggregates;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.valueobjects.*;
import com.backend.hormonalcare.profile.domain.model.valueobjects.*;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    @Column()
    private PersonName name;

    @Embedded
    private Gender gender;

    @Embedded
    private Birthday birthday;

    @Embedded
    private PhoneNumber phoneNumber;

    private String image;

    @Getter
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id" )
    private User user;


    public Profile(PersonName name, Gender gender, PhoneNumber phoneNumber, Birthday birthday, String image, User user) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.image = image;
        this.user = user;
    }

    public Profile(){
    }

    public Profile(CreateProfileCommand command, User user) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.birthday = new Birthday(command.birthday());
        this.gender = new Gender(command.gender());
        this.phoneNumber = new PhoneNumber(command.phoneNumber());
        this.image = command.image();
        this.user = user;
    }

    public Profile updateProfile(String firstName, String lastName, String gender, String phoneNumber, String image, Date birthday) {
        this.name = new PersonName(firstName, lastName);
        this.gender = new Gender(gender);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.image = image;
        this.birthday = new Birthday(birthday);
        return this;
    }

    public Profile upsetPhoneNumber(PhoneNumber phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Profile upsetImage(String image){
        this.image = image;
        return this;
    }

}

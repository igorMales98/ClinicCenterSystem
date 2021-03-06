package com.clinicCenter.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
public abstract class User implements UserDetails {

    @Column(name = "type", insertable = false, updatable = false)
    protected String type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "enabled")
    private boolean enabled = false;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private Long ssn;

    @Column(name = "passwordChanged")
    private Boolean passwordChanged;

    @Column(name = "times_rated")
    private Integer timesRated;

    @Column(name = "average_rating")
    private Double averageRating;

    @Version
    private Long version = 0L;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }


    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.password = password;
    }


    public String getPassword() {
        System.out.println(password);
        return password;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", ssn=" + ssn +
                ", timesRated=" + timesRated +
                ", averageRating=" + averageRating +
                ", authorities=" + authorities +
                '}';
    }

    public Boolean getEnabled() {
        return this.enabled;
    }
}

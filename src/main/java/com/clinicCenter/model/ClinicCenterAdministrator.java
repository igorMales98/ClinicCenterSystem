package com.clinicCenter.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Builder
@Entity
@DiscriminatorValue("CCA")
public class ClinicCenterAdministrator extends User {
    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    /*
    For clinic center administrator
    ako imamo klase za zahteve
    private Collection<RegisterRequest> registerRequests;
     */
}

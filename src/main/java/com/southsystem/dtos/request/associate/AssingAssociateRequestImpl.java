package com.southsystem.dtos.request.associate;

import com.southsystem.resources.validator.associate.AssingAssociateValidator;

@AssingAssociateValidator
public class AssingAssociateRequestImpl implements AssingAssociateRequest {

    private Long associateCpf;
    private String associateName;

    @Override
    public Long getAssociateCpf() {
        return associateCpf;
    }

    @Override
    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateCpf(Long associateCpf) {
        this.associateCpf = associateCpf;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }
}

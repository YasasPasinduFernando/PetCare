package org.agile.petcare.Service;

import org.agile.petcare.Model.Owner;
import org.agile.petcare.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createOwner(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerRepository.save(owner);
    }
}


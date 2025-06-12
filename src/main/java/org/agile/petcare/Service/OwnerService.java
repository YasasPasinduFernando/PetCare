package org.agile.petcare.Service;

import org.agile.petcare.Model.Owner;
import org.agile.petcare.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ✅ Create owner with password hashing and duplicate email check
    public Owner createOwner(Owner owner) {
        if (ownerRepository.existsByEmailAddress(owner.getEmailAddress())) {
            throw new RuntimeException("Email already exists");
        }

        // Hash the password
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        // Save and return the owner
        return ownerRepository.save(owner);
    }

    // ✅ Get all owners
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // ✅ Get a single owner by ID
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    // ✅ Delete an owner by ID
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    public Owner loginOwner(String email, String rawPassword) {
        Owner owner = ownerRepository.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(rawPassword, owner.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return owner; // Or return a token/session if needed
    }

}

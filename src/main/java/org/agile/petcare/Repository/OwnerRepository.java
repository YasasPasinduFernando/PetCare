package org.agile.petcare.Repository;

import org.agile.petcare.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}

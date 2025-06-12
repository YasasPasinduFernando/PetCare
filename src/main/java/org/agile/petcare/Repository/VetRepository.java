package org.agile.petcare.Repository;

import org.agile.petcare.Model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}

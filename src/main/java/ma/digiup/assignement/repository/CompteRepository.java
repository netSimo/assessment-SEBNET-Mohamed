package ma.digiup.assignement.repository;

import ma.digiup.assignement.domain.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
  Compte findByNrCompte(String nrCompte);
}

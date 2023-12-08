package ma.digiup.assignement.repository;

import ma.digiup.assignement.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

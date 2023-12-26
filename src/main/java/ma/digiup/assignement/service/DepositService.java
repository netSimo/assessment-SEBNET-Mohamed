package ma.digiup.assignement.service;

import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService {

    @Autowired
    public CompteRepository compteRepository ;


    public Compte deposit(String rib, Double montant) {
        Compte compte = compteRepository.findByRib(rib)
                .orElseThrow(() -> new IllegalStateException("Compte not found with Rib: " + rib));

        if (montant > 10000) {
            throw new IllegalStateException("Montant non autorisé");
        } else {
            BigDecimal nouveauSolde = compte.getSolde().add(BigDecimal.valueOf(montant));
            compte.setSolde(nouveauSolde);
        }

        return compteRepository.save(compte);
    }

}

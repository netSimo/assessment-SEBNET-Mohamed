package ma.digiup.assignement.web;


import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/Deposit")
public class DepositController {


    @Autowired
    private DepositService depositService;

    public void setDepositService(DepositService depositService) {
        this.depositService = depositService;
    }

    @PatchMapping("/Montant")
    public ResponseEntity<Compte> Deposit(@RequestParam String Rib , @RequestParam Double Montant ){

        try{
            return new ResponseEntity<>(depositService.deposit(Rib,Montant), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

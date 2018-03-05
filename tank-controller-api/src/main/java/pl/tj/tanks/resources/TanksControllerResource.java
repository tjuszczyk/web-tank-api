package pl.tj.tanks.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tj.tanks.services.TankController;

@RestController
@RequestMapping("/api/tank-controller")
public class TanksControllerResource {

    @Autowired
    TankController tankController;

    @ResponseBody
    @PutMapping(path = "/movement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> move(@RequestBody MovementDetail movementDetail) {
        tankController.moveTank(movementDetail);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping(path = "/movement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovementDetail> currentMovement() throws Exception {
        return ResponseEntity.ok(
                tankController.currentTankMovement()
        );
    }
}
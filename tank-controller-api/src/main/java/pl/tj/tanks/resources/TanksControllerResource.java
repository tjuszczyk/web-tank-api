package pl.tj.tanks.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tank-controller")
public class TanksControllerResource {

    private volatile MovementDetail currentMovement = new MovementDetail(MovementDetail.MovementDirection.STOPPED);

    @RequestMapping(path = "/movement", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> move(@RequestBody MovementDetail movementDetail) {
        this.currentMovement = movementDetail;
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/movement", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MovementDetail> currentMovement() {
        return ResponseEntity.ok(this.currentMovement);
    }
}
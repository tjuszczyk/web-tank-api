package pl.tj.tanks.services;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.tj.tanks.resources.MovementDetail;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(value = SCOPE_SINGLETON)
public class TankController {

    private volatile MovementDetail currentMovement = new MovementDetail(MovementDetail.MovementDirection.STOPPED);

    public void moveTank(MovementDetail movementDetail){
        this.currentMovement = movementDetail;
    }

    public MovementDetail currentTankMovement() {
        return this.currentMovement;
    }
}
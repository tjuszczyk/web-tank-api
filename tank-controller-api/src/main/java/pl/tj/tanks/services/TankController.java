package pl.tj.tanks.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.tj.tanks.controller.MovementDirection;
import pl.tj.tanks.controller.TankConnector;
import pl.tj.tanks.resources.MovementDetail;

import javax.inject.Inject;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(value = SCOPE_SINGLETON)
public class TankController {

    public static final Logger LOG = LoggerFactory.getLogger(TankController.class);

    private volatile MovementDetail currentMovement = new MovementDetail(MovementDirection.STOPPED);

    @Inject
    private TankConnector tankConnector;


    public void moveTank(MovementDetail movementDetail) {
        LOG.info(":::Moving tank: [{}]", movementDetail);
        this.currentMovement = movementDetail;
        tankConnector.moveTank(movementDetail.direction);
    }

    public MovementDetail currentTankMovement() {
        LOG.info(":::Obtaining current tank position: [{}]", currentMovement);

        return this.currentMovement;
    }
}

package pl.tj.tanks.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.tj.tanks.controller.MovementDirection;
import pl.tj.tanks.controller.TankConnector;

public class StubbedConnector implements TankConnector {
    public static final Logger LOG = LoggerFactory.getLogger(StubbedConnector.class);

    @Override
    public void moveTank(MovementDirection direction) {
        LOG.info("Moving tank: {}",direction);
    }
}

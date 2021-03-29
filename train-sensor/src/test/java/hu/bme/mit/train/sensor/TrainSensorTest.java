package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainUser user;
    TrainController controller;
    TrainSensor sensor;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void AlarmNotRaised() {
        when(controller.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(60);
        Assert.assertEquals(sensor.getSpeedLimit(), 60);
    }

    @Test
    public void AbsoluteMarginHigh() {
        sensor.overrideSpeedLimit(999);
        verify(user).setAlarmState(true);
    }
    @Test
    public void AbsoluteMarginLow() {
        sensor.overrideSpeedLimit(-1972);
        verify(user).setAlarmState(true);
    }
    @Test
    public void RelativeMargin() {
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user).setAlarmState(true);
    }
}

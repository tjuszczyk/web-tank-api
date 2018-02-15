package pl.tj.tanks.controller.usb;

import javax.usb.*;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UsbTankConnector {
    public UsbDevice findDevice(short vendorId, short productId) {

        return findDevice((UsbHub) getUsbRootHub(), vendorId, productId);
    }

    public UsbDevice findDevice(UsbHub hub, short vendorId, short productId) {

        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId)
                return device;
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device, vendorId, productId);
                if (device != null)
                    return device;
            }
        }
        return null;
    }

    public UsbDevice getUsbRootHub() {

        try {
            final UsbServices services = UsbHostManager.getUsbServices();
            return services.getRootUsbHub();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (UsbException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, UsbException {
        System.out.println(new UsbTankConnector().findDevice((short)0x1fd2,(short)0x5007)
        .getParentUsbPort().getPortNumber());
    }
}

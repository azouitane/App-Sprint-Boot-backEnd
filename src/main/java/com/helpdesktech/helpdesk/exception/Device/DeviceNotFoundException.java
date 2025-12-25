package com.helpdesktech.helpdesk.exception.Device;

public class DeviceNotFoundException extends RuntimeException {
  public DeviceNotFoundException(String message) {
    super(message);
  }
}

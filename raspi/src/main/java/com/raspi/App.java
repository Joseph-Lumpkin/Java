package com.raspi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class App {
	
	public static void main(String[] args) {
		
		GpioController gpio = GpioFactory.getInstance();
		
		GpioPinDigitalOutput pinOut = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
		
		while (true) {
			pinOut.high();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pinOut.low();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
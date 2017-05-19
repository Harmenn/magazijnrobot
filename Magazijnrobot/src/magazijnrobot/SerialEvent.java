package magazijnrobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialEvent implements SerialPortEventListener {
	public boolean connected = false;
	private SerialPort serialPort = null;
	private CommPortIdentifier portId = null;
	//private String portName = "COM3";

	public SerialEvent(String portName) {
		@SuppressWarnings("rawtypes")
		Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();

		try {
			while (portId == null && enumComm.hasMoreElements()) {
				CommPortIdentifier currPortId = (CommPortIdentifier) enumComm.nextElement();

				System.out.println(currPortId.getName());
				if (currPortId.getName().equals(portName) || currPortId.getName().startsWith(portName)) {
					System.out.println("Found port");
					serialPort = (SerialPort) currPortId.open("fastDel", 1000);
					portId = currPortId;
					connected = true;
					System.out.println(connected);
					break;
				}

			}
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			TimeUnit.SECONDS.sleep(2);

		} catch (Exception e) {
			System.out.println("Finished With an error");
			//e.printStackTrace();
		}
	}

	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		System.out.println("Received data");
		try {

			BufferedReader input = null;
			if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				if (input == null) {
					input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				}
				String inputLine = input.readLine();
				System.out.println(inputLine);
				String[] splitted = inputLine.split("-");
				switch (splitted[0]) {
				case "tsp":
					if(splitted[1]=="update") {
						if(splitted[2]=="at_location") {
							StartScherm.bpp_connectie.sendMessage("command-arm_out");
						}
					}
					break;
				case "bpp":
					if(splitted[1]=="update") {
						if(splitted[2]=="retrieved_product") {
							if(StartScherm.lastRetrievedProduct==StartScherm.producten.size()-1) {
								System.out.println("Alle producten zijn verzamelt");
								StartScherm.tsp_connectie.sendMessage("left-2000-200");
							} else {
								System.out.println("Haal product "+StartScherm.producten.get(StartScherm.lastRetrievedProduct));
								StartScherm.lastRetrievedProduct++;
								StartScherm.tsp_connectie.sendMessage("getproduct-"+StartScherm.producten.get(StartScherm.lastRetrievedProduct).getX()+"-"+StartScherm.producten.get(StartScherm.lastRetrievedProduct).getY());
							}
						}
						
					}
					break;
				case "commando":

					break;
				default:

					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void sendMessage(String message) {
		try {
			OutputStream output = serialPort.getOutputStream();
			output.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		serialPort.close();
	}
}

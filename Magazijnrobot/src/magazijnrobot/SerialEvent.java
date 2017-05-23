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
import tsp_simulator.Coordinate;

public class SerialEvent implements SerialPortEventListener {
	public boolean connected = false;
	private SerialPort serialPort = null;
	private CommPortIdentifier portId = null;
	// private String portName = "COM3";
	private int fallenProducts = 0;

	public SerialEvent(String portName) {
		@SuppressWarnings("rawtypes")
		Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();

		try {
			while (portId == null && enumComm.hasMoreElements()) {
				CommPortIdentifier currPortId = (CommPortIdentifier) enumComm.nextElement();

				System.out.println(currPortId.getName());
				if (currPortId.getName().equals(portName) || currPortId.getName().startsWith(portName)) {
					System.out.println("Found port " + portName);
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
			//TimeUnit.SECONDS.sleep(2);
			TimeUnit.MILLISECONDS.sleep(500);

		} catch (Exception e) {
			System.out.println("Finished With an error");
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		try {

			BufferedReader input = null;
			if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				if (input == null) {
					input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				}
				String inputLine = input.readLine();
				System.out.println(inputLine);
				String[] splitted = inputLine.split("-");
				/*
				 * for (String s : splitted) { System.out.println(s); }
				 */
				if (splitted[0].equals("tsp")) {
					// System.out.println("DEBUG: REACHED TSP");
					if (splitted[1].equals("update")) {
						if (splitted[2].equals("at_location")) {
							StartScherm.bpp_connectie.sendMessage("command-arm_out");
						} else if (splitted[2].equals("arm_is_up")) {
							System.out.println("Arm is up");
							StartScherm.bpp_connectie.sendMessage("command-arm_in");
						} else if (splitted[2].equals("at_y_3")) {
							StartScherm.tsp_connectie.sendMessage("command-all_left");
						} else if (splitted[2].equals("all_left")) {
							StartScherm.tsp_connectie.sendMessage("command-prepare_sort");
						} else if (splitted[2].equals("ready_to_sort")) {
							//StartScherm.lastRetrievedProduct = 3;
							//System.out.println("there are "+StartScherm.binlist.size()+" bins");
							sortProduct();
							// StartScherm.bpp_connectie.sendMessage("command-arm_all_in");
						}
					}
				} else if (splitted[0].equals("bpp")) {
					// System.out.println("DEBUG: REACHED BPP");
					if (splitted[1].equals("status")) {
						// System.out.println("DEBUG: STATUS OK");
						if (splitted[2].equals("arm_out_ok")) {
							// System.out.println("DEBUG: REACHED ARM-OUT-OK");
							StartScherm.tsp_connectie.sendMessage("command-arm_up");
						} else if (splitted[2].equals("arm_in_ok")) {
							StartScherm.lastRetrievedProduct++;
							if (StartScherm.lastRetrievedProduct == StartScherm.producten.size()) {
								System.out.println("Alle producten zijn verzamelt");
								StartScherm.tsp_connectie.sendMessage("command-y-3");
							} else {
								int lastP = StartScherm.lastRetrievedProduct;// -1;
								System.out.println("Haal product " + StartScherm.producten.get(lastP));
								LiveView.setCurrentCoord(new Coordinate(StartScherm.producten.get(lastP).getX(),
										StartScherm.producten.get(lastP).getY()));
								StartScherm.tsp_connectie
										.sendMessage("getproduct-" + StartScherm.producten.get(lastP).getX() + "-"
												+ StartScherm.producten.get(lastP).getY());
							}
						} else if (splitted[2].equals("sort_succes")) {
							if (fallenProducts != StartScherm.producten.size()) {
								sortProduct();
							} else {
								System.out.println("Done sorting");
							}
						}

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
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

	private void sortProduct() {
		System.out.println("Fallenproducts: "+fallenProducts);
		System.out.println("lastProduct: "+StartScherm.lastRetrievedProduct );
		System.out.println("sorteer product: "+StartScherm.producten.get(StartScherm.lastRetrievedProduct - 1 - fallenProducts));
		for (int i = 0; i < StartScherm.binlist.size(); i++) {
			Bin b = StartScherm.binlist.get(i);
			b.printList();
			System.out.println("at iteration i:"+i);
			if (b.getProducts().contains(StartScherm.producten.get(StartScherm.lastRetrievedProduct - 1 - fallenProducts))) {
				System.out.println("found product in bin");
				if (i == 0) {
					System.out.println("Turn left");
					StartScherm.bpp_connectie.sendMessage("command-rotate_left");
				} else {
					System.out.println("Turn right");
					StartScherm.bpp_connectie.sendMessage("command-rotate_right");
				}
                StartScherm.resultaat.addProduct(StartScherm.producten.get(StartScherm.lastRetrievedProduct - 1 - fallenProducts));
				fallenProducts++;
				System.out.println("___________________");
				return;
			}
		}
		System.out.println("Failed to sort product");
	}
}

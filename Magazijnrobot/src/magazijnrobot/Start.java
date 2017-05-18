package magazijnrobot;

import java.io.IOException;

public class Start {

	public static void main(String[] args) throws IOException {
		SerialEvent event = new SerialEvent("COM3");

		event.sendMessage("commando right");
	}
}

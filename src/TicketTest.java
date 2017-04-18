import java.io.IOException;

import Controller.CinemaSystem;
import Model.*;


public class TicketTest {

	public static void main(String args[]) throws Exception {
		CinemaSystem cs = new CinemaSystem();
		cs.readData();
		Ticket ticket = cs.searchTicket(11111111);
		if(ticket == null) {
			System.out.println("No info");
		} else {
			if(ticket.getAvailable() == true) {
				System.out.println("Already used");
			} else {
				System.out.println("Haven't been used");
				System.out.println(cs.useTicket(11111111, true));
				cs.writeData();
			}
		}
		
	}
	
}

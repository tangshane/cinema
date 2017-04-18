package Controller;

import java.io.*;
import java.util.*;

import Model.*;

public class CinemaSystem {
	// basic
	public ArrayList<Admin> adminList = new ArrayList<Admin>();
	public ArrayList<Film> filmInfoList = new ArrayList<Film>();
	public ArrayList<Screen> screenList = new ArrayList<Screen>();
	public ArrayList<TicketInfo> ticketInfoList = new ArrayList<TicketInfo>();

	// date
	public ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	public ArrayList<Timetable> timetableList = new ArrayList<Timetable>();
	// report and ticketnumber.txt
	
	Screen seats = null;
	
	public void readData() throws Exception {
		adminList.clear();
		Scanner reader = new Scanner(new File("basic/Admin.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			adminList.add(new Admin(s[0], s[1]));
		}
		filmInfoList.clear();
		reader = new Scanner(new File("basic/FilmInfo.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			filmInfoList.add(new Film(s[0], Integer.parseInt(s[1]), s[2]));
		}
		reader = new Scanner(new File("basic/Screen.txt"));
		int line = 0;
		int row = 0;
		int col = 0;
		int number = 0;
		String[][] seat = null;
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			if(line == 0) {
				number = Integer.parseInt(s[0]);
				row = Integer.parseInt(s[1]);
				col = Integer.parseInt(s[2]);
				seat = new String[row][col];
			}	
			else {
				for(int i = 0; i<s.length; i++) {
					seat[line-1][i] = s[i];
				}
			}
			line++;
			if(line == row + 1) {
				line = 0;
				screenList.add(new Screen(number, row, col, seat));				
			}
		}
		ticketInfoList.clear();
		reader = new Scanner(new File("basic/TicketInfo.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			ticketInfoList.add(new TicketInfo(Integer.parseInt(s[0]), s[1], Boolean.parseBoolean(s[2]), Integer.parseInt(s[3])));
		}
		ticketList.clear();
		reader = new Scanner(new File(getCurrentDatePath()+"/Ticket.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			ticketList.add(new Ticket(Integer.parseInt(s[0]), Boolean.parseBoolean(s[1])));
		}
		timetableList.clear();
		reader = new Scanner(new File(getCurrentDatePath()+"/Timetable.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			timetableList.add(new Timetable(Integer.parseInt(s[0]), s[1], s[2]));
		}
		reader.close();
	}

	public boolean addAccount(String id, String password) throws Exception {
		readData();
		Admin admin = new Admin(id, password);
		adminList.add(admin);
		writeData();	
		return true;
	}

	public int searchAdmin(String id, String password) throws Exception{
		readData();
		for(int i = 0; i<adminList.size();i++){
			if(adminList.get(i).getID().equals(id) && adminList.get(i).getPassword().equals(password)){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteAccount(String id, String password) throws Exception {
		readData();
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateAdmin(String id, String password) throws Exception {
		readData();
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.set(index, new Admin(id, password));
		return true;
	}
	
	public boolean addFilm(String name, int runtime, String poster) throws Exception {
		readData();
		Film film = new Film(name, runtime, poster);
		filmInfoList.add(film);
		writeData();	
		return true;
	}

	public int searchFilm(String name) throws Exception{
		readData();
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteFilm(String name) throws Exception {
		readData();
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateFilm(String name, int runtime, String poster) throws Exception {
		readData();
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.set(index, new Film(name, runtime, poster));
		return true;
	}

	public boolean addTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		readData();
		TicketInfo ticket = new TicketInfo(type, description, IDRequired, discount);
		ticketInfoList.add(ticket);
		writeData();	
		return true;
	}

	public int searchTicketInfo(int type) throws Exception{
		readData();
		for(int i = 0; i<ticketInfoList.size();i++){
			if(ticketInfoList.get(i).getType() == type){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteTicketInfo(int type) throws Exception {
		readData();
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		readData();
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.set(index, new TicketInfo(type, description, IDRequired, discount));
		return true;
	}
	
	public boolean addTimetable(int screen, String filmName, String showtime) throws Exception {
		readData();
		Timetable timetable = new Timetable(screen, filmName, showtime);
		timetableList.add(timetable);
		writeData();	
		return true;
	}

	public int searchTimetable(int screen, String filmName, String showtime) throws Exception{
		readData();
		for(int i = 0; i<timetableList.size();i++){
			Timetable timetable = timetableList.get(i);
			if(timetable.getScreen() == screen && timetable.getFilmName().equals(filmName) && timetable.getShowtime().equals(showtime)){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteTimetable(int screen, String filmName, String showtime) throws Exception {
		readData();
		int index = searchTimetable(screen, filmName, showtime);
		if(index == -1) {
			return false;
		}
		timetableList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateTimetable(int screen, String filmName, String showtime) throws Exception {
		readData();
		int index = searchTimetable(screen, filmName, showtime);
		if(index == -1) {
			return false;
		}
		timetableList.set(index, new Timetable(screen, filmName, showtime));
		return true;
	}
	
	public boolean searchScreen(String name, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+name+"/"+showtime+".txt"));
		int line = 0;
		int row = 0;
		int col = 0;
		int number = 0;
		String[][] seat = null;
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			if(line == 0) {
				number = Integer.parseInt(s[0]);
				row = Integer.parseInt(s[1]);
				col = Integer.parseInt(s[2]);
				seat = new String[row][col];
			}	
			else {
				for(int i = 0; i<s.length; i++) {
					seat[line-1][i] = s[i];
				}
			}
			line++;
			if(line == row + 1) {
				seats = new Screen(number, row, col, seat);		
				break;
			}
		}
		if(seats == null)
			return false;
		else
			return true;
	}
	
	public Ticket searchTicket(int number) {
		for(int i = 0; i<ticketList.size(); i++) {
			if(ticketList.get(i).getNumber() == number)
				return ticketList.get(i);
		}
		return null;
	}
	
	public boolean useTicket(int number, boolean IDRequire) throws IOException {
		if(IDRequire == false)
			return false;
		for(int i = 0; i<ticketList.size(); i++) {
			if(ticketList.get(i).getNumber() == number) {
				Ticket ticket = new Ticket(number, true);
				ticketList.set(i, ticket);
				break;
			}
		}
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+number+".txt"));
		String[] s = reader.nextLine().split("]]]]");
		Ticket ticket = new Ticket(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Boolean.parseBoolean(s[2]), 
								s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), true);
		reader.close();
		BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+number+".txt"));
		output.write(ticket.toString() + "\r\n");
		output.close();
		return true;
	}
	
	public void writeData() throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter("basic/Admin.txt"));
		for (int i = 0; i < adminList.size(); i++) {
			output.write(adminList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter("basic/FilmInfo.txt"));
		for (int i = 0; i < filmInfoList.size(); i++) {
			output.write(filmInfoList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter("basic/Screen.txt"));
		for (int i = 0; i < screenList.size(); i++) {
			output.write(screenList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter("basic/TicketInfo.txt"));
		for (int i = 0; i < ticketInfoList.size(); i++) {
			output.write(ticketInfoList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/Ticket.txt"));
		for (int i = 0; i < ticketList.size(); i++) {
			output.write(ticketList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/Timetable.txt"));
		for (int i = 0; i < timetableList.size(); i++) {
			output.write(timetableList.get(i).toString() + "\r\n");
		}
		output.close();
	}
	
	public String getCurrentDatePath() {
		Date date = new Date();
		String path = String.format("%4d%02d%02d", 1900+date.getYear(), date.getMonth()+1, date.getDate());
		return path;
	}
//	
//	public static void main(String args[]) throws IOException {
//		CinemaSystem cs = new CinemaSystem();
//		try {
//			cs.readData();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(int i = 0; i<cs.ticketInfoList.size(); i++) {
//			if(cs.ticketInfoList.get(i).getType() == 1)
//				System.out.print("Child\t");	
//			else if(cs.ticketInfoList.get(i).getType() == 2)
//				System.out.print("Adult\t");	
//			else if(cs.ticketInfoList.get(i).getType() == 3)
//				System.out.print("Senior\t");	
//			else if(cs.ticketInfoList.get(i).getType() == 4)
//				System.out.print("Student\t");	
//			System.out.print(cs.ticketInfoList.get(i).getDescription()+"\t");
//			if(cs.ticketInfoList.get(i).isIDRequired() == false)
//				System.out.print("None\t");
//			else
//				System.out.print("Student ID\t");
//			if(cs.ticketInfoList.get(i).getDiscount() == 0)
//				System.out.println("None");
//			else
//				System.out.println(cs.ticketInfoList.get(i).getDiscount()+"%");
//		}
//		
//		for(int i = 0; i<cs.filmInfoList.size(); i++) {
//			System.out.println(cs.filmInfoList.get(i).getName() + "\t" + cs.filmInfoList.get(i).getRuntime()+" min"); 
////			File sourceimage = new File("basic/poster/" + cs.filmInfoList.get(i).getPoster());
////			Image image = ImageIO.read(sourceimage);
////			JFrame frame = new JFrame();
////		    JLabel label = new JLabel(new ImageIcon(image));
////		    frame.getContentPane().add(label, BorderLayout.CENTER);
////		    frame.pack();
////		    frame.setVisible(true); 
////		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		}
//		System.out.println(cs.ticketList.size());
//		for(int i = 0; i<cs.ticketList.size(); i++) {
//			System.out.println(cs.ticketList.get(i));
//		}
//	}
}

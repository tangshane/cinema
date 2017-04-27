package Controller;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import Model.*;
import View.*;


/**
 * Control switching frame and read or write data
 * @author Yunyao Liu
 * @author Zhenhao Li
 * @author Yuqian Li
 * @author Zhekong Yang
 * @author Zheng Dong
 * @author Yuqin Cui
 * @version v1.0
 */
public class CinemaSystem {
	private static final int FEE = 16;
	// check gate frame
	private CheckGate checkgate;
	private CheckTicket checkticket;
	// kiosk frame
	private KioskWelcome kioskwelcome;
	private KioskFilm kioskfilm;
	private KioskTime kiosktime;
	private KioskTicket kioskticket;
	private KioskSeat kioskseat;
	private KioskPay kioskpay;
	private KioskFinish kioskfinish;
	// manage frame
	private AdminLogin adminlogin;
	private AdminManage adminmanage;
	private AdminTimetable admintimetable;
	private AdminReport adminreport;
	
	// kiosk info
	private Film currentfilm;
	private Timetable currenttimetable;
	private TicketInfo currentticketinfo;
	private int[] currentrow;
	private int[] currentcol;
	private int occupy;
	private int number;
	private Ticket[] newticket;
	
	// basic info
	private ArrayList<Admin> adminList = new ArrayList<Admin>();
	private ArrayList<Film> filmInfoList = new ArrayList<Film>();
	private ArrayList<Screen> screenList = new ArrayList<Screen>();
	private ArrayList<TicketInfo> ticketInfoList = new ArrayList<TicketInfo>();
	private Ticket currentticket;
	private Ticket briefCurrentTicket;
	private ArrayList<Ticket> briefTicketList = new ArrayList<Ticket>();
	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	private ArrayList<Timetable> timetableList = new ArrayList<Timetable>();
	public StringBuilder reportContent = new StringBuilder();
	
	Screen seats = null;
	
	/*
	 * constructor
	 */
	public CinemaSystem() {
		currentticket = new Ticket();
		checkgate = new CheckGate(this);
		checkticket = new CheckTicket(this);
		kioskwelcome = new KioskWelcome(this);
		kioskfilm = new KioskFilm(this);
		kiosktime = new KioskTime(this);
		kioskticket = new KioskTicket(this);
		kioskseat = new KioskSeat(this);
		kioskpay = new KioskPay(this);
		kioskfinish = new KioskFinish(this);
		adminlogin = new AdminLogin(this);
		adminmanage = new AdminManage(this);
		admintimetable = new AdminTimetable(this);
		adminreport = new AdminReport(this);
		currentfilm = new Film();
		currenttimetable = new Timetable();
		currentticketinfo = new TicketInfo();
		occupy = 0;
		number = 0;
		currentrow = new int[0];
		currentcol = new int[0];
	}
	
	public void setCurrentFilm(Film currentfilm) {
		this.currentfilm = currentfilm;
	}
	public Film getCurrentFilm() {
		return this.currentfilm;
	}
	
	public void setCurrentTimetable(Timetable currenttimetable) {
		this.currenttimetable = currenttimetable;
	}
	
	public void setCurrentTicketInfo(TicketInfo currentticketinfo) {
		this.currentticketinfo = currentticketinfo;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Read data from Basic folder and current/previous date folder as several array list
	 * @param current whether read data from current date folder or not
	 * @throws Exception 
	 */
	public void readData(boolean current) throws Exception {
		String datePath = "";
		if(current) {
			datePath = getCurrentDatePath();
		} else {
			datePath = getPreviousDatePath();
		}
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
		screenList.clear();
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
		briefTicketList.clear();
		reader = new Scanner(new File(datePath+"/Ticket.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			briefTicketList.add(new Ticket(Integer.parseInt(s[0]), Boolean.parseBoolean(s[1])));
		}
		timetableList.clear();
		reader = new Scanner(new File(datePath+"/Timetable.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			timetableList.add(new Timetable(Integer.parseInt(s[0]), s[1], s[2]));
		}
		reader.close();
	}
	
	/**
	 * add admin account
	 * @param id administrator username
	 * @param password administrator password
	 * @return whether add account successfully or not
	 * @throws Exception
	 */
	public boolean addAccount(String id, String password) throws Exception {
		Admin admin = new Admin(id, password);
		adminList.add(admin);
		writeData(true);	
		return true;
	}

	/**
	 * search admin as a login method for administrator
	 * @param id administrator username
	 * @param password administrator password
	 * @return the position the administrator in array list
	 * @throws Exception
	 */
	public int searchAdmin(String id, String password) throws Exception{
		for(int i = 0; i<adminList.size();i++){
			if(adminList.get(i).getID().equals(id) && adminList.get(i).getPassword().equals(password)){
				return i;				
			}
		}
		return -1;
	}
	
	/**
	 * delete an administrator account from system
	 * @param id administrator username
	 * @param password administrator password
	 * @return whether administrator account deleted successfully
	 * @throws Exception
	 */
	public boolean deleteAccount(String id, String password) throws Exception {
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.remove(index);
		writeData(true);	
		return true;
	}
	
	/**
	 * This method is to update username or password of administrator account
	 * @param id administrator username
	 * @param password administrator password
	 * @return whether administrator account udpated successfully
	 * @throws Exception
	 */
	public boolean updateAdmin(String id, String password) throws Exception {
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.set(index, new Admin(id, password));
		return true;
	}
	
	/**
	 * add a new film to film list
	 * @param name film name
	 * @param runtime film runtime record in minutes
	 * @param poster film poster filename in *.jpg
	 * @return whether add new film successfully
	 * @throws Exception
	 */
	public boolean addFilm(String name, int runtime, String poster) throws Exception {
		Film film = new Film(name, runtime, poster);
		filmInfoList.add(film);
		writeData(true);	
		return true;
	}

	/**
	 * search a film according to film name
	 * @param name film name
	 * @return the position in film list
	 * @throws Exception
	 */
	public int searchFilm(String name) throws Exception{
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return i;				
			}
		}
		return -1;
	}
	
	/**
	 * get brief information according to film name
	 * @param name film name
	 * @return the brief information of specific film
	 * @throws Exception
	 */
	public Film getFilm(String name) throws Exception{
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return filmInfoList.get(i);				
			}
		}
		return null;
	}
	
	/**
	 * delete film according film name
	 * @param name film name
	 * @return whether delete film successfully
	 * @throws Exception
	 */
	public boolean deleteFilm(String name) throws Exception {
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.remove(index);
		writeData(true);	
		return true;
	}
	
	/**
	 * update film name or runtime or poster
	 * @param name film name
	 * @param runtime film runtime
	 * @param poster film poster file name
	 * @return whether update information of film successfully
	 * @throws Exception
	 */
	public boolean updateFilm(String name, int runtime, String poster) throws Exception {
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.set(index, new Film(name, runtime, poster));
		return true;
	}

	/**
	 * add a new type ticket 
	 * @param type ticket type id
	 * @param description ticket brief description
	 * @param IDRequired when in the check gate, ID required check sometimes
	 * @param discount special discount of some type ticket
	 * @return add a new ticket successfully
	 * @throws Exception
	 */
	public boolean addTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		TicketInfo ticket = new TicketInfo(type, description, IDRequired, discount);
		ticketInfoList.add(ticket);
		writeData(true);	
		return true;
	}

	/**
	 * search a type ticket according to its type id
	 * @param type type id
	 * @return the position in ticket list
	 * @throws Exception
	 */
	public int searchTicketInfo(int type) throws Exception{
		for(int i = 0; i<ticketInfoList.size();i++){
			if(ticketInfoList.get(i).getType() == type){
				return i;				
			}
		}
		return -1;
	}
	
	/**
	 * delete a type of ticket from document
	 * @param type type id
	 * @return whether ticket deleted successfully
	 * @throws Exception
	 */
	public boolean deleteTicketInfo(int type) throws Exception {
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.remove(index);
		writeData(true);	
		return true;
	}
	
	/**
	 * update specific ticket information
	 * @param type type id
	 * @param description brief information of ticket
	 * @param IDRequired when in the check gate, ID required check sometimes
	 * @param discount special discount of some type of tickets
	 * @return whether update ticket information successfully
	 * @throws Exception
	 */
	public boolean updateTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.set(index, new TicketInfo(type, description, IDRequired, discount));
		return true;
	}
	
	/**
	 * get today timetable from document
	 * @param filter whether filter some timetable before current time, or read the whole timetable
	 * @return a array list of timetable
	 */
	public ArrayList<Timetable> getTimetable(boolean filter) {
		if(filter == true) {
			ArrayList<Timetable> timetable = new ArrayList<Timetable>();
			Date d = new Date();
			int hour = d.getHours();
			int min = d.getMinutes();
			for(int i = 0; i<timetableList.size(); i++) {
				Timetable t = timetableList.get(i);
				if(compareTime(d.getHours(), d.getMinutes(), t.getShowtime()) == -1) {
					timetable.add(t);
				}
			}
			return timetable;
		}
		return timetableList;	
	}
	
	/**
	 * add a new timetable. It will fail when two time slots overlapped.
	 * @param screen screen id
	 * @param filmName film name
	 * @param showtime show time
	 * @return whether the new timetable can be added successfully
	 * @throws Exception
	 */
	public boolean addTimetable(int screen, String filmName, String showtime) throws Exception {
		for(int i = 0; i<timetableList.size(); i++) {
			Timetable timetable = timetableList.get(i);
			if(screen == timetable.getScreen()) {
				Film targetfilm = getFilm(timetable.getFilmName());
				Film film = getFilm(filmName);
				String starttime = showtime;
				String endtime = addTime(showtime, film.getRuntime());
				String targetstart = timetable.getShowtime();
				String targetend = addTime(timetable.getShowtime(), targetfilm.getRuntime());
//				System.out.println("Want Add Time: " + starttime + " to " + endtime);
//				System.out.println("Current  Time: " + targetstart + " to " + targetend);
//				if(compareTime(endtime, targetstart) ==1)
//					System.out.println("Endtime too late. Overlap");
//				if(compareTime(starttime, targetend) ==-1)
//					System.out.println("Starttime too early. Overlap");
				if(compareTime(targetstart, endtime) != -1 || compareTime(targetend, starttime) != 1)
					continue;
				else {
					return false;
				}				
			}
		}
		Timetable timetable = new Timetable(screen, filmName, showtime);
		timetableList.add(timetable);
		createFile(getCurrentDatePath(), filmName, screen+" "+showtime);
		BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+filmName+"/"+screen+" "+showtime+".txt"));
		output.write(createSeat(screen).toString());
		output.close();
		writeData(true);	
		return true;
	}

	/**
	 * search a specific timetable according to screen id, film name and show time
	 * @param screen screen id
	 * @param filmName film name
	 * @param showtime show time
	 * @return the position of that timetable 
	 * @throws Exception
	 */
	public int searchTimetable(int screen, String filmName, String showtime) throws Exception{
		readData(true);
		for(int i = 0; i<timetableList.size();i++){
			Timetable timetable = timetableList.get(i);
			if(timetable.getScreen() == screen && timetable.getFilmName().equals(filmName) && timetable.getShowtime().equals(showtime)){
				return i;				
			}
		}
		return -1;
	}
	
	/**
	 * delete timetable according screen id, film name and show time
	 * @param screen screen id
	 * @param filmName film name
	 * @param showtime show time
	 * @return whether that timetable deleted successfully
	 * @throws Exception
	 */
	public boolean deleteTimetable(int screen, String filmName, String showtime) throws Exception {
		int index = searchTimetable(screen, filmName, showtime);
		if(index == -1) {
			return false;
		}
		timetableList.remove(index);
		writeData(true);	
		deleteFile(getCurrentDatePath(), filmName, showtime);  
		readData(true);
		return true;
	}
	
	/**
	 * update timetable details
	 * @param screen screen id
	 * @param filmName film name
	 * @param showtime show time
	 * @return whether timetable details updated successfully
	 * @throws Exception
	 */
	public boolean updateTimetable(int screen, String filmName, String showtime) throws Exception {
		readData(true);
		int index = searchTimetable(screen, filmName, showtime);
		if(index == -1) {
			return false;
		}
		timetableList.set(index, new Timetable(screen, filmName, showtime));
		return true;
	}
	
	/**
	 * search screen according to film name and show time
	 * @param filmname film name
	 * @param showtime show time
	 * @return whether screen existed
	 * @throws FileNotFoundException
	 */
	public boolean searchScreen(String filmname, int screen, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+screen+" "+showtime+".txt"));
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
	
	/**
	 * get the number of empty seats from specific timetable
	 * @param filmname film name
	 * @param showtime show time
	 * @return the number of empty seats
	 * @throws FileNotFoundException
	 */
	public int availableScreen(String filmname, int screen, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+screen+" "+showtime+".txt"));
		int line = 0;
		int row = 0;
		int col = 0;
		int empty = 0;
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			if(line == 0) {
				number = Integer.parseInt(s[0]);
				row = Integer.parseInt(s[1]);
				col = Integer.parseInt(s[2]);
			}	
			else {
				for(int i = 0; i<s.length; i++) {
					if(s[i].equals("0")) {
						empty++;
					}
				}
			}
			line++;
			if(line == row + 1) {
				break;
			}
		}
		return empty;
	}
	
	/**
	 * select specific seat from screen according to filmname, showtime, seatrow, seatcol
	 * @param filmname film name
	 * @param showtime show time
	 * @param seatrow row number of seat
	 * @param seatcol column number of seat
	 * @return whether seat selected successfully
	 * @throws IOException
	 */
	public boolean updateScreen(String filmname, int screen, String showtime, int seatrow, int seatcol) throws IOException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+screen+" "+showtime+".txt"));
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
					if(line-1 == seatrow && i == seatcol) {
						seat[line-1][i] = "1";		
					} else {
						seat[line-1][i] = s[i];											
					}
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
		else {
			BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+filmname+"/"+screen+" "+showtime+".txt"));
			output.write(seats.toString());
			output.close();
			return true;
		}
	}
	
	/**
	 * create an empty seat table of specific screen
	 * @param screen screen id
	 * @return an empty seat table
	 */
	public Screen createSeat(int screen) {
		String[][] screenseat = null;
		String[][] seat = null;
		int row = 0;
		int col = 0;
		for(int i = 0; i<screenList.size(); i++) {
			if(screenList.get(i).getNumber() == screen) {
				screenseat = screenList.get(i).getSeat();
				row = screenList.get(i).getRow();
				col = screenList.get(i).getCol();
				seat = new String[row][col];
			}
		}
		for(int i = 0; i<screenseat.length; i++) {
			for(int j = 0; j<screenseat[0].length; j++) {
				if(screenseat[i][j].equals("NULL")) {
					seat[i][j] = "NULL";
				} else {
					seat[i][j] = "0";
				}
			}
		}
		return new Screen(screen, row, col, seat);
	}
	
	/**
	 * search ticket according to ticket number
	 * @param number ticket number
	 * @return a brief information of ticket
	 */
	public Ticket searchTicket(int number) {
		for(int i = 0; i<briefTicketList.size(); i++) {
			if(briefTicketList.get(i).getNumber() == number)
				return briefTicketList.get(i);
		}
		return null;
	}
	
	/**
	 * create a new ticket 
	 * @param type ticket type
	 * @param IDRequired ticket ID required
	 * @param film film name
	 * @param showtime showtime
	 * @param screen screen id
	 * @param row row number of seat
	 * @param col column number of seat
	 * @return a new brief ticket 
	 */
	public Ticket createTicket(int type, boolean IDRequired, String film, String showtime, int screen, int row, int col){
		Ticket ticket = new Ticket();
		int ticketno = ticket.generateTicketNumber();
		while(searchTicket(ticketno) != null) {
			ticketno = ticket.generateTicketNumber();
		}
		briefCurrentTicket = new Ticket(ticketno, false);
		briefTicketList.add(briefCurrentTicket);
		currentticket = new Ticket(ticketno, type, IDRequired, film, showtime, screen, row, col, false);
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+ticketno+".txt"));
			output.write(currentticket.toString());
			output.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			updateScreen(film, screen, showtime, row, col);
			writeData(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentticket;
	}
	
	/**
	 * get today's specific ticket or yesterday's specific ticket 
	 * @param number ticket number
	 * @param current read from current document folder or previous document folder
	 * @throws FileNotFoundException
	 */
	public void getTicket(String number, boolean current) throws FileNotFoundException {
		String datePath = "";
		if(current) {
			datePath = getCurrentDatePath();
		} else {
			datePath = getPreviousDatePath();
		}
		Scanner reader = new Scanner(new File(datePath+"/"+number+".txt"));
		String[] s = reader.nextLine().split("]]]]");
		currentticket = new Ticket(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Boolean.parseBoolean(s[2]), 
								s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), true);
		reader.close();
	}
	
	/**
	 * use a ticket at check gate
	 * @param no ticket number
	 * @param IDRequire ID required check at check gate
	 * @return whether ticket used successfully 
	 * @throws IOException
	 */
	public boolean useTicket(String no, boolean IDRequire) throws IOException {
		if(IDRequire == false) {
			JOptionPane.showMessageDialog(null, "Please check ID!", "Alert", JOptionPane.ERROR_MESSAGE); 			
			return false;
		}
		int number = Integer.parseInt(no);
		for(int i = 0; i<briefTicketList.size(); i++) {
			if(briefTicketList.get(i).getNumber() == number) {
				Ticket ticket = new Ticket(number, true);
				briefTicketList.set(i, ticket);
				break;
			}
		}
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+number+".txt"));
		String[] s = reader.nextLine().split("]]]]");
		currentticket = new Ticket(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Boolean.parseBoolean(s[2]), 
								s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), true);
		reader.close();
		BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+number+".txt"));
		output.write(currentticket.toString() + "\r\n");
		output.close();
		writeData(true);
		JOptionPane.showMessageDialog(null, "Door open, enjoy your film!", "Alert", JOptionPane.INFORMATION_MESSAGE); 			
		gotoGate();
		return true;
	}
	
	/**
	 * write data into document
	 * @param current write to current document folder or previous document folder
	 * @throws IOException
	 */
	public void writeData(boolean current) throws IOException {
		String datePath = "";
		if(current) {
			datePath = getCurrentDatePath();
		} else {
			datePath = getPreviousDatePath();
		}
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
		output = new BufferedWriter(new FileWriter(datePath+"/Ticket.txt"));
		for (int i = 0; i < briefTicketList.size(); i++) {
			output.write(briefTicketList.get(i).toString() + "\r\n");
		}
		output.close();
		output = new BufferedWriter(new FileWriter(datePath+"/Timetable.txt"));
		for (int i = 0; i < timetableList.size(); i++) {
			output.write(timetableList.get(i).toString() + "\r\n");
		}
		output.close();
	}
	
	/**
	 * get current date
	 * @return current date as format yyyyMMdd
	 */
	public String getCurrentDatePath() {
		Date date = new Date();
		String path = String.format("%4d%02d%02d", 1900+date.getYear(), date.getMonth()+1, date.getDate());
		return path;
	}
	
	/**
	 * get previous date
	 * @return previous date as format yyyyMMdd
	 */
	public String getPreviousDatePath() {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1);    //得到前一天
        String  yestedayDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return yestedayDate;
	}
	
	/**
	 * calculate time
	 * @param time start time
	 * @param delta delta time
	 * @return end time
	 */
	public String addTime(String time, int delta) {
		int hour = Integer.parseInt(time.substring(0,2));
		int min = Integer.parseInt(time.substring(2));
		int deltaMin = delta;
		int newMin = (hour * 60 + min + deltaMin) % 60;
		int newHour = (hour * 60 + min + deltaMin) / 60;
		return String.format("%04d", Integer.parseInt(newHour + "" + newMin));
	}
	
	/**
	 * compare two time
	 * @param timeA 
	 * @param timeB
	 * @return whether timeA earlier than timeB, timeA later than timeB
	 */
	public int compareTime(String timeA, String timeB) {
		int hourA = Integer.parseInt(timeA.substring(0, 2));
		int hourB = Integer.parseInt(timeB.substring(0, 2));
		int minA = Integer.parseInt(timeA.substring(2)); 
		int minB = Integer.parseInt(timeB.substring(2));
		int newTimeA = hourA * 60 + minA;
		int newTimeB = hourB * 60 + minB;
		if(newTimeA > newTimeB)
			return 1;
		else if(newTimeA < newTimeB)
			return -1;
		else
			return 0;
	}
	
	/**
	 * create file 
	 * @param date specific date
	 * @param film film name
	 * @param file file name
	 * @return whether create file successfully
	 */
	public boolean createFile(String date, String film, String file) {
        String separator = File.separator;
        String directory = date + separator + film;
        String fileName = file + ".txt";
        File f = new File(directory,fileName);
        if(!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
        return false;
    }
	
	/**
	 * delete file
	 * @param date specific date 
	 * @param film film name
	 * @param file file name
	 * @return whether file deleted successfully
	 */
	public boolean deleteFile(String date, String film, String file){     
		String separator = File.separator;
        String directory = date + separator + film;
        String fileName = file + ".txt";
        File f = new File(directory,fileName);
        if(f.isFile() && f.exists()){     
            f.delete();     
            return true;     
        }else{     
            return false;     
        }     
    }    
	
	/**
	 * get type from type id
	 * @param type type id
	 * @return type
	 */
	public String convertType(int type) {
		String tickettype = "";
		switch(type) {
        case 1:
        		tickettype = "Child";
			break;
		case 2:
			tickettype = "Adult";
			break;
		case 3:
			tickettype = "Senior";
			break;
		case 4:
			tickettype = "Student";
			break;
		}
		return tickettype;
	}
	
	/**
	 * convert time from hhmm to hh:mm
	 * @param time current format as hhmm
	 * @return new format as hh:mm
	 */
	public String convertTime(String time) {
		String showtime = new String();
		showtime = time.substring(0,2) + ":" + time.substring(2);
		return showtime;
	}
	
	/**
	 * extract time format hhmm from format hh:mm
	 * @param time format hh:mm
	 * @return format hhmm
	 */
	public String extractTime(String time) {
		int index = time.indexOf(":");
		return time.substring(0, index) + time.substring(index+1);
	}
	
	/**
	 * compare two time
	 * @param hourA target hours
	 * @param minA target minutes
	 * @param timetable current timetable
	 * @return which one is earlier
	 */
	public int compareTime(int hourA, int minA, String timetable) {
		int hourB = Integer.parseInt(timetable.substring(0,2));
		int minB = Integer.parseInt(timetable.substring(2));
		if(hourA < hourB) {
			return -1;			
		} else if(hourA == hourB){
			if(minA < minB)
				return -1;
			else 
				return 1;
		} else {
			return 1;
		}
	}	
	
	/**
	 * create seat format as one character and one digit
	 * @param totalrow total row in that screen
	 * @param totalcol total column in that screen
	 * @param row row number of seat
	 * @param col column number of seat
	 * @return new seat format
	 */
	public String convertSeat(int totalrow, int totalcol, int row, int col) {
		String seat = new String();
		seat = (char)((totalrow-1-row) + 65) + "";
		seat = seat + (col+1);
		return seat;
	}
	
	/**
	 * extract row and col of seat 
	 * @param seat seat format which has one letter and one digit
	 * @return row and col
	 */
	public int[] extractSeat(String seat) {
		int row = seats.getRow() - 1 - (seat.charAt(0) - 65);
		int col = Integer.parseInt(seat.substring(1))-1;
		int[] res = new int[]{row, col};
		return res;
	}
	
	/**
	 * create a report of yesterday
	 * @throws IOException
	 */
	public void createReport() throws IOException {
		File f = new File(getCurrentDatePath()+"/"+"Ticket.txt");
		if(f.exists()) {
			return ;
		}
		createFile(getCurrentDatePath(), "", "Ticket");
		createFile(getCurrentDatePath(), "", "Timetable");
		try {
			readData(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i<briefTicketList.size(); i++) {
			getTicket(briefTicketList.get(i).getNumber()+"", false);
			ticketList.add(currentticket);
		}
		Report r = new Report(filmInfoList.size(), ticketInfoList.size());
		String[] film = new String[filmInfoList.size()];
		for(int i = 0; i<filmInfoList.size(); i++) {
			film[i] = filmInfoList.get(i).getName();
		}
		r.setFilm(film);
		String[] type = new String[ticketInfoList.size()];
		for(int i = 0; i<ticketInfoList.size(); i++) {
			type[i] = convertType(ticketInfoList.get(i).getType());
		}
		r.setType(type);
		int totalticket = 0;
		int totalmount = 0;
		int[] salePerType = new int[ticketInfoList.size()];
		for(int i = 0; i<ticketList.size(); i++) {
			for(int j = 0; j<type.length; j++) {
				if(convertType(ticketList.get(i).getType()).equals(type[j])) {
					salePerType[j] = salePerType[j] + 1;
					totalmount += FEE * (100 - ticketInfoList.get(j).getDiscount()) / 100.0;
					totalticket += 1;
				} 
			}
		}
		r.setSalePerType(salePerType);
		int[] salePerFilm = new int[filmInfoList.size()];
		for(int i = 0; i<ticketList.size(); i++) {
			for(int j = 0; j<film.length; j++) {
				if(ticketList.get(i).getFilm().equals(film[j])) {
					salePerFilm[j] = salePerFilm[j] + 1;
				} 
			}
		}
		r.setSalePerFilm(salePerFilm);
		r.setTotalTicketNumber(totalticket);
		r.setTotalTicketAmount(totalmount);
		BufferedWriter output = new BufferedWriter(new FileWriter(getPreviousDatePath()+"/Report.txt"));
		output.write(r.toString());
		output.close();
		writeData(false);
	}

	/**
	 * Get report content
	 * @param current read from current date or previous date file folder
	 * @throws FileNotFoundException
	 */
	public void getReport(boolean current) throws FileNotFoundException {
		String datePath = "";
		if(current) {
			datePath = getCurrentDatePath();
		} else {
			datePath = getPreviousDatePath();
		}
		Scanner reader = new Scanner(new File(datePath+"/Report.txt"));
		while(reader.hasNextLine()) {
			reportContent.append(reader.nextLine() + "\n");			
		}
		reader.close();
	}
	
	/**
	 * switch to check gate frame
	 */
	public void gotoGate() {
		checkticket.setVisible(false);
		checkgate.setLocationRelativeTo(null);
		checkgate.setVisible(true);
	}
	
	/**
	 * switch to check ticket frame
	 * @param number wanted ticket number
	 */
	public void GateToTicket(String number) {
		try {
			readData(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(number.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter ticket number!", "Alert", JOptionPane.ERROR_MESSAGE); 			
			return;
		}	
		int ticketNumber = Integer.parseInt(number);
		Ticket ticket = searchTicket(ticketNumber);
		if(ticket == null) {
			JOptionPane.showMessageDialog(null, "No such a ticket!", "Alert", JOptionPane.ERROR_MESSAGE); 
		} else if(ticket.getAvailable() == true) {
			JOptionPane.showMessageDialog(null, "The ticket has been used!", "Alert", JOptionPane.ERROR_MESSAGE);
		} else {
			checkgate.setVisible(false);
			try {
				getTicket(number, true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String type = convertType(currentticket.getType());
			checkticket.number.setText(currentticket.getNumber()+"");
			checkticket.type.setText(type);
			checkticket.film.setText(currentticket.getFilm());
			checkticket.showtime.setText(convertTime(currentticket.getShowtime()));
			checkticket.screen.setText(currentticket.getScreen()+"");
			try {
				searchScreen(currentticket.getFilm(), currentticket.getScreen(), currentticket.getShowtime());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			checkticket.seat.setText(convertSeat(seats.getRow(), seats.getCol(), currentticket.getRow(), currentticket.getCol()));
			if(currentticket.getIDRequired() == false) {
				checkticket.checkID.setEnabled(false);
				checkticket.checkID.repaint();
			} else {
				checkticket.checkID.setEnabled(true);
				checkticket.checkID.repaint();
			}
			checkticket.setLocationRelativeTo(null);
			checkticket.setVisible(true);
		}
	}
	
	/**
	 * switch to kiosk welcome frame
	 */
	public void gotoWelcome() {
		kioskfilm.setVisible(false);
		kioskpay.setVisible(false);
		kioskwelcome.setLocationRelativeTo(null);
		kioskwelcome.setVisible(true);
	}
	
	/**
	 * switch to kiosk film frame
	 */
	public void gotoFilm() {
		kioskwelcome.setVisible(false);
		kiosktime.setVisible(false);
		String[] content = new String[filmInfoList.size()];
		for(int i = 0; i<filmInfoList.size(); i++) {
			content[i] = filmInfoList.get(i).getName();
		}
		kioskfilm.filminfoModel = new SpinnerListModel(content);
		kioskfilm.filminfo.setModel(kioskfilm.filminfoModel);
		kioskfilm.filminfo.setEditor(new JSpinner.DefaultEditor(kioskfilm.filminfo));
		kioskfilm.filminfo.repaint();
		Vector<String> title = new Vector<String>();// 列名
		title.add("Film"); title.add("Runtime"); title.add("Poster");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
		for (int i = 0; i < filmInfoList.size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            Film f = filmInfoList.get(i);  
            ImageIcon icon = new ImageIcon("basic/poster/" + f.getPoster());//图片处理  
            icon.setImage(icon.getImage().getScaledInstance(80,100,Image.SCALE_DEFAULT));  
            Image img = icon.getImage();  
            v.add(f.getName());  
            v.add(f.getRuntime() + " min");
            v.add(img);  
            data.add(v);  
        } 
        kioskfilm.infoModel = new DefaultTableModel(data, title);  
        kioskfilm.info.setModel(kioskfilm.infoModel);
        kioskfilm.info.repaint();
        kioskfilm.info.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
		kioskfilm.setLocationRelativeTo(null);
		kioskfilm.setVisible(true);
	}
	
	/**
	 * switch to kiosk time frame
	 * @param film selected film
	 */
	public void gotoTime(String film) {
		kioskfilm.setVisible(false);
		kioskticket.setVisible(false);
		try {
			kiosktime.currentfilm = getFilm(film);
			setCurrentFilm(kiosktime.currentfilm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		kiosktime.setLocationRelativeTo(null);
		kiosktime.setVisible(true);
		Vector<String> title = new Vector<String>();// 列名
		title.add("Screen"); title.add("Showtime"); title.add("Confirm");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
		for (int i = 0; i < getTimetable(true).size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            Timetable t = getTimetable(true).get(i); 
            if(t.getFilmName().equals(currentfilm.getName())) {
            		v.add(t.getScreen());
	            v.add(convertTime(t.getShowtime()));
	            v.add("Confirm");
	            data.add(v);  			            	
            }
        }
        kiosktime.timetableModel = new DefaultTableModel(data, title);
        kiosktime.timetable.setModel(kiosktime.timetableModel);
        kiosktime.timetable.repaint();
//		((AbstractTableModel) kiosktime.timetableModel).fireTableDataChanged();
        kiosktime.timetable.getColumnModel().getColumn(2).setCellRenderer(new JButtonRenderer());
        kiosktime.timetable.getColumnModel().getColumn(2).setCellEditor(new JButtonEditor(new JCheckBox(), this, true));       
	}
	
	/**
	 * switch to kiosk ticket frame
	 */
	public void gotoTicket() {
		kiosktime.setVisible(false);
		kioskseat.setVisible(false);
		int available = 0;
		try {
			available = availableScreen(currenttimetable.getFilmName(), currenttimetable.getScreen(), currenttimetable.getShowtime());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String[] combobox = new String[available+1];
		combobox[0] = "";
		for(int i = 1; i<combobox.length; i++) {
			combobox[i] = i+"";
		}
		kioskticket.numberModel = new DefaultComboBoxModel(combobox);
		kioskticket.number.setModel(kioskticket.numberModel);
		Vector<String> title = new Vector<String>();// 列名
		title.add("Type"); title.add("Description"); title.add("ID Required"); title.add("Discount");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
		String[] type = new String[ticketInfoList.size()+1];
		for (int i = 0; i < ticketInfoList.size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            TicketInfo t = ticketInfoList.get(i);
            type[i+1] = convertType(t.getType());
            v.add(convertType(t.getType()));
            v.add(t.getDescription());
            v.add(t.isIDRequired());
            v.add(t.getDiscount() + "%");
            data.add(v);  			            	
        }
		kioskticket.ticketinfoModel = new DefaultTableModel(data, title);
        kioskticket.ticketinfo.setModel(kioskticket.ticketinfoModel);
        kioskticket.ticketinfo.repaint();
        kioskticket.typeModel = new DefaultComboBoxModel(type);
        kioskticket.type.setModel(kioskticket.typeModel);
        kioskticket.type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//System.out.println("type.actionPerformed, event="+evt);
				try {
					int index = searchTicketInfo((int)kioskticket.type.getSelectedIndex());
					setCurrentTicketInfo(ticketInfoList.get(index));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        kioskticket.type.repaint();
		kioskticket.setLocationRelativeTo(null);
		kioskticket.setVisible(true);
	}
	
	/**
	 * switch to kiosk seat frame
	 */
	public void gotoSeat() {
		setNumber(kioskticket.number.getSelectedIndex());
		try {
			searchScreen(currentfilm.getName(), currenttimetable.getScreen(), currenttimetable.getShowtime());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		kioskfinish.setVisible(false);
		kioskticket.setVisible(false);
		kioskpay.setVisible(false);
		kioskseat.seats.removeAll();
		kioskseat.seats.setLayout(new GridLayout(seats.getRow(), seats.getCol()));
		occupy = 0;
		currentrow = new int[number];
		currentcol = new int[number];		
		for(int i = 0; i<seats.getRow(); i++) {
			for(int j = 0; j<seats.getCol(); j++) {
				if(seats.getSeat()[i][j].equals("NULL")) {
					JLabel empty = new JLabel();
					kioskseat.seats.add(empty);
				} else {
					JButton seat = new JButton(convertSeat(seats.getRow(), seats.getCol(), i, j));
					kioskseat.seats.add(seat);
					if(seats.getSeat()[i][j].equals("1")) {
						seat.setEnabled(false);
					}
					seat.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("seat.actionPerformed, event="+evt);
							if(number!=occupy) {
								currentrow[occupy] = extractSeat(seat.getText())[0];
								currentcol[occupy] = extractSeat(seat.getText())[1]; 
								seat.setText("X");
								occupy++;
							} else {
								JOptionPane.showMessageDialog(null, "You have already selected all of your seats.", "Alert", JOptionPane.ERROR_MESSAGE); 			
							}
						}
					});
				}
			}
		}
		kioskseat.seats.repaint();
		kioskseat.setLocationRelativeTo(null);
		kioskseat.setVisible(true);
	}
	
	/**
	 * switch to kiosk pay frame
	 */
	public void gotoPay() {
		if(number!=occupy) {
			JOptionPane.showMessageDialog(null, "Please choose your seats! " + (number - occupy) + "/" + number +"left.", "Alert", JOptionPane.ERROR_MESSAGE); 			
			return ;
		}
		kioskseat.setVisible(false);
		String tickettype = convertType(currentticketinfo.getType());
		String[][] content = new String[number*7][2];
		for(int i = 0; i<number; i++) {
        		content[7*i][0] = "Ticket No.";
        		content[7*i][1] = "TBA";
        		
        		content[7*i+1][0] = "Type";
        		content[7*i+1][1] = tickettype;

        		content[7*i+2][0] = "Film";
        		content[7*i+2][1] = currentfilm.getName();

        		content[7*i+3][0] = "Showtime";
        		content[7*i+3][1] = currenttimetable.getShowtime();

        		content[7*i+4][0] = "Screen";
        		content[7*i+4][1] = currenttimetable.getScreen()+"";

        		content[7*i+5][0] = "Seat";
        		content[7*i+5][1] = convertSeat(seats.getRow(),seats.getCol(),currentrow[i],currentcol[i]);

        		content[7*i+6][0] = "";
        		content[7*i+6][1] = "";
        		if(i == number-1) {
        			content[7*i+6][0] = "Total";
            		content[7*i+6][1] = "£"+ FEE *(100-currentticketinfo.getDiscount())/100.0 * number;	
        		}
        }
		kioskpay.ticketModel = new DefaultTableModel(content,new String[]{"title","content"});
        kioskpay.ticket.setModel(kioskpay.ticketModel);
        kioskpay.ticket.enable(false);
        kioskpay.ticket.repaint();
		kioskpay.setLocationRelativeTo(null);
		kioskpay.setVisible(true);
	}
	
	/**
	 * switch to kiosk finish frame
	 */
	public void gotoFinish() {
		kioskpay.setVisible(false);
		try { 
			newticket = new Ticket[number];
			for(int i = 0; i<newticket.length; i++) {
				newticket[i] = createTicket(currentticketinfo.getType(), currentticketinfo.isIDRequired(), currentfilm.getName(), 
						currenttimetable.getShowtime(), currenttimetable.getScreen(), currentrow[i], currentcol[i]);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[][] content = new String[number*7][2];
        for(int i = 0; i<number; i++) {
        		content[7*i][0] = "Ticket No.";
        		content[7*i][1] = newticket[i].getNumber()+"";
        		
        		content[7*i+1][0] = "Type";
        		content[7*i+1][1] = convertType(newticket[i].getType());

        		content[7*i+2][0] = "Film";
        		content[7*i+2][1] = newticket[i].getFilm();

        		content[7*i+3][0] = "Showtime";
        		content[7*i+3][1] = newticket[i].getShowtime();

        		content[7*i+4][0] = "Screen";
        		content[7*i+4][1] = newticket[i].getScreen()+"";

        		content[7*i+5][0] = "Seat";
        		content[7*i+5][1] = convertSeat(seats.getRow(), seats.getCol(),newticket[i].getRow(),newticket[i].getCol());

        		content[7*i+6][0] = "";
        		content[7*i+6][1] = "";
        		if(i == number-1) {
        			content[7*i+6][0] = "Total";
            		content[7*i+6][1] = "£"+ FEE *(100-currentticketinfo.getDiscount())/100.0 * number;	
        		}
        }
        kioskfinish.ticketModel = new DefaultTableModel(content,new String[]{"Title","Content"});
        kioskfinish.ticket.setModel(kioskfinish.ticketModel);
        kioskfinish.ticket.repaint();
		kioskfinish.setLocationRelativeTo(null);
		kioskfinish.setVisible(true);
	}
	
	/**
	 * switch to administrator login frame
	 */
	public void gotoAdmin() {
		adminlogin.setVisible(false);
		adminlogin.username.setText("");
		adminlogin.password.setText("");
		adminlogin.setLocationRelativeTo(null);
		adminlogin.setVisible(true);
	}
	
	/**
	 * switch to administrator management frame
	 * @param choose
	 */
	public void gotoManage(int choose) {
		admintimetable.setVisible(false);
		adminreport.setVisible(false);
		if(choose == 1) {
			int valid = -1;
			try {
				valid = searchAdmin(adminlogin.username.getText(), adminlogin.password.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(valid == -1) {
				JOptionPane.showMessageDialog(null, "Username or password!", "Alert", JOptionPane.ERROR_MESSAGE); 			
				return ;
			}
			adminlogin.setVisible(false);			
		} else if(choose == 2){
			if(admintimetable.screen.getSelectedIndex()!=0 && admintimetable.film.getSelectedIndex()!=0 &&
					admintimetable.hour.getSelectedIndex()!=0 && admintimetable.min.getSelectedIndex()!=0) {
				boolean status = false;
				try {
					status = addTimetable(Integer.parseInt(admintimetable.screen.getSelectedItem().toString()), admintimetable.film.getSelectedItem().toString(), 
							admintimetable.hour.getSelectedItem().toString()+admintimetable.min.getSelectedItem().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(status) {
					JOptionPane.showMessageDialog(null, "Added successfully!", "Info", JOptionPane.INFORMATION_MESSAGE); 			
				} else {
					JOptionPane.showMessageDialog(null, "Add failed!", "Alert", JOptionPane.ERROR_MESSAGE); 		
				}
			}			
		}
		adminmanage.setLocationRelativeTo(null);
		adminmanage.setVisible(true);
	}
	
	/**
	 * switch to administrator timetable frame
	 */
	public void gotoTimetable() {
		adminmanage.setVisible(false);
		String[] screenContent = new String[screenList.size()+1];
		screenContent[0] = "";
		for(int i = 1; i<screenList.size()+1; i++) {
			screenContent[i] = screenList.get(i-1).getNumber()+"";
		}
		admintimetable.screenModel = new DefaultComboBoxModel(screenContent);
		admintimetable.screen.setModel(admintimetable.screenModel);
		admintimetable.screen.repaint();
		String[] filmContent = new String[filmInfoList.size()+1];
		filmContent[0] = "";
		for(int i = 1; i<filmInfoList.size()+1; i++) {
			filmContent[i] = filmInfoList.get(i-1).getName();
		}
		admintimetable.filmModel = new DefaultComboBoxModel(filmContent);
		admintimetable.film.setModel(admintimetable.filmModel);
		admintimetable.film.repaint();
		String[] hourContent = new String[25];
		hourContent[0] = "";
		for(int i = 1; i<25; i++) {
			hourContent[i] = String.format("%02d", i-1);
		}
		admintimetable.hourModel = new DefaultComboBoxModel(hourContent);
		admintimetable.hour.setModel(admintimetable.hourModel);
		admintimetable.hour.repaint();
		String[] minContent = new String[61];
		minContent[0] = "";
		for(int i = 1; i<61; i++) {
			minContent[i] = String.format("%02d", i-1);
		}
		admintimetable.minModel = new DefaultComboBoxModel(minContent);
		admintimetable.min.setModel(admintimetable.minModel);
		admintimetable.min.repaint();
		//TODO update table content
		Vector<String> title = new Vector<String>();// 列名
		title.add("Screen"); title.add("Film"); title.add("Showtime"); title.add("Confirm");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
		for (int i = 0; i < getTimetable(false).size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            Timetable t = getTimetable(false).get(i); 
            v.add(t.getScreen());
            v.add(t.getFilmName());
	        v.add(convertTime(t.getShowtime()));
	        v.add("Delete");
	        data.add(v);  			            	
        }
        admintimetable.timetableModel = new DefaultTableModel(data, title);
        admintimetable.timetable.setModel(admintimetable.timetableModel);
        admintimetable.timetable.repaint();
        admintimetable.timetable.getColumnModel().getColumn(3).setCellRenderer(new JButtonRenderer());
        admintimetable.timetable.getColumnModel().getColumn(3).setCellEditor(new JButtonEditor(new JCheckBox(), this, false));       
		admintimetable.setLocationRelativeTo(null);
		admintimetable.setVisible(true);
	}
	
	/**
	 * switch to administrator report frame
	 */
	public void gotoReport() {
		adminmanage.setVisible(false);
		try {
			getReport(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		adminreport.report.setText(reportContent.toString());
		adminreport.report.setEditable(false);
		adminreport.setLocationRelativeTo(null);
		adminreport.setVisible(true);
	}	
	
	public static void main(String args[]) {
		CinemaSystem cs = new CinemaSystem();
		try {
			cs.createReport();
			cs.readData(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// check gate
		cs.gotoGate();
		// kiosk
		cs.gotoWelcome();
		// Admin
		cs.gotoAdmin();
	}
}

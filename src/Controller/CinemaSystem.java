package Controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Model.*;
import View.*;

public class CinemaSystem {
	private static final int fee = 16;
	// check gate frame
	CheckGate checkgate;
	CheckTicket checkticket;
	// kiosk frame
	KioskWelcome kioskwelcome;
	KioskFilm kioskfilm;
	KioskTime kiosktime;
	KioskTicket kioskticket;
	KioskSeat kioskseat;
	KioskPay kioskpay;
	KioskFinish kioskfinish;
	// manage frame
	AdminLogin adminlogin;
	AdminManage adminmanage;
	AdminTimetable admintimetable;
	AdminReport adminreport;
	
	// kiosk info
	Film currentfilm;
	Timetable currenttimetable;
	TicketInfo currentticketinfo;
	int[] currentrow;
	int[] currentcol;
	int occupy;
	int number;
	Ticket[] newticket;
	
	// basic
	public ArrayList<Admin> adminList = new ArrayList<Admin>();
	public ArrayList<Film> filmInfoList = new ArrayList<Film>();
	public ArrayList<Screen> screenList = new ArrayList<Screen>();
	public ArrayList<TicketInfo> ticketInfoList = new ArrayList<TicketInfo>();
	public Ticket currentticket;
	public Ticket briefCurrentTicket;
	
	// date
	public ArrayList<Ticket> briefTicketList = new ArrayList<Ticket>();
	public ArrayList<Timetable> timetableList = new ArrayList<Timetable>();
	// report and ticketnumber.txt
	
	Screen seats = null;
	
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
		reader = new Scanner(new File(getCurrentDatePath()+"/Ticket.txt"));
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			briefTicketList.add(new Ticket(Integer.parseInt(s[0]), Boolean.parseBoolean(s[1])));
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
		Admin admin = new Admin(id, password);
		adminList.add(admin);
		writeData();	
		return true;
	}

	public int searchAdmin(String id, String password) throws Exception{
		for(int i = 0; i<adminList.size();i++){
			if(adminList.get(i).getID().equals(id) && adminList.get(i).getPassword().equals(password)){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteAccount(String id, String password) throws Exception {
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateAdmin(String id, String password) throws Exception {
		int index = searchAdmin(id, password);
		if(index == -1) {
			return false;
		}
		adminList.set(index, new Admin(id, password));
		return true;
	}
	
	public boolean addFilm(String name, int runtime, String poster) throws Exception {
		Film film = new Film(name, runtime, poster);
		filmInfoList.add(film);
		writeData();	
		return true;
	}

	public int searchFilm(String name) throws Exception{
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return i;				
			}
		}
		return -1;
	}
	
	public Film getFilm(String name) throws Exception{
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return filmInfoList.get(i);				
			}
		}
		return null;
	}
	
	public boolean deleteFilm(String name) throws Exception {
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateFilm(String name, int runtime, String poster) throws Exception {
		int index = searchFilm(name);
		if(index == -1) {
			return false;
		}
		filmInfoList.set(index, new Film(name, runtime, poster));
		return true;
	}

	public boolean addTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		TicketInfo ticket = new TicketInfo(type, description, IDRequired, discount);
		ticketInfoList.add(ticket);
		writeData();	
		return true;
	}

	public int searchTicketInfo(int type) throws Exception{
		for(int i = 0; i<ticketInfoList.size();i++){
			if(ticketInfoList.get(i).getType() == type){
				return i;				
			}
		}
		return -1;
	}
	
	public boolean deleteTicketInfo(int type) throws Exception {
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.remove(index);
		writeData();	
		return true;
	}
	
	public boolean updateTicketInfo(int type, String description, boolean IDRequired, int discount) throws Exception {
		int index = searchTicketInfo(type);
		if(index == -1) {
			return false;
		}
		ticketInfoList.set(index, new TicketInfo(type, description, IDRequired, discount));
		return true;
	}
	
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
	
	public boolean addTimetable(int screen, String filmName, String showtime) throws Exception {
		for(int i = 0; i<timetableList.size(); i++) {
			Timetable timetable = timetableList.get(i);
			Film targetfilm = getFilm(timetable.getFilmName());
			Film film = getFilm(filmName);
			String starttime = showtime;
			String endtime = showtime + film.getRuntime();
			String targetstart = timetable.getShowtime();
			String targetend = timetable.getShowtime() + targetfilm.getRuntime();
			if(compareTime(targetstart, endtime) != -1 || compareTime(targetend, starttime) != 1)
				continue;
			else
				return false;
		}
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
	
	public boolean searchScreen(String filmname, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+showtime+".txt"));
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
	
	public int availableScreen(String filmname, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+showtime+".txt"));
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
	
	public boolean updateScreen(String filmname, String showtime, int seatrow, int seatcol) throws IOException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+filmname+"/"+showtime+".txt"));
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
			BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+filmname+"/"+showtime+".txt"));
			output.write(seats.toString());
			output.close();
			return true;
		}
	}
	
	public Ticket searchTicket(int number) {
		for(int i = 0; i<briefTicketList.size(); i++) {
			if(briefTicketList.get(i).getNumber() == number)
				return briefTicketList.get(i);
		}
		return null;
	}
	
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(getCurrentDatePath()+"/"+ticketno+".txt:"+currentticket.toString());
		try {
			updateScreen(film, showtime, row, col);
			writeData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentticket;
	}
	
	public void getTicket(String number) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+number+".txt"));
		String[] s = reader.nextLine().split("]]]]");
		currentticket = new Ticket(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Boolean.parseBoolean(s[2]), 
								s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), true);
		reader.close();
	}
	
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
		writeData();
		JOptionPane.showMessageDialog(null, "Door open, enjoy your film!", "Alert", JOptionPane.INFORMATION_MESSAGE); 			
		gotoGate();
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
		for (int i = 0; i < briefTicketList.size(); i++) {
			output.write(briefTicketList.get(i).toString() + "\r\n");
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
	
	public String addTime(String time, String delta) {
		int hour = Integer.parseInt(time.substring(0,2));
		int min = Integer.parseInt(time.substring(2));
		int deltaHour = Integer.parseInt(delta.substring(0,2));
		int deltaMin = Integer.parseInt(delta.substring(2));
		int newMin = (min + deltaMin) % 60;
		int newHour = (hour + deltaHour) + (min + deltaMin) / 60;
		return new String(newHour + "" + newMin);
	}
	
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
	
	public void gotoGate() {
		checkticket.setVisible(false);
		checkgate.setLocationRelativeTo(null);
		checkgate.setVisible(true);
	}
	
	public void GateToTicket(String number) {
		try {
			readData();
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
				getTicket(number);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String type = convertType(currentticket.getType());
			checkticket.number.setText(currentticket.getNumber()+"");
			checkticket.type.setText(type);
			checkticket.film.setText(currentticket.getFilm());
			checkticket.showtime.setText(convertTime(currentticket.getShowtime()));
			checkticket.screen.setText(currentticket.getScreen()+"");
			checkticket.seat.setText("Row "+currentticket.getRow()+" Col "+(currentticket.getCol()));
			if(currentticket.getIDRequired() == false) {
				checkticket.checkID.disable();
			}
			checkticket.setLocationRelativeTo(null);
			checkticket.setVisible(true);
		}
	}
	
	public void gotoWelcome() {
		kioskfilm.setVisible(false);
		kioskwelcome.setLocationRelativeTo(null);
		kioskwelcome.setVisible(true);
	}
	
	public void gotoFilm() {
		kioskwelcome.setVisible(false);
		kioskfilm.setLocationRelativeTo(null);
		kioskfilm.setVisible(true);
	}
	
	public void gotoTime(String film) {
		kioskfilm.setVisible(false);
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
        kiosktime.timetable.getColumnModel().getColumn(2).setCellEditor(new JButtonEditor(new JCheckBox(), this));       
	}
	
	public void gotoTicket() {
		kiosktime.setVisible(false);
		int available = 0;
		try {
			available = availableScreen(currenttimetable.getFilmName(), currenttimetable.getShowtime());
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
				//TODO add your code for type.actionPerformed
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
	
	public void gotoSeat() {
		setNumber(kioskticket.number.getSelectedIndex());
		try {
			searchScreen(currentfilm.getName(), currenttimetable.getShowtime());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		kioskticket.setVisible(false);
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
							//TODO add your code for back.actionPerformed
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
	
	public void gotoPay() {
		if(number!=occupy) {
			JOptionPane.showMessageDialog(null, "Please choose your seats! " + (number - occupy) + "/" + number +"left.", "Alert", JOptionPane.ERROR_MESSAGE); 			
			return ;
		}
		kioskticket.setVisible(false);
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
            		content[7*i+6][1] = "£"+fee*(100-currentticketinfo.getDiscount())/100.0 * number;	
        		}
        }
		kioskpay.ticketModel = new DefaultTableModel(content,new String[]{"title","content"});
        kioskpay.ticket.setModel(kioskpay.ticketModel);
        kioskpay.ticket.enable(false);
        kioskpay.ticket.repaint();
		kioskpay.setLocationRelativeTo(null);
		kioskpay.setVisible(true);
	}
	
	public void gotoFinish() {
		//TODO add generate ticket especially ticket number
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
            		content[7*i+6][1] = "£"+fee*(100-currentticketinfo.getDiscount())/100.0 * number;	
        		}
        }
        kioskfinish.ticketModel = new DefaultTableModel(content,new String[]{"Title","Content"});
        kioskfinish.ticket.setModel(kioskfinish.ticketModel);
        kioskfinish.ticket.repaint();
		kioskfinish.setLocationRelativeTo(null);
		kioskfinish.setVisible(true);
	}
	
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
	
	public String convertTime(String time) {
		String showtime = new String();
		showtime = time.substring(0,2) + ":" + time.substring(2);
		return showtime;
	}
	
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
	
	public String convertSeat(int totalrow, int totalcol, int row, int col) {
		String seat = new String();
		seat = (char)((totalrow-1-row) + 65) + "";
		seat = seat + (col+1);
		return seat;
	}
	
	public int[] extractSeat(String seat) {
		int row = seats.getRow() - 1 - (seat.charAt(0) - 65);
		int col = Integer.parseInt(seat.substring(1))-1;
		int[] res = new int[]{row, col};
		return res;
	}
	
	public void gotoAdmin() {
		adminlogin.setVisible(false);
		adminlogin.setLocationRelativeTo(null);
		adminlogin.setVisible(true);
	}
	
	public void gotoManage() {
		adminlogin.setVisible(false);
		adminmanage.setLocationRelativeTo(null);
		adminmanage.setVisible(true);
	}
	
	public void gotoTimetable() {
		adminmanage.setVisible(true);
		admintimetable.setLocationRelativeTo(null);
		admintimetable.setVisible(true);
	}
	
	public void gotoReport() {
		adminmanage.setVisible(true);
		adminreport.setLocationRelativeTo(null);
		adminreport.setVisible(true);
	}
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String args[]) {
		CinemaSystem cs = new CinemaSystem();
		// check gate
		//cs.gotoGate();
		// kiosk
//		cs.gotoWelcome();
		cs.gotoAdmin();
	}
}

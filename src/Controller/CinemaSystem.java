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
	
	// kiosk info
	Film currentfilm;
	Timetable currenttimetable;
	TicketInfo currentticketinfo;
	int currentrow;
	int currentcol;
	
	// basic
	public ArrayList<Admin> adminList = new ArrayList<Admin>();
	public ArrayList<Film> filmInfoList = new ArrayList<Film>();
	public ArrayList<Screen> screenList = new ArrayList<Screen>();
	public ArrayList<TicketInfo> ticketInfoList = new ArrayList<TicketInfo>();
	public Ticket currentticket;
	
	// date
	public ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
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
		currentfilm = new Film();
		currenttimetable = new Timetable();
		currentticketinfo = new TicketInfo();
		currentrow = -1;
		currentcol = -1;
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
	
	public void setSeat(int row, int col) {
		this.currentrow = row;
		this.currentcol = col;
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
	
	public Film getFilm(String name) throws Exception{
		readData();
		for(int i = 0; i<filmInfoList.size();i++){
			if(filmInfoList.get(i).getName().equals(name)){
				return filmInfoList.get(i);				
			}
		}
		return null;
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
	
	public Ticket searchTicket(int number) {
		for(int i = 0; i<ticketList.size(); i++) {
			if(ticketList.get(i).getNumber() == number)
				return ticketList.get(i);
		}
		return null;
	}
	
	public Ticket createTicket(int type, boolean IDRequired, String film, String showtime, int screen, int row, int col) {
		Ticket ticket = new Ticket();
		int number = ticket.generateTicketNumber();
		while(searchTicket(number) != null) {
			number = ticket.generateTicketNumber();
		}
		ticket = new Ticket(number, type, IDRequired, film, showtime, screen, row, col, false);
		ticketList.add(ticket);
		return ticket;
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
		for(int i = 0; i<ticketList.size(); i++) {
			if(ticketList.get(i).getNumber() == number) {
				Ticket ticket = new Ticket(number, true);
				ticketList.set(i, ticket);
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
			String type = "";
			switch(currentticket.getType()) {
			case 1:
				type = "Child";
				break;
			case 2:
				type = "Adult";
				break;
			case 3:
				type = "Senior";
				break;
			case 4:
				type = "Student";
				break;
			}
			checkticket.number.setText(currentticket.getNumber()+"");
			checkticket.type.setText(type);
			checkticket.film.setText(currentticket.getFilm());
			checkticket.showtime.setText(currentticket.getShowtime().substring(0,2)+":"+currentticket.getShowtime().substring(2));
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
		for (int i = 0; i < timetableList.size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            Timetable t = timetableList.get(i); 
            if(t.getFilmName().equals(currentfilm.getName())) {
            		v.add(t.getScreen());
	            v.add(t.getShowtime().substring(0,2)+":"+t.getShowtime().substring(2));
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
	
	public void gotoSeat() {
		System.out.println(currenttimetable);
		try {
			searchScreen(currentfilm.getName(), currenttimetable.getShowtime());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		kioskticket.setVisible(false);
		kioskseat.seats.removeAll();
		kioskseat.seats.setLayout(new GridLayout(seats.getRow(), seats.getCol()));
		for(int i = 0; i<seats.getRow(); i++) {
			for(int j = 0; j<seats.getCol(); j++) {
				if(seats.getSeat()[i][j].equals("NULL")) {
					JLabel empty = new JLabel();
					kioskseat.seats.add(empty);
				} else {
					char row = (char)(seats.getRow() - i + 65);
					JButton seat = new JButton(row+""+(j+1));
					kioskseat.seats.add(seat);
					if(seats.getSeat()[i][j].equals("1")) {
						seat.setEnabled(false);
					}
					seat.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("seat.actionPerformed, event="+evt);
							//TODO add your code for back.actionPerformed
							currentrow = (seat.getText().charAt(0) - 65);
							currentcol = Integer.parseInt(seat.getText().substring(1)); 
							seat.setText("X");
						}
					});
				}
			}
		}
		kioskseat.seats.repaint();
		kioskseat.setLocationRelativeTo(null);
		kioskseat.setVisible(true);
	}
	
	public void gotoTicket() {
		kiosktime.setVisible(false);
		Vector<String> title = new Vector<String>();// 列名
		title.add("Type"); title.add("Description"); title.add("ID Required"); title.add("Discount");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
		String[] type = new String[ticketInfoList.size()];
		for (int i = 0; i < ticketInfoList.size(); i++) {  
            Vector<Object> v = new Vector<Object>();  
            TicketInfo t = ticketInfoList.get(i);
            switch(t.getType()) {
            case 1:
            		v.add("Child");
            		type[i] = "Child";
            		break;
            case 2:
            		v.add("Adult");
            		type[i] = "Adult";
            		break;
            case 3:
            		v.add("Senior");
            		type[i] = "Senior";
            		break;
            case 4:
            		v.add("Student");
            		type[i] = "Student";
            		break;
            }
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
					int index = searchTicketInfo((int)kioskticket.type.getSelectedIndex()+1);
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
	
	public void gotoPay() {
		kioskseat.setVisible(false);
		String tickettype = "";
        switch(currentticketinfo.getType()) {
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
        String[][] content = new String[][]{{"Type",tickettype}, {"Film", currentfilm.getName()},
        		{"Showtime", currenttimetable.getShowtime()}, {"Screen", currenttimetable.getScreen()+""}, 
        		{"Seat",currentrow +":"+ currentcol}};
        kioskpay.ticketModel = new DefaultTableModel(content,new String[]{"title","content"});
        kioskpay.ticket.setModel(kioskpay.ticketModel);
        kioskpay.ticket.repaint();
		kioskpay.setLocationRelativeTo(null);
		kioskpay.setVisible(true);
	}
	
	public void gotoFinish() {
		//TODO add generate ticket especially ticket number
		kioskpay.setVisible(false);
		String tickettype = "";
        switch(currentticketinfo.getType()) {
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
        String[][] content = new String[][]{{"Ticket No.", currentticket.getNumber()+""},{"Type",tickettype}, 
        		{"Film", currentfilm.getName()},{"Showtime", currenttimetable.getShowtime()}, 
        		{"Screen", currenttimetable.getScreen()+""}, {"Seat",currentrow +":"+ currentcol}};
        kioskfinish.ticketModel = new DefaultTableModel(content,new String[]{"title","content"});
        kioskfinish.ticket.setModel(kioskfinish.ticketModel);
        kioskfinish.ticket.repaint();
		kioskfinish.setLocationRelativeTo(null);
		kioskfinish.setVisible(true);
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
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String args[]) {
		CinemaSystem cs = new CinemaSystem();
		// check gate
		//cs.gotoGate();
		// kiosk
		cs.gotoWelcome();
	}
}

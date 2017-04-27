package Model;

/**
 * Timetable Model
 * @author Yuqin Cui
 * @version v1.0
 */
public class Timetable {
	private int screen;
	private String filmName;
	private String showtime;
	
	public Timetable() {
		
	}
	
	public Timetable(int screen, String filmName, String showtime) {
		this.screen = screen;
		this.filmName = filmName;
		this.showtime = showtime;
	}
	
	public void setScreen(int screen) {
		this.screen = screen;
	}
	public int getScreen() {
		return this.screen;
	}
	
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getFilmName() {
		return this.filmName;
	}
	
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getShowtime() {
		return this.showtime;
	}
	
	public String toString() {
		return screen + "]]]]" + filmName + "]]]]" + showtime;
	}
}

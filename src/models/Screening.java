package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Screening {
	private LocalDate date;
	private LocalTime time;
	private String title;
	private String venue;
	
	public Screening(LocalDate date, LocalTime time, String title, String venue) {
		super();
		this.date = date;
		this.time = time;
		this.title = title;
		this.venue = venue;
	}
	
	
	@Override
	public String toString() {
		String strDate = String.format("%ta %td %tb", date, date, date).toLowerCase();
		return String.format("%s (%s - %s, %s)", title, strDate, time, venue);
	}
}

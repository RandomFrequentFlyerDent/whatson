package main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Screening;

public class WhatsOn {

	public static void main(String[] args) throws ParseException {
		List<Screening> screenings = new ArrayList<Screening>();
		
		try {
			Document doc = Jsoup.connect("https://www.lux-nijmegen.nl/film/?sorteer=tijd").get();
			Elements films = doc.select("#agenda > div > ul > li");
			for (Element film : films) {
				// don't add event if there is no screening time (e.g. it's a festival)
				Element tempElement = film.select("a > div.content-wrap > div.times > div > span").first();
				if (tempElement != null && tempElement.text().contains(":")) {
					String strTime = tempElement.text();
					LocalTime eventTime = LocalTime.parse(strTime, DateTimeFormatter.ISO_LOCAL_TIME);

					// date
					String strDate = film.attr("data-date");
					LocalDate eventDate = LocalDate.parse(strDate, DateTimeFormatter.BASIC_ISO_DATE);  

					// title
					tempElement = film.select("a > div.content-wrap > div.content > div > div > h3").first();
					String title = tempElement.text();

					// venue
					String venue = "LUX";

					// create screening
					Screening screening = new Screening(eventDate, eventTime, title, venue);
					screenings.add(screening);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < screenings.size(); i++) {
			System.out.println(screenings.get(i).toString());
		}

	}

}

package controller;

import java.io.IOException;
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

public class LuxScraper implements Scraper {
	private List<Screening> screenings;
	private final static String LOCATION = "LUX";

	public LuxScraper() {
		super();
		this.screenings = new ArrayList<Screening>();
	}

	@Override
	public List<Screening> scrape() {
		Elements films;
		try {
			films = retrieveFilmsFromWebsite();
			extractInformation(films);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.screenings;
	}

	private void extractInformation(Elements films) {
		for (Element film : films) {
			Element timeElement = film.select("a > div.content-wrap > div.times > div > span").first();
			if (isFilm(timeElement)) {
				// time
				String strTime = timeElement.text();
				LocalTime eventTime = LocalTime.parse(strTime, DateTimeFormatter.ISO_LOCAL_TIME);

				// date
				String strDate = film.attr("data-date");
				LocalDate eventDate = LocalDate.parse(strDate, DateTimeFormatter.BASIC_ISO_DATE);

				// title
				Element titleElement = film.select("a > div.content-wrap > div.content > div > div > h3").first();
				String title = titleElement.text();

				// venue
				String venue = LOCATION;

				// create screening and add to list
				Screening screening = new Screening(eventDate, eventTime, title, venue);
				this.screenings.add(screening);
			}
		}

	}

	private boolean isFilm(Element filmTime) {
		return (filmTime != null && filmTime.text().contains(":"));
	}

	private Elements retrieveFilmsFromWebsite() throws IOException {
		Document doc = Jsoup.connect("https://www.lux-nijmegen.nl/film/?sorteer=tijd").get();
		Elements films = doc.select("#agenda > div > ul > li");
		return films;
	}

}

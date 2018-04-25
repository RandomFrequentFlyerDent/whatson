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
import org.lightcouch.CouchDbClient;

import controller.LuxScraper;
import models.Screening;

public class WhatsOn {

	public static void main(String[] args) throws IOException {
		LuxScraper lux = new LuxScraper();
		List<Screening> screenings = lux.scrape();

		Screening test = screenings.get(0);

		try (CouchDbClient dbClient = new CouchDbClient("whatson", true, "http", "127.0.0.1", 5984, "user",
				"passwords")) {
			dbClient.save(test);
		}

	}

}

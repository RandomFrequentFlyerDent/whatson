package main;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.lightcouch.CouchDbClient;

import controller.LuxScraper;
import models.Movie;
import models.Screening;

public class WhatsOn {

	public static void main(String[] args) throws IOException {
		LuxScraper lux = new LuxScraper();

		List<Movie> movies = lux.scrapeMovies();
		Collections.sort(movies);
		for (Movie m : movies) {
			System.out.println(m.toString());
		}

		List<Screening> screenings = lux.scrape();

		Screening test = screenings.get(5);

		try (CouchDbClient dbClient = new CouchDbClient("whatson", true, "http", "127.0.0.1", 5984, "username",
				"password")) {
			dbClient.save(test);
		}

	}

}

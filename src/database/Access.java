package database;

import org.lightcouch.CouchDbClient;

public class Access {
	private CouchDbClient dbClient;
	
	public Access() {
//		this.dbClient = new CouchDbClient("couchdb.properties");
		this.dbClient = new CouchDbClient("whatson", true, "http", "127.0.0.1", 5984, "user", "password");
	}

}

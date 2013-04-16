package com.weero.main;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
//		Parse.initialize(this, "JS63fPaSuMX7Hz4TKUyvJblbwiZCXIdHJnNDJYSt", "86UO6juiB2Ftx78QVW4KU3ooJ2TWpGx0hEeV1PQH");
//		Parse.initialize(this, "cjBhhLvonfh4hKZH1k2rQK3Pg8GrVh9j6guZ3Ybb", "1GewykJkYwDCTbfQW0e62Wedw4muWeZTZMlQ5ofE");
		Parse.initialize(this, "enfk50Leo8Re1q56KU138wxHg2NB6OYVxQqDSBKO", "tfWPdu67cPWdWK7HveoYx9URjWDYMIjhyCrfIPPi");
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}

package org.stokesdrift.shores;

public class Shores {

	public static void main(String[] args) {
		// TODO do something
		
		// 1. load up the models from the open specification format
		/**
		 * "version": "1.0.0",
    "title": "Swagger Petstore",
    "description": "A sample API that uses a petstore as an example to demonstrate features in the swagger-2.0 specification",
    "termsOfService": "http://swagger.io/terms/",
    "contact": {
      "name": "Swagger API Team"
    },
    "license": {
      "name": "MIT"
    }
		 */
		// 2. find template directory
		// 3. load templates based on generator definition? 
				// - file naming convention with model references pointing to template
				// - output pathing?
				// - support for system / command line options
				// - general build / project configuration
		// 4. create directories based on generator definition
		// 5. create files based on iterating over the models and using generator definition as a base
			// - support generating based on new model name, make sure not to override if already exists
	}
	
}

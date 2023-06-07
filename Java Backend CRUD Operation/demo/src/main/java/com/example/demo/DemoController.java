// Java Program to Illustrate DemoController

// Importing package to code module
package com.example.demo;
// Importing required classes
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
@RequestMapping(path = "/demo")
// Class
public class DemoController {

	@GetMapping(path = "",
	        produces = "application/json")
	@ResponseBody

	// Method
	public String helloWorld()
	{

		// Print statement
		return "Hello World!";
	}
	
	@GetMapping(path = "/test",
	        produces = "application/json")
	@ResponseBody

	// Method
	public String geTenants()
	{
		//TODO: write code to connect t db and retrieve the data
		
		// Print statement
		return "Hello test!";
	}
}

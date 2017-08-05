package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Spark.get("/", new Route() {
			public Object handle(Request request, Response response) {
				return "Hello World From Spark\n";
			}
		});

		Spark.get("/test", new Route() {
			public Object handle(Request request, Response response) {
				// TODO Auto-generated method stub
				return "Test page!\n";
			}
		});

		Spark.get("/echo/:thing", new Route() {
			public Object handle(Request request, Response response) {
				try {
					String thing = request.params(":thing");
					return thing;
				} catch (Exception e) {
					return e.getMessage();
				}
			}
		});

		System.out.println("Hello World!");
	}
}

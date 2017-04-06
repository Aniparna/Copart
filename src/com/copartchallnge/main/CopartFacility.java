package com.copartchallnge.main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import com.copartchallange.dataconnection.MysqlConector;


public class CopartFacility {

	/**
	 * @param Aniparna Sengupta
	 * 
	 * trying to get the location of a place using its latitude and longitude. 
	 * The zipcode is used to get the latitude and longitude from a 
	 * desired location using that we can calculate the distance.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter thePostalcode and CustomerId");
			String zipcode=sc.next();
			String CustID=sc.next();
			MysqlConector con = new MysqlConector();
			List<String> details = con.fetchDetails(Integer.parseInt(CustID), 2);
			
			String latLongs[] = getLatLongPositions(zipcode);
			System.out.println("latitude"+latLongs[0]);
			System.out.println("longitude"+latLongs[1]);
			Double latitude=Double.parseDouble(latLongs[0]);
			Double longitude=Double.parseDouble(latLongs[1]);
			
			Double distance=calculateDist(latitude,latitude);
			System.out.println("the distance in km is"+distance);
			/*
			 * caluclating the distance from using the postalcode from the databse and sorting them in
			 * an array after ascendingly sorting them the first value would be the shortest distance from teh customer 
			 * 
			 * 
			 */
			
			System.out.println("For customer"+CustID+"with a provided zipcode of,"+postalCode+", closest Copart Facility(based on distance) is"+distance);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	

	private static Double calculateDist(Double lon1, Double lat1) {
		// TODO Auto-generated method stub
		 final int R = 6371; // Radius of the earth

		 	//selecting zipcode from the databse passing it to the function getLatLongPositions
		 // then calulating all the latitdes and longitudes of the function for simplicity i am using 
		 //using this as a sample input 


		 
		 
		    Double latDistance = Math.toRadians(lat2 - lat1);
		    Double lonDistance = Math.toRadians(lon2 - lon1);
		    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c ; // in kilometers


		    distance = Math.pow(distance, 2);

		    return Math.sqrt(distance);
		
		
	}
	
	
	
	public static String[] getLatLongPositions(String address) throws Exception
	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      org.w3c.dom.Document document =  builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         System.out.println(latitude);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         System.out.println(longitude);
	         return new String[] {latitude, longitude};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    return null;
	  }
	}

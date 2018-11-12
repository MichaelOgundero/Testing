package Package;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.*;
import java.io.*;
public class TEST {
	 private static String url = "";
  
	public static void POST(String route, NameValuePair[] data,int expectedStatusCode)
	{
		// Create an instance of HttpClient.
	    HttpClient client = new HttpClient();
	    // Create a method instance.
	    PostMethod post = new PostMethod(url+route);
	    // Provide custom retry handler is necessary
	    client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		  new DefaultHttpMethodRetryHandler());
	    try {
	    	 post.addParameters(data);
	        // Execute the method.
	        int statusCode = client.executeMethod(post);
	        Assert.assertEquals(expectedStatusCode, statusCode);
	        System.out.println("POST method succeed");

	      } catch (HttpException e) {
	        System.err.println(" protocol violation: " + e.getMessage());
	        e.printStackTrace();
	      } catch (IOException e) {
	        System.err.println(" transport error: " + e.getMessage());
	        e.printStackTrace();
	      } finally {
	        // Release the connection.
	        post.releaseConnection();
	      }  
	}
	@SuppressWarnings("deprecation")
	public static void PUT(String route, String  data,int expectedStatusCode)
	{
		// Create an instance of HttpClient.
	    HttpClient client = new HttpClient();
	    // Provide custom retry handler is necessary
	    client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		  new DefaultHttpMethodRetryHandler());
	    // Create a method instance.
	    PutMethod put = new PutMethod(url+route);
	    try {
			put.setRequestEntity(new StringRequestEntity(data,
			        "application/json","UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	    try {
	        // Execute the method.
	        int statusCode = client.executeMethod(put);
	        Assert.assertEquals(expectedStatusCode, statusCode);
	        	System.out.println("hello");

	      } catch (HttpException e) {
	        System.err.println("Fatal protocol violation: " + e.getMessage());
	        e.printStackTrace();
	      } catch (IOException e) {
	        System.err.println("Fatal transport error: " + e.getMessage());
	        e.printStackTrace();
	      } finally {
	        // Release the connection.
	        put.releaseConnection();
	      }  
	}
	public static void Get(String route,int expectedStatusCode)
	
	{
		// Create an instance of HttpClient.
	    HttpClient client = new HttpClient();
	    // Create a method instance.
	    GetMethod method = new GetMethod(url+route);
	    // Provide custom retry handler is necessary
	    client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		  new DefaultHttpMethodRetryHandler());
	    try {
	        // Execute the method.
	        int statusCode = client.executeMethod(method);
	        Assert.assertEquals(expectedStatusCode, statusCode);
	      } catch (HttpException e) {
	        System.err.println("Fatal protocol violation: " + e.getMessage());
	        e.printStackTrace();
	      } catch (IOException e) {
	        System.err.println("Fatal transport error: " + e.getMessage());
	        e.printStackTrace();
	      } finally {
	        // Release the connection.
	        method.releaseConnection();
	      }  
	}
	
	
	public static void DELETE(String route,int expectedStatusCode) throws Exception {
		  HttpClient client=new HttpClient();
		  DeleteMethod method=new DeleteMethod(url+route);
		  //client.executeMethod(method);
		 ///int status=method.getStatusCode();
		  try {
		        // Execute the method.
		        int statusCode = client.executeMethod(method);
		        Assert.assertEquals(expectedStatusCode, statusCode);
		      } catch (HttpException e) {
		        System.err.println("Fatal protocol violation: " + e.getMessage());
		        e.printStackTrace();
		      } catch (IOException e) {
		        System.err.println("Fatal transport error: " + e.getMessage());
		        e.printStackTrace();
		      } finally {
		        // Release the connection.
		        method.releaseConnection();
		      }  
		  //if (status == 404)   throw new IllegalArgumentException("Dataset does not exist.");
		 // if (status == 503)   throw new IllegalStateException("Service error: " + status);
		}
	
  public static void main(String[] args) {

  }
}

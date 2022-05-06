package no.hvl.dat110.aciotdevice.client;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private static String logpath = "/accessdevice/log";
	private static final String URLLOG = "http://" + Configuration.host + ":" + Configuration.port + logpath;

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		AccessMessage aMessage = new AccessMessage(message);
		RequestBody body = RequestBody.create(JSON, gson.toJson(aMessage));
		
		Request req = new Request.Builder()
				.url(URLLOG)
				.post
				.build;
		
		
		try {
			
			Response rep = client.newCall(req).excecute();
			String body = rep.body.string();
			System.out.println(body);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static String codepath = "/accessdevice/code";
	private static final String URLCODE = "http://" + Configuration.host + ":" + Configuration.port + codepath;
	
	public AccessCode doGetAccessCode() {

		AccessCode code = new AccessCode();
		
		// TODO: implement a HTTP GET on the service to get current access code
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		
		Request req = new Request.Builder()
							.url(URLCODE)
							.get
							.build;
		
		
		
		try  {
			Response rep = client.newCall(req).execute();
			String body = rep.body.string();
			System.out.println(body);
			code = gson.fromJson(body, AccessCode.class);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return code;
	}
}

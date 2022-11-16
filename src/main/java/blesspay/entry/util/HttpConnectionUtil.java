package blesspay.entry.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import com.google.gson.Gson;

import blesspay.entry.model.entity.Entry;
import blesspay.entry.model.to.ErrorTO;
import blesspay.entry.model.to.OrchestratorResponseTO;

public class HttpConnectionUtil {

	/**
	 * TODO: Return an object containing the response code, message and 
	 *       the entry information.     
	 * @param requestUrl
	 * @param body
	 * @return
	 */
	public static Optional<OrchestratorResponseTO> sendPostRequest(String requestUrl, String body) {
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			
			OutputStream os = con.getOutputStream();
			
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(body);
			osw.flush();
			osw.close();
			
			con.connect();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(returnInputStream(con)));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			
			in.close();
			
			if(con.getResponseCode() == 200) {
				OrchestratorResponseTO response = new OrchestratorResponseTO();
				response.setStatusCode(con.getResponseCode());
				response.setEntry(new Gson().fromJson(content.toString(), Entry.class));
				return Optional.of(response);
			} else {
				OrchestratorResponseTO response = new OrchestratorResponseTO();
				response.setStatusCode(con.getResponseCode());
				response.setError(new Gson().fromJson(content.toString(), ErrorTO.class));
				return Optional.of(response);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private static InputStream returnInputStream(HttpURLConnection con) {
		try {
			if(con.getResponseCode() == 200) {
				return con.getInputStream();
			} else {
				return con.getErrorStream();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

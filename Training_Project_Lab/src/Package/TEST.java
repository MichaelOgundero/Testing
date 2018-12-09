package Package;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TEST {
	private static String url = "https://training-project-lab.appspot.com/";

	public static String Log_in_SignUp(String route, NameValuePair[] data,int expectedStatus)
			throws JSONException, IOException {
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		PostMethod post = new PostMethod(url+ route);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		post.setQueryString(data);
		try {
			int statusCode = client.executeMethod(post);
			System.out.println(route  + " Expected=" + 200 + " Status code :" + statusCode);
			// Read the response body.
			if(statusCode==expectedStatus) {
			String responseBody = post.getResponseBodyAsString();
			System.out.println(responseBody);
			JSONObject object = new JSONObject(responseBody);
			String loc = object.getString("token");
			System.out.println(route +" : succeeded");
			return loc;
			}
			else 
			return null;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(post.getResponseBodyAsString());
			System.out.println(route +" : Failed");
			return null;

		} catch (IOException e) {
			System.out.println(post.getResponseBodyAsString());
			System.out.println(route +" : Failed");
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		} finally {
			post.releaseConnection();
		}
	}

	public static String GetGameID(String Token) throws JSONException {// Create an instance of HttpClient.
		
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod("https://training-project-lab.appspot.com/getGameList");
		// method.setQueryString(data);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.setRequestHeader("Authorization", "Bearer " + Token);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println( "Get Game ID :  "+ "expected"+ "Status code :" + statusCode);

			// Read the response body.
			String responseBody = method.getResponseBodyAsString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			// System.out.println(new String(responseBody));
			String json = new String(responseBody);
			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray("ListGameLobby");
			int GameLobby;
			for (int i=0;i<array.length();i++)
			{
				JSONArray a= array.getJSONObject(i).getJSONArray("users");
				for(int j=0;j<a.length();j++)
				{ String username =a.getJSONObject(j).getString("username");
					if(username.equals(Token))
					{GameLobby=a.getJSONObject(j).getInt("GameLobby");
					return Integer.toString(GameLobby);}
					
				}
			}
			return Integer.toString(array.getJSONObject(0).getInt("GameID"));
			

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	public static boolean POST(String route, NameValuePair[] data, String Token,int expectedStatus) {
		boolean result = false;
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		PostMethod post = new PostMethod(url+route);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        post.setQueryString(data);
		// post.addParameters(data);
        //	post.setRequestBody(data);
		// post.setRequestEntity(new StringRequestEntity(data,
		// "application/json","UTF-8"));
		// Bearer
		post.setRequestHeader("Authorization", "Bearer " + Token);
		// Execute the method.
		try {
			int statusCode = client.executeMethod(post);
			System.out.println(route  + " Expected=" + 200 + " Status code :" + statusCode);
			if (statusCode == expectedStatus)
				{System.out.println(route+ " : succeed");
				result = true;}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
		} finally {
			post.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static boolean PUT(String route, NameValuePair[] data, String Token,int expectedStatus) {
		boolean result = false;
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// Create a method instance.
		PutMethod put = new PutMethod(url+route);
		put.setQueryString(data);
		// put.setRequestBody(data);
		// put.addParameters(data);
		// put.setRequestBody(data);
		put.setRequestHeader("Authorization", "Bearer " + Token);

		try {
			// Execute the method.
			int statusCode = client.executeMethod(put);
			System.out.println(route + "Status code :" + statusCode);
			if (statusCode == expectedStatus)
				{System.out.println(route+ " : succeed");
				result = true;}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			System.out.println(route+ " : failed");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
			
		} finally {
			put.releaseConnection();
		}
		return result;
	}

	public static boolean Get(String route, NameValuePair[] data, String Token, int expectedStatusCode)

	{
		boolean result = false;

		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod(url+route);
		method.setQueryString(data);
		method.setRequestHeader("Authorization", "Bearer " + Token);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println(route + " Status code :" + statusCode);
			if (statusCode == expectedStatusCode)
				{System.out.println(route+ " : succeed");
			result = true;}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
			
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	public static boolean DELETE(String route, NameValuePair[] data, String Token,int expectedStatus)
			throws Exception {
		boolean result = false;
		HttpClient client = new HttpClient();
		DeleteMethod method = new DeleteMethod(route);
		// client.executeMethod(method);
		/// int status=method.getStatusCode();
		method.setRequestHeader("Authorization", "Bearer " + Token);
		method.setQueryString(data);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println(route + " Status code :" + statusCode);
			if (statusCode == expectedStatus)
			{	result = true;
			System.out.println(route+ " : succeed");}
			
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(route+ " : failed");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(route+ " : failed");
			return result;
		} finally {
			method.releaseConnection();
		}
		return result;
		// if (status == 404) throw new IllegalArgumentException("Dataset does not
		// exist.");
		// if (status == 503) throw new IllegalStateException("Service error: " +
		// status);
	}

	public static JSONArray GetMoves_Attacks_Placement(String route, String Token, String ArrayName,
			NameValuePair[] data,int expectedStatus) throws JSONException {
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod(url+ route);

		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.setRequestHeader("Authorization", "Bearer " + Token);
		method.setQueryString(data);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println(route + " Status code :" + statusCode);

			// Read the response body.
			if (statusCode == expectedStatus) {
				String responseBody = method.getResponseBodyAsString();
				System.out.println(responseBody);

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary data
				// System.out.println(new String(responseBody));
				// String json = new String(responseBody);
				JSONObject object = new JSONObject(responseBody);
				JSONArray array = object.getJSONArray(ArrayName);
				for (int i = 0; i < array.length(); i++)
					System.out.println("x= " + array.getJSONObject(i).getInt("xCoordinate") + " y = "
							+ array.getJSONObject(i).getInt("yCoordinate"));
				System.out.println(route+ " : succeed");

				return array;
				// https://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java
			} else
				System.out.println(route+ " : failed");
				return null;

		} catch (HttpException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();

		}
	}

	public static int GetBaseID(String Username) throws JSONException {

		NameValuePair[] data = { new NameValuePair("username", Username) };
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod("https://gameengine-dot-training-project-lab.appspot.com/get_state");
		method.setQueryString(data);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// method.setRequestHeader("Authorization", "Bearer " + Username);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Get Base iD"+ " Status code :" + statusCode);

			// Read the response body.
			String responseBody = method.getResponseBodyAsString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			// System.out.println(new String(responseBody));
			JSONObject object = new JSONObject(responseBody);
			JSONArray array = object.getJSONArray("bases");
			for (int i = 0; i < array.length(); i++)
				if (array.getJSONObject(i).getString("playerBelongsTo").trim().equals(Username.trim())) {
					System.out.println("baseID= " + array.getJSONObject(i).getInt("id") + " player  = "
							+ array.getJSONObject(i).getString("playerBelongsTo"));
					return array.getJSONObject(i).getInt("id");
				}
			// https://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java

		} catch (HttpException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			method.releaseConnection();

		}
		return -1;
	}

	public static ArrayList GetUnitId(String Username) throws JSONException {
		ArrayList<Integer> UnitsID = new ArrayList();
		NameValuePair[] data = { new NameValuePair("username", Username) };
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod("https://gameengine-dot-training-project-lab.appspot.com/get_state");
		method.setQueryString(data);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//method.setRequestHeader("Authorization", "Bearer " + Username);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("getState : " + "Status code :" + statusCode);

			// Read the response body.
			String responseBody = method.getResponseBodyAsString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			// System.out.println(new String(responseBody));
			JSONObject object = new JSONObject(responseBody);
			JSONArray array = object.getJSONArray("units");
			for (int i = 0; i < array.length(); i++)
				if (array.getJSONObject(i).getString("playerBelongsTo").trim().equals(Username.trim())) {
					System.out.println("UnitID= " + array.getJSONObject(i).getInt("id") + " player  = "
							+ array.getJSONObject(i).getString("playerBelongsTo"));
					UnitsID.add(array.getJSONObject(i).getInt("id"));

				}
			return UnitsID;
			// https://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java

		} catch (HttpException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();

		}
	}

	public static void main(String[] args) throws Exception {

		// Scenario 1:
		NameValuePair[] empty = {};

		/* Authentication */

		System.out.println("**********************************Authentication**************************************");
		String token_user1;
		String token_user2;
		NameValuePair[] User1 = { new NameValuePair("name", "mahdi"), new NameValuePair("lastName", "karray"),
				new NameValuePair("email", "karraymahdi1"), new NameValuePair("password", "123456789")
		};
		NameValuePair[] User2 = { new NameValuePair("name", "houssam"), new NameValuePair("lastName", "mahdi"),
				new NameValuePair("email", "Houssam_mahdi1"), new NameValuePair("password", "123456789")
		};
		NameValuePair[] Fake_User = { new NameValuePair("email","Houssam_mahdi_98@gmail.com"),
				new NameValuePair("password", "987654321") };

		System.out.println("********signUp********");
		token_user1 = Log_in_SignUp("signUp", User1,200);
		token_user2 = Log_in_SignUp("signUp", User2,200);
		
		System.out.println("********logOut********");
		NameValuePair[] TokenUser1_SignUp = { new NameValuePair("token", token_user1) };
		NameValuePair[] TokenUser2_SignUp = { new NameValuePair("token", token_user2) };
	
		System.out.println(POST("logOut",TokenUser1_SignUp, "", 200));
		System.out.println(POST("logOut",TokenUser2_SignUp, "", 200));
		
		NameValuePair[] User_1_1 = {	new NameValuePair("email", "karraymahdi1"), new NameValuePair("password", "123456789")};
		NameValuePair[] User_2_2 = {	new NameValuePair("email", "Houssam_mahdi1"), new NameValuePair("password", "123456789")};
		System.out.println("********logIn********");
		token_user1 = Log_in_SignUp("logIn", User_1_1,200);
		token_user2 = Log_in_SignUp("logIn", User_2_2,200);
		
		System.out.println("Fake User:  " + Log_in_SignUp("logIn", Fake_User,400));
		NameValuePair[] TokenUser1 = { new NameValuePair("token", token_user1) };
		NameValuePair[] TokenUser2 = { new NameValuePair("token", token_user2) };
		/* Lobby */
		System.out.println("**********************************Lobby**************************************");
		// New Game Lobby
		System.out.println("********New Game Lobby********");
		NameValuePair[] NewGameLobby = { new NameValuePair("playerNumber", "2") };
		System.out.println(" New Game lobby / True: " +POST("newGameLobby", NewGameLobby,token_user1,200));
		System.out.println("********joinGameLobby********");
		// joinGameLobby
		String GameId =GetGameID("karraymahdi1");
		NameValuePair[] GameID = { new NameValuePair("GameID",GameId) };
		System.out.println(" joinGameLobby /True (Player 1) : " +PUT("joinGameLobby", GameID, token_user1,200));
		System.out.println(" joinGameLobby /True (Player 2): " + PUT("joinGameLobby", GameID, token_user2,200));
		// setSeed
		System.out.println("********setSeed********");
		NameValuePair[] seed = { new NameValuePair("seed", "0") };
		System.out.println(" setSeed /True : " + PUT("setSeed", seed, token_user1,200));
		System.out.println("********Ready********");
		// Ready
		System.out.println(" Ready /True : " + PUT("Ready", empty, token_user1,200));
		System.out.println("********UnReady********");
		// Unready user1
		System.out.println(" Unready user1 /True : " + PUT("unReady", empty, token_user1,200)); 
		System.out.println("********Ready********");
		// Ready User1
		System.out.println(" ready user1 /True : " + PUT("Ready", empty, token_user1,200));
		System.out.println(" Ready user 2  /True : " + PUT("Ready", empty, token_user2,200));
		System.out.println("********getGameList********");
		// getGameList //
		//System.out.println(" getGameList /True : " + Get("getGameList", empty, token_user1, 200));

		/* Game Engine */
		System.out.println("**********************************Game Engine **************************************");
		// First player
		System.out.println("***************FIRST PLAYER ***************");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
			    // Your database code here
			  }
			},6000);
		int BaseIDBob = (GetBaseID("karraymahdi1"));
		// Get Placement
		NameValuePair[] getplacment = {
				new NameValuePair("baseID", Integer.toString(BaseIDBob)) };
		System.out.println("********getPlacement********");
		JSONArray placement = GetMoves_Attacks_Placement("getPlacement",token_user1, "positions", getplacment,200);
		System.out.println(placement);
		
		
		NameValuePair[] CreateUnit = {
				new NameValuePair("xCoord", Integer.toString(placement.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(placement.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("type", "RANGED"), new NameValuePair("baseID", Integer.toString(BaseIDBob)) };
		
		
		System.out.println("********Create unit********");
		System.out.println("Create unit : "
				+ PUT("createUnit", CreateUnit, token_user1,200));
		// getMoves
		System.out.println("********GetUnitId********");
		ArrayList<Integer> UnitIDBob = GetUnitId("karraymahdi1");
		System.out.println(UnitIDBob);
		NameValuePair[] getmoves = { 
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		System.out.println("********getMoves********");
		JSONArray getMoves = GetMoves_Attacks_Placement("getMoves", token_user1, "positions", getmoves,200);
		System.out.println("Get moves :" + getMoves);
		// Move
		NameValuePair[] move = {
				new NameValuePair("xCoord", Integer.toString(getMoves.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getMoves.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		System.out.println("********Move********");
		System.out.println(
				"Move : " + PUT("move", move, token_user1,200));

		// End turn First player
		NameValuePair[] endturnBob = { new NameValuePair("username", "karraymahdi") };
		System.out.println("********Endturn********");
		System.out.println("Endturn : "
				+ PUT("endTurn", empty, token_user1,200));

		// end first player

		// second player
		System.out.println("*************** Second Player *******************");
		System.out.println("********GetBaseID********");
		int BaseIDAlice = (GetBaseID("Houssam_mahdi1"));
		// Get Placement
		NameValuePair[] getplacmentAlice = { 
				new NameValuePair("baseID", Integer.toString(BaseIDAlice)) };
		System.out.println("********getPlacement********");
		JSONArray placementAlice = GetMoves_Attacks_Placement("getPlacement",token_user2, "positions", getplacmentAlice,200);
		System.out.println(placementAlice);

		NameValuePair[] CreateUnit1 = {
				new NameValuePair("xCoord", Integer.toString(placementAlice.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(placementAlice.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("type", "MELEE"), new NameValuePair("baseID", Integer.toString(BaseIDAlice))
				 };
		System.out.println("********Create unit********");
		System.out.println("Create unit : "
				+ PUT("createUnit", CreateUnit1, token_user2,200));

		// getAttack
		System.out.println("********GetUnitId********");
		ArrayList<Integer> UnitIDAlice = GetUnitId("Houssam_mahdi1");
		System.out.println("unit id = " + UnitIDAlice);

		NameValuePair[] getmovesAlice = {
				new NameValuePair("unitID", Integer.toString(UnitIDAlice.get(0))) };
		System.out.println("********getAttacks********");
		JSONArray getMovesAlice = GetMoves_Attacks_Placement("getAttacks", token_user2, "positions", getmovesAlice,200);
		System.out.println("Get Attack :" + getMovesAlice);

		// Attack
		NameValuePair[] AttackAlice = {
				new NameValuePair("xCoord", Integer.toString(getMovesAlice.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getMovesAlice.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDAlice.get(0)))
			 };
		System.out.println("********Attack********");
		System.out.println("Attack : "
				+ PUT("attack", AttackAlice,token_user2,200));

		// End turn second player
		
		NameValuePair[] endturnAlice = { new NameValuePair("username", "Houssam_mahdi1") };
		System.out.println("********Endturn********");
		System.out.println("Endturn : "
				+ PUT("endTurn", empty, token_user2,200));
		System.out.println("***************FIRST PLAYER ***************");
		// BOB ATTACK
		NameValuePair[] getAttackBob = { 
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		System.out.println("********getAttacks********");
		JSONArray getAttackbob = GetMoves_Attacks_Placement("getAttacks", token_user1, "positions", getAttackBob,200);
		System.out.println("Get Attack :" + getAttackbob);

		NameValuePair[] AttackBob = {
				new NameValuePair("xCoord", Integer.toString(getAttackbob.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getAttackbob.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		System.out.println("********Attack********");
		System.out.println("Attack : "
				+ PUT("attack", AttackBob, token_user1,200));

//End turn Bob
		System.out.println("********Endturn********");
		System.out.println("Endturn : "
				+ PUT("endTurn", endturnBob,token_user1,200));
//System.out.println("***************Second PLAYER ***************");
		
		/*
// Alice ATTACK 
		System.out.println("********Attack********");
		System.out.println("Attack : "
				+ PUT("attack", AttackAlice, token_user2,200));
	
		NameValuePair[] DeleteUser1 = { new NameValuePair("email", "Houssam_mahdi_98@hotmail.com"),
			 };
		System.out.println(DELETE("https://security-dot-training-project-lab.appspot.com/deleteaccount", DeleteUser1, "",200));
	
	*/
	}
	
	
}

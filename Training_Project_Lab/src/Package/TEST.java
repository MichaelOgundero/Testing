package Package;

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
	private static String url = "https://webhook.site/b3ede3ed-e77c-4730-be61-e820f51e844b";

	public static String Log_in(String route, NameValuePair[] data, int expectedStatusCode)
			throws JSONException, IOException {
		// boolean result = false ;
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		PostMethod post = new PostMethod("https://security-dot-training-project-lab.appspot.com/" + route);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		post.setQueryString(data);
		try {
			int statusCode = client.executeMethod(post);
			System.out.println("Expected=" + expectedStatusCode + " Status code :" + statusCode);
			// Read the response body.
			String responseBody = post.getResponseBodyAsString();
			// System.out.println(responseBody);
			JSONObject object = new JSONObject(responseBody);
			String loc = object.getString("token");
			// System.out.println(loc);
			return loc;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(post.getResponseBodyAsString());
			return null;

		} catch (IOException e) {
			System.out.println(post.getResponseBodyAsString());
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
		GetMethod method = new GetMethod("https://training-project-lab.appspot.com/GetGameList");
		// method.setQueryString(data);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.setRequestHeader("Authorization", "Bearer " + Token);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Status code :" + statusCode);

			// Read the response body.
			String responseBody = method.getResponseBodyAsString();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			// System.out.println(new String(responseBody));
			String json = new String(responseBody);
			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray("ListGameLobby");
			return array.getJSONObject(0).getString("GameID");
			// https://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java

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

	public static boolean POST(String route, NameValuePair[] data, String Token, int expectedStatusCode) {
		boolean result = false;
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Create a method instance.
		PostMethod post = new PostMethod(route);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// post.setQueryString(data);
		// post.addParameters(data);
		post.setRequestBody(data);
		// post.setRequestEntity(new StringRequestEntity(data,
		// "application/json","UTF-8"));
		// Bearer
		post.setRequestHeader("Authorization", "Bearer " + Token);
		// Execute the method.
		try {
			int statusCode = client.executeMethod(post);
			System.out.println("Expected=" + expectedStatusCode + " Status code :" + statusCode);
			if (statusCode == expectedStatusCode)
				result = true;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return result;
		} finally {
			post.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static boolean PUT(String route, NameValuePair[] data, String Token, int expectedStatusCode) {
		boolean result = false;
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// Create a method instance.
		PutMethod put = new PutMethod(route);
		put.setQueryString(data);
		// put.setRequestBody(data);
		// put.addParameters(data);
		// put.setRequestBody(data);
		put.setRequestHeader("Authorization", "Bearer " + Token);

		try {
			// Execute the method.
			int statusCode = client.executeMethod(put);
			System.out.println("Status code :" + statusCode);
			if (statusCode == expectedStatusCode)
				result = true;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		GetMethod method = new GetMethod(route);
		method.setQueryString(data);
		method.setRequestHeader("Authorization", "Bearer " + Token);
		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Status code :" + statusCode);
			if (statusCode == expectedStatusCode)
				result = true;

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	public static boolean DELETE(String route, NameValuePair[] data, String Token, int expectedStatusCode)
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
			System.out.println("Status code :" + statusCode);
			if (statusCode == expectedStatusCode)
				result = true;

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			NameValuePair[] data) throws JSONException {
		HttpClient client = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod("https://gameengine-dot-training-project-lab.appspot.com/" + route);

		// Provide custom retry handler is necessary
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.setRequestHeader("Authorization", "Bearer " + Token);
		method.setQueryString(data);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Status code :" + statusCode);

			// Read the response body.
			if (statusCode == 200) {
				String responseBody = method.getResponseBodyAsString();

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary data
				// System.out.println(new String(responseBody));
				// String json = new String(responseBody);
				JSONObject object = new JSONObject(responseBody);
				JSONArray array = object.getJSONArray(ArrayName);
				for (int i = 0; i < array.length(); i++)
					System.out.println("x= " + array.getJSONObject(i).getInt("xCoordinate") + " y = "
							+ array.getJSONObject(i).getInt("yCoordinate"));
				return array;
				// https://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java
			} else
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
		// method.setRequestHeader("Authorization", "Bearer " + Token);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Status code :" + statusCode);

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
		// method.setRequestHeader("Authorization", "Bearer " + Token);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			System.out.println("Status code :" + statusCode);

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

		/*
		 * System.out.println("Authentication"); String token_user1 ; String token_user2
		 * ; NameValuePair [] User1= { new NameValuePair("name","mahdi"), new
		 * NameValuePair("last_name","karray"), new
		 * NameValuePair("email","karraymahdi"), new
		 * NameValuePair("password","123456789")
		 * 
		 * }; NameValuePair [] User2= { new NameValuePair("name","houssam"), new
		 * NameValuePair("last_name","mahdi"), new
		 * NameValuePair("email","Houssam_mahdi"), new
		 * NameValuePair("password","123456789")
		 * 
		 * }; NameValuePair [] Fake_User= {new
		 * NameValuePair("email","Houssam_mahdi_98@gmail.com"), new
		 * NameValuePair("password","987654321")};
		 * 
		 * token_user1=Log_in("signup",User1, 200);
		 * System.out.println("sign_up user 1 (result should be 400) : "+
		 * Log_in("signup",User1, 400)); token_user2=Log_in("signup",User2, 200);
		 * System.out.println("sign_up user(result should be 400) :  "+
		 * Log_in("signup",User2, 400)); NameValuePair [] TokenUser1= { new
		 * NameValuePair("token",token_user1)}; NameValuePair [] TokenUser2= { new
		 * NameValuePair("token",token_user2)}; System.out.println(POST(
		 * "https://security-dot-training-project-lab.appspot.com/logout",TokenUser1,"",
		 * 200)); System.out.println(POST(
		 * "https://security-dot-training-project-lab.appspot.com/logout",TokenUser2,"",
		 * 200)); token_user1=Log_in("login",User1, 200);
		 * System.out.println("login_user1 (result should be 400) :  "+
		 * Log_in("login",User1, 400)); token_user2=Log_in("login",User2, 200);
		 * System.out.println("log_in user2 (result should be 400) :  "+
		 * Log_in("login",User2, 400)); System.out.println("Fake User:  "+
		 * Log_in("login",Fake_User, 400)); //System.out.println(token_user1);
		 * //System.out.println(token_user2);
		 * 
		 * /*NameValuePair [] User1= { new
		 * NameValuePair("email","Houssam_mahdi_98@hotmail.com"), new
		 * NameValuePair("password","123456789") }; System.out.println(DELETE(
		 * "https://security-dot-training-project-lab.appspot.com/deleteaccount",User1,
		 * "",200));
		 */

		/* Lobby */

		/*
		 * System.out.println("Lobby");
		 * 
		 * //New Game Lobby NameValuePair [] NewGameLobby= {new
		 * NameValuePair("playerNumber","2")};
		 * System.out.println(" New Game lobby / True: "+
		 * POST("new_game_lobby",NewGameLobby,"",201));
		 * System.out.println(" New Game lobby /False: "+
		 * POST("new_game_lobby",empty,"",400));
		 * 
		 * 
		 * //joinGameLobby NameValuePair [] GameID= {new
		 * NameValuePair("GameID",GetGameID(""))};
		 * System.out.println(" joinGameLobby /True : "+
		 * PUT("joinGameLobby",GameID,token_user1,200));// Should get GameID as
		 * parameter System.out.println(" joinGameLobby /True : "+
		 * PUT("joinGameLobby",GameID,token_user2,200));// Should get GameID as
		 * parameter //setSeed NameValuePair [] seed= {new NameValuePair("seed","0")};
		 * System.out.println(" setSeed /True : "+ PUT("setSeed",seed,"",200));
		 * 
		 * //unReady System.out.println(" setSeed /True : "+
		 * PUT("unReady",empty,token_user1,400));
		 * System.out.println(" setSeed /True : "+
		 * PUT("unReady",empty,token_user2,400)); //Ready
		 * System.out.println(" setSeed /True : "+ PUT("Ready",empty,token_user1,200));
		 * System.out.println(" setSeed /True : "+ PUT("Ready",empty,token_user2,200));
		 * // Unready user1 System.out.println(" setSeed /True : "+
		 * PUT("unReady",empty,token_user1,400)); //Ready User1
		 * System.out.println(" setSeed /True : "+ PUT("Ready",empty,token_user1,200));
		 * 
		 * //getGameList // System.out.println(" getGameList /True : "+
		 * Get("get_game_list",empty,"",200));
		 * 
		 */

		/* Game Engine */
		System.out.println("Game Engine");

		// First player
		// first player
		System.out.println("***************FIRST PLAYER ***************");
		int BaseIDBob = (GetBaseID("Bob"));
		// Get Placement
		NameValuePair[] getplacment = { new NameValuePair("username", "Bob"),
				new NameValuePair("baseID", Integer.toString(BaseIDBob)) };
		JSONArray placement = GetMoves_Attacks_Placement("get_placement", "", "positions", getplacment);
		System.out.println(placement);
		NameValuePair[] CreateUnit = {
				new NameValuePair("xCoord", Integer.toString(placement.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(placement.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("type", "RANGED"), new NameValuePair("baseID", Integer.toString(BaseIDBob)),
				new NameValuePair("username", "Bob") };
		System.out.println("Create unit : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/create_unit", CreateUnit, "", 200));
		// getMoves
		ArrayList<Integer> UnitIDBob = GetUnitId("Bob");
		System.out.println(UnitIDBob);
		NameValuePair[] getmoves = { new NameValuePair("username", "Bob"),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		JSONArray getMoves = GetMoves_Attacks_Placement("get_moves", "", "positions", getmoves);
		System.out.println("Get moves :" + getMoves);
		// Move
		NameValuePair[] move = {
				new NameValuePair("xCoord", Integer.toString(getMoves.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getMoves.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))), new NameValuePair("username", "Bob") };
		System.out.println(
				"Move : " + PUT("https://gameengine-dot-training-project-lab.appspot.com/move", move, "", 200));

		// End turn First player
		NameValuePair[] endturnBob = { new NameValuePair("username", "Bob") };
		System.out.println("Endturn : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/end_turn", endturnBob, "", 200));

		// end first player

		// second player
		System.out.println("*************** Second Player *******************");
		int BaseIDAlice = (GetBaseID("Alice"));
		// Get Placement
		NameValuePair[] getplacmentAlice = { new NameValuePair("username", "Alice"),
				new NameValuePair("baseID", Integer.toString(BaseIDAlice)) };
		JSONArray placementAlice = GetMoves_Attacks_Placement("get_placement", "", "positions", getplacmentAlice);
		System.out.println(placementAlice);

		NameValuePair[] CreateUnit1 = {
				new NameValuePair("xCoord", Integer.toString(placementAlice.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(placementAlice.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("type", "MELEE"), new NameValuePair("baseID", Integer.toString(BaseIDAlice)),
				new NameValuePair("username", "Alice") };
		System.out.println("Create unit : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/create_unit", CreateUnit1, "", 200));

		// getAttack
		ArrayList<Integer> UnitIDAlice = GetUnitId("Alice");
		System.out.println("unit id = " + UnitIDAlice);

		NameValuePair[] getmovesAlice = { new NameValuePair("username", "Alice"),
				new NameValuePair("unitID", Integer.toString(UnitIDAlice.get(0))) };
		JSONArray getMovesAlice = GetMoves_Attacks_Placement("get_attacks", "", "positions", getmovesAlice);
		System.out.println("Get Attack :" + getMovesAlice);

		// Attack
		NameValuePair[] AttackAlice = {
				new NameValuePair("xCoord", Integer.toString(getMovesAlice.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getMovesAlice.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDAlice.get(0))),
				new NameValuePair("username", "Alice") };
		System.out.println("Attack : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/attack", AttackAlice, "", 200));

		// End turn second player
		NameValuePair[] endturnAlice = { new NameValuePair("username", "Alice") };
		System.out.println("Endturn : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/end_turn", endturnAlice, "", 200));

		// BOB ATTACK
		NameValuePair[] getAttackBob = { new NameValuePair("username", "Bob"),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))) };
		JSONArray getAttackbob = GetMoves_Attacks_Placement("get_attacks", "", "positions", getAttackBob);
		System.out.println("Get Attack :" + getAttackbob);

		NameValuePair[] AttackBob = {
				new NameValuePair("xCoord", Integer.toString(getAttackbob.getJSONObject(0).getInt("xCoordinate"))),
				new NameValuePair("yCoord", Integer.toString(getAttackbob.getJSONObject(0).getInt("yCoordinate"))),
				new NameValuePair("unitID", Integer.toString(UnitIDBob.get(0))), new NameValuePair("username", "Bob") };
		System.out.println("Attack : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/attack", AttackBob, "", 200));

//End turn Bob
		System.out.println("Endturn : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/end_turn", endturnBob, "", 200));

// Alice ATTACK 
		System.out.println("Attack : "
				+ PUT("https://gameengine-dot-training-project-lab.appspot.com/attack", AttackAlice, "", 200));

	}
}

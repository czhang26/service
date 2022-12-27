
package com.azure.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.azure.db.Person;
import com.azure.db.SQLDatabaseConnection;
import com.google.gson.Gson;


/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }
    
	@GET
	@Path("/{email}")
	public Response getPerson(@PathParam("email") String email) {
		
		SQLDatabaseConnection conn = new SQLDatabaseConnection();
		Person person = conn.connection(email);
		Gson gson = new Gson();
	    String jsonResp = gson.toJson(person);
	    return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
	}
}

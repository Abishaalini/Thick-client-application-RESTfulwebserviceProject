package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class EmployeesAPI
 */
@WebServlet("/EmployeesAPI")
public class EmployeesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Employee employeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return employeeObj.readEmployee();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("EmployeeName") String EmployeeName,
			@FormParam("EmployeeEmail") String EmployeeEmail, @FormParam("EmployeeType") String EmployeeType,
			@FormParam("EmployeeContact") String EmployeeContact) {
		String output = employeeObj.insertEmployee(EmployeeName, EmployeeEmail, EmployeeType, EmployeeContact );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData) {
		// Convert the input string to a JSON object
		JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
		// Read the values from the JSON object
		String EmployeeID = employeeObject.get("EmployeeID").getAsString();
		String EmployeeName = employeeObject.get("EmployeeName").getAsString();
		String EmployeeEmail = employeeObject.get("EmployeeEmail").getAsString();
		String EmployeeType = employeeObject.get("EmployeeType").getAsString();
		String EmployeeContact = employeeObject.get("EmployeeContact").getAsString();
		
		String output = employeeObj.updateEmployee(EmployeeID, EmployeeName, EmployeeEmail, EmployeeType,
				EmployeeContact );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

		// Read the value from the element <EmployeeID>
		String EmployeeID = doc.select("EmployeeID").text();
		String output = employeeObj.deleteEmployee(EmployeeID);
		return output;
	}

	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = employeeObj.insertEmployee(request.getParameter("EmployeeName"),
				request.getParameter("EmployeeEmail"),
				request.getParameter("EmployeeType"),
				request.getParameter("EmployeeContact"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = employeeObj.updateEmployee(paras.get("hidItemIDSave").toString(),
		paras.get("EmployeeName").toString(),
		paras.get("EmployeeEmail").toString(),
		paras.get("EmployeeType").toString(),
		paras.get("EmployeeContact").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = employeeObj.deleteEmployee(paras.get("EmployeeID").toString());
		response.getWriter().write(output);
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{
		String[] p = param.split("=");
	map.put(p[0], p[1]);
	}
	}
	catch (Exception e)
	{
	}
	return map;
	}

}

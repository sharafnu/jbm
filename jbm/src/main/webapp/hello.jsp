<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Date"%>
hi...<%=new Date()%>

<%
	Connection connection;
	try {
		Class.forName("org.postgresql.Driver");

		//String host = "jdbc:postgresql://postgres-jbm-sharaf.jelastic.servint.net/postgres";
		//String host = "jdbc:postgresql://localhost:5432/jbm";
		String host = "jdbc:postgresql://localhost:5432/maxmaidn_test";
		response.getWriter().println(host + "<br/><br/>");
		connection = DriverManager.getConnection(host, "maxmaidn",
				"84gC2Bmn6j");
		System.out.println("CONNECTION: " + connection);
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("select prop_key from system_property");
		while (rs.next()) {
			out.println(rs.getString("prop_key"));
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		out.println(e);
	} catch (SQLException e) {
		e.printStackTrace();
		out.println(e);
	} catch (Exception e) {
		out.println(e);
	}
%>
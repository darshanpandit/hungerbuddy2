<%@page import="com.pandit.search.OutputWrapper"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.TreeSet"%>
<%@page import="com.pandit.core.CustomDocument"%>
<%@page import="com.pandit.search.HungerConfig"%>
<%@page import="com.pandit.search.SearchResult"%>
<%@page import="com.pandit.process.QueryProcessSingleton"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.lucene.document.Document"%>
<%@page import="org.apache.lucene.document.Fieldable"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>hungerBuddy</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>


<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="ico/favicon.png">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top ">
		<div class="navbar-inner mybar">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">hungerBuddy</a>
				<div class="nav-collapse collapse">
					<form method="POST" class="form-search"
						action="/hungerBuddy2/index.jsp">
						<input type="text" name="query" class="input-xxlarge search-query">
						<button type="submit" class="btn">Search</button>

					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class='container'>
		<%
		    String query = request.getParameter("query");
		    if (query == null || query.length() == 0) {
		%>
		<h1>Tell Me, What are you looking for Today??</h1>
		<%
		    } else {
			QueryProcessSingleton processSingleton = QueryProcessSingleton
				.getInstance();
			OutputWrapper outputWrapper = processSingleton.process(query);
			SearchResult searchResult = outputWrapper.getSearchResult();
			String query1 = outputWrapper.getQuery();
			out.println("<h4>"+query1+"</h4>");
			String message = outputWrapper.getMessage();
			out.println("<h5>"+message+"</h5>");
				%><div class='accordion' id='accordion2'>
				<%
				if (searchResult != null && searchResult.getTotalHits() > 0) {
				    Set<Document> set = searchResult.getDocumentSet();
				   // List<CustomDocument> list = new ArrayList<CustomDocument>();
				   int i=0;
				    for (Document document : set) {
						i++;
						CustomDocument customDocument = new CustomDocument();
						Set<String> fields = new TreeSet<String>();

						for (Fieldable fieldable : document.getFields()) {
						    fields.add(fieldable.name());
						}

						for (String temp : fields) {
					 	   customDocument.putAll(temp,
							    Arrays.asList(document.getValues(temp)));
						}
						//list.add(customDocument);
						//String document = 
						//out.println("var candidates={"+customDocument.toJSON()+"}");
						%><div class="bs-docs-example">
							<div class='accordion-group'>
   								 <div class='accordion-heading'>
      								<a class='accordion-toggle' data-toggle='collapse' data-parent='#accordion2' href='#collapse<%out.print(i);%>'><%out.print(customDocument.get(HungerConfig.field_name));%></a>
    							</div>
    							<%if(i==1){ %>
    							<div id='collapse<%out.print(i);%>' class='accordion-body collapse in'> 
    								<div class='accordion-inner'><%out.println("Type: "+customDocument.get(HungerConfig.field_type));
    								out.println("<br>Location: "+customDocument.get(HungerConfig.field_location));
    								out.println("<br>FoursquareId: "+customDocument.get(HungerConfig.field_id));
    								out.println("<br>Contact: "+customDocument.get(HungerConfig.field_contact));
    								out.println("<br>Serves: "+customDocument.get(HungerConfig.field_food));
    								out.println("<br>Comments: "+customDocument.get(HungerConfig.field_commment));%>
      								</div>
      							</div>
    							<% }else{ %>
    							<div id='collapse<%out.print(i);%>' class='accordion-body collapse in'>
    							 	<div class='accordion-inner'><%out.println("Type: "+customDocument.get(HungerConfig.field_type));
    							 	out.println("<br>Location: "+customDocument.get(HungerConfig.field_location));
    							 	out.println("<br>FoursquareId: "+customDocument.get(HungerConfig.field_id));
    							 	out.println("<br>Contact: "+customDocument.get(HungerConfig.field_contact));
    							 	out.println("<br>Serves: "+customDocument.get(HungerConfig.field_food));
    							 	out.println("<br>Comments: "+customDocument.get(HungerConfig.field_commment));%>
      								</div>
      							</div>
    							<%	} %>
     								
    							
 							 </div>
						<%
				    }
				    //Gson gson = new Gson();
				    //String json = gson.toJson(list);
		
			    }
				%>
				</div></div><%	   }
		%>
	</div>
	<!-- /container -->
<script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap-transition.js"></script>
    <script src="../assets/js/bootstrap-alert.js"></script>
    <script src="../assets/js/bootstrap-modal.js"></script>
    <script src="../assets/js/bootstrap-dropdown.js"></script>
    <script src="../assets/js/bootstrap-scrollspy.js"></script>
    <script src="../assets/js/bootstrap-tab.js"></script>
    <script src="../assets/js/bootstrap-tooltip.js"></script>
    <script src="../assets/js/bootstrap-popover.js"></script>
    <script src="../assets/js/bootstrap-button.js"></script>
    <script src="../assets/js/bootstrap-collapse.js"></script>
    <script src="../assets/js/bootstrap-carousel.js"></script>
    <script src="../assets/js/bootstrap-typeahead.js"></script>
	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	
</body>
</html>
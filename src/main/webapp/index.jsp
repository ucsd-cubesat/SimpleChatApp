<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!-- JSP uses 'Tags' to facilitate embedding dynamically computed content into the page.
    JSTL is one library for such JSP Tags, whose usage is explained below. Here's a guide:
    https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>JSP Example</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
    <!-- Example 1: Pure Java
        In this example, we use the % (scriptlet) tag to embed pure Java code. This code runs on the sever,
        meaning it does not appear to the user when this page is loaded in the web browser.
        Instead, each time this page is requested, the server runs this code to produce content.

        First thing to note: the implicit 'out' object is an object that is always available in Java
        code. This object actually produces the content into the page. For more info, visit
        https://www.dineshonjava.com/jsp-out-object/

        In the first scriptlet, we write a simple HTML paragraph.
        In the second scriptlet, we make an unordered list by generating each list item with a for-loop.

        NOTE: We want to avoid scriptlets in general, so try to use JSTL tags instead.
    -->
    <h1>Example 1</h1>
    <%
        out.print("<p>This paragraph was made with JSP!</p>");
    %>
    <p>This paragraph was made with plain HTML!</p>
    
    <p>This is a list generated with JSP:</p>
    <ul>
    <%
        for (int i = 0; i < 10; i++) {
            out.print("<li> List Item i = "+i+"</li>");
        }
    %>
    </ul>

    <!-- Example 2: Simple Output Tag
        The %= (out) tag can render a single Java expression. Expressions don't allow if-else,
        loops, or variables. Instead, you may use single lines of code that produce a value.

        This example makes function calls that return values.
    -->
    <h1>Example 2</h1>
    <p>Max of 100 and 10 is: <%= Math.max(100, 10) %></p>
    <p>Did you know: <%= "A string literal counts as an expression." %></p>
    <p>OS of server: <%= System.getProperty("os.name") %></p>

    <!-- Example 3: JSTL Variables and EL
        The set tag can create variables that are usable from other JSTL tags.
        Finally, the out tag can output a value created by a set tag using EL.

        EL stands for expression language. You can enclose an EL statement in '\${}' and it will be
        evaluated to produce a value. Some IDEs can autocomplete in here.
    -->
    <h1>Example 3</h1>
    <c:set var="my_variable" value="Some sort of value..." />
    <p>my_variable = <c:out value="${my_variable}"/></p>
    <p><c:out value="This paragraph was created without EL."/></p>

    <!-- Example 4: Conditionals
        The if tag evaluates its inner tags only if the specified condition is true.

        NOTE: The 'pageContext' object is also an implicit object. Read more about it here:
        https://www.javatpoint.com/pageContext-implicit-object
    -->
    <% pageContext.setAttribute("millis", System.currentTimeMillis()); %>
    <c:if test="${millis % 2 == 0}">
        <h1>Example 4</h1>
        <p>This only appears when the server's current time is an even number!</p>
    </c:if>

    <!-- Import Statements
        If you need to import classes into the page, use the import page like so.
        Then you can use the class later.
    -->
    <%@ page import="java.util.Date" %>
    <%
        Date date = new Date();
    %>

</body>
</html>
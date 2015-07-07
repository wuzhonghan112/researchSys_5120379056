<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="welcome.page.title" /></title>
</head>
<body>
    <form name="form1" action="logout.do" method="post">
        <table width="200" border="1">
            <tr>
                <td colspan="2"><bean:message key="welcome.page.title" /></td>
            </tr>
            <tr>
                <td><bean:message key="welcome.page.username"/></td>
                <td> <%=(String)session.getAttribute("username")%> </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="submit" 
                value="<bean:message key='welcome.page.logout' />"></td>
            </tr>
        </table>
    </form>
</body>
</html>
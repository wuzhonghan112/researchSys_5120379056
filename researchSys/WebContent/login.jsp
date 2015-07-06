<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>   
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="login.page.title" /></title>
</head>
<body>
    <form name="form" action="login.do" method="post">
        <table width="320" border="1">
            <tr>
                <td><bean:message key="login.page.username" /></td>
                <td>
                    <logic:present name="loginFormBean">
                        <html:text property="username" name="loginFormBean" />
                    </logic:present>
                    <logic:notPresent name="loginFormBean">
                        <input type="text" name="username" size="10">
                    </logic:notPresent>
                    <html:errors property="username"/>
                </td>
            </tr>
            <tr>
                <td><bean:message key="login.page.password" /></td>
                <td>
                    <logic:present name="loginFormBean">
                        <html:password property="password" name="loginFormBean" />
                    </logic:present>
                    <logic:notPresent name="loginFormBean">
                        <input type="password" name="password" size="10">
                    </logic:notPresent>
                    <html:errors property="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="submit"
                      value="<bean:message key='login.page.login' />">
                </td>
            </tr>
            <tr>
                <td>
                    <a href="register.do?method=init">
                        <bean:message key="login.page.register" />
                    </a>
                </td>
            </tr>
        </table>
        <html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
    </form>
</body>
</html>
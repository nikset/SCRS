<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Autenticazione-Uni</title>
</head>
<body>
    <h2>Pagina Registrazione</h2>
    <div id="welcomeTextDiv">
        <span id="btnText" style="padding-left: 24px;">Registrazione</span>
        <div id="redirectBtnTable" style="padding: 23px 0px 0px 35px;">
                
            <form:form id="registrationForm" action="esegui_registrazione" method="GET">
            
             <br>
             <b>Pin</b>
             <input name="captchaPin" type="text" value="" size="40" maxlength="200" />
             <br>
            
            <br>
            <br>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <input id="redirectBtn" type="submit" value="Registrazione" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
 
            
        </div>
    </div>
</body>
</html>
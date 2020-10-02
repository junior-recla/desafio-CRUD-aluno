<%-- 
    Document   : alterar
    Created on : 25/09/2020, 12:04:44
    Author     : Carlos
--%>

<%@page import="src.model.application.AplAluno"%>
<%@page import="src.model.domain.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Aluno</title>
    </head>
    <body>
        <fieldset>
            <legend>Alterar aluno</legend>
            <div>
                <form action="../CtrlAluno" method="post" accept-charset="iso-8859-1">
                    <%
                        String id = request.getParameter("id");
                        Aluno aluno = AplAluno.get(id);
                    %>
                    <label for="id">ID: <%=aluno.getId()%></label>
                    <input type="hidden" name="id" value="<%=aluno.getId()%>">
                    <br/><br/>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" value="<%=aluno.getNome()%>"  required/>
                    <br/>
                    <br/>
                    <label for="idade">Idade:</label>
                    <input type="text" name="idade" value="<%=aluno.getIdade()%>" required/>
                    <br/>
                    <br/>
                    <input type="hidden" name="operacao" value="alterar">
                    <input type="submit" value="Salvar"/>
                </form>
            </div>
        </fieldset>
    </body>
</html>

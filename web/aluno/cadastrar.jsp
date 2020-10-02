<%-- 
    Document   : cadastrar
    Created on : 25/09/2020, 12:04:24
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Aluno</title>
    </head>
    <body>
        <fieldset>
            <legend>Cadastrar Ator</legend>
            <div>
                <form action="../CtrlAluno" method="post" accept-charset="iso-8859-1">
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" required/>
                    <br/>
                    <br/>
                    <label for="idade">Idade:</label>
                    <input type="text" name="idade" required/>
                    <br/>
                    <br/>
                    <input type="hidden" name="operacao" value="salvar">
                    <input type="submit" value="Cadastrar"/>
                </form>
            </div>
        </fieldset>
    </body>
</html>

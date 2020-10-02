<%-- 
    Document   : index
    Created on : 25/09/2020, 12:04:07
    Author     : Carlos
--%>

<%@page import="java.util.List"%>
<%@page import="src.model.application.AplAluno"%>
<%@page import="src.model.domain.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD Aluno</title>
    </head>
    <body>
        <div>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Idade</th>
                    <th></th>
                    <th></th>
                </tr>

                <%
                    List<Aluno> alunos = AplAluno.getAll();
                    if (alunos != null)
                        for (Aluno aluno : alunos) {%>
                <tr>
                    <td><%=aluno.getId()%></td>
                    <td><%=aluno.getNome()%></td>
                    <td><%=aluno.getIdade()%></td>
                    <td><button onclick="confirmarExclusao('../CtrlAluno', '<%=aluno.getId()%>')">Excluir</button></td>
                    <td><button name="button"><a href="alterar.jsp?id=<%=aluno.getId()%>">Alterar</a></button></td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
        <br>
        <button name="button"><a href="cadastrar.jsp">Cadastrar</a></button>

        <script type="text/javascript">
            function confirmarExclusao(pagina, id) {
                var r = confirm("Tem Certeza que deseja excluir?");
                if (r == true) {
                    window.location.href = pagina + "?operacao=excluir&id=" + id;
                } else {
                    window.history.go(0);
                }
            }
        </script>
    </body>
</html>

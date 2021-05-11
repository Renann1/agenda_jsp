package Controller;

import Model.ContatoExisteException;
import Model.Contato;
import Model.ContatoDAOImpl;
import Model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletNovo extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", Util.decodificar(request.getParameter("nome")));
        request.setAttribute("email", Util.decodificar(request.getParameter("email")));
        request.setAttribute("telefone", Util.decodificar(request.getParameter("telefone")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("novo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(Util.decodificar(request.getParameter("nome")), Util.decodificar(request.getParameter("email")), Util.decodificar(request.getParameter("telefone")));
                new ContatoDAOImpl().cadastrar(usuario.getId(), contato);
                request.setAttribute("sucesso", "Novo contato cadastrado com sucesso.");
            } catch (IndexOutOfBoundsException | IllegalArgumentException | ContatoExisteException e) {
                erro(request, e.getMessage());
            }
        }

        request.getRequestDispatcher("novo.jsp").forward(request, response);
    }
}

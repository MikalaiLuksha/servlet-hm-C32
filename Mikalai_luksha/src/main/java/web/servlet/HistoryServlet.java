package web.servlet;

import entity.Operation;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/history", name = "HistoryServlet")

public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HistoryService historyService = new HistoryService();
        String operation = req.getParameter("operation");
        String sort = req.getParameter("sort");
        if (operation != null) {
            List<Operation> result = (List<Operation>) req.getSession().getAttribute("result");
            List<Operation> operations = (List<Operation>) historyService.calcResult(operation, result);
            if (sort != null && sort.equalsIgnoreCase("asc")){
                operations.sort(Operation::compareTo);
            }


            req.setAttribute("resHis", operations);
        }


        getServletContext().getRequestDispatcher("/pages/history.jsp").forward(req, resp);
    }


}

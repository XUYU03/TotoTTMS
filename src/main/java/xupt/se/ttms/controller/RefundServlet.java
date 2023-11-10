package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.dao.RefundDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Refund;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/RefundServlet")
public class RefundServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type=request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("search")) {
            try {
                search(request, response);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String cus_name = request.getParameter("cus_name");
        List<Refund> result = null;
        if(cus_name != null && cus_name.length()>0) {
            result = new RefundDAO().QueryLikecus_Name(cus_name);
        }
        else
            result = new RefundDAO().Query();

        String jsonStr="";

        JSONArray array = new JSONArray();
        JSONObject json ;
        for(Refund r : result){
            json = new JSONObject();
            json.put("cus_name",r.getCus_name());
            json.put("refund_time",r.getRefund_time());
            json.put("refund_sort",r.getRefund_sort());
        }
    }


}

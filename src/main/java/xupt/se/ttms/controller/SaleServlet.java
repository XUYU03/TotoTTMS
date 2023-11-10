package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID=1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		// 根据请求操作类型，执行相应的增、删、该、查
		if(type.equalsIgnoreCase("add"))
			add(request, response);
		else if(type.equalsIgnoreCase("pay")) {
			pay(request, response);
		} else if(type.equalsIgnoreCase("search")) {
			search(request, response);
		}else if(type.equalsIgnoreCase("update")) {
			update(request, response);
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Sale sale=null;
		int sale_id=0;
		try
		{
			sale_id=Integer.parseInt(request.getParameter("sale_ID"));
			int emp_id=Integer.parseInt(request.getParameter("emp_id"));
			int cus_id=Integer.parseInt(request.getParameter("cus_id"));
			Timestamp sale_time=Timestamp.valueOf(request.getParameter("sale_time"));
			double sale_payment = Integer.parseInt(request.getParameter("sale_payment"));
			double sale_change = Integer.parseInt(request.getParameter("sale_change"));
			int sale_type=Integer.parseInt(request.getParameter("sale_type"));
			int sale_status=Integer.parseInt(request.getParameter("sale_status"));
			int sale_sort=Integer.parseInt(request.getParameter("sale_sort"));
			sale =new Sale(sale_id,emp_id,cus_id,sale_time,sale_payment,sale_change,
					sale_type,sale_status,sale_sort);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();

			if(new SaleSrv().modify(sale) == 1) {
				out.write("数据修改成功");
			}
			else
				out.write("数据修改失败，请重试");
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("操作错误，请重试");
		}
	}
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		int sale_ID= Integer.parseInt(request.getParameter("sale_ID"));
		List<Sale> result=null;
		if(sale_ID!=-1){
			result=new SaleSrv().Fetch(sale_ID);
		} else{
			result=new SaleSrv().FetchAll();
		}
		String jsonStr="";
		try {
			JSONArray array=new JSONArray();
			JSONObject json;
			for(Sale s : result) {
				json=new JSONObject();
				json.put("sale_ID", s.getSale_ID());
				json.put("emp_id",s.getEmp_id());
				json.put("cus_id",s.getCus_id());
				json.put("sale_time",s.getSale_time());
				json.put("sale_payment",s.getSale_payment());
				json.put("sale_change",s.getSale_change());
				json.put("sale_type",s.getSale_type());
				json.put("sale_status",s.getSale_status());
				json.put("sale_sort",s.getSale_sort());
				array.put(json);
			}
			jsonStr=array.toString();
		} catch(JSONException e) {
			e.printStackTrace();
		} finally {
			out.println(jsonStr);
			out.flush();
			out.close();
		}
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		int arr[]=new int[5];
		arr[0]=Integer.valueOf(request.getParameter("a"));
		arr[1]=Integer.valueOf(request.getParameter("b"));
		arr[2]=Integer.valueOf(request.getParameter("c"));
		arr[3]=Integer.valueOf(request.getParameter("d"));
		arr[4]=Integer.valueOf(request.getParameter("e"));

		DBUtil db=null;
		db=new DBUtil();
		int m=0;

		Sale stu=null;
		int id=0;
		int flag=0;
		PrintWriter out=response.getWriter();
		for(int i=0;i<5;i++) {
			if (arr[i]!=0) {
				try {
					String sql="update ticket set ticket_status=9 where ticket_id="+arr[i];

					if(!db.openConnection()) {
						System.out.print("fail to connect database");
						return ;
					}
					m=db.execCommand(sql);
					int emp_id=Integer.valueOf(request.getParameter("id"));
					Timestamp time=null;
					time=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("0000-00-00 00:00:00").getTime());
					double price=Double.valueOf(request.getParameter("price"));
					stu=new Sale(id, emp_id,0, time, price,  Double.valueOf(0),1,1,1);
					response.setContentType("text/html;charset=utf-8");
					if(new SaleSrv().add1(stu) == 1) {
						flag=1;
					}
					out.close();
				} catch(Exception e) {
					e.printStackTrace();
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write("操作错误，请重试");
				}
			}

		}
		System.out.println(flag);
		out.write(""+flag);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		int arr[]=new int[5];
		arr[0]=Integer.valueOf(request.getParameter("a"));
		arr[1]=Integer.valueOf(request.getParameter("b"));
		arr[2]=Integer.valueOf(request.getParameter("c"));
		arr[3]=Integer.valueOf(request.getParameter("d"));
		arr[4]=Integer.valueOf(request.getParameter("e"));
		DBUtil db=null;
		db=new DBUtil();
		int m=0;

		Sale stu=null;
		int id=0;
		int flag=0;
		PrintWriter out=response.getWriter();
		for(int i=0;i<5;i++) {
			if (arr[i]!=0) {
				try {

					//String locktime=lock(current);
					//System.out.println(current);
					String sql="update ticket set ticket_status=9 where ticket_id="+arr[i];

					if(!db.openConnection()) {
						System.out.print("fail to connect database");
						return ;
					}
					m=db.execCommand(sql);
					int cus_id=Integer.valueOf(request.getParameter("id"));
					Timestamp time=null;
					time=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("0000-00-00 00:00:00").getTime());
					double price=Double.valueOf(request.getParameter("price"));
					stu=new Sale(id, 0,cus_id, time, price, Double.valueOf(0),1,1,1);
					response.setContentType("text/html;charset=utf-8");
					if(new SaleSrv().add(stu) == 1) {
						flag=1;
					}
					out.close();
				} catch(Exception e) {
					e.printStackTrace();
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write("操作错误，请重试");
				}
			}

		}
		out.write(""+flag);

	}


}

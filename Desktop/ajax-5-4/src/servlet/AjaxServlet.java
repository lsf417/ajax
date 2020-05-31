package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;

import dao.AjaxDao;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

	AjaxDao dao=new AjaxDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置请求 响应 数据类型
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 获取参数
		String method = req.getParameter("method");
		switch (method) {
		case "load":
			load(req, resp);
			break;
		case "add":
			try {
				add(req, resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case "del":
			del(req, resp);
			break;
		case "update":
			try {
				update(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	//修改
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer score = Integer.parseInt(req.getParameter("score"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String clazz = req.getParameter("clazz");
		String bir = req.getParameter("bir");
		
		dao.update(id, name, gender, clazz, score, bir);
	}

	//删除
	private void del(HttpServletRequest req, HttpServletResponse resp) {
		String ids=req.getParameter("ids");
		String[] arr=ids.split(",");
		for (String s : arr) {
			dao.del(Integer.parseInt(s));
		}
	}
	/**
	 * 添加
	 * @param req
	 * @param resp
	 * @throws ParseException 
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		//获取数据
		String name=req.getParameter("name");
		String gender=req.getParameter("gender");
		String clazz=req.getParameter("clazz");
		Integer score=Integer.parseInt(req.getParameter("score"));
		//出生日期字符串
		String bir=req.getParameter("bir");
		//
		dao.add(name, gender, clazz, score, bir);
	}

	// 加载
	private void load(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//取查询参数,但要注意：第一次加载时，由于没有点击查询按钮,如果这四个参数是没有值的，结果为null
		String name = req.getParameter("name");
		String clazz = req.getParameter("clazz");
		String gender = req.getParameter("gender");
		String result = req.getParameter("result");
		
		// 取得当前页，以及每一页显示的条数
		Integer page = Integer.parseInt(req.getParameter("page"));// 当前页
		Integer rows = Integer.parseInt(req.getParameter("rows"));// 每一页显示几条

		// 取排序的字段，注意第一次，一定是null，只有点击了排序后，才会有值
		String sort = req.getParameter("sort");// 按哪一个字段排序
		String order = req.getParameter("order");// 排序方式
		// 获取输出流
		PrintWriter out = resp.getWriter();
		//集合存储数据
		Map<String, Object> json=new HashMap<>();
		json.put("rows", dao.load(page, rows, sort, order,name,clazz,gender,result));
        json.put("total",dao.getCount(name,clazz,gender,result));
        //转换json对象
		String js=JSON.toJSONStringWithDateFormat(json,"yyyy-MM-dd");
		out.write(js);
		//关闭 清空
		out.flush();
		out.close();
	}
}

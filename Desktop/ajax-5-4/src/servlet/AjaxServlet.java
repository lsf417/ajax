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
		// �������� ��Ӧ ��������
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// ��ȡ����
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

	//�޸�
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer score = Integer.parseInt(req.getParameter("score"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String clazz = req.getParameter("clazz");
		String bir = req.getParameter("bir");
		
		dao.update(id, name, gender, clazz, score, bir);
	}

	//ɾ��
	private void del(HttpServletRequest req, HttpServletResponse resp) {
		String ids=req.getParameter("ids");
		String[] arr=ids.split(",");
		for (String s : arr) {
			dao.del(Integer.parseInt(s));
		}
	}
	/**
	 * ���
	 * @param req
	 * @param resp
	 * @throws ParseException 
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		//��ȡ����
		String name=req.getParameter("name");
		String gender=req.getParameter("gender");
		String clazz=req.getParameter("clazz");
		Integer score=Integer.parseInt(req.getParameter("score"));
		//���������ַ���
		String bir=req.getParameter("bir");
		//
		dao.add(name, gender, clazz, score, bir);
	}

	// ����
	private void load(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//ȡ��ѯ����,��Ҫע�⣺��һ�μ���ʱ������û�е����ѯ��ť,������ĸ�������û��ֵ�ģ����Ϊnull
		String name = req.getParameter("name");
		String clazz = req.getParameter("clazz");
		String gender = req.getParameter("gender");
		String result = req.getParameter("result");
		
		// ȡ�õ�ǰҳ���Լ�ÿһҳ��ʾ������
		Integer page = Integer.parseInt(req.getParameter("page"));// ��ǰҳ
		Integer rows = Integer.parseInt(req.getParameter("rows"));// ÿһҳ��ʾ����

		// ȡ������ֶΣ�ע���һ�Σ�һ����null��ֻ�е��������󣬲Ż���ֵ
		String sort = req.getParameter("sort");// ����һ���ֶ�����
		String order = req.getParameter("order");// ����ʽ
		// ��ȡ�����
		PrintWriter out = resp.getWriter();
		//���ϴ洢����
		Map<String, Object> json=new HashMap<>();
		json.put("rows", dao.load(page, rows, sort, order,name,clazz,gender,result));
        json.put("total",dao.getCount(name,clazz,gender,result));
        //ת��json����
		String js=JSON.toJSONStringWithDateFormat(json,"yyyy-MM-dd");
		out.write(js);
		//�ر� ���
		out.flush();
		out.close();
	}
}

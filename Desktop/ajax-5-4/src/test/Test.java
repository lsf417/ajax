package test;
import java.text.ParseException;

import dao.AjaxDao;

public class Test {

	public static void main(String[] args) {
		AjaxDao dao=new AjaxDao();
		String name="���ٷ�";
		String gender="��";
		String cla="s1t80";
		int score=88;
		String bir="1994-04-17";
		try {
			dao.add(name, gender, cla, score, bir);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

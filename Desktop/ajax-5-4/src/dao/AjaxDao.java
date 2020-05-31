package dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AjaxDao extends BaseDao {

	//���ַ���ת��Ϊ����
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @param page ҳ��
	 * @param size ÿҳ����
	 * @param sort �������
	 * @param order ����ʽ
	 * @return
	 */
	public List<Map<String, Object>> load(Integer page,Integer size,String sort,String order,String name,String clazz,String gender,String result) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//��ʼҳ
			Integer start = (page-1)*size;
			//String sql = "select * from info limit ?,?";//��ʼ�±꣬��ʾ��������
			String sql = "select * from info where 1=1";
			//ƴ�Ӳ�ѯ����
			if(name!=null&&!name.equals("")){
				sql+=" and name like '%"+name+"%'";
			}
			if(clazz!=null&&!clazz.equals("")){
				sql+=" and class like '%"+clazz+"%'";
			}
			if(gender!=null&&!gender.equals("-1")){
				sql+=" and gender='"+gender+"'";
			}
			if(result!=null&&!result.equals("-1")){
				if(result.equals("1")){
					sql+=" and score>=90";
				}else if(result.equals("2")){
					sql+=" and score>=80";
				}else if(result.equals("3")){
					sql+=" and score>=60";
				}else{
					sql+=" and score<60";
				}
				
			}
			
			if(sort!=null){//�����Ϊ�գ���ʾҪ���� 
				if(sort.equals("clazz")){
					sort="class";
				}
				sql+=" order by "+sort+" "+order;
			}
			sql+=" limit ?,?";
			list=getMap(sql, start,size);
			return list;
	}
	
	/**
	 * ��ҳ��
	 * @return
	 */
	public int getCount(String name,String clazz,String gender,String result){
		String sql="select count(1) from info where 1=1";
		//ƴ�Ӳ�ѯ����
		if(name!=null&&!name.equals("")){
			sql+=" and name like '%"+name+"%'";
		}
		if(clazz!=null&&!clazz.equals("")){
			sql+=" and class like '%"+clazz+"%'";
		}
		if(gender!=null&&!gender.equals("-1")){
			sql+=" and gender='"+gender+"'";
		}
		if(result!=null&&!result.equals("-1")){
			if(result.equals("1")){
				sql+=" and score>=90";
			}else if(result.equals("2")){
				sql+=" and score>=80";
			}else if(result.equals("3")){
				sql+=" and score>=60";
			}else{
				sql+=" and score<60";
			}
			
		}
		return (int)(long)getOne(sql);
	}
	
	//���
	public int add(String name,String gender,String cla,Integer score,String bir) throws ParseException{
		String sql="insert info values(0,?,?,?,?,?)";
		
		return executeUpdate(sql, name,gender,cla,score,sdf.parse(bir));
	}
	
	//ɾ��
	public int del(Integer id){
		String sql="delete from info where id=?";
		//id���ַ�������ת��Ϊint
		return executeUpdate(sql, id);
	}
	
	public int update(Integer id,String name,String gender,String clazz,Integer score,String bir) throws ParseException{
		String sql="update info set name=?,gender=?,class=?,score=?,bir=? where id=?";
		//id���ַ�������ת��Ϊint
		return executeUpdate(sql, name,gender,clazz,score,sdf.parse(bir),id);
	}
	
}

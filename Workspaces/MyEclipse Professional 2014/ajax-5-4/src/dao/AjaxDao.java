package dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AjaxDao extends BaseDao {

	//把字符串转换为日期
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @param page 页号
	 * @param size 每页条数
	 * @param sort 排序的列
	 * @param order 排序方式
	 * @return
	 */
	public List<Map<String, Object>> load(Integer page,Integer size,String sort,String order,String name,String clazz,String gender,String result) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//起始页
			Integer start = (page-1)*size;
			//String sql = "select * from info limit ?,?";//开始下标，显示几条数据
			String sql = "select * from info where 1=1";
			//拼接查询条件
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
			
			if(sort!=null){//如果不为空，表示要排序 
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
	 * 总页数
	 * @return
	 */
	public int getCount(String name,String clazz,String gender,String result){
		String sql="select count(1) from info where 1=1";
		//拼接查询条件
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
	
	//添加
	public int add(String name,String gender,String cla,Integer score,String bir) throws ParseException{
		String sql="insert info values(0,?,?,?,?,?)";
		
		return executeUpdate(sql, name,gender,cla,score,sdf.parse(bir));
	}
	
	//删除
	public int del(Integer id){
		String sql="delete from info where id=?";
		//id是字符串，需转换为int
		return executeUpdate(sql, id);
	}
	
	public int update(Integer id,String name,String gender,String clazz,Integer score,String bir) throws ParseException{
		String sql="update info set name=?,gender=?,class=?,score=?,bir=? where id=?";
		//id是字符串，需转换为int
		return executeUpdate(sql, name,gender,clazz,score,sdf.parse(bir),id);
	}
	
}

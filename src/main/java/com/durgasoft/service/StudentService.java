package com.durgasoft.service;
import com.durgasoft.beans.Student;
import java.util.*;
import java.sql.*;

public class StudentService {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	
	public StudentService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","15240");
			st=con.createStatement();
		}catch(Exception e) {
			
		}
	}
	public void addStudent(Student std) {
		try {
		st.executeUpdate(String.format("insert into student values('%s','%s','%s')",std.getSid(),std.getSname(),std.getSaddr()));
		}catch(Exception e) {
			
		}
		
	}
	
	public List<Student> getAllStudent(){
		List<Student> stdList=null;
		try {
			rs=st.executeQuery("select * from student");
			stdList=new ArrayList<Student>();
			while(rs.next()) {
				Student std=new Student();
				std.setSid(rs.getString("SID"));
				std.setSname(rs.getString("SNAME"));
				std.setSaddr(rs.getString("SADDR"));
				stdList.add(std);
			}
		}catch(Exception e) {
			
		}
		return stdList;
	}

}

//create data table--------------------------->

/*
 create table student(
 	SID varchar2(10) primary key,
 	SNAME varchar2(20),
 	SADDR varchar2(30)
 );
 
 
 */





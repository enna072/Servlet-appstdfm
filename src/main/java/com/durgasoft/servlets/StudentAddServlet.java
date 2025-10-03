package com.durgasoft.servlets;
import com.durgasoft.beans.Student;
import com.durgasoft.service.StudentService;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import java.util.*;


@WebServlet("/add")
public class StudentAddServlet extends HttpServlet implements Servlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//formalities
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		//first insert the Student data in the database table
		String sid=request.getParameter("sid");
		String sname=request.getParameter("sname");
		String saddr=request.getParameter("saddr");
		//now create an student object to pass in the StudentService method to add it in the database
		Student std=new Student();
		std.setSid(sid);
		std.setSname(sname);
		std.setSaddr(saddr);
		//creating  an StudentService method to add student
		StudentService stdService=new StudentService();
		stdService.addStudent(std);//------------------------->student has been added
		
		
		//now we need all the student inside the database.
		List<Student> stdList=stdService.getAllStudent();
		
		// now show the students in the stdList using HTML table
		
		out.println("""
				<html>
				<body>
					<h2 style='color:red' align='center'>Durga software solution</h2>
					<h3 style='color:blue' align='center'>Student details</h3>
					<table align='center' bgcolor='lightblue' border='2'>
					<tr><td>STUDENT ID</td><td>STUDENT NAME</td><td>STUDENT ADDRESS</td></tr>
				""");
		for(Student std1:stdList) {
			String record=String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",std1.getSid(),std1.getSname(),std1.getSaddr());
			out.println(record);
		}
		out.println("</table></body></html>");//------------------>data representation done;
		
		
		
		//now add the std_add_form.html using RequestDispatcher , using include
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/std_add_form.html");
		
		requestDispatcher.include(request, response);
		
		
		
		
		
	}

}

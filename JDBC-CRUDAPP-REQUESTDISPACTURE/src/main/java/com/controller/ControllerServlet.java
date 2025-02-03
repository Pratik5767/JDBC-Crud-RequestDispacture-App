package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Student;
import com.services.IStudentService;
import com.servicesFactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		RequestDispatcher dispature = null;
		
		// Create
		if (request.getRequestURI().endsWith("addform")) {
			String age = request.getParameter("sage");
			String name = request.getParameter("sname");
			String address = request.getParameter("saddr");

			Student std = new Student();
			std.setSname(name);
			std.setSage(Integer.parseInt(age));
			std.setSaddress(address);

			String status = studentService.addStudent(std);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				dispature = request.getRequestDispatcher("../success.html");
				dispature.forward(request, response);
			} else {
				dispature = request.getRequestDispatcher("../failure.html");
				dispature.forward(request, response);
			}
			out.close();
		}

		// Read
		if (request.getRequestURI().endsWith("searchform")) {
			String sid = request.getParameter("sid");

			Student std = studentService.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();

			if (std != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" + std.getSid() + "</td></tr>");
				out.println("<tr><th>NAME</th><td>" + std.getSname() + "</td></tr>");
				out.println("<tr><th>AGE</th><td>" + std.getSage() + "</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>" + std.getSaddress() + "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<h1 style='color: red; text-align: center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID :: "
						+ sid + "</h1>");
			}
			out.close();
		}

		// Delete
		if (request.getRequestURI().endsWith("deleteform")) {
			String sid = request.getParameter("sid");

			String status = studentService.deleteStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				dispature = request.getRequestDispatcher("../deleteSuccess.html");
				dispature.forward(request, response);
			} else if (status.equals("failure")) {
				dispature = request.getRequestDispatcher("../deleteFailure.html");
				dispature.forward(request, response);
			} else {
				dispature = request.getRequestDispatcher("../deleteNotfound.html");
				dispature.forward(request, response);
			}
			out.close();
		}

		// Update
		if (request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");

			Student std = studentService.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();

			if (std != null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td> " + std.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='"+std.getSid()+"'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + std.getSname()
						+ "'/></td></tr>");
				out.println(
						"<tr><th>AGE</th><td><input type='text' name='sage' value='" + std.getSage() + "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + std.getSaddress()
						+ "'/></td></tr>");
				out.println("<tr><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<body>");
				out.println("<h1 style='color: red; text-align: center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID :: "
						+ sid + "</h1>");
				out.println("<body/>");
			}
			out.close();
		}
		
		if (request.getRequestURI().endsWith("updateRecord")) {
			PrintWriter out = response.getWriter();
			
			String id = request.getParameter("sid");
			String name = request.getParameter("sname");
			String age = request.getParameter("sage");
			String addr = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSid(Integer.parseInt(id));
			student.setSname(name);
			student.setSage(Integer.parseInt(age));
			student.setSaddress(addr);
			
			String status = studentService.updateStudent(student);
			if (status.equals("success")) {
				dispature = request.getRequestDispatcher("../../updateSuccess.html");
				dispature.forward(request, response);
			} else {
				dispature = request.getRequestDispatcher("../../updateFailed.html");
				dispature.forward(request, response);
			}
			out.close();
		}
	}
}
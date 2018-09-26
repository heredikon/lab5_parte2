package edu.eci.pdsw.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.pdsw.validator.EmployeeValidator;
import edu.eci.pdsw.validator.ErrorType;
import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;
import edu.eci.pdsw.validator.IdValidator;
import edu.eci.pdsw.validator.NameValidator;
import edu.eci.pdsw.validator.SalaryValidator;
import edu.eci.pdsw.validator.SocialSecurityValidator;

/**
 * Servlet class for employee validation
 */
@WebServlet(urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

	/**
	 * Auto generated serial version id
	 */
	private static final long serialVersionUID = -2768174622692970274L;

	/**
	 * The employee validator to use
	 */
	private EmployeeValidator idValidator;
        private EmployeeValidator nameValidator;
        private EmployeeValidator salaryValidator;
        private EmployeeValidator socSecValidator;
        

	public ValidateServlet() {
                this.idValidator = new IdValidator();
                this.nameValidator = new NameValidator();
		this.salaryValidator = new SalaryValidator();
                this.socSecValidator = new SocialSecurityValidator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();

		// TODO Add the corresponding Content Type, Status, and Response
                //Employee empleado = new Employee();
                
                
                //Optional<String> optName = Optional.ofNullable(req.getParameter("name"));
                //String name = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
                
                
                
		resp.setContentType("text/html");
		resp.setStatus(resp.SC_ACCEPTED);
		responseWriter.write(readFile("form.html"));
		responseWriter.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();

		// TODO Create and validate employee
                Employee empleado = new Employee(Integer.parseInt(req.getParameter("personID")),req.getParameter("name"), Integer.parseInt(req.getParameter("salary")), SocialSecurityType.valueOf(req.getParameter("SocialSecurity")));                
                
                
                Optional<ErrorType> response = idValidator.validate(empleado);
                if (!response.isPresent()){
                    response = nameValidator.validate(empleado);
                    if(!response.isPresent()){
                        response = salaryValidator.validate(empleado);
                        if (!response.isPresent()){
                            response = socSecValidator.validate(empleado);
                        }
                    }
                }

		// TODO Add the Content Type, Status, and Response according to validation response
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));
		responseWriter.flush();
	}

	/**
	 * Reads a file from the resources folder
	 * 
	 * @param path The file path
	 * @return the file content
	 * @throws IOException if the file doesn't exist
	 */
	public String readFile(String path) throws IOException {
		StringBuilder html = new StringBuilder();
		try (BufferedReader r = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path)))) {
			r.lines().forEach(line -> html.append(line).append("\n"));
		}
		return html.toString();
	}

}

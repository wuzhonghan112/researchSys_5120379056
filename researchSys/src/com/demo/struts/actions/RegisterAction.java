package com.demo.struts.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.beans.User;
import com.demo.hibernate.dao.UserDAO;
import com.demo.struts.forms.RegisterForm;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class RegisterAction extends DispatchAction{

	protected UserDAO userDAO;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response)
	           throws Exception{
		ActionForward forward = mapping.findForward(Constants.FAILURE_KEY);
		return(forward);
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response)
	           throws Exception{
	    
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		RegisterForm registerForm = (RegisterForm) form;
		
		try{
			//get parameters
			String username = registerForm.getUsername();
			//String password1 = registerForm.getPassword1();
			//String password2 = registerForm.getPassword2();
			//String email = registerForm.getEmail();
			
			//register
			if(isExist(request, registerForm)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						   "register.message.failed"));
			}else{
				insert(request, registerForm);
				log.info("User " + username + " register.");
			}
			
		}catch(Exception e){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					   "register.message.failed"));
		}
		
		if(!errors.isEmpty()){
			saveErrors(request, errors);
			forward = mapping.findForward(Constants.FAILURE_KEY);
		}else{
			forward = mapping.findForward(Constants.SUCCESS_KEY);
		}
		
		//Finish with
		return (forward);
	}
	
	/*private boolean isExist(RegisterForm registerForm){
		return registerForm.getUsername().equals("admin");
	}
	
	private void insert(RegisterForm registerForm){
		
	}*/
	private boolean isExist(HttpServletRequest request, RegisterForm registerForm){
		if(getUserDAO().isExist(registerForm.getUsername())){
			return true;
		}else{
			return false;
		}
	}
	
	private void insert(HttpServletRequest request, RegisterForm registerForm){
		User user = new User();
		user.setUsername(registerForm.getUsername());
		user.setPassword(registerForm.getPassword1());
		user.setEmail(registerForm.getEmail());
		getUserDAO().insertUser(user);
	}
	
}

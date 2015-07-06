package com.demo.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.dao.UserDAO;
import com.demo.struts.forms.LoginForm;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class LoginAction extends DispatchAction {

	protected UserDAO userDAO;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			    HttpServletRequest request, HttpServletResponse response)
	            throws Exception{
		
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		LoginForm loginForm = (LoginForm) form;
		try{
			//get parameters
			String username = loginForm.getUsername();
			
			//invalidate the original session if exists
			HttpSession session = request.getSession(false);
			if(session != null){
				session.invalidate();
			}
			//create a new session for the user
			session = request.getSession(true);
			
			//login		
			if(valid(request, loginForm)){
				session.setAttribute(Constants.USERNAME_KEY, username);
				log.info("User " + username + " login.");
			}else{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						   "login.message.failed"));
			}
		}catch(Exception e){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					   "login.message.failed"));
		}
		
		if(!errors.isEmpty()){
			saveErrors(request, errors);
			request.setAttribute("loginFormBean", loginForm);
			forward = mapping.findForward(Constants.FAILURE_KEY);
		}else{
			forward = mapping.findForward(Constants.SUCCESS_KEY);
		}
		
		//Finish with
		return (forward);
	}
	
	private boolean valid(HttpServletRequest request, LoginForm loginForm){
		if(getUserDAO().isValid(loginForm.getUsername(), 
				loginForm.getPassword())){
			return true;
		}else{
			return false;
		}
	}
}

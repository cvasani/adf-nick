package com.packt.jdeveloper.cookbook.shared.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

import javax.security.auth.login.FailedLoginException;

import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

import weblogic.servlet.security.ServletAuthentication;

public class AuthenticationBean {
    public AuthenticationBean() {
    }

 
    
    

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String login() {
        final String WELCOME_URL = "/adfAuthentication?success_url=/faces/welcome.jspx";
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        if (authenticate(request)) {
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher(WELCOME_URL);
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                reportLoginError(e.getMessage());
            }
            ctx.responseComplete();
        }
        return null;
    }

    private boolean authenticate(HttpServletRequest request) {
        String password = getPassword() == null ? "" : getPassword();
        CallbackHandler handler = new URLCallbackHandler(getUsername(), password.getBytes());
        boolean authenticated = false;
        try {
            Subject subject = Authentication.login(handler);
            ServletAuthentication.runAs(subject, request);

            ServletAuthentication.generateNewSessionID(request);
            authenticated = true;
        } catch (FailedLoginException failedLoginException) {
            reportLoginError("Wrong credentials specified.");
        } catch (LoginException loginException) {
            reportLoginError(loginException.getMessage());
        }
        return authenticated;
    }

    private void reportLoginError(String errorMessage) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, errorMessage);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, fm);
    }


    public String logout2() {
        // Add event code here...
        // Add event code here...
        // create a dispatcher and forward to the login.html page
        final String LOGOUT_URL = "/adfAuthentication?logout=true&" + "end_url=/faces/login.jspx";
        FacesContext ctx;
        ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGOUT_URL);
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // log exception
        }
        ctx.responseComplete();
        //        return null;
        return null;
    }
}

package com.jio.securityguard.exception;

import java.util.Date;
import java.util.List;

public class ErrorResponse 
{

    private String message;
 
    private List<String> details;
    
    private Date time;
    
    private String status;
    
    public ErrorResponse(String message, List<String> details, String status) {
        super();
        this.message = message;
        this.details = details;
        this.status = status;
        this.setTime(new Date());
    }
 
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 
}
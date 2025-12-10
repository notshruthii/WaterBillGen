package com.reva.water.util;

public class InvalidInputException extends Exception
{   String msg=null;
    @Override
	 public String toString()
	{
		return msg;
	}
    public InvalidInputException(String msg)
    {super(msg);
    	this.msg=msg;
    }

}

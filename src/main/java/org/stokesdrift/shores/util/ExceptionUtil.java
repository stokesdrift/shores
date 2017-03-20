package org.stokesdrift.shores.util;

public class ExceptionUtil {

	
	public static RuntimeException unchecked(Throwable t){
	    if (t instanceof RuntimeException){
	      return (RuntimeException) t;
	    } else if (t instanceof Error) { 
	      throw (Error) t;
	    } else {
	      return new RuntimeException(t);
	    }
	}
}

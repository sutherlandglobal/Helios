package helios.exceptions;

public final class ExceptionFormatter 
{
	public static String asString(Throwable e)
	{
		StringBuilder stackTrace = new StringBuilder();
		stackTrace.append("Exception: "
				+ e.getClass().getCanonicalName() + ": "
				+ e.getMessage() + "\n");

		for (StackTraceElement st : e.getStackTrace()) 
		{
			stackTrace.append(st.toString());
			stackTrace.append("\n");
		}
		
		return stackTrace.toString();
	}
}

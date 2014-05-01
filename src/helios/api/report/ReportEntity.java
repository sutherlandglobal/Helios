package helios.api.report;

import helios.report.parameters.sanitize.StringSanitizer;

/*
 * Report meta object.
 * 
 * Report invocation by the API layer involves a lot of juggling of readable names, class names, jar paths, etc. This is to mitigate that kind of hash nightmare.
 * One big use case is the same metric name and class across multiple sites.
 * 
 *  @author Jason Diamond
 */
public class ReportEntity 
{
	private String reportName;
	private String reportClass;
	private String jarPath;
	private String superClassName;
	
	private static final String REPORT_CLASSNAME_SUFFIX = ".class";
	
	private static final int MAX_REPORT_CLASSNAME_LEN = 100;
	private static final int MAX_JARPATH_LEN = 200;	

	public ReportEntity(String reportClass, String jarPath) throws ClassNotFoundException
	{
		this.jarPath = StringSanitizer.sanitize(jarPath, MAX_JARPATH_LEN);
		this.reportClass = StringSanitizer.sanitize(reportClass, MAX_REPORT_CLASSNAME_LEN).replace("/", ".").replace(REPORT_CLASSNAME_SUFFIX, "");
		
		this.superClassName = getSuperClassName(this.reportClass);
		
		
		int classNamePosStart = this.reportClass.replace(REPORT_CLASSNAME_SUFFIX, "").lastIndexOf('.');
		
		//report name is calced from report class
		this.reportName = this.reportClass.substring(classNamePosStart + 1).replace(REPORT_CLASSNAME_SUFFIX, "");	
	}
	
	public String getReportName() {
		return reportName;
	}
	
	public String getSuperClassName()
	{
		return superClassName;
	}

	public String getReportClass() {
		return reportClass;
	}

	public String getJarPath() {
		return jarPath;
	}

	private static String getClassName(String rawClassName)
	{
		return rawClassName.replace("/", ".").replace(".class","");
	}
	
	public static String getSuperClassName(String className) throws ClassNotFoundException
	{
		String retval = null;
		
		Class<?> genSuperclass = Class.forName(getClassName(className));

		if(genSuperclass != null && !genSuperclass.isInterface())
		{
			String genSuperclassName = genSuperclass.getGenericSuperclass().toString();
			retval = genSuperclassName.substring(genSuperclassName.indexOf(" ")+1);
		}
		
		return retval;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(reportName);
		sb.append(", ");
		sb.append(reportClass);
		sb.append(", ");
		sb.append(superClassName);
		sb.append(", ");
		sb.append(jarPath);
		sb.append(", ");
		
		return sb.toString();
	}
}

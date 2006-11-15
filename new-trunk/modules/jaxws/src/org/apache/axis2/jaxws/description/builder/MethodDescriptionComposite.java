/**
 * 
 */
package org.apache.axis2.jaxws.description.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MethodDescriptionComposite {
	
	//Method reflective information
	private String 		methodName;	//a public method name in this class
	private String 		returnType;	//Methods return type
	private String[]	exceptions;
	private	String		declaringClass; //the class/interface that actually declares this method

	boolean	oneWayAnnotated;	
	private WebMethodAnnot			webMethodAnnot;	
	private WebResultAnnot 			webResultAnnot;
	private WebServiceContextAnnot 	webServiceContextAnnot;	
	private HandlerChainAnnot		handlerChainAnnot;	
	private SoapBindingAnnot 		soapBindingAnnot;
	private WebServiceRefAnnot 		webServiceRefAnnot;	
	private WebEndpointAnnot 		webEndpointAnnot;
	private RequestWrapperAnnot 	requestWrapperAnnot; //TODO EDIT CHECK: only on methods of SEI
	private ResponseWrapperAnnot 	responseWrapperAnnot;//TODO EDIT CHECK: only on methods of SEI
	private List<ParameterDescriptionComposite> parameterDescriptions;//TODO EDIT CHECK: only on methods of SEI

	private DescriptionBuilderComposite	parentDBC;
	
	/*
	 * Default Constructor
	 */
	public MethodDescriptionComposite () {
		parameterDescriptions = new ArrayList<ParameterDescriptionComposite>();
	}
	
	public MethodDescriptionComposite (	
			String 					methodName,
			String 					returnType,
			WebMethodAnnot 			webMethodAnnot,
			WebResultAnnot 			webResultAnnot,
			boolean 				oneWayAnnotated,
			HandlerChainAnnot 		handlerChainAnnot,
			SoapBindingAnnot 		soapBindingAnnot,
			WebServiceRefAnnot 		webServiceRefAnnot,
			WebEndpointAnnot 		webEndpointAnnot,
			RequestWrapperAnnot 	requestWrapperAnnot,
			ResponseWrapperAnnot 	responseWrapperAnnot,
			WebServiceContextAnnot	webServiceContextAnnot
	) {
		
		this.methodName 			= methodName;
		this.returnType				= returnType;
		this.webMethodAnnot 		= webMethodAnnot;
		this.webResultAnnot 		= webResultAnnot;
		this.oneWayAnnotated 		= oneWayAnnotated;
		this.handlerChainAnnot 		= handlerChainAnnot;
		this.soapBindingAnnot 		= soapBindingAnnot;
		this.webServiceRefAnnot 	= webServiceRefAnnot;
		this.webEndpointAnnot 		= webEndpointAnnot;
		this.requestWrapperAnnot 	= requestWrapperAnnot;
		this.responseWrapperAnnot 	= responseWrapperAnnot;
		this.webServiceContextAnnot = webServiceContextAnnot;
	}
	
	/**
	 * @return Returns the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @return Returns the returnType
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @return returns whether this is OneWay
	 */
	public boolean isOneWay() {
		return oneWayAnnotated;
	}

	/**
	 * @return Returns the webEndpointAnnot.
	 */
	public WebEndpointAnnot getWebEndpointAnnot() {
		return webEndpointAnnot;
	}

	/**
	 * @return Returns the requestWrapperAnnot.
	 */
	public RequestWrapperAnnot getRequestWrapperAnnot() {
		return requestWrapperAnnot;
	}

	/**
	 * @return Returns the responseWrapperAnnot.
	 */
	public ResponseWrapperAnnot getResponseWrapperAnnot() {
		return responseWrapperAnnot;
	}

	/**
	 * @return Returns the webServiceContextAnnot.
	 */
	public WebServiceContextAnnot getWebServiceContextAnnot() {
		return webServiceContextAnnot;
	}

	/**
	 * @return Returns the handlerChainAnnot.
	 */
	public HandlerChainAnnot getHandlerChainAnnot() {
		return handlerChainAnnot;
	}

	/**
	 * @return Returns the soapBindingAnnot.
	 */
	public SoapBindingAnnot getSoapBindingAnnot() {
		return soapBindingAnnot;
	}

	/**
	 * @return Returns the webMethodAnnot.
	 */
	public WebMethodAnnot getWebMethodAnnot() {
		return webMethodAnnot;
	}

	/**
	 * @return Returns the webResultAnnot.
	 */
	public WebResultAnnot getWebResultAnnot() {
		return webResultAnnot;
	}

	/**
	 * @return Returns the webServiceRefAnnot.
	 */
	public WebServiceRefAnnot getWebServiceRefAnnot() {
		return webServiceRefAnnot;
	}

	/**
	 * @return Returns the exceptions.
	 */
	public String[] getExceptions() {
		return exceptions;
	}
	
	/**
	 * @return Returns the exceptions.
	 */
	public Class[] getExceptionTypes() {
		//TODO: Implement this...
		//for each exception in the array, convert it to a class, and return that
		//If a classloader was not set, then just use the default
		Class[] classes = new Class[0];
		return classes;
	}
	
	/**
	 * @return Returns the fully qualified name of the declaring class.
	 */
	public String getDeclaringClass() {
		return declaringClass;
	}
	
	/**
	 * @return Returns the ModuleClassType.
	 */
	public DescriptionBuilderComposite getDescriptionBuilderCompositeRef() {
		
		return this.parentDBC;
	}
	
	/**
	 * @param methodName The methodName to set.
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @param returnType The returnType to set.
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * @param oneWayAnnotated The oneWay boolean to set
	 */
	public void setOneWayAnnot(boolean oneWayAnnotated) {
		this.oneWayAnnotated = oneWayAnnotated;
	}

	/**
	 * @param webEndpointAnnotImpl The webEndpointAnnotImpl to set.
	 */
	public void setWebEndpointAnnot(WebEndpointAnnot webEndpointAnnot) {
		this.webEndpointAnnot = webEndpointAnnot;
	}

	/**
	 * @param requestWrapperAnnot The requestWrapperAnnot to set.
	 */
	public void setRequestWrapperAnnot(
			RequestWrapperAnnot requestWrapperAnnot) {
		this.requestWrapperAnnot = requestWrapperAnnot;
	}

	/**
	 * @param responseWrapperAnnot The responseWrapperAnnot to set.
	 */
	public void setResponseWrapperAnnot(
			ResponseWrapperAnnot responseWrapperAnnot) {
		this.responseWrapperAnnot = responseWrapperAnnot;
	}
	
	/**
	 * @param webServiceContextAnnot The webServiceContextAnnot to set.
	 */
	public void setWebServiceContextAnnot(WebServiceContextAnnot webServiceContextAnnot) {
		this.webServiceContextAnnot = webServiceContextAnnot;
	}


	/**
	 * @param handlerChainAnnot The handlerChainAnnot to set.
	 */
	public void setHandlerChainAnnot(HandlerChainAnnot handlerChainAnnot) {
		this.handlerChainAnnot = handlerChainAnnot;
	}

	/**
	 * @param soapBindingAnnot The soapBindingAnnot to set.
	 */
	public void setSoapBindingAnnot(SoapBindingAnnot soapBindingAnnot) {
		this.soapBindingAnnot = soapBindingAnnot;
	}

	/**
	 * @param webMethodAnnot The webMethodAnnot to set.
	 */
	public void setWebMethodAnnot(WebMethodAnnot webMethodAnnot) {
		this.webMethodAnnot = webMethodAnnot;
	}

	/**
	 * @param webResultAnnot The webResultAnnot to set.
	 */
	public void setWebResultAnnot(WebResultAnnot webResultAnnot) {
		this.webResultAnnot = webResultAnnot;
	}

	/**
	 * @param webServiceRefAnnot The webServiceRefAnnot to set.
	 */
	public void setWebServiceRefAnnot(WebServiceRefAnnot webServiceRefAnnot) {
		this.webServiceRefAnnot = webServiceRefAnnot;
	}
	
	/**
	 *  @param parameterDescription The parameterDescription to add to the set.
	 */
	public void addParameterDescriptionComposite(ParameterDescriptionComposite parameterDescription) {
		parameterDescriptions.add(parameterDescription);
	}
	
	/**
	 *  @param parameterDescription The parameterDescription to add to the set.
	 *  @param index The index at which to place this parameterDescription
	 */
	public void addParameterDescriptionComposite(ParameterDescriptionComposite parameterDescription, int index) {
		parameterDescription.setListOrder(index);
		parameterDescriptions.add(index, parameterDescription);
	}

	/**
	 *  @param parameterDescription The parameterDescription to add to the set.
	 */
	public void setParameterDescriptionCompositeList(List<ParameterDescriptionComposite> parameterDescriptionList) {
		this.parameterDescriptions = parameterDescriptionList;
	}
	
	/**
	 *  @param parameterDescription The parameterDescription to add to the set.
	 */
	public ParameterDescriptionComposite getParameterDescriptionComposite(int index) {
		return parameterDescriptions.get(index);
	}

	/**
	 */
	public List<ParameterDescriptionComposite> getParameterDescriptionCompositeList() {
		return parameterDescriptions;
	}

	/**
	 * @param exceptions The exceptions to set.
	 */
	public void setExceptions(String[] exceptions) {
		this.exceptions = exceptions;
	}
	
	/**
	 * @param declaringClass The wrapper class to set.
	 */
	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}
	
	/**
	 * @return Returns the ModuleClassType.
	 */
	public void setDescriptionBuilderCompositeRef(DescriptionBuilderComposite dbc) {
		
		this.parentDBC = dbc;
	}
	
	/**
	 * Convenience method for unit testing. We will print all of the 
	 * data members here.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String newLine = "\n";
		sb.append("***** BEGIN MethodDescriptionComposite *****");
		sb.append(newLine);
		sb.append("MDC.name= " + methodName);
		sb.append(newLine);
		sb.append("MDC.returnType= " + returnType);
		if(exceptions != null) {
			for(int i=0; i < exceptions.length; i++) {
				sb.append("MDC.exception= " + exceptions[i]);
				sb.append(newLine);
			}
		}
		sb.append(newLine);
		sb.append("\t ** @OneWay **");
		sb.append(newLine);
		sb.append("\t isOneWay= ");
		if(oneWayAnnotated) {
			sb.append("true");
		}
		else {
			sb.append("false");
		}
		sb.append(newLine);
		if(webMethodAnnot != null) {
			sb.append("\t ** @WebMethod **");
			sb.append(newLine);
			sb.append("\t" + webMethodAnnot.toString());
		}
		sb.append(newLine);
		if(requestWrapperAnnot != null) {
			sb.append("\t ** @RequestWrapper **");
			sb.append(newLine);
			sb.append("\t" + requestWrapperAnnot.toString());
		}
		sb.append(newLine);
		if(responseWrapperAnnot != null) {
			sb.append("\t ** @ResponsetWrapper **");
			sb.append(newLine);
			sb.append("\t" + responseWrapperAnnot.toString());
		}
		sb.append(newLine);
		if(soapBindingAnnot != null) {
			sb.append("\t ** @SOAPBinding **");
			sb.append(newLine);
			sb.append("\t" + soapBindingAnnot.toString());
		}
		sb.append(newLine);
		if(webEndpointAnnot != null) {
			sb.append("\t ** @WebEndpoint **");
			sb.append(newLine);
			sb.append("\t" + webEndpointAnnot.toString());
		}
		sb.append(newLine);
		if(webResultAnnot != null) {
			sb.append("\t ** @WebResult **");
			sb.append(newLine);
			sb.append("\t" + webResultAnnot.toString());
		}
		sb.append(newLine);
		if(webServiceRefAnnot != null) {
			sb.append("\t ** @WebServiceRef **");
			sb.append(newLine);
			sb.append("\t" + webServiceRefAnnot.toString());
		}
		sb.append(newLine);
		if(handlerChainAnnot != null) {
			sb.append("\t ** @HandlerChain **");
			sb.append(newLine);
			sb.append("\t" + handlerChainAnnot.toString());
		}
		sb.append(newLine);
		Iterator<ParameterDescriptionComposite> pdcIter = parameterDescriptions.iterator();
		while(pdcIter.hasNext()) {
			ParameterDescriptionComposite pdc = pdcIter.next();
			sb.append("\t\t" + pdc.toString());
			sb.append(newLine);
		}
		sb.append("***** END MethodDescriptionComposite *****");
		return sb.toString();
	}
}

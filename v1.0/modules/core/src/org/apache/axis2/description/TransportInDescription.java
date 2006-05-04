/*
* Copyright 2004,2005 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package org.apache.axis2.description;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.i18n.Messages;
import org.apache.axis2.engine.Phase;
import org.apache.axis2.phaseresolver.PhaseMetadata;
import org.apache.axis2.transport.TransportListener;

import javax.xml.namespace.QName;
import java.util.ArrayList;

/**
 * Represents an incoming transport deployed in Axis2.
 */
public class TransportInDescription implements ParameterInclude {

    /**
     * Field flowInclude
     */
    private Flow faultFlow;

    // Stores handler Fault in inFlow
    private Phase faultPhase;

    /**
     * Field flowInclude
     */
    private Flow inFlow;

    // to store handler in inFlow
    private Phase inPhase;

    /**
     * Field name
     */
    protected QName name;

    /**
     * Field paramInclude
     */
    protected final ParameterInclude paramInclude;
    protected TransportListener receiver;

    /**
     * Constructor AxisTransport.
     *
     * @param name
     */
    public TransportInDescription(QName name) {
        paramInclude = new ParameterIncludeImpl();
        this.name = name;
        inPhase = new Phase(PhaseMetadata.TRANSPORT_PHASE);
        faultPhase = new Phase(PhaseMetadata.TRANSPORT_PHASE);
    }

    /**
     * Method addParameter.
     *
     * @param param
     */
    public void addParameter(Parameter param) throws AxisFault {
        paramInclude.addParameter(param);
    }

    public void removeParameter(Parameter param) throws AxisFault {
        paramInclude.removeParameter(param);
    }

    public void deserializeParameters(OMElement parameterElement) throws AxisFault {
        this.paramInclude.deserializeParameters(parameterElement);
    }

    public Flow getFaultFlow() {
        return faultFlow;
    }

    public Phase getFaultPhase() {
        return faultPhase;
    }

    public Flow getInFlow() {
        return inFlow;
    }

    public Phase getInPhase() {
        return inPhase;
    }

    /**
     * @return Returns QName.
     */
    public QName getName() {
        return name;
    }

    /**
     * Method getParameter.
     *
     * @param name
     * @return Returns Parameter.
     */
    public Parameter getParameter(String name) {
        return paramInclude.getParameter(name);
    }

    public ArrayList getParameters() {
        return paramInclude.getParameters();
    }

    /**
     * @return Returns TransportListener.
     */
    public TransportListener getReceiver() {
        return receiver;
    }

    // to check whether the parameter is locked at any level
    public boolean isParameterLocked(String parameterName) {
        return paramInclude.isParameterLocked(parameterName);
    }

    public void setFaultFlow(Flow faultFlow) {
        this.faultFlow = faultFlow;
    }

    public void setFaultPhase(Phase faultPhase) {
        this.faultPhase = faultPhase;
    }

    public void setInFlow(Flow inFlow) {
        this.inFlow = inFlow;
    }

    public void setInPhase(Phase inPhase) {
        this.inPhase = inPhase;
    }

    /**
     * @param name
     */
    public void setName(QName name) {
        this.name = name;
    }

    /**
     * @param receiver
     */
    public void setReceiver(TransportListener receiver) {
        this.receiver = receiver;
    }
}

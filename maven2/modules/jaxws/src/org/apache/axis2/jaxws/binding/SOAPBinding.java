/*
 * Copyright 2006 The Apache Software Foundation.
 * Copyright 2006 International Business Machines Corp.
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
package org.apache.axis2.jaxws.binding;

import java.net.URI;
import java.util.Set;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.axis2.jaxws.message.MessageException;
import org.apache.axis2.jaxws.util.SAAJFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An implementation of the <link>javax.xml.ws.soap.SOAPBinding</link>
 * interface.  This is the default binding for JAX-WS, and will exist
 * for all Dispatch and Dynamic Proxy instances unless the XML/HTTP
 * Binding is explicitly specificied.
 */
public class SOAPBinding extends BindingImpl 
    implements javax.xml.ws.soap.SOAPBinding {

    private boolean mtomEnabled = false;
    private static Log log = LogFactory.getLog(SOAPBinding.class);
    
    public SOAPBinding(String bindingId) {
        super(bindingId);
    }
    
    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#getMessageFactory()
     */
    public MessageFactory getMessageFactory() {
        String bindingNamespace = null;
        try {
            /*
             * SAAJFactory.createMessageFactory takes a namespace String as a param:
             *     "http://schemas.xmlsoap.org/soap/envelope/"  (SOAP1.1)
             *     "http://www.w3.org/2003/05/soap-envelope"    (SOAP1.2)
             *     
             * The bindingId will be in one of the following forms:
             *     "http://schemas.xmlsoap.org/wsdl/soap/http"      (SOAP1.1)
             *     "http://www.w3.org/2003/05/soap/bindings/HTTP/"  (SOAP1.2)
             */
            if (bindingId.equalsIgnoreCase(SOAPBinding.SOAP12HTTP_BINDING)
                    || bindingId.equalsIgnoreCase(SOAPBinding.SOAP12HTTP_MTOM_BINDING)) {
                bindingNamespace = SOAP12_ENV_NS;
            } else {
                // TODO currently defaults to SOAP11.  Should we be more stricct about checking?
                bindingNamespace = SOAP11_ENV_NS;
            }
            return SAAJFactory.createMessageFactory(bindingNamespace);
        } catch (MessageException e) {
            // TODO log it and then what?
            if(log.isDebugEnabled()){
                log.debug("MessageException calling SAAJFactory.createMessageFactory(\""+bindingNamespace+"\")");
            }
        } catch (SOAPException e) {
            // TODO log it and then what?
            if(log.isDebugEnabled()){
                log.debug("SOAPException calling SAAJFactory.createMessageFactory(\""+bindingNamespace+"\")");
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#getRoles()
     */
    public Set<URI> getRoles() {
        return roles;
    }

    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#getSOAPFactory()
     */
    public SOAPFactory getSOAPFactory() {
        String bindingNamespace = null;
        try {
            /*
             * SAAJFactory.createMessageFactory takes a namespace String as a param:
             *     "http://schemas.xmlsoap.org/soap/envelope/"  (SOAP1.1)
             *     "http://www.w3.org/2003/05/soap-envelope"    (SOAP1.2)
             *     
             * The bindingId will be in one of the following forms:
             *     "http://schemas.xmlsoap.org/wsdl/soap/http"      (SOAP1.1)
             *     "http://www.w3.org/2003/05/soap/bindings/HTTP/"  (SOAP1.2)
             */
            if (bindingId.equalsIgnoreCase(SOAPBinding.SOAP12HTTP_BINDING)
                    || bindingId.equalsIgnoreCase(SOAPBinding.SOAP12HTTP_MTOM_BINDING)) {
                bindingNamespace = SOAP12_ENV_NS;
            } else {
                // TODO currently defaults to SOAP11.  Should we be more stricct about checking?
                bindingNamespace = SOAP11_ENV_NS;
            }
            return SAAJFactory.createSOAPFactory(bindingNamespace);
        } catch (MessageException e) {
            // TODO log it and then what?
            if(log.isDebugEnabled()){
                log.debug("MessageException calling SAAJFactory.createSOAPFactory(\""+bindingNamespace+"\")");
            }
        } catch (SOAPException e) {
            // TODO log it and then what?
            if(log.isDebugEnabled()){
                log.debug("SOAPException calling SAAJFactory.createSOAPFactory(\""+bindingNamespace+"\")");
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#isMTOMEnabled()
     */
    public boolean isMTOMEnabled() {
        return mtomEnabled;
    }

    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#setMTOMEnabled(boolean)
     */
    public void setMTOMEnabled(boolean flag) {
        mtomEnabled = flag;        
    }

    /*
     * (non-Javadoc)
     * @see javax.xml.ws.soap.SOAPBinding#setRoles(java.util.Set)
     */
    public void setRoles(Set<URI> set) {
        roles = set;
    }

}

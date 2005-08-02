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

package org.apache.axis2.engine;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.om.OMElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @version $Rev: $ $Date: $
 */

public class MessageContextEnabledEcho {
    private MessageContext msgcts;
      private Log log = LogFactory.getLog(getClass());

    public MessageContextEnabledEcho() {
    }

    public void init(MessageContext msgcts) {
        this.msgcts = msgcts;

    }

    public OMElement echoOMElement(OMElement omEle) throws AxisFault {
        if (msgcts != null) {
            log.info("MessageContext injected");
        } else {
            throw new AxisFault("Message Context not injected");
        }
        return null;
    }

}
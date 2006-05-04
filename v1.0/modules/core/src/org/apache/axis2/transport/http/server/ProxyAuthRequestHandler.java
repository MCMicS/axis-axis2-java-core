/*
* $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//httpclient/src/test/org/apache/commons/httpclient/server/ProxyAuthRequestHandler.java,v 1.12 2004/11/28 15:44:39 olegk Exp $
* $Revision: 155418 $
* $Date: 2005-02-26 08:01:52 -0500 (Sat, 26 Feb 2005) $
*
* ====================================================================
*
*  Copyright 1999-2004 The Apache Software Foundation
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
* ====================================================================
*
* This software consists of voluntary contributions made by many
* individuals on behalf of the Apache Software Foundation.  For more
* information on the Apache Software Foundation, please see
* <http://www.apache.org/>.
*
*/


package org.apache.axis2.transport.http.server;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.BasicScheme;

import java.io.IOException;

/**
 * This request handler guards access to a proxy when used in a request handler
 * chain. It checks the headers for valid credentials and performs the
 * authentication handshake if necessary.
 */
public class ProxyAuthRequestHandler implements HttpRequestHandler {

    /**
     * The proxy authenticate response header.
     */
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    private Credentials credentials = null;
    private String realm = null;
    private boolean keepalive = true;

    public ProxyAuthRequestHandler(final Credentials creds) {
        this(creds, null, true);
    }

    public ProxyAuthRequestHandler(final Credentials creds, final String realm) {
        this(creds, realm, true);
    }

    /**
     * TODO replace creds parameter with a class specific to an auth scheme
     * encapsulating all required information for a specific scheme
     *
     * @param creds
     */
    public ProxyAuthRequestHandler(final Credentials creds, final String realm, boolean keepalive) {
        if (creds == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }

        this.credentials = creds;
        this.keepalive = keepalive;

        if (realm != null) {
            this.realm = realm;
        } else {
            this.realm = "test";
        }
    }

    /**
     * Checks if the credentials provided by the client match the required
     * credentials
     *
     * @param clientAuth
     * @return true if the client is authorized, false if not.
     */
    private boolean checkAuthorization(Header clientAuth) {
        String expectedAuthString =
                BasicScheme.authenticate((UsernamePasswordCredentials) credentials, "ISO-8859-1");

        return expectedAuthString.equals(clientAuth.getValue());
    }

    // TODO add more auth schemes
    private SimpleResponse performBasicHandshake(final SimpleHttpServerConnection conn,
                                                 final SimpleRequest request) {
        SimpleResponse response = new SimpleResponse();

        response.setStatusLine(request.getRequestLine().getHttpVersion(),
                HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED);

        if (!request.getRequestLine().getMethod().equalsIgnoreCase("HEAD")) {
            response.setBodyString("unauthorized");
        }

        response.addHeader(new Header("Proxy-Authenticate", "basic realm=\"" + this.realm + "\""));

        if (this.keepalive) {
            response.addHeader(new Header("Proxy-Connection", "keep-alive"));
            conn.setKeepAlive(true);
        } else {
            response.addHeader(new Header("Proxy-Connection", "close"));
            conn.setKeepAlive(false);
        }

        return response;
    }

    public boolean processRequest(final SimpleHttpServerConnection conn,
                                  final SimpleRequest request)
            throws IOException {
        Header clientAuth = request.getFirstHeader(PROXY_AUTH_RESP);

        if ((clientAuth != null) && checkAuthorization(clientAuth)) {
            return false;
        } else {
            SimpleResponse response = performBasicHandshake(conn, request);

            // Make sure the request body is fully consumed
            request.getBodyBytes();
            conn.writeResponse(response);

            return true;
        }
    }
}

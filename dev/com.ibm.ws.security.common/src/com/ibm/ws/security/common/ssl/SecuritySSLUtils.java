/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.security.common.ssl;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;

import com.ibm.wsspi.ssl.SSLSupport;

public class SecuritySSLUtils {

    public static SSLSocketFactory getSSLSocketFactory(SSLSupport sslSupport, String sslConfigurationName) throws SSLException, NoSSLSocketFactoryException {
        SSLSocketFactory sslSocketFactory = null;
        if (sslSupport != null) {
            sslSocketFactory = sslSupport.getSSLSocketFactory(sslConfigurationName);
        }
        if (sslSocketFactory == null) {
            throw new NoSSLSocketFactoryException();
        }
        return sslSocketFactory;
    }

}

/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.openapi.impl.validation;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.OAuthFlows;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.microprofile.openapi.impl.validation.OASValidationResult.ValidationEvent;
import com.ibm.ws.microprofile.openapi.utils.OpenAPIModelWalker.Context;

/**
 *
 */
public class OAuthFlowsValidator extends TypeValidator<OAuthFlows> {

    private static final TraceComponent tc = Tr.register(OAuthFlowsValidator.class);

    private static final OAuthFlowsValidator INSTANCE = new OAuthFlowsValidator();

    public static OAuthFlowsValidator getInstance() {
        return INSTANCE;
    }

    private OAuthFlowsValidator() {}

    /** {@inheritDoc} */
    @Override
    public void validate(ValidationHelper helper, Context context, String key, OAuthFlows t) {
        if (t != null) {
            if (t.getImplicit() != null) {
                OAuthFlow implicit = t.getImplicit();;
                if (StringUtils.isNotBlank(implicit.getTokenUrl())) {
                    final String message = Tr.formatMessage(tc, "nonApplicableFieldWithValue", "tokenUrl", implicit.getTokenUrl(), "OAuth Flow Object", "implicit");
                    helper.addValidationEvent(new ValidationEvent(ValidationEvent.Severity.WARNING, context.getLocation(), message));
                }
                ValidatorUtils.validateRequiredField(implicit.getAuthorizationUrl(), context, "authorizationUrl").ifPresent(helper::addValidationEvent);
            }
            if (t.getPassword() != null) {
                OAuthFlow password = t.getPassword();
                if (StringUtils.isNotBlank(password.getAuthorizationUrl())) {
                    final String message = Tr.formatMessage(tc, "nonApplicableFieldWithValue", "authorizationUrl", password.getAuthorizationUrl(), "OAuth Flow Object", "password");
                    helper.addValidationEvent(new ValidationEvent(ValidationEvent.Severity.WARNING, context.getLocation(), message));
                }
                ValidatorUtils.validateRequiredField(password.getTokenUrl(), context, "tokenUrl").ifPresent(helper::addValidationEvent);
            }
            if (t.getClientCredentials() != null) {
                OAuthFlow clientCred = t.getClientCredentials();
                if (StringUtils.isNotBlank(clientCred.getAuthorizationUrl())) {
                    final String message = Tr.formatMessage(tc, "nonApplicableFieldWithValue", "authorizationUrl", clientCred.getAuthorizationUrl(), "OAuth Flow Object",
                                                            "clientCredentials");
                    helper.addValidationEvent(new ValidationEvent(ValidationEvent.Severity.WARNING, context.getLocation(), message));
                }
                ValidatorUtils.validateRequiredField(clientCred.getTokenUrl(), context, "tokenUrl").ifPresent(helper::addValidationEvent);
            }
            if (t.getAuthorizationCode() != null) {
                OAuthFlow authCode = t.getAuthorizationCode();
                ValidatorUtils.validateRequiredField(authCode.getTokenUrl(), context, "tokenUrl").ifPresent(helper::addValidationEvent);
                ValidatorUtils.validateRequiredField(authCode.getAuthorizationUrl(), context, "authorizationUrl").ifPresent(helper::addValidationEvent);
            }
        }
    }
}

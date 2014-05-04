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

package org.wso2.carbon.event.input.adaptor.jms.internal.ds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.event.input.adaptor.core.InputEventAdaptorFactory;
import org.wso2.carbon.event.input.adaptor.jms.JMSEventAdaptorFactory;
import org.wso2.carbon.utils.ConfigurationContextService;


/**
 * @scr.component name="input.jmsEventAdaptorService.component" immediate="true"
 * @scr.reference name="configurationcontext.service"
 * interface="org.wso2.carbon.utils.ConfigurationContextService" cardinality="1..1"
 * policy="dynamic" bind="setConfigurationContextService" unbind="unsetConfigurationContextService"
 */


public class JMSEventAdaptorServiceDS {

    private static final Log log = LogFactory.getLog(JMSEventAdaptorServiceDS.class);

    /**
     * initialize the agent service here service here.
     *
     * @param context
     */
    protected void activate(ComponentContext context) {

        try {
            InputEventAdaptorFactory jmsEventAdaptorFactory = new JMSEventAdaptorFactory();
            context.getBundleContext().registerService(InputEventAdaptorFactory.class.getName(), jmsEventAdaptorFactory, null);
            if (log.isDebugEnabled()) {
                log.debug("Successfully deployed the input jms event adaptor");
            }
        } catch (RuntimeException e) {
            log.error("Can not deployed the input jms event adaptor", e);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    protected void setConfigurationContextService(
            ConfigurationContextService configurationContextService) {
        JMSEventAdaptorServiceHolder.registerConfigurationContextService(configurationContextService);
    }

    protected void unsetConfigurationContextService(
            ConfigurationContextService configurationContextService) {
        JMSEventAdaptorServiceHolder.unregisterConfigurationContextService(configurationContextService);
    }

}
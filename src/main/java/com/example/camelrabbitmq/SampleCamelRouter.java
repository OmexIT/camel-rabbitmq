/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.camelrabbitmq;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.RoutePolicy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and routes to RabbitMQ
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
//@Component
public class SampleCamelRouter extends RouteBuilder implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void configure() throws Exception {
        RoutePolicy myPolicy = (RoutePolicy) applicationContext.getBean("myPolicy");

        from("timer:hello?period=1")
                .routePolicy(myPolicy)
            .transform(simple("Random number ${random(0,100)}"))
            .to("rabbitmq:foo");

        from("rabbitmq:foo")
            .log("From RabbitMQ: ${body}");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.agent.plugin.tracing.jaeger.span;

import io.opentracing.Span;
import io.opentracing.tag.Tags;
import org.apache.shenyu.agent.plugin.tracing.common.constant.TracingConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Jaeger error span.
 */
public final class JaegerErrorSpan {
    
    /**
     * Set error.
     * 
     * @param span span to be set
     * @param cause failure cause of span
     */
    public static void setError(final Span span, final Throwable cause) {
        span.setTag(Tags.ERROR.getKey(), true).log(System.currentTimeMillis() * 1000, getReason(cause));
    }
    
    private static Map<String, ?> getReason(final Throwable cause) {
        Map<String, String> result = new HashMap<>(3, 1);
        result.put(TracingConstants.ErrorLogTags.EVENT, TracingConstants.ErrorLogTags.EVENT_ERROR_TYPE);
        result.put(TracingConstants.ErrorLogTags.ERROR_KIND, cause.getClass().getName());
        result.put(TracingConstants.ErrorLogTags.MESSAGE, cause.getMessage());
        return result;
    }
}

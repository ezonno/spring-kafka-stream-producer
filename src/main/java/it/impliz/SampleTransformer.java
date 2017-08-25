/*
 * Copyright 2015 the original author or authors.
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

package it.impliz;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;

/**
 * @author Ilayaperumal Gopinathan
 */
@EnableBinding(Processor.class)
public class SampleTransformer {

	private static final String TRANSFORMATION_VALUE = "HI";

	// Transformer application definition

    @ReleaseStrategy
    public boolean isReadytoRelease(List<Message<?>> messages) {
        return messages.size() == 2;
    }


    @CorrelationStrategy
    public String correlateBy(@Header("contentType") String id) {
        return id;
    }

//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
	@Aggregator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT, discardChannel = "nullChannel")
	public List<Message<?>> receive(List<Message<?>> messages) {
		System.out.println("******************");
		System.out.println("At the transformer");
		System.out.println("******************");
//		System.out.println("Received value "+ barMessage.getValue() + " of type " + barMessage.getClass());
//		System.out.println("Transforming the value to " + TRANSFORMATION_VALUE + " and with the type " + barMessage.getClass());
//		barMessage.setValue(TRANSFORMATION_VALUE);
		return messages;
	}
}

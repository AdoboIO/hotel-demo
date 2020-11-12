/*
 * Copyright (c) 2020-2020. AxonIQ
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;)
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.axoniq.demo.hotel.account.command;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import io.axoniq.demo.hotel.account.command.web.PayCommand;
import io.axoniq.demo.hotel.account.command.web.ProcessPaymentCommand;
import io.axoniq.demo.hotel.account.command.web.api.PaymentRequestedEvent;
import io.axoniq.demo.hotel.account.command.web.api.PaymentStatus;
import io.axoniq.demo.hotel.account.command.web.api.PaymentSucceededEvent;


@Aggregate(snapshotTriggerDefinition = "paymentSnapshotTriggerDefinition", cache = "cache")
class Payment {
    @AggregateIdentifier
    private UUID paymentId;
    private PaymentStatus paymentStatus;

    private Payment() {
    }

    Payment(PayCommand command) {
        apply(new PaymentRequestedEvent(command.getPaymentId(), command.getAccountId(), command.getTotalAmount()));
    }

    @CommandHandler
    void handle(ProcessPaymentCommand command) {
        Assert.isTrue(PaymentStatus.PROCESSING.equals(this.paymentStatus),
                      "Unsupported operation - Payment is not in PROCESSING state");
        apply(new PaymentSucceededEvent(command.getPaymentId()));
    }

    @EventSourcingHandler
    void on(PaymentRequestedEvent event) {
        this.paymentId = event.getPaymentId();
        this.paymentStatus = PaymentStatus.PROCESSING;
    }

    @EventSourcingHandler
    void on(PaymentSucceededEvent event) {
        this.paymentStatus = PaymentStatus.SUCCEEDED;
    }
}

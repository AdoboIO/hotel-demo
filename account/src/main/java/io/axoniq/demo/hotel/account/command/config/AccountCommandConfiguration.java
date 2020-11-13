package io.axoniq.demo.hotel.account.command.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.messaging.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class AccountCommandConfiguration {

    /************************************************/
    /* Register interceptors on the bus */

    /************************************************/

    @Autowired
    public void registerCommandInterceptorsOnBus(CommandBus commandBus) {
        commandBus.registerHandlerInterceptor(new LoggingInterceptor<>());
    }

    @Autowired
    public void registerEventInterceptors(EventBus eventBus) {
        eventBus.registerDispatchInterceptor(new LoggingInterceptor<>());
    }

    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.registerDefaultHandlerInterceptor((t, u) -> new LoggingInterceptor<>(u));
    }

    /***************************************/
    /*  Aggregate cache configuration   */

    /***************************************/

    @Bean("cache")
    public Cache cache() {
        return new WeakReferenceCache();
    }

    /***************************************/
    /*  Aggregate snapshot configuration   */

    /***************************************/


    @Bean("accountSnapshotTriggerDefinition")
    EventCountSnapshotTriggerDefinition accountSnapshotTriggerDefinition(Snapshotter snapshotter,
                                                                         AccountCommandProperties bookingCommandProperties) {
        return new EventCountSnapshotTriggerDefinition(snapshotter,
                                                       bookingCommandProperties.getSnapshotTriggerThresholdAccount());
    }

    @Bean("paymentSnapshotTriggerDefinition")
    EventCountSnapshotTriggerDefinition paymentSnapshotTriggerDefinition(Snapshotter snapshotter,
                                                                         AccountCommandProperties bookingCommandProperties) {
        return new EventCountSnapshotTriggerDefinition(snapshotter,
                                                       bookingCommandProperties.getSnapshotTriggerThresholdPayment());
    }
}
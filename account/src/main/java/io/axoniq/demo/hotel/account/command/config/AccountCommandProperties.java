package io.axoniq.demo.hotel.account.command.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.command")
public class AccountCommandProperties {

    private Integer snapshotTriggerThresholdAccount;
    private Integer snapshotTriggerThresholdPayment;

    public Integer getSnapshotTriggerThresholdAccount() {
        return snapshotTriggerThresholdAccount;
    }

    public void setSnapshotTriggerThresholdAccount(Integer snapshotTriggerThresholdAccount) {
        this.snapshotTriggerThresholdAccount = snapshotTriggerThresholdAccount;
    }

    public Integer getSnapshotTriggerThresholdPayment() {
        return snapshotTriggerThresholdPayment;
    }

    public void setSnapshotTriggerThresholdPayment(Integer snapshotTriggerThresholdPayment) {
        this.snapshotTriggerThresholdPayment = snapshotTriggerThresholdPayment;
    }
}

package com.sample.keyvaluestore.bo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class KeyValueStore {

    @Id
    @Column(name = "KEY_STRING")
    private String keyString;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "EXPIRES_AT")
    private Date expiresAt; //Can be leveraged for SoftDeletion. While deleting can set the expiresAt to PAST TIME and scheduler can clean it up


    public KeyValueStore(String keyString, String value, Date expiresAt) {
        this.keyString = keyString;
        this.value = value;
        this.expiresAt = expiresAt;
    }

    public KeyValueStore() {
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String key_string) {
        this.keyString = key_string;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}

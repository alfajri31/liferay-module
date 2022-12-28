package com.docs.guestbookweb.model;

public class Entry {

    private Long entryId;

    private String name;
    private String message;
    private Long userId;

    public Entry(String name, String message) {
        setName(name);
        setMessage(message);
    }

    public Entry() {

    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    private Long getEntryId() {
        return this.entryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    private void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long getUserId() {
        return userId;
    }



}
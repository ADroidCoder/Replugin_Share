package com.share.listener;

/**
 * Created by hapi on 2017/9/28.
 */

public class ShareEvent {

    private final int eventType;
    private final Object[] eventData;

    public ShareEvent(int var1) {
        this(var1, (Object[]) null);
    }

    public ShareEvent(int type, Object[] data) {
        this.eventType = type;
        this.eventData = data;
    }

    public int getType() {
        return this.eventType;
    }

    public Object[] getParas() {
        return this.eventData == null ? new Object[0] : this.eventData;
    }

}

package com.sell.po;

public class ContentWithBLOBs extends Content {
    private byte[] icon;

    private byte[] text;

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }
}
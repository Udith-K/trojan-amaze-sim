package com.google.zxing.pdf417;

/* JADX INFO: loaded from: classes.dex */
public final class PDF417ResultMetadata {
    private String addressee;
    private String fileId;
    private String fileName;
    private boolean lastSegment;
    private int[] optionalData;
    private int segmentIndex;
    private String sender;
    private int segmentCount = -1;
    private long fileSize = -1;
    private long timestamp = -1;
    private int checksum = -1;

    public void setSegmentIndex(int i) {
        this.segmentIndex = i;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public void setOptionalData(int[] iArr) {
        this.optionalData = iArr;
    }

    public boolean isLastSegment() {
        return this.lastSegment;
    }

    public void setLastSegment(boolean z) {
        this.lastSegment = z;
    }

    public void setSegmentCount(int i) {
        this.segmentCount = i;
    }

    public void setSender(String str) {
        this.sender = str;
    }

    public void setAddressee(String str) {
        this.addressee = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setChecksum(int i) {
        this.checksum = i;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}

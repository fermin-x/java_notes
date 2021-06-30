package com.fermin.videocapture.bo;

public class CaptureRequest {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //    String filePath;
    String fileName;
    String filePath;
    String savePath;
    boolean timeRandom = true;
    String captureTime;
    String count = "1";
    String width;
    String height;
    boolean originalSize = true;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public CaptureRequest() {
    }

    public CaptureRequest(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public boolean isTimeRandom() {
        return timeRandom;
    }

    public void setTimeRandom(boolean timeRandom) {
        this.timeRandom = timeRandom;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(boolean originalSize) {
        this.originalSize = originalSize;
    }
}

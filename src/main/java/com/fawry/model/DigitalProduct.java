package com.fawry.model;

import java.math.BigDecimal;

public class DigitalProduct extends Product {
    private String downloadUrl;
    private String fileSize;
    
    public DigitalProduct() {}
    
    public DigitalProduct(Long id, String name, BigDecimal price, Integer quantity, 
                         String description, String downloadUrl, String fileSize) {
        super(id, name, price, quantity, description);
        this.downloadUrl = downloadUrl;
        this.fileSize = fileSize;
    }
    
    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
    
    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }
    
    @Override
    public boolean isPerishable() {
        return false;
    }
    
    @Override
    public boolean isShippable() {
        return false;
    }
    
    @Override
    public String getProductType() {
        return "DIGITAL";
    }
    
    @Override
    protected void validateSpecific() {
        if (downloadUrl == null || downloadUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Download URL cannot be null for digital products");
        }
        if (fileSize == null || fileSize.trim().isEmpty()) {
            throw new IllegalArgumentException("File size cannot be null for digital products");
        }
    }
    
    @Override
    protected double getProductWeight() {
        return 0.0;
    }
} 
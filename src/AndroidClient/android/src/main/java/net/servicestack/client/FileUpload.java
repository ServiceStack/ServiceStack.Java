package net.servicestack.client;

public class FileUpload {
    private String fieldName;
    private String fileName;
    private String contentType;
    private byte[] fileBytes;

    public FileUpload(String fieldName, String fileName, String contentType, byte[] fileBytes) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.contentType = contentType != null ? contentType : "application/octet-stream";
        this.fileBytes = fileBytes;
    }

    public String getFieldName() { return fieldName != null ? fieldName : "upload"; }
    public String getFileName() { return fileName; }
    public String getContentType() { return contentType; }
    public byte[] getFileBytes() { return fileBytes; }
}

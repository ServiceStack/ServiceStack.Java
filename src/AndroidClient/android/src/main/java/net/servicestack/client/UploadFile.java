package net.servicestack.client;

public class UploadFile {
    private String fieldName;
    private String fileName;
    private String contentType;
    private byte[] contents;

    public UploadFile(String fieldName, String fileName, String contentType, byte[] contents) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.contentType = contentType != null ? contentType : "application/octet-stream";
        this.contents = contents;
    }

    public String getFieldName() { return fieldName != null ? fieldName : "upload"; }
    public String getFileName() { return fileName; }
    public String getContentType() { return contentType; }
    public byte[] getContents() { return contents; }
}

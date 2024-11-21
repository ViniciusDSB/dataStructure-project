class FileData {
    private final String fileName;
    private final String content;

    public FileData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFilename() {
        return fileName;
    }

    public String getContent() {
        return content;
    }
}

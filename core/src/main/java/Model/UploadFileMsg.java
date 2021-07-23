package Model;

import java.io.File;
import java.io.Serializable;

public class UploadFileMsg implements Serializable {
private static final long SERIAL_ID = 1L;
private File file;
private int start;
private int end;
private byte[] bytes;
private String file_md5;

    public static long getSerialId() {
        return SERIAL_ID;
    }

    public File getFile() {
        return file;
    }
    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

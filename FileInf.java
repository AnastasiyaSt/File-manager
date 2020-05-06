import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

public class FileInf {
    private String name;
    private String type;
    private long size;
    private Date lastModif;

    public FileInf(Path path) throws IOException {
        File file=new File(String.valueOf(path));
        this.name=path.getFileName().toString();
        int ind=name.lastIndexOf('.');
        if(ind>0){
            name= name.substring(0, ind);
        }
        if (file.isDirectory()){
            this.size=-1;
            this.type="Directory";
        }
        else{
            this.size= file.length()/1024+1;
            this.type=getFileType(path);
        }
        long time=file.lastModified();
        this.lastModif=new Date(time);
    }

    private static String getFileType(Path path) {
        File file=new File(String.valueOf(path));
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public long getSize() {
        return size;
    }


    public Date getLastModif() {
        return lastModif;
    }

}

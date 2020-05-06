import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static Path path;

    public FileManager(Path path) {
        this.path = path;
    }

    public static void dir() throws IOException {
        File f=new File(String.valueOf(path));
        String[] data;
        data=f.list();
        System.out.println();
        System.out.printf("%-40s%-16s%-16s%-32s%n", "File name ", "Type ", "Size ","Last modified ");
        for(String p:data){
            FileInf fileInf=new FileInf(Paths.get(path+"/"+p));
            System.out.printf("%-40s", fileInf.getName());
            System.out.printf("%-16s", fileInf.getType());
            if(!fileInf.getType().equals("Directory")){
                System.out.printf("%-16s", fileInf.getSize()+" КБайт");
            }
            else {System.out.printf("%-16s","  ");}
            System.out.printf("%-32s", fileInf.getLastModif());
            System.out.println();
        }

    }

    public static void cd(Path newPath) throws Exception {

        if (isItAbsolute(newPath)){
            setPath(newPath);
        }
        else{
            newPath=Paths.get(String.valueOf(path)+"\\"+String.valueOf(newPath)).normalize();
        }
        if(Files.exists(newPath)){
            setPath(newPath);
            System.out.println(path);
        }
        else{
            System.err.println("This directory/file does not exist ");
        }


    }

    public static void open(Path newPath) throws Exception {

        if (!isItAbsolute(newPath.normalize())){
            newPath=Paths.get(String.valueOf(path)+"\\"+String.valueOf(newPath)).normalize();
        }
        File file = new File(String.valueOf(newPath.normalize()));
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            desktop.open(file);
        }
        else{ System.err.println("File not found");}
    }

    public static void copy(Path p1, Path p2) throws Exception{
        if (!FileManager.isItAbsolute(p1)){
            p1=Paths.get(String.valueOf(path)+"\\"+String.valueOf(p1)).normalize();
        }
        if (!FileManager.isItAbsolute(p2)){
            p2=Paths.get(String.valueOf(path)+"\\"+String.valueOf(p1)).normalize();
        }
        File f=new File(String.valueOf(p1));
        File f2=new File(String.valueOf(p2));
        FileInf f1=new FileInf(p1);
        String newName=String.valueOf(p2)+"\\"+f1.getName()+"copy."+f1.getType();
        p2=Paths.get(newName);
        try{
            if((f.exists())&(f2.exists())){
                Files.copy(p1, p2);
            }
            else{System.err.println("File/Directory not found");}

        }
        catch(FileAlreadyExistsException e){
            System.err.println("This file already exists");
        }
    }
    public static void delete(Path nPath){
        if(!isItAbsolute(nPath)){
            nPath=Paths.get(String.valueOf(path)+"\\"+String.valueOf(nPath)).normalize();
        }
        File file=new File(String.valueOf(nPath));
        if(file.delete()){
            System.out.println("File/Directory deleted");
        }else System.out.println("File/Directory not found "+nPath);

    }

    private static boolean isItAbsolute(Path path){
        if (String.valueOf(path.normalize()).indexOf(':')==1){
            return true;
        }
        return false;
    }

    public static void setPath(Path path) {
        FileManager.path = path;
    }

    public static Path getPath() {
        return path;
    }
}



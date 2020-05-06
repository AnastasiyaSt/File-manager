import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("Hello! I'm file manager!");
        System.out.println("I know commands:"+"\n"+"cd (path) -change directory"+"\n"+"dir-show directory"+"\n"+
                "open(path)-open file/directory" +"\n"+"delete(path)-delete file/directory"+"\n"+
                "copy(Path_source,Path_dest)-copy file/directory"+"\n");
        System.out.println("Write current directory");
        String str=sc.nextLine();
        Path p=Paths.get(str).normalize();
        String command=sc.nextLine();
        FileManager fm=new FileManager(p);

        while (command.equals("exit")!=true){
            switch (command){
                case "dir":
                    fm.dir();
                    command=sc.nextLine();
                    break;
                case "cd" :
                    Path newPath=Paths.get(sc.nextLine());
                    fm.cd(newPath);
                    command=sc.nextLine();
                    break;
                case "open":
                    Path nPath=Paths.get(sc.nextLine());
                    fm.open(nPath);
                    command = sc.nextLine();
                    break;
                case "copy":
                    Path p1=Paths.get(sc.nextLine());
                    Path p2=Paths.get(sc.nextLine());
                    fm.copy(p1,p2);
                    command=sc.nextLine();
                    break;
                case "delete":
                    Path path=Paths.get(sc.nextLine());
                    fm.delete(path);
                    command=sc.nextLine();
                    break;
                default:
                    System.err.println("Unknown command");
                    command = sc.nextLine();
                    break;
            }


        }
    }
}

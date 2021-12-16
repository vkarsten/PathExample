import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Path is an interface that is part ofJava's NIO (New Input/Output) file package

public class PathExample1 {
    // Creating a Path instance variable
    private Path path;

    public static void main(String[] args) {
        PathExample1 example = new PathExample1();
        System.out.println("Creating paths: ");
        example.createPath();
        System.out.println("------------------------------\n");

        System.out.println("Retrieving information about paths: ");
        example.getPathInfo();
        System.out.println("------------------------------\n");

        System.out.println("Converting paths: ");
        example.convertToAbsolute();
        example.convertToRealPath();
        example.convertToFile();
        System.out.println("------------------------------\n");

        System.out.println("Comparing paths: ");
        example.comparePaths();
        System.out.println("------------------------------\n");

        System.out.println("Joining paths: ");
        example.joinPaths();
        System.out.println("------------------------------\n");

        System.out.println("Removing paths: ");
        example.removePath();
    }

    // Writing a createPath() method that uses the static get() method of Paths class
    // Paths class implements Path interface
    private void createPath() {
        // get() method throws runtime InvalidPathException if the arguments passed contains illegal characters
        // can take more than one argument to join Strings into one path
        path = Paths.get("/WorkingFolder/Dec16/file1.txt"); // creates absolute path
        System.out.println("Created the path: " + path);
    }

    // Retrieving information about a path
    private void getPathInfo() {
        // Name elements of the path can be accessed by using an iterator (starting from the element closest to the root)
        // However, there are also different methods to access parts of the path separately:

        // returns "file1.txt"
        Path filename = path.getFileName();
        System.out.println("File name: " + filename);

        // returns "WorkingFolder" (the highest element before the root element)
        Path name0 = path.getName(0);
        System.out.println("Name 0: " + name0);

        // returns "WorkingFolder/Dec16" (start index is inclusive, end index is exclusive)
        Path subPath = path.subpath(0, 2);
        System.out.println("Subpath: " + subPath);

        // returns "/WorkingFolder/Dec16"
        Path parent = path.getParent();
        System.out.println("Parent: " + parent);

        // returns "/"
        Path root = path.getRoot();
        System.out.println("Root: " + root);

        // returns 3 (WorkingFolder, Dec16, file1)
        int count = path.getNameCount();
        System.out.println("Name count: " + count);
    }

    // Converting a path
    // From relative to absolute path:
    public void convertToAbsolute() {
        Path relative = Paths.get("file2.txt");
        System.out.println("Relative path: " + relative);

        // toAbsolutePath() method
        Path absolute = relative.toAbsolutePath();
        System.out.println("Absolute path: " + absolute);
    }

    // To a real path:
    public void convertToRealPath() {
        Path real = null;
        //Path realPath = Paths.get("realFil.txt"); // this will cause an error!
        Path realPath = Paths.get("realFile.txt");
        try {
            real = realPath.toRealPath(); // returns the real path of an existing file. That is why exceptions need to be handled in case the file is not found!
        }
        catch (IOException e) {
            System.out.println("Real path could not be created !");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Real path: " + real);
    }

    // To File
    public void convertToFile() {
        File file = path.toFile();
        System.out.println("Converted " + file + " to type " + file.getClass());
    }

    // Comparing paths
    public void comparePaths() {
        Path path1 = path.subpath(0, 2);
        Path path2 = path.getName(1);

        // equals(Path other) tests if two paths are equal
        System.out.println(path1 + " equals " + path2 + ": " + path1.equals(path2));

        // compareTo(Path other) compares two abstract paths lexicographically
        // returns zero if the argument is equal to this path, a value less than zero if this path is lexicographically less than the argument,
        // or a value greater than zero if this path is lexicographically greater than the argument.
        System.out.println(path1 + " is longer than " + path2 + ", so this returns: " + path1.compareTo(path2));
        System.out.println(path2 + " is shorter than " + path1 + ", so this returns: " + path2.compareTo(path1));

        // endsWith(Path other) tests if this path ends with the given path.
        System.out.println(path1 + " ends with " + path2 + ": " + path1.endsWith(path2));
        System.out.println(path2 + " ends with " + path1 + ": " + path2.endsWith(path1));
        // also works with Strings:
        String end = "Dec16";
        System.out.println(path1 + " ends with " + end + ": " + path1.endsWith(end));
    }

    // Joining paths
    public void joinPaths() {
        // with get()
        Path joined = Paths.get("/WorkingFolder/", "Dec16/file1.txt");
        System.out.println("Joined /WorkingFolder/ and Dec16/file1.txt to: " + joined);

        // with resolve()
        Path part1 = Paths.get("/WorkingFolder/");
        System.out.println("Join " + part1 + " and Dec16/file1.txt using resolve: " + part1.resolve("Dec16/file1.txt"));

        // relativize() constructs a path from one location in the file system to another location
        Path p1 = Paths.get("first");
        Path p2 = Paths.get("second");
        System.out.println("Relativizing " + p2 + " to " + p1 + ": " + p1.relativize(p2));
        System.out.println("Relativizing " + p1 + " to " + p2 + ": " + p2.relativize(p1));

    }

    // Removing paths
    public void removePath() {
        // Removing redundancies from files with normalize()
        Path redundantPath = Paths.get("./README.md"); // . refers to the current directory and will be removed
        System.out.println("This is the path " + redundantPath + " without redundancies: " + redundantPath.normalize());

        // Deleting files
        Path helloAliceAndViktoria = Paths.get("lalalalalaheheheh.txt");
        try {
            Files.deleteIfExists(helloAliceAndViktoria);
        } catch (IOException e) {
            e.printStackTrace(); // in case the file does not exist
        }

    }
}

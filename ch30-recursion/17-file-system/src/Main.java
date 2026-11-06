import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;

public class Main {
    // Folders to leave out: the compiled classes, and the version control folder.
    static final Set<String> SKIP = Set.of("classes", ".git");

    // Lists a folder and everything in it. Each folder calls the method again, one level deeper, so the output is
    // indented to show the tree.
    static void listDirectory(File folder, int depth) {
        File[] items = folder.listFiles();
        if (items == null) {
            System.out.println("Cannot read " + folder.getPath());
            return;
        }
        Arrays.sort(items);
        for (File item : items) {
            String name = item.getName();
            boolean isFolder = item.isDirectory();
            System.out.println("  ".repeat(depth) + name + (isFolder ? "/" : ""));
            if (SKIP.contains(name)) {
                continue;
            }
            // A symbolic link could point back up the tree, so links are listed but never followed.
            if (isFolder && !Files.isSymbolicLink(item.toPath())) {
                listDirectory(item, depth + 1);
            }
        }
    }

    // The bytes used by a file, or by a folder and everything in it. A file is the base case, since it has nothing
    // inside it to visit.
    static long diskUsage(File item) {
        long total = item.length();
        if (item.isDirectory() && !Files.isSymbolicLink(item.toPath())) {
            File[] children = item.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (!SKIP.contains(child.getName())) {
                        total += diskUsage(child);
                    }
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        File root = new File(System.getProperty("user.dir"));
        System.out.println(root.getName() + "/");
        listDirectory(root, 1);
        System.out.println();
        System.out.println("Disk usage: " + String.format("%,d", diskUsage(root)) + " bytes");
    }
}

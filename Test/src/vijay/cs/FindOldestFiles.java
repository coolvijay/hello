package vijay.cs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindOldestFiles {

    public static void main(String[] args) throws IOException
    {
        String parentFolder = "C:\\Users\\vijrana\\Desktop\\conflicts";
        int noOfOldFilesToGet = 2;

        List<Path> oldestFiles = getNOldestFilesFromDir(parentFolder, noOfOldFilesToGet);

        File f = new File(parentFolder);
        
        System.out.println( " is dir -----" + f.isDirectory());
        System.out.println( " path -----" + f.getPath());
        
        for(Path p : oldestFiles) {
            System.out.println(p.toAbsolutePath());    
            Files.move(p.toAbsolutePath(), Paths.get("C:\\Users\\vijrana\\Desktop\\one\\" + p.getFileName()));
        }
        
        
    }
    private static List<Path> getNOldestFilesFromDir(String parentFolder, int noOfOldFilesToGet) throws IOException {
        Comparator<? super Path> lastModifiedComparator = 
                (p1, p2) -> Long.compare(p1.toFile().lastModified(),
                                         p2.toFile().lastModified());

        List<Path> nOldFiles = Collections.emptyList();

        try (Stream<Path> paths = Files.walk(Paths.get(parentFolder))) {
            nOldFiles = paths.filter(Files::isRegularFile)
                               .sorted(lastModifiedComparator)
                               .limit(noOfOldFilesToGet)
                               .collect(Collectors.toList());
        }

        return nOldFiles;
    }
}

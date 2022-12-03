package devkosta.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import static devkosta.constants.Constants.INPUTS_PATH;

public class Problem {
    private final File[] inputFiles;

    public Problem(String inputDir) {
        this.inputFiles = new File(INPUTS_PATH + inputDir).listFiles();
    }

    public void solve(Function<String[], Integer> solvePart) {
        ArrayList<String> inputs = parseInputFiles();
        for (int i = 0; i < inputs.size(); ++i) {
            String input = inputs.get(i);
            String[] content = input.split("\\R");

            int res = solvePart.apply(content);

            System.out.printf("Test %d: %d\n", i + 1, res);
        }
        System.out.println();
    }

    private ArrayList<String> parseInputFiles() {
        ArrayList<String> parsedInputFiles = new ArrayList<>();
        for (File file : this.inputFiles) {
            String fileContent = parseInputFile(file);
            parsedInputFiles.add(fileContent);
        }

        return parsedInputFiles;
    }

    private String parseInputFile(File file) {
        try {
            Scanner fileReader = new Scanner(file);

            StringBuilder fileContent = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                fileContent.append(data).append(ls);
            }

            fileReader.close();

            return fileContent.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }

        return null;
    }
}

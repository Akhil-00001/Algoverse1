package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtil { 

    public static final String DELIMITER    = "|";
    public static final String LIST_SEP     = ",";
    public static final String EMPTY_MARKER = "-";

    
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("FileUtil read error [" + filePath + "]: " + e.getMessage());
        }
        return lines;
    }

    
    public static void writeLines(String filePath, List<String> lines) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("FileUtil write error [" + filePath + "]: " + e.getMessage());
        }
    }

    
    public static void appendLine(String filePath, String line) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("FileUtil append error [" + filePath + "]: " + e.getMessage());
        }
    }

    
    public static String esc(String value) {
        if (value == null || value.isEmpty()) return EMPTY_MARKER;
        return value.replace(DELIMITER, "\\|");
    }

    
    public static String unesc(String value) {
        if (EMPTY_MARKER.equals(value)) return "";
        return value.replace("\\|", DELIMITER);
    }

    
    public static String join(String... fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) sb.append(DELIMITER);
            sb.append(esc(fields[i]));
        }
        return sb.toString();
    }

    
    public static String[] split(String line) {
        String[] parts = line.split("\\|", -1);
        for (int i = 0; i < parts.length; i++) parts[i] = unesc(parts[i]);
        return parts;
    }

    
    public static void ensureFile(String filePath) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            if (!f.exists()) f.createNewFile();
        } catch (IOException e) {
            System.err.println("FileUtil ensureFile error [" + filePath + "]: " + e.getMessage());
        }
    }
}

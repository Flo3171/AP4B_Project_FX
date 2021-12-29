package fr.utbm.ap4b_project_fx.energySims.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUsage {
    

    public static List<String> readFile(String fileName){
        BufferedReader inputFlux = null;
        String readLine;
        List<String> lines = new ArrayList<>();
        try  {
            inputFlux = new BufferedReader(new FileReader(fileName));
            readLine = inputFlux.readLine();
            while (readLine != null){
                lines.add(readLine);
                readLine = inputFlux.readLine();
            }
        }
        catch (IOException exc){
            exc.printStackTrace();
        }
        try {
            inputFlux.close();
        }
        catch (IOException exc){
            exc.printStackTrace();
        }

        return lines;

    }

    public static void writeFile(String fileName, List<String> lines){
        Writer outputFlux = null;
        try{
            outputFlux = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            for (int i = 0; i < lines.size() - 1; i++) {
                outputFlux.write(lines.get(i) + "\n");
            }
            outputFlux.write(lines.get(lines.size() - 1));
        }
        catch (IOException exc){
            exc.printStackTrace();
        }
        try {
            outputFlux.close();
        }
        catch (IOException exc){
            exc.printStackTrace();
        }
    }
}

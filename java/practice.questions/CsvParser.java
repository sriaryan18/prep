import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CsvParser {

    public void parseCsv(String fileName){
        try{
            int rowNum = 0;
          BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
          while ((line = bufferedReader.readLine()) != null) {
            rowNum++;
            System.out.println("Reading rowNum: "+ rowNum);
            String[] values = line.split(",");
            System.out.println("Value in row " + line.toString());
          }
            
        }catch(Exception ex){
            ex.setStackTrace(null);

        }
    }

}

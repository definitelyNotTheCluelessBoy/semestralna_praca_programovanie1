import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.ArrayList;

/**
 * Nástroj na prácu so súbomy. Ich čítanie a zapisovanie do nich.
 * 
 * @author Matúš Kán
 */
public class FileTool {
    
    private String absolutePathToFolder;
    private int chessboardSize;
    
    /**
     * Konštrukto inicializuje atribúty a vytvorý zložku kde uloží všetky riešenia.
     * 
     * @param path Je to absolútna cesta k zložke v ktorej sa vytvorí nová zložka s riešeniami.
     * @param chessboardSize Je veľkosť šachovnice. Slúží na to aby nástroj vedel v akom rozmedzí má zapisovať dáta.
     */
    
    public FileTool(String path, int chessboardSize) {
        this.absolutePathToFolder = path + "riesenia\\";
        this.chessboardSize = chessboardSize;
        
        File file = new File(this.absolutePathToFolder);
        file.mkdir();
    }
    
    /**
     * Metóda ktorá uloží všetky riešenia.
     * @param solutionNumber Poradové číslo riešenia.
     * @param solution Riešenie v forme dvojrozmerného poľa.
     */
    
    public void saveSolution(int solutionNumber, ChessboardBox[][] solution) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(this.absolutePathToFolder + "riešenie_číslo_" + solutionNumber + ".txt"));
            for (int i = 0; i < this.chessboardSize; i++) {
                for (int j = 0; j < this.chessboardSize; j++) {
                    if (solution[i][j].hasQueen()) {
                        printWriter.print(1 + "  ");
                    }  else {
                        printWriter.print(0 + "  ");
                    }
                }
                printWriter.println("");
            }
            printWriter.close();
        }  catch (FileNotFoundException e) {
            System.out.println("Nenasiel som subor.");
        }
    }
    
    /**
     * Metóda ktorá vráti konkrétne požadované riešenie.
     * @param solutionNumber Poradové číslo riešenia.
     * @return Dvojrozmerný ArrayList ktorý slúží ako súradnicová mapa pre proces vykreslovania.
     */
    
    public ArrayList<ArrayList<Integer>> readSolution(int solutionNumber) {
        ArrayList<ArrayList<Integer>> wantedSolution = new ArrayList<ArrayList<Integer>>();
        
        try {
            Scanner scanner = new Scanner(new File(this.absolutePathToFolder + "riešenie_číslo_" + solutionNumber + ".txt"));
            while (scanner.hasNextLine()) {
                ArrayList<Integer> lineForWantedSolution = new ArrayList<Integer>();
                String[] line = scanner.nextLine().split(" ");
                for (String i : line) {
                
                    if (i.equals("0")) {
                        lineForWantedSolution.add(0);
                    } else if (i.equals("1")) {
                        lineForWantedSolution.add(1);
                    }
                }
                wantedSolution.add(lineForWantedSolution);
                System.out.println("");
            }
            scanner.close();
        }  catch (FileNotFoundException e) {
            System.out.println("Nenasiel som subor.");
        }
        return wantedSolution;
    }
    
    /**
     * Metóda ktorá zmaže všetky riešenia a následne vymaže aj zložku kde boli uložené.
     * @param numberOfSolutions počet riešení ktoré ma vymazať.
     */
    
    public void deleteFiles(int numberOfSolutions) {
        try {
            for (int i = 1; i <= numberOfSolutions; i++) {
                File file = new File(this.absolutePathToFolder + "riešenie_číslo_" + i + ".txt");
                file.delete();
            }
    
        }  catch (Exception e) {
            System.out.println("Nenasiel som subor.");
        }
    }
    
    /**
     * Metóda ktorá zmaže zložku kde boli uložené riešenia.
     */
    
    public void deleteFolder() {
        try {
            File file = new File(this.absolutePathToFolder);
            file.delete();
        }  catch (Exception e) {
            System.out.println("Nenasiel som subor.");
        }
    }
}

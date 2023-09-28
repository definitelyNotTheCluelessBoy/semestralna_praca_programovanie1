import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Hlavna plocha šachovnice kde sa odohráva hlavné prehľadávanie a výpočet 
 * všetkých riešení.
 * 
 * @author Matúš Kán 
 */

public class Chessboard {

    private int chessboardSize;
    private int counter = 0;
    private ChessboardBox[][] chessboardboxes;
    private Inspector inspector;
    private FileTool fileTool;
    private GraficChessboard graficChessboard;
    private TileWithQueen[][] tilesWithQueens;
    private long timer; 
    
    /**
     * Inicializuje poľe a naplní ho políčkami ktoré sú prázdene (bez dámi).
     * Násletne si vytvorí inšpektora (nástroj na overenie bezpečnosti políčka)
     * nakoľko vytvárať ho lokálne vo funkcí by bolo neefektívne keďže by sa
     * vytvoril nanovo vždy keď sa funkcia zavolá rekurzívne.
     * 
     * @param chessboardSize Veľkosť šachovnice a počet dám.
     */
    
    public Chessboard(int chessboardSize) {
        this.chessboardSize = chessboardSize;
        this.chessboardboxes = new ChessboardBox[this.chessboardSize][this.chessboardSize];
        
        this.graficChessboard = new GraficChessboard(this.chessboardSize);
        this.tilesWithQueens = this.graficChessboard.getTilesWithQueens();
        
        for (int i = 0; i < chessboardSize; i++) {
            for (int j = 0; j < chessboardSize; j++) {
                this.chessboardboxes[i][j] = new ChessboardBox(i, j);
            }
        }
        
        this.inspector = new Inspector(this.chessboardSize);
        this.fileTool = new FileTool("", this.chessboardSize);
        this.timer = 0;
        
        Manazer manager = new Manazer();
        manager.spravujObjekt(this);
    }

    /** 
     * Metóda ktorá slúži na opustenie programu.
     */
    
    public void exit() {
        JOptionPane.showMessageDialog(null, "Po stlačení OK sa program ukončí. Ďakujem za spoluprácu", "Ďakujem za spoluprácu", JOptionPane.WARNING_MESSAGE);
        this.fileTool.deleteFolder();
        System.exit(1);
    }
    
    /** 
     * Metóda ktorej úlohou je vymazať všetky texstové súbory po ukončení programu.
     */
    
    public void clearFolder() {
        this.fileTool.deleteFiles(this.counter);
    }
    
    /**
     * Metóda určená na animáciu a vizualizáciu prehľadávanie šachovnice.
     * Animuje každý krok. Keď nájde riešenie na 3 sekundy ho vysvieti.
     * 
     * @param numberOfPlacedQueens Posúva ďalej informáciu o tom koľko už je položených královien.
     * @param field Posúva ďalej informácie o tom kde už sú dámy vo forme dvoj rozmerného poľa.
     */
    
    private void animateSolving(int numberOfPlacedQueens, ChessboardBox[][] field) {
        
        if (numberOfPlacedQueens == this.chessboardSize) {
            this.holdTime(3000);
        } else {
            for (ChessboardBox box: field[numberOfPlacedQueens]) {
                int coordinatesX = box.giveCoordinatesOfBox()[0];
                int coordinatesY = box.giveCoordinatesOfBox()[1];
                
                this.tilesWithQueens[coordinatesY][coordinatesX].showQueen();
                this.holdTime(this.timer);
                
                if (!this.inspector.isBoxUnderThreat(field , coordinatesX, coordinatesY)) {
                    this.tilesWithQueens[coordinatesY][coordinatesX].changeColor("green");
                    this.tilesWithQueens[coordinatesY][coordinatesX].showQueen();
                    this.holdTime(this.timer);
                    
                    field[coordinatesX][coordinatesY].placeQueen();
                    this.animateSolving(numberOfPlacedQueens + 1, field);
                    field[coordinatesX][coordinatesY].removeQueen();
                }
                
                this.tilesWithQueens[coordinatesY][coordinatesX].changeColor("red");
                this.tilesWithQueens[coordinatesY][coordinatesX].showQueen();
                this.holdTime(this.timer);
                this.tilesWithQueens[coordinatesY][coordinatesX].changeColor(this.tilesWithQueens[coordinatesY][coordinatesX].getColor());
                this.tilesWithQueens[coordinatesY][coordinatesX].hideQueen();
            }
        }
    }
    
    /** 
     * Primitívna metóda ktorá "zdrží čas" aby sa proces riešenia dal sledovať.
     * 
     * @param delay Čas "zdržania" v milisekundách. 
     */
    
    public void holdTime(long delay) {

        long time =  System.currentTimeMillis();
        long wantedTime =  System.currentTimeMillis() + delay;
        while (time < wantedTime) {
            time = System.currentTimeMillis();
        }

    }
    
    /** 
     * Metóda kto nadvezuje na metodu holdTime().
     * Jej úlohou je zmeniť všeobecne dáný čas zdržania ktorý si zvolí používatel.
     * 
     * @param newTimer Nový čas zdržania animácie.
     */
    
    public void changeTimer(int newTimer) {
        this.timer = newTimer; 
    }
    
    /** 
     * Zaobalovacia metóda metódy animateSoloving().
     * Jéj úlohou je zavolať metódu animateSolving() s konkretnými doplnenými parametrami.
     */
    
    public void lookForSolutionsForAnimation() {
        this.animateSolving(0, this.chessboardboxes);
    }
    
    /** 
     * Metóda určená na vyhľadanie a zapísanie všetkých riešení.
     * Riešenia zapisuje do textových súborov.
     * 
     * @param numberOfPlacedQueens Posúva ďalej informáciu o tom koľko už je položených královien.
     * @param field Posúva ďalej informácie o tom kde už sú dámy vo forme dvoj rozmerného poľa.
     */
    
    private void findAllSolutionsForTextPrinting(int numberOfPlacedQueens, ChessboardBox[][] field) {
        if (numberOfPlacedQueens == this.chessboardSize) {
            this.counter++;
            this.fileTool.saveSolution(this.counter, field);
        } else {
            for (ChessboardBox box: field[numberOfPlacedQueens]) {
                int coordinatesX = box.giveCoordinatesOfBox()[0];
                int coordinatesY = box.giveCoordinatesOfBox()[1];
                
                if (!this.inspector.isBoxUnderThreat(field , coordinatesX, coordinatesY)) {
                    field[coordinatesX][coordinatesY].placeQueen();
                    this.findAllSolutionsForTextPrinting(numberOfPlacedQueens + 1, field);
                    field[coordinatesX][coordinatesY].removeQueen();
                }
              
            }
        }
    }

    /** 
     * Zaobalovacia metóda metódy findAllSolutionsForTextPrinting().
     * Jéj úlohou je zavolať metódu findAllSolutionsForTextPrinting() s konkretnými doplnenými parametrami.
     *
     * @return Vráti počet riešení.
     */
    
    public int lookForSolutionsForTextPrinting() {
        this.findAllSolutionsForTextPrinting(0, this.chessboardboxes);
        return this.counter;
    }
    
    /** 
     * Vykreslí vybrané riešenie ktoré si použivaťel zvolí. 
     *
     * @param solutionNumbe Číslo riešenia ktoré chce uživatel zobraziť.
     */
    
    public void displayChosenSolution(int solutionNumber) {
        ArrayList<ArrayList<Integer>> solution = this.fileTool.readSolution(solutionNumber);
        
        for (int i = 0; i < this.chessboardSize; i++) {
            for (int j = 0; j < this.chessboardSize; j++) {
                if (solution.get(j).get(i) == 1) {
                    this.tilesWithQueens[i][j].showQueen();
                }
            }
        }
    }
    
    /** 
     * Skryje všetky vykreslené královny aby mohol vykresliť nové riešenie.
     */
    
    public void hideAllQueens() {
        for (int i = 0; i < this.chessboardSize; i++) {
            for (int j = 0; j < this.chessboardSize; j++) {
                this.tilesWithQueens[i][j].hideQueen();
            }
        }
    }
}




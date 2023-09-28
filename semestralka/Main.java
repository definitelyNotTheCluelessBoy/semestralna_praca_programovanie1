import javax.swing.JOptionPane;

/**
 * Spúšťacia trieda pre spúšťaciu metódu main().
 * 
 * @author Matúš Kán
 */

public class Main {

    /**
     * Prázdný konštruktor.
     */

    private Main() {
    }

    /**
     * Hlavná metóda ktorá riadí chod programu.
     */

    public static void main(String[] args) {

        String[] options = {"animuj", "vykresli"};
        String mod = (String)JOptionPane.showInputDialog(null , "Ak si želáte animovať proces vyhľadávania zadajde \"animuj\".\n Ak si želáte vykresliť konkrétné hladané riešenie zadajte \"vykresli\"." , "Vyberte si režim programu." , JOptionPane.QUESTION_MESSAGE , null , options , options[1]);
        int size = Integer.parseInt(JOptionPane.showInputDialog(null , "Zadaj požadovaný počet dám." , "Zvolte velkosť" , JOptionPane.QUESTION_MESSAGE));

        Chessboard chessboard = new Chessboard(size); 

        if (mod.equals("animuj")) {
            int speed = Integer.parseInt(JOptionPane.showInputDialog(null , "Zadaj požadovanú rýchlosť animácie v milisekundách." , "Zvolte rýchlosť." , JOptionPane.QUESTION_MESSAGE));
            JOptionPane.showMessageDialog(null, "Ak si želáte poustiť animáciu a ukončiť program stlačte ESC", "Informácia o ukončení programu.", JOptionPane.ERROR_MESSAGE);
            chessboard.changeTimer(speed); 

            chessboard.lookForSolutionsForAnimation();
            
            chessboard.exit();
        } else {
            if (size == 2 || size == 3) {
                JOptionPane.showMessageDialog(null, "Pre počet dám ktoré ste si zvolili nexistuje riešenie\n nakoľko sa nedajú umiestniť ani raz tak aby sa neohrozovali.", "Neexistujúce riešenie", JOptionPane.WARNING_MESSAGE);
                chessboard.exit();
            } else {
                int numberOfSolutions = chessboard.lookForSolutionsForTextPrinting();

                int solutionNumber = Integer.parseInt(JOptionPane.showInputDialog(null , "Množstvo riešení pre " + size + " dám je " + numberOfSolutions + ".\n Zvoľte čislo riešenia ktoré si želáte zobraziť. (od 1 po " + numberOfSolutions + " )" ,
                            "Zvolte číslo riešenia." , JOptionPane.QUESTION_MESSAGE));
                while (solutionNumber < 1 || solutionNumber > numberOfSolutions) {
                    solutionNumber = Integer.parseInt(JOptionPane.showInputDialog(null , "Zvolené riešenie bohužial neexistuje skúste zvoliť riešenie ešte raz.\n Množstvo riešení pre " + size + " dám je " + numberOfSolutions + ".\n Zvoľte čislo riešenia ktoré si želáte zobraziť. (od 1 po " + numberOfSolutions + " )" ,
                            "Zvolte číslo riešenia." , JOptionPane.QUESTION_MESSAGE));
                }

                chessboard.displayChosenSolution(solutionNumber);
                while (JOptionPane.showConfirmDialog(null, "Prajete si zobraziť iné riešenie?") == 0) {
                    solutionNumber = Integer.parseInt(JOptionPane.showInputDialog(null , "Množstvo riešení pre " + size + " dám je " + numberOfSolutions + ".\n Zvoľte čislo riešenia ktoré si želáte zobraziť. (od 1 po " + numberOfSolutions + " )" ,
                            "Zvolte číslo riešenia." , JOptionPane.QUESTION_MESSAGE));
                    while (solutionNumber < 1 || solutionNumber > numberOfSolutions) {
                        solutionNumber = Integer.parseInt(JOptionPane.showInputDialog(null , "Zvolené riešenie bohužial neexistuje skúste zvoliť riešenie ešte raz.\n Množstvo riešení pre " + size + " dám je " + numberOfSolutions + ".\n Zvoľte čislo riešenia ktoré si želáte zobraziť. (od 1 po " + numberOfSolutions + " )" ,
                                "Zvolte číslo riešenia." , JOptionPane.QUESTION_MESSAGE));
                    }
                    chessboard.hideAllQueens();
                    chessboard.displayChosenSolution(solutionNumber);
                }

                chessboard.clearFolder();
                chessboard.exit();
            }
        }
    }
}

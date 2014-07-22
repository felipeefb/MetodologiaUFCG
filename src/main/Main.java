
package main;

import core.bst.BSTImpl;
import core.estrutura.Estrutura;
import core.estrutura.EstruturaHashMap;
import core.linked_list.SingleLinkedList;
import utils.CalculaMemoUtilizada;
import utils.CalculaTempo;
import utils.DadosAnalisados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class Main2 is used to simply run without passing args on terminal.
 */
public class Main {

    /** The Constant LIST. */
    private static final String LIST = "list";

    /** The Constant AVLTREE. */
    private static final String BSTTREE = "bsttree";

    /** The Constant BSTTREE. */
    private static final String HASHTABLE = "hash";

    /** The estrutra. */
    private static Estrutura estrutura;

    /** Memory Usage Checker . */
    private static CalculaMemoUtilizada memoriaUtilizada;

    /** The load total time. */
    private static long loadTotalTime;

    /** The query total time. */
    private static long queryTotalTime;

    /** The write dados. */
    private static DadosAnalisados dadosAnalisados;

    /**
     * Exec query.
     * 
     * @param path the path
     */
    public static void execQuery(String path) {
        BufferedReader br = null;
        CalculaTempo cTime = new CalculaTempo();

        try {

            String word;

            br = new BufferedReader(new FileReader(path));

            boolean result = false;

            while ((word = br.readLine()) != null) {
                cTime.startTime();
                result = estrutura.search(word.toLowerCase());
                if (result) {
                    System.out.println(word + " : S");
                } else {
                    System.out.println(word + " : N");
                }
                dadosAnalisados.writeConsulta(word, result ? "S" : "N", cTime.stopTime());

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Load data.
     * 
     * @param path the path
     */
    public static void loadData(String path) {
        BufferedReader br = null;
        CalculaTempo cTime = new CalculaTempo();

        try {

            String word;

            br = new BufferedReader(new FileReader(path));

            cTime.startTime();

            memoriaUtilizada.checkMemoInicial();

            while ((word = br.readLine()) != null) {
                cTime.startTime();
                estrutura.insert(word.toLowerCase());
            }

            memoriaUtilizada.checkMemoFInal();
            long tempFinal = cTime.stopTime();
            dadosAnalisados.writeInsert(word, tempFinal);
            dadosAnalisados.writeMemoria(word, tempFinal);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {
        // instanciar o medidor de tempo e de memoria antes do metodo load, caso
        // contrario o tempo de medição será interferido pelo tempo de
        // instanciação das classes
        memoriaUtilizada = new CalculaMemoUtilizada();

        // Path para o arquivo contendo as palavras que serao adicionadas.
        String dataPath;

        // Path para o arquivo contendo as palavras que serao consultadas.
        String queryPath;

        // FLAG para definir qual estrutura será usada: LIST, BSTTREE ou
        // AVLTREE
        String StructureType;

        if (args.length < 3) {
            System.err.print("Missing args\n");
            return;
        } else {
            StructureType = args[0];
            dataPath = args[1];
            queryPath = args[2];
        }

        if (StructureType.equals(LIST)) {
            estrutura = new SingleLinkedList<String>();
        } else if (StructureType.equals(BSTTREE)) {
            estrutura = new BSTImpl<String, String>();
        } else if (StructureType.equals(HASHTABLE)) {
            estrutura = new EstruturaHashMap();
        } else {
            System.err
                    .println("Estrutura invalida, escolha entre: "
                            + LIST + ", " + HASHTABLE + " ou " + BSTTREE);
            return;
        }

        dadosAnalisados = new DadosAnalisados(StructureType);

        CalculaTempo cTime = new CalculaTempo();

        cTime.startTime();
        loadData(dataPath);
        loadTotalTime = cTime.stopTime();

        cTime.startTime();
        execQuery(queryPath);
        queryTotalTime = cTime.stopTime();

        System.out.println("tempo_de_carga : " + String.valueOf(loadTotalTime));
        System.out.println("tempo_da_consulta : "
                + String.valueOf(queryTotalTime));
        dadosAnalisados.writeTotalTime(StructureType, queryTotalTime);

        double memoryUsage = memoriaUtilizada.getMemoUtilizada();
        // ManipulateTextFile.addCSVMemoryUsage(algorithm, memoryUsage);
        dadosAnalisados.writeMemoria(StructureType, (long) memoryUsage);

        System.out.println("consumo_de_memoria : "
                + String.valueOf(memoryUsage));
    }
}

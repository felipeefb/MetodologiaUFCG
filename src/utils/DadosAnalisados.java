
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// TODO: Auto-generated Javadoc
/**
 * The Class DadosAnalisados.
 */
public class DadosAnalisados {

    /** The file name query. */
    private static String FILE_NAME_QUERY;

    /** The file name insert. */
    private static String FILE_NAME_INSERT;

    /** The file name memory. */
    private static String FILE_NAME_MEMORY;

    /** The file name total time. */
    private static String FILE_NAME_TOTAL_TIME;

    /** The writer query. */
    PrintWriter writerQuery;

    /** The writer insert. */
    PrintWriter writerInsert;

    /** The writer memory. */
    PrintWriter writerMemory;

    /** The writer total time. */
    PrintWriter writerTotalTime;

    /**
     * Instantiates a new write analysis.
     * 
     * @param algorithm the algorithm
     */
    public DadosAnalisados(String algorithm) {
        FILE_NAME_QUERY = "query_" + algorithm + ".csv";
        FILE_NAME_INSERT = "insert_" + algorithm + ".csv";
        FILE_NAME_MEMORY = "memory_" + algorithm + ".csv";
        FILE_NAME_TOTAL_TIME = "total_time" + ".csv";

        try {

            writerQuery = new PrintWriter(FILE_NAME_QUERY, "UTF-8");
            writerQuery.println("Word " + " , " + " Find , Time");
            writerInsert = new PrintWriter(FILE_NAME_INSERT, "UTF-8");
            writerInsert.println("Word  " + " ," + " Time to Insert");
            writerMemory = new PrintWriter(FILE_NAME_MEMORY, "UTF-8");
            writerMemory.println("Word" + " , " + " Memory");
            File totalTime = new File(FILE_NAME_TOTAL_TIME);
            try {
                if (!totalTime.exists()) {
                    totalTime.createNewFile();
                }
                writerTotalTime = new PrintWriter(new FileWriter(FILE_NAME_TOTAL_TIME, true));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("deu pau");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("deu pau");
            e.printStackTrace();
        }

    }

    /**
     * Write time cost for inserting a word.
     * 
     * @param word the word
     * @param time the time
     */
    public void writeInsert(String word, long time) {
        writerInsert.println(word + ", " + String.valueOf(time));
        writerInsert.flush();
    }

    /**
     * Write query time cost for a specific word.
     * 
     * @param word the word
     * @param resp the resp
     * @param time the time
     */
    public void writeConsulta(String word, String resp, long time) {
        writerQuery.println(word + ", " + resp + " , " + String.valueOf(time));
        writerQuery.flush();
    }

    /**
     * Write memoria.
     * 
     * @param word the word
     * @param time the time
     */
    public void writeMemoria(String word, long time) {
        writerMemory.println(word + ", " + String.valueOf(time));
        writerMemory.flush();
    }

    /**
     * Closing files.
     */
    public void close() {

        writerInsert.close();
        writerQuery.close();
        writerMemory.close();
        writerTotalTime.close();
    }

    /**
     * Write total time.
     * 
     * @param structureType the structure type
     * @param queryTotalTime the query total time
     */
    public void writeTotalTime(String structureType, long queryTotalTime) {
        writerTotalTime.println(structureType + " , " + String.valueOf(queryTotalTime));
        writerTotalTime.flush();
    }
}

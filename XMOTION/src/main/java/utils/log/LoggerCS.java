/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.log;

import java.util.logging.Logger;

/**
 *
 * @author Movement
 */
public class LoggerCS {

    private static String logConfig = "";
    private static Logger logger;

    public static void setLogConfigFileName(String filename) {
        logConfig = filename;

    }

    public static void saveLog(
            String message) {
        logger.info(message);
    }
}

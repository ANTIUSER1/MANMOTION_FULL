/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.log;

import org.apache.log4j.Logger;

/**
 *
 * @author Movement
 */
public class LoggerCS {

    final static Logger logger = Logger.getLogger(LoggerCS.class);

    public static void logInfo(String args) {
        logger.info(args);
    }

    public static void logError(String args) {
        logger.error(args);

    }

}

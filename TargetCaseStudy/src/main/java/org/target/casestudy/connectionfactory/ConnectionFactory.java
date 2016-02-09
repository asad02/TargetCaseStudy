/**
 * 
 */
package org.target.casestudy.connectionfactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.target.casestudy.constant.ApplicationConstant;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 * 
 * This class is a connection manager, this will create a new sqlMapClient it not created.
 */
public class ConnectionFactory {
	
    private static SqlMapClient sqlMapClient;
    
    // Loaded only when this class will be loaded first time.
    static {
        try {
 
            String resource = ApplicationConstant.CONFIG_FILE;
            Reader reader = Resources.getResourceAsReader(resource);
            // if sqlMapClient is null then build a client using the reader.
            if (sqlMapClient == null) {
            	sqlMapClient = SqlMapClientBuilder.buildSqlMapClient (reader);
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    /**
     * This static method will return an instance of sqlMapClient,
     * and accessible directly with the class name.
     * @return sqlMapClient
     */
    public static SqlMapClient getSqlMapFactory() {
        return sqlMapClient;
    }

}

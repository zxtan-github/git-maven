package org.ifunq.tanzx.autoConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * AutoConifg测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-07-28 16:31
 **/
public class AutoConifgTest01 {

    public static void main( String[] args ) throws Exception {
        InputStream is = AutoConifgTest01.class.getClassLoader().getResourceAsStream( "config.properties" );
        if ( is == null ) {
            System.err.println( "Can not load config resource config.properties in classpath" );
        } else {
            Properties prop = new Properties();
            prop.load( is );
            is.close();
            for(String key : prop.stringPropertyNames() ) {
                String value = prop.getProperty( key );
                if ( value != null ) {
                    System.out.printf( "%s = %s %n", key, value );
                }
            }
        }
    }

}
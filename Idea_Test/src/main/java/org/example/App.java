package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println( "Hello World!" );
    }
}

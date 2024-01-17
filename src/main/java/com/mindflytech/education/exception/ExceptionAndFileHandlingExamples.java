package com.mindflytech.education.exception;

import com.mindflytech.util.Utils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import jakarta.servlet.ServletException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import static com.mindflytech.util.Utils.generateFileAbsolutePath;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Log4j2
public class ExceptionAndFileHandlingExamples {
    private static final String DIRECTORY = "C:\\ws\\github\\java-education-examples";

    static {
        //compile error
        //Unhandled exception: java.lang.Exception
//        throw new Exception("-- checked exception in static initializer cannot be thrown");

        //compile error
        //Initializer must be able to complete normally
//        throw new RuntimeException("-- unchecked exception in static initializer cannot be thrown");

        System.out.println("-- within static initializatior <clinit>, an exception will be thrown");
        callRuntimeException();
    }

    private static void callRuntimeException() {
        throw new RuntimeException("-- this is not detected by the compiler and makes the thread collapse");
    }

    {
        System.out.println("-- initializer (DOES NOT) throws exception");
        Runnable runnable = () -> {
            System.out.println("-- runnable starts, will (NOT) throw exception");
            //can't throw exception here
//            throw new Exception("-- exception from lambda");
        };
        //DON'T DO THIS: IF THERE IS NO CONSTRUCTOR, YOU NEED TO HANDLE IT HERE
        //IF THERE IS A CONSTRUCTOR, YOU CAN'T HANDLE IT THERE, IT WILL INEVITABLE THROW
        //AN EXCEPTION
//        throw new Exception("-- exception from initializer");
        callRuntimeException();
    }

    @SneakyThrows
    public static void main(String[] args) throws ServletException, IOException, FileNotFoundException {
        ExceptionAndFileHandlingExamples exceptionExamples = new ExceptionAndFileHandlingExamples();
        exceptionExamples.runExamples();
    }

    private void runExamples() throws ServletException, IOException, SQLException {
        try {
            catchSubtypeOfThrownException();
        } catch(Throwable t) {
            System.out.println("-- Throwable caught: " + t);
        } finally {
            System.out.println("-- exception thrown, not caught, program will exit");
            //resoure close operations
        }
        // compile error
        // //finally' without 'try'
        // finally {
//
//        }
//        withoutTryWithResources();
//        tryMultiCatch();
//        tryMultiCatchOwnExceptionType();
        try {
            tryWithResources();
            System.out.println("-- if you see this,  no " +
                "exception was thrown in tryWithResources");
        } catch (IOException | SQLException e) {
            System.out.println("-- exception in tryWithResources(): " + e);
        }
//        finallyNotExecuted();
//        try {
//            tryCatchFinallyExecutionOrderDemonstrated();
//        } catch (SQLException e) {
//            System.out.println("-- SQLException caught in tryCatchFinallyExecutionOrderDemonstrated: " + e);
//        }

        try {
            tryCatchFinallyExecutionOrderDemonstrated();
        } catch (SQLException e) {
            System.out.println("-- SQLException caught in tryCatchFinallyExecutionOrderDemonstrated: " + e);
        }

        try {
            tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn();
        } catch (SQLException e) {
            System.out.println("-- SQLException caught, which means finally's exception DOES NOT supress " +
                    "try's exception; in tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn: " + e);
        } catch (IOException e) {
            System.out.println("-- IOException caught, which means finally's exception DOES  supress " +
                    " try's exception; in tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn: " + e);
        }

        try {
            tryFinallyDoesFinallySupressExceptionsWithItsOwn();
        } catch (SQLException e) {
            System.out.println("-- SQLException caught, which means finally's exception DOES NOT supress " +
                    "try's exception; in tryFinallyDoesFinallySupressExceptionsWithItsOwn: " + e);
        } catch (IOException e) {
            System.out.println("-- IOException caught, which means finally's exception DOES  supress " +
                    " try's exception; in tryFinallyDoesFinallySupressExceptionsWithItsOwn: " + e);
        }

        try {
            String result = tryCatchFinallyDoesFinallyOverrideReturnValue();
            System.out.println("-- result of tryCatchFinallyDoesFinallyOverrideReturnValue: '" + result + "'");
        } catch (SQLException e) {
            System.out.println("-- SQLException caught in tryCatchFinallyDoesFinallyOverrideReturnValue: " + e);
        } catch (IOException e) {
            System.out.println("-- IOException caught in tryCatchFinallyDoesFinallyOverrideReturnValue: " + e);
        }

        try {
            String result = tryFinallyDoesFinallyOverrideReturnValue();
            System.out.println("-- result of tryFinallyDoesFinallyOverrideReturnValue: '" + result + "'");
        } catch (SQLException e) {
            System.out.println("-- SQLException caught in tryFinallyDoesFinallyOverrideReturnValue: " + e);
        } catch (IOException e) {
            System.out.println("-- IOException caught in tryFinallyDoesFinallyOverrideReturnValue: " + e);
        }


    }

    private void tryCatchFinallyExecutionOrderDemonstrated() throws SQLException {
        try {
            System.out.println("-- tryCatchFinallyExecutionOrderDemonstrated try block begins");
            throwsSqlExceptionWithFiftyPercentChance();
            System.out.println("-- tryCatchFinallyExecutionOrderDemonstrated try block, after throwsSqlException (50% chance reached)");
        } catch (SQLException e) {
            System.out.println("-- tryCatchFinallyExecutionOrderDemonstrated in catch block, caught SQLException: " + e);
            throw e;
        } finally {
            System.out.println("-- tryCatchFinallyExecutionOrderDemonstrated finally block executed");
        }
    }

    private void tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn() throws SQLException, IOException {
        try {
            System.out.println("-- tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn try block begins");
//            throwsSqlExceptionWithFiftyPercentChance();
            surelyThrowsSqlException();
            System.out.println("-- tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn try block, after throwsSqlException (50% chance reached)");
        } catch (SQLException e) {
            System.out.println("-- tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn in catch block, caught SQLException: " + e);
            throw e;
        } finally {
            System.out.println("-- tryCatchFinallyDoesFinallySupressExceptionsWithItsOwn finally block executed");
            surelyThrowsIoException();
        }
    }

    private void tryFinallyDoesFinallySupressExceptionsWithItsOwn() throws SQLException, IOException {
        try {
            System.out.println("-- tryFinallyDoesFinallySupressExceptionsWithItsOwn try block begins");
//            throwsSqlExceptionWithFiftyPercentChance();
            surelyThrowsSqlException();
            System.out.println("-- tryFinallyDoesFinallySupressExceptionsWithItsOwn try block, after throwsSqlException (50% chance reached)");
        } finally {
            System.out.println("-- tryFinallyDoesFinallySupressExceptionsWithItsOwn finally block executed");
            surelyThrowsIoException();
        }
    }

    private String tryCatchFinallyDoesFinallyOverrideReturnValue() throws SQLException, IOException {
        try {
            System.out.println("-- tryCatchFinallyDoesFinallyOverrideReturnValue, try block begins");
//            throwsSqlExceptionWithFiftyPercentChance();
            surelyThrowsSqlException();
            neverActuallyThrowsSqlExceptionButIndicatesItInThrowsClause();
//            System.out.println("-- try block, after throwsSqlException (never reached)");
            return "returned-from-try";
        } catch (SQLException e) {
            System.out.println("-- tryCatchFinallyDoesFinallyOverrideReturnValue, in catch block, caught SQLException: " + e);
            return "returned-from-catch";
        } finally {
            System.out.println("-- tryCatchFinallyDoesFinallyOverrideReturnValue, finally block executed");
            return "returned-from-finally";
        }
    }

    private String tryFinallyDoesFinallyOverrideReturnValue() throws SQLException, IOException {
        try {
            System.out.println("-- tryFinallyDoesFinallyOverrideReturnValue, try block begins");
//            throwsSqlExceptionWithFiftyPercentChance();
//            surelyThrowsSqlException();
            neverActuallyThrowsSqlExceptionButIndicatesItInThrowsClause();
//            System.out.println("-- try block, after throwsSqlException (never reached)");
            return "returned-from-try";
        }  finally {
            System.out.println("-- tryFinallyDoesFinallyOverrideReturnValue, finally block executed");
            return "returned-from-finally";
        }
    }

    private void finallyNotExecuted() {
        try {
            System.out.println("-- try block started. will system.exit():");
            System.exit(-1);
            System.out.println("-- after system.exit()");
        } finally {
            System.out.println("-- in finally block");
        }
    }

    private void tryMultiCatch() throws SQLException, IOException {
        try {
            throwsIoException();
            throwsSqlExceptionWithFiftyPercentChance();
        } catch (IOException | SQLException ex) {
            Class<? extends Exception> exceptionClass = ex.getClass();
            log.error("Exception thrown: " + ex + ", class: " + exceptionClass);
            throw ex;
        }
    }

//    class SuperException extends Exception {}
//    class SubException extends SuperException {}

//    private void tryMultiCatchOwnExceptionType() throws SubException, SuperException {
//        try {
////            throwsIoException();
////            throwsSqlException();
//            //Compile error!
//            //Types in multi-catch must be disjoint:
//            // 'com.mindflytech.education.exception.ExceptionAndFileHandlingExamples.SubException'
//            // is a subclass of
//            // 'com.mindflytech.education.exception.ExceptionAndFileHandlingExamples.SuperException'
////        } catch (SubException | SuperException ex) {
//        } catch (SuperException | SubException ex) {
//            Class<? extends Exception> exceptionClass = ex.getClass();
//            log.error("Exception thrown: " + ex + ", class: " + exceptionClass);
//            throw ex;
//        }
//    }

    private void throwsIoException() throws IOException{
        boolean thrown = Utils.fiftyPercentChance();
        if(thrown) {
            throw new IOException("I am just a tiny IOException");
        }
    }

    private void surelyThrowsIoException() throws IOException{
        throw new IOException("I am a BIG IOException");
    }

    public void exceptionHandler() {
        try {
            declaredToThrowCheckedAndUncheckedException();
        } catch (IOException | IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public void declaredToThrowCheckedAndUncheckedException() throws IOException, IndexOutOfBoundsException {

    }

    private void throwsSqlExceptionWithFiftyPercentChance() throws SQLException{
        System.out.println("-- throwsSqlException() called");
        boolean thrown = Utils.fiftyPercentChance();
        if(thrown) {
            System.out.println("-- int throwsSqlException(), SQLException will be thrown");
            throw new SQLException("I am just a tiny SQLException");
        }
    }

    private void surelyThrowsSqlException() throws SQLException{
        throw new SQLException("I am a big SQLException");
    }

    private void neverActuallyThrowsSqlExceptionButIndicatesItInThrowsClause() throws SQLException{
        System.out.println("-- bloerp");
    }

    private void withoutTryWithResources() {
        withPrintWriter();
        withAppendingToFile();
    }

    private void withPrintWriter() {
        PrintWriter generatedTempFileWriter;
        try {
            String fileName = generateFileAbsolutePath(DIRECTORY);
            log.info("-- FYI current filename is: '" + fileName + "'");
            generatedTempFileWriter = new OwnPrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("-- file not found, program will exit");
            throw new RuntimeException(e);
        }
        generatedTempFileWriter.append("something without try-with-resources");
        generatedTempFileWriter.flush();
        generatedTempFileWriter.close();
    }

    private void withAppendingToFile() {
        String testFileName = "test.txt";
        String dateTime = Utils.generateCurrentDateTime();
        String toAppend = System.lineSeparator() + "I will repeatedly append stuff to the file at: " + dateTime;
        try {
            Path testPath = Paths.get(testFileName);
            Files.writeString(testPath, toAppend, APPEND);
        } catch (IOException e) {
            log.error("-- IOException happened while writing to '" + testFileName + "', exception is: " + e);
            throw new RuntimeException(e);
        }

        try {
            write(toAppend);
        } catch (IOException e) {
            log.error("-- IOException happened while writing to '" + testFileName + "', exception is: " + e);
            throw new RuntimeException(e);
        }

        writeWithOldschoolExceptionHandling(testFileName, toAppend);
    }

    private void write(final String s) throws IOException {
        final String u = "uu";
        Files.writeString(
                Path.of(System.getProperty("java.io.tmpdir"), "filename.txt"),
                s + System.lineSeparator(),
                CREATE, APPEND
        );
    }

    /**
     * Code is a courtesy of https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
     * Do not use old strategy without try-with-resources
     * @param text the text to be written into the file
     */
    private void writeWithOldschoolExceptionHandling(String fileName, String text) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(text);
            out.close();
        } catch (IOException e) {
            log.error("-- IOException occurred while writing: '" + text + "' to file: '" + fileName + "', e: " + e);
        }
        finally {
            if(out != null) {
                out.close();
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                log.error("Couldn't close BufferedWriter, possible memory leak");
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                log.error("Couldn't close FileWriter, possible memory leak");
            }
        }
    }

    private void tryWithResources() throws IOException, SQLException {
        try(PrintWriter writer = new OwnPrintWriter("test.txt");
//                String test = "test";
                Connection connection = null) {
            System.out.println("-- within tryWithResources() try-w-rs block");
            writer.append("something will be written into the file");
            writer.flush();
            throwsSqlExceptionWithFiftyPercentChance();
        }

        System.out.println("-- after execution of tryWithResources() try-w-rs block");

        /**
         * Demonstration of
         *
         * "Streams have a BaseStream.close() method and implement AutoCloseable, but nearly all
         * stream instances do not actually need to be closed after use. Generally, only streams
         * whose source is an IO channel (such as those returned by Files.lines(Path, Charset))
         * will require closing. Most streams are backed by collections, arrays, or generating
         * functions, which require no special resource management. (If a stream does require
         * closing, it can be declared as a resource in a try-with-resources statement.)"
         */
        try(Stream<String> fileStream = Files.lines(Path.of("test.txt"))) {
            fileStream.forEach(line -> System.out.println("-- line of file: '" + line + "'"));
        }
    }

    private void catchSubtypeOfThrownException() throws ServletException{
        try {
            throwSomething();
        } catch(HttpRequestMethodNotSupportedException e) {
            System.out.println("-- HttpRequestMethodNotSupportedException thrown");
        } catch(ServletException e) {
            System.out.println("-- ServletException thrown");
        }
        //compile error
        //Exception 'org.springframework.web.HttpMediaTypeException' has already been caught
//        catch (HttpMediaTypeException e) {
//            System.out.println("-- CloneNotSupportedException thrown");
//        }
        catch(CloneNotSupportedException e) {
            System.out.println("-- CloneNotSupportedException thrown");
        } catch(RuntimeException e) {
            System.out.println("-- RuntimeException thrown");
        }
        //THE SAME IS TRUE FOR RUNTIME EXCEPTIONS AS WELL
        //Compile error
        //Exception 'java.lang.IndexOutOfBoundsException' has already been caught
//        catch(IndexOutOfBoundsException e) {
//
//        }
        //compile error
        //Exception 'javax.xml.transform.TransformerException' is never thrown in the corresponding try block
//        catch (TransformerException e) {
//
//        }
    }

    private void throwSomething() throws ServletException, CloneNotSupportedException {
        long millis = System.currentTimeMillis();
        long remainder = millis % 4;
        if(remainder == 0) {
            throw new ServletException();
        } else if(remainder == 1){
            throw new HttpRequestMethodNotSupportedException("throwSomething");
        } else {
            throw new CloneNotSupportedException();
        }
    }
}

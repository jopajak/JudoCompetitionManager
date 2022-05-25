package com.example.judocompetitionmanager;

import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.Options;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.iq80.leveldb.impl.Iq80DBFactory.*;

/**
 * A wrapper class for leveldb's port for java implementing simple
 * reads/writes/deletes of strings and json data types.
 * You should always remember to close the stream with close()
 * in order to prevent memory leaks.
 * <p>https://github.com/dain/leveldb
 */

public final class Database {
    private static final Boolean COMPRESSION = false;
    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
    private static final Integer DB_RETRY_COUNT = 5;
    private static final Integer DB_RETRY_MILLISECONDS = 500;
    // Cache size specified in MiB
    private static final Integer CACHE_SIZE = 500;
    private static Database instance = new Database();
    private static final String FILENAME = "database";
    private static DB db;

    private Database() {
        Options options = new Options();
        options.cacheSize(Long.valueOf(CACHE_SIZE) * 1048576); // in MiB
        options.compressionType(CompressionType.NONE);
        try {
            db = factory.open(new File(FILENAME), options);
            LOGGER.log(Level.INFO, "DB initialized successfully");
            LOGGER.log(Level.INFO, "directory: {0}", getFilename());
            LOGGER.log(Level.INFO, "cache: {0} MiB", getCacheSize());
            LOGGER.log(Level.INFO, "compression: {0}", isCompressionEnabled());
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to create DB, exception: ", ioException);
            System.exit(1);
        }
    }

    /**
     * Gets and existing DB instance or creates a new one if none is found.
     * @return Database instance
     */
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    /**
     * Closes an open DB IOStream.
     */
    public static void close() {
        for (int i = 0; i < DB_RETRY_COUNT; i++) {
            try {
                Database.instance = null;
                db.close();
                break;
            } catch (IOException ioException) {
                LOGGER.log(Level.SEVERE, "Failed to close DB: ", ioException);
                try {
                    Thread.sleep(DB_RETRY_MILLISECONDS);
                } catch (InterruptedException interruptedException) {
                    LOGGER.log(Level.SEVERE, "Interrupted while trying to close the DB: ", interruptedException);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Writes a String to the DB.
     *
     * @param key   a String that will be used to access the value
     * @param value a String that will be written
     */
    public void writeString(String key, String value) {
        LOGGER.log(Level.FINE, "Writing String at key: {0} ", key);
        db.put(bytes(key), bytes(value));
    }

    /**
     * Writes a String to the DB.
     *
     * @param key   a String that will be used to access the value
     * @param value a byte sequence that will be written
     */
    public void writeBytes(String key, byte[] value) {
        LOGGER.log(Level.FINE, "Writing byte[] at key: {0} ", key);
        db.put(bytes(key), value);
    }

    /**
     * Reads a value under the key as a String.
     *
     * @param key a String that will be used to access the value
     * @return String representation of the value or an empty String if the value is not found
     */
    public byte[] readBytes(String key) {
        LOGGER.log(Level.FINE, "Reading byte[] at key: {0} ", key);
        return db.get(bytes(key));
    }

    /**
     * Reads a value under the key as a String.
     *
     * @param key a String that will be used to access the value
     * @return String representation of the value or an empty String if the value is not found
     */
    public String readString(String key) {
        LOGGER.log(Level.FINER, "Reading String at key: {0} ", key);
        byte[] value = db.get(bytes(key));
        if (value == null) {
            LOGGER.log(Level.WARNING, "Reading String failed with empty value at key: {0} ", key);
            return "";
        }
        return asString(db.get(bytes(key)));
    }

    /**
     * Tries to delete a record with the provided key.
     * @param key a value under this key will be removed
     * @return
     * <p>0 on success
     * <p>1 if the record doesn't exist
     * <p>2 when DBException is thrown
     */
    public Integer delete(String key){
        try {
            if (db.get(bytes(key)) == null) {
                LOGGER.log(Level.WARNING, "Tried deleting nonexistent key: {0} ", key);
                return 1;
            } else {
                LOGGER.log(Level.FINE, "Deleting key: {0} ", key);
                db.delete(bytes(key));
            }
        } catch (DBException dbe) {
            LOGGER.log(Level.SEVERE, "Encountered DBException when deleting a key: {0}", key);
            LOGGER.log(Level.SEVERE, "Exception: ", dbe);
            return 2;
        }
        return 0;
    }

    /**
     * Writes a JSONArray as a UTF-8 String into the DB.
     *
     * @param key  a String that will be used to access the value
     * @param data a JSONArray that will be stored
     */
    public void writeJsonArray(String key, JSONArray data) {
        LOGGER.log(Level.FINE, "Writing JSONArray at key: {0} ", key);
        db.put(bytes(key), data.toString().getBytes(StandardCharsets.UTF_8));
    }

    public void writeJsonObject(String key, JSONObject data) {
        LOGGER.log(Level.FINE, "Writing JSONObject at key: {0} ", key);
        db.put(bytes(key), data.toString().getBytes(StandardCharsets.UTF_8));
    }

    public void writeList(String key, List<String> list) {
        LOGGER.log(Level.FINE, "Writing List at key: {0} ", key);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.put(bytes(key), bos.toByteArray());
    }

    @SuppressWarnings("unchecked")
    public List<String> readList(String key) {
        LOGGER.log(Level.FINER, "Reading List at key: {0} ", key);
        try {
            ObjectInputStream oos = new ObjectInputStream(new ByteArrayInputStream(db.get(bytes(key))));
            return new ArrayList<>((List<String>) oos.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /*
    @SuppressWarnings("unchecked")
    public List<Investment> readInvestments(String key) {
        LOGGER.log(Level.FINER, "Reading Investments at key: {0} ", key);
        try {
            ObjectInputStream oos = new ObjectInputStream(new ByteArrayInputStream(db.get(bytes(key))));
            return new ArrayList<>((List<Investment>) oos.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void writeInvestments(String key, List<Investment> list) {
        LOGGER.log(Level.FINE, "Writing Investments at key: {0} ", key);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.put(bytes(key), bos.toByteArray());
    }
    */
    public JSONObject jsonify_competition(List<String> list) throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", list.get(0));
        jo.put("start_date", list.get(1));
        jo.put("end_date", list.get(2));
        jo.put("competition_website", list.get(3));
        jo.put("organizer", list.get(4));
        jo.put("address", list.get(5));
        return jo;
    }

    public JSONObject jsonify_contestant(List<String> list) throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", list.get(0));
        jo.put("surname", list.get(1));
        jo.put("age", list.get(2));
        jo.put("weight", list.get(3));
        jo.put("sex", list.get(4));
        jo.put("weightCategory", list.get(5));
        return jo;
    }


    /**
     * Checks for the existence of data under a given key.
     *
     * @param key a String that will be used to access the value
     * @return true when the value under the key is not null
     */
    public Boolean isRecordPresent(String key) {
        return db.get(bytes(key)) != null;
    }

    /**
     * The readJsonArray method reads an object as JSONArray from the DB.
     * @param key a String that will be used to access the value
     * @return JSONArray under the key or an empty JSONArray object if there's none or isn't a JSONArray
     */
    public JSONArray readJsonArray(String key) {
        LOGGER.log(Level.FINER, "Reading JSONArray at key: {0} ", key);
        byte[] value = db.get(bytes(key));
        if (value == null) {
            LOGGER.log(Level.WARNING, "Tried to read a nonexistent JSONArray at key: {0} ", key);
            return new JSONArray();
        } else {
            try {
                return new JSONArray(asString(db.get(bytes(key))));
            } catch (JSONException jsonexception) {
                LOGGER.log(Level.WARNING, "Tried to read a String as JSONArray at key: {0} ", key);
                return new JSONArray();
            }
        }
    }

    public JSONObject readJsonObject(String key) {
        LOGGER.log(Level.FINER, "Reading JSONObject at key: {0} ", key);
        byte[] value = db.get(bytes(key));
        if (value == null) {
            LOGGER.log(Level.WARNING, "Tried to read a nonexistent JSONObject at key: {0} ", key);
            return new JSONObject();
        } else {
            try {
                return new JSONObject(asString(db.get(bytes(key))));
            } catch (JSONException jsonexception) {
                LOGGER.log(Level.WARNING, "Tried to read a String as JSONAObject at key: {0} ", key);
                return new JSONObject();
            }
        }
    }

    /**
     * Returns Name of the folder in which the DB stores it's files.
     */
    public String getFilename() {
        return FILENAME;
    }

    /**
     * Returns DB cache size in MiB.
     */
    public Integer getCacheSize() {
        return CACHE_SIZE;
    }

    /**
     * Checks whether Google's Snappy compression is turned on
     * <p>https://en.wikipedia.org/wiki/Snappy_(compression)
     */
    public Boolean isCompressionEnabled() {
        return COMPRESSION;
    }
}

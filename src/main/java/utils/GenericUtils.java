package utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.w3c.dom.Document;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kasun on 6/20/18.
 */
public class GenericUtils {

    private static final Logger log = Logger.getLogger(GenericUtils.class);

    private static ThreadLocal<Kryo> kryoTL = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            return new Kryo();
        }
    };

    private static ThreadLocal<SecureRandom> secureRandom = new ThreadLocal<SecureRandom>() {
        protected SecureRandom initialValue() {
            return new SecureRandom();
        }
    };

    /* do not touch if you do not know what you're doing, critical for serialize/deserialize
     * implementation to be stable to retain backward compatibility */
    public static byte[] serializeObject(Object obj) {
        Kryo kryo = kryoTL.get();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try (Output out = new Output(byteOut)) {
            kryo.writeClassAndObject(out, obj);
            out.flush();
            byte[] data = byteOut.toByteArray();
            ByteBuffer result = ByteBuffer.allocate(data.length + Integer.SIZE / 8);
            result.putInt(data.length);
            result.put(data);
            return result.array();
        }
    }
}

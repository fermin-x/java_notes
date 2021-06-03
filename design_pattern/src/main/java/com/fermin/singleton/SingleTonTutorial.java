package com.fermin.singleton;

import java.io.*;

/**
 * Singleton Pattern restricts instantiation of class and makes sure that
 * only one instance exits in java virtual machine.
 * <p>
 * There are different approaches To implement singleton pattern, but all of them have the following concepts
 * Private constructor to restrict instantiation of a class from other classes.
 * Private static variables of the same class that is the only instance of the class.
 * Public static method that returns the instance of the class.
 */
public class SingleTonTutorial {

}


/**
 * serialization and singleton
 * <p>
 * sometimes in distribute systems, we need to implement serializable interface in Singleton class.
 * <p>
 * <p>
 * to overcome this scenario all we need to do it provide the implementation of readResolve() method
 */

class SerializedSingleton implements Serializable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton() {
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }

    // this is crucial when deserializing a SingleTon class.
    // this method should be private
    private Object readResolve() throws ObjectStreamException {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

}

/**
 * the problem with serialized class is that whenever we deserialize it,
 * it will create a new instance of the class.
 */
class SingletonSerializedTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
        out.writeObject(instanceOne);
        out.close();

        //deserailize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject();
        in.close();

        System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
        System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());
    }
}

/**
 * The instance of single class is created at the time of class loading.
 * the drawback is that instance is created even though client application might not use it.
 */
class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}

/**
 * Static block initialization creates instance even before it's being used ,
 * which is quite similar to Eager initialization.
 * Both of them are not best practice to use.
 */
class StaticBlockSingle {
    private static StaticBlockSingle instance;

    static {
        try {
            instance = new StaticBlockSingle();
        } catch (Exception exception) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    private StaticBlockSingle() {
    }

    public static StaticBlockSingle getInstance() {
        return instance;
    }

}

class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

}

/**
 * private inner static class contains the instance of the class.
 * {@link SingletonHelper } is not loaded into memory and only when someone calls the getInstance method,
 * this class get loaded and creates Singleton class instance.
 */
class BillPughSingleton {

    private BillPughSingleton() {
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
}

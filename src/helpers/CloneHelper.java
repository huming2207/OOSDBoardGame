package helpers;

import java.io.*;

public class CloneHelper
{
    /**
     * Perform a deep clone for a certain object
     * @param originalObject Original object to be cloned
     * @return a new cloned object with different reference (but hash code might be the same)
     * @throws IOException Throws when serialisation/deserialization fails
     * @throws ClassNotFoundException Throws when deserialization fails
     */
    public static Object deepClone(Object originalObject) throws IOException, ClassNotFoundException
    {
        // A reasonable way to do deep cloning for the object
        // Simply serialize the object to byte array and convert it back to a new object
        // Ref: https://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object-in-java
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Covert the object to byte[]
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(originalObject);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] byteBuffer = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        // ...then covert it back to a brand new object
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer);
        return new ObjectInputStream(byteArrayInputStream).readObject();
    }
}

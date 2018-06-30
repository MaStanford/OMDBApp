package mark.stanford.com.salesforceapp.data;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Quick and dirty way to persist a small amount of data when performance isnt an issue.
 * Serialize to a string into a text file, and deserialize from a text file
 * I'm using GSON to do the serialization/desrialization.
 *
 * If I had more time, I would either Use SQLite and make my own DAO, or use the new ROOM and livemodel
 * Right now I'm using an observer on the data singleton and storing on the backgorund when it's mutated,
 * With livemodel, I would observe and update the sqlite in better design.
 *
 * Without Room and LiveModel, I could use a sqlite DB and a contentprovider and all network calls would go the
 *  content provider.
 *  Fragments/Activities/Controllers would observe for changes in the contentprovider and update UI accordingly.
 */
public class FileUtils {
    public static void saveObject(Context context, String fileName, Object object){
        FileOutputStream outputStream;
        String fileContents = new Gson().toJson(object);
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(Context context, String fileName, Class klazz){
        StringBuffer objectString = new StringBuffer("");
        try {
            FileInputStream fIn = context.openFileInput(fileName) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;
            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                objectString.append(readString);
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch(IOException ioe) {
            ioe.printStackTrace ( ) ;
            return null;
        }
        return new Gson().fromJson(objectString.toString(), klazz);
    }

    public static Object loadObject(Context context, String fileName, Type klazz){
        StringBuffer objectString = new StringBuffer("");
        try {
            FileInputStream fIn = context.openFileInput(fileName) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;
            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                objectString.append(readString);
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch(IOException ioe) {
            ioe.printStackTrace ( ) ;
            return null;
        }
        return new Gson().fromJson(objectString.toString(), klazz);
    }
}

package com.sergio.imgtopdf;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Sergio on 10/12/17.
 */

public class FileUtils {

    public static final String LOG_ACTIVITY="FileUtils";

    public static final String FOLDER_NAME="DependenciaJudicial";


    public static String folderApp() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ FOLDER_NAME + "/";

        Log.d(LOG_ACTIVITY,"path:"+path);


        File folder = new File(path);
        if (!folder.exists()) {
            Log.d(LOG_ACTIVITY,"folder.exists():"+folder.exists());
            folder.mkdir();
        }

        return path;

    }


    public static String createFolderApp(String newFolder ) {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ FOLDER_NAME +"/"+newFolder+ "/";

        Log.d(LOG_ACTIVITY,"path:"+path);


        File folder = new File(path);
        if (!folder.exists()) {
            Log.d(LOG_ACTIVITY,"folder.exists():"+folder.exists());
            folder.mkdir();
        }

        return path;

    }

    public static Boolean checkExistFolder(String nameFolder) {

        File folder = new File(nameFolder);

        return folder.exists();
    }



}

package swd.SWDProject.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    public static FirebaseAuth firebaseAuth;

    public static void initFirebase() throws IOException {
        System.out.println();
        FileInputStream serviceAccount = new FileInputStream("swd-project-409fb-firebase-adminsdk-5ilkk-cb7fc23b3c.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://swd-project-409fb.firebaseio.com")
                .build();
        firebaseAuth =  FirebaseAuth.getInstance(FirebaseApp.initializeApp(options));
    }
}

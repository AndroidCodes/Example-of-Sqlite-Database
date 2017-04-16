package com.example.peacock.androidsqlite;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.pageelements.Label;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bean.Contact;

public class ListoneActivity extends AppCompatActivity {

    private static final String STORAGE = "storage";

    private static final int REQUEST_CODE1 = 111;

    SharedPreferences sp = null;
    Button btn_Get;
    EditText editText_name;
    DatabaseHandler db = null;
    private List<Contact> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listone);

        db = new DatabaseHandler(this);

        sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STORAGE, "false");
        editor.commit();

        btn_Get = (Button) findViewById(R.id.btn_Get);
        editText_name = (EditText) findViewById(R.id.editText_name);

        itemList = db.getAllContacts();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ListItemAdapter(this, itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        final DatabaseHandler db = new DatabaseHandler(this);
        btn_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Contact> contacts = new ArrayList<Contact>();
                itemList.clear();

                if (!editText_name.getText().toString().isEmpty()) {
                    contacts = db.getSelectedContacts("" + editText_name.getText());
                } else {
                    contacts = db.getAllContacts();
                }

                for (Contact cn : contacts) {
                    //String log = "Id: " + cn.get_id() + " ,Name: " + cn.get_name() + " ,Phone: " + cn.get_phone_number();
                    // Writing Contacts to log
                    //Log.d("Name: ", log);
                    itemList.add(cn);
                }
                recyclerView.setAdapter(mAdapter);
                try {
                    createPdf();
                    //createpdfnew();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createpdfnew() throws Exception {

//        String FILE = Environment.getExternalStorageDirectory()
//                + "/HelloWorld.pdf";

        String folder_main = "/Narola Gems/".concat("Documents");
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {

            f.mkdirs();

        }

        String FILE = Environment.getExternalStorageDirectory().toString().concat(folder_main).concat("/").
                concat(String.valueOf(System.currentTimeMillis())).concat(".pdf");

        // Create a document and set it's properties
        Document objDocument = new Document();
        objDocument.setCreator("DynamicPDFHelloWorld.java");
        objDocument.setAuthor("Your Name");
        objDocument.setTitle("Hello World");

        // Create a page to add to the document
        Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
                54.0f);

        // Create a Label to add to the page
        String strText = "Hello World...\n From DynamicPDF Generator for Java \n DynamicPDF.com";
        Label objLabel = new Label(strText, 0, 0, 504, 100, Font.getHelvetica(), 18,
                TextAlign.CENTER);

        // Add label to page
        objPage.getElements().add(objLabel);

        // Add page to document
        objDocument.getPages().add(objPage);

        try {
            // Outputs the document to file
            objDocument.draw(FILE);
            Toast.makeText(this, "File has been written to :" + FILE,
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,
                    "Error, unable to write to file\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public int createPdf() throws Exception {

        String FILE, No = "", A = "", B = "";

        String folder_main = "/Narola Gems/".concat("Documents");
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {

            f.mkdirs();

        }

        FILE = Environment.getExternalStorageDirectory().toString().concat(folder_main).concat("/").
                concat(String.valueOf(System.currentTimeMillis())).concat(".pdf");

        Document objDocument = new Document();
        objDocument.setCreator("DynamicPDFHelloWorld.java");
        objDocument.setAuthor("Your Name");
        objDocument.setTitle("Hello World");

        // Create a page to add to the document
        Page objPage = new Page(PageSize.B3, PageOrientation.LANDSCAPE, 0.0f);

        int x = 10, Height = 30;

        // Create a Label to add to the page
        Label objLabel = new Label(" Sr", 0, x, 250, Height,
                Font.getHelvetica(), 12, TextAlign.LEFT);

        objPage.getElements().add(objLabel);

        objLabel = new Label("Cert No", 30, x, 250, Height,
                Font.getHelvetica(), 12, TextAlign.LEFT);

        objPage.getElements().add(objLabel);

        objLabel = new Label("Shape", 100, x, 250, Height,
                Font.getHelvetica(), 12, TextAlign.LEFT);

        objPage.getElements().add(objLabel);

        x += Height;


        objLabel = new Label("\n===================================================", 0, x, 250, Height, Font.getHelvetica(),
                12, TextAlign.LEFT);

        // Add label to page
        objPage.getElements().add(objLabel);

        for (int i = 0; i < itemList.size(); i++) {

            x += Height;

            No = "" + itemList.get(i).get_id();

            /*A = itemList.get(i).get_name();

            B = itemList.get(i).get_phone_number();*/

            objLabel = new Label(" " + No, 0, x, 250, Height,
                    Font.getHelvetica(), 12, TextAlign.LEFT);

            objPage.getElements().add(objLabel);

            objLabel = new Label("" + A, 30, x, 250, Height,
                    Font.getHelvetica(), 12, TextAlign.LEFT);

            objPage.getElements().add(objLabel);

            objLabel = new Label("" + B, 100, x, 250, Height,
                    Font.getHelvetica(), 12, TextAlign.LEFT);

            objPage.getElements().add(objLabel);

        }
//        if (!(itemList.size() % 3 == 0))
        objDocument.getPages().add(objPage);
        String result = "";

        try {
            // Outputs the document to file
            objDocument.draw(FILE);
            Toast.makeText(this, "File has been written to :" + FILE,
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,
                    "Error, unable to write to file\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }


        return 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (sp.getString(STORAGE, "").isEmpty() || sp.getString(STORAGE, "").equals("false")) {
                checkForPermission();
            }
        }
    }

    private void checkForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (sp.getString(STORAGE, "").isEmpty() ||
                    sp.getString(STORAGE, "").equals("false")) {
                checkPermission(REQUEST_CODE1);
            }
        }
    }

    private void checkPermission(int RequestCode) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWritePermission = checkSelfPermission(Manifest.permission.
                    WRITE_EXTERNAL_STORAGE);
            int hasReadPermission = checkSelfPermission(Manifest.permission.
                    READ_EXTERNAL_STORAGE);
            List<String> permissions = new ArrayList<String>();
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {

                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            } else {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(STORAGE, "true");
                editor.commit();
            }
            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {

                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

            } else {

                SharedPreferences.Editor editor = sp.edit();
                editor.putString(STORAGE, "true");
                editor.commit();

            }
            if (!permissions.isEmpty()) {

                requestPermissions(permissions.toArray(new String[permissions.size()]), RequestCode);

            }
        }
    }
}


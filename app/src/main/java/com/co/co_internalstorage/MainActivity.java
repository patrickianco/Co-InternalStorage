package com.co.co_internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSave, btnLoad;
    EditText message;
    TextView display;
    FileInputStream fis;
    FileOutputStream fos;
    String filename = "sampleFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText) findViewById(R.id.message);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        display = (TextView) findViewById(R.id.display);
    }

    public void saveMessage(View view){

        try{
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(message.getText().toString().getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "File Created!", Toast.LENGTH_LONG).show();
    }

    public void loadMessage(View view){
        try{
            fis = openFileInput(filename);
            StringBuffer fileContent = new StringBuffer("");

            byte[] buffer = new byte[1024];

            int n;
            while((n = fis.read(buffer)) != -1){
                fileContent.append(new String(buffer, 0, n));
            }
            display.setText("The message is: " + fileContent);
            fis.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

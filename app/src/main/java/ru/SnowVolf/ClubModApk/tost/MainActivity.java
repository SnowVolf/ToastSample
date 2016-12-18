package ru.SnowVolf.ClubModApk.tost;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private EditText jvsrc;//объявляем edittext
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //иначе будет NullPointerException
        jvsrc = (EditText) findViewById(R.id.text_java);
    }
    @Override
    //объявляем меню
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.git){
            VisitGit();
            return true;
        } else if (id == R.id.text2clip){
            Copy2Clipboard();
            return true;
        }
        return onOptionsItemSelected(item);
    }

    public void VisitGit(){
        Uri uri = Uri.parse("http://github.com/SnowVolf/ToastSample");
        Intent gihub = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(gihub);//запускаем арбуз
    }
    public void SimpleTost(View v){
        Toast.makeText(getApplicationContext(),"Просто тост",Toast.LENGTH_LONG).show();
            jvsrc.setText(R.string.sim_tst);//записываем значеие в edittext
            jvsrc.setSelection(jvsrc.getText().length());//ставим курсор в конец строки
    }
    public void HtmlTost(View v){
        Toast.makeText(getApplicationContext(), Html.fromHtml("Просто тост с <tt>Html</tt> <b>оформлением</b>"),
                Toast.LENGTH_LONG).show();
       jvsrc.setText(R.string.html_tst);
        jvsrc.setSelection(jvsrc.getText().length());
    }
    public void HtmlColorTost(View v){
        Toast.makeText(getApplicationContext(), Html.fromHtml("<font color =\"#2196f3\"><i>Цветной</i> текст с <s>оформлением</s></font>"),
                Toast.LENGTH_LONG).show();
        jvsrc.setText(R.string.color_tst);
        jvsrc.setSelection(jvsrc.getText().length());

    }
    public void Copy2Clipboard(){
        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        //копируем текст из jvsrc
        ClipData clipData = ClipData.newPlainText("test", jvsrc.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
        if(clipData != null){
            Toast.makeText(getApplicationContext(), "Скопировано", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed(){
        //прибиваем процесс из памяти
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}

package com.uny.matematika;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.uny.matematika.data.EntityMhs;
import com.uny.matematika.data.MhsParcel;

import java.util.List;

import static com.uny.matematika.MathApp.mhsDatabase;

public class MainActivity extends AppCompatActivity {

    LinearLayout linMain;
    Button btnAdd;
    EditText edtCari;
    Button btnCari;

    List<EntityMhs> mhsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadData();
    }

    void initViews(){
        btnCari = findViewById(R.id.btnCari);
        edtCari = findViewById(R.id.edtCari);
        linMain = findViewById(R.id.linMain);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 111);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMhs(edtCari.getText().toString());
            }
        });
    }



    void searchMhs(String param){
        mhsList = mhsDatabase.getMhsDao().seacrhMhs("%"+param+"%");
        if (mhsList.size() > 0){
            linMain.removeAllViews();
            for (int i = 0; i < mhsList.size(); i++){
                ViewMhsItem viewMhsItem = new ViewMhsItem(this);
                viewMhsItem.setParcel(mhsList.get(i).toParcel());
                linMain.addView(viewMhsItem);
            }
        }
    }

    void loadData(){
        mhsList = mhsDatabase.getMhsDao().getMhs();
        for (int i = 0; i < mhsList.size(); i++){
            ViewMhsItem viewMhsItem = new ViewMhsItem(this);
            viewMhsItem.setParcel(mhsList.get(i).toParcel());
            int finalI = i;
            viewMhsItem.setOnDeleted(new ViewMhsItem.OnDeleted() {
                @Override
                public void onSucceded(boolean isDeleted) {
                    //loadData();
                    linMain.removeView((ViewMhsItem)linMain.getChildAt(finalI));
                }
            });
            linMain.addView(viewMhsItem);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 111){
            MhsParcel mhsParcel = data.getParcelableExtra("mhs");
            ViewMhsItem viewMhsItem = new ViewMhsItem(this);
            viewMhsItem.setParcel(mhsParcel);
            linMain.addView( viewMhsItem, 0);
        } else if (resultCode == RESULT_OK && requestCode == 112){
            MhsParcel mhsParcel = data.getParcelableExtra("mhs");
            for (int i = 0; i < linMain.getChildCount(); i++){
                if (((ViewMhsItem)linMain.getChildAt(i)).getParcel().getId() == mhsParcel.getId()){
                    ((ViewMhsItem)linMain.getChildAt(i)).setParcel(mhsParcel);
                }
            }
        }
    }
}
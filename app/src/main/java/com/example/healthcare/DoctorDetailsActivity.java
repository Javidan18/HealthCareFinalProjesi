package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={

            {"Doctor Nmae : Ali Vefa","Hospital Adress : Konya","Exp ; 5yrs","Mobile No :05528715455","600"},
            {"Doctor Nmae : Cuneyt Aslan","Hospital Adress : Adana","Exp ; 15yrs","Mobile No :05526966632","900"},
            {"Doctor Nmae : Crhis Albert","Hospital Adress : Bolu","Exp ; 8yrs","Mobile No :05529353614","300"},
            {"Doctor Nmae : Yasemin Kara","Hospital Adress : Istanbul","Exp ; 6yrs","Mobile No :05528887965","500"},
            {"Doctor Nmae : Cesur Kara","Hospital Adress : Istanbul","Exp ; 7yrs","Mobile No :05523336963","800"}
    };
    private String[][] doctor_details2={

            {"Doctor Nmae : Ali Sefa","Hospital Adress : Konya","Exp ; 5yrs","Mobile No :05528714155","600"},
            {"Doctor Nmae : Basak Aslan","Hospital Adress : Adana","Exp ; 15yrs","Mobile No :05522966632","900"},
            {"Doctor Nmae : Alex Albert","Hospital Adress : Bolu","Exp ; 8yrs","Mobile No :05523356614","300"},
            {"Doctor Nmae : Deniz Kara","Hospital Adress : Istanbul","Exp ; 6yrs","Mobile No :05528885965","500"},
            {"Doctor Nmae : Korkmaz Kara","Hospital Adress : Istanbul","Exp ; 7yrs","Mobile No :05523331363","800"}
    };
    private String[][] doctor_details3={

            {"Doctor Nmae : Veli Vefa","Hospital Adress : Konya","Exp ; 5yrs","Mobile No :05528714445","600"},
            {"Doctor Nmae : Yamac Aslan","Hospital Adress : Adana","Exp ; 15yrs","Mobile No :05528968632","900"},
            {"Doctor Nmae : Cumali Albert","Hospital Adress : Bolu","Exp ; 8yrs","Mobile No :05529357614","300"},
            {"Doctor Nmae : Sena Kara","Hospital Adress : Istanbul","Exp ; 6yrs","Mobile No :05528886965","500"},
            {"Doctor Nmae : Metin Kara","Hospital Adress : Istanbul","Exp ; 7yrs","Mobile No :05525336363","800"}
    };
    private String[][] doctor_details4={

            {"Doctor Nmae : Idil Vefa","Hospital Adress : Konya","Exp ; 5yrs","Mobile No :05526714455","600"},
            {"Doctor Nmae : Ali Aslan","Hospital Adress : Adana","Exp ; 15yrs","Mobile No :05528966692","900"},
            {"Doctor Nmae : Yavuz Albert","Hospital Adress : Bolu","Exp ; 8yrs","Mobile No :05529356714","300"},
            {"Doctor Nmae : Bella Kara","Hospital Adress : Istanbul","Exp ; 6yrs","Mobile No :05528888565","500"},
            {"Doctor Nmae : Salih Kara","Hospital Adress : Istanbul","Exp ; 7yrs","Mobile No :05523336263","800"}
    };
    private String[][] doctor_details5={

            {"Doctor Nmae : Azer Vefa","Hospital Adress : Konya","Exp ; 5yrs","Mobile No :05528714456","1600"},
            {"Doctor Nmae : Celesun Aslan","Hospital Adress : Adana","Exp ; 15yrs","Mobile No :05528966631","1900"},
            {"Doctor Nmae : Karaca Albert","Hospital Adress : Bolu","Exp ; 8yrs","Mobile No :05529356619","1300"},
            {"Doctor Nmae : Kahraman Kara","Hospital Adress : Istanbul","Exp ; 6yrs","Mobile No :05528888967","1500"},
            {"Doctor Nmae : Sultan Kara","Hospital Adress : Istanbul","Exp ; 7yrs","Mobile No :05523336362","1800"}
    };

    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);


        Intent it=getIntent();
        String title= it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item =new HashMap<String, String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
               new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.ListViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Intent it =new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });


    }
}
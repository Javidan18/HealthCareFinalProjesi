package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCoast;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.textViewBMDPackageName);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnAddToCart=findViewById(R.id.buttonBMDAddToCart);
        //edDetails.setKeyListener(null);
        tvTotalCoast=findViewById(R.id.textViewBMDTotalCoast);
        edDetails=findViewById(R.id.editTextTextBMDMultiline);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCoast.setText("Total Coast : "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedpreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db =new Database(getApplicationContext(),"Healthcare",null,1);

                if(db.checkCart(username,product)==1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));

                }

            }
        });

    }
}
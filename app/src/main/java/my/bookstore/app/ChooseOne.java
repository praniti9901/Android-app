package my.bookstore.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {

    Button Admin,Customer,DeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.bg2, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img3, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img4, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img5, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img6, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img7, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img8, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.bg3, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img9, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img10, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img11, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img12, null), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage = findViewById(R.id.back3);
        bgimage.setBackground(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        Admin = (Button)findViewById(R.id.admin);
        DeliveryPerson = (Button)findViewById(R.id.delivery);
        Customer = (Button)findViewById(R.id.customer);


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail  = new Intent(ChooseOne.this,Adminlogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Adminloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Register  = new Intent(ChooseOne.this,AdminRegistration.class);
                    startActivity(Register);
                }
            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemailcust  = new Intent(ChooseOne.this,Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphonecust  = new Intent(ChooseOne.this,Loginphone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registercust  = new Intent(ChooseOne.this,Registration.class);
                    startActivity(Registercust);
                }

            }
        });

        DeliveryPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,Delivery_Login.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Delivery_Loginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registerdelivery  = new Intent(ChooseOne.this,Delivery_Registration.class);
                    startActivity(Registerdelivery);
                }

            }
        });

    }
}
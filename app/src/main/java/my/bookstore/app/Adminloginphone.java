package my.bookstore.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Adminloginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminloginphone);

        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.otp);
        signinemail=(Button)findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenumber =number;
                Intent b = new Intent(Adminloginphone.this,Adminsendotp.class);

                b.putExtra("Phonenumber",Phonenumber);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminloginphone.this,AdminRegistration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminloginphone.this,Adminlogin.class));
                finish();
            }
        });

    }
}
package lodore.com.lodore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckOutActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnCheckOut;
    EditText edit_email, edit_password;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", edit_email.getText().toString());
        outState.putString("password", edit_password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {


        super.onRestoreInstanceState(savedInstanceState);
        String string = savedInstanceState.getString("email");
        edit_email.setText(string);
        edit_password.setText(savedInstanceState.getString("password"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this, CheckOutCompleteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}

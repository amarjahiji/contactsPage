package com.example.contactspage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;



public class MainActivity extends AppCompatActivity {

    private ImageButton plusbutton;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;
    private View dialog;
    private EditText firstname, lastname, number, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            plusbutton=findViewById(R.id.plusbutton);
                    recyclerView=findViewById(R.id.recyclerView);

                    plusbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog();
                        }
                    });
        }

        private void alertDialog(){
            inflater = LayoutInflater.from ( this);
            dialog = inflater.inflate(R.layout.alertdialogbox, null );
            firstname = dialog.findViewById(R.id.firstName);
            lastname =dialog.findViewById(R.id.lastName) ;
            number = dialog.findViewById(R.id.number);
            email = dialog.findViewById(R.id.email);

            new AlertDialog.Builder(this)
                    .setTitle("Enter your details")
                    .setView(dialog);

        }




    }
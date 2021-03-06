package com.inti.bayviewhotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PortalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logout;

    private FirebaseUser user;
    private DatabaseReference reference;
    private ImageView staffImage, kitchenImage, housekeepingImage;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);


        // Add Logout button
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(PortalActivity.this, MainActivity.class));
            }
        });

        // Display current logged in user
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.displayName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;

                    greetingTextView.setText("Welcome, " + fullName + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PortalActivity.this, "Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });

        // Image view as navigation. Declare variables here

        staffImage = (ImageView) findViewById(R.id.staffImage);
        staffImage.setOnClickListener(this);

        housekeepingImage = (ImageView) findViewById(R.id.housekeepingImage);
        housekeepingImage.setOnClickListener(this);

        kitchenImage = (ImageView) findViewById(R.id.kitchenImage);
        kitchenImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.staffImage:
                startActivity(new Intent(this, RegisterUsers.class));
                break;

            case R.id.housekeepingImage:
                //startActivity(new Intent(this, <ActivityNameHere>.class));
                //example: startActivity(new Intent(this, HousekeepingActivity.class));
                startActivity(new Intent(this, HousekeepingPortal.class));
                break;

            case R.id.kitchenImage:
                startActivity(new Intent(this, KitchenPortal.class));
                break;
        }
    }
}
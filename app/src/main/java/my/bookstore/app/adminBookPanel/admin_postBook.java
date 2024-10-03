package my.bookstore.app.adminBookPanel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import my.bookstore.app.R;

public class admin_postBook extends AppCompatActivity {

    ImageButton imageButton;
    Button post_book;
    Spinner Bookes;
    TextInputLayout desc, qty, pri;
    String descrption, quantity, price, bookes;
    Uri imageuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, dataa;
    FirebaseAuth Fauth;
    StorageReference ref;
    String AdminId, RandomUID, State, City, Area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post_book);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Bookes = findViewById(R.id.bookes);
        desc = findViewById(R.id.description);
        qty = findViewById(R.id.Quantity);
        pri = findViewById(R.id.price);
        post_book = findViewById(R.id.post);
        Fauth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("BookDetails");

        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataa = firebaseDatabase.getInstance().getReference("Admin").child(userid);
            dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Admin adminn = snapshot.getValue(Admin.class);
                    State = adminn.getState();
                    City = adminn.getCity();
                    Area = adminn.getArea();
                    imageButton = findViewById(R.id.image_upload);

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v); // Call image picker
                        }
                    });

                    post_book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bookes = Bookes.getSelectedItem().toString().trim();
                            descrption = desc.getEditText().getText().toString().trim();
                            quantity = qty.getEditText().getText().toString().trim();
                            price = pri.getEditText().getText().toString().trim();

                            if (isValid()) {
                                uploadImage();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Error", "Database error: " + error.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    // Move this method outside of onClickListener
    public void onSelectImageClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    // Handle the selected image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData(); // Get the URI of the selected image
            imageButton.setImageURI(imageuri); // Set the image in the ImageButton
        }
    }

    // Method to upload image to Firebase Storage
    private void uploadImage() {
        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(admin_postBook.this);
            progressDialog.setTitle("Uploading.....");
            progressDialog.show();
            RandomUID = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUID);
            AdminId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            BookDetails info = new BookDetails(bookes, quantity, price, descrption, String.valueOf(uri), RandomUID, AdminId);
                            firebaseDatabase.getInstance().getReference("BookDetails").child(State).child(City).child(Area)
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            Toast.makeText(admin_postBook.this, "Book Posted Successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(admin_postBook.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }
    }

    // Validation method
    private boolean isValid() {
        desc.setErrorEnabled(false);
        desc.setError("");
        qty.setErrorEnabled(false);
        qty.setError("");
        pri.setErrorEnabled(false);
        pri.setError("");

        boolean isValidDescription = false, isValidPrice = false, isValidQuantity = false, isValid = false;
        if (TextUtils.isEmpty(descrption)) {
            desc.setErrorEnabled(true);
            desc.setError("Description is Required");
        } else {
            desc.setError(null);
            isValidDescription = true;
        }
        if (TextUtils.isEmpty(quantity)) {
            qty.setErrorEnabled(true);
            qty.setError("Enter number of Books ");
        } else {
            isValidQuantity = true;
        }
        if (TextUtils.isEmpty(price)) {
            pri.setErrorEnabled(true);
            pri.setError("Please Mention Price");
        } else {
            isValidPrice = true;
        }
        isValid = (isValidDescription && isValidQuantity && isValidPrice);
        return isValid;
    }
}

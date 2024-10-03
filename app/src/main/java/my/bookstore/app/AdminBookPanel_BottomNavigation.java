package my.bookstore.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.bookstore.app.adminBookPanel.AdminHomeFragment;
import my.bookstore.app.adminBookPanel.AdminOrderFragment;
import my.bookstore.app.adminBookPanel.AdminPendingOrderFragment;
import my.bookstore.app.adminBookPanel.AdminProfileFragment;

public class AdminBookPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.admin_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.adminHome) {
            fragment = new AdminHomeFragment();
        } else if (id == R.id.PendingOrders) {
            fragment = new AdminPendingOrderFragment();
        } else if (id == R.id.Orders) {
            fragment = new AdminOrderFragment();
        } else if (id == R.id.adminProfile) {
            fragment = new AdminProfileFragment();
        }


        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}
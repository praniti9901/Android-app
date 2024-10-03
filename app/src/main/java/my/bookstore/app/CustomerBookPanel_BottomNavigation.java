package my.bookstore.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.bookstore.app.customerBookPanel.CustomerCartFragment;
import my.bookstore.app.customerBookPanel.CustomerHomeFragment;
import my.bookstore.app.customerBookPanel.CustomerOrdersFragment;
import my.bookstore.app.customerBookPanel.CustomerProfileFragment;
import my.bookstore.app.customerBookPanel.CustomerTrackFragment;
import my.bookstore.app.adminBookPanel.AdminOrderFragment;
import my.bookstore.app.adminBookPanel.AdminProfileFragment;
import my.bookstore.app.adminBookPanel.AdminHomeFragment;
import my.bookstore.app.adminBookPanel.AdminPendingOrderFragment;


public class CustomerBookPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_book_panel_bottom_navigation);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        if (item.getItemId() == R.id.cust_Home) {
            fragment = new CustomerHomeFragment();
        } else if (item.getItemId() == R.id.cart) {
            fragment = new CustomerCartFragment();
        } else if (item.getItemId() == R.id.cust_profile) {
            fragment = new CustomerProfileFragment();
        } else if (item.getItemId() == R.id.Cust_order) {
            fragment = new CustomerOrdersFragment();
        }

        return loadCustomerFragment(fragment);
    }

    private boolean loadCustomerFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}

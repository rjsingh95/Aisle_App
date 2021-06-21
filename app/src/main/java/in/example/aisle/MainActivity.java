package in.example.aisle;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import org.jetbrains.annotations.NotNull;

import in.example.aisle.Viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity {


     public static HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation();

        homeViewModel = new ViewModelProvider(MainActivity.this).get(HomeViewModel.class);



        homeViewModel.getProfileList();



    }

    private void bottomNavigation() {


        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();
//         navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
                NavigationUI.setupWithNavController(bottomNavigationView, navController);

        addBadge(50,bottomNavigationView,R.id.navigation_matches);
        addBadge(9,bottomNavigationView,R.id.navigation_notes);


        NavOptions navOptions = new NavOptions.Builder()
                .setLaunchSingleTop(true).build();




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                if (navController.getGraph().findNode(item.getItemId()) != null) {
                    navController.navigate(item.getItemId(), null,navOptions);

                }
             if (item.getItemId()==R.id.navigation_notes){

                 removeBadge(bottomNavigationView,R.id.navigation_notes);
             }
                if (item.getItemId()==R.id.navigation_matches){

                    removeBadge(bottomNavigationView,R.id.navigation_matches);
                }
//
                return false;
            }
        });



    }

    private void addBadge(int i, BottomNavigationView bottomNavigationView, int navigation_matches) {
       BadgeDrawable badge= bottomNavigationView.getOrCreateBadge(
               navigation_matches);
        badge.setNumber(i);
        badge.setVisible(true);

    }
    private void removeBadge( BottomNavigationView bottomNavigationView, int navigation_matches) {
        BadgeDrawable badge= bottomNavigationView.getOrCreateBadge(
                navigation_matches);

        badge.setVisible(false);

    }


}
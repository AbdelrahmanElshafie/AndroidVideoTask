package com.shafie.androidvideotask;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.shafie.androidvideotask.databinding.ActivityMainBinding;
import com.shafie.androidvideotask.fragmants.SeedFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load SeedFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SeedFragment())
                .commit();
    }
}

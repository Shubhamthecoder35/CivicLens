package com.example.civiclens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.civiclens.databinding.ActivityLoginBinding;

/**
 * LoginActivity - User Authentication Screen
 * Part of Experiment 2: UI Design
 * Part of Experiment 11/12: Firebase Authentication (future implementation)
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupUI();
    }

    private void setupUI() {
        // Login button click handler (placeholder)
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            // TODO: Implement Firebase Authentication (Experiment 11/12)
            Toast.makeText(this, "Login functionality will be implemented in Experiment 11/12", Toast.LENGTH_SHORT).show();
        });

        // Signup button click handler (placeholder)
        binding.btnSignup.setOnClickListener(v -> {
            // TODO: Navigate to Signup screen or show signup dialog
            Toast.makeText(this, "Signup functionality will be implemented in Experiment 11/12", Toast.LENGTH_SHORT).show();
        });

        // Forgot password click handler (placeholder)
        binding.tvForgotPassword.setOnClickListener(v -> {
            // TODO: Implement password reset
            Toast.makeText(this, "Password reset will be implemented in Experiment 11/12", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

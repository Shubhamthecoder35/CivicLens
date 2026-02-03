package com.example.civiclens;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
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
    private boolean isLoginMode = true;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupUI();
    }

    private void setupUI() {
        // Back
        binding.btnBack.setOnClickListener(v -> finish());

        // Segmented tabs
        binding.tabLogin.setOnClickListener(v -> setMode(true));
        binding.tabSignup.setOnClickListener(v -> setMode(false));

        // Password visibility toggle
        binding.btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility());

        // Forgot password click handler (placeholder)
        binding.tvForgotPassword.setOnClickListener(v -> {
            // TODO: Implement password reset
            Toast.makeText(this, "Password reset will be implemented in Experiment 11/12", Toast.LENGTH_SHORT).show();
        });

        // Primary actions
        binding.btnSignIn.setOnClickListener(v -> {
            // TODO: Implement Firebase Authentication (Experiment 11/12)
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {
            // TODO: Continue signup flow (Experiment 11/12)
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        // Footer links
        binding.tvCreateAccountLink.setOnClickListener(v -> setMode(false));
        binding.tvLoginLink.setOnClickListener(v -> setMode(true));
        styleFooterLinks();

        // Social placeholders
        binding.btnGoogle.setOnClickListener(v -> Toast.makeText(this, "Google Sign-In (coming soon)", Toast.LENGTH_SHORT).show());
        binding.btnApple.setOnClickListener(v -> Toast.makeText(this, "Apple Sign-In (coming soon)", Toast.LENGTH_SHORT).show());
        binding.btnFacebook.setOnClickListener(v -> Toast.makeText(this, "Facebook Sign-In (coming soon)", Toast.LENGTH_SHORT).show());
        binding.btnGoogle2.setOnClickListener(v -> Toast.makeText(this, "Google Sign-In (coming soon)", Toast.LENGTH_SHORT).show());
        binding.btnApple2.setOnClickListener(v -> Toast.makeText(this, "Apple Sign-In (coming soon)", Toast.LENGTH_SHORT).show());
        binding.btnFacebook2.setOnClickListener(v -> Toast.makeText(this, "Facebook Sign-In (coming soon)", Toast.LENGTH_SHORT).show());

        // Initial state
        setMode(true);
    }

    private void styleFooterLinks() {
        SpannableString s1 = new SpannableString("Don't have an account? Create Account");
        int start1 = s1.toString().indexOf("Create Account");
        if (start1 >= 0) {
            s1.setSpan(new StyleSpan(Typeface.BOLD), start1, start1 + "Create Account".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        binding.tvCreateAccountLink.setText(s1);

        SpannableString s2 = new SpannableString("Already have an account? Login");
        int start2 = s2.toString().indexOf("Login");
        if (start2 >= 0) {
            s2.setSpan(new StyleSpan(Typeface.BOLD), start2, start2 + "Login".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        binding.tvLoginLink.setText(s2);
    }

    private void setMode(boolean login) {
        isLoginMode = login;

        binding.loginContainer.setVisibility(login ? android.view.View.VISIBLE : android.view.View.GONE);
        binding.signupContainer.setVisibility(login ? android.view.View.GONE : android.view.View.VISIBLE);

        binding.tabLogin.setBackgroundResource(login ? R.drawable.bg_segment_selected : R.drawable.bg_segment_unselected);
        binding.tabSignup.setBackgroundResource(login ? R.drawable.bg_segment_unselected : R.drawable.bg_segment_selected);

        int selectedText = getColor(R.color.white);
        int unselectedText = getColor(R.color.primary_color);
        binding.tabLogin.setTextColor(login ? selectedText : unselectedText);
        binding.tabSignup.setTextColor(login ? unselectedText : selectedText);

        binding.tvScreenTitle.setText(login ? "Welcome Back" : "Create an Account");
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        int selection = binding.etLoginPassword.getSelectionEnd();

        int type = InputType.TYPE_CLASS_TEXT
                | (isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.etLoginPassword.setInputType(type);
        binding.etLoginPassword.setSelection(Math.max(selection, 0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

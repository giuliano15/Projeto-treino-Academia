// Generated by view binder compiler. Do not edit!
package com.example.academia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.academia.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LoginBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final EditText editTextSenhaQuadrado;

  @NonNull
  public final EditText editTextSenhaRedondo;

  @NonNull
  public final TextView textViewCriarConta;

  @NonNull
  public final TextView textViewEmail;

  @NonNull
  public final TextView textViewEsqueciSenha;

  @NonNull
  public final TextView textViewSenha;

  private LoginBinding(@NonNull RelativeLayout rootView, @NonNull EditText editTextEmail,
      @NonNull EditText editTextSenhaQuadrado, @NonNull EditText editTextSenhaRedondo,
      @NonNull TextView textViewCriarConta, @NonNull TextView textViewEmail,
      @NonNull TextView textViewEsqueciSenha, @NonNull TextView textViewSenha) {
    this.rootView = rootView;
    this.editTextEmail = editTextEmail;
    this.editTextSenhaQuadrado = editTextSenhaQuadrado;
    this.editTextSenhaRedondo = editTextSenhaRedondo;
    this.textViewCriarConta = textViewCriarConta;
    this.textViewEmail = textViewEmail;
    this.textViewEsqueciSenha = textViewEsqueciSenha;
    this.textViewSenha = textViewSenha;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LoginBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextEmail;
      EditText editTextEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.editTextSenhaQuadrado;
      EditText editTextSenhaQuadrado = ViewBindings.findChildViewById(rootView, id);
      if (editTextSenhaQuadrado == null) {
        break missingId;
      }

      id = R.id.editTextSenhaRedondo;
      EditText editTextSenhaRedondo = ViewBindings.findChildViewById(rootView, id);
      if (editTextSenhaRedondo == null) {
        break missingId;
      }

      id = R.id.textViewCriarConta;
      TextView textViewCriarConta = ViewBindings.findChildViewById(rootView, id);
      if (textViewCriarConta == null) {
        break missingId;
      }

      id = R.id.textViewEmail;
      TextView textViewEmail = ViewBindings.findChildViewById(rootView, id);
      if (textViewEmail == null) {
        break missingId;
      }

      id = R.id.textViewEsqueciSenha;
      TextView textViewEsqueciSenha = ViewBindings.findChildViewById(rootView, id);
      if (textViewEsqueciSenha == null) {
        break missingId;
      }

      id = R.id.textViewSenha;
      TextView textViewSenha = ViewBindings.findChildViewById(rootView, id);
      if (textViewSenha == null) {
        break missingId;
      }

      return new LoginBinding((RelativeLayout) rootView, editTextEmail, editTextSenhaQuadrado,
          editTextSenhaRedondo, textViewCriarConta, textViewEmail, textViewEsqueciSenha,
          textViewSenha);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
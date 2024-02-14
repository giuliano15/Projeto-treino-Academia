// Generated by view binder compiler. Do not edit!
package com.example.academia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.academia.R;
import com.example.academia.view.CarouselViewPager;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMain2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final CarouselViewPager carousel;

  @NonNull
  public final ConstraintLayout mainContainer;

  private ActivityMain2Binding(@NonNull ConstraintLayout rootView, @NonNull Button button,
      @NonNull CarouselViewPager carousel, @NonNull ConstraintLayout mainContainer) {
    this.rootView = rootView;
    this.button = button;
    this.carousel = carousel;
    this.mainContainer = mainContainer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMain2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.carousel;
      CarouselViewPager carousel = ViewBindings.findChildViewById(rootView, id);
      if (carousel == null) {
        break missingId;
      }

      ConstraintLayout mainContainer = (ConstraintLayout) rootView;

      return new ActivityMain2Binding((ConstraintLayout) rootView, button, carousel, mainContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
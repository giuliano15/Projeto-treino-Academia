// Generated by view binder compiler. Do not edit!
package com.example.academia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.academia.R;
import com.example.academia.view.ScaledFrameLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCarouselBinding implements ViewBinding {
  @NonNull
  private final ScaledFrameLayout rootView;

  @NonNull
  public final ImageButton backButton;

  @NonNull
  public final RelativeLayout descriptionLayout;

  @NonNull
  public final TextView descriptionView;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageButton infoButton;

  @NonNull
  public final TextView label;

  @NonNull
  public final ScaledFrameLayout rootItem;

  private ItemCarouselBinding(@NonNull ScaledFrameLayout rootView, @NonNull ImageButton backButton,
      @NonNull RelativeLayout descriptionLayout, @NonNull TextView descriptionView,
      @NonNull ImageView imageView, @NonNull ImageButton infoButton, @NonNull TextView label,
      @NonNull ScaledFrameLayout rootItem) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.descriptionLayout = descriptionLayout;
    this.descriptionView = descriptionView;
    this.imageView = imageView;
    this.infoButton = infoButton;
    this.label = label;
    this.rootItem = rootItem;
  }

  @Override
  @NonNull
  public ScaledFrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCarouselBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCarouselBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_carousel, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCarouselBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_button;
      ImageButton backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.description_layout;
      RelativeLayout descriptionLayout = ViewBindings.findChildViewById(rootView, id);
      if (descriptionLayout == null) {
        break missingId;
      }

      id = R.id.description_view;
      TextView descriptionView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionView == null) {
        break missingId;
      }

      id = R.id.image_view;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.info_button;
      ImageButton infoButton = ViewBindings.findChildViewById(rootView, id);
      if (infoButton == null) {
        break missingId;
      }

      id = R.id.label;
      TextView label = ViewBindings.findChildViewById(rootView, id);
      if (label == null) {
        break missingId;
      }

      ScaledFrameLayout rootItem = (ScaledFrameLayout) rootView;

      return new ItemCarouselBinding((ScaledFrameLayout) rootView, backButton, descriptionLayout,
          descriptionView, imageView, infoButton, label, rootItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

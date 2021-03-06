// Generated by view binder compiler. Do not edit!
package com.example.tripa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.tripa.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class FragmentLogout2Binding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout logoutFragment;

  private FragmentLogout2Binding(@NonNull FrameLayout rootView,
      @NonNull FrameLayout logoutFragment) {
    this.rootView = rootView;
    this.logoutFragment = logoutFragment;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLogout2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLogout2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_logout2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLogout2Binding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    FrameLayout logoutFragment = (FrameLayout) rootView;

    return new FragmentLogout2Binding((FrameLayout) rootView, logoutFragment);
  }
}

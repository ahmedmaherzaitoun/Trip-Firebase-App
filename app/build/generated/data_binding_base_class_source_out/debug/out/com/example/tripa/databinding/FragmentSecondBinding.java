// Generated by view binder compiler. Do not edit!
package com.example.tripa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.tripa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSecondBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView datetimedesplay;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final Button go;

  @NonNull
  public final Button seletdateandtime;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final EditText tripdesc;

  private FragmentSecondBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView datetimedesplay, @NonNull EditText editTextTextPersonName,
      @NonNull Button go, @NonNull Button seletdateandtime, @NonNull Spinner spinner,
      @NonNull EditText tripdesc) {
    this.rootView = rootView;
    this.datetimedesplay = datetimedesplay;
    this.editTextTextPersonName = editTextTextPersonName;
    this.go = go;
    this.seletdateandtime = seletdateandtime;
    this.spinner = spinner;
    this.tripdesc = tripdesc;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSecondBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSecondBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_second, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSecondBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.datetimedesplay;
      TextView datetimedesplay = rootView.findViewById(id);
      if (datetimedesplay == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = rootView.findViewById(id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.go;
      Button go = rootView.findViewById(id);
      if (go == null) {
        break missingId;
      }

      id = R.id.seletdateandtime;
      Button seletdateandtime = rootView.findViewById(id);
      if (seletdateandtime == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = rootView.findViewById(id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.tripdesc;
      EditText tripdesc = rootView.findViewById(id);
      if (tripdesc == null) {
        break missingId;
      }

      return new FragmentSecondBinding((ConstraintLayout) rootView, datetimedesplay,
          editTextTextPersonName, go, seletdateandtime, spinner, tripdesc);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
// Generated by view binder compiler. Do not edit!
package com.example.tripa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.tripa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LyListItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView btnDetails;

  @NonNull
  public final ImageView delete;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textViewDate1;

  @NonNull
  public final TextView textViewFrom1;

  @NonNull
  public final TextView textViewName1;

  @NonNull
  public final TextView textViewTo1;

  private LyListItemBinding(@NonNull CardView rootView, @NonNull TextView btnDetails,
      @NonNull ImageView delete, @NonNull TextView textView2, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView textViewDate1, @NonNull TextView textViewFrom1,
      @NonNull TextView textViewName1, @NonNull TextView textViewTo1) {
    this.rootView = rootView;
    this.btnDetails = btnDetails;
    this.delete = delete;
    this.textView2 = textView2;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textViewDate1 = textViewDate1;
    this.textViewFrom1 = textViewFrom1;
    this.textViewName1 = textViewName1;
    this.textViewTo1 = textViewTo1;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LyListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LyListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.ly_list_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LyListItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_details;
      TextView btnDetails = rootView.findViewById(id);
      if (btnDetails == null) {
        break missingId;
      }

      id = R.id.delete;
      ImageView delete = rootView.findViewById(id);
      if (delete == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = rootView.findViewById(id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = rootView.findViewById(id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView_date1;
      TextView textViewDate1 = rootView.findViewById(id);
      if (textViewDate1 == null) {
        break missingId;
      }

      id = R.id.textView_from1;
      TextView textViewFrom1 = rootView.findViewById(id);
      if (textViewFrom1 == null) {
        break missingId;
      }

      id = R.id.textView_name1;
      TextView textViewName1 = rootView.findViewById(id);
      if (textViewName1 == null) {
        break missingId;
      }

      id = R.id.textView_to1;
      TextView textViewTo1 = rootView.findViewById(id);
      if (textViewTo1 == null) {
        break missingId;
      }

      return new LyListItemBinding((CardView) rootView, btnDetails, delete, textView2, textView6,
          textView7, textViewDate1, textViewFrom1, textViewName1, textViewTo1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

package com.WazaBe.HoloEverywhere;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HoloAlertDialogBuilder extends AlertDialog.Builder
{
  private final Context mContext;
  private ImageView mIcon;
  private TextView mMessage;
  private TextView mTitle;

  public HoloAlertDialogBuilder(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    if (Boolean.valueOf(true).booleanValue())
    {
      View localView2 = View.inflate(this.mContext, R.layout.alert_dialog_title, null);
      this.mTitle = ((TextView)localView2.findViewById(R.id.alertTitle));
      this.mIcon = ((ImageView)localView2.findViewById(R.id.icon));
      setCustomTitle(localView2);
      View localView3 = View.inflate(this.mContext, R.layout.alert_dialog_message, null);
      this.mMessage = ((TextView)localView3.findViewById(R.id.message));
      setView(localView3);
      return;
    }
    View localView1 = View.inflate(this.mContext, R.layout.alert_dialog_holo, null);
    this.mTitle = ((TextView)localView1.findViewById(R.id.alertTitle));
    this.mIcon = ((ImageView)localView1.findViewById(R.id.icon));
    this.mMessage = ((TextView)localView1.findViewById(R.id.message));
    setView(localView1);
  }

  public HoloAlertDialogBuilder setIcon(int paramInt)
  {
    this.mIcon.setImageResource(paramInt);
    return this;
  }

  public HoloAlertDialogBuilder setIcon(Drawable paramDrawable)
  {
    this.mIcon.setImageDrawable(paramDrawable);
    return this;
  }

  public HoloAlertDialogBuilder setMessage(int paramInt)
  {
    this.mMessage.setText(paramInt);
    return this;
  }

  public HoloAlertDialogBuilder setMessage(CharSequence paramCharSequence)
  {
    this.mMessage.setText(paramCharSequence);
    return this;
  }

  public HoloAlertDialogBuilder setTitle(int paramInt)
  {
    this.mTitle.setText(paramInt);
    return this;
  }

  public HoloAlertDialogBuilder setTitle(CharSequence paramCharSequence)
  {
    this.mTitle.setText(paramCharSequence);
    return this;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.WazaBe.HoloEverywhere.HoloAlertDialogBuilder
 * JD-Core Version:    0.6.0
 */
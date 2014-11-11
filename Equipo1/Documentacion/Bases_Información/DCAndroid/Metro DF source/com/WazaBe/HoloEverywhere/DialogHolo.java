package com.WazaBe.HoloEverywhere;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class DialogHolo extends Dialog
{
  private final Context mContext;
  private TextView mTitle;

  public DialogHolo(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    View localView = View.inflate(this.mContext, R.layout.alert_dialog_holo, null);
    this.mTitle = ((TextView)localView.findViewById(R.id.alertTitle));
    setContentView(localView);
  }

  public DialogHolo(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    View localView = View.inflate(this.mContext, R.layout.alert_dialog_holo, null);
    this.mTitle = ((TextView)localView.findViewById(R.id.alertTitle));
    setContentView(localView);
  }

  public void setTitle(int paramInt)
  {
    this.mTitle.setText(paramInt);
  }

  public void setTitle(CharSequence paramCharSequence)
  {
    this.mTitle.setText(paramCharSequence);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.WazaBe.HoloEverywhere.DialogHolo
 * JD-Core Version:    0.6.0
 */
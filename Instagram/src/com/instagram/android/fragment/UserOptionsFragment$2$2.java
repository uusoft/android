package com.instagram.android.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.instagram.api.request.LogoutTask;

class UserOptionsFragment$2$2
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    new LogoutTask().execute(new Void[0]);
  }
}

/* Location:           C:\Temp\android\apktool\Instagram_1.1.0\
 * Qualified Name:     com.instagram.android.fragment.UserOptionsFragment.2.2
 * JD-Core Version:    0.6.0
 */
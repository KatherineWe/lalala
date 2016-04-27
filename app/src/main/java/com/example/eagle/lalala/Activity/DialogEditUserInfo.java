package com.example.eagle.lalala.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eagle.lalala.R;

/**
 * Created by NeilHY on 2016/4/26.
 */
public class DialogEditUserInfo extends DialogFragment {

    private TextView title;
    private EditText infoEdit;

    public interface EditInputListener
    {
        void onEditInputComplete(String info);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle bundle=getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edituserinfo, null);

        infoEdit = (EditText) view.findViewById(R.id.dialog_editinfo_et);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setTitle(bundle.getString("title"))

                // Add action buttons
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                EditInputListener listener= (EditInputListener) getActivity();
                                listener.onEditInputComplete(infoEdit.getText().toString());
                            }
                        }).setNegativeButton("取消", null);
        return builder.create();
    }
}

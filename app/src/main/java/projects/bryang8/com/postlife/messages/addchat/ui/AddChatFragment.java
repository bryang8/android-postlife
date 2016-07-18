package projects.bryang8.com.postlife.messages.addchat.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.messages.addchat.AddChatPresenterImpl;
import projects.bryang8.com.postlife.messages.addchat.AddChatPresenter;

public class AddChatFragment extends DialogFragment implements AddChatView, DialogInterface.OnShowListener {
    @Bind(R.id.editTxtEmail)
    EditText editTextEmail;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btnAccept)
    Button btnAccept;

    private AddChatPresenter presenter;
    public AddChatFragment() {
        presenter = new AddChatPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_chat, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void showInput() {
        editTextEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTextEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactadded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        editTextEmail.setText("");
        editTextEmail.setError(getString(R.string.addcontact_error_message));
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog)getDialog();
        if (dialog != null) {

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.addContact(editTextEmail.getText().toString());
                }
            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}

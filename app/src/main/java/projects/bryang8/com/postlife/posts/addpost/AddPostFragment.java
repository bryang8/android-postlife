package projects.bryang8.com.postlife.posts.addpost;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.domain.FirebaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends DialogFragment implements AddPostView, DialogInterface.OnShowListener {
    @Bind(R.id.editTxtContent)
    EditText editTextContent;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btnAccept)
    Button btnAccept;

    private AddPostPresenter presenter;

    public AddPostFragment() {
        presenter = new AddPostPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_post, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void showInput() {
        editTextContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTextContent.setVisibility(View.GONE);
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
    public void postAdded() {
        Toast.makeText(getActivity(), R.string.addPost_message, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Post post = new Post();
                    post.setContent(editTextContent.getText().toString());
                    post.setDate(new Date());
                    post.setEmail_poster(FirebaseHelper.getInstance().getAuthUserEmail().replace(".","_"));
                    post.setLikesNum(0);
                    FirebaseHelper.getInstance().getMyUserReference().child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            post.setName_poster(dataSnapshot.getValue().toString());
                            presenter.addPost(post);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
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

package projects.bryang8.com.postlife.messages.chat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.messages.chat.ChatPresenter;
import projects.bryang8.com.postlife.messages.chat.ChatPresenterImpl;
import projects.bryang8.com.postlife.messages.chat.ui.adapters.ChatAdapter;
import projects.bryang8.com.postlife.entities.ChatMessage;

public class ChatActivity extends AppCompatActivity implements ChatView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.editTxtMessage)
    EditText inputMessage;
    @Bind(R.id.messageRecyclerView)
    RecyclerView recyclerView;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter adapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        setToolbarData(intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupAdapter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    private void setToolbarData(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";

        txtUser.setText(recipient);
        txtStatus.setText(status);

    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        chatPresenter.sendMessage(inputMessage.getText().toString());
        inputMessage.setText("");
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}
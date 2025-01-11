package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
public class HelpSupportActivity extends AppCompatActivity {
    private TextInputEditText etSubject, etMessage;
    private MaterialButton btnSubmitTicket;
    private RecyclerView rvFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);

        initializeViews();
        setupFaqRecyclerView();
        setupClickListeners();
    }

    private void initializeViews() {
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        btnSubmitTicket = findViewById(R.id.btnSubmitTicket);
        rvFaq = findViewById(R.id.rvFaq);
    }

    private void setupFaqRecyclerView() {
        rvFaq.setLayoutManager(new LinearLayoutManager(this));
        List<FaqItem> faqItems = getFaqItems();
        FaqAdapter adapter = new FaqAdapter(faqItems);
        rvFaq.setAdapter(adapter);
    }

    private List<FaqItem> getFaqItems() {
        List<FaqItem> items = new ArrayList<>();
        items.add(new FaqItem("How do I track my car expenses?",
                "You can track your car expenses by using the Expenses Report feature in the app."));
        items.add(new FaqItem("How do I update my profile?",
                "Go to User Settings to update your profile information and preferences."));
        items.add(new FaqItem("How can I contact support?",
                "You can submit a support ticket using the form above, and our team will respond within 24 hours."));
        return items;
    }

    private void setupClickListeners() {
        btnSubmitTicket.setOnClickListener(v -> {
            if (validateInput()) {
                submitSupportTicket();
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (etSubject.getText().toString().trim().isEmpty()) {
            etSubject.setError("Subject is required");
            isValid = false;
        }

        if (etMessage.getText().toString().trim().isEmpty()) {
            etMessage.setError("Message is required");
            isValid = false;
        }

        return isValid;
    }

    private void submitSupportTicket() {
        // Implement support ticket submission logic here
        Toast.makeText(this, "Support ticket submitted successfully", Toast.LENGTH_SHORT).show();
        clearInputs();
    }

    private void clearInputs() {
        etSubject.setText("");
        etMessage.setText("");
    }
}

// FAQ item data class
class FaqItem {
    private String question;
    private String answer;

    public FaqItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
}

// FAQ adapter class
class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {
    private List<FaqItem> faqItems;

    public FaqAdapter(List<FaqItem> faqItems) {
        this.faqItems = faqItems;
    }

    @NonNull
    @Override
    public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faq, parent, false);
        return new FaqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqViewHolder holder, int position) {
        FaqItem item = faqItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return faqItems.size();
    }

    static class FaqViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQuestion;
        private TextView tvAnswer;

        public FaqViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
        }

        public void bind(FaqItem item) {
            tvQuestion.setText(item.getQuestion());
            tvAnswer.setText(item.getAnswer());
        }
    }
}